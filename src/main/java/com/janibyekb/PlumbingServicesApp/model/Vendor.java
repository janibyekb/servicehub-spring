package com.janibyekb.PlumbingServicesApp.model;


import com.janibyekb.PlumbingServicesApp.enums.AuthorityEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="vendor")
public class Vendor extends User{

    private String role = String.valueOf(AuthorityEnum.VENDOR);
    private String specialization;
    private String address;
    private Integer workingRange=0;
    private String qualification;
    private String bio;
    private Double averageRating;
    private Integer totalRating = 0;
    private Boolean isApproved = false;
    private Double serviceFee;

    public Vendor(String name, String hashedPassword, String email) {
        super(name, hashedPassword, email);
    }

    public Vendor(String name, String hashedPassword, String email, String bio) {
        super(name, hashedPassword, email);
        this.bio = bio;
    }
}
