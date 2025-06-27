package exercise;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RevistasUniversitarias  extends Revista{


	private List<Revista> revistas;

	public RevistasUniversitarias(String numero, LocalDate fechaPublicacion, Integer ejemplares,
			Integer paginasFijas, Integer anuncios, List<Articulo> articulos, List<Revista> revistas) {
		super(numero, fechaPublicacion, ejemplares, paginasFijas, anuncios, articulos);
		this.revistas = revistas;
	}

	public List<Revista> getRevistas() {
		return revistas;
	}

	
	
	
	public List<String> getTitulosOrdenados(String titulo){
		
		return revistas.stream()
				.flatMap(revista -> revista.getArticulos().stream())
				.filter(articulo -> articulo.titulo().toUpperCase().contains(titulo.toUpperCase()))
				.sorted(Comparator.comparing(articulo ->   ((Articulo) articulo).paginas()).reversed()
						.thenComparing(Comparator.comparing(articulo -> ((Articulo) articulo).categoria())))
				.map(articulo -> articulo.titulo())
				.distinct()
				.toList();
	}
	
	
	public SortedMap<Extension, Integer> getMaxArticulosPorExtension(LocalDate fechaBaja, LocalDate fechaAlta) {
	    return revistas.stream()
	            .filter(revista -> (revista.getFechaPublicacion().isAfter(fechaBaja) || revista.getFechaPublicacion().isEqual(fechaBaja))
	                    && revista.getFechaPublicacion().isBefore(fechaAlta))
	            .collect(Collectors.groupingBy(
	                    revista -> revista.getExtension(),
	                    () -> new TreeMap<Extension, Integer>(),
	                    Collectors.collectingAndThen(
	                            Collectors.maxBy(Comparator.comparing(revista -> revista.getArticulos().size())),
	                            opt -> opt.map(r -> r.getArticulos().size()).orElse(0)
	                    )
	            ));
	}

	public Map<String, Double> getPorcentajePaginasPorCategoria() {
	    Map<String, Integer> maux = getTotalPaginasPorCategoria(null);

	    Integer totalPaginas = maux.values().stream()
	        .mapToInt(valores -> valores)
	        .sum();

	    return maux.entrySet().stream()
	    		.collect(Collectors.toMap(
	    				(Map.Entry<String, Integer> entry) -> entry.getKey(),
	    		        (Map.Entry<String, Integer> entry) -> entry.getValue() * 100.0 / totalPaginas));
	}


	private Map<String, Integer> getTotalPaginasPorCategoria(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
