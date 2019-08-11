package pruebasUnitarias;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import exception.ArchivosException;
import modelo.Coordenada;
import modelo.DespachoDron;

@RunWith(JUnit4.class)
public class DespachoDronTest {

	@Test
	public void entregaPedidoTest() throws ArchivosException {
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
	public void crearReportEntregaTest() throws ArchivosException {

		ArrayList<String> rutas = new ArrayList<String>();
		rutas.add("AAAAIAAD");
		rutas.add("DDAIAI");
		rutas.add("AAIADAD");
		ArrayList<String> coordenadas = new ArrayList<String>();
		Coordenada coordenada = new Coordenada(0, 0, "Norte");
		DespachoDron despachoDron = new DespachoDron(rutas, coordenada);
		coordenadas.add("Hola");
		coordenadas.add("Mundo");
		despachoDron.setCoordenadas(coordenadas);
		
		File reportEntrega = new File("./resources/out.txt");
		assertTrue(despachoDron.crearReportEntrega());
		assertTrue(reportEntrega.exists());
	}
}
