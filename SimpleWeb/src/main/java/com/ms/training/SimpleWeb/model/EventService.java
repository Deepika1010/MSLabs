package com.ms.training.SimpleWeb.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
	
	@Autowired
	private EventRepository repo;
	
	public List<Event> getAllEvents()
	{
		return repo.findAll();
	}
	
	public void addEvent(Event e)
	{
		repo.save(e);
	}

}
