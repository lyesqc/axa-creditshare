package org.services;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.transaction.Transactional;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.dao.AbstractLogDao;
import org.dao.EmployeeDao;
import org.dao.LogDao;
import org.entity.Employee;
import org.entity.MyLog;
import org.model.AxaTopUps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.utils.SSLUtil;

@Service("quantityService")
@Transactional
public class QuantityServiceImpl implements QuantityService {

	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	LogDao logDao; 
	@Value("${comp.url}")
	String url;
	@Value("${comp.contrno}")
	String contrnos;
	String myHttpStatus;
	String myBody;
	HttpComponentsClientHttpRequestFactory requestFactory;
	
	
	public Map<String, String> sendBatchQuantity(List<Employee> list1) throws JsonProcessingException, IOException   {
		
		
		System.out.println("inside sendBatchQuantity");
		
		///---------------SSL setting
		
		
		
		
		
		
		/*
		TrustStrategy acceptingTrustStrategy = new TrustStrategy() {

			public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				
				return true;
			}
			
			
		};
		
		
				try{
		
		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
		        .loadTrustMaterial(null, acceptingTrustStrategy)
		        .build();
		
SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
		
CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf)
.build();

		 requestFactory =
		        new HttpComponentsClientHttpRequestFactory();
		
		
		requestFactory.setHttpClient(httpClient);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		*/
		
		
		
		try{
			//System.out.println("before ssl");
			SSLContextBuilder builder = new SSLContextBuilder();
			 builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			 //System.out.println("before ssl 1");
			 SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(builder.build(), NoopHostnameVerifier.INSTANCE);
			 //System.out.println("before ssl 1.2");
			 Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
			            .register("http", new PlainConnectionSocketFactory())
			            .register("https", sslConnectionSocketFactory)
			            .build();
			 //System.out.println("before ssl 2");
			 PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
			 //System.out.println("before ssl 3");
			 cm.setMaxTotal(100);
			 CloseableHttpClient httpclient = HttpClients.custom()
			            .setSSLSocketFactory(sslConnectionSocketFactory)
			            .setConnectionManager(cm)
			            .build();
			 //System.out.println("before ssl 4");
		 requestFactory =
		        new HttpComponentsClientHttpRequestFactory();

		requestFactory.setHttpClient(httpclient);
		
		}catch(Exception e ){
			e.printStackTrace();
		}
		
		
		///----------SSL Settings
		//System.out.println("before loop");
		for (String contrno: contrnos.split(";")){
		
			//System.out.println("inside loop");
				//System.out.println("TEST .................");
			
		AxaTopUps axaTopups;
		
		
		
		
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		//Prepare the Json Request to be sent in body
		List<Employee> list = employeeDao.getAllEmployees(contrno);
		System.out.println("List size : "+list.size());
		String aux = employeeDao.getAuxSimOfContrno(list);
		System.out.println("List size : "+list.size());
		for (Employee employee : list){
			try{
				SSLUtil.turnOffSslChecking();
			axaTopups = new AxaTopUps();
		System.out.println("TEST .................1 "+employee.getMsisdn());
		axaTopups.attributes =  axaTopups.new AxaTopUpsAttributes(employee,aux);
		ResponseEntity<String> response =  restTemplate.postForEntity(url, axaTopups, String.class);
		System.out.println(" Response " +response.getStatusCode());
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		myBody = root.toString();
		myHttpStatus = response.getStatusCode().toString();
		System.out.println(employee.getMsisdn()+" : "+root.toString());
			
			}catch(HttpClientErrorException e){
				myHttpStatus = e.getStatusCode().toString();
				myBody = e.getResponseBodyAsString();
				System.out.println("An exception has been occured "+ e.getStatusCode());
				
				System.out.println("---");
				System.out.print(myBody);
				System.out.println("---");
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();;
			}
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
			
			MyLog myLog = new LogBuilder().actorIs(employee.getMsisdn()).labelxIs(format.format(new Date())).operationIs("ChangeQt")
			.contrnoIs(employee.getContrno()).statusIs(myHttpStatus).msgResponseIs(myBody).build();
			
			logDao.save(myLog);
			
			
		}
		
			
		}
		
		//JsonNode name = root.path("name");
		//restTemplate.postForObject(url, request, responseType, uriVariables)
		return null;
	}
	
	
	
	/*
	{
	    "data": {
	        "id": "c2cc290e-7dc3-11e7-80c3-005056875f59",
	        "type": "axapack-topups",
	        "attributes": {
	            "master-msisdn": "99597123456",
	            "slave-msisdn": "99597123457",
	            "notify-msisdn": "99597123456",
	            "amount" : 100,
	            "transfer-datetime" : "2017-08-18T10:15:15.000+03:00"
	        }
	    }
	}*/
	
	
	
	
	
	

}
