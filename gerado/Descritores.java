import util.ArquivoOrigem;
import util.Coluna;

@ArquivoOrigem(nome="descritores.csv")
public class Descritores {

	public static final String tabela = "comum.descritor_grupo_valor";
	
	private Integer idDescritorGrupoValor;
	private String idDescritorGrupoMaterial;
	private String idMaterial;
	private String valor;
	private String idBem;
	private String valorAscii;


	public Integer getIdDescritorGrupoValor() {
		return this.idDescritorGrupoValor;
	}

	@Coluna(nomeArquivo="#id_descritor_grupo_valor:Integer" , nomeBanco="id_descritor_grupo_valor" )
	public void setIdDescritorGrupoValor(Integer idDescritorGrupoValor) {
		this.idDescritorGrupoValor = idDescritorGrupoValor;
	}

	public String getIdDescritorGrupoMaterial() {
		return this.idDescritorGrupoMaterial;
	}

	@Coluna(nomeArquivo="id_descritor_grupo_material" , nomeBanco="id_descritor_grupo_material" )
	public void setIdDescritorGrupoMaterial(String idDescritorGrupoMaterial) {
		this.idDescritorGrupoMaterial = idDescritorGrupoMaterial;
	}

	public String getIdMaterial() {
		return this.idMaterial;
	}

	@Coluna(nomeArquivo="id_material" , nomeBanco="id_material" )
	public void setIdMaterial(String idMaterial) {
		this.idMaterial = idMaterial;
	}

	public String getValor() {
		return this.valor;
	}

	@Coluna(nomeArquivo="valor" , nomeBanco="valor" )
	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getIdBem() {
		return this.idBem;
	}

	@Coluna(nomeArquivo="id_bem" , nomeBanco="id_bem" )
	public void setIdBem(String idBem) {
		this.idBem = idBem;
	}

	public String getValorAscii() {
		return this.valorAscii;
	}

	@Coluna(nomeArquivo="valor_ascii" , nomeBanco="valor_ascii" )
	public void setValorAscii(String valorAscii) {
		this.valorAscii = valorAscii;
	}

	@Override
	public String toString() {
		return ( ""
				+ "; idDescritorGrupoValor: " + idDescritorGrupoValor 
				+ "; idDescritorGrupoMaterial: " + idDescritorGrupoMaterial 
				+ "; idMaterial: " + idMaterial 
				+ "; valor: " + valor 
				+ "; idBem: " + idBem 
				+ "; valorAscii: " + valorAscii 
				+ "").substring(1);
	}
	
	public String toCSV() {
		return ( ""
				+ ";" + idDescritorGrupoValor 
				+ ";" + idDescritorGrupoMaterial 
				+ ";" + idMaterial 
				+ ";" + valor 
				+ ";" + idBem 
				+ ";" + valorAscii 
				+ "").substring(1);
	}
	
	public String toInsert(){
	
	 String colunas = "";
	 String valores = "";
	 
				valores += ", " + toString(idDescritorGrupoValor); 
				colunas += ", id_descritor_grupo_valor";
				valores += ", " + toString(idDescritorGrupoMaterial); 
				colunas += ", id_descritor_grupo_material";
				valores += ", " + toString(idMaterial); 
				colunas += ", id_material";
				valores += ", " + toString(valor); 
				colunas += ", valor";
				valores += ", " + toString(idBem); 
				colunas += ", id_bem";
				valores += ", " + toString(valorAscii); 
				colunas += ", valor_ascii";
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
	 
				valores += ", id_descritor_grupo_valor = " +  toString(idDescritorGrupoValor) ;
				where   += " and id_descritor_grupo_valor = " + toString(idDescritorGrupoValor) ;
				valores += ", id_descritor_grupo_material = " +  toString(idDescritorGrupoMaterial) ;

				valores += ", id_material = " +  toString(idMaterial) ;

				valores += ", valor = " +  toString(valor) ;

				valores += ", id_bem = " +  toString(idBem) ;

				valores += ", valor_ascii = " +  toString(valorAscii) ;

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