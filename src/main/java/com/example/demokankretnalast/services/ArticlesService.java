package com.example.demokankretnalast.services;

import com.example.demokankretnalast.entity.Articles;
import com.example.demokankretnalast.entity.Img;
import com.example.demokankretnalast.entity.User;
import com.example.demokankretnalast.repositories.ArticlesRepo;
import com.example.demokankretnalast.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.List.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticlesService {
    private final ArticlesRepo articlesRepo;
    private final UserRepo userRepo;

    public List<Articles> listArticles(String title) {
        if (title != null)
            return articlesRepo.findArticlesByTitleContainingIgnoreCase(title);
        return articlesRepo.findAll();
    }

    public void saveArticle(Principal principal,
                            Articles art,
                            MultipartFile file1,
                            MultipartFile file2,
                            MultipartFile file3) throws IOException {
        art.setUser(getUserByPrincipal(principal));
        Img img1;
        Img img2;
        Img img3;
        if (file1.getSize() != 0) {
            img1 = toImageEntity(file1);
            img1.setPreviewImage(true);
            art.addImageToArticle(img1);
        }
        if (file2.getSize() != 0) {
            img2 = toImageEntity(file2);
            art.addImageToArticle(img2);
        }
        if (file3.getSize() != 0) {
            img3 = toImageEntity(file3);
            art.addImageToArticle(img3);
        }
        log.info("Saving new Article. Title: {}; Author email: {}", art.getTitle(), art.getUser().getEmail());
        Articles artFromDB = articlesRepo.save(art);
        artFromDB.setPreviewImageId(artFromDB.getImages().get(0).getId());
        articlesRepo.save(art);
    }

    private Img toImageEntity(MultipartFile file) throws IOException {
        Img img = new Img();
        img.setName(file.getName());
        img.setOriginalFileName(file.getOriginalFilename());
        img.setContentType(file.getContentType());
        img.setSize(file.getSize());
        img.setBytes(file.getBytes());
        return img;
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepo.findByEmail(principal.getName());
    }

    public void deleteArticle(Long id) {
        articlesRepo.deleteById(id);
    }

    public Articles getArticleById(Long id) {
        return articlesRepo.findById(id).orElseThrow(null);
    }


}
