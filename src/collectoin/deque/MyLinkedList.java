package collectoin.deque;

/**
 * LinkedList demo 实现了双端队列 比ArrayDeque简单许多
 * @author yangWu
 * @date 2021/5/20 7:58 下午
 * @version 1.0
 */

import java.util.Iterator;

/**
 * 实现如下接口
 * 入队 linkFirst linkLast
 * 出队 unlinkFirst unlinkLast
 * 获取 getFirst getLast
 */
public class MyLinkedList<E> {
    int size; // 节点个数
    Node<E> first; // 头结点 在右 与ArrayDeque相反
    Node<E> last;  // 尾节点 在左 与ArrayDeque相反
    public MyLinkedList() {}

    // 队首入队
    public void linkFirst(E e) {
        Node<E> newNode = new Node<>(null, e, first);
        // 头结点为空 尾节点和头结点同时指向该节点
        if (first == null) last = newNode;
        else first.prev = newNode;

        first = newNode;
        size++;
    }
    // 队尾入队
    public void linkLast(E e) {
        Node<E> newNode = new Node<>(last, e, null);
        // 若尾节点为空 则该节点为第一个节点
        if (last == null) first = newNode;
        else last.next = newNode;

        last = newNode;
        size++;
    }
    // 出队首
    public E unlinkFirst() {
        if (size == 0) throw new IllegalStateException("deque is empty");
        E e = first.val;
        Node<E> next = first.next;
        // 头结点断链
        first.val = null; // *重点* 帮助GC
        first.next = null; // *重点* 帮助GC
        first = next;
        // 此时队列为空
        if (next == null)
            last = null;
        else first.prev = null;
        size--;
        return e;
    }
    // 出队尾
    public E unlinkLast() {
        if (size == 0) throw new IllegalStateException("deque is empty");
        E e = last.val;
        Node<E> prev = last.prev;
        // 尾节点断链
        last.val = null;
        last.prev = null;
        last = prev;
        if (last == null) first = null;
        else last.next = null;
        size--;
        return e;
    }
    // 取队首
    public E getFrist() {
        if (size == 0) throw new IllegalStateException("deque is empty");
        return first.val;
    }
    // 取队尾
    public E getLast() {
        if (size == 0) throw new IllegalStateException("deque is empty");
        return last.val;
    }

    // 正序打印
    public Iterator<E> iterator() {
        // 使用匿名内部类创建迭代器对象
         return new Iterator<E>() {
             Node<E> f = first;
             Node<E> l = last;
             @Override public boolean hasNext() {
                 // 注意是f 不是first
                 return f != null;
             }

             @Override public E next() {
                 E e = f.val;
                 f = f.next; // 注意是f 不是first
                 return e;
             }
         };
    }

    // 打印辅助函数
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<E> it = iterator();
        while (it.hasNext())
            sb.append(it.next()).append(" ");
        // 显示空队列为null
        return sb.length() == 0 ? "null" : sb.toString();
    }

    // 节点类型 private static 静态私有类 *重点*
    private static class Node<E> {
        E val;
        Node<E> next;
        Node<E> prev;

        // Node不需要访问修饰符 prev在前符合逻辑
        Node(Node<E> prev, E val, Node<E> next) {
            this.prev = prev;
            this.val = val;
            this.next = next;
        }
    }

    // 测试
    public static void main(String[] args) {
        MyLinkedList<Integer> queue = new MyLinkedList<>();
        queue.linkFirst(1);
        queue.linkFirst(2);
        queue.linkFirst(3);

        queue.linkLast(4);
        queue.linkLast(5);
        queue.linkLast(6);
        System.out.println(queue);


        queue.unlinkFirst();
        queue.unlinkFirst();

        queue.unlinkLast();
        queue.unlinkLast();
        System.out.println(queue);

        queue.unlinkFirst();
        queue.unlinkFirst();
        System.out.println(queue);

//        queue.unlinkFirst(); // 边界测试
    }
}

