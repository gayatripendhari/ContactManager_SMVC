package com.jspiders.springmvc.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.jspiders.springmvc.dto.UserDTO;

@Component
public class UserDAO {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;

	private void openConnection() {
		entityManagerFactory = Persistence.createEntityManagerFactory("contact_manager");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}

	private void closeConnection() {
		if (entityManagerFactory != null)
			entityManagerFactory.close();
		if (entityManager != null)
			entityManager.close();
		if (entityTransaction != null) {
			if (entityTransaction.isActive())
				entityTransaction.rollback();
		}
	}

	public void addUser(UserDTO userDTO) {

		openConnection();
		entityTransaction.begin();
		entityManager.persist(userDTO);
		entityTransaction.commit();
		closeConnection();

	}

	public UserDTO authUser(String email, String password) {
		openConnection();
		Query query = entityManager
				.createQuery("SELECT user FROM UserDTO user WHERE user.email = ?1 AND user.password = ?2");
		query.setParameter(1, email);
		query.setParameter(2, password);
		UserDTO user = (UserDTO) query.getSingleResult();
		closeConnection();
		return user;
	}
	
	public UserDTO getUserById(int id) {
	    openConnection();
	    UserDTO user = entityManager.find(UserDTO.class, id);
	    closeConnection();
	    return user;
	}

	public void updateUser(UserDTO userDTO) {
	    openConnection();
	    entityTransaction.begin();
	    entityManager.merge(userDTO);
	    entityTransaction.commit();
	    closeConnection();
	}

	public boolean deleteUser(int id) {
	    openConnection();
	    try {
	        entityTransaction.begin();
	        UserDTO user = entityManager.find(UserDTO.class, id);
	        if (user != null) {
	            entityManager.remove(user);
	            entityTransaction.commit();
	            return true;
	        } else {
	            entityTransaction.rollback();
	            return false;
	        }
	    } catch (Exception e) {
	        if (entityTransaction.isActive()) {
	            entityTransaction.rollback();
	        }
	        e.printStackTrace();
	        return false;
	    } finally {
	        closeConnection();
	    }
	}


}