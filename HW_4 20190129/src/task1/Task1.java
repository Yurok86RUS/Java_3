package task1;

public class Task1 {

    public static void main(String[] args) throws InterruptedException {

        WaitNotify waitNotify = new WaitNotify();
        waitNotify.start();

    }

    public static class WaitNotify {
        private final Object sync1 = new Object();
        private final Object sync2 = new Object();
        private volatile char currentLetter = 'A';

        void printA() {
            synchronized (sync1) {
                synchronized (sync2) {
                    try {
                        for (int i = 0; i < 5; i++) {
                            while (currentLetter != 'A') {
                                if (currentLetter == 'B') {
                                    sync1.wait();
                                }
                                if (currentLetter == 'C'){
                                    sync2.wait();
                                }
                            }
                            System.out.print("A");
                            currentLetter = 'B';
                            sync1.notify();
                            sync2.notify();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        void printB() {
            synchronized (sync1) {
                    try {
                        for (int i = 0; i < 5; i++) {
                            if (currentLetter != 'B') {
                                sync1.wait();
                            }
                            System.out.print("B");
                            currentLetter = 'C';
                            sync1.notify();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }

        void printC() {
            synchronized (sync2) {
                    try {
                        for (int i = 0; i < 5; i++) {
                            if (currentLetter != 'C') {
                                sync2.wait();
                            }
                            System.out.print("C");
                            currentLetter = 'A';
                            sync2.notify();
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
            Thread t3 = new Thread(new Runnable() {
                @Override
                public void run() {
                    printC();
                }
            });
            t1.start();
            t2.start();
            t3.start();
            t1.join();
            t2.join();
            t3.join();
        }

    }


}
