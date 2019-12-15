package ex1;

import java.nio.channels.Pipe;
import java.nio.ByteBuffer;
import java.io.IOException;

public class Consumer implements Runnable {
    private Pipe pipi;

    public Consumer(Pipe pipi) {
        this.pipi = pipi;
    }

    @Override
    public void run() {
        while (true) {
            Pipe.SourceChannel sourceChannel = pipi.source();
            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();

            try{
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " consuming...");
                sourceChannel.read(buf);

            } catch (IOException | InterruptedException error) {
                error.printStackTrace();
            }

            buf.flip();
        }
    }
}
