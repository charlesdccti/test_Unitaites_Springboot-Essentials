package com.example.model;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.lang.String; 


@Entity
@Table(name = "departamentos")
public class Departamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "endereco")
    private String endereco;

	public void setName(String name) {this.name = name;}
	public String getName() {return name;}
	public void setEndereco(String endereco) {this.endereco = endereco;}
	public String getEndereco() {return endereco;}

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	
}