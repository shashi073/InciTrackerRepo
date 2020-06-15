package com.genpact.IncidentTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genpact.IncidentTracker.model.Ticker;
import com.genpact.IncidentTracker.service.TickerService;

@RestController
public class TickerController {

	@Autowired
	private TickerService service;
	
	@GetMapping("/allTicker")
	public List<Ticker> list() {
	    return service.listAll();
	}
}
