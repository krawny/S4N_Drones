package modelo;

import java.util.ArrayList;

import exception.ArchivosException;

public interface GestionArchivos {

	/**
	 * 
	 * @param ruta
	 * @param capacidadDron
	 * @return lista de las ruta destino de los pedidos
	 * @throws ArchivosException Se lanza cuando los parametros son invalidos
	 */
	public ArrayList<String> leerAchivo(String ruta, int capacidadDron) throws ArchivosException;

	public boolean validarEstructura(String archivo);

}
