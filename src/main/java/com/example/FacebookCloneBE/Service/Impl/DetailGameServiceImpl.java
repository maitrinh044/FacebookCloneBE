package com.example.FacebookCloneBE.Service.Impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FacebookCloneBE.DTO.DetailGameDTO.DetailGameDTO;
import com.example.FacebookCloneBE.Enum.ActiveEnum;
import com.example.FacebookCloneBE.Mapper.DetailGameMapper;
import com.example.FacebookCloneBE.Model.DetailGame;
import com.example.FacebookCloneBE.Model.Game;
import com.example.FacebookCloneBE.Repository.DetailGameRepository;
import com.example.FacebookCloneBE.Repository.GameRepository;
import com.example.FacebookCloneBE.Service.DetailGameService;

@Service
public class DetailGameServiceImpl implements DetailGameService {
    @Autowired
    private DetailGameRepository detailGameReopsitory;
    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<DetailGameDTO> getByGame(Long gameId) {
        try {
            Optional<Game> existingGame = gameRepository.findById(gameId);
            if (existingGame.isPresent()) {
                List<DetailGame> list = detailGameReopsitory.findByGameID(existingGame.get());
                return StreamSupport.stream(list.spliterator(), false).map(DetailGameMapper::toDTO).toList();
            }
            return Collections.emptyList();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<DetailGameDTO> addDetailGame(DetailGameDTO detailGameDTO) {
        try {
            DetailGame detailGame = DetailGameMapper.toEntityPOST(detailGameDTO);
            DetailGame addGM = detailGameReopsitory.save(detailGame);
            return Optional.of(DetailGameMapper.toDTO(addGM));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // @Override
    // public List<DetailGameDTO> controlActiveStatusByGame(Long gameId) {
    // try {
    // Optional<Game> gm = gameRepository.findById(gameId);
    // if (gm.isPresent()) {
    // List<DetailGame> list =
    // detailGameReopsitory.controlActiveStatusByGameID(gm.get().getId(),
    // gm.get().getActiveStatus());
    // return list.stream().map(DetailGameMapper::toDTO).toList();
    // }
    // } catch (Exception e) {
    // // TODO: handle exception
    // e.printStackTrace();
    // return Collections.emptyList();
    // }
    // }
    @Override
    public List<DetailGameDTO> controlActiveStatusByGame(Long gameId) {
        try {
            Optional<Game> gm = gameRepository.findById(gameId);
            if (gm.isPresent()) {
                // Tìm kiếm các DetailGame theo gameId
                Game game = gameRepository.findById(gameId).get();
                List<DetailGame> list = detailGameReopsitory.findByGameID(game);

                // Đảo ngược trạng thái cho từng DetailGame
                for (DetailGame detailGame : list) {
                    detailGame.setActiveStatus(detailGame.getActiveStatus().equals(ActiveEnum.ACTIVE)
                            ? ActiveEnum.INACTIVE
                            : ActiveEnum.ACTIVE);
                    detailGameReopsitory.save(detailGame); // Lưu lại trạng thái đã thay đổi
                }

                // Chuyển đổi và trả về danh sách DTO
                return list.stream().map(DetailGameMapper::toDTO).toList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList(); // Trả về danh sách rỗng nếu có lỗi
    }
}
