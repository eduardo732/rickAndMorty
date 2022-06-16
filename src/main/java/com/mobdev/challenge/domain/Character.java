package com.mobdev.challenge.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Character {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String status;
    private String species;
    private String type;
    private Origin origin;
    private LocationCharacter location;
    private String image;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private String[] episode;
    private String url;
    private String created;

}
