package com.mobdev.challenge.application.service;


import com.mobdev.challenge.domain.Character;
import com.mobdev.challenge.domain.Location;
import com.mobdev.challenge.infraestructure.rest.dto.GetRootResponseDTO;

import java.util.concurrent.ExecutionException;

public interface ICharacterService {
    Character getCharacter(Integer id) throws ExecutionException, InterruptedException;
    GetRootResponseDTO getResponse(Integer id) throws ExecutionException, InterruptedException;
    Location getLocation(String url) throws ExecutionException, InterruptedException;
}
