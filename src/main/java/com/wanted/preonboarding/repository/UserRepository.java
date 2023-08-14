package com.wanted.preonboarding.repository;

import com.wanted.preonboarding.data.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getById(Long id);

    User getByEmail(String email);
}