package com.niit.controller;

import java.io.*;

import java.util.List;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.niit.model.*;
import com.niit.service.*;
import com.google.gson.Gson;

@Controller
public class BlogContoller 
{

	@Autowired
	private BlogService blogservice;

	@RequestMapping("/newblog")
	public String createBlog(HttpServletRequest request,Model model)
	{
		String name=request.getParameter("blog");
		model.addAttribute("name",name);
		System.out.println("In Blog Controller");
		return "BlogPage";
	}
	
	@ModelAttribute("blog")
	public Blog returnObject()
	{
		return new Blog();
	}

	@RequestMapping("/postb")
	public String postblog(@ModelAttribute("blog") Blog blog , Model model,Principal p) throws IOException
	{
		System.out.println("I am in blogpost");
		blog.setbUserName(p.getName());
		blog.setbUserName(blog.getbUserName());
		blog.setCreationdatetime(new java.util.Date());
		blogservice.createNewBlog(blog);
		
		return "BlogPage";
	}
	
	String setName;

	List<Blog> blist;
	@SuppressWarnings("unchecked")
	@RequestMapping("/GsonCon")
	public @ResponseBody String getValues()throws Exception
	{
		String result = "";
		
			
			blist = blogservice.getBlog();
			Gson gson = new Gson();			  
			result = gson.toJson(blist);			
		
		
		return result;
	}
	

	
}
