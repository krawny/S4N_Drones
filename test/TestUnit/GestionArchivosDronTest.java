package TestUnit;

import java.io.File;

import org.junit.Test;

import modelo.Coordenada;
import modelo.GestionArchivosDron;

public class GestionArchivosDronTest {

	
	public GestionArchivosDron gesti;
	
	@Test
	public void validarEstructuraTest() {

//		Pattern p = Pattern.compile("A|D|I");
//	      Matcher m = p.matcher("qqqqqQQQ");
//	      if (!m.find()) {
//	         System.err.println("NO tiene A");
//	      }
		Coordenada coordenada = new Coordenada(0, 0, "Norte");
		gesti = new GestionArchivosDron(coordenada);
		String archivo = "DDAIAD";
		
		boolean resp = gesti.validarEstructura(archivo);
		System.out.println(resp);

	}
	
	@Test
	public void leerAchivoTest() {
		File archivoEntrada = new File("//jdj");
		if(archivoEntrada.exists()) {
			System.out.println("Existe");
		}
		
	}
}
