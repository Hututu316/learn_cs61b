
import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    public class Node{
        Node prev;
        T item;
        Node next;
        Node(Node p,T i, Node n){
            prev = p;
            item = i;
            next = n;
        }
    }

    final private Node sentinel;
    private int size;


    public LinkedListDeque61B(){
        sentinel = new Node(null,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque61B(T x){
        this();
        addFirst(x);
    }
    /**
     * Add {@code x} to the front of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        Node newNode = new Node(sentinel,x,sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size ++;
    }

    /**
     * Add {@code x} to the back of the deque. Assumes {@code x} is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        Node newNode = new Node(sentinel.prev,x,sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size ++;
    }

    /**
     * Returns a List copy of the deque. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */
    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();

        Node p = sentinel.next;
        while (p != sentinel){
            returnList.add(p.item);
            p = p.next;
        }
        return returnList;
    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst() {
        if (sentinel.next == sentinel){
            return null;
        }

        Node first = sentinel.next;
        Node second = first.next;

        sentinel.next = second;
        second.prev = sentinel;

        size--;
        return first.item;
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        if (sentinel.prev == sentinel){
            return null;
        }
        Node last = sentinel.prev;

        sentinel.prev = last.prev;
        last.prev.next = sentinel;

        size--;
        return last.item;
    }

    /**
     * The Deque61B abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T get(int index) {
        Node p = sentinel.next;
        int i;
        for(i = 0; i < index; i++){
            p = p.next;
        }

        return p.item;
    }

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node node, int index) {
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(node.next, index - 1);
    }
}
