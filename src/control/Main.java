package control;

import java.util.ArrayList;

import Modelo.Coordenada;
import Modelo.DespachoDron;
import Modelo.GestionArchivosDron;

public class Main {

	public static void main(String[] args) {

		String ruta = "./resources/in.txt";
		int numDron = 3;
		String mensaje;
		Coordenada coordenada = new Coordenada(0, 0, 'N');
		mensaje = controlador(ruta, numDron, coordenada);
		System.out.println(mensaje);
	}

	public static String controlador(String ruta, int numDron, Coordenada coordenada) {

		GestionArchivosDron gesti = new GestionArchivosDron(coordenada);
		ArrayList<String> rutasObtenidasArchivo;
		rutasObtenidasArchivo = gesti.leerAchivo(ruta, numDron);

		if (rutasObtenidasArchivo == null) {
			// como controlo las excepciones para decir si fue q el archivo no estaba, o
			// estaba vacio o no tenia la estructura valida?
			return gesti.getMjError();
		}

		Coordenada coordenada2 = new Coordenada(0, 0, 'N');
		DespachoDron despachoDron = new DespachoDron(rutasObtenidasArchivo, coordenada2);

		if (!despachoDron.entregaPedido()) {
			return "No se pudo entregar el pedido o generar el archivo";
		}
		return "El despacho se genero con Exito";
	}

}
