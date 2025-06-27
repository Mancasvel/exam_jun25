package exercise;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import utiles.Checkers;

public class Revista implements Comparable<Revista>{
	
	private String numero;
	private LocalDate fechaPublicacion;
	private Integer ejemplares;
	private Integer paginasFijas;
	private Integer anuncios;
	private List<Articulo> articulos;
	
	public Revista(String numero, LocalDate fechaPublicacion, Integer ejemplares, Integer paginasFijas, Integer anuncios,
			List<Articulo> articulos) {
		this.numero = numero;
		Checkers.check("La fecha de publicacion debe ser un lunes", fechaPublicacion.getDayOfWeek().equals(DayOfWeek.MONDAY));
		this.fechaPublicacion = fechaPublicacion;
		this.ejemplares = ejemplares;
		this.paginasFijas = paginasFijas;
		this.anuncios = anuncios;
		Checkers.check("Debe haber al menos un articulo en la revista", !articulos.isEmpty());
		this.articulos = articulos;
		
		Checkers.check("El numero total de paginas debe ser multiplo de 8", getTotalPaginas()%8==0);
	}

	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(LocalDate fechaPublicacion) {
		Checkers.check("La fecha de publicacion debe ser un lunes", fechaPublicacion.getDayOfWeek().equals(DayOfWeek.MONDAY));
		this.fechaPublicacion = fechaPublicacion;
	}

	public Integer getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(Integer ejemplares) {
		this.ejemplares = ejemplares;
	}

	public String getNumero() {
		return numero;
	}

	public Integer getPaginasFijas() {
		return paginasFijas;
	}

	public Integer getAnuncios() {
		return anuncios;
	}

	public List<Articulo> getArticulos() {
		return new ArrayList<>(articulos);
	}

	public Integer getTotalPaginas() {
		Integer aux = 0;
		Integer res = paginasFijas;
		for(Articulo articulo: articulos) {
			
			aux =+ articulo.paginas();
		}

		return aux + res;
	}
	
	public Boolean getRevistaTematica() {
		Boolean res = false;
		
		for(Articulo articulo: articulos) {
			if(articulo.getArticuloLargo().equals(true)) {
				res = true;
				break;
				
			}
		}
		return res;
	}
	
	public Extension getExtension() {
		
		Extension res = Extension.BAJA;
		
		if(getTotalPaginas()>= 32 && getTotalPaginas()<=64) {
			
			res = Extension.MEDIA;
			
		}
		if(getTotalPaginas()> 64) {
			
			res = Extension.ALTA;
		}
		
		return res;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Revista other = (Revista) obj;
		return Objects.equals(numero, other.numero);
	}

	@Override
	public int compareTo(Revista o) {
		int res = getNumero().compareTo(o.numero);
		return res;
	}

}
