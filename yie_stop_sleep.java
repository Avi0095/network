// Lab_7:  Write a Java program to illustrate yield(), stop() and sleep() method using thread

class A extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            if (i == 1)
                Thread.yield();

            System.out.println("From A: " + i);
        }
        System.out.println("Exit A");
    }
}

class B extends Thread {
    public void run() {
        for (int j = 1; j <= 5; j++) {
            System.out.println("From B: " + j);

            if (j == 3) {
                System.out.println("Exit B");
                return; // stop() er poriborte return
            }
        }
        System.out.println("Exit B");
    }
}

class C extends Thread {
    public void run() {
        for (int k = 1; k <= 5; k++) {
            System.out.println("From C: " + k);

            if (k == 1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        System.out.println("Exit C");
    }
}

public class yie_stop_sleep {
    public static void main(String[] args) {

        System.out.println("Start A");
        new A().start();

        System.out.println("Start B");
        new B().start();

        System.out.println("Start C");
        new C().start();

        System.out.println("End main");
    }
}