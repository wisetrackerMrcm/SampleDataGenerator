package mockDataGenerator;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WebsiteMockData {
 	
	private MockRandomEventGenerator eventGenerator = new MockRandomEventGenerator();
	private String _wtno; 
	private String _accessToken;
	private int users;
	private int eventsLength;
	
	private FileWriter fileWriter;
	public WebsiteMockData(String serviceNo, String serviceToken,  int users, int eventsLength ) {
		try {
			this._wtno = serviceNo;
			this._accessToken = serviceToken;
			this.users = users;
			this.eventsLength = eventsLength;
			this.fileWriter = new FileWriter(new File("."+File.separator+"dist"+File.separator+String.join("_", "Web_SampleData", String.valueOf(System.nanoTime()), ".data"))); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	private String uuid;
	private String sid;
	
	public void generate() { 
		try { 
			
			// users 
			System.out.println("Program started to create web sample data. "); 
			for( int _u = 0; _u < this.users; _u++) {
				referrer_url_idx = 0;
				
				// uuid & sid
				this.eventGenerator.changeUser();
				String userId = String.join("_", "WebTestUser",String.valueOf(_u)); 
				WebDataTemplate template = new WebDataTemplate(
						this._wtno,
						this._accessToken,
						String.join("_", "u",(UUID.randomUUID()).toString()),
						String.join("_", "s",(UUID.randomUUID()).toString()),
						userId
				); 
				this.setUserAgent(template); 
				template.put("eventTime", System.currentTimeMillis() );
				this.fileWriter.write(template.toJSONString()+"\n"); 				
				template.setSessionValue("isVisitNew", false ); 
				
				// events by user 
				System.out.println("create user " + userId );  
				// create session  
				for( int _e = 0; _e < this.eventsLength; _e++) { 
					this.setUrlAndReferer(template);
					this.eventGenerator.occureRandomEvent(template);
					this.fileWriter.write(template.toJSONString()+"\n"); 
					template.resetSampleData(); 
					System.out.println("random event ("+_e+") " + userId ); 
				}  
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if( this.fileWriter != null ){
				try {
					this.fileWriter.flush();
					this.fileWriter.close(); 
				} catch (Exception e2) {
				}
			}
		}
	}
	 
	/*
	 * URL Source  
	 **/
	private String[] randomUrlArray = {
			"https://test.mysite.com/product/bestshop/bestshopInfo.dw?CATID=all&SEARCHGB=1&SELGB=2&",
			"https://test.mysite.com/search/product/result.dw?prev=&LINK_GB=E&LINK_URL=&LINK_CD=P016094789&keyword=58556028000",
			"https://test.mysite.com/approvalModule/alipay/return_url.jsp?out_trade_no=20190404006595&total_fee=41.58&trade_status=TRADE_FINISHED&sign=82085b135a2acbf564a2595bb6b638d3&trade_no=2019040422001328331026037051&currency=USD&sign_type=MD5",
			"http://test.mysite.com/search/product/result.dw?prev=&LINK_GB=E&LINK_URL=&LINK_CD=P016094846&keyword=乔治阿玛尼200&wts=adChannel&wtc=adCampaign&wtm=adType&wtw=adKeyword",
			"http://test.mysite.com/search/product/result.dw?prev=&LINK_GB=E&LINK_URL=&LINK_CD=P016094846&keyword=迪奥999",
			"https://test.mysite.com/search/product/result.dw?prev=&LINK_GB=E&LINK_URL=&LINK_CD=P016094789&keyword=乔治阿玛尼405",
			"http://test.mysite.com/promotion/exhibition/exhibitionInfo.dw?page=3&disp_grp_cd=&tab=P01&sort=new&keyword=&",
			"http://test.mysite.com/product/bestshop/bestshopInfo.dw?CATID=all&SEARCHGB=1&SELGB=2&wts=adChannel&wtc=adCampaign&wtm=adType&wtw=adKeyword",
			"http://test.mysite.com/search/product/result.dw?prev=&LINK_GB=E&LINK_URL=&LINK_CD=P016094789&keyword=娇兰 abeille",
			"http://test.mysite.com/search/product/result.dw?prev=娇兰 abeille&LINK_GB=E&LINK_URL=&LINK_CD=P016094846&keyword=cpb 隔离",
			"http://test.mysite.com/search/product/result.dw?prev=cpb 隔离&LINK_GB=E&LINK_URL=&LINK_CD=P016094789&keyword=cpb",
			"https://test.mysite.com/btq/chanel/product/list.dw?cateCd1=02&cateCd2=01",
			"https://test.mysite.com/btq/chanel/product/list.dw?cateCd1=03&newProduct=Y",
			"https://test.mysite.com/btq/chanel/product/list.dw?cateCd1=03&cateCd2=08&wts=adChannel&wtc=adCampaign&wtm=adType&wtw=adKeyword",
			"https://test.mysite.com/btq/chanel/product/list.dw?cateCd1=03&cateCd2=14",
			"https://test.mysite.com/btq/chanel/product/list.dw?cateCd1=03&cateCd2=09&wts=adChannel&wtc=adCampaign&wtm=adType&wtw=adKeyword",
			"https://test.mysite.com/btq/chanel/product/list.dw?cateCd1=03&cateCd2=12",
			"https://test.mysite.com/btq/chanel/product/list.dw?cateCd1=03&cateCd2=03",
			"https://test.mysite.com/search/product/result.dw?prev=&LINK_GB=E&LINK_URL=&LINK_CD=P016094846&keyword=伊夫圣罗兰&wts=adChannel&wtc=adCampaign&wtm=adType&wtw=adKeyword",
			"https://test.mysite.com/search/product/result.dw?prev=&LINK_GB=E&LINK_URL=&LINK_CD=P016094846&keyword=乔治阿玛尼",
			"https://test.mysite.com/search/product/result.dw?prev=伊夫圣罗兰&LINK_GB=E&LINK_URL=&LINK_CD=P016094789&bigcatname=&brand=&sex=&benefit=&itemNum=40&page=3&ranktype=best&keyword=mlb&price_min=0&price_max=218&discount_min=0&discount_max=100&price_default_max=218&",
			"https://test.mysite.com/search/product/result.dw?prev=乔治阿玛尼&LINK_GB=E&LINK_URL=&LINK_CD=P016094846&keyword=拍立得",
			"https://test.mysite.com/promotion/exhibition/exhibitionDetail.dw?promCd=P016094787&histryPageIdx=1&histryGrpNo=1",
			"http://test.mysite.com/btq/dior/product/list.dw?cateCd1=01&cateCd2=01&cateCd3=04&wts=adChannel&wtc=adCampaign&wtm=adType&wtw=adKeyword",
			"http://test.mysite.com/btq/dior/product/list.dw?cateCd1=01&cateCd2=01&cateCd3=06",
			"http://test.mysite.com/btq/dior/product/list.dw?cateCd1=01&cateCd2=01&cateCd3=02&wts=adChannel&wtc=adCampaign&wtm=adType&wtw=adKeyword",
			"https://test.mysite.com/product/secret/secret.dw?secretPromCd=S00221&histryPageIdx=1",
			"https://test.mysite.com/product/detail/detailInfo.dw?skucode=2119801908303&brandCd=198A&adminYN=Y&wts=adChannel&wtc=adCampaign&wtm=adType&wtw=adKeyword",
			"https://test.mysite.com/search/product/result.dw?prev=&LINK_GB=E&LINK_URL=&LINK_CD=P016094789&keyword=雅诗兰黛",
			"https://test.mysite.com/search/product/result.dw?prev=&LINK_GB=E&LINK_URL=&LINK_CD=P016094846&keyword=2119801969006",
			"https://test.mysite.com/search/product/result.dw?prev=&LINK_GB=E&LINK_URL=&LINK_CD=P016094789&keyword=2119801969006",
			"https://test.mysite.com/promotion/event/eventInfo.dw?page=1&disp_grp_cd=&tab=E01&sort=new&keyword=&",
			"https://test.mysite.com/search/product/result.dw?prev=&LINK_GB=E&LINK_URL=&LINK_CD=P016094789&keyword=乔治阿玛尼",
			"https://test.mysite.com/category/main/bigCategory.dw?PARENTCATID=10006&brand=&sex=&benefit=&itemNum=40&page=2&ranktype=best&price_min=0&price_max=8,910&discount_min=0&discount_max=100&price_default_max=8910&",
			"http://test.mysite.com/search/product/result.dw?prev=&LINK_GB=E&LINK_URL=&LINK_CD=P016094789&keyword=迪奥",
			"http://test.mysite.com/search/product/result.dw?prev=迪奥&LINK_GB=E&LINK_URL=&LINK_CD=P016094789&keyword=f022703000&wts=adChannel&wtc=adCampaign&wtm=adType&wtw=adKeyword",
			"https://test.mysite.com/search/product/result.dw?prev=迪奥&LINK_GB=E&LINK_URL=&LINK_CD=P016094789&keyword=f022703000",
			"https://test.mysite.com/search/product/result.dw?prev=&LINK_GB=E&LINK_URL=&LINK_CD=P016094789&keyword=ysl",
			"https://test.mysite.com/search/product/result.dw?prev=&LINK_GB=E&LINK_URL=&LINK_CD=P016094846&keyword=3ce&wts=adChannel&wtc=adCampaign&wtm=adType&wtw=adKeyword",
			"https://test.mysite.com/approvalModule/alipay/return_url.jsp?out_trade_no=20190404007316&total_fee=374.00&trade_status=TRADE_FINISHED&sign=f42238509ccd01c5a827baad870f1ea3&trade_no=2019040422001309201035574604&currency=USD&sign_type=MD5",
			"https://test.mysite.com/search/product/result.dw?prev=&LINK_GB=E&LINK_URL=&LINK_CD=P016094789&bigcatname=&brand=&sex=&benefit=&itemNum=40&page=4&ranktype=new&keyword=ysl&price_min=0&price_max=381&discount_min=0&discount_max=100&price_default_max=381&",
			"https://test.mysite.com/product/bestshop/bestshopInfo.dw?CATID=all&SEARCHGB=2&SELGB=2&wts=adChannel&wtc=adCampaign&wtm=adType&wtw=adKeyword",
			"https://test.mysite.com/btq/esteelauder/product/list.dw?cateCd1=01&cateCd2=01&cateCd3=02&wts=adChannel&wtc=adCampaign&wtm=adType&wtw=adKeyword" 	
	}; 
	
	
	private int referrer_url_idx = 0;
	private void setUrlAndReferer(WebDataTemplate bean) { 
		try { 
			
			int rIdx = (int) Math.floor((Math.random()*1000)%43); 
			String url = randomUrlArray[rIdx];
			bean.setURL(url); 
			if( referrer_url_idx > 0 ) {
				bean.setReferer(randomUrlArray[referrer_url_idx]);
			}
			referrer_url_idx = rIdx;
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}  
	
	/**
	 * User-Agent Source 
	 ***/
	private List<String[]> randomAgentArr = new ArrayList<String[]>() {
		{
			add( new String[]{"Chrome","Windows 7","Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36","63.0"});
			add( new String[]{"Chrome","Windows 7","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.25 Safari/537.36 Core/1.70.3650.400 QQBrowser/10.4.3341.400","70.0"});
			add( new String[]{"Chrome","Windows NT","Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36","63.0"});
			add( new String[]{"Chrome","Windows 7","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 SE 2.X MetaSr 1.0","58.0"});
			add( new String[]{"Chrome","Windows 7","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 UBrowser/6.2.4094.1 Safari/537.36","55.0"});
			add( new String[]{"Chrome","Windows NT","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.140 Safari/537.36 Edge/17.17134","64.0"});
			add( new String[]{"Chrome","Windows NT","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36","73.0"});
			add( new String[]{"Chrome","Windows NT","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36","55.0"});
			add( new String[]{"Chrome","Windows NT","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36","73.0"});
			add( new String[]{"Chrome","Windows 7","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36","55.0"});
			add( new String[]{"Chrome","Windows NT","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36","63.0"});
			add( new String[]{"Chrome","Windows 7","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36","63.0"});
			add( new String[]{"Chrome","Windows 7","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36","69.0"});
			add( new String[]{"Chrome","Windows NT","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36","72.0"});
			add( new String[]{"Chrome","Windows 7","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36","72.0"});
			add( new String[]{"Chrome","Windows NT","Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 SE 2.X MetaSr 1.0","58.0"});
			add( new String[]{"Chrome","Windows 7","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36 SE 2.X MetaSr 1.0","35.0"});
			add( new String[]{"Chrome","Windows NT","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 SE 2.X MetaSr 1.0","58.0"});
			add( new String[]{"Microsoft Internet Explorer 11.0","Windows 7","Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko","11.0"});
			add( new String[]{"Chrome","Windows NT","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36","71.0"});
			add( new String[]{"Chrome","Windows 7","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36","73.0"});
			add( new String[]{"Microsoft Internet Explorer 11.0","Windows NT","Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko Core/1.47.516.400 QQBrowser/9.4.8186.400","11.0"});
			add( new String[]{"Chrome","Macintosh","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36","73.0"});
			add( new String[]{"Safari","Macintosh","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_3) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0.3 Safari/605.1.15","605.0"});
			add( new String[]{"Microsoft Internet Explorer 11.0","Windows 7","Mozilla/5.0 (Windows NT 6.1; Trident/7.0; rv:11.0) like Gecko","11.0"});
			add( new String[]{"Chrome","Windows 7","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.90 Safari/537.36 2345Explorer/9.6.0.18627","56.0"});
			add( new String[]{"Chrome","Windows 7","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36","71.0"});
			add( new String[]{"Chrome","Windows NT","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36","65.0"});
			add( new String[]{"Chrome","Windows 7","Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36","70.0"});
			add( new String[]{"Microsoft Internet Explorer 11.0","Windows 7","Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0) like Gecko Core/1.70.3650.400 QQBrowser/10.4.3341.400","11.0"});
			add( new String[]{"Firefox","Windows NT","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0","66.0"});
			add( new String[]{"Microsoft Internet Explorer 10.0","Windows 7","Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.1; WOW64; Trident/6.0) Core/1.63.6788.400 QQBrowser/10.3.2843.400","10.3"});
			add( new String[]{"Chrome","Windows NT","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.75 Safari/537.36","73.0"});
			add( new String[]{"Chrome","Windows NT","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 UBrowser/6.2.4094.1 Safari/537.36","55.0"});
			add( new String[]{"Chrome","Mobile/Android","Mozilla/5.0 (Linux; Android 4.0; GT-I9300 Build/IMM76D) AppleWebKit/534.30 (KHTML, like Gecko) Chrome/59.0.3071.92","59.0"});
			add( new String[]{"Microsoft Internet Explorer 11.0","Windows 7","Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; rv:11.0; UBrowser/6.2.4094.1) like Gecko","11.0"}); 
		} 
	}; 
	
	private void setUserAgent(WebDataTemplate bean) {
		try {
			int rIdx = (int) Math.floor((Math.random()*1000)%35); 
			String[] rd = randomAgentArr.get(rIdx); 
			bean.setUserAgent(rd); 
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
}
