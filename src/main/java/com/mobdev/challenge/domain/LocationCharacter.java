package com.mobdev.challenge.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationCharacter {

    @JsonProperty("name")
    private String name;
    @JsonProperty("url")
    private String url;
}
