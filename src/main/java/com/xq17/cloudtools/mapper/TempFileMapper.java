package com.xq17.cloudtools.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.xq17.cloudtools.bean.TempFile;
import com.xq17.cloudtools.bean.User;


/**
 * 
 * @ClassName: TempFileMapper
 * @Description: 临时文件数据模型层
 * @author xq17
 * @date 2020年9月19日
 *
 */
@Mapper
public interface TempFileMapper {

    int insert(TempFile tempFile);
    
    int deleteById(Integer fileId);

	TempFile findById(Integer fileId);

	TempFile findLastBySessionId(String sessionId);

	int update(User user);

}