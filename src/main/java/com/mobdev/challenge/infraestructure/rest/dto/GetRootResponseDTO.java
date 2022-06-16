package com.mobdev.challenge.infraestructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetRootResponseDTO {

    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private Integer episode_count;
    @JsonProperty("origin")
    private GetOriginResponseDTO origin;

}
