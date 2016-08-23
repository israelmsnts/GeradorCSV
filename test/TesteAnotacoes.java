import static org.junit.Assert.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;

import util.ArquivoOrigem;
import util.Coluna;

public class TesteAnotacoes {
	

	@Test
	public void testeNomeColuna() {
		Class<TesteComTipos> obj = TesteComTipos.class;
		
		String[] esperado = new String[] {
				 "data_nascimento"
				,"#id:Integer"
				,"nome"};
		
		String[] resultado = getColunas(obj);

		Arrays.sort(esperado);
		Arrays.sort(resultado);
		
		assertArrayEquals(esperado, resultado);
	}
	
	@Test
	public void testeNomeArquivo() {
		Class<TesteComTipos> obj = TesteComTipos.class;
		
		assertTrue(obj.isAnnotationPresent(ArquivoOrigem.class));
		
		ArquivoOrigem arquivoOrigem = obj.getAnnotation(ArquivoOrigem.class);
		
		assertEquals("test/testeComTipos.csv",arquivoOrigem.nome()); 
	}

	private String[] getColunas(Class<TesteComTipos> obj) {
		String[] resultado = new String[3];
		int i = 0;
		for (Method method : obj.getDeclaredMethods()) {

			if (method.isAnnotationPresent(Coluna.class)) {
				Annotation annotation = method.getAnnotation(Coluna.class);
				Coluna coluna = (Coluna) annotation;
				resultado[i] =coluna.nomeArquivo();
				i++;
			}
		}
		return resultado;
	}

}