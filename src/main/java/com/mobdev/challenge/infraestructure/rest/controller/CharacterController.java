package com.mobdev.challenge.infraestructure.rest.controller;

import com.mobdev.challenge.infraestructure.rest.helpers.Error;
import com.mobdev.challenge.infraestructure.rest.helpers.GenericResponse;
import com.mobdev.challenge.application.service.ICharacterService;
import com.mobdev.challenge.infraestructure.rest.dto.GetRootResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/v1/character")
public class CharacterController {


    private ICharacterService iCharacterService;
    private Error error;

    public CharacterController(ICharacterService iCharacterService, Error error) {
        this.iCharacterService = iCharacterService;
        this.error = error;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<GenericResponse<GetRootResponseDTO>> getCharacter(@PathVariable("id") Integer id) {

        GenericResponse<GetRootResponseDTO> response = new GenericResponse<GetRootResponseDTO>();

        ArrayList<Error> errors = new ArrayList<>();

        try {
            response.setPayload(iCharacterService.getResponse(id));
            response.setStatus(HttpStatus.OK);
        } catch (Exception e) {
            error.setDescription(e.getMessage());
            error.setCode("400-500");
            errors.add(error);
            response.setErrors(errors);
            response.setStatus(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<GenericResponse<GetRootResponseDTO>>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<GenericResponse<GetRootResponseDTO>>(response, HttpStatus.OK);

    }

}
