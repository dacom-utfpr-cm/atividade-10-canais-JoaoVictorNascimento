package ex1;

import java.nio.channels.Pipe;
import java.nio.ByteBuffer;
import java.io.IOException;

public class Producer implements Runnable {
    private int rawMaterial;
    private Pipe pipi;

    public Producer(int rawMaterial, Pipe pipi){
        this.rawMaterial = rawMaterial;
        this.pipi = pipi;
    }

    @Override
    public void run() {
        while(true) {
            Pipe.SinkChannel sinkChannel = pipi.sink();
            ByteBuffer buf = ByteBuffer.allocate(48);
            System.out.println(Thread.currentThread().getName() + " starting production...");

            buf.clear();
            buf.putInt(rawMaterial++);
            buf.flip();

            while (buf.hasRemaining()) {
                try{
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " producing...");
                    sinkChannel.write(buf);
                } catch (IOException | InterruptedException error) {
                    error.printStackTrace();
                }
            }

        }
    }
}
