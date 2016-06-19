
package br.univel;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.core.SerializableString;

@XmlRootElement(name = "cliente")
@XmlAccessorType(XmlAccessType.FIELD)
public class Cliente implements Serializable {

	@XmlElement(name = "id")
	private int id;

	@XmlElement(name = "nome")
	private String nome;

	@XmlElement(name = "endereco")
	private String endereco;

	@XmlElement(name = "numero")
	private int numero;

	@XmlElement(name = "complemento")
	private String complemento;

	@XmlElement(name = "bairro")
	private String bairro;

	@XmlElement(name = "cidade")
	private String cidade;

	@XmlElement(name = "estado")
	private String estado;

	@XmlElement(name = "cep")
	private int cep;

	@XmlElement(name = "telefone")
	private String telefone;

	@XmlElement(name = "celular")
	private String celular;

	public Cliente(){

	}

	public Cliente(int id, String nome, String endereco, int numero, String complemento, String bairro, String cidade,
			String estado, int cep, String telefone, String celular) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.telefone = telefone;
		this.celular = celular;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getCep() {
		return cep;
	}

	public void setCep(int cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

}
