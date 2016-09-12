package com.niit.dao;

import java.util.*;
import com.niit.model.*;
	
public interface ForumDaoInterface
{
	public void createNewForum(Forum f);
	public List<Forum> getForumList(String UserName);
	public void delete(int fid);
	public List<Forum> getForum();
}
