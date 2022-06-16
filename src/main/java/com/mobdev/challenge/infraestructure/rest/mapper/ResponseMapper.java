package com.mobdev.challenge.mapper;

import com.mobdev.challenge.entity.Character;
import com.mobdev.challenge.entity.Location;
import com.mobdev.challenge.vo.GetOriginResponse;
import com.mobdev.challenge.vo.GetRootResponse;

public class ResponseMapper {

    public static GetRootResponse responseRootMapper(Character character, GetOriginResponse getOriginResponse) {

        int episodeCount = character.getEpisode().length;

        return GetRootResponse.builder()
                    .id(character.getId())
                    .name(character.getName())
                    .status(character.getStatus())
                    .species(character.getSpecies())
                    .type(character.getType())
                    .episode_count(episodeCount)
                    .origin(getOriginResponse)
                    .build();
    }

    public static GetOriginResponse responseOriginMapper(Location location) {

        return GetOriginResponse.builder()
                    .name(location.getName())
                    .url(location.getUrl())
                    .dimension(location.getDimension())
                    .residents(location.getResidents())
                    .build();
    }
}
