package collectoin;

/**
 * Deque demo
 * @author yangWu
 * @date 2021/5/18 3:26 下午
 * @version 1.0
 */
public interface MyDeque<E> extends MyQueue<E> {
  // add
  void addFirst(E e);

  void addLast(E e);

  boolean offerFirst(E e);

  boolean offerLast(E e);

  // remove
  E removeFirst();

  E removeLast();

  E pollFirst();

  E pollLast();

  // get
  E getFirst();

  E getLast();

  E peekFirst();

  E peekLast();

  // stack
  void push(E e);

  void pop();









}
