package com.mobdev.challenge.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GetOriginResponse {
    private String name;
    private String url;
    private String dimension;
    private String[] residents;
}
