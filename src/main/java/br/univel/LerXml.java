package br.univel;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class LerXml <T> {

    public List<T> LerArquivoXml(File file) {
        List<T> list = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Wrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            if (list != null)
                list.clear();
            else
                list = new ArrayList<>();

            Wrapper wrapper = (Wrapper) um.unmarshal(file);

            System.out.println(wrapper.getList());
            list.addAll(wrapper.getList());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
