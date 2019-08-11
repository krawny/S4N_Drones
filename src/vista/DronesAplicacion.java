package vista;

import control.DronesControlador;
import modelo.Coordenada;

public class DronesAplicacion {

	public static void main(String[] args) {

		String ruta = "./resources/in.txt";
		int numDron = 3;
		String mensaje;
		DronesControlador controlador = new DronesControlador(ruta, numDron);
		mensaje = controlador.iniciarDespachos();
		System.out.println(mensaje);
	}
}
