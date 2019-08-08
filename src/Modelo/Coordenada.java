package Modelo;

public class Coordenada {

	private int x;
	private int y;
	private Character orientacion;

	public Coordenada(int x, int y, Character orientacion) {
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

	public Character getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(Character orientacion) {
		this.orientacion = orientacion;
	}

}
