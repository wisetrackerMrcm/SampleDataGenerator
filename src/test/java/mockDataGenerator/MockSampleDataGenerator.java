package mockDataGenerator;

import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MockSampleDataGenerator {
 
	/***
	 * This value means tester count in order to create sample data.
	 ***/
	private int users = 100;
	
	/***
	 * This value means event count in order to create sample data per tester. 
	 ***/
	private int event_per_user = 20; 
	
	 
	
	/**
	 * SampleData Generator for WebSite SDK   
	 **/
	@Test
	public void webSampleData() { 
		try {
			/**
			 *  generate data configuration
			 ***/
			String serviceId = "service_id_str1";
			String serviceToken = "service_token_str1";
			/*********************************************************************/
			WebsiteMockData mockData = new WebsiteMockData(serviceId, serviceToken, this.users, this.event_per_user);
			mockData.generate(); 
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}  
	
	/**
	 * SampleData Generator for Mobile App   
	 **/
	@Test
	public void appSampleData() {
		try {
			/**
			 *  generate data configuration
			 ***/
			String serviceId = "service_id_str2";
			String serviceToken = "service_token_str2"; 
			/*********************************************************************/
			AppMockData mockData = new AppMockData(serviceId, serviceToken, this.users, this.event_per_user);
			mockData.generate(); 
		} catch (Exception e) {
			e.printStackTrace();
		}  
	} 
	 
}
