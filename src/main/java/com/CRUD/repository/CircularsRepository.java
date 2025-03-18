package com.CRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CRUD.entity.Circular;

@Repository
public interface CircularsRepository extends JpaRepository<Circular, Long>{

}
