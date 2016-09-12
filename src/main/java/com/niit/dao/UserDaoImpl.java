package com.niit.dao;

import com.niit.model.*;
import java.util.*;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.niit.model.User;
@Repository
@Transactional
public class UserDaoImpl implements UserDaoInterface 
{

	public UserDaoImpl()
	{
		
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	


	public void saveOrUpdate(User user)
	{
	Session s =sessionFactory.getCurrentSession();
	Transaction t = s.beginTransaction();
	s.saveOrUpdate(user);
	t.commit();
		
		
	}

	@Override
	public User getUserById(int userid) {
		Session session=this.sessionFactory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		User u=(User) session.load(User.class,userid);
		System.out.println("data of user by id="+u);
		tx.commit();
		return u;	
	}

	@Override
	public List<User> list() {
		
		@SuppressWarnings("unchecked")
		Session session=this.sessionFactory.getCurrentSession();
		 @SuppressWarnings("unchecked")
		List<User> user = (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		 return user;
	}

	@SuppressWarnings("unchecked")
	public User getUserByname(String username) {
		System.out.println("getting data in dao based on name"+username);
		Session session=this.sessionFactory.getCurrentSession();
		Transaction tx=session.beginTransaction();
		Criteria cr=session.createCriteria(User.class);
		cr.add(Restrictions.eq("username", username));
		List<User> users = cr.list();
		User user = users.get(0);
		System.out.println("User Data="+user.getUsername());
		tx.commit();
		return user;
	}
	
	
/*	public boolean login(String username , String password)
	{
		boolean b =false;
		Session session = sessionFactory.getCurrentSession();
		Transaction trans=session.beginTransaction();
		System.out.println("i am in login dao implementation");
		System.out.println(username+password);
		Criteria  c =session.createCriteria(User.class);
		System.out.println("criteria ="+c);
		c.add(Restrictions.and(Restrictions.eq("Username",username), Restrictions.eq("Password", password)));
		
		List<User> l = c.list();
		System.out.println(l);
		if(l.isEmpty())
		
			b= false;
		
		else  	
			b= true;
		trans.commit();
		return b;
			
	}*/
	
}
