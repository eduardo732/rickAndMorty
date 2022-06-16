package com.mobdev.challenge.infraestructure.rest.helpers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Error {

    private String code;
    private String description;

}
