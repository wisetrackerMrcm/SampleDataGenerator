package mockDataGenerator;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.simple.JSONObject;

public class AppMockData {
 	
	private MockRandomEventGenerator eventGenerator = new MockRandomEventGenerator();
	private String _wtno;
	private String _accessToken;

	private int users;
	private int eventsLength;
	
	private FileWriter fileWriter;
	public AppMockData(String serviceNo, String serviceToken, int users, int eventsLength ) {
		try {
			this._wtno = serviceNo;
			this._accessToken = serviceToken;
			this.users = users;
			this.eventsLength = eventsLength;
			this.fileWriter = new FileWriter(new File("."+File.separator+"dist"+File.separator+String.join("_", "App_SampleData", String.valueOf(System.nanoTime()), ".data"))); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	private String uuid;
	private String sid;
	
	public void generate() { 
		try { 
			
			// users 
			System.out.println("Program started to create app sample data. ");
			for( int _u = 0; _u < this.users; _u++) { 
				// uuid & sid 
				this.eventGenerator.changeUser();
				String userId = String.join("_","AppTestUser",String.valueOf(_u));
				AppDataTemplate template = new AppDataTemplate(
						this._wtno,
						this._accessToken,
						String.join("_", "u",(UUID.randomUUID()).toString()),
						String.join("_", "s",(UUID.randomUUID()).toString()),
						userId
				);  
				this.setUserDevice(template);
				template.put("eventTime", System.currentTimeMillis() );
				this.fileWriter.write(template.toJSONString()+"\n"); 				
				template.setSessionValue("isVisitNew", false );  
 				// events by user
				System.out.println("create user " + userId ); 
				for( int _e = 0; _e < this.eventsLength; _e++) { 
 					this.eventGenerator.occureRandomEvent(template);
					this.fileWriter.write(template.toJSONString()+"\n"); 
					template.resetSampleData();
					System.out.println("random event ("+_e+") " + String.join("_","AppTestUser",String.valueOf(_u)) ); 
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
 	
 	private List<String[]> randomUserDevice = new ArrayList<String[]>(){
 		{ 
 			add(new String[]{"b67146cf-0193-4da8-b1d3-dc529392709b","1080*2076","SM-G950N","28","KO","KR","SKTelecom","4.1.6","6","_N_","1","KR"});
 			add(new String[]{"e46b1a78-26f2-4cb5-845a-c55dc73087ca","1080*2220","SM-G965N","26","KO","KR","SKTelecom","4.1.6","6","_N_","2","KR"});
 			add(new String[]{"7da4106f-854d-4d18-a8c2-93f63680377d","1080*1920","SM-G930K","26","KO","KR","KT","4.1.6","6","_N_","1","KR"});
 			add(new String[]{"6d768fc8-1d19-47cf-8ae7-8f1630b4f8f2","1080*2076","SM-G960N","26","KO","KR","SKTelecom","4.1.6","6","_N_","1","KR"});
 			add(new String[]{"6d55f51a-acf3-4c84-905a-3f4a08cb02ab","1080*2076","SM-G960N","28","KO","KR","LG U+","4.1.6","6","_N_","1","KR"});
 			add(new String[]{"c24c0f4f-5b8b-4a9d-b274-ac27f70e5b2f","1080*2094","SM-G955N","28","KO","KR","KT","4.1.6","6","_N_","2","KR"});
 			add(new String[]{"041990e1-d950-4db3-bab1-5d91e7078540","1080*2094","SM-N960N","28","KO","KR","KT","4.1.6","6","_N_","1","KR"});
 			add(new String[]{"97207f7d-db26-428d-a74b-98f6b07506ee","1440*2952","LM-G710N","26","KO","KR","SKTelecom","4.1.6","6","_N_","1","KR"});
 			add(new String[]{"9ee32bd8-4cc2-4c41-b64c-644a4d0b77fd","1080*1920","SM-G930K","26","KO","KR","KT","4.1.6","6","_N_","2","KR"});
 			add(new String[]{"9d5ebc39-dca3-45ba-99a2-d3d69bc5b728","1080*2076","SM-G950N","26","KO","KR","SKTelecom","4.1.6","6","_N_","1","KR"});
 			add(new String[]{"fc40735b-0891-467f-a431-3dc8641f773a","1440*2672","LGM-G600L","26","KO","KR","LG U+","4.1.6","6","_N_","1","KR"});
 			add(new String[]{"56d01dc1-a860-4b38-a9cf-e45cdc03450f","1080*2094","SM-G955N","28","KO","KR","LG U+","4.1.6","6","_N_","1","KR"});
 			add(new String[]{"06f0509b-1309-4df8-a4f9-b8cabee1aef7","1080*2094","SM-G950N","28","KO","KR","LG U+","4.1.6","6","_N_","2","KR"});
 			add(new String[]{"06f0509b-1309-4df8-a4f9-b8cabee1aef7","1080*2094","SM-G950N","28","KO","KR","LG U+","4.1.6","6","_N_","1","KR"});
 			add(new String[]{"116b6c39-7def-462b-a70b-43f52fcf3588","1440*2560","SM-N916K","23","KO","KR","_N_","4.1.6","6","_N_","2","KR"});
 			add(new String[]{"d6320f96-c3d1-4453-9962-7639324189f4","1080*2094","SM-N950N","26","KO","KR","SKTelecom","4.1.6","6","_N_","2","KR"});
 			add(new String[]{"aed33034-4792-4160-9a14-e679fde9f00a","1080*2076","SM-G950N","26","KO","KR","LG U+","4.1.6","6","_N_","1","KR"});
 			add(new String[]{"e459707c-c1aa-42e8-98f8-0d78886e13d2","1080*1920","SM-N920S","24","KO","KR","SKTelecom","4.1.6","6","_N_","2","KR"});
 			add(new String[]{"703b0942-cb41-4b2f-b4d5-b3038521e0f1","1080*2094","SM-N960N","28","KO","KR","SKTelecom","4.1.6","6","_N_","1","KR"});
 			add(new String[]{"0f6d8cf2-69be-4c17-ad1b-c0e5cebe7e55","1440*2560","SM-G925S","24","KO","KR","SKTelecom","4.1.6","6","_N_","1","KR"});
 			add(new String[]{"4c040821-f97f-42c3-9724-7e222d1ce043","1080*2076","SM-A530N","26","KO","KR","SKTelecom","4.1.6","6","_N_","1","KR"});
 			add(new String[]{"bd280b81-348c-4131-8b90-42a977951ad8","1080*1620","BBB100-1","27","KO","KR","KT","4.1.6","4","_N_","2","KR"});
 			add(new String[]{"6c8c0a89-6244-47ec-b53b-0e1951d6e713","1080*2058","SM-G960N","28","KO","KR","KT","4.1.6","6","_N_","1","KR"});
 			add(new String[]{"6b99c4d0-84f6-4d90-90a1-df3a68912f3a","1440*2560","SM-G920S","24","KO","KR","KT","4.1.6","6","_N_","1","KR"});
 			add(new String[]{"c71f35da-ea83-4eb8-ba24-5d303dd66948","1080*2076","SM-G950N","26","KO","KR","SKTelecom","4.1.6","6","_N_","2","KR"});
 			add(new String[]{"2069b326-f11e-4fd3-9d8f-8508adb5644f","1080*2128","SM-G975N","28","KO","KR","KT","4.1.6","6","_N_","2","KR"});
 			add(new String[]{"e17659bf-2c08-42f1-829a-e71600b3f1f1","1080*2094","SM-G965N","26","KO","KR","SKTelecom","4.1.6","6","_N_","1","KR"});
 			add(new String[]{"e17659bf-2c08-42f1-829a-e71600b3f1f1","1080*2094","SM-G965N","26","KO","KR","_N_","4.1.6","6","_N_","1","KR"});
 			add(new String[]{"257cd4e8-7215-4bd1-8058-07a31cc347fc","1080*2220","SM-N950N","26","KO","KR","SKTelecom","4.1.6","6","_N_","2","KR"});
 			add(new String[]{"a6e3c318-3490-43ab-b6dd-76635e7cb9da","1080*2094","SM-N960N","27","KO","KR","LG U+","4.1.6","6","_N_","1","KR"});
 			add(new String[]{"6ffa1185-104c-4626-aba3-4bc40cfcf253","1080*2094","SM-N950N","28","KO","KR","SKTelecom","4.1.6","6","_N_","2","KR"});
 			add(new String[]{"ecf67799-7c97-4d7f-b5a8-ad31c7de3d71","720*1280","SM-J737S","26","KO","KR","SKTelecom","4.1.6","6","_N_","1","KR"});
 			add(new String[]{"5e25ac62-7c72-4bde-964a-9ce6f4cf48e3","1080*2042","SM-G973N","28","KO","KR","SKTelecom","4.1.6","6","_N_","2","KR"});
 			add(new String[]{"164069CF-C250-4E65-B553-39AA9FE414F1","375*667","iPhone 6s","I11","KO","KR","KT","1.1.7","4","_N_","2","KR"});
 			add(new String[]{"D7B91981-EDDC-4AFD-9D9E-69BC6597FABB","375*667","iPhone 7","I12","KO","KR","KT","1.1.7","4","_N_","1","KR"});
 			add(new String[]{"163FF4FF-4ABA-42A7-92EB-ADA03854C90E","375*812","iPhone XS","I12","KO","KR","SKTelecom","1.1.8","6","_N_","2","KR"});
 			add(new String[]{"349C3089-50F3-474D-9EB7-BAF612381DEB","375*667","iPhone 8","I12","KO","KR","KT","1.1.8","4","_N_","1","KR"});
 			add(new String[]{"5F6493B7-ACD7-47CD-BE84-8796DAF472F3","375*667","iPhone 8","I12","KO","KR","LG U+","1.1.8","4","_N_","2","KR"});
 			add(new String[]{"27C42245-6309-4EA8-8D75-56C6939C66EF","375*812","iPhone XR","I12","KO","KR","KT","1.1.8","6","_N_","1","KR"});
 			add(new String[]{"B4AB22DE-2F1D-459C-B879-283B3F0B6979","320*568","iPhone SE","I12","KO","KR","LG U+","1.1.8","4","_N_","1","KR"});
 			add(new String[]{"40CBCA33-FD9E-462A-82ED-7017A0937501","414*736","iPhone 8 Plus","I12","KO","KR","KT","1.1.8","6","_N_","1","KR"});
 			add(new String[]{"9389C7CF-4A62-464F-9119-EBA48C32AC45","375*667","iPhone 8","I11","KO","KR","SKTelecom","1.1.8","4","_N_","1","KR"});
 			add(new String[]{"886886EE-0F28-4FE7-900B-F76D111CC0BE","375*812","iPhone X","I12","KO","KR","LG U+","1.1.8","6","_N_","2","KR"});
 			add(new String[]{"5E5804C4-25BD-4DE8-8529-66BE41CA91B4","375*667","iPhone 6","I12","KO","KR","LG U+","1.1.8","4","_N_","2","KR"});
 			add(new String[]{"EA019CB5-C4AE-49EE-B7AC-01F61803E0D7","414*896","iPhone XR","I12","ZH","KR","KT","1.1.8","6","_N_","1","KR"});
 			add(new String[]{"D1DC90DA-723A-4CF3-A228-62D097ECB9EA","375*667","iPhone 6s","I11","KO","KR","SKTelecom","1.1.8","4","_N_","1","KR"});
 			add(new String[]{"31BA76C3-EB35-4A09-8DA7-2546144C2F98","375*812","iPhone X","I12","KO","KR","LG U+","1.1.8","6","_N_","1","KR"});
 			add(new String[]{"067EA00C-C12D-4DFD-BC68-1A7155201D92","414*736","iPhone 7 Plus","I12","KO","KR","KT","1.1.8","6","_N_","2","KR"});
 			add(new String[]{"245750A6-4019-47E3-A060-675090600411","375*812","iPhone XS","I12","KO","KR","SKTelecom","1.1.8","6","_N_","1","KR"});
 			add(new String[]{"94EDC974-9C23-47BF-B8D1-AA6455D56BE7","375*812","iPhone X","I12","EN","KR","LG U+","1.1.8","6","_N_","2","KR"});
 			add(new String[]{"FEC6F095-A3B1-4F9A-8435-FA91FFF1E11E","375*667","iPhone 8","I12","KO","KR","KT","1.1.8","4","_N_","1","KR"});
 			add(new String[]{"B092890C-0DDE-46B8-B315-442F83587004","375*667","iPhone 6s","I12","KO","KR","LG U+","1.1.8","4","_N_","1","KR"});
 			add(new String[]{"EC828AF8-4F81-4FEB-B85E-6D69B00716FB","375*667","iPhone 7","I11","KO","KR","LG U+","1.1.8","4","_N_","1","KR"});
 			add(new String[]{"96CDC785-27F9-4B3C-88F8-923192A66848","375*667","iPhone 7","I12","KO","KR","SKTelecom","1.1.8","4","_N_","2","KR"});
 			add(new String[]{"EEEF275D-9C6D-432D-99A8-44AF416DF9EC","375*812","iPhone XS","I12","KO","KR","KT","1.1.8","6","_N_","2","KR"});
 			add(new String[]{"64F00D5E-97FC-4397-A481-6CA51947B970","375*667","iPhone 7","I12","KO","KR","SKTelecom","1.1.8","4","_N_","1","KR"});
 			add(new String[]{"C61290F8-361E-4179-9CDD-3F9689279F80","414*736","iPhone 8 Plus","I12","KO","KR","KT","1.1.8","6","_N_","1","KR"});
 			add(new String[]{"D31E4216-9CFC-4E87-8BC9-4DB1480580D3","375*667","iPhone 7","I12","KO","US","KT","1.1.8","4","_N_","1","US"});
 			add(new String[]{"BF7ACA1F-1CF4-4E72-B830-3D037EDCBCB6","375*812","iPhone XS","I12","KO","KR","SKTelecom","1.1.8","6","_N_","1","KR"});
 			add(new String[]{"3B64D877-4C26-4D6C-B389-F2C237522F3B","375*667","iPhone 8","I12","KO","KR","SKTelecom","1.1.8","4","_N_","2","KR"});
 			add(new String[]{"74C2D088-EF62-405B-A7E2-716E5531A110","414*736","iPhone 7 Plus","I12","KO","KR","SKTelecom","1.1.8","6","_N_","1","KR"});
 			add(new String[]{"179DB600-6202-4D72-A237-85B09D2658DC","320*568","iPhone SE","I11","KO","KR","KT","1.1.8","4","_N_","1","KR"});
 			add(new String[]{"7AF63FEC-840F-4D1B-8467-6236FDBA3850","414*896","iPhone XR","I12","KO","KR","KT","1.1.8","6","_N_","2","KR"});
 			add(new String[]{"009CEA0A-F9D7-4932-8FC8-F8E3ED8CF6EB","375*667","iPhone 6s","I12","KO","KR","SKTelecom","1.1.8","4","_N_","1","KR"});
 			add(new String[]{"490D5349-451D-4322-800A-EE5BB1B742B2","375*812","iPhone X","I12","KO","KR","SKTelecom","1.1.8","6","_N_","1","KR"});
 			add(new String[]{"0D327979-E94F-45A3-9D60-5697E62D93DD","375*812","iPhone XS","I12","KO","KR","KT","1.1.8","6","_N_","1","KR"});
 			add(new String[]{"B5948DFB-2F39-4107-BD09-D99B30720A67","320*568","iPhone SE","I12","KO","KR","SKTelecom","1.1.8","4","_N_","1","KR"});
 			add(new String[]{"7DB5252E-2CC1-4B09-B5FD-3863DFA22665","375*812","iPhone X","I12","KO","KR","KT","1.1.8","6","_N_","2","KR"});
 			add(new String[]{"6C141253-33ED-457D-9F42-328E9857B454","375*667","iPhone 6s","I12","KO","KR","SKTelecom","1.1.8","4","_N_","2","KR"});
 			add(new String[]{"776A6041-048B-4B57-892A-D7AE0604F958","375*667","iPhone 7","I12","KO","KR","SKTelecom","1.1.8","4","_N_","2","KR"});
 			add(new String[]{"FE7D9B84-DE97-4904-80C6-F7548D25F8FB","375*667","iPhone 8","I12","KO","KR","LG U+","1.1.8","4","_N_","1","KR"});
 			add(new String[]{"DCB50F38-9789-4A0C-B32E-8295FFA4DE02","375*812","iPhone X","I12","KO","KR","SKTelecom","1.1.8","6","_N_","1","KR"});
 			add(new String[]{"65CFFD46-1AD8-404B-ADFB-5729049245EC","375*812","iPhone X","I11","KO","KR","SKTelecom","1.1.8","6","_N_","2","KR"});
 			add(new String[]{"AD8DED9C-0CCF-4F9C-8C6A-8DFDD6975917","375*667","iPhone 6s","I10","KO","KR","LG U+","1.1.7","4","_N_","1","KR"});
 			add(new String[]{"6F76C2E2-4A89-4479-8265-FA6AFAE599EC","375*667","iPhone 8","I12","KO","KR","SKTelecom","1.1.8","4","_N_","2","KR"});
 			add(new String[]{"498F5DD4-D9CE-45D5-B720-B42B7924BD47","375*667","iPhone 6s","I12","KO","KR","LG U+","1.1.8","4","_N_","2","KR"});
 			add(new String[]{"36472CDD-7379-4491-B024-8D9E79ACD5D3","375*667","iPhone 7","I12","KO","KR","KT","1.1.8","4","_N_","1","KR"});
 			add(new String[]{"72053A4A-9E83-4754-82EF-5713340E9467","375*812","iPhone X","I12","KO","CN","SKTelecom","1.1.8","6","_N_","1","CN"});
 			add(new String[]{"47587FA4-C8B0-402D-80B6-1A17A4EAF675","375*812","iPhone X","I12","KO","KR","KT","1.1.8","6","_N_","2","KR"});
 			add(new String[]{"DBD43AD1-BA48-4E68-80C8-0E129887A132","375*667","iPhone 8","I12","KO","KR","LG U+","1.1.8","4","_N_","1","KR"});
 			add(new String[]{"7EABC1FE-5BAD-48B2-AE97-59E0B33F3D35","375*812","iPhone X","I12","KO","US","KT","1.1.8","6","_N_","1","US"});
 			add(new String[]{"7EABC1FE-5BAD-48B2-AE97-59E0B33F3D35","375*812","iPhone X","I12","KO","US","KT","1.1.8","6","_N_","2","US"});
 			add(new String[]{"07701758-371B-44DC-9424-8C4CD7410190","414*736","iPhone 8 Plus","I12","KO","US","KT","1.1.8","6","_N_","2","US"});
 			add(new String[]{"C553A473-BF50-43DA-8877-483B4E3099D4","414*736","iPhone 6s Plus","I12","KO","KR","SKTelecom","1.1.8","6","_N_","2","KR"});
 			add(new String[]{"3D851D4C-0E59-4429-871B-94BC23FE5610","375*667","iPhone 6s","I12","KO","KR","SKTelecom","1.1.8","4","_N_","2","KR"});
 			add(new String[]{"262DE7EA-C701-470D-914D-B52E91C00ED0","375*667","iPhone 7","I12","KO","KR","SKTelecom","1.1.8","4","_N_","2","KR"});
 			add(new String[]{"1320235F-7F14-4813-87CF-07CCA7DC91E9","375*667","iPhone 7","I12","KO","KR","SKTelecom","1.1.8","4","_N_","2","KR"});
 			add(new String[]{"4024B88A-03F0-4A5F-B945-FFD94054A263","375*667","iPhone 7","I12","KO","KR","KT","1.1.8","4","_N_","2","KR"});
 			add(new String[]{"07CBCC3F-DF5F-4419-B416-C5EC3FD281E9","375*667","iPhone 6s","I12","KO","KR","SKTelecom","1.1.8","4","_N_","2","KR"});
 			add(new String[]{"EF676C79-2B29-4002-BBB9-D35556E0C338","375*812","iPhone X","I12","KO","KR","SKTelecom","1.1.8","6","_N_","2","KR"});
 			add(new String[]{"86F9424E-BE68-40B2-86C5-C86AB4C9FCCB","375*667","iPhone 8","I12","KO","KR","KT","1.1.8","4","_N_","2","KR"});
 			add(new String[]{"376F7B05-2C26-42EF-83DF-95708B81DAB5","375*812","iPhone X","I11","KO","KR","LG U+","1.1.8","6","_N_","2","KR"});
 			add(new String[]{"C1C7EED1-E2CB-4698-9A4E-FA6633668362","375*667","iPhone 7","I10","KO","KR","SKTelecom","1.1.8","4","_N_","1","KR"});
 			add(new String[]{"0CD4FF97-E96E-4E5A-99CE-D6FA12D72284","375*667","iPhone 8","I12","KO","KR","KT","1.1.8","4","_N_","2","KR"});
 			add(new String[]{"9763628F-F55D-490E-BFED-D34D8560D229","414*736","iPhone 8 Plus","I11","KO","KR","KT","1.1.7","6","_N_","1","KR"});
 			add(new String[]{"FCBB27C6-23D9-40F5-8AF6-C4AFD47CC0F3","375*667","iPhone 6s","I11","KO","KR","SKTelecom","1.1.8","4","_N_","2","KR"});
 			add(new String[]{"88F19254-FD19-4E97-BF8E-F28B6D7699E9","375*667","iPhone 8","I12","KO","KR","KT","1.1.8","4","_N_","1","KR"});
 			add(new String[]{"373FFE13-F9B3-44AA-BBAA-2F179F2A83AE","375*667","iPhone 6s","I11","KO","KR","SKTelecom","1.1.8","4","_N_","2","KR"});
 			add(new String[]{"7F7EB84B-C443-44AC-9F63-AE7E6347F4AF","375*812","iPhone X","I12","KO","KR","SKTelecom","1.1.8","6","_N_","2","KR"});
 			add(new String[]{"33F861F2-EE9D-49FD-BF54-A8D53505D063","375*812","iPhone X","I12","KO","KR","SKTelecom","1.1.8","6","_N_","1","KR"}); 
 		}
 	};
 	
	private void setUserDevice(AppDataTemplate bean) {
		try {
			int rIdx = (int) Math.floor((Math.random()*1000)%99); 
			String[] rd = randomUserDevice.get(rIdx); 
			bean.setUserDevice(rd); 
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
	
}
