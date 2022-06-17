package com.mobdev.challenge.infraestructure.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetOriginResponseDTO {
    private String name;
    private String url;
    private String dimension;
    private String[] residents;
}
