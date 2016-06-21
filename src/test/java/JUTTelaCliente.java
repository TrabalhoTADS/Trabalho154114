import static org.junit.Assert.assertEquals;

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

		assertEquals("A lista vazia deve ter 3 elementos", 3, c.getListaCliente().size());
		//Assert.IsNull(true, c.getListaCliente());
	}
/*
	@Test
	public void testLerClienteXML() {
		fail("Not yet implemented");
	}

	@Test
	public void testSalvarClienteSerializacao() {
		fail("Not yet implemented");
	}

	@Test
	public void testLerClienteSerializacao() {
		fail("Not yet implemented");
	}*/

}
