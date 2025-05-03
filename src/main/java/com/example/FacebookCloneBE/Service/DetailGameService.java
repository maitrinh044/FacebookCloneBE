package com.example.FacebookCloneBE.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.FacebookCloneBE.DTO.DetailGameDTO.DetailGameDTO;
import com.example.FacebookCloneBE.Model.DetailGame;

@Service
public interface DetailGameService {
    public List<DetailGameDTO> getByGame(Long gameId);

    public Optional<DetailGameDTO> addDetailGame(DetailGameDTO detailGameDTO);

    public List<DetailGameDTO> controlActiveStatusByGame(Long gameId);
}
