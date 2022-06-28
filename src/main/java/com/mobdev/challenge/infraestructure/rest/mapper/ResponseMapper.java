package com.mobdev.challenge.infraestructure.rest.mapper;

import com.mobdev.challenge.domain.Character;
import com.mobdev.challenge.domain.Location;
import com.mobdev.challenge.infraestructure.rest.dto.GetOriginResponseDTO;
import com.mobdev.challenge.infraestructure.rest.dto.GetRootResponseDTO;

import java.util.ArrayList;

public class ResponseMapper {

    public static GetRootResponseDTO responseRootMapper(Character character, GetOriginResponseDTO getOriginResponseDTO) {

        int episodeCount = character.getEpisode().length;

        return GetRootResponseDTO.builder()
                .id(character.getId())
                .name(character.getName())
                .status(character.getStatus())
                .species(character.getSpecies())
                .type(character.getType())
                .episode_count(episodeCount)
                .origin(getOriginResponseDTO)
                .build();
    }

    public static GetOriginResponseDTO responseOriginMapper(Location location) {
        if (location.getId() == null) {
            return GetOriginResponseDTO.builder()
                    .name("")
                    .url("")
                    .dimension("")
                    .residents(new String[0])
                    .build();
        }
        if (location.getResidents().length == 0) {
            return GetOriginResponseDTO.builder()
                    .name(location.getName())
                    .url(location.getUrl())
                    .dimension(location.getDimension())
                    .residents(new String[0])
                    .build();
        }
        return GetOriginResponseDTO.builder()
                .name(location.getName())
                .url(location.getUrl())
                .dimension(location.getDimension())
                .residents(location.getResidents())
                .build();
    }
}
