package indi.oracle.java.thread;

public class HoldOnHelperThread extends Thread {
    
    private volatile boolean retire = false;

    @Override
    public void run() {
        while (!retire) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
