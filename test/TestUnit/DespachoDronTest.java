package TestUnit;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import Modelo.Coordenada;
import Modelo.DespachoDron;

@RunWith(JUnit4.class)
public class DespachoDronTest {

	public DespachoDron despachoDron;
	private ArrayList<String> rutas;
	private ArrayList<String> cordenadas;
	private Coordenada coordenada;

	@Before
	public void Before() {

		ArrayList<String> rutas = new ArrayList<String>();
		rutas.add("AAAAIAAD");
		rutas.add("DDAIAI");
		rutas.add("AAIADAD");
		ArrayList<String> coordenadas = new ArrayList<String>();
		Coordenada coordenada = new Coordenada(0, 0, "Norte");
		DespachoDron despachoDron = new DespachoDron(rutas, coordenada);
	}

	@Test
	public void entregaPedidoTest() {
		ArrayList<String> rutas = new ArrayList<String>();
		rutas.add("AAAAIAAD");
		rutas.add("DDAIAI");
		rutas.add("AAIADAD");
		ArrayList<String> coordenadas = new ArrayList<String>();
		Coordenada coordenada = new Coordenada(0, 0, "Norte");
		DespachoDron despachoDron = new DespachoDron(rutas, coordenada);
		despachoDron.setCoordenadas(coordenadas);

		assertTrue(despachoDron.entregaPedido());
		assertTrue(coordenadas.get(0).equals("(-2, 4) Norte"));
		assertTrue(coordenadas.get(1).equals("(-3, 3) Sur"));
		assertTrue(coordenadas.get(2).equals("(-4, 2) Oriente"));

	}

	@Test
	public void crearReportEntregaTest() {

		ArrayList<String> rutas = new ArrayList<String>();
		rutas.add("AAAAIAAD");
		rutas.add("DDAIAI");
		rutas.add("AAIADAD");
		ArrayList<String> coordenadas = new ArrayList<String>();
		Coordenada coordenada = new Coordenada(0, 0, "Norte");
		DespachoDron despachoDron = new DespachoDron(rutas, coordenada);
		despachoDron.setCoordenadas(coordenadas);
		coordenadas.add("Hola");
		coordenadas.add("Mundo");

		assertTrue(despachoDron.crearReportEntrega());

		File archivoEntrada = null;
		FileReader lector = null;
		BufferedReader buffer = null;
		ArrayList<String> listPedidos = null;

		try {

			archivoEntrada = new File("./resources/out.txt");
			lector = new FileReader(archivoEntrada);
			buffer = new BufferedReader(lector);
			String linea;
			listPedidos = new ArrayList<String>();

			for (int i = 0; (linea = buffer.readLine()) != null; i++) {
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

		assertTrue(listPedidos.get(0).equals("Hola"));
		System.out.println(listPedidos.get(0));
		assertTrue(listPedidos.get(1).equals("Mundo"));
		System.out.println(listPedidos.get(1));

	}
}
