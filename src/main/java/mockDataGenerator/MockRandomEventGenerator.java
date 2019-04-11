package mockDataGenerator;

import static org.mockito.Matchers.contains;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONObject;

public class MockRandomEventGenerator {
 	
	public MockRandomEventGenerator() { 
	}

	private int eno = 1;
	private int ino = 0; 
	private int tno = 1; 
	
	public void occureRandomEvent(DataTemplateInf template) { 
		try {
			template.putData("eventTime", System.currentTimeMillis()); 
			int r = (int) Math.floor((Math.random()*1000)%6); 
			switch(r) { 
				case 0 : this.addLogEvent(template);	break; 
				case 1 : this.addLogEventProperties(template);	break;  
				case 2 : this.addUserIdentify(template);	break; 
				case 3 : this.addGroupIdentify(template);	break; 
				case 4 : this.addConversion(template);	break; 
				case 5 : this.addRevenue(template);	break;  
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}   
	
	// 0. logEvent	
	private void addLogEvent(DataTemplateInf template)  throws Exception {		
		template.setSequence(tno++, eno++); 
		JSONObject event = new JSONObject();
		event.put("evtname", String.join("_", "event", String.valueOf((char) ((int)(new Random().nextInt(26)) + 65 ))  )); 
		template.putData("events", event);  
	}
	
	// 1. logEvent + Properties
	private void addLogEventProperties(DataTemplateInf template)  throws Exception  {
		template.setSequence(tno++, eno++);
		JSONObject event = new JSONObject();
		event.put("evtname", String.join("_", "event", String.valueOf((char) ((int)(new Random().nextInt(26)) + 65 ))  ));
		
		// properties 추가. 
		this.addProperties(event); 
		template.putData("events", event);  
	}
	
	// 2. User Identify
	private void addUserIdentify(DataTemplateInf template)  throws Exception  {
		template.setSequence(tno++, ino++); 
		JSONObject userIdentify = new JSONObject();
		this.addIdentifyAction(userIdentify);
		template.putData("userproperties", userIdentify);
	}
	
	// 3. Group Properties
	private void addGroupIdentify(DataTemplateInf template)  throws Exception {
		template.setSequence(tno++, ino++); 
		
		JSONObject groups = new JSONObject();
		groups.put("company", String.join("_", "company", String.valueOf((char) ((int)(new Random().nextInt(26)) + 65 ))  )); 
		template.putData("groups", groups );
		
		if((new Random().nextInt(10)%2) == 0 ) {
			JSONObject groupIdentify = new JSONObject();
			this.addIdentifyAction(groupIdentify); 
			template.putData("groupproperties", groupIdentify);
		} 
	}
	
	
	// 4. Conversion
	private void addConversion(DataTemplateInf template) throws Exception {
		template.setSequence(tno++, eno++); 
		
		JSONObject conversion = new JSONObject();
		conversion.put("cvrname", String.join("_", "conversion", String.valueOf((char) ((int)(new Random().nextInt(26)) + 65 ))  ));
		
		// properties 추가. 
		if((new Random().nextInt(10)%2) == 0 ) {
			this.addProperties(conversion);  
		} 
		template.putData("conversion", conversion);  
 	}
	
	// 5. Revenue 
	private void addRevenue(DataTemplateInf template)  throws Exception  {
		template.setSequence(tno++, eno++);
		
		JSONObject revenue = new JSONObject();
		revenue.put("ordNo", String.join("_", "ordNo", String.valueOf(System.nanoTime()))); 
		revenue.put("eventType", ((new Random().nextInt(10)%2) == 0 )?"purchase":"refund");
		revenue.put("curcy", "KRW");
		
		// products 추가. 
		List<JSONObject> products = new ArrayList<JSONObject>();
		this.addProduct(products);
		if((new Random().nextInt(10)%2) == 0 ) {
			this.addProduct(products);	 
 		}  
		
		revenue.put("products", products );
		
		// properties 추가. 
		if((new Random().nextInt(10)%2) == 0 ) {
			this.addProperties(revenue); 
		} 
		template.putData("revenue", revenue);   
 		
	}
	
	private void addIdentifyAction(JSONObject parent)  throws Exception {
		JSONObject identify = new JSONObject(); 
		int r = (int) Math.floor((Math.random()*1000)%6); 
		switch(r) { 
			// $set
			case 0 : 
					identify.put("setKey", System.nanoTime());
					identify.put("unsetKey", System.nanoTime());
					parent.put("$set", identify);
				break;
			// $setOnce	
			case 1 : 
					identify.put("setOnceKey", System.nanoTime()); 
					parent.put("$setOnce", identify);
				break; 
			// $unset 	
			case 2 : 
					identify.put("unsetKey", 0); 
					parent.put("$unset", identify);
				break;
			// $add	
			case 3 : 
					identify.put("addKey", 1); 
					parent.put("$add", identify); 
				break;
			// $append	
			case 4 : 
					identify.put("appendKey", System.nanoTime()); 
					parent.put("$append", identify);
				break;
				
			// $ prepend	
			case 5 : 
					identify.put("prependKey", System.nanoTime()); 
					parent.put("$prepend", identify);
				break; 
		} 
	}
	
	
	private void addProperties(JSONObject parent) throws Exception {
		JSONObject properties = new JSONObject();
		properties.put("prop", String.join("_", "prop", String.valueOf((char) ((int)(new Random().nextInt(26)) + 65 ))  )); 
		if((new Random().nextInt(10)%2) == 0 ) {
			JSONObject map = new JSONObject();
			map.put("string", "value");
			map.put("numeric", 100);  
			properties.put("map", map); 
		}else { 
			properties.put("array", new ArrayList<String>() {
				{
					add("value1");
					add("value2");
				}
			}); 
		} 
		parent.put("properties", properties); 
	}
	private void addProduct(List<JSONObject> products) throws Exception { 
		JSONObject product = new JSONObject();
		product.put("pg1", String.join("_","cat1", String.valueOf((char) ((int)(new Random().nextInt(26)) + 65 )) ));
		product.put("pg2", String.join("_","cat2", String.valueOf((char) ((int)(new Random().nextInt(26)) + 65 )) ));
		product.put("pg3", String.join("_","cat3", String.valueOf((char) ((int)(new Random().nextInt(26)) + 65 )) ));
		product.put("pg4", String.join("_","cat4", String.valueOf((char) ((int)(new Random().nextInt(26)) + 65 )) ));
		product.put("pnc", String.join("_","product", String.valueOf((char) ((int)(new Random().nextInt(26)) + 65 )) ));
		
		long unit = (new Random().nextInt(10) * 1000);
		long ea = new Random().nextInt(10);
		if( unit <= 0 ) {
			unit = 1000;
		}
		if( ea <= 0 ) {
			ea = 1;
		} 
		product.put("amt", unit * ea );
		product.put("ea", ea );
		product.put("ordPno", String.join("_", "prdOrdNo", String.valueOf(System.nanoTime()))); 
		if((new Random().nextInt(10)%2) == 0 ) {
			this.addProperties(product);	 
 		}  
		products.add(product); 
	}
	
	public void changeUser() { 
		this.eno = 1;
		this.ino = 0; 
		this.tno = 1; 
	}
	
}
