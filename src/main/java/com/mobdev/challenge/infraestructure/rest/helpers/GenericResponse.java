package com.mobdev.challenge.helpers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class GenericResponse<T> {

    private ArrayList<Error> errors;
    private HttpStatus status;
    private T payload;
}
