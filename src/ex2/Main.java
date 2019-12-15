/*
    Autor: João Victor Nascimento
    RA: 1817442
    Descrição: Implementar o problema do jantar dos filósofos usando Java IO: PipedInputStream e PipedOutputStream.
 */

package ex2;

public class Main {
    public static void main(String[] args) {
        DiningPhilosopher philosopher = new DiningPhilosopher(5);
        for(int i=0; i < 5; i++){
            new Thread(new Philosopher(i, philosopher)).start();
        }
    }
}
