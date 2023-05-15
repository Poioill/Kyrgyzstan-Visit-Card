package com.example.demokankretnalast.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@SuppressWarnings("ALL")
@Entity
@Table(name = "usr")
@Data
public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    private boolean active;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Img avatar;
    private String name;
    @Column(length = 1000)
    private String password;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
    mappedBy = "user")
    private List<Tour> tours = new ArrayList<>();
    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }
    // security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

}
