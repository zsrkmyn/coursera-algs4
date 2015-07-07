import java.util.NoSuchElementException;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private class ArrIterator implements Iterator<Item> {
        private int [] order;
        private int cur;
        private int size;

        public ArrIterator(int n)
        {
            size = n;
            order = new int[n];
            cur = 0;

            for (int i = 0; i < n; i++) {
                order[i] = i;
            }

            for (int i = 0; i < n; i++) {
                int j = StdRandom.uniform(i, n);
                int tmp = order[i];
                order[i] = order[j];
                order[j] = tmp;
            }
        }

        public boolean hasNext()
        { return cur < size; }

        public Item next()
        {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return storage[order[cur++]];
        }

        public void remove()
        { throw new UnsupportedOperationException(); }
    }

    private Item [] storage;
    private int size;
    private int capacity;

    public RandomizedQueue()                 // construct an empty randomized queue
    {
        capacity = 32;
        storage = (Item []) new Object[capacity];
        size = 0;
    }


    private void resize(int cap)
    {
        capacity = cap;
        Item [] nstore = (Item []) new Object[cap];
        for (int i = 0; i < size && i < cap; i++) {
            nstore[i] = storage[i];
        }
        storage = nstore;
    }

    public boolean isEmpty()                 // is the queue empty?
    { return size == 0; }

    public int size()                        // return the number of items on the queue
    { return size; }

    public void enqueue(Item item)           // add the item
    {
        if (item == null) {
            throw new NullPointerException();
        }
        if (size >= capacity) {
            resize(capacity * 2);
        }
        storage[size++] = item;
    }

    public Item dequeue()                    // remove and return a random item
    {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int i = StdRandom.uniform(size);
        Item ret = storage[i];
        storage[i] = storage[--size];
        storage[size] = null;

        if (size < capacity / 2) {
            resize(capacity / 2);
        }

        return ret;
    }

    public Item sample()                     // return (but do not remove) a random item
    {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return storage[StdRandom.uniform(size)];
    }

    public Iterator<Item> iterator()         // return an independent iterator over items in random order
    { return new ArrIterator(size); }

    public static void main(String[] args)   // unit testing
    {
    }
}
