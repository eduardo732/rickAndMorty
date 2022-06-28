package com.mobdev.challenge.application.service.impl;

import com.mobdev.challenge.domain.Character;
import com.mobdev.challenge.domain.Location;
import com.mobdev.challenge.infraestructure.rest.mapper.ResponseMapper;
import com.mobdev.challenge.application.service.ICharacterService;
import com.mobdev.challenge.infraestructure.rest.dto.GetOriginResponseDTO;
import com.mobdev.challenge.infraestructure.rest.dto.GetRootResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements ICharacterService {


    private final WebClient webClient;


    public CharacterServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public GetRootResponseDTO getResponse(Integer id) {
        Optional<Character> character = Optional.ofNullable(getCharacter(id)
                .orElseThrow(() -> new RuntimeException("Invalid Character's id")));
        Optional<Location> location = Optional.ofNullable(getLocation(character.get().getOrigin().getUrl())
                .orElseThrow(() -> new RuntimeException("Invalid origin")));
        GetOriginResponseDTO getOriginResponseDTO = ResponseMapper.responseOriginMapper(location.get());
        return ResponseMapper.responseRootMapper(character.get(), getOriginResponseDTO);
    }

    @Override
    public Optional<Character> getCharacter(Integer id) {
        return webClient.get()
                .uri("/character/" + id)
                .retrieve()
                .bodyToMono(Character.class)
                .timeout(Duration.ofMillis(10_000))
                .onErrorResume(WebClientResponseException.NotFound.class, notFound -> Mono.empty())
                .blockOptional();

    }

    @Override
    public Optional<Location> getLocation(String url) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Location.class)
                .timeout(Duration.ofMillis(10_000))
                .onErrorResume(WebClientResponseException.NotFound.class, notFound -> Mono.empty())
                .blockOptional();
    }
}
