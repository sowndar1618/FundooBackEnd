package com.bridgeit.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Repository;

import com.bridgeit.model.User;
import com.bridgeit.model.UserOtp;
import com.bridgeit.utility.UserToken;
import com.bridgeit.utility.Utility;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
/*
	public void addTeam(Team team) {
		getCurrentSession().save(team);
	}

	public void updateTeam(Team team) {

		System.out.println(team.getName() + "ename" + team.getRating());
		Team teamToUpdate = getTeam(team.getId());
		System.out.println(teamToUpdate.getName() + "namwe" + teamToUpdate.getRating());

		teamToUpdate.setName(team.getName());
		teamToUpdate.setRating(team.getRating());
		getCurrentSession().update(teamToUpdate);

	}

	public Team getTeam(int id) {
		Team team = (Team) getCurrentSession().get(Team.class, id);
		return team;
	}

	public void deleteTeam(int id) {
		Team team = getTeam(id);
		if (team != null)
			getCurrentSession().delete(team);
	}

	@SuppressWarnings("unchecked")
	public List<Team> getTeams() {
		return getCurrentSession().createQuery("from Team").list();
	}

*/	@Override
	public void addUser(User user) {
		getCurrentSession().save(user);

	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from User").list();
	}

	public User getUser(Integer id) {
		User user = (User) getCurrentSession().get(User.class, id);
		return user;
	}

	@Override
	public void updateUser(User user, Integer id, User userCon) {
		// TODO Auto-generated method stub
		// System.out.println(user);
		// System.out.println(id);
		User userUpdate = getUser(user.getId());
		// System.out.println(userUpdate.getEmail()+" "+userUpdate.getName()+ "
		// "+userUpdate.getId());
		// System.out.println(userUpdate+" user");
		// System.out.println(user.getName()+" "+user.getEmail());
		userUpdate.setName(userCon.getName());
		userUpdate.setEmail(userCon.getEmail());
		getCurrentSession().update(userUpdate);
		// System.out.println("ok");

	}

	@Override
	public boolean sendOtp(User user) {
		String otp = Utility.getOtp();
		System.out.println("otp sent");
		Utility.emailOtp(user, otp);
		UserOtp otp2 = new UserOtp();
		otp2.setEmail(user.getEmail());
		otp2.setOtp(otp);
		otp2.setId(user.getId());

		System.out.println(otp2.getEmail() + "  " + otp2.getOtp() + "  " + otp2.getId());

		// getCurrentSession().update(otp2);
		System.out.println("update");
		getCurrentSession().save(otp2);

		return true;
	}

	@SuppressWarnings("unchecked")
	public List<UserOtp> getOtps() {

		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from UserOtp").list();
	}

	@Override
	public boolean verifyOtp(UserOtp otp) {
		System.out.println("verifyOtp" + otp.getOtp());
		List<UserOtp> otpList = getOtps();
		System.out.println(otpList.get(0).getOtp() + "  " + otp.getOtp());
		for (int i = 0; i < otpList.size(); i++) {
			if (otpList.get(i).getOtp().equals(otp.getOtp())) {
				return true;
			}
		}

		return false;
	}


	@Override
	public void deleteUser(Integer id) {

		User user = getUser(id);
		if (user != null) {
			getCurrentSession().delete(user);
		}

	}
	@Override
	public String logIn(User user) {
		HttpHeaders header=new HttpHeaders();
		List<User> userList=getUsers();
		for(int i=0;i<userList.size();i++) {
			if(userList.get(i).getEmail().equals(user.getEmail())&&userList.get(i).getPassword().equals(user.getPassword()))
			{
				try {
					//header.add("user-id", userList.get(i).getId());
					System.out.println(userList.get(i).getId());
				return	UserToken.generateToken(userList.get(i).getId());
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
		
		
		
	
	}
	@Override
	public boolean checkEmail(User user) {
		System.out.println(user.getEmail());
		List<User> listUser=getUsers();
		
		if(listUser.size()==0) {
			return true;
		}else {
		for(int i=0;i<listUser.size();i++) {
			System.out.println(listUser.get(i).getEmail());
			if(listUser.get(i).getEmail().equals(user.getEmail())) {
				System.out.println("a");
				return false;
			}
		}
		
		return true;
		}
		
	}
	@Override
	public void verifyToken(String token) {
		// TODO Auto-generated method stub
		try {
			UserToken.tokenVerify(token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void resetPassword(User tempUser) {
		// TODO Auto-generated method stub
		User user=new User();
		
	
		
		List<User>userList=getUsers();
		
		
		
		for(int i=0;i<userList.size();i++) {
			
			if(userList.get(i).getEmail().equals(tempUser.getEmail())) {
				
				
				 user=userList.get(i);
				
				
			}
			
			
		}
		
		user.setPassword(tempUser.getPassword());
		
		
		
		getCurrentSession().update(user);
		
	}
	@Override
	public List<UserOtp> getOtp() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from UserOtp").list();	
		}
	@Override
	public boolean resetOtp(User user,String otp) {
		// TODO Auto-generated method stub
		
		
	
	
		UserOtp otp2 = new UserOtp();
	List<UserOtp> userList=getOtp();
	UserOtp newUser=new UserOtp();
	for(int i=0;i<userList.size();i++) {
		
		if(userList.get(i).getEmail().equals(user.getEmail())) {
			
		 newUser=userList.get(i);
		}
		
		
	}
	
	
		newUser.setEmail(user.getEmail());
		newUser.setOtp(otp);
	

		System.out.println(newUser.getEmail() + "  " + newUser.getOtp() );

		// getCurrentSession().update(otp2);
		System.out.println("update");
		getCurrentSession().update(newUser);
System.out.println("updated");
		return true;
	
		
		
		

	}


}
