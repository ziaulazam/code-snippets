package queue;

/**
 * This is an generic Queue implementation using a circular array.
 * This class has been optimized for algorithmic runtime, memory usage,
 * and memory throughput.
 *
 * @author Quinn Liu(quinnliu@vt.edu)
 */
public class Queue<E> {
    private static final int DEFAULT_SIZE = 10;
    private int maxSize;
    private int frontElementIndex;
    private int rearElementIndex;
    private E circularArray[];

    /**
     * Creates a queue.
     *
     * @param maxSize The maximum number of elements this queue can hold.
     */
    public Queue(int maxSize) {
        // One extra space is added and the queue is only allowed to have
        // a max of maxSize number of elements. This is used for the logic
        // to determine if the queue is empty or full as it is difting
        // through the circular array.
        this.maxSize = maxSize+1;
        this.rearElementIndex = 0;
        this.frontElementIndex = 1;
        this.circularArray = (E[]) new Object[this.maxSize];
    }

    /**
     * Creates a queue with default size.
     */
    public Queue() { this(DEFAULT_SIZE); }

    /**
     * Adds an element to the queue.
     *
     * @param element Element to add to the queue if not full.
     * @return whether the element is added to queue.
     * @throws java.lang.IllegalStateException If queue is full.
     */
    public boolean enqueue(E element) {
        if (((rearElementIndex +2) % maxSize) == frontElementIndex) {
            throw new IllegalStateException("Bounded queue is full at size = "
                    + this.length() + ". Call dequeue() before recalling " +
                    "enqueue()");
        }

        // increment without going out the bounds of the circular array
        rearElementIndex = (rearElementIndex +1) % maxSize;
        circularArray[rearElementIndex] = element;
        return true;
    }

    /**
     * @return Remove and return element at front of queue.
     * @throws java.lang.IllegalStateException If queue is empty.
     */
    public E dequeue() {
        if(length() == 0) {
            throw new IllegalStateException("Bounded queue is currently empty. "
                    + "Call enqueue() before dequeue()");
        }
        E element = circularArray[frontElementIndex];

        // increment without going out the bounds of the circular array
        frontElementIndex = (frontElementIndex +1) % maxSize;
        return element;
    }

    /** Returns the front element or null if queue is empty. */
    public E frontValue() {
        if (length() == 0) {
            return null;
        } else {
            return circularArray[frontElementIndex];
        }
    }

    /** Returns the number of elements in queue. */
    public int length() {
        return ((rearElementIndex + maxSize) - frontElementIndex + 1) % maxSize;
    }

    /**
     * Removes all elements in queue.
     */
    public void clear() {
        rearElementIndex = 0;
        frontElementIndex = 1;
    }
}