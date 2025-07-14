package com.example.service;

import java.util.List;

import com.example.model.BookingForm;
import com.example.model.ContactForm;

public interface BookingFormService {
	public BookingForm saveBookingFormService(BookingForm bookingForm);
	public List<BookingForm> readAllBookingsService();
	public void deleteBookingService(int id);

	
}