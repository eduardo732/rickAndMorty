package com.mobdev.challenge.application.service;


import com.mobdev.challenge.domain.Character;
import com.mobdev.challenge.domain.Location;
import com.mobdev.challenge.infraestructure.rest.dto.GetRootResponseDTO;

import java.util.Optional;

public interface ICharacterService {
    Optional<Character> getCharacter(Integer id);
    GetRootResponseDTO getResponse(Integer id);
    Optional<Location> getLocation(String url);
}
