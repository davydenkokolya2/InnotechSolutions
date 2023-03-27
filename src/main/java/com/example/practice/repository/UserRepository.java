package com.example.practice.repository;

import com.example.practice.entity.UserDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDB, Long> {
    List<UserDB> findByEmail(String email);
}

