package com.xq17.cloudtools.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xq17.cloudtools.bean.FileStore;
import com.xq17.cloudtools.mapper.FileStoreMapper;
import com.xq17.cloudtools.service.FileStoreService;

@Service
public class FileStoreServiceImpl implements FileStoreService {
	
	@Autowired
	FileStoreMapper fsMapper;

	@Override
	public Integer addFileStore(FileStore fileStore) {
		// TODO Auto-generated method stub
		return fsMapper.addFileStore(fileStore);
	}

	@Override
	public FileStore getFileStoreById(Integer fileStoreId) {
		// TODO Auto-generated method stub
		return fsMapper.getFileStoreById(fileStoreId);
	}

	@Override
	public Integer addSize(Integer id, Integer size) {
		return fsMapper.addSize(id, size);
	}

	@Override
	public Integer subSize(Integer id, Integer size) {
		// TODO Auto-generated method stub
		return fsMapper.subSize(id, size);
	}

	@Override
	public Integer changeMaxSize(Integer id, Integer maxSize) {
		// TODO Auto-generated method stub
		return fsMapper.changeMaxSize(id, maxSize);
	}

	@Override
	public Integer updatePermission(Integer id, Integer permission, Integer size) {
		// TODO Auto-generated method stub
		return fsMapper.updatePermission(id, permission, size);
	}

	@Override
	public Integer deleteById(Integer id) {
		// TODO Auto-generated method stub
		return fsMapper.deleteById(id);
	}

}
