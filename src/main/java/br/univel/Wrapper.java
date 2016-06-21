package br.univel;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "lista")
public class Wrapper {
	private List<?> list;

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> _list) {
		this.list = _list;
	}
}
