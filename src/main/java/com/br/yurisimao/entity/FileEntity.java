package com.br.yurisimao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class FileEntity.
 * @author Yuri Simão
 * @since 08/03/2020
 */
@Entity
@Getter 
@Setter 
@ToString 
@AllArgsConstructor 
@NoArgsConstructor
public class FileEntity {

	/** The id. */
	@Id
	@GeneratedValue
	private Long id;

	/** The descricao. */
	@Column(name = "DESCRICAO")
	private String descricao;

}


