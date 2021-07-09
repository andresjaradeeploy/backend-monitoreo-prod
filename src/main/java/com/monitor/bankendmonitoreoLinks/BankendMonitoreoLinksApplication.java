package com.monitor.bankendmonitoreoLinks;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.monitor.bankendmonitoreoLinks.components.ApiMarketing;
import com.monitor.bankendmonitoreoLinks.components.MonitorComponent;


@SpringBootApplication
public class BankendMonitoreoLinksApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(BankendMonitoreoLinksApplication.class, args);
		
		ApiMarketing apiMarketing= new ApiMarketing();
		apiMarketing.main();
		MonitorComponent monitorComponent= new MonitorComponent();
		/*ArrayList<String> urls = new ArrayList<String>();
		urls.add("https://www.carulla.com/agua-brisa-garrafa-6-lts-426as254/p");
		urls.add("https://www.carulla.com/agua-brisa-garrafa-6-lts-426asdsa254/p");
		urls.add("https://www.google.com");*/
		monitorComponent.main();
			
		
	
	}
	
	


}
