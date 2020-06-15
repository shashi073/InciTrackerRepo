package com.genpact.IncidentTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.genpact.IncidentTracker.model.Ticker;

public interface TickerRepository extends JpaRepository<Ticker, Integer>{

}
