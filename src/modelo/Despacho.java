package modelo;

import exception.ArchivosException;

public interface Despacho {

	/**
	 * 
	 * @return
	 * @throws ArchivosException se lanza cuando no es posible recorrer las rutas o generar el archivo de salida
	 */
	public boolean entregaPedido() throws ArchivosException;

}
