package com.genpact.IncidentTracker.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.genpact.IncidentTracker.model.Ticker;
import com.genpact.IncidentTracker.repository.TickerRepository;

@Service
@Transactional
public class TickerService {
	
	@Autowired
	private TickerRepository repo;
	
	@GetMapping("/tickers")
	public List<Ticker> listAll() {
        return repo.findAll();
    }
     
    public void save(Ticker ticker) {
        repo.save(ticker);
    }
     
    public Ticker get(Integer id) {
        return repo.findById(id).get();
    }
     
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
