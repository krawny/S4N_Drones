package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DespachoDron implements Despacho {

	private ArrayList<String> rutas;
	private ArrayList<String> coordenadas;
	private Coordenada coordenada;

	public DespachoDron(ArrayList<String> rutas, Coordenada coordenada) {
		this.rutas = rutas;
		this.setCoordenada(coordenada);
		this.coordenadas = new ArrayList<String>();
	}

	/**
	 * Este método se encarga de mover el dron de acuerdo con las rutas de entrada,
	 * al finalizar el total de entregas genera un archivo con las coordenadas del 
	 * dron en el momento de cada entrega.
	 * */
	@Override
	public boolean entregaPedido() {
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
	 * y a las políticas de movimiento definidas
	 * **/
	public void moverDron(Character mov) {

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

	/**
	 * Genera el reporte de entrega en un archivo txt con las coordenadas de
	 * la ubicación del dron en el momento de la entrega
	 **/
	public boolean crearReportEntrega() {

		File reportEntrega;
		reportEntrega = new File("./resources/out.txt");

		try {
			FileWriter fileWiter = new FileWriter(reportEntrega);
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
		;

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
