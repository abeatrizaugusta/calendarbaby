package mina.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mina.dao.UsuarioDao;
import mina.modelo.Funcionalidade;
import mina.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginController {
	private Usuario usuario = new Usuario();
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String logar(){
		usuario = new UsuarioDao().buscarPorLoginESenha(usuario.getLogin(), usuario.getSenha());
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		if (usuario != null) { // logou
			// inclui usuario na sessao

			context.getExternalContext().getSessionMap().put("usuarioLogado", usuario);
			
			List<Funcionalidade> lista = 
					usuario.getGrupo().getFuncionalidades(); 
			
			for (Funcionalidade f : lista) {
				context.getExternalContext().getSessionMap()
				.put(f.getPagina(), f);
			}
			
			return "indexteste?faces-redirect=true";
		} else { // não logou
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, 
					new FacesMessage("Login e/ou senha incorretos."));
			
			return "login?faces-redirect=true";
		}
	}
	
	public String deslogar(){
		FacesContext context = FacesContext.getCurrentInstance();
//		context.getExternalContext().getSessionMap().
//		remove("usuarioLogado");
		
		context.getExternalContext().getSessionMap().clear();
		
		return "login?faces-redirect=true";
	}
	
	public boolean temAcesso(String nomePagina){
		FacesContext context = FacesContext.getCurrentInstance();
		
		Funcionalidade f = (Funcionalidade)
		context.getExternalContext().getSessionMap().
		get("/" + nomePagina + ".xhtml");
		if (f == null){
			return false;
		} else {
			return true;
		}
	}

}
