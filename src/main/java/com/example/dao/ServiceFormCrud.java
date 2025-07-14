package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.ServiceForm;

@Repository
public interface ServiceFormCrud extends JpaRepository<ServiceForm, Integer> {
	
}
