package com.user.repository;

import com.user.entity.PropertyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyUserRepository extends JpaRepository<PropertyUser, Long> {
}