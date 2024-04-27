package com.janibyekb.PlumbingServicesApp.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;
    @ManyToOne(optional = false)
    private Vendor vendor;

    private Double rating;
    private String text;
    private LocalDate createdOn;
}
