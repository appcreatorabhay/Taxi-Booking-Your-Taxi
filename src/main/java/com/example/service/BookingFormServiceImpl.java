package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.BookingFormCrud;
import com.example.model.BookingForm;
@Service
public class BookingFormServiceImpl implements BookingFormService {
	private BookingFormCrud  bookingFormCrud;
	
    @Autowired
	public void setBookingFormCrud(BookingFormCrud bookingFormCrud) {
		this.bookingFormCrud = bookingFormCrud;
	}


	@Override
	public BookingForm saveBookingFormService(BookingForm bookingForm) {
		
		return bookingFormCrud.save(bookingForm);
		
		
		
	}


	@Override
	public List<BookingForm> readAllBookingsService() {
		// TODO Auto-generated method stub
		return bookingFormCrud.findAll();
	}


	@Override
	public void deleteBookingService(int id) {
		bookingFormCrud.deleteById(id);
	}

}