package com.mangoplate.mangoplate.repository;

import com.mangoplate.mangoplate.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUserIdAndPassword(String id, String pw);

}
