package com.example.prototip.models;


import javax.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "application")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class App {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String title, anons, fullText, photoUrl;
    public String getPhotoUrl() {
        return photoUrl;
    }
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
