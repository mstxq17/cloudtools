package com.xq17.cloudtools.service;

import com.xq17.cloudtools.bean.TempFile;

public interface TempFileService {

	public int insert(TempFile tf);

	public int deleteById(Integer fileId);

	TempFile findById(Integer fileId);

	TempFile findLastBySessionId(String sessionId);

}
