package com.bridgeit.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.dao.UserDAO;
//import com.bridgeit.model.Note;
//import com.bridgeit.model.Team;
import com.bridgeit.model.User;
import com.bridgeit.model.UserOtp;
import com.bridgeit.utility.Utility;



@Service
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserDAO userDAO;

	User tempUser;
	String otp;
	/*public void addTeam(Team team) {
		teamDAO.addTeam(team);		
	}

	public void updateTeam(Team team) {
		teamDAO.updateTeam(team);
	}

	public Team getTeam(int id) {
		return teamDAO.getTeam(id);
	}

	public void deleteTeam(int id) {
		teamDAO.deleteTeam(id);
	}

	public List<Team> getTeams() {
		return teamDAO.getTeams();
	}
*/
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDAO.addUser(user);
	}

	@Override
	public List<User> getUsers() {

		
		return userDAO.getUsers();
	}

	@Override
	public void updateUser(User user,Integer id,User userCon) {

		userDAO.updateUser(user,id,userCon);
	}

	@Override
	public User getUser(Integer id) {
		// TODO Auto-generated method stub
		return userDAO.getUser(id);
	}

	@Override
	public boolean sendOtp(User user) {

		
		return userDAO.sendOtp(user);
	}

	@Override
	public boolean verifyOtp(UserOtp otp) {
		
		return userDAO.verifyOtp(otp);
	}

	@Override
	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
		userDAO.deleteUser(id);
	}

	@Override
	public String logIn(User user) {
		// TODO Auto-generated method stub
	return userDAO.logIn(user);	
	}

	@Override
	public boolean checkEmail(User user) {
		return userDAO.checkEmail(user);

	}

	@Override
	public void verify(String token) {
		// TODO Auto-generated method stub
	
		userDAO.verifyToken(token);
		
	}

	@Override
	public boolean reSendOtp(User user) {

		boolean checkUser=userDAO.checkEmail(user);
		
		if(!checkUser) {
		tempUser=user;
			 otp=Utility.getOtp();
			 
			 
			System.out.println(otp);
			boolean resetOpt=userDAO.resetOtp(user,otp);
			if(resetOpt)
			Utility.emailOtp(user, otp);
			
			return true;
		}
		
		
		return false;
	}

	@Override
	public boolean resetPassword(UserOtp userOtp) {
		// TODO Auto-generated method stub
	
		List<UserOtp> otpList =userDAO.getOtp();
		
		for(int i=0;i<otpList.size();i++)
		{	
			System.out.println(userOtp.getOtp()+ "   " +otpList.get(i).getOtp() );
		if(otpList.get(i).getOtp().equals(userOtp.getOtp()))
		{
		System.out.println("ss");
		System.out.println(tempUser);
			userDAO.resetPassword(tempUser);
		return true;	
			
		}
		
		}
		System.out.println("sssss");
		return false;
	}

}
