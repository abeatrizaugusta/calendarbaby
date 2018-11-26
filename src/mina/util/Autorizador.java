package mina.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import mina.modelo.Funcionalidade;
import mina.modelo.Usuario;


public class Autorizador implements PhaseListener{
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		// Obt�m contexto da aplica��o 
		FacesContext context = event.getFacesContext();
		// Obt�m o nome da p�gina que est� sendo chamada
	    String nomePagina = context.getViewRoot().getViewId();

	    // System.out.println(nomePagina);

	    // se for a p�gina de login, o usu�rio pode acessar 
	    if ("/login.xhtml".equals(nomePagina) || ("/indexteste.xhtml".equals(nomePagina))) {
	        return;
	    }

	    // Obt�m usu�rio da sess�o
	    Usuario usuarioLogado = 
	    	(Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

	    // se h� usu�rio logado, ele pode acessar as p�ginas
	    if(usuarioLogado != null) {
	    	
	    	// se for a index, o usu�rio pode acessar
	    	if (nomePagina.equals("/indexteste.xhtml")) {
	    		return;
	    	}
	    	
	    	Funcionalidade func = (Funcionalidade)
	    	context.getExternalContext().getSessionMap().
	    	get(nomePagina);
	    	
	    	if (func != null){
	    		return;
	    	}
	    	
	    	NavigationHandler handler = context.getApplication().getNavigationHandler();
		    handler.handleNavigation(context, null, "/indexteste?faces-redirect=true");
		    context.renderResponse();
	        return;
	    }

	    // se n�o h�, o usu�rio � redirecionado para o login
	    NavigationHandler handler = context.getApplication().getNavigationHandler();
	    handler.handleNavigation(context, null, "/login?faces-redirect=true");
	    context.renderResponse();
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW; // o autorizador ser� executado na fase restore_view
	}


}
