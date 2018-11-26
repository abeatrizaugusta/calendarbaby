package mina.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mina.modelo.HistoricoVacinas;

public class HistoricoVacinasDao {
	public HistoricoVacinas listaPorId(HistoricoVacinas c) {
		EntityManager em = JPAUtil.getEntityManager();
		
		String jpql = "SELECT DISTINCT c FROM HistoricoVacinas c LEFT JOIN FETCH c.itens WHERE c.id = :pId";
		
		TypedQuery<HistoricoVacinas> query = em.createQuery(jpql, HistoricoVacinas.class);
		query.setParameter("pId", c.getId());
		
		c = query.getSingleResult();
		
		em.close();
		
		return c;
	}
}
