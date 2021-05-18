package collectoin;

/**
 * queue demo
 * @author yangWu
 * @date 2021/5/18 3:20 下午
 * @version 1.0
 */
public interface MyQueue<E> extends MyCollection<E> {

  boolean add(E e);

  boolean offer(E e);

  E remove();

  E poll();

  E element();

  E peek();

}
