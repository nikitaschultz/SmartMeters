package com.example.SPTest.repositories;

import com.example.SPTest.models.Meter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeterRepository extends JpaRepository<Meter, Long> {
}
