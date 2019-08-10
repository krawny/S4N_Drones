package modelo;

public class Coordenada {

	private int x;
	private int y;
	private String orientacion;

	public Coordenada(int x, int y, String orientacion) {
		this.x = x;
		this.y = y;
		this.orientacion = orientacion;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(String orientacion) {
		this.orientacion = orientacion;
	}

}
