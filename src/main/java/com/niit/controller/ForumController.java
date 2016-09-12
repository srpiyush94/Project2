package com.niit.controller;
import java.io.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import java.security.Principal;
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
public class ForumController 
{


	@Autowired
	private ForumService fservice;


	@RequestMapping("/newforum")
	public String createBlog(HttpServletRequest request,Model model)
	{
		String name=request.getParameter("forum");
		model.addAttribute("name",name);
		System.out.println("In Forum Controller");
		return "Forum";
	}
	
	@ModelAttribute("forum")
	public Forum returnObject()
	{
		return new Forum();
	}

	@RequestMapping("/postF")
	public String postblog(@ModelAttribute("forum") Forum forum , Model model,Principal p) throws IOException
	{
		System.out.println("I am in Forum");
		
		forum.setForumUserName(p.getName());
		forum.setCreationdatetime(new java.util.Date());
		
		fservice.createNewForum(forum);
		
		return "Forum";
	
	}
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/GsonCon1")
	public @ResponseBody String getValues() throws Exception
	{
		List<Forum> flist;
		String result="";
		flist = fservice.getForum();
		Gson gson = new Gson();
		result = gson.toJson(flist);
		System.out.println("before flist");
		System.out.println(flist);
		return result;
	}
	
	

}
