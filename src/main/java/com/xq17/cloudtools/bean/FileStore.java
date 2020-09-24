package com.xq17.cloudtools.bean;

import java.io.Serializable;

/**
 * 
 * @ClassName: FileStore
 * @Description: 文件仓库实体类
 * @author xq17
 * @date 2020年9月22日
 *
 */
public class FileStore implements Serializable {

	private static final long serialVersionUID = -6520578081905114523L;
	private Integer fileStoreId;
	private Integer userId;
	private Integer currentSize;
	private Integer maxSize;
	private Integer permission;

	public FileStore(Integer fileStoreId, Integer userId, Integer currentSize, Integer maxSize, Integer permission) {
		super();
		this.fileStoreId = fileStoreId;
		this.userId = userId;
		this.currentSize = currentSize;
		this.maxSize = maxSize;
		this.permission = permission;
	}


	public FileStore() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "FileStore [fileStoreId=" + fileStoreId + ", userId=" + userId + ", currentSize=" + currentSize
				+ ", maxSize=" + maxSize + ", permission=" + permission + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentSize == null) ? 0 : currentSize.hashCode());
		result = prime * result + ((fileStoreId == null) ? 0 : fileStoreId.hashCode());
		result = prime * result + ((maxSize == null) ? 0 : maxSize.hashCode());
		result = prime * result + ((permission == null) ? 0 : permission.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		FileStore other = (FileStore) obj;
		if (currentSize == null) {
			if (other.currentSize != null)
				return false;
		} else if (!currentSize.equals(other.currentSize))
			return false;
		if (fileStoreId == null) {
			if (other.fileStoreId != null)
				return false;
		} else if (!fileStoreId.equals(other.fileStoreId))
			return false;
		if (maxSize == null) {
			if (other.maxSize != null)
				return false;
		} else if (!maxSize.equals(other.maxSize))
			return false;
		if (permission == null) {
			if (other.permission != null)
				return false;
		} else if (!permission.equals(other.permission))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	public Integer getFileStoreId() {
		return fileStoreId;
	}

	public void setFileStoreId(Integer fileStoreId) {
		this.fileStoreId = fileStoreId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
