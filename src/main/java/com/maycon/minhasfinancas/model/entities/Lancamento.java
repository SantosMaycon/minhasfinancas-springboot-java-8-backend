package com.maycon.minhasfinancas.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import com.maycon.minhasfinancas.model.entities.enums.StatusLancamento;
import com.maycon.minhasfinancas.model.entities.enums.TipoLancamento;

@Entity @Table(name = "lancamento", schema = "financas")
public class Lancamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private Integer ano;
	private Integer mes;
	private BigDecimal valor;
	@Column(name = "data_cadastro")
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	private LocalDate dataCadastro;
	@Enumerated(value = EnumType.STRING)
	private TipoLancamento tipo;
	@Enumerated(value = EnumType.STRING)
	private StatusLancamento status;
	
	@ManyToOne @JoinColumn(name = "id_usuario")
	private Usuario usuario;

	public Lancamento() {
	
	}

	public Lancamento(Long id, String descricao, Integer ano, Integer mes, BigDecimal valor, LocalDate dataCadastro,
			TipoLancamento tipo, StatusLancamento status, Usuario usuario) {
		this.id = id;
		this.descricao = descricao;
		this.ano = ano;
		this.mes = mes;
		this.valor = valor;
		this.dataCadastro = dataCadastro;
		this.tipo = tipo;
		this.status = status;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public TipoLancamento getTipo() {
		return tipo;
	}

	public void setTipo(TipoLancamento tipo) {
		this.tipo = tipo;
	}

	public StatusLancamento getStatus() {
		return status;
	}

	public void setStatus(StatusLancamento status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lancamento [id=" + id + ", descricao=" + descricao + ", ano=" + ano + ", mes=" + mes + ", valor="
				+ valor + ", dataCadastro=" + dataCadastro + ", tipo=" + tipo + ", status=" + status + ", usuario="
				+ usuario + "]";
	}
}
