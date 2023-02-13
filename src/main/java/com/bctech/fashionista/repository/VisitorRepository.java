package com.bctech.fashionista.repository;

import com.bctech.fashionista.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {


    Optional<Visitor> findByEmail(String email);
}
