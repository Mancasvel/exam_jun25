package tests;

import java.util.List;

import exercise.FactoriaRevistas;
import exercise.Revista;

public class Tests {
	
	
	public static void main(String[] args) {
		
		FactoriaRevistas factoria = new FactoriaRevistas();
		
		List<Revista> revistas = factoria.leeRevistas("/data/revistas_informatica.csv");
		
	}

}
