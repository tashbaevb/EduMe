package com.example.prototip.models;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "job")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String title, anons, full_text;
    int price;
    String photoUrl;

    public Job(String title, String anons, String full_text, int price) {
        this.title = title;
        this.anons = anons;
        this.full_text = full_text;
        this.price = price;
    }
}