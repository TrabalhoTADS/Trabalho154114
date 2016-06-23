import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.univel.Cliente;
import br.univel.TelaCliente;

public class JUTTelaCliente {

/*	@Test
    public void testPreencheTabela() {
        //Assert.assertNotSame("   asdas ", new TelaCliente().preencheTabela(), null);
    }*/

    @Test
    public void testSalvarClienteXML() {
        TelaCliente c = new TelaCliente();
        Cliente cliente = new Cliente (1, "Teste", "Teste", 1, "Teste", "Teste", "Teste", "Teste", 1, "Teste", "Teste");
        List<Cliente> lista = new ArrayList<>();
        lista.add(cliente);
        c.setListaCliente(lista);

        File file = new File ("C:\\Users\\Eduardo\\git\\Trabalho154114\\TesteSalvar.xml");
        c.SalvarClienteXML(file);

        //assertFalse("A lista nao deve estar vazia", c.getListaCliente().size() == 0);
        assertEquals("A lista vazia deve ter 1 elementos", 1, lista.size());
        assertTrue("Arquivo Criado!", file.exists());
        assertTrue("Arquivo esta vazio!", file.length() > 0);
        //Assert.IsNull(true, c.getListaCliente());
    }

    @Test
    public void testLerClienteXML() {
        TelaCliente c = new TelaCliente();
        File file = new File ("C:\\Users\\Eduardo\\git\\Trabalho154114\\ListaClientes.xml");
        c.LerClienteXML(file);

        assertTrue("A lista nao pode estar vazia", c.getListaCliente().size() > 0);
    }

    @Test
    public void testSalvarClienteSerializacao() {

    }
/*
    @Test
    public void testLerClienteSerializacao() {
        fail("Not yet implemented");
    }*/

}
