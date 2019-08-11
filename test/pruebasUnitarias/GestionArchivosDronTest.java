package pruebasUnitarias;

import static org.junit.Assert.assertTrue;

import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import control.DronesControlador;
import exception.ArchivosException;
import modelo.Coordenada;
import modelo.GestionArchivosDron;

@RunWith(JUnit4.class)
public class GestionArchivosDronTest {

	private GestionArchivosDron gesti;
	private Logger logger = Logger.getLogger(GestionArchivosDronTest.class.getName());
	private static final String ORIENTACION = "Norte";

	@Test
	public void validarEstructuraTest() {
		gesti = new GestionArchivosDron();
		String ruta = "DDAIAD";
		assertTrue(gesti.validarEstructura(ruta));
	}

	@Test
	public void validarEstructuraErrorTest() {
		gesti = new GestionArchivosDron();
		String ruta = "SDDAIAD";
		assertTrue(!gesti.validarEstructura(ruta));
	}

	@Test
	public void leerAchivoRutaInvalidaTest() {
		gesti = new GestionArchivosDron();
		try {
			assertTrue(gesti.leerAchivo("//rutaErronea", 0) == null);
		} catch (ArchivosException e) {
			logger.info(" " + e);
		}
	}

	@Test
	public void leerAchivoSinArchivoTest() {
		gesti = new GestionArchivosDron();
		try {
			assertTrue(gesti.leerAchivo("./resources/entrada.txt", 3) == null);
		} catch (ArchivosException e) {
			logger.info("Se presento el siguiente error, " + e);
		}
	}

	@Test
	public void leerAchivoTest() {

		String ruta = "./resources/in.txt";
		int numDron = 3;
		String mensaje;
		DronesControlador controlador = new DronesControlador(ruta, numDron);
		mensaje = controlador.iniciarDespachos();
		assertTrue(mensaje.equals("El despacho se genero con Exito"));
	}

	@Test
	public void leerAchivoDatosNullTest() {
		gesti = new GestionArchivosDron();
		try {
			assertTrue(gesti.leerAchivo(null, 0) == null);
		} catch (ArchivosException e) {
			logger.info(" " + e);
		}
	}

	@Test
	public void leerAchivoCapacidadInvalidaTest() {
		gesti = new GestionArchivosDron();
		try {
			assertTrue(gesti.leerAchivo("Hl", -1) == null);
		} catch (ArchivosException e) {
			logger.info("Se presento el siguiente error, " + e);
		}
	}
}
