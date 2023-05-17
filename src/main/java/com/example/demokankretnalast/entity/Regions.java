package com.example.demokankretnalast.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Regions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String capital;
    private String regionFullName;
    @Column(length = 10000)
    private String introDescription;
    @Column(length = 10000)
    private String aboutRegion;
    @Column(length = 10000)
    private String history;
    @Column(length = 10000)
    private String climate;
    @Column(length = 10000)
    private String tourism;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "regions")
    private List<ImgRegion> imagesRegion = new ArrayList<>();

    public void addImageToRegion(ImgRegion imgRegion){
        imgRegion.setRegions(this);
        imagesRegion.add(imgRegion);
    }
}
