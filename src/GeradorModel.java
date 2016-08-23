import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.rythmengine.Rythm;

import util.Campo;
import util.LeitorCSV;
import util.StringUtil;

public class GeradorModel {

	@SuppressWarnings("serial")
	private static Map<String, Object> conf = new HashMap<String, Object>(){{
		put("codegen.compact", false);
	}};
	private static Map<String, Object> params = new HashMap<String, Object>();

	public static void main(String[] args) throws IOException {
		Rythm.init(conf);
		
		String arquivo = "test/testeComTipos.csv";
		String tabela = "test.pessoa";

		String arquivoJava = carregarParametros(arquivo,tabela);
		gerarArquivo(arquivoJava, params);
		
		arquivo = "test/testeSemTipos.csv";
		tabela = "test.pessoa";

		 arquivoJava = carregarParametros(arquivo,tabela);
		gerarArquivo(arquivoJava, params);
		
		arquivo = "descritores.csv";
	    tabela = "comum.descritor_grupo_valor";
	    
		arquivoJava = carregarParametros(arquivo,tabela);
		gerarArquivo(arquivoJava, params);

		
	}

	private static String carregarParametros(String arquivo, String tabela) throws IOException {
		String classe = StringUtil.ClassName(StringUtil.capitalize(arquivo
				.substring( (arquivo.lastIndexOf('/') + 1)
							,arquivo.lastIndexOf('.'))));
		String arquivoJava = classe + ".java";

		LeitorCSV leitor = LeitorCSV.carregarArquivo(arquivo);
		
		List<String> cols = leitor.getColunas();

		List<Campo> campos = new ArrayList<Campo>();
		for (String coluna : cols) {
			campos.add(new Campo(coluna));
		}

		params.put("nomeClasse", classe);
		params.put("nomeArquivo",arquivo);
		params.put("tabela",tabela);
		params.put("campos", campos);
		return arquivoJava;
	}

	private static void gerarArquivo(String arquivoJava, Map<String, Object> params) throws IOException {

		File f = new File("./gerado/" + arquivoJava);
		if (!f.exists()) {
			f.createNewFile();
		}

		BufferedWriter writer = null;
		try {
			
			writer = new BufferedWriter(new FileWriter(f));
			writer.write(Rythm.render("./src/templates/Model.java.tmp", params));

		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
			}
		}
	}

}
