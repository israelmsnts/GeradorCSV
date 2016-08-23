import java.io.IOException;
import java.util.List;

import util.LeitorCSV;

public class CarregarModel {
	public static void main(String[] args) throws IOException {

		List<Descritores> lista = LeitorCSV.carregarObjetos(Descritores.class);

		for (Descritores d : lista) {
			d.setValor("!!!!!!!!!!!!!!!!!!");
			d.setIdDescritorGrupoMaterial("9");
		}
		
		for (Descritores d : lista) {
			
			System.out.println( d.toUpdate());
		}
	}
}
