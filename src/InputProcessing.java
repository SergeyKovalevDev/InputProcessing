import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class InputProcessing {
    private final List<Integer> list = new LinkedList<>();
    private Integer lastAverage = 50;

    private void taskStarter() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        new Thread(() -> {
            try {
                Thread getter = new Thread(new dataGetter());
                Thread handler = new Thread(new dataHandler());
                System.out.println(getter.isAlive());
                boolean isExit = false;
                while (!isExit) {
                    switch (reader.readLine()) {
                        case "start" -> {
                            getter.start();
//                            handler.start();
                        }
                        case "stop" -> {
                            getter.interrupt();
//                            handler.interrupt();
                        }
                        case "exit" -> {
                            if (getter.isAlive() || handler.isAlive()) {
                                System.out.println("You cannot exit while the threads are running");
                            }
                            else {
                                isExit = true;
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private class dataGetter extends Thread {

        @Override
        public void run() {
            while (true) {
                Integer data = dataGenerator();
                list.add(data);
//                System.err.println(data);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    private Integer dataGenerator() {
        return (int) (Math.random() * 100);
    }

    private class dataHandler extends Thread {
        @Override
        public void run() {
            while (true) {

            }
        }
    }

    private class userInterface implements Runnable {
        @Override
        public void run() {

        }
    }

    public static void main(String[] args) {
        InputProcessing inputProcessing = new InputProcessing();
        inputProcessing.taskStarter();
    }
}
