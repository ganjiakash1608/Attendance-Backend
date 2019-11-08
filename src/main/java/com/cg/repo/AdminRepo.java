package com.cg.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Admin;

@Repository
public interface AdminRepo extends CrudRepository<Admin, Integer> {

}

