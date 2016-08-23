import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import util.LeitorCSV;


public class TesteLeitorCSV {

	@Test
	public void testeCarregarArquivoColunas() throws IOException {
		LeitorCSV l = LeitorCSV.carregarArquivo("./test/teste.csv");
		
		
		String[] esperado = new String[] {"id","nome","data_nascimento"};
		assertArrayEquals(esperado, l.getColunas().toArray());
	}
	
	@Test
	public void testeCarregarArquivoLinhas() throws IOException {
		LeitorCSV l = LeitorCSV.carregarArquivo("./test/teste.csv");
		
		assertEquals(3, l.getLinhas().size());
		
		//1;pessoa1;04/01/1988
		assertEquals("1", l.getLinhas().get(0).get(0));
		assertEquals("pessoa1", l.getLinhas().get(0).get(1));
		assertEquals("04/01/1988", l.getLinhas().get(0).get(2));
	}
	
	@Test(expected = FileNotFoundException.class)
	public void testeCarregarArquivoInexistente() throws IOException {
		LeitorCSV l = LeitorCSV.carregarArquivo("./test/nao_exite.csv");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testeCarregarObjetosClasseInvalida() throws IOException {
		
		List<String> lista = LeitorCSV.carregarObjetos(String.class);
		assertTrue(lista != null);

	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testeCarregarObjetosAnotacaoColunaErrada() throws IOException {

		List<TesteComTiposAnotacaoErrada> lista = LeitorCSV.carregarObjetos(TesteComTiposAnotacaoErrada.class);
		assertTrue(lista != null);
	}
	
	@Test
	public void testeCarregarObjetos() throws IOException {
		
		List<TesteSemTipos> lista = LeitorCSV.carregarObjetos(TesteSemTipos.class);
		
		assertTrue(lista != null);
		assertTrue(!lista.isEmpty());
		assertEquals("Quantidade Objetos", 3, lista.size());
		
		TesteSemTipos p = new TesteSemTipos();
		p.setId("1");
		p.setNome("pessoa1");
		p.setDataNascimento("04/01/1988");
		
		assertEquals("getId diferentes", p.getId(), lista.get(0).getId());
		assertEquals("getNome diferentes", p.getNome(), lista.get(0).getNome());
		assertEquals("getDataNascimento diferentes", p.getDataNascimento(), lista.get(0).getDataNascimento());
	}
	
	@Test
	public void testeCarregarObjetosComTipos() throws IOException {
		
		List<TesteComTipos> lista = LeitorCSV.carregarObjetos(TesteComTipos.class);
		
		assertTrue(lista != null);
		assertTrue(!lista.isEmpty());
		assertEquals("Quantidade Objetos", 3, lista.size());
		
		TesteComTipos p = new TesteComTipos();
		p.setId(1);
		p.setNome("pessoa1");
		p.setDataNascimento("04/01/1988");
		
		assertEquals("getId diferentes", p.getId(), lista.get(0).getId());
		assertEquals("getNome diferentes", p.getNome(), lista.get(0).getNome());
		assertEquals("getDataNascimento diferentes", p.getDataNascimento(), lista.get(0).getDataNascimento());
	}
	
}
