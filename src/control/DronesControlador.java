package control;

import java.util.ArrayList;

import exception.ArchivosException;
import modelo.Coordenada;
import modelo.DespachoDron;
import modelo.GestionArchivosDron;

public class DronesControlador {
	
	private String ruta;
	private int numDron;
	private Coordenada coordenada;
	
	public DronesControlador(String ruta, int numDron, Coordenada coordenada) {
		this.ruta = ruta;
		this.numDron = numDron;
		this.coordenada = coordenada;
	}


	public String iniciarDespachos() {

		GestionArchivosDron gesti = new GestionArchivosDron(coordenada);
		ArrayList<String> rutasObtenidasArchivo;
		try {
			rutasObtenidasArchivo = gesti.leerAchivo(ruta, numDron);
			if (rutasObtenidasArchivo == null) {
				return "Se presento un error ";
			}
			Coordenada coordenada2 = new Coordenada(0, 0, "Norte");
			DespachoDron despachoDron = new DespachoDron(rutasObtenidasArchivo, coordenada2);

			if (!despachoDron.entregaPedido()) {
				return "Se presento un error al generar el archivo";
			}

		} catch (ArchivosException e) {
			return e.getMessage();
		} catch (Exception e) {
			return "Se presento un error, " + e.getMessage();
		}
		return "El despacho se genero con Exito";
	}
}
