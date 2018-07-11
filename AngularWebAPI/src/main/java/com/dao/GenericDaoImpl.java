package com.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



@Transactional(propagation=Propagation.MANDATORY)
public class GenericDaoImpl<T, PK extends Serializable> extends HibernateDaoSupport implements GenericDao<T, PK> {

	private Class<T> type;
	
	@Autowired  
	@Qualifier("sessionFactory")
	public SessionFactory sessFactory;
	
	public GenericDaoImpl(SessionFactory sessionFactory, Class<T> type) {
		super.setSessionFactory(sessionFactory);
		this.sessFactory = sessionFactory;
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED)
	public PK create(T o) {
		Session sessionObj = sessFactory.getCurrentSession();
		return (PK) sessionObj.save(o);
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public T get(PK id) {
		System.out.println("session factory = "+sessFactory);
		Session sessionObj = sessFactory.getCurrentSession();
		System.out.println("sessFactory.getCurrentSession() = "+sessionObj);
		T value = (T) sessionObj.get(type, id);
		if (value == null) {
            return null;
        }

        if (value instanceof HibernateProxy) {
			Hibernate.initialize(value);
	        value = (T) ((HibernateProxy) value).getHibernateLazyInitializer().getImplementation();
        }
        return value;
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<T> getAll() {
		Criteria crit = sessFactory.getCurrentSession().createCriteria(type);
		return crit.list();
	}
	
	/*@Transactional(propagation=Propagation.REQUIRED)
	public void createOrUpdate(T o) {
		Session sessionObj = sessFactory.getCurrentSession();
		if (o instanceof AbstractPersistentObject) {
			if (((AbstractPersistentObject) o).isCreation()) {
				sessionObj.saveOrUpdate(o);
			} else {
				sessionObj.merge(o);
			}
		} else {
			throw new RuntimeException("this method support only AbstractPersistentObject");
		}
	}*/

	/*@Transactional(propagation=Propagation.REQUIRED)
	public void merge(T o) {
		Session sessionObj = sessFactory.getCurrentSession();
		if (o instanceof AbstractPersistentObject) {
				sessionObj.merge(o);
			
		} else {
			throw new RuntimeException("this method support only AbstractPersistentObject");
		}
	}

*/
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(T o) {
		Session sessionObj = sessFactory.getCurrentSession();
		sessionObj.update(o);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(T o) {
		Session sessionObj = sessFactory.getCurrentSession();
		Object merged = null;
		try {
			merged = sessionObj.merge(o);
		} catch (ObjectNotFoundException e) {
			// disappeared already due to cascade
			return;
		}
		sessionObj.delete(merged);
	}

	@Override
	public void merge(T o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createOrUpdate(T persistentObject) {
		// TODO Auto-generated method stub
		
	}
}