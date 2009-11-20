package nak.nsf.dao.support;

import java.lang.reflect.ParameterizedType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 基本Dao实现类
 * 
 */
public abstract class BaseDaoSupport<T> extends HibernateDaoSupport implements
		BaseDao<T> {

	@SuppressWarnings("unchecked")
	private Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass()
			.getGenericSuperclass()).getActualTypeArguments()[0];

	protected final Log logger = LogFactory.getLog(getClass());

	public void delete(T object) {
		getSession().delete(object);
	}

	@SuppressWarnings("unchecked")
	public T read(String uuid) {
		return (T) getSession().createCriteria(entityClass).add(
				Restrictions.idEq(uuid)).uniqueResult();
	}

	public void save(T object) {
		Object t = getSession().save(object);
		System.out.println("tesss");
	}

	public void saveOrUpdate(T object) {
		getSession().saveOrUpdate(object);
	}

	public void update(T object) {
		getSession().update(object);
	}
}
