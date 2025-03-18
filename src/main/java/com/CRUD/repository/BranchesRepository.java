package com.CRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CRUD.entity.Branches;

@Repository
public interface BranchesRepository extends JpaRepository<Branches, Long>{

}
