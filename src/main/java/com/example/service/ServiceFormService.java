package com.example.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.model.ServiceForm;

public interface ServiceFormService {
	public ServiceForm addService(ServiceForm serviceForm, MultipartFile multipartFile) throws Exception;
	public List<ServiceForm> readAllServices();
}
