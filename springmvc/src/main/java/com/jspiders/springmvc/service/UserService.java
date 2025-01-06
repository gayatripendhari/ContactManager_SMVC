package com.jspiders.springmvc.service;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jspiders.springmvc.dao.UserDAO;
import com.jspiders.springmvc.dto.UserDTO;

@Component
public class UserService {

	@Autowired
	private UserDAO userDAO;

	public boolean addUser(String name, String email, long mobile, String password) {
		UserDTO userDTO = new UserDTO();
		userDTO.setName(name);
		userDTO.setEmail(email);
		userDTO.setMobile(mobile);
		userDTO.setPassword(password);
		try {
			userDAO.addUser(userDTO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public UserDTO authUser(String email, String password) {
		try {
			UserDTO user = userDAO.authUser(email, password);
			if (user.getEmail().equals(email) && user.getPassword().equals(password))
				return user;
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public UserDTO getUserById(int id) {
	    return userDAO.getUserById(id);
	}

	public boolean editUser(int id, String name, String email, long mobile, String password) {
	    try {
	        UserDTO userDTO = new UserDTO(id, name, email, mobile, password);
	        userDAO.updateUser(userDTO);
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public boolean deleteUser(int id) {
	    try {
	        return userDAO.deleteUser(id);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}


}