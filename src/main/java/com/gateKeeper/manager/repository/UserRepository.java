package com.gateKeeper.manager.repository;

import com.gateKeeper.manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
