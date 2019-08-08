package Modelo;

import java.util.ArrayList;

public interface GestionArchivos {

	public ArrayList<String> leerAchivo(String ruta, int capacidadDron);

	public boolean validarEstructura(String archivo);

}
