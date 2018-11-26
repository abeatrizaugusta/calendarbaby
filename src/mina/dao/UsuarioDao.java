package mina.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import mina.modelo.Usuario;
import mina.util.Utils;

public class UsuarioDao {
	public Usuario buscarPorLoginESenha(String login, String senha) {
		Usuario usuario;
		
		String jpql = "SELECT DISTINCT f FROM Usuario f "
				+ "LEFT JOIN FETCH f.grupo g LEFT JOIN FETCH g.funcionalidades "
				+ "WHERE f.login = :pLogin AND f.senha = :pSenha";
				
		EntityManager em = JPAUtil.getEntityManager();
		TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
		query.setParameter("pLogin", login);
		query.setParameter("pSenha", Utils.toMD5(senha));
		
		try {
			usuario = query.getSingleResult();
	    } catch (NoResultException ex) {
	        usuario = null;
	    }
		
		em.close();
		
		return usuario;
	}
	
	
		//public Usuario pesquisarPorCpf(String cpf){
		//EntityManager em = JPAUtil.getEntityManager();	
		//String jpql = "select p FROM Pessoa p where p.cpf = :pCpf";
		//TypedQuery<Pessoa> query = em.createQuery(jpql, Pessoa.class);
		//query.setParameter("pCpf", cpf);
		//Pessoa p = query.getSingleResult();
		//em.close();
		//return p;
	//}
	
}

