package com.xq17.cloudtools.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName: User
 * @Description: 用户实体类
 * @author xq17
 * @date 2020年9月21日
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = -8429382000147363763L;
	private Integer userId;
	private String openId;
	private Integer fileStoreId;
	private String userName;
	private String email;
	private String password;
	private Date registerTime;
	private String imagePath;
	private Integer role;

	// 用户仓库属性
	// 当前使用的情况
	private Integer currentSize;
	// 当前的仓库容量
	private Integer maxSize;
	// 当前仓库权限
	private Integer permission;
	
	public User(Integer userId, String openId, Integer fileStoreId, String userName, String email, String password,
			Date registerTime, String imagePath, Integer role, Integer currentSize, Integer maxSize,
			Integer permission) {
		super();
		this.userId = userId;
		this.openId = openId;
		this.fileStoreId = fileStoreId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.registerTime = registerTime;
		this.imagePath = imagePath;
		this.role = role;
		this.currentSize = currentSize;
		this.maxSize = maxSize;
		this.permission = permission;
	}
	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentSize == null) ? 0 : currentSize.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fileStoreId == null) ? 0 : fileStoreId.hashCode());
		result = prime * result + ((imagePath == null) ? 0 : imagePath.hashCode());
		result = prime * result + ((maxSize == null) ? 0 : maxSize.hashCode());
		result = prime * result + ((openId == null) ? 0 : openId.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((permission == null) ? 0 : permission.hashCode());
		result = prime * result + ((registerTime == null) ? 0 : registerTime.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (currentSize == null) {
			if (other.currentSize != null)
				return false;
		} else if (!currentSize.equals(other.currentSize))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fileStoreId == null) {
			if (other.fileStoreId != null)
				return false;
		} else if (!fileStoreId.equals(other.fileStoreId))
			return false;
		if (imagePath == null) {
			if (other.imagePath != null)
				return false;
		} else if (!imagePath.equals(other.imagePath))
			return false;
		if (maxSize == null) {
			if (other.maxSize != null)
				return false;
		} else if (!maxSize.equals(other.maxSize))
			return false;
		if (openId == null) {
			if (other.openId != null)
				return false;
		} else if (!openId.equals(other.openId))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (permission == null) {
			if (other.permission != null)
				return false;
		} else if (!permission.equals(other.permission))
			return false;
		if (registerTime == null) {
			if (other.registerTime != null)
				return false;
		} else if (!registerTime.equals(other.registerTime))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "User [userId=" + userId + ", openId=" + openId + ", fileStoreId=" + fileStoreId + ", userName="
				+ userName + ", email=" + email + ", password=" + password + ", registerTime=" + registerTime
				+ ", imagePath=" + imagePath + ", role=" + role + ", currentSize=" + currentSize + ", maxSize="
				+ maxSize + ", permission=" + permission + "]";
	}


	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Integer getFileStoreId() {
		return fileStoreId;
	}
	public void setFileStoreId(Integer fileStoreId) {
		this.fileStoreId = fileStoreId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public Integer getCurrentSize() {
		return currentSize;
	}
	public void setCurrentSize(Integer currentSize) {
		this.currentSize = currentSize;
	}
	public Integer getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(Integer maxSize) {
		this.maxSize = maxSize;
	}
	public Integer getPermission() {
		return permission;
	}
	public void setPermission(Integer permission) {
		this.permission = permission;
	}
	
	
	
	

}
