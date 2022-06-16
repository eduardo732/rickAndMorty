package com.mobdev.challenge.application.service.impl;

import com.mobdev.challenge.domain.Character;
import com.mobdev.challenge.domain.Location;
import com.mobdev.challenge.infraestructure.rest.mapper.ResponseMapper;
import com.mobdev.challenge.application.service.ICharacterService;
import com.mobdev.challenge.infraestructure.rest.dto.GetOriginResponseDTO;
import com.mobdev.challenge.infraestructure.rest.dto.GetRootResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

@Service
public class CharacterServiceImpl implements ICharacterService {

    @Autowired
    private WebClient webClient;

    public GetRootResponseDTO getResponse(Integer id) throws ExecutionException, InterruptedException {
        Character character = getCharacter(id);
        Location location = getLocation(character.getOrigin().getUrl());
        GetOriginResponseDTO getOriginResponseDTO = ResponseMapper.responseOriginMapper(location);
        return ResponseMapper.responseRootMapper(character, getOriginResponseDTO);
    }
    @Override
    public Character getCharacter(Integer id) throws ExecutionException, InterruptedException {
        return webClient.get()
                .uri("/character/" + id)
                .retrieve()
                .bodyToMono(Character.class)
                .timeout(Duration.ofMillis(10_000))
                .toFuture()
                .get();
    }

    @Override
    public Location getLocation(String url) throws ExecutionException, InterruptedException {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Location.class)
                .timeout(Duration.ofMillis(10_000))
                .toFuture()
                .get();
    }
}
