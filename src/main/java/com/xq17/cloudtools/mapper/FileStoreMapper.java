package com.xq17.cloudtools.mapper;

import com.xq17.cloudtools.bean.FileStore;

public interface FileStoreMapper {
	/**
	 * 
	 * @Title: addFileStore @Description: 新分配仓库返回仓库id @param @param
	 *         fileStore @param @return 参数 @return Integer 返回类型 @throws
	 */
	public Integer addFileStore(FileStore fileStore);

	/**
	 * 
	 * @Title: getFileStoreById @Description: 通过仓库ID获取仓库所有数据 @param @param
	 * fileStoreId @param @return 参数 @return FileStore 返回类型 @throws
	 */
	public FileStore getFileStoreById(Integer fileStoreId);

	/**
	 * 
	 * @Title: addSize @Description: 增加仓库容量 @param @param id @param @param
	 * size @param @return 参数 @return Integer 返回类型 @throws
	 */
	public Integer addSize(Integer id, Integer size);

	/**
	 * 
	 * @Title: subSize @Description: 减少仓库容量 @param @param id @param @param
	 * size @param @return 参数 @return Integer 返回类型 @throws
	 */
	public Integer subSize(Integer id, Integer size);

	/**
	 * 
	 * @Title: changeMaxSize @Description: 修改用户的仓库最大容量 @param @param
	 * id @param @param maxSize @param @return 参数 @return Integer 返回类型 @throws
	 */
	public Integer changeMaxSize(Integer id, Integer maxSize);

	/**
	 * 
	 * @Title: updatePermission @Description: 更新仓库权限 @param @param id @param @param
	 * permission @param @param size @param @return 参数 @return Integer 返回类型 @throws
	 */
	public Integer updatePermission(Integer id, Integer permission, Integer size);

	/**
	 * 
	 * @Title: deleteById @Description: 通过残酷Id删除仓库 @param @param id @param @return
	 * 参数 @return Integer 返回类型 @throws
	 */
	public Integer deleteById(Integer id);
}
