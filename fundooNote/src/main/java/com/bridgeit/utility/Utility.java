package com.bridgeit.utility;

import java.util.Random;

import com.bridgeit.model.User;


public class Utility {

	public static void emailOtp(User user,String otp) {
		
      
		Mailer.send("thermoamigo18@gmail.com","ccjxydwuynbqglcw",user.getEmail(),"Otp verfication",otp);  
	}
/*
	public static void forgetOtp(String user,String otp) {
    
		Mailer.send("sowndaraperumal18@gmail.com","aapiktzgjlbsasvi",user,"Otp verfication",otp);  
	}*/
	public static  String getOtp() {
		int otp=0;
		int ran=0;
		
		Random random=new Random();
		for(int i=0;i<6;i++) {
			if(i==0&&random.nextInt(10)==0) {
				i--;
			}
			ran=random.nextInt(10);
			otp=otp*10+ran;
		}
	
		String generatedOtp=otp+"";
	
	return generatedOtp;
	}
}
