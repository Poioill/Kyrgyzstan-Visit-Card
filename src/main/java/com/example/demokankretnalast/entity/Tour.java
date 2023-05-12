package com.example.demokankretnalast.entity;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Table(name = "tours")
@AllArgsConstructor
@NoArgsConstructor
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private String region;
}
