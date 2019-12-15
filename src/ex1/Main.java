
/*
    Autor: João Victor Nascimento
    RA: 1817442
    Descrição: Implementar o problema do produtor-consumidor usando Java NIO: Pipe, Pipe.SinkChannel e Pipe.SourceChannel.
 */

package ex1;

import java.nio.channels.Pipe;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int rawMaterial = 10;
        Pipe pipi = Pipe.open();
        Producer producer = new Producer(10, pipi);
        Consumer consumer = new Consumer(pipi);

        new Thread(producer, "Moisés").start();

        new Thread(consumer, "Malaquías").start();
        new Thread(consumer, "Abimeleque").start();
        new Thread(consumer, "Jonas").start();
    }
}
