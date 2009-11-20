package nak.nsf.dao.support;

/**
 * 基础Dao接口
 * 
 */
public interface BaseDao<T> {

	/**
	 * 删除对象映射数据
	 * <p>
	 * 
	 * @param object
	 *            orm类实例
	 */
	public void delete(T object);

	/**
	 * 得到对象映射数据
	 * <p>
	 * 
	 * @param clas
	 *            orm类
	 * @param uuid
	 *            主键
	 * @return orm类实例
	 */
	public T read(String uuid);

	/**
	 * 保存新对象映射数据
	 * <p>
	 * 
	 * @param object
	 *            orm类实例
	 */
	public void save(T object);

	/**
	 * 保存或更新对象映射数据
	 * <p>
	 * 
	 * @param object
	 *            orm类实例
	 */
	public void saveOrUpdate(T object);

	/**
	 * 更新对象映射数据
	 * <p>
	 * 
	 * @param object
	 *            orm类实例
	 */
	public void update(T object);
}
