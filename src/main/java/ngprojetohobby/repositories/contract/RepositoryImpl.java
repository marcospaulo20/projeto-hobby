package ngprojetohobby.repositories.contract;

import java.io.Serializable;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@TransactionAttribute(TransactionAttributeType.REQUIRED)
public abstract class RepositoryImpl<T> {

	@Inject
	private EntityManager em;

	private final Class<T> clazz;

	public RepositoryImpl(Class<T> clazz) {
		this.clazz = clazz;
	}

	public List<T> getAll(final Class<T> entityClass) {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(entityClass);
		query.select(query.from(entityClass));
		return em.createQuery(query).getResultList();
	}

	public T getById(Long id) {
		return em.find(clazz, id);
	}

	public T create(T entity) {
		em.persist(entity);
		em.flush();
		em.refresh(entity);
		return entity;
	}

	public T update(T entity) {		
		return (T) em.merge(entity);
	}

	public <T> void remove(Class<T> entityClass, Serializable primaryKey) {
		try {
			T ref = em.getReference(entityClass, primaryKey);
			em.remove(ref);
		} catch(EntityNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Integer countAll(Class<T> entityClass) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<T> root = query.from(entityClass);
		query.select(builder.count(root));
		TypedQuery<Long> exeQuery = em.createQuery(query);
		return (Integer) exeQuery.getSingleResult().intValue();
	}
	
	public List<T> findAll(Class<T> entityClass, int startPosition, int maxResults, String sortFields) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(entityClass);
		Root<T> root = query.from(entityClass);
		query.select(root).orderBy(builder.asc(root.<String>get(sortFields)));		
		return em.createQuery(query).setFirstResult(startPosition).setMaxResults(maxResults).getResultList();
	}
	
	public List<T> findAllByCol(Class<T> entityClass, String fieldName, Long fieldValue) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(entityClass);
		Root<T> root = query.from(entityClass);
		query.select(root).where(builder.equal(root.<String> get(fieldName), fieldValue));
		return em.createQuery(query).getResultList();
	}

	public List<?> findWithNamedQuery(String queryName) {
		return em.createNamedQuery(queryName).getResultList();
	}
	
	public List<?> findWithNamedQuery(String queryName, int startPosition, int resultLimit) {
		return em.createNamedQuery(queryName).setFirstResult(startPosition).setMaxResults(resultLimit).getResultList();
	}

}
