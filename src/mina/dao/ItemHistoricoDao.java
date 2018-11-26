package mina.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import mina.modelo.HistoricoVacinas;
import mina.modelo.ItemHistorico;

public class ItemHistoricoDao {
	public List<ItemHistorico> listaPorHistorico(HistoricoVacinas c){
		EntityManager em = JPAUtil.getEntityManager();
		
		String jpql = "SELECT i FROM ItemHistorico i WHERE i.historico = :pHistoricoVacinas";
		
		TypedQuery<ItemHistorico> query = em.createQuery(jpql, ItemHistorico.class);
		query.setParameter("pHistoricoVacinas", c);
		
		List<ItemHistorico> lista = query.getResultList();
		
		em.close();
		
		return lista;

	}
}
