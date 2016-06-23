package br.univel;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class SalvarXml<T> {
	public void SalvarArquivoXml(File file, List<T> _list) {

		try {
			JAXBContext context = JAXBContext.newInstance(Cliente.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			Wrapper wrapper = new Wrapper();
			wrapper.setList(_list);

			m.marshal(wrapper, file);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
