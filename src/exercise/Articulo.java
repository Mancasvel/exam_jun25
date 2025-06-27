package exercise;

import utiles.Checkers;

public record Articulo(String titulo, String categoria, Integer paginas) {
	
	public Articulo{
		Checkers.check("El titulo no puede estar vacio", !(titulo.isBlank()));
		Checkers.check("El numero de paginas debe ser mayor a 0", paginas > 0);
		
	}
	
	
	
	private static final Integer articuloLargo = 10;
	
	public Boolean getArticuloLargo() {
		Boolean res = false;
		
		if(paginas >= articuloLargo) {
			res = true;
		}
		
		return res;

	}
	
}
