package util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Campo {
	private final Pattern p = Pattern.compile( "_([a-zA-Z])" );
	private String nome
		,tipo = "String";
	private String nomeColuna;
	
	
	public Campo(String nome) {
		super();
		this.nomeColuna = nome;
		if(nome.contains(":")){
			this.tipo = nome.split(":")[1];
			nome = nome.split(":")[0];
		}else {
			this.tipo = "String";	
		}
		this.nome = StringUtil.capitalize(nome).replace("#","");		
	}
	
	public Campo(String nome, String tipo) {
		this(nome);
		this.tipo = tipo;
	}

	public Campo(String nome, Class<?> tipo) {
		this(nome);
		this.tipo = tipo.getSimpleName();
	}

	public String getNome() {
		return nome;
	}

	public String getTipo() {
		return tipo;
	}

	private String nomeCapitalizado() {
		return getNome().substring(0, 1).toUpperCase() + getNome().substring(1);
	}

	public String get() {
		return String.format("public %s get%s()", getTipo(), nomeCapitalizado());
	}

	public String set() {
		return String.format("public void set%s(%s %s)", nomeCapitalizado(), getTipo(), getNome());
	}

	public String getNomeColunaBanco() {
		String col = nomeColuna.replace("#", "");
		if(col.contains(":")){
			return col.split(":")[0];
		}
		return col;
	}
	
	public String getNomeColunaArquivo() {
		return nomeColuna;
	}

}
