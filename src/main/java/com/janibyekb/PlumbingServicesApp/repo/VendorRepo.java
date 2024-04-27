package com.janibyekb.PlumbingServicesApp.repo;


import com.janibyekb.PlumbingServicesApp.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepo extends JpaRepository<Vendor, Long> {
    Optional<Vendor> findByUsername(String username);
}