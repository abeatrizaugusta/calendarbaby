package mina.controller;

import java.util.LinkedList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.PersistenceException;

import mina.dao.DAO;
import mina.modelo.Grupo;
import mina.modelo.Usuario;
import mina.util.Utils;

@ManagedBean
@ViewScoped
public class UsuarioController {
private Usuario usuario = new Usuario();
	
	private String senhaBd;
	private Integer grupoId;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getSenhaBd() {
		return senhaBd;
	}

	public void setSenhaBd(String senhaBd) {
		this.senhaBd = senhaBd;
	}

	public Integer getGrupoId() {
		return grupoId;
	}

	public void setGrupoId(Integer grupoId) {
		this.grupoId = grupoId;
	}
	
	public void gravar(){
		if (senhaBd == null || !senhaBd.equals(usuario.getSenha())) {
			usuario.setSenha(Utils.toMD5(usuario.getSenha()));
		}
		Grupo g = new DAO<Grupo>(Grupo.class).listaPorId(this.grupoId);
		usuario.setGrupo(g);
		try {
			if (usuario.getId() == null) {
				new DAO<Usuario>(Usuario.class).adiciona(usuario);
			} else {
				new DAO<Usuario>(Usuario.class).atualiza(usuario);
			}
		} catch (PersistenceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O Login já está sendo utilizado!"));
		}
		this.grupoId = null;
		this.senhaBd = null;
	}
	
	public List<Grupo> getTodosGrupos() {
		return new DAO<Grupo>(Grupo.class).listaTodos();
	}
	
	public void remover(Usuario u){
		new DAO<Usuario>(Usuario.class).remove(u.getId());
	}
	
	public void ehEmail(FacesContext fc, UIComponent comoponent, Object value) throws ValidatorException{
		String email = value.toString();
		if(!email.contains("@")){
			throw new ValidatorException(new FacesMessage("E-mail Inválido!"));
		}

	}
	
	public List<Usuario> getUsario(){
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario u = (Usuario)context.getExternalContext().getSessionMap().get("usuarioLogado");
		LinkedList<Usuario> lista = new LinkedList<Usuario>();
		lista.add(u);
		return lista;
	}
	 public void atualizaUsuario(){
			FacesContext context = FacesContext.getCurrentInstance();
			Usuario u = (Usuario)context.getExternalContext().getSessionMap().get("usuarioLogado");
			u.setSenha(null);
			this.usuario = u;
	   }
	   
	   public void alterar(){
		   FacesContext context = FacesContext.getCurrentInstance();
		   Usuario u = (Usuario)context.getExternalContext().getSessionMap().get("usuarioLogado");
		   String senha = Utils.toMD5(this.usuario.getSenha());
		   if(u.getSenha().equals(senha)){
		   new DAO<Usuario>(Usuario.class).atualiza(this.usuario);}
		   else{
		   this.usuario.setSenha(Utils.toMD5(this.usuario.getSenha()));
		   new DAO<Usuario>(Usuario.class).atualiza(this.usuario);
		   }
	   }
		   
	public void carregar(Usuario u) {
		this.senhaBd = u.getSenha();
		this.grupoId = u.getGrupo().getId();
		this.usuario = u;
		
}
}

