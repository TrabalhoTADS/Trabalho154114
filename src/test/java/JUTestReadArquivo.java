import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import br.univel.ReaderArquivo;

public class JUTestReadArquivo {

	@Test
	public void testReaderArquivo() {
		assert (new ReaderArquivo().lerArquivo("C:\\Users\\Eduardo\\git\\Trabalho154114\\listaclientes.txt")) != null;
	}

}
