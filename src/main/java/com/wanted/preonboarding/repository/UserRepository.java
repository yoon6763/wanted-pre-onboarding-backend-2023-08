package com.wanted.preonboarding.repository;

import com.wanted.preonboarding.data.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User getById(Long id);

    User getByEmail(String email);
}