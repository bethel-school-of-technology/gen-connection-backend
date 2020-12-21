package com.gencon.auth;

import org.springframework.data.jpa.repository.JpaRepository;

public interface genUserRepository extends JpaRepository<genUser, Integer> {
    
}
