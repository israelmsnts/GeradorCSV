package util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeitorCSV {
	
	private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	private String csvFile;
	private  List<List<String>> linhas = null;
	private  List<String> colunas = null;
	
	private LeitorCSV(String csvFile) {
		this.csvFile = csvFile;
		linhas = new ArrayList<List<String>>();
		colunas = new ArrayList<String>();
	}

	@SuppressWarnings("resource")
	public static LeitorCSV carregarArquivo(String csvFile) throws IOException{

		LeitorCSV  leitor = new LeitorCSV(csvFile);
		File f = new File(csvFile);
		
		if(!f.exists()){
			throw new FileNotFoundException(csvFile + ": Arquivo não exite");
		}
		
		BufferedReader br = null;

        br = new BufferedReader(new FileReader(csvFile));
        int i = 0;
        String linha = "";
        while ((linha = br.readLine()) != null) {
        	if(i>=1){
        		if(!linha.equals("")){
        			leitor.getLinhas().add( new ArrayList<String>(Arrays.asList(linha.split(";"))));
        		}
        	}else{
        		leitor.getColunas().addAll(Arrays.asList(linha.split(";")));
        	}
        	i++;
        		
        }
		
        return leitor;
	}

	public List<List<String>> getLinhas() {
		return linhas;
	}

	public List<String> getColunas() {
		return colunas;
	}
	
	public String getCsvFile() {
		return csvFile;
	}

	public static <T> List<T> carregarObjetos(Class<T> classe) throws IOException {
		
		if(!classe.isAnnotationPresent(ArquivoOrigem.class)){
			throw new IllegalArgumentException("Classe não possue anotação ArquivoOrigem");
		}

		ArquivoOrigem arquivoOrigem = classe.getAnnotation(ArquivoOrigem.class);
		String arquivo = arquivoOrigem.nome();
		List<T> retorno = gerarObjetos(classe, arquivo);
		return retorno;
	}
	
	public static <T> List<T> carregarObjetos(Class<T> classe, String arquivo) throws IOException {
		List<T> retorno = gerarObjetos(classe, arquivo);
		return retorno;
	}

	private static <T> List<T> gerarObjetos(Class<T> classe, String arquivo)
			throws IOException {
		LeitorCSV leitor = LeitorCSV.carregarArquivo(arquivo);
		
		Map<String,Method> map = new HashMap<>();
		for(String col : leitor.getColunas()){
			for (Method method : classe.getDeclaredMethods()) {

				if (method.isAnnotationPresent(Coluna.class)) {
					
					Annotation annotation = method.getAnnotation(Coluna.class);
					if(!method.getName().startsWith("set")){
						throw new IllegalArgumentException("Só metodos set podem ser anotados com @Coluna: " + method.getName());
					}
					Coluna coluna = (Coluna) annotation;
					if(coluna.nomeArquivo().equals(col)){
						map.put(col, method);
					}
				}
			}
		}
		
		String[] cols = leitor.getColunas().toArray(new String[leitor.getColunas().size()]);
		
		List<T> retorno = new ArrayList<>();
		for(List<String> linha : leitor.getLinhas()){
			try {
				T obj = classe.newInstance();		
				int i = 0;
				for (String valor : linha) {
					String col =  cols[i];
					if(valor != null  && valor.equals("")){
						valor = null;
					}
					Method m = map.get(col);
					Object objParam = null;
					if(col.contains(":")){
						String tipo = col.split(":")[1];
						objParam = converterTipo(tipo, valor);
					}else {
						objParam =  valor ;
					}
					m.invoke(obj, objParam);
					i++;
				}
				retorno.add(obj);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return retorno;
	}

	private static Object converterTipo(String tipo, String valor) {

		if(!tipo.equals("String") && valor  == null){
			return null;
		}
		
		if(tipo.equals("Integer")){
			return Integer.parseInt(valor);
		}
		
		if(tipo.equals("Long")){
			return Long.parseLong(valor);
		}
		
		if(tipo.equals("Double")){
			return Double.parseDouble(valor);
		}
		
		if (tipo.equals("Date")) {
			try {
				return formatter.parse(valor);			
			} catch (ParseException e) {
				System.err.println("erro no parser da data" + valor);
			}
		}
		return valor;
	}
	
}
