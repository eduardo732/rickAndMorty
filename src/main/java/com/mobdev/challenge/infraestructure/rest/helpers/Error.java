package com.mobdev.challenge.infraestructure.rest.helpers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
public class Error {

    private String code;
    private String description;

}
