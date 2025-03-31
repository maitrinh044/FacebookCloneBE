package com.example.FacebookCloneBE.Mapper;

import com.example.FacebookCloneBE.DTO.DetailGameDTO.DetailGameDTO;
import com.example.FacebookCloneBE.Model.DetailGame;

public class DetailGameMapper {
    public static DetailGame toEntityPOST(DetailGameDTO detailGameDTO) {
        DetailGame detailGame = new DetailGame();
        // detailGame.setId(detailGameDTO.getId());
        detailGame.setGameID(detailGameDTO.getGameID());
        detailGame.setPlanDuration(detailGameDTO.getPlanDuration());
        detailGame.setPrice(detailGameDTO.getPrice());
        detailGame.setActiveStatus(detailGameDTO.getActiveStatus());
        return detailGame;
    }

    public static DetailGame toEntityPUT(DetailGameDTO detailGameDTO) {
        DetailGame detailGame = new DetailGame();
        detailGame.setId(detailGameDTO.getId());
        detailGame.setGameID(detailGameDTO.getGameID());
        detailGame.setPlanDuration(detailGameDTO.getPlanDuration());
        detailGame.setPrice(detailGameDTO.getPrice());
        detailGame.setActiveStatus(detailGameDTO.getActiveStatus());
        return detailGame;
    }

    public static DetailGameDTO toDTO(DetailGame detailGame) {
        DetailGameDTO detailGameDTO = new DetailGameDTO();
        detailGameDTO.setId(detailGame.getId());
        detailGameDTO.setGameID(detailGame.getGameID());
        detailGameDTO.setPlanDuration(detailGame.getPlanDuration());
        detailGameDTO.setPrice(detailGame.getPrice());
        detailGameDTO.setActiveStatus(detailGame.getActiveStatus());
        return detailGameDTO;
    }

}
