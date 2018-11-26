package mina.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import mina.dao.DAO;
import mina.dao.HistoricoVacinasDao;
import mina.modelo.HistoricoVacinas;
import mina.modelo.ItemHistorico;
import mina.modelo.Usuario;
import mina.modelo.Vacina;

@ManagedBean
@ViewScoped
public class HistoricoVacinasController {
	private HistoricoVacinas historico = new HistoricoVacinas();
	private String notas;
	private Integer vacinaId;
	private Integer usuarioId;
	
	public HistoricoVacinas getHistorico() {
		return historico;
	}
	public void setHistorico(HistoricoVacinas historico) {
		this.historico = historico;
	}
	public String getNotas() {
		return notas;
	}
	public void setNotas(String notas) {
		this.notas = notas;
	}
	public Integer getVacinaId() {
		return vacinaId;
	}
	public void setVacinaId(Integer vacinaId) {
		this.vacinaId = vacinaId;
	}
	public Integer getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	

	public List<Vacina> getTodosVacinas(){
		return new DAO<Vacina>(Vacina.class).listaTodos();
	}
	
	public void gravarItem(){
		Vacina v = new DAO<Vacina>(Vacina.class).listaPorId(vacinaId);
		
		ItemHistorico item = new ItemHistorico();
		item.setVacinas(v);
		item.setNotas(notas);
		item.setHistorico(historico);
		
		historico.add(item);
		notas = null;
		vacinaId = null;
	}
	
	public List<ItemHistorico> getItensDoHistorico() {
		return historico.getItens();
	}
	
	public void removerItem(ItemHistorico item){
		historico.getItens().remove(item);
	}

	public void gravar(){
		if(historico.getItens().isEmpty()){
			FacesContext.getCurrentInstance().addMessage("tabelaItens", new FacesMessage("Item Obrigatorio"));
			return;
			
		}
		
		if (this.historico.getId() == null) {
			new DAO<HistoricoVacinas>(HistoricoVacinas.class).adiciona(historico);
		} else {
			new DAO<HistoricoVacinas>(HistoricoVacinas.class).atualiza(historico);
		}
		
		this.historico = new HistoricoVacinas();
	}
	
	public List<HistoricoVacinas> getTodosHistoricos(){
		return new DAO<HistoricoVacinas>(HistoricoVacinas.class).listaTodos();
	}
	
	public void remover(HistoricoVacinas c){	
			new DAO<HistoricoVacinas>(HistoricoVacinas.class).remove(c.getId());
			
	}
	//public List<HistoricoVacinas> getMinhasVacinas(){
	//	FacesContext context = FacesContext.getCurrentInstance();
	//	Usuario p = (Usuario)context.getExternalContext().getSessionMap().get("usuarioLogado");
	//	return p.getHistorico();
		
	//}
	
	public void carregar(HistoricoVacinas c){
		c = new HistoricoVacinasDao().listaPorId(c);
		historico = c;
		
	}
	FacesContext context = FacesContext.getCurrentInstance() ;
	Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");
	Integer usuario_id = usuarioLogado.getId();
}
