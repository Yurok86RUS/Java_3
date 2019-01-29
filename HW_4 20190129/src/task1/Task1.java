package task1;

public class Task1 {

    public static void main(String[] args) throws InterruptedException {

        WaitNotify waitNotify = new WaitNotify();
        waitNotify.start();

    }

    public static class WaitNotify {
        private final Object monitor = new Object(); // sync
        private volatile char currentLetter = 'A';

        void printA() {
            synchronized (monitor) {
                try {
                    for (int i = 0; i < 5; i++) {
                        while (currentLetter != 'A') {
                            monitor.wait();
                        }
                        System.out.print("A");
                        currentLetter = 'B';
                        monitor.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        void printB() {
            synchronized (monitor) {
                try {
                    for (int i = 0; i < 5; i++) {
                        if (currentLetter != 'B') {
                            monitor.wait();
                        }
                        System.out.print("B");
                        currentLetter = 'A';
                        monitor.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        void start() throws InterruptedException {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    printA();
                }
            });
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    printB();
                }
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        }

    }


}
