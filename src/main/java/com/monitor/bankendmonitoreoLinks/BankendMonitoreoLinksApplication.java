package com.monitor.bankendmonitoreoLinks;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.monitor.bankendmonitoreoLinks.components.ApiMarketing;
import com.monitor.bankendmonitoreoLinks.components.MonitorComponent;


@SpringBootApplication
public class BankendMonitoreoLinksApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(BankendMonitoreoLinksApplication.class, args);
		
		
		MonitorComponent monitorComponent= new MonitorComponent();
			monitorComponent.main();
			
		
	
	}
	
	


}
