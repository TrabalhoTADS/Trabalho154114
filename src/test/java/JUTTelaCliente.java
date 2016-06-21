import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

import br.univel.TelaCliente;

public class JUTTelaCliente {

	@Test
	public void testPreencheTabela() {
		Assert.assertNotSame("   asdas ", new TelaCliente().preencheTabela());
	}

	@Test
	public void testSalvarClienteXML() {

		File file = new File ("C:\\Users\\Eduardo\\git\\Trabalho154114\\listaclientes.txt");
		Assert.assertNotEquals(new TelaCliente().SalvarClienteXML(file), null);
	}

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
	}

}
