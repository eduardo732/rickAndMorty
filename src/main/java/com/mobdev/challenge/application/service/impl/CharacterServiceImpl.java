package com.mobdev.challenge.service.impl;

import com.mobdev.challenge.entity.Character;
import com.mobdev.challenge.entity.Location;
import com.mobdev.challenge.mapper.ResponseMapper;
import com.mobdev.challenge.service.ICharacterService;
import com.mobdev.challenge.vo.GetOriginResponse;
import com.mobdev.challenge.vo.GetRootResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

@Service
public class CharacterServiceImpl implements ICharacterService {

    @Autowired
    private WebClient webClient;

    public GetRootResponse getResponse(Integer id) throws ExecutionException, InterruptedException {
        Character character = getCharacter(id);
        Location location = getLocation(character.getOrigin().getUrl());
        GetOriginResponse getOriginResponse = ResponseMapper.responseOriginMapper(location);
        return ResponseMapper.responseRootMapper(character, getOriginResponse);
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
