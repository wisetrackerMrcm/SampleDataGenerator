package mockDataGenerator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class AppDataTemplate extends JSONObject implements DataTemplateInf {

 	public AppDataTemplate(
			String _wtno,
			String uuid,
			String sid,
			String userId
	) { 
		StringBuilder query = new StringBuilder();
		query
		.append(" {   ")
		.append("    \"sequence\":0, ")
		.append("    \"eventId\":0, ")
		.append("    \"session\":{   ") 
		.append("         \"sdk_version\":\"1\",")
		.append("         \"apvr\":\"1.0\",")
		.append("         \"userMode\":3,")
		.append("         \"_wthst\":\"collector.naver.wisetracker.co.kr\",")
		.append("         \"_wtno\":\"").append(_wtno).append("\",")
		.append("         \"uuid\":\"").append(uuid).append("\", ")
		.append("         \"sid\":\"").append(sid).append("\", ")		
		.append("         \"userId\":\"").append(userId).append("\", ")  
		.append("         \"_wtDebug\":true,")  
		.append("         \"tz\":-9, ")
		.append("         \"adidChanage\":\"N\",")
		.append("         \"buildMode\":\"dev\"")
		.append("         \"pkg\":\"com.wisetracker.app\",")
		.append("         \"advtFlag\":0") 		

		
//		.append("         \"wtc\":\"adCampaign\",")
//		.append("         \"wtclkTime\":\"1533607852278\",")
//		.append("         \"wtm\":\"adType\",")
//		.append("         \"wtref\":\"wts=adSource&wtc=adCampaign&wtm=adType&wtw=keyword&wtclkTime=1533607852278&_wtcid=DOP102_3443436477568846_bd3660eb78fd43bbaa3883354a4883c9&_wtckp=1440\",")
//		.append("         \"wts\":\"adSource\",")
//		.append("         \"wtw\":\"keyword\",")
//		.append("         \"pushNo\":\"\"") 
		
		
		.append("    } ")
		.append(" } ");  
		
		try {
  			JSONParser parser = new JSONParser();
			super.putAll((JSONObject)parser.parse(query.toString()));  
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
 	
 	public void setUserDevice(String[] ua) { 
 		try {
 			//  			add(new String[]{"b67146cf-0193-4da8-b1d3-dc529392709b","1080*2076","SM-G950N","28","KO","KR","SKTelecom","4.1.6","6","_N_","1","KR"});
 			
 			this.setSessionValue("advtId", ua[0]); // advtId
 			this.setSessionValue("phone", ua[2]); // phone 
 			this.setSessionValue("plat", (ua[2].matches(".*i(Phone|Pad).*"))?"IOS":"AOS"); // plat
 			this.setSessionValue("vendorId", UUID.randomUUID().toString() ); // vendorId
 			this.setSessionValue("os", ua[3]); // os
 			if((new Random().nextInt(10)%2) == 0 ) {
 	 			this.setSessionValue("gps", "Y"); // gps 
 			}else {
 	 			this.setSessionValue("gps", "N"); // gps 
 			}
 			this.setSessionValue("cari", ua[6]); // cari
 			this.setSessionValue("isWifi", Integer.parseInt(ua[10])); // isWifi
 			this.setSessionValue("inch", Integer.parseInt(ua[8])); // inch
 			this.setSessionValue("installTime", System.currentTimeMillis());
 			this.setSessionValue("lng", ua[4]); // lng
 			this.setSessionValue("cntr", ua[5]); // cntr
 			this.setSessionValue("sr", ua[1]); // sr
 			this.setSessionValue("osvr", "1.0"); // sr 
		} catch (Exception e) {
			e.printStackTrace();
		}
 	} 
 	
 	
 	public void resetSampleData() {
 		try {
 			List<String> _arr = new ArrayList<String>(); 
 			Iterator<String> itr = this.keySet().iterator();
			while(itr.hasNext()) {
				_arr.add(itr.next());
			}
			
			if( _arr.size() > 0 ) {
				for( String key : _arr ) {
					if( !key.equals("session") ) {
						this.remove(key);
					}					
				}
			}  
 		} catch (Exception e) {
			e.printStackTrace();
		}
 		
 	}
 	
 	private void setSessionValue(String key, Object value) throws Exception { 
 		Map<String,Object> session = (Map<String, Object>)this.get("session");
 		session.put(key, value);  
 	}

 	@Override
 	public void setSequence(int seq, int eeq) {
 		try {
 			this.put("sequence", seq );
 			this.put("eventId", eeq);  
		} catch (Exception e) {
			e.printStackTrace();
		}
 	} 
 	
	@Override
	public void putData(String key, Object value) {
		this.put(key, value);
	}
}
