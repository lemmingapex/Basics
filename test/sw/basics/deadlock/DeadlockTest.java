package sw.basics.deadlock;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class DeadlockTest {

	@Test
	public void test() throws InterruptedException {
		BlockingQueue<Runnable> q = new LinkedBlockingQueue<Runnable>();
		for(int i = 0; i < 100; i++){
            q.add(new Deadlock(i));
		}
		
		ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 100, 1, TimeUnit.MINUTES, q);
		pool.prestartAllCoreThreads();
		pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
		System.out.println("Never run");
	}
}
