import java.io.File;

import org.junit.Test;

import br.univel.ReaderArquivo;

public class JUTestReadArquivo {

	@Test
	public void testReaderArquivo() {
		assert (new ReaderArquivo().lerArquivo(new File("ListaClientes.txt"))) != null;
	}

}
