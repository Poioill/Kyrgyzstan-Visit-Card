package com.example.demokankretnalast.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Regions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String regionName;
    private String regionSubTitle;
    @Column(length = 500)
    private String introDescription;
    @Column(length = 1000)
    private String aboutRegion;
    @Column(length = 3000)
    private String history;
    @Column(length = 3000)
    private String climate;
    @Column(length = 3000)
    private String tourism;
}
