package collectoin.deque;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author yangWu
 * @date 2021/5/18 9:18 下午
 * @version 1.0
 */
public class Test {
    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<>(5);
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addLast(10);
        deque.addLast(20);
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
        deque.pollFirst();
//        int[] a = new int[]{0, 0, 0, 1, 2, 3};
//        a = Arrays.copyOf(a, 10);
//        System.arraycopy(a, 3, a, 7, 3);
//        System.out.println();
    }
}
