package com.api.curso.data.vo.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "autor", "dataLancamento", "preco", "titulo" }) // como mudar a ordem do json
public class LivroVO extends RepresentationModel<LivroVO> implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	
	private Long key;
	// @JsonProperty("first_name") nome do json gerado
	private String autor;

	private Date dataLancamento;
	// @JsonIgnore omite o campo no json
	private Double preco;

	private String titulo;

	public LivroVO() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(autor, dataLancamento, key, preco, titulo);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LivroVO other = (LivroVO) obj;
		return Objects.equals(autor, other.autor) && Objects.equals(dataLancamento, other.dataLancamento)
				&& Objects.equals(key, other.key) && Objects.equals(preco, other.preco)
				&& Objects.equals(titulo, other.titulo);
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


}
