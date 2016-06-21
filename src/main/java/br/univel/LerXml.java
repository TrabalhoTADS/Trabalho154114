package br.univel;

import java.io.File;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class LerXml {

	public List<?> LerArquivoXml(File file) {
		List<?> list = null;
		try {
			JAXBContext context = JAXBContext.newInstance(Wrapper.class);
			Unmarshaller um = context.createUnmarshaller();

			//list.clear();

			Wrapper wrapper = (Wrapper) um.unmarshal(file);

			System.out.println(wrapper.getList());
			//list.addAll(wrapper.getList());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
