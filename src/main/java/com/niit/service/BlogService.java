package com.niit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.niit.model.*;
import com.niit.dao.*;

public interface BlogService 
{
	public void createNewBlog(Blog blog);
	public List<Blog> getBlogList(String bUserName);
	public Blog getBlogById(int bid);
	public Blog getBlogByName(String bname);
	public List<Blog> getBlog();

}
