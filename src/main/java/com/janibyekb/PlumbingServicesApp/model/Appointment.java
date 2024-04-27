package com.janibyekb.PlumbingServicesApp.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="appointment")
public class Appointment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String description;
    @ManyToOne(optional = false)
    private User user;
    @ManyToOne(optional = true)
    private Vendor vendor;
    private LocalDateTime createdDate=LocalDateTime.now();
    private String location;
    private LocalDateTime date;
    private List<String> imgUrls = new ArrayList<>();
}
