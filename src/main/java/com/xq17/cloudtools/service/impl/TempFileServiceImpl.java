package com.xq17.cloudtools.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xq17.cloudtools.bean.TempFile;
import com.xq17.cloudtools.mapper.TempFileMapper;
import com.xq17.cloudtools.service.TempFileService;

@Service
public class TempFileServiceImpl implements TempFileService {

	@Autowired
	TempFileMapper tfMapper;

	@Override
	public int insert(TempFile tf) {
		// TODO Auto-generated method stub
		return tfMapper.insert(tf);
	}

	@Override
	public int deleteById(Integer fileId) {
		// TODO Auto-generated method stub
		return tfMapper.deleteById(fileId);
	}

	@Override
	public TempFile findById(Integer fileId) {
		// TODO Auto-generated method stub
		return tfMapper.findById(fileId);
	}

	@Override
	public TempFile findLastBySessionId(String sessionId) {
		// TODO Auto-generated method stub
		return tfMapper.findLastBySessionId(sessionId);
	}
}
