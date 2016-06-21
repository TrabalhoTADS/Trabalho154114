import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import br.univel.TelaCliente;

public class JUTTelaCliente {

/*	@Test
	public void testPreencheTabela() {
		//Assert.assertNotSame("   asdas ", new TelaCliente().preencheTabela(), null);
	}*/

	@Test
	public void testSalvarClienteXML() {
		TelaCliente c = new TelaCliente();
		File file = new File ("C:\\Users\\Eduardo\\git\\Trabalho154114\\listaclientes.txt");
		c.preencheTabela();

		//assertEquals("A lista vazia deve ter 3 elementos", 3, c.getListaCliente().size());
		assertFalse("A lista nao deve estar vazia", c.getListaCliente().size() == 0);
		//assertTrue("Arquivo Criado!", file.exists());
		//assertTrue("Arquivo Nao esta vazio!", file.length() > 0);
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
