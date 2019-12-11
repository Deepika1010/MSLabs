package com.ms.training.SimpleWeb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ms.training.SimpleWeb.model.Event;
import com.ms.training.SimpleWeb.model.EventService;

@RestController
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@RequestMapping(method = RequestMethod.GET, path = "/event")
	public List<Event> getAllEvents()
	{
		return eventService.getAllEvents();
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/event")
	public ResponseEntity<String> saveEvent(@RequestBody Event event)
	{
		eventService.addEvent(event);
		
		return ResponseEntity.ok().build();
	}

}
