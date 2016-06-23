package br.univel;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "lista")
public class ClienteWrapper {
	private List<Cliente> list;

	public List<Cliente> getList() {
		return list;
	}

	public void setList(List<Cliente> _list) {
		this.list = _list;
	}

}
