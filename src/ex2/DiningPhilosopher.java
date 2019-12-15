package ex2;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.Semaphore;

public class DiningPhilosopher implements RestaurantResource {

    int numberForks = 0;
    Semaphore mutex =  new Semaphore(1);
    PipedInputStream[] pipeIn;
    PipedOutputStream[] pipeOut;
    int amountOfPhilosophersTryingToEat = 0;

    public DiningPhilosopher(int qntResources) {

        numberForks = qntResources;
        pipeIn = new PipedInputStream[numberForks];
        pipeOut = new PipedOutputStream[numberForks];

        int iterator = numberForks;
        while(iterator != 0) {
            PipedOutputStream forkOutPut = new PipedOutputStream();
            pipeOut[iterator-1] = forkOutPut;
            try {
                pipeIn[iterator-1] = new PipedInputStream(forkOutPut, 1);
            } catch (IOException error){
                error.printStackTrace();
            }
            iterator--;
        }
        mutex = new Semaphore(1);
    }

    @Override
    public void pickFork(int idPhilo) {
        try {
            mutex.acquire();
            amountOfPhilosophersTryingToEat++;
            if (amountOfPhilosophersTryingToEat == numberForks) {
                mutex.release();
                Thread.sleep(500);
            } else {
                mutex.release();
            }
            pipeOut[idPhilo].write(1);
            pipeOut[(idPhilo + 1) % numberForks].write(2);

        } catch (IOException | InterruptedException error) {
            error.printStackTrace();
        }
    }

    @Override
    public void dropFork(int idPhilo) {
        try {
            mutex.acquire();
            amountOfPhilosophersTryingToEat--;
            mutex.release();

            pipeIn[idPhilo].read();
            pipeIn[(idPhilo + 1) % numberForks].read();

        } catch (IOException | InterruptedException error) {
            error.printStackTrace();
        }
    }
}
