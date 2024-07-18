package com.gateKeeper.manager.repository;

import com.gateKeeper.manager.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    public Boolean existsByEmail(String email);

}
