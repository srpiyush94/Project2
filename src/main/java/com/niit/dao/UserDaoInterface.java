package com.niit.dao;

import com.niit.model.*;
import java.util.*;
public interface UserDaoInterface
{
	public void saveOrUpdate(User user);
	public User getUserById(int userid);
	public  List<User> list();
	public User getUserByname(String username);
	//public boolean login(String username , String password);
	
	}
