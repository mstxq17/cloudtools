package com.xq17.cloudtools.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
    * @ClassName: TempFile  
    * @Description: 临时文件存放对象
    * @author xq17  
    * @date 2020年9月17日  
    *
 */

public class TempFile implements Serializable {

	private static final long serialVersionUID = 2513790036263250227L;
	private Integer fileId;
	private String size;
	private Date uploadTime;
	private String filePath;
	private String fileName;

	public TempFile(String size, Date uploadTime, String filePath, String fileName) {
		super();
		this.size = size;
		this.uploadTime = uploadTime;
		this.filePath = filePath;
		this.fileName = fileName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileId == null) ? 0 : fileId.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((filePath == null) ? 0 : filePath.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((uploadTime == null) ? 0 : uploadTime.hashCode());
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
		TempFile other = (TempFile) obj;
		if (fileId == null) {
			if (other.fileId != null)
				return false;
		} else if (!fileId.equals(other.fileId))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (filePath == null) {
			if (other.filePath != null)
				return false;
		} else if (!filePath.equals(other.filePath))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (uploadTime == null) {
			if (other.uploadTime != null)
				return false;
		} else if (!uploadTime.equals(other.uploadTime))
			return false;
		return true;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
