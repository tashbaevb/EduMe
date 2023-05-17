package com.example.prototip.models;


import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "application")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class App {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title, anons, fullText, photoUrl;

    public App(String title, String anons, String fullText) {
        this.title = title;
        this.anons = anons;
        this.fullText = fullText;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
