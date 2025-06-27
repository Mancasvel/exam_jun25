package exercise;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import utiles.Checkers;

public class FactoriaRevistas {
	
	
	public Revista parseaRevista(String lineaCsv) {
		
		String[] partes = lineaCsv.split(";");
		
		Checkers.check("Cadena incorrecta", partes.length == 6);
		
		String numero = partes[0];
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fecha = LocalDate.parse(partes[1].trim(), formatter );
		Integer numEjemplar = Integer.valueOf(partes[2].trim());
		Integer paginasFijas = Integer.valueOf(partes[3].trim());
		Integer numAnuncios = Integer.valueOf(partes[4].trim());
		List<Articulo> articulos = parseaArticulos(partes[5].trim());
		
		return new Revista(numero, fecha, numEjemplar, paginasFijas, numAnuncios, articulos);
		
		
		
	}

	private List<Articulo> parseaArticulos(String articulosCsv) {

		String limpio = articulosCsv.replace("[", "").replace("]", "");
		
		String[] partes = limpio.split(",");
		
		List<Articulo> res = new ArrayList<>();
		
		// si esta vacía que devuelva una lista nueva completamente vacía no una lista con un valor de longitud con espacio en blanco
		if(partes.length== 0) {
			return new ArrayList<>();
		}
		for(String parte: partes) {
			
			String[] partesInternas = parte.split("-");
			Checkers.check("Cadena incorrecta de articulos", partesInternas.length == 3);
			String titulo = partesInternas[0].trim();
			String categoria = partesInternas[1].trim();
			Integer paginas = Integer.valueOf(partesInternas[2].trim());
			
			res.add(new Articulo(titulo, categoria, paginas));
		}
		
		return res;
	}
	
	public List<Revista> leeRevistas(String rutaCsv) {
	    List<Revista> res = new ArrayList<>();
	    List<String> lineas;

	    try {
	        lineas = java.nio.file.Files.readAllLines(java.nio.file.Path.of(rutaCsv));
	    } catch (Exception e) {
	        throw new RuntimeException("Error al leer el archivo: " + e.getMessage(), e);
	    }

	    // Saltamos la primera línea si es cabecera
	    for (int i = 1; i < lineas.size(); i++) {
	        String linea = lineas.get(i).trim();
	        if (!linea.isEmpty()) {
	            res.add(parseaRevista(linea));
	        }
	    }

	    return res;
	}


}
