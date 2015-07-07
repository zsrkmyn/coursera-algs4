import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        public Item val;
        public Node next;
        public Node prev;
    }

    private class ListIterator implements Iterator<Item> {
        private Node cur;

        public ListIterator() {
            cur = head;
        }

        public boolean hasNext()
        { return cur != null; }

        public void remove()
        { throw new UnsupportedOperationException(); }

        public Item next()
        {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item ret = cur.val;
            cur = cur.next;
            return ret;
        }

    }

    private Node head;
    private Node tail;
    private int size;

    public Deque()                           // construct an empty deque
    {
        head = null;
        tail = head;
        size = 0;
    }

    public boolean isEmpty()                 // is the deque empty?
    { return size == 0; }

    public int size()                        // return the number of items on the deque
    { return size; }

    public void addFirst(Item item)          // add the item to the front
    {
        if (item == null) {
            throw new NullPointerException();
        }

        Node n = new Node();
        n.val = item;

        if (isEmpty()) {
            head = n;
            tail = head;
        } else {
            head.prev = n;
            n.next = head;
            head = n;
        }

        size++;
    }

    public void addLast(Item item)           // add the item to the end
    {
        if (item == null) {
            throw new NullPointerException();
        }
        Node n = new Node();
        n.val = item;

        if (isEmpty()) {
            head = n;
            tail = head;
        } else {
            n.prev = tail;
            tail.next = n;
            tail = n;
        }
        size++;
    }

    public Item removeFirst()                // remove and return the item from the front
    {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item ret = head.val;
        head.val = null;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = head;
        }
        size--;
        return ret;
    }

    public Item removeLast()                 // remove and return the item from the end
    {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item ret = tail.val;
        tail.val = null;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            head = tail;
        }
        size--;
        return ret;
    }

    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    { return new ListIterator(); }

    public static void main(String[] args)   // unit testing
    {
    }
}
