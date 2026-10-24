// 5 Write a Java program to illustrate suspend, resume and stop operation of thread. 

class sus_res_stop implements Runnable {

    Thread th;
    boolean suspend_flag = false, stop_flag = false;

    sus_res_stop(String name) {
        th = new Thread(this, name);
        th.start();
    }

    public void run() {
        int i = 1;
        try {
            while (i++ < 20) {

                synchronized(this) {
                    while (suspend_flag) wait();
                    if (stop_flag) break;
                }

                System.out.println("Thread running: " + i);
                Thread.sleep(300);
            }
        } catch (Exception e) {}

        System.out.println("Thread exiting");
    }

    synchronized void my_suspend() { suspend_flag = true; }

    synchronized void my_resume() { suspend_flag = false; notify(); }

    synchronized void my_stop() {
        stop_flag = true;
        suspend_flag = false;
        notify();
    }
}

public class eg_SRS {
    public static void main(String[] args) throws Exception {

        sus_res_stop t = new sus_res_stop("SRS");
        System.out.println("Thread created and started");

        Thread.sleep(2000);
        t.my_suspend();
        System.out.println("Suspended");

        Thread.sleep(2000);
        t.my_resume();
        System.out.println("Resumed");

        Thread.sleep(2000);
        t.my_stop();
        System.out.println("Stopped");
    }
}
