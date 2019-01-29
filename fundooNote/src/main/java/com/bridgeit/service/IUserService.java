package com.bridgeit.service;

import java.util.List;

import com.bridgeit.model.Note;
import com.bridgeit.model.Team;
import com.bridgeit.model.User;
import com.bridgeit.model.UserOtp;

public interface IUserService {
	
	/*public void addTeam(Team team);
	public void updateTeam(Team team);
	public Team getTeam(int id);
	public void deleteTeam(int id);
	public List<Team> getTeams();*/

	public void addUser(User user);
	public List<User> getUsers();
	public void updateUser(User user,Integer id,User UserCon);
	public User getUser(Integer id);
	public boolean sendOtp(User user);
	public boolean verifyOtp(UserOtp otp);
	
	public void deleteUser(Integer id);
	public String logIn(User user);
	public boolean checkEmail(User user);
	public void verify(String token);
	public boolean reSendOtp(User user);
	public boolean resetPassword(UserOtp userOtp);
	
}
