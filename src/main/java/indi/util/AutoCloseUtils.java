package indi.util;

public class AutoCloseUtils {
    
    /**
     * 
     * @param function
     * @param millis
     * @return false - 未能按时完成，已终止任务
     */
    public static final boolean run(Runnable function, Long millis) {
        AutoCloseThread autoCloseThread = new AutoCloseThread(function);
        Long deadlineMillis = System.currentTimeMillis() + millis;
        autoCloseThread.start();
        while (autoCloseThread.isAlive()) {
            if (System.currentTimeMillis() > deadlineMillis) {
                autoCloseThread.interrupt();
                return false;
            }
        }
        return true;
    }

    private static class AutoCloseThread extends Thread {
        private Runnable function;
        
        public AutoCloseThread(Runnable function) {
            this.function = function;
        }
        
        @Override
        public void run() {
            function.run();
        }
    }
}
