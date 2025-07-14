package com.example.service;

import java.util.List;
import com.example.model.ContactForm;

public interface ContactFormService {
	public ContactForm saveContactFormService(ContactForm contactForm);
	public List<ContactForm> readAllContactsService();
	public void deleteContactService(int id);
}
