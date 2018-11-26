package mina.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mina.dao.DAO;
import mina.dao.GrupoDao;
import mina.modelo.Funcionalidade;
import mina.modelo.Grupo;

@ManagedBean
@ViewScoped
public class GrupoController {
	private Grupo grupo = new Grupo();
	private Integer funcionalidadeId;
	
	public Grupo getGrupo() {
		return grupo;
	}

	public Integer getFuncionalidadeId() {
		return funcionalidadeId;
	}

	public void setFuncionalidadeId(Integer funcionalidadeId) {
		this.funcionalidadeId = funcionalidadeId;
	}
	
	public List<Grupo> getTodosGrupos(){
		return new DAO<Grupo>(Grupo.class).listaTodos();
	}
	
	public List<Funcionalidade> getTodasFuncionalidades(){
		return new DAO<Funcionalidade>(Funcionalidade.class).listaTodos();
	}
	
	public void gravarFuncionalidade(){
		
		Funcionalidade f = new DAO<Funcionalidade>(Funcionalidade.class).listaPorId(funcionalidadeId);
		grupo.addFuncionalidade(f);
		funcionalidadeId = null;
		
	}
	
	public void gravar(){
		if (this.grupo.getId() == null) {
			new DAO<Grupo>(Grupo.class).adiciona(this.grupo);
		} else {
			new DAO<Grupo>(Grupo.class).atualiza(this.grupo);
		}
		this.grupo = new Grupo();
	}
	
	public List<Funcionalidade>getFuncionalidadesDoGrupo(){
		return grupo.getFuncionalidades();
	}
	
	public void remover(Funcionalidade f){
		this.grupo.getFuncionalidades().remove(f);
	}
	
	public void remover(Grupo g){
		try {
			new DAO<Grupo>(Grupo.class).remove(g.getId());
			
		}catch(Exception e) {
			
		}
			FacesContext.getCurrentInstance().addMessage("grupo", new FacesMessage("Impossível remover, grupo associado a um usuário!"));
		}
	
	public void carregar(Grupo g){
		g = new GrupoDao().listaPorId(g);
		grupo = g;
		
	}
}
