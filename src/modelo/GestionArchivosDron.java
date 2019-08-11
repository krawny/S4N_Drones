package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import exception.ArchivosException;

public class GestionArchivosDron implements GestionArchivos {

	private Coordenada coordenada;
	private static final String NORTE = "Norte";
	private static final String SUR = "Sur";
	private static final String ORIENTE = "Oriente";
	private static final String OCCIDENTE = "Occidente";

	public GestionArchivosDron() {
		this.coordenada = new Coordenada(0, 0, NORTE);
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

		switch (coordenada.getOrientacion()) {
		case NORTE:
			if (mov == 'A') {
				coordenada.setY(coordenada.getY() + 1);
			} else if (mov == 'I') {
				coordenada.setOrientacion(OCCIDENTE);
			} else {
				coordenada.setOrientacion(ORIENTE);
			}
			break;
		case SUR:
			if (mov == 'A') {
				coordenada.setY(coordenada.getY() - 1);
			} else if (mov == 'I') {
				coordenada.setOrientacion(OCCIDENTE);
			} else {
				coordenada.setOrientacion(ORIENTE);
			}
			break;
		case ORIENTE:
			if (mov == 'A') {
				coordenada.setY(coordenada.getX() + 1);
			} else if (mov == 'I') {
				coordenada.setOrientacion(NORTE);
			} else {
				coordenada.setOrientacion(SUR);
			}
			break;
		case OCCIDENTE:
			if (mov == 'A') {
				coordenada.setY(coordenada.getX() + -1);
			} else if (mov == 'I') {
				coordenada.setOrientacion(SUR);
			} else {
				coordenada.setOrientacion(NORTE);
			}
			break;

		default:
			break;
		}

	}

}
