package org.test.ws;

import org.model.AxaTopUps;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class AxaTopupWs {

	@RequestMapping("/changingQt")
	@ResponseBody
	
	public void myService(@ModelAttribute AxaTopUps req){
		System.out.println("the request is : "+req.getAttributes().getSlave_msisdn()+", "+req.getAttributes().getAmount());
	}
}
