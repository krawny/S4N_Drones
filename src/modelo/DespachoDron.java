package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import exception.ArchivosException;

public class DespachoDron implements Despacho {

	private ArrayList<String> rutas;
	private ArrayList<String> coordenadas;
	private Coordenada coordenada;
	private final String norte;

	public DespachoDron(ArrayList<String> rutas) {
		this.rutas = rutas;
		this.norte = "Norte";
		this.coordenada = new Coordenada(0, 0, norte);
		this.coordenadas = new ArrayList<String>();
	}

	/**
	 * Este m�todo se encarga de mover el dron de acuerdo con las rutas de entrada,
	 * al finalizar el total de entregas genera un archivo con las coordenadas del
	 * dron en el momento de cada entrega.
	 */
	@Override
	public boolean entregaPedido() throws ArchivosException {
		for (String ruta : rutas) {
			for (int i = 0; i < ruta.length(); i++) {
				moverDron(ruta.charAt(i));
			}
			coordenadas.add("(" + coordenada.getX() + ", " + coordenada.getY() + ") " + coordenada.getOrientacion());
		}
		return crearReportEntrega();
	}

	/**
	 * Genera los movimientos del dron de acuerdo con las indicaciones de las rutas
	 * y a las pol�ticas de movimiento definidas
	 **/
	public void moverDron(Character mov) {

		if (mov == 'A') {

			if (coordenada.getOrientacion().equals(norte)) {
				coordenada.setY(coordenada.getY() + 1);
			} else if (coordenada.getOrientacion().equals("Sur")) {
				coordenada.setY(coordenada.getY() - 1);
			} else if (coordenada.getOrientacion().equals("Oriente")) {
				coordenada.setX(coordenada.getX() + 1);
			} else {
				coordenada.setX(coordenada.getX() - 1);
			}
		} else if ((mov == 'I' && coordenada.getOrientacion().equals(norte))
				|| (mov == 'I' && coordenada.getOrientacion().equals("Sur"))) {
			coordenada.setOrientacion("Occidente");
		} else if ((mov == 'D' && coordenada.getOrientacion().equals(norte))
				|| (mov == 'D' && coordenada.getOrientacion().equals("Sur"))) {
			coordenada.setOrientacion("Oriente");
		} else if ((mov == 'I' && coordenada.getOrientacion().equals("Oriente"))
				|| (mov == 'D' && coordenada.getOrientacion().equals("Occidente"))) {
			coordenada.setOrientacion(norte);
		} else {
			coordenada.setOrientacion("Sur");
		}

	}

	/**
	 * Genera el reporte de entrega en un archivo txt con las coordenadas de la
	 * ubicaci�n del dron en el momento de la entrega
	 **/
	public boolean crearReportEntrega() {

		File reportEntrega;
		reportEntrega = new File("./resources/out.txt");

		try (FileWriter fileWiter = new FileWriter(reportEntrega)) {

			BufferedWriter bufferedW = new BufferedWriter(fileWiter);
			PrintWriter printW = new PrintWriter(bufferedW);

			for (String linea : coordenadas) {
				printW.write(linea + "\n");
			}

			printW.close();
			bufferedW.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public Coordenada getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}

	public ArrayList<String> getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(ArrayList<String> coordenadas) {
		this.coordenadas = coordenadas;
	}

}
