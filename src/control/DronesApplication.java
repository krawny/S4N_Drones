package control;

import java.util.ArrayList;

import exception.ArchivosException;
import modelo.Coordenada;
import modelo.DespachoDron;
import modelo.GestionArchivosDron;

public class DronesApplication {

	public static void main(String[] args) {

		String ruta = "./resources/in.txt";
		int numDron = 3;
		String mensaje;
		Coordenada coordenada = new Coordenada(0, 0, "Norte");
		mensaje = controlador(ruta, numDron, coordenada);
		System.out.println(mensaje);
	}

	public static String controlador(String ruta, int numDron, Coordenada coordenada) {

		GestionArchivosDron gesti = new GestionArchivosDron(coordenada);
		ArrayList<String> rutasObtenidasArchivo;
		try {
			rutasObtenidasArchivo = gesti.leerAchivo(ruta, numDron);
			
			if (rutasObtenidasArchivo == null) {
				return gesti.getMjError();
			}
			
			Coordenada coordenada2 = new Coordenada(0, 0, "Norte");
			DespachoDron despachoDron = new DespachoDron(rutasObtenidasArchivo, coordenada2);

			if (!despachoDron.entregaPedido()) {
				return "No se pudo entregar el pedido o generar el archivo";
			}
			
		} catch (ArchivosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}catch (Exception e) {
			System.out.println("Se presento un error al leer el archivo"+e.getMessage());
		}

		return "El despacho se genero con Exito";

		
	}

}
