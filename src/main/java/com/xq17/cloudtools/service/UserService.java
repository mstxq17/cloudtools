package com.xq17.cloudtools.service;

import java.util.Map;

import com.xq17.cloudtools.bean.User;

public interface UserService {

	/**
	 * 
	    * @Title: insert  
	    * @Description: 用户业务模型层
	    * @param @param user
	    * @param @return    参数  
	    * @return Interger    返回类型  
	    * @throws
	 */
	public Integer insert(User user);

	public User findByName(String username);

	public User findByEmail(String email);

	public User findByOpenId(String openId);

	public User findByUserId(Integer userId);

	/**
	 * 
	 * @Title: login @Description: 通过邮箱或者用户名验证密码 @param @param user @param @return
	 * 参数 @return User 返回类型 @throws
	 */
	public User login(Map<String, Object> map);


	/**
	 * 
	    * @Title: update  
	    * @Description: 修改用户的信息  
	    * @param @param user
	    * @param @return    参数  
	    * @return Integer    返回类型  
	    * @throws
	 */
	public Integer update(User user);
	
	/**
	 * 
	    * @Title: findUsers  
	    * @Description: 返回后台用户管理所需要的信息
	    * @param @param map
	    * @param @return    参数  
	    * @return Map<String,Object>    返回类型  
	    * @throws
	 */
	public Map<String, Object> findUsers(Map<String, Object> map);
}
