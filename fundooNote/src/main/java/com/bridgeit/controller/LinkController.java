package com.bridgeit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.model.Response;
import com.bridgeit.model.User;
import com.bridgeit.model.UserOtp;
import com.bridgeit.service.IUserService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200" }, exposedHeaders = { "jwtTokenxxx" })

public class LinkController {

	static Logger logger=LoggerFactory.getLogger(LinkController.class);
	User tempUser;
	Response respone;

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/")
	public String mainPage() {
		return "wellcome";
	}

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<Response> logIn(@RequestBody User user,HttpServletResponse response1) {

		respone = new Response();
		
		respone.setStatus("log  in sucessful..");
		System.out.println(user);
		 logger.trace("User Registration");
		String token = userService.logIn(user);

		//System.out.println(tempUser);
		System.out.println(token);
		

		if(token!=null) {
		//header.add("jwtToken", token);
		response1.addHeader("jwtTokenxxx", token);
		respone.setStatusCode(200);
		System.out.println(token);
		
		return new ResponseEntity<Response>(respone,HttpStatus.OK);
		
		
		}
		else {
			respone.setStatusCode(404);
			return new ResponseEntity<Response>(respone,HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/forgetPassword",method=RequestMethod.POST)
	public ResponseEntity<Response> forgetPassword(@RequestBody User user) {

		System.out.println(user);
		boolean check = userService.reSendOtp(user);
		Response response = new Response();
		
		if(check) {
		tempUser = user;
		System.out.println(tempUser);
		response.setStatusCode(200);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
		}	
		response.setStatusCode(404);
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
		
	}

	@RequestMapping(value = "/resetPassword",method=RequestMethod.POST)
	public ResponseEntity<Response> resetPassword(@RequestBody UserOtp userOtp) {

		System.out.println("user otp"+userOtp);
		boolean check = userService.resetPassword(userOtp);
		Response response=new Response();
		if(check) {
        response.setStatusCode(200);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		}
		response.setStatusCode(404);
		return new ResponseEntity<Response>(response,HttpStatus.OK);

	}
 
	@GetMapping(value = "/verifyEmail/{token:.+}")
	public ResponseEntity<Response> verifyEmail(@PathVariable String token) {

		Response response = new Response();
		System.out.println(token);
		userService.verify(token);
		response.setMessage("verifed");
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Response> addUser(@RequestBody User user, HttpServletRequest request) {

		System.out.println("hello");
		System.out.println(user);
		tempUser = user;
		boolean emailCheck = userService.checkEmail(user);
		respone = new Response();
		if (emailCheck) {
			userService.sendOtp(user);
			respone.setStatus("otp sent");
			respone.setStatusCode(200);
			return new ResponseEntity<Response>(respone, HttpStatus.OK);
		}
		respone.setStatus("otp not sent");
		
		return new ResponseEntity<Response>(respone, HttpStatus.OK);

		// teamService.addUser(user);

		// return "email id is in valid";

	}

	@RequestMapping("/view")
	public String view() {
		System.out.println(userService.getUsers());
		return "ok";
	}

	@RequestMapping("/editUser/{id}")
	public String editUser(@PathVariable Integer id, @RequestBody User userCon) {
		System.out.println(id);
		User user = userService.getUser(id);
		System.out.println(user);

		userService.updateUser(user, id, userCon);
		return "update";
	}

	/*
	 * @RequestMapping(value="/index") public ModelAndView indexPage() { return new
	 * ModelAndView("home"); }
	 */
	@RequestMapping("/delete/{id}")
	public ResponseEntity<Response> deleteUser(@PathVariable Integer id) {

		userService.deleteUser(id);
		respone = new Response();
		respone.setStatus("deleted");
		return new ResponseEntity<Response>(respone, HttpStatus.OK);

	}

	@RequestMapping(value="/register", method = RequestMethod.POST)
	public ResponseEntity<Response> register(@RequestBody UserOtp otp) {
		// System.out.println("register");
		System.out.println(otp);
		boolean verifyOtp = userService.verifyOtp(otp);
		// System.out.println(verifyOtp);
		respone = new Response();
		if (verifyOtp) {
			// User user=teamService.getUser();
			// System.out.println("aa");
			// System.out.println(tempUser);
			userService.addUser(tempUser);
			respone.setStatusCode(200);
			respone.setStatus("user is registered");
			return new ResponseEntity<Response>(respone, HttpStatus.OK);

		}
respone.setStatusCode(404);
		respone.setStatus("user is not registered");
		return new ResponseEntity<Response>(respone, HttpStatus.OK);
	}

}
