//lab-6 write a java program to create thread class

class A extends Thread {
    public void run() {
        for(int i=1;i<=3;i++)
            System.out.println("A: " + i);
        System.out.println("Exit A");
    }
}

class B extends Thread {
    public void run() {
        for(int i=1;i<=3;i++)
            System.out.println("B: " + i);
        System.out.println("Exit B");
    }
}

class C extends Thread {
    public void run() {
        for(int i=1;i<=3;i++)
            System.out.println("C: " + i);
        System.out.println("Exit C");
    }
}

public class ThreadTest {
    public static void main(String[] args) {
        new A().start();
        new B().start();
        new C().start();
    }
}
