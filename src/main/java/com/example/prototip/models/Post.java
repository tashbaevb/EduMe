package com.example.prototip.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String title, anons, full_text, faculty;
    int views;
    String photoUrl;
    public String getPhotoUrl() {
        return photoUrl;
    }
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    public Post(String title, String anons, String full_text, String faculty) {
        this.title = title;
        this.anons = anons;
        this.full_text = full_text;
        this.faculty = faculty;
    }
}

