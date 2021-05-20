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
    private int head; // 头指针始终指向对头元素
    private int tail; // 尾指针始终指向下一个待插入元素的位置
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    public MyArrayDeque() {
        elem = new Object[5 + 1]; // 自定义放6个元素
    }
    public MyArrayDeque(int cap) {
        // 验证参数有效性
        cap = (cap < 1) ? 1 : (cap - MAX_ARRAY_SIZE > 0)
                ? MAX_ARRAY_SIZE : cap + 1;
        elem = new Object[cap];
    }
    public void addFirst(E e) {
        // 不能插入null
        Objects.requireNonNull(e);
        // 头指针从尾部向前移动 依次递减 具体查看notion模型
        head = dec(head, elem.length);
        elem[head] = e;
        // 如果头部和尾部相遇 扩容
        if (head == tail)
            grow(1);
    }
    public void addLast(E e) {
        Objects.requireNonNull(e);
        // 先在尾部插入元素 tail在向右移一位
        elem[tail] = e;
        tail = inc(tail, elem.length);
        if (tail == head)
            grow(1);
    }
    private int dec(int i, int mod) {
        // first循环递减
        if (--i < 0) i = mod - 1;
        return i;
    }
    private int inc(int i, int mod) {
        // tail循环递增
        if (++i >= mod) i = 0;
        return i;
    }
    private void grow(int needed) {
        final int oldCapacity = elem.length;
        // jump代表预扩容增量 避免当needed很小时频繁的扩容 而是进行一次大扩容
        // 如果原数组很小 扩容两倍 否则扩容50%
        int jump = (oldCapacity < 64) ? oldCapacity + 2 : (oldCapacity >> 1);
        // 先尝试计算扩容后的数组容量
        int newCapacity = oldCapacity + jump;
        // 如果预增量不够 或者新容量大于最大容量 重新分配容量
        if (jump < needed || newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = newCapacity(needed, jump);
        // 数组扩容
        elem = Arrays.copyOf(elem, newCapacity);
        // 如果头尾不想交 或者头尾相交且队列不为空
        if (tail < head || (tail == head && elem[head] != null)) {
            // 计算扩容量
            int newSpace = newCapacity - oldCapacity;
            // 将first到数组尾部部分整体移动到扩容后的数组尾部 *关键*
            System.arraycopy(elem, head, elem, head + newSpace
                , oldCapacity - head);
            // 将中间过期数据解引用 注意头指针指向新位置 *关键*
            for (int i = head, to = (head += newSpace); i < to; i++)
                elem[i] = null;
        }
    }
    private int newCapacity(int needed, int jump) {
        final int oldCapacity = elem.length;
        // 先计算目标容量
        int minCapacity = needed + oldCapacity;
        if (minCapacity - MAX_ARRAY_SIZE > 0) {
            // 如果目标容量超过整数最大值
            if (minCapacity < 0)
                throw new IllegalStateException("to large");
            return Integer.MAX_VALUE;
        }
        // 如果预扩容增量小于实际需要 返回目标容量
        if (needed > jump)
            return minCapacity;
        // 否则按照预扩容增量扩容 减少总扩容次数
        return (jump + oldCapacity - MAX_ARRAY_SIZE > 0)
            ? MAX_ARRAY_SIZE
            : jump + oldCapacity;
    }
    public E pollFirst() {
         // 首先获取对头元素
         E e = (E) elem[head];
         if (e != null) {
             // 对头出队 *关键*
             elem[head] = null;
             // 头指针向后移一位
             head = inc(head, elem.length);
         }
         // 底层函数可以返回null
         return e;
    }
    public E pollLast() {
        // 队尾指针先向前移动一位 指向队尾元素
         tail = dec(tail, elem.length);
         E e = (E) elem[tail];
         if (e != null)
             // 队尾不空 则出队
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
