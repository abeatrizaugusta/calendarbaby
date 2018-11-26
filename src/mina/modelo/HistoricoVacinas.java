package mina.modelo;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class HistoricoVacinas {
	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Temporal(TemporalType.DATE)
	private Calendar data = Calendar.getInstance();
	private String local;
	
	@OneToOne
	private Usuario usuario;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "historico")
	private List<ItemHistorico> itens = new LinkedList<ItemHistorico>();
	
	public void add(ItemHistorico item) {
		this.itens.add(item);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<ItemHistorico> getItens() {
		return itens;
	}

	public void setItens(List<ItemHistorico> itens) {
		this.itens = itens;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
	
	
}