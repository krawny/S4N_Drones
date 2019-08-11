package vista;

import control.DronesControlador;
import modelo.Coordenada;

public class DronesAplicacion {

	public static void main(String[] args) {

		String ruta = "./resources/in.txt";
		int numDron = 3;
		String mensaje;
		Coordenada coordenada = new Coordenada(0, 0, "Norte");
		DronesControlador controlador = new DronesControlador(ruta, numDron, coordenada);
		mensaje = controlador.iniciarDespachos();
		System.out.println(mensaje);
	}
}
