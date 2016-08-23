import static org.junit.Assert.*;

import org.junit.Test;

import util.Campo;


public class TestCampo {

	@Test
	public void testeConstrutorUmArgumento() {
		Campo campo = new Campo("id_bem");
		assertEquals("nome errado", "idBem", campo.getNome());
		assertEquals("nome coluna errado", "id_bem", campo.getNomeColunaBanco());
	}
	
	@Test
	public void testeConstrutorUmArgumentoTipoExplicito() {
		String coluna = "#id_bem:Integer";
		Campo campo = new Campo(coluna);
		assertEquals("nome errado", "idBem", campo.getNome());
		assertEquals("Tipo errado", "Integer", campo.getTipo());
		assertEquals("Coluna Banco errado", "id_bem", campo.getNomeColunaBanco());
		assertEquals("Coluna Arquivo errado", coluna, campo.getNomeColunaArquivo());
	}
	
	@Test
	public void testeConstrutorDoisArgumentosTipoExplicito() {
		Campo campo = new Campo("id_bem:Integer", Long.class);
		assertEquals("nome errado", "idBem", campo.getNome());
		assertEquals("Tipo errado", "Long", campo.getTipo());
		assertEquals("Coluna Banco errado", "id_bem", campo.getNomeColunaBanco());
		assertEquals("Coluna Arquivo errado", "id_bem:Integer", campo.getNomeColunaArquivo());
	}
	
	
	@Test
	public void testeConstrutorDoisArgumentos() {
		Campo campo = new Campo("id_bem","long");
		assertEquals("Nome errado", "idBem", campo.getNome());
		assertEquals("Tipo errado", "long", campo.getTipo());
	}
	
	@Test
	public void testeConstrutorArgumentoClasse() {
		Campo campo = new Campo("id_bem",Long.class);
		assertEquals("Nome errado", "idBem", campo.getNome());
		assertEquals("Tipo errado", "Long", campo.getTipo());
	}
	
	@Test
	public void testeGetter() {
		Campo campo = new Campo("id_bem","long");
		assertEquals("Get errado", "public long getIdBem()", campo.get());
	}
	
	@Test
	public void testeSetter() {
		Campo campo = new Campo("id_bem","long");
		assertEquals("Set errado", "public void setIdBem(long idBem)", campo.set());
	}


}
