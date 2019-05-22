package com.example.model;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.lang.String; 
import java.lang.Integer; 


@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "idade")
    private String idade;

    @Column(name = "telefone")
    private Integer telefone;

	public void setName(String name) {this.name = name;}
	public String getName() {return name;}
	public void setIdade(String idade) {this.idade = idade;}
	public String getIdade() {return idade;}
	public void setTelefone(Integer telefone) {this.telefone = telefone;}
	public Integer getTelefone() {return telefone;}

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	
}