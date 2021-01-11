package com.rodrigo.minhasfinancas.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//No caso,  como já tinhamos criado a tabela ( com suas colunas) manualmente, agora vai ser necessário  fazer referência a mesma.
@Entity
@Table(name = "lancamento", schema = "financas")
public class Lancamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "ano")
	private Integer ano;

	@Column(name = "mes")
	private Integer mes;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	@Column(name = "valor")
	private BigDecimal valor;

	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;

}
