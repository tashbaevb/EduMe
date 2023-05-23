package com.example.prototip.models;


import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "feedbacks")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title, fullText;
}
