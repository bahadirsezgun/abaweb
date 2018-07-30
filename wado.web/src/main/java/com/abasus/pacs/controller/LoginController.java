package com.abasus.pacs.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.abasus.pacs.util.ResultObj;

@RestController
@RequestMapping(value="/login")
public class LoginController {

	
	@PostMapping(value="/control/{userName}/{password}") 
	public @ResponseBody ResultObj findPatientByIdToSendKOS(@PathVariable(value="userName") String userName,@PathVariable(value="password") String password) {
		
		if(userName.equals("ecopacs") && password.equals("ecopacs")) {
			return new ResultObj("success");
		}
		
		return new ResultObj("fail");
	}
	
}
