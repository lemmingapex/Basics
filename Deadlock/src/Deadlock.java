import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Deadlock implements Runnable {

	final int id;
	
	public Deadlock(int pId) {
		// TODO Auto-generated constructor stub
		id = pId;
	}
	
	public void method1() {
		synchronized (String.class) {
			System.out.println("Aquired lock on String.class object");

			synchronized (Integer.class) {
				System.out.println("Aquired lock on Integer.class object");
			}
		}
	}

	public void method2() {
		synchronized (Integer.class) {
			System.out.println("Aquired lock on Integer.class object");

			synchronized (String.class) {
				System.out.println("Aquired lock on String.class object");
			}
		}
	}

	@Override
	public void run() {
		method1();
		method2();
		System.out.println(id + " is all done!");
	}
	
	public static void main(String args[]) {
		BlockingQueue<Runnable> q = new LinkedBlockingQueue<Runnable>();
		for(int i = 0; i < 100; i++){
            q.add(new Deadlock(i));
		}
		
		ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 100, 1, TimeUnit.MINUTES, q);
		pool.prestartAllCoreThreads();
	}
}
