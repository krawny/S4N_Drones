package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class GestionArchivosDron implements GestionArchivos {

	private Coordenada coordenada;
	private String mjError;

	public GestionArchivosDron(Coordenada coordenada) {
		this.coordenada = coordenada;
	}

	@Override
	public ArrayList<String> leerAchivo(String ruta, int capacidadDron) {

		File archivoEntrada = null;
		FileReader lector = null;
		BufferedReader buffer = null;
		ArrayList<String> listPedidos = null;

		try {

			archivoEntrada = new File(ruta);
			lector = new FileReader(archivoEntrada);
			buffer = new BufferedReader(lector);
			String linea;
			listPedidos = new ArrayList<String>();

			for (int i = 0; (linea = buffer.readLine()) != null; i++) {

				if (i >= capacidadDron) {
					mjError = "Archivo Invalido: tiene más pedidos de los soportados";
					return null;
				}
				if (!validarEstructura(linea)) {
					return null;
				}
				listPedidos.add(linea);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (null != lector) {
					lector.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return listPedidos;
	}

	@Override
	public boolean validarEstructura(String linea) {

		for (int i = 0; i < linea.length(); i++) {
			if (!(linea.charAt(i) == 'A' || linea.charAt(i) == 'D' || linea.charAt(i) == 'I')) {
				mjError = "Archivo Invalido: Caracteres invalidos";
				return false;
			}

			simularMov(linea.charAt(i));

			if (Math.abs(coordenada.getX()) > 10 || Math.abs(coordenada.getY()) > 10) {
				mjError = "Archivo Invalido: ruta fuera del rango";
				return false;
			}
		}

		return true;
	}

	public void simularMov(Character mov) {

		if (mov == 'A') {

			if (coordenada.getOrientacion() == 'N') {
				coordenada.setY(coordenada.getY() + 1);
			} else if (coordenada.getOrientacion() == 'S') {
				coordenada.setY(coordenada.getY() - 1);
			} else if (coordenada.getOrientacion() == 'E') {
				coordenada.setX(coordenada.getX() + 1);
			} else {
				coordenada.setX(coordenada.getX() - 1);
			}
		} else if ((mov == 'I' && coordenada.getOrientacion() == 'N')
				|| (mov == 'I' && coordenada.getOrientacion() == 'S')) {
			coordenada.setOrientacion('O');
		} else if ((mov == 'D' && coordenada.getOrientacion() == 'N')
				|| (mov == 'D' && coordenada.getOrientacion() == 'S')) {
			coordenada.setOrientacion('E');
		} else if ((mov == 'I' && coordenada.getOrientacion() == 'E')
				|| (mov == 'D' && coordenada.getOrientacion() == 'O')) {
			coordenada.setOrientacion('N');
		} else {
			coordenada.setOrientacion('S');
		}

	}

	public String getMjError() {
		return mjError;
	}

}
