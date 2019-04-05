package mockDataGenerator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WebDataTemplate  extends JSONObject  implements DataTemplateInf {

 	public WebDataTemplate(
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
		.append("       \"sdk_version\":1, ")
		.append("       \"dSource\":\"webSite\", ")
		.append("       \"buildMode\":\"dev\", ") 
		.append("       \"_wtno\":\"").append(_wtno).append("\", ")
		.append("       \"_wthst\":\"//collector.naver.wisetracker.co.kr\", ") 
		.append("       \"uuid\":\"").append(uuid).append("\", ")
		.append("       \"sid\":\"").append(sid).append("\", ")		
		.append("       \"userId\":\"").append(userId).append("\", ")   
		.append("       \"lng\":\"ko-KR\", ")
		.append("       \"cntr\":\"ko\", ")
		.append("       \"tz\":-9, ")
		.append("       \"screen\":{   ")
		.append("          \"width\":1920, ")
		.append("          \"height\":1050 ")
		.append("       }, ")
		.append("       \"cookies\":{   ")
		.append("          \"isawIntroLayer\":\"true\" ")
		.append("       } ")  
		.append("    } ")
		.append(" } ");  
		try {
 			JSONParser parser = new JSONParser();
			super.putAll((JSONObject)parser.parse(query.toString())); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
  	
 	
 	public void setURL(String url) {
 		try {
 			this.setSessionValue("url", url);
 			String[] t = url.replaceAll("(http|https)://","").split("(/|\\?)");
 			if( t != null ) {
 				this.setSessionValue("domain", t[0]);
 				if( url.indexOf("?") >= 0 ) {
 					this.setSessionValue("queryString", t[t.length-1]); 
 					String[] qmap = t[t.length-1].split("&");
 					if ( qmap != null ) { 
 						JSONObject json = new JSONObject();
 						for( String q : qmap ) {
 							if( q != null && !q.equals("") ) {
 								String[] kv = q.split("=", 2); 
 								if(!kv[1].equals("")) {
 									json.put(kv[0], kv[1]); 
 								}
 							} 
 						}
 						this.setSessionValue("qmap", json); 
 					} 
 				} 
 			}  
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
 	
 	public void setReferer(String ref) {
 		try {
 			this.setSessionValue("wtref", ref);
 			String[] t = ref.replaceAll("(http|https)://","").split("(/|\\?)");
 			if( t != null ) {
  				if( ref.indexOf("?") >= 0 ) {
  					String[] qmap = t[t.length-1].split("&");
 					if ( qmap != null ) { 
 						JSONObject json = new JSONObject();
 						for( String q : qmap ) {
 							if( q != null && !q.equals("") ) {
 								String[] kv = q.split("=", 2); 
 								if(!kv[1].equals("")) {
 									json.put(kv[0], kv[1]);
 									if( kv[0].matches("(wts|wtc|wtm|wtw)")) {
 										this.put(kv[0], kv[1]);
 									}
 								}
 							} 
 						}
 						this.setSessionValue("rmap", json); 
 					} 
 				} 
 			}    
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
 	
 	public void setUserAgent(String[] ua) { 
 		try {
 			this.setSessionValue("ba", ua[0]);
 			this.setSessionValue("os", ua[1]);
 			this.setSessionValue("ua", ua[2]);
 			this.setSessionValue("bv", ua[3]); 
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
