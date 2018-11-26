package mina.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ItemHistorico {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne
	private Vacina vacina;
	private String notas;
	
	@OneToOne
	private HistoricoVacinas historico;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Vacina getVacina() {
		return vacina;
	}

	public void setVacinas(Vacina vacina) {
		this.vacina = vacina;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public HistoricoVacinas getHistorico() {
		return historico;
	}

	public void setHistorico(HistoricoVacinas historico) {
		this.historico = historico;
	}

	
}
