package com.xq17.cloudtools.mapper;

import java.util.List;
import java.util.Map;

import com.xq17.cloudtools.bean.User;

/**
 * 
 * @ClassName: UserMapper
 * @Description: 用户数据模型层
 * @author xq17
 * @date 2020年9月21日
 *
 */
public interface UserMapper {
	
	/**
	 * 
	    * @Title: insert  
	    * @Description: 插入新用户
	    * @param @param user
	    * @param @return    参数  
	    * @return Integer    返回类型  
	    * @throws
	 */
	public Integer insert(User user);

	public User findByName(String username);

	public User findByEmail(String email);

	public User findByOpenId(String openId);

	public User findByUserId(Integer userId);

	/**
	 * 
	    * @Title: login  
	    * @Description: 通过邮箱或者用户名验证密码
	    * @param @param user
	    * @param @return    参数  
	    * @return User    返回类型  
	    * @throws
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
	    * @Title: totalUser  
	    * @Description: 返回所有用户数 
	    * @param @return    参数  
	    * @return Integer    返回类型  
	    * @throws
	 */
	public Integer totalUser();

	/**
	 * 
	    * @Title: findUsers  
	    * @Description: 返回后台管理所需的会员信息 
	    * @param @param map
	    * @param @return    参数  
	    * @return List<User>    返回类型  
	    * @throws
	 */

	public List<User> findUsers(Map<String, Object> map);

}
