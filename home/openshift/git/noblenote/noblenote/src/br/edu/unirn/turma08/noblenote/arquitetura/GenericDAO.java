package br.edu.unirn.turma08.noblenote.arquitetura;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

public abstract class GenericDAO<T> {

	private EntityManager em = Database.getInstance().getEntityManager();

	private Class<T> entityClass;

	public GenericDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public void create(T c) {
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
	}

	public void update(T c) {
		em.merge(c);
	}

	public void delete(T c) {
		em.remove(c);
	}

	public void remove(Object id, Class<T> classe) {
		T entityToBeRemoved = em.getReference(classe, id);
		em.remove(entityToBeRemoved);
	}

	public void close() {

	}

	public T find(int entityID) {
		return em.find(entityClass, entityID);
	}

	public T findByPrimaryKey(int id) {
		T c = em.find(entityClass, id);
		return c;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findAll() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return em.createQuery(cq).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> findAllLike(String coluna, String valor) {
		String tabela = entityClass.getSimpleName();
		String jpql = "from " + tabela + " where " + coluna + " like :valor";
		Query q = em.createQuery(jpql);
		q.setParameter("valor", "%" + valor + "%");
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	protected T findOneResult(String namedQuery, Map<String, Object> parameters) {
		T result = null;

		try {
			Query query = em.createNamedQuery(namedQuery);

			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			result = (T) query.getSingleResult();

		} catch (Exception e) {
			System.out.println("Error while running query: " + e.getMessage());
			e.printStackTrace();
		}

		return result;
	}

	private void populateQueryParameters(Query query,
			Map<String, Object> parameters) {

		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}

	public EntityManager getEm() {
		return em;
	}

}