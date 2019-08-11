package runnable;

public class Work implements Runnable{

	private int indice;
	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public Work(){
		
	}

	@Override
	public void run() {
		System.out.println("Ejecutando el hilo "+ indice);
		//llamado al método
		
	}

}
