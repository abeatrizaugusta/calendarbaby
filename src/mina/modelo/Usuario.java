package mina.modelo;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Integer id;
	private String nome;
	private String email;
	@Temporal(TemporalType.DATE)
	private Calendar nascimento = Calendar.getInstance();
	private String nomebebe;
	@Temporal(TemporalType.DATE)
	private Calendar nascimentobebe = Calendar.getInstance();
	
	@Column(unique = true)
	private String login;
	private String senha;
	
	@OneToOne
	private Grupo grupo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getNascimento() {
		return nascimento;
	}

	public void setNascimento(Calendar nascimento) {
		this.nascimento = nascimento;
	}

	public String getNomebebe() {
		return nomebebe;
	}

	public void setNomebebe(String nomebebe) {
		this.nomebebe = nomebebe;
	}

	public Calendar getNascimentobebe() {
		return nascimentobebe;
	}

	public void setNascimentobebe(Calendar nascimentobebe) {
		this.nascimentobebe = nascimentobebe;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	

}
