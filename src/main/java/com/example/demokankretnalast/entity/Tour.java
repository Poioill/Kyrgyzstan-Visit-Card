package com.example.demokankretnalast.entity;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("ALL")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @Column(columnDefinition = "text", length = 10000)
    private String description;
    @Column(columnDefinition = "text", length = 10000)
    private String advantages;
    private String region;
    private Integer cost;
    private Integer age;
    @Column(columnDefinition = "text",length = 10000)
    private String notes;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
            mappedBy = "tour")
    private List<Img> images = new ArrayList<>();
    private Long previewImageId;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    public void addImageToProduct(Img img){
        img.setTour(this);
        images.add(img);
    }

    private LocalDateTime dateOfCreated;
    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }
}
