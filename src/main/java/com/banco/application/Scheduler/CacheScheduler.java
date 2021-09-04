package com.banco.application.Scheduler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.banco.application.service.EmpregadoService;

@Component
public class CacheScheduler {

	@Autowired
	EmpregadoService empregadoService;
	
	@Scheduled(fixedRate = 86400000)
	public void evictAllcachesAtIntevals() {
		empregadoService.clearCacheEmpregados();
	}
}
