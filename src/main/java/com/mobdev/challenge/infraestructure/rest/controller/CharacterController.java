package com.mobdev.challenge.infraestructure.rest.controller;

import com.mobdev.challenge.infraestructure.rest.helpers.Error;
import com.mobdev.challenge.infraestructure.rest.helpers.GenericResponse;
import com.mobdev.challenge.application.service.ICharacterService;
import com.mobdev.challenge.infraestructure.rest.dto.GetRootResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ICharacterService iCharacterService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<GenericResponse<GetRootResponseDTO>> getCharacter(@PathVariable("id") Integer id) {

        GenericResponse<GetRootResponseDTO> response = new GenericResponse<GetRootResponseDTO>();

        ArrayList<Error> errors = new ArrayList<>();
        Error error;

        // 1-826 range of character's id
        if (id == null || id < 1 || id > 826) {
            error = setErrors("400", "Character's id is invalid.");
            errors.add(error);
            // If you want use log, here it should be
        }

        if (errors.size() > 0) {
            response.setErrors(errors);
            response.setStatus(HttpStatus.BAD_REQUEST);
            return new ResponseEntity<GenericResponse<GetRootResponseDTO>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            response.setPayload(iCharacterService.getResponse(id));
            response.setStatus(HttpStatus.OK);
        } catch (Exception e) {
            error = setErrors("500", "Internal server error.");
            errors.add(error);
            response.setErrors(errors);
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<GenericResponse<GetRootResponseDTO>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<GenericResponse<GetRootResponseDTO>>(response, HttpStatus.OK);

    }

    public Error setErrors(String code, String description) {
        Error error = new Error();
        error.setCode(code);
        error.setDescription(description);
        return error;
    }
}
