package com.mangoplate.mangoplate.repository;

import com.mangoplate.mangoplate.domain.entity.EmailSuccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailSuccessRepository extends JpaRepository<EmailSuccess, Long> {

    Optional<EmailSuccess> findByEmail(String email);

}