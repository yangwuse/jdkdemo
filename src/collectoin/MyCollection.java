package collectoin;

import java.util.Iterator;

/**
 * collection demo
 * @author yangWu
 * @date 2021/5/18 3:08 下午
 * @version 1.0
 */
public interface MyCollection<E> extends Iterable<E> {

    // Query operations

    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Iterator<E> iterator();

    Object[] toArray();

    // Modify operations

    boolean add(E e);

    boolean remove(Object o);


    // Bulk operations

    boolean containAll(MyCollection<?> c);

    boolean addAll(MyCollection<? extends E> c);

    boolean removeAll(MyCollection<?> c);

    void clear();

    // Comparison and hashing

    boolean equals(Object o);

    int hashCode();

}
