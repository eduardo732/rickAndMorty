package com.mobdev.challenge.infraestructure;

import com.mobdev.challenge.application.service.ICharacterService;
import com.mobdev.challenge.infraestructure.rest.controller.CharacterController;
import com.mobdev.challenge.infraestructure.rest.dto.GetOriginResponseDTO;
import com.mobdev.challenge.infraestructure.rest.dto.GetRootResponseDTO;
import com.mobdev.challenge.infraestructure.rest.helpers.Error;
import com.mobdev.challenge.infraestructure.rest.helpers.GenericResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

public class CharacterControllerTest {

    private static final Integer BAD_CHARACTER_ID = 0;
    private static final Integer INTERNAL_SERVER_ERROR_ID = 5;
    private static final Integer CHARACTER_ID = 1;
    private static final String NAME = "Rick Sanchez";
    private static final String CHARACTER_STATUS = "Alive";
    private static final String SPECIE = "Human";
    private static final String TYPE = "";
    private static final Integer EPISODE_COUNT = 51;
    private static final String ORIGIN_NAME = "Earth (C-137)";
    private static final String ORIGIN_URL = "https://rickandmortyapi.com/api/location/1";
    private static final String ORIGIN_DIMENSION = "Dimension C-137";
    private static final String[] ORIGIN_RESIDENTS = new String[0];

    private static final HttpStatus STATUS = HttpStatus.OK;
    private static final HttpStatus STATUS_BAD_REQUEST = HttpStatus.BAD_REQUEST;
    private static final HttpStatus STATUS_INTERNAL_ERROR = HttpStatus.INTERNAL_SERVER_ERROR;


    public static final GenericResponse<GetRootResponseDTO> GENERIC_RESPONSE = new GenericResponse<GetRootResponseDTO>();
    public static final GetRootResponseDTO GET_ROOT_RESPONSE_DTO = new GetRootResponseDTO();
    public static final GetOriginResponseDTO GET_ORIGIN_RESPONSE_DTO = new GetOriginResponseDTO();

    public static final ArrayList<Error> ERRORS = new ArrayList<>();
    public static final ArrayList<Error> ERRORS_SERVER = new ArrayList<>();


    public static final Error ERROR = new Error();

    public static final Error ERROR_SERVER = new Error();


    @Mock
    ICharacterService iCharacterService;

    @InjectMocks
    CharacterController characterController;

    @Before
    public void init() throws ExecutionException, InterruptedException {
        MockitoAnnotations.initMocks(this);

        //First test SUCCESS
        GET_ORIGIN_RESPONSE_DTO.setName(ORIGIN_NAME);
        GET_ORIGIN_RESPONSE_DTO.setUrl(ORIGIN_URL);
        GET_ORIGIN_RESPONSE_DTO.setDimension(ORIGIN_DIMENSION);
        GET_ORIGIN_RESPONSE_DTO.setResidents(ORIGIN_RESIDENTS);

        GET_ROOT_RESPONSE_DTO.setId(CHARACTER_ID);
        GET_ROOT_RESPONSE_DTO.setName(NAME);
        GET_ROOT_RESPONSE_DTO.setStatus(CHARACTER_STATUS);
        GET_ROOT_RESPONSE_DTO.setSpecies(SPECIE);
        GET_ROOT_RESPONSE_DTO.setType(TYPE);
        GET_ROOT_RESPONSE_DTO.setEpisode_count(EPISODE_COUNT);
        GET_ROOT_RESPONSE_DTO.setOrigin(GET_ORIGIN_RESPONSE_DTO);

        GENERIC_RESPONSE.setStatus(HttpStatus.OK);
        GENERIC_RESPONSE.setErrors(null);
        GENERIC_RESPONSE.setPayload(GET_ROOT_RESPONSE_DTO);

        Mockito.when(iCharacterService.getResponse(CHARACTER_ID)).thenReturn(GET_ROOT_RESPONSE_DTO);

        //Second test Failure for a bad id
        ERROR.setCode("400");
        ERROR.setDescription("Character's id is invalid.");
        ERRORS.add(ERROR);

        //Third test Failure for an internal server error
        ERROR_SERVER.setCode("500");
        ERROR_SERVER.setDescription("Internal server error.");
        ERRORS_SERVER.add(ERROR_SERVER);
        Mockito.when(iCharacterService.getResponse(INTERNAL_SERVER_ERROR_ID)).thenThrow(ExecutionException.class);

    }

    @Test
    public void getCharacterTest() {
        final ResponseEntity<GenericResponse<GetRootResponseDTO>> response = characterController.getCharacter(CHARACTER_ID);
        assertEquals(response.getStatusCode(), STATUS);
        assertEquals(response.getBody().getStatus(), GENERIC_RESPONSE.getStatus());
        assertEquals(response.getBody().getPayload(), GENERIC_RESPONSE.getPayload());
    }

    @Test
    public void getCharacterWithBadIdTest() {
        final ResponseEntity<GenericResponse<GetRootResponseDTO>> response = characterController.getCharacter(BAD_CHARACTER_ID);
        assertEquals(response.getStatusCode(), STATUS_BAD_REQUEST);
        assertEquals(response.getBody().getErrors().size(), ERRORS.size());
    }

    @Test
    public void getCharacterWithInternalServerError() {
        final ResponseEntity<GenericResponse<GetRootResponseDTO>> response = characterController.getCharacter(INTERNAL_SERVER_ERROR_ID);
        assertEquals(response.getStatusCode(), STATUS_INTERNAL_ERROR);
    }
}
