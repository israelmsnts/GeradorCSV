import util.ArquivoOrigem;
import util.Coluna;

@ArquivoOrigem(nome="test/testeComTipos.csv")
public class TesteComTipos {

	public static final String tabela = "test.pessoa";
	
	private Integer id;
	private String nome;
	private String dataNascimento;


	public Integer getId() {
		return this.id;
	}

	@Coluna(nomeArquivo="#id:Integer" , nomeBanco="id" )
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	@Coluna(nomeArquivo="nome" , nomeBanco="nome" )
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNascimento() {
		return this.dataNascimento;
	}

	@Coluna(nomeArquivo="data_nascimento" , nomeBanco="data_nascimento" )
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return ( ""
				+ "; id: " + id 
				+ "; nome: " + nome 
				+ "; dataNascimento: " + dataNascimento 
				+ "").substring(1);
	}
	
	public String toCSV() {
		return ( ""
				+ ";" + id 
				+ ";" + nome 
				+ ";" + dataNascimento 
				+ "").substring(1);
	}
	
	public String toInsert(){
	
	 String colunas = "";
	 String valores = "";
	 
				valores += ", " + toString(id); 
				colunas += ", id";
				valores += ", " + toString(nome); 
				colunas += ", nome";
				valores += ", " + toString(dataNascimento); 
				colunas += ", data_nascimento";
		return (
				"insert into " + tabela 
				+ "("
				+ colunas.substring(1)
				+ ")"
				+ " values ("
				+ valores.substring(1)
				+ ")"   
						
				);
	}
	
	public String toUpdate(){

	 	String where = "";
	 	String valores = "";
	 
				valores += ", id = " +  toString(id) ;
				where   += " and id = " + toString(id) ;
				valores += ", nome = " +  toString(nome) ;

				valores += ", data_nascimento = " +  toString(dataNascimento) ;

		return (
				"update " + tabela + "set "
				+ valores.substring(1)
				+ " where 1=1 "
				+ where 
				);
	}
	
	private boolean isNumeric(String str) {
		if(str == null) {
			return false;
		}
		return str.trim().matches("[+-]?\\d+(\\.\\d+)?");
	}

	private String toString(Object o) {

		if (o == null) {
			return "NULL";
		}
		
		String str;
		if(!(o instanceof String)){
			return o.toString();
		}else {
			str = o.toString();
		}
		
		str = str.trim();
		
		if(str.isEmpty()){
			return null;
		}
		
		Object o1 = null ;
		
		if (!str.startsWith("'") &&  !str.startsWith("nextval") &&  !str.startsWith("now()") ){
				o1 = "'" + str + "'";
		}else {
				o1 = str;
		}
		
		return o1.toString();
	}


}