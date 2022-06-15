package com.mobdev.challenge.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
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
    private Location location;
    private String image;
    private Episode episode;
    private String url;
    private String created;

}
