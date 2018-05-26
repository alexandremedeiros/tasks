package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tasks")
public class Task {
	
	@Id 
    @GeneratedValue(strategy=GenerationType.AUTO) 
	private Long id;
	
	@Column(name="titulo", nullable=false)
	private String titulo;
	
	@Column(name="status", nullable=false)
	private String status;
	
	@Column(name="descricao", nullable=false)
	private String descricao;
	
	@Column(name="data_criacao", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date dataCriacao;
	
	@Column(name="data_edicao")
	@Temporal(TemporalType.DATE)
	private Date dataEdicao;
	
	@Column(name="data_conclusao")
	@Temporal(TemporalType.DATE)
	private Date dataConclusao;
	
	@Column(name="data_remocao")
	@Temporal(TemporalType.DATE)
	private Date dataRemocao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Date getDataEdicao() {
		return dataEdicao;
	}

	public void setDataEdicao(Date dataEdicao) {
		this.dataEdicao = dataEdicao;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public Date getDataRemocao() {
		return dataRemocao;
	}

	public void setDataRemocao(Date dataRemocao) {
		this.dataRemocao = dataRemocao;
	}
	
	

}
