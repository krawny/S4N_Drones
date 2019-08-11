package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import exception.ArchivosException;

public class GestionArchivosDron implements GestionArchivos {

	private Coordenada coordenada;

	public GestionArchivosDron(Coordenada coordenada) {
		this.coordenada = coordenada;
	}

	@Override
	public ArrayList<String> leerAchivo(String ruta, int capacidadDron) throws ArchivosException {

		if (ruta == null || capacidadDron <= 0) {
			throw new ArchivosException(
					"No se proporcionó información de la ruta del archivo o de la capacidad del dron");
		}

		File archivoEntrada = null;
		BufferedReader buffer = null;
		ArrayList<String> listPedidos = null;
		archivoEntrada = new File(ruta);
		if (!archivoEntrada.exists()) {
			throw new ArchivosException("No se ha encontrado el archivo en la ruta: " + ruta);
		}

		try (FileReader lector = new FileReader(archivoEntrada)) {
			buffer = new BufferedReader(lector);
			String linea;
			listPedidos = new ArrayList<>();

			for (int i = 0; (linea = buffer.readLine()) != null; i++) {
				if (i >= capacidadDron) {
					throw new ArchivosException("El archivo tiene más pedidos de los soportados por el dron");
				}
				if (!validarEstructura(linea)) {
					throw new ArchivosException(
							"Las rutas no tienen una estructura valida o se encuentran fuera del rango de covertura");
				}
				listPedidos.add(linea);
			}
		} catch (Exception e) {
			throw new ArchivosException("Se presento el siguiente error, " + e, e);
		}
		return listPedidos;
	}

	@Override
	public boolean validarEstructura(String linea) {

		for (int i = 0; i < linea.length(); i++) {
			if (!(linea.charAt(i) == 'A' || linea.charAt(i) == 'D' || linea.charAt(i) == 'I')) {
				return false;
			}
			simularMov(linea.charAt(i));
			if (Math.abs(coordenada.getX()) > 10 || Math.abs(coordenada.getY()) > 10) {
				return false;
			}
		}
		return true;
	}

	public void simularMov(Character mov) {

		if (mov == 'A') {

			if (coordenada.getOrientacion().equals("Norte")) {
				coordenada.setY(coordenada.getY() + 1);
			} else if (coordenada.getOrientacion().equals("Sur")) {
				coordenada.setY(coordenada.getY() - 1);
			} else if (coordenada.getOrientacion().equals("Oriente")) {
				coordenada.setX(coordenada.getX() + 1);
			} else {
				coordenada.setX(coordenada.getX() - 1);
			}
		} else if ((mov == 'I' && coordenada.getOrientacion().equals("Norte"))
				|| (mov == 'I' && coordenada.getOrientacion().equals("Sur"))) {
			coordenada.setOrientacion("Occidente");
		} else if ((mov == 'D' && coordenada.getOrientacion().equals("Norte"))
				|| (mov == 'D' && coordenada.getOrientacion().equals("Sur"))) {
			coordenada.setOrientacion("Oriente");
		} else if ((mov == 'I' && coordenada.getOrientacion().equals("Oriente"))
				|| (mov == 'D' && coordenada.getOrientacion().equals("Occidente"))) {
			coordenada.setOrientacion("Norte");
		} else {
			coordenada.setOrientacion("Sur");
		}

	}
}
