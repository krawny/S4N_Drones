package Modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DespachoDron implements Despacho {

	private ArrayList<String> rutas;
	private ArrayList<String> cordenadas;
	private Coordenada coordenada;

	public DespachoDron(ArrayList<String> rutas, Coordenada coordenada) {
		this.rutas = rutas;
		this.setCoordenada(coordenada);
		this.cordenadas = new ArrayList<String>();
	}

	/*
	 * Retorno si fue exitoso generar el pedido, falso si por alguna razón no se
	 * entrego o genero el pedido.
	 */
	@Override
	public boolean entregaPedido() {
		for (String ruta : rutas) {
			for (int i = 0; i < ruta.length(); i++) {
				moverDron(ruta.charAt(i));
			}
			cordenadas.add("(" + coordenada.getX() + ", " + coordenada.getY() + ") " + coordenada.getOrientacion());
		}
		return crearReportEntrega();
	}

	public void moverDron(Character mov) {

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

	/*
	 * Genera el reporte de entrega en un archivo txt con las coordenadas de
	 * ubicación y lo deja en resources
	 */
	public boolean crearReportEntrega() {

		File reportEntrega;
		reportEntrega = new File("./resources/out.txt");

		try {
			FileWriter fileWiter = new FileWriter(reportEntrega);
			BufferedWriter bufferedW = new BufferedWriter(fileWiter);
			PrintWriter printW = new PrintWriter(bufferedW);

			for (String linea : cordenadas) {
				printW.write(linea + "\n");
			}

			printW.close();
			bufferedW.close();
		} catch (IOException e) {

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

}
