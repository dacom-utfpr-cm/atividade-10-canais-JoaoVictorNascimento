package ex2;
import java.util.Random;

public class Philosopher implements Runnable {
    int id = 0;
    RestaurantResource restaurantResource = null;

    public Philosopher(int id, RestaurantResource restaurantResource){
        this.id = id;
        this.restaurantResource = restaurantResource;
    }

    @Override
    public void run() {
        while(true) {
            restaurantResource.pickFork(id);
            try {
                int randomTime = new Random().nextInt(200);
                Thread.sleep(randomTime);
                System.out.println("Philosopher " + id + " is eating...");
            } catch (Exception error) {
                error.printStackTrace();
            }
            restaurantResource.dropFork(id);
        }
    }
}
