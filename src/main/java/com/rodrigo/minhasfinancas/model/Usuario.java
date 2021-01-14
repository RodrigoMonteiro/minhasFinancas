package com.rodrigo.minhasfinancas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

// No caso,  como já tinhamos criado a tabela ( com suas colunas) manualmente, agora vai ser necessário  fazer referência a mesma.
@Entity
@Data
@Builder
@Table(name = "usuario", schema = "financas")
public class Usuario {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "senha")
	private String senha;

	@Column(name = "email")
	private String email;

}
