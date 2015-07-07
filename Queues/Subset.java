import java.util.NoSuchElementException;

public class Subset {
    public static void main(String[] args)
    {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> q = new RandomizedQueue<String>();

        try {
            while (true) {
                String str;
                str = StdIn.readString();
                q.enqueue(str);
            }
        } catch (NoSuchElementException e) { }

        for (int i = 0; i < k; i++) {
            StdOut.println(q.dequeue());
        }
    }
};
