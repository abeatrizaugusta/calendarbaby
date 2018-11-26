package mina.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mina.dao.DAO;
import mina.modelo.Vacina;

@ManagedBean
@ViewScoped
public class VacinaController {
	private Vacina vacina = new Vacina();
		
	public Vacina getVacina() {
		return vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
	}

	public void gravar(){
		//não tem id - insere
		if(this.vacina.getId() == null){
			new DAO<Vacina>(Vacina.class).adiciona(vacina);
		} else{ //tem id - atualiza
			new DAO<Vacina>(Vacina.class).atualiza(vacina);
		}
		//limpa formulário
		this.vacina = new Vacina(); 
	}
	
	public List<Vacina> getTodosVacinas(){
		return new DAO<Vacina>(Vacina.class).listaTodos();
	}
	
	public void remover(Vacina v){	
			try{
			new DAO<Vacina>(Vacina.class).remove(v.getId());
			
			}catch(Exception e){
				
				FacesContext.getCurrentInstance().addMessage("vacina", new FacesMessage("Impossível remover, vacina associada a um usuário!"));
		}
	}
	
	public void carregar(Vacina v){
		this.vacina = v;
	}
	

}
