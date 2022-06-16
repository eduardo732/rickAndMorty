package com.mobdev.challenge.service;


import com.mobdev.challenge.entity.Character;
import com.mobdev.challenge.entity.Location;
import com.mobdev.challenge.vo.GetRootResponse;
import reactor.core.publisher.Flux;

import java.util.concurrent.ExecutionException;

public interface ICharacterService {
    Character getCharacter(Integer id) throws ExecutionException, InterruptedException;
    GetRootResponse getResponse(Integer id) throws ExecutionException, InterruptedException;
    Location getLocation(String url) throws ExecutionException, InterruptedException;
}
