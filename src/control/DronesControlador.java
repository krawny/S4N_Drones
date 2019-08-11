package control;

import java.util.ArrayList;

import exception.ArchivosException;
import modelo.Coordenada;
import modelo.DespachoDron;
import modelo.GestionArchivosDron;

public class DronesControlador {
	
	private String ruta;
	private int numDron;
	
	public DronesControlador(String ruta, int numDron) {
		this.ruta = ruta;
		this.numDron = numDron;
	}

	public String iniciarDespachos() {

		GestionArchivosDron gesti = new GestionArchivosDron();
		ArrayList<String> rutasObtenidasArchivo;
		try {
			rutasObtenidasArchivo = gesti.leerAchivo(ruta, numDron);
			if (rutasObtenidasArchivo == null) {
				return "Se presento un error ";
			}
			DespachoDron despachoDron = new DespachoDron(rutasObtenidasArchivo);

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
