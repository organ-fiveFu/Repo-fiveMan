package com.nurse.healthy.model.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
@Builder
public class Archive {

    @Id
    @Column(name = "id")
    private Long id;

    private String name;

    private String phone;
}
