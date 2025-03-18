package com.CRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CRUD.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
