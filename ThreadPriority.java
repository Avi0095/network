// lab 8 Write a Java program to use priority of thread

class A extends Thread {
    public void run() {
        System.out.println("A started");
        for(int i=1;i<=4;i++)
            System.out.println("A: " + i);
        System.out.println("Exit A");
    }
}

class B extends Thread {
    public void run() {
        System.out.println("B started");
        for(int i=1;i<=4;i++)
            System.out.println("B: " + i);
        System.out.println("Exit B");
    }
}

class C extends Thread {
    public void run() {
        System.out.println("C started");
        for(int i=1;i<=4;i++)
            System.out.println("C: " + i);
        System.out.println("Exit C");
    }
}

public class ThreadPriority {
    public static void main(String[] args) {

        A a = new A();
        B b = new B();
        C c = new C();

        a.setPriority(1);
        b.setPriority(5);
        c.setPriority(10);

        System.out.println("Start A"); a.start();
        System.out.println("Start B"); b.start();
        System.out.println("Start C"); c.start();

        System.out.println("End main");
    }
}
