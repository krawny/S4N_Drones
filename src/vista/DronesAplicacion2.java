package vista;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import runnable.Work;

public class DronesAplicacion2 {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(2);
	for(int i= 1; i<=2; i++) {
		Work work = new Work();
		work.setIndice(i);
		executor.submit(work);
	}
	
	
		
	}
}
