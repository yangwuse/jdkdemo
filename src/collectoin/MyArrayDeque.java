package collectoin;

import java.rmi.dgc.VMID;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * ArrayDeque demo
 * @author yangWu
 * @date 2021/5/18 3:43 下午
 * @version 1.0
 */
public class MyArrayDeque<E> {
    private Object[] elem;
    private int head;
    private int tail;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    public MyArrayDeque() {
        elem = new Object[5 + 1];
    }
    public MyArrayDeque(int cap) {
        cap = (cap < 1)
            ? 1
            : (cap - MAX_ARRAY_SIZE > 0)
                ? MAX_ARRAY_SIZE
                : cap;
        elem = new Object[cap];
    }
    public void addFirst(E e) {
        Objects.requireNonNull(e);
        head = dec(head, elem.length);
        elem[head] = e;
        if (head == tail)
            grow(1);
    }
    public void addLast(E e) {
        Objects.requireNonNull(e);
        elem[tail] = e;
        tail = inc(tail, elem.length);
        if (tail == head)
            grow(1);
    }
    private int dec(int i, int mod) {
        if (--i < 0) i = mod - 1;
        return i;
    }
    private int inc(int i, int mod) {
        if (++i >= mod) i = 0;
        return i;
    }
    private void grow(int needed) {
        final int oldCapacity = elem.length;
        int jump = (oldCapacity < 64) ? oldCapacity + 2 : (oldCapacity >> 1);
        int newCapacity = oldCapacity + jump;
        if (jump < needed || newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = newCapacity(needed, jump);
        elem = Arrays.copyOf(elem, newCapacity);
        if (tail < head || (tail == head && elem[head] != null)) {
            int newSpace = newCapacity - oldCapacity;
            System.arraycopy(elem, head, elem, head + newSpace
                , oldCapacity - head);
            for (int i = head, to = (head += newSpace); i < to; i++)
                elem[i] = null;
        }
    }
    private int newCapacity(int needed, int jump) {
        final int oldCapacity = elem.length;
        int minCapacity = needed + oldCapacity;
        if (minCapacity - MAX_ARRAY_SIZE > 0) {
            if (minCapacity < 0)
                throw new IllegalStateException("to large");
            return Integer.MAX_VALUE;
        }
        if (needed > jump)
            return minCapacity;
        return (jump + oldCapacity - MAX_ARRAY_SIZE > 0)
            ? MAX_ARRAY_SIZE
            : jump + oldCapacity;
    }
    public E pollFirst() {
         E e = (E) elem[head];
         if (e != null) {
             elem[head] = null;
             head = inc(head, elem.length);
         }
         return e;
    }
    public E pollLast() {
         tail = dec(tail, elem.length);
         E e = (E) elem[tail];
         if (e != null)
             elem[tail] = null;
         return e;
    }
    public E getFirst() {
        return (E) elem[head];
    }
    public E getLast() {
        tail = dec(tail, elem.length);
        return (E) elem[tail];
    }

    public static void main(String[] args) {
        MyArrayDeque<Integer> deque = new MyArrayDeque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);
        deque.addLast(7);

        deque.pollFirst();
        deque.pollFirst();

        deque.pollLast();
        deque.pollLast();
        deque.pollLast();
        deque.pollLast();
        deque.pollLast();
        deque.pollLast();


    }
}
