package es.datastructur.synthesizer;

import java.util.Random;

public class GuitarString {

    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    private Random random = new Random();

    /** Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /** Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR/frequency);
        buffer = new ArrayRingBuffer<Double>(capacity);
        for (int i = 0; i < capacity; i++) {
            buffer.enqueue(0.0);
        }
    }


    /** Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        for (int i = 0; i < buffer.fillCount(); i++) {
            double variate = random.nextDouble() - 0.5;
            buffer.dequeue();
            buffer.enqueue(variate);
        }
    }

    /** Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double frontSample = buffer.dequeue();
        frontSample = (frontSample + buffer.peek()) / 2;
        buffer.enqueue(frontSample * DECAY);
    }

    /** Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}
