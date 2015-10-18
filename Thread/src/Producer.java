import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Producer implements Runnable {
static final int MAXQUEUE = 5;
private List messages = new ArrayList();
	public void run() {
		while(true) {
			putMessage();
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {}
		}
	}
	
	private synchronized void putMessage() {
		while(messages.size() >= MAXQUEUE) 
			try {
				wait();
			}catch (InterruptedException e) {}
			messages.add(new Date().toString());
			notify();
		
		
	}
	
	public synchronized String getMessage() {
		while(messages.size() == 0)
			try {
				notify();
				wait();
			} catch(InterruptedException e) {}
		String message = (String) messages.remove(0);
		return message;
	}
		}
	


