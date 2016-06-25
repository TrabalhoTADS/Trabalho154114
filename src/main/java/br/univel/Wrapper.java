package br.univel;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "lista")
public class Wrapper<T> {
	private List<T> list;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> _list) {
		this.list = _list;
	}

}
