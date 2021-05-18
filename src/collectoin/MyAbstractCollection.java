package collectoin;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * AbstractCollection demo
 * @author yangWu
 * @date 2021/5/18 3:50 下午
 * @version 1.0
 */
public abstract class MyAbstractCollection<E> implements MyCollection<E> {

    // Called by subclasses
    protected MyAbstractCollection() {
    }

    // Query Operations

    public abstract Iterator<E> iterator();

    public abstract int size();

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Object o) {
        Iterator<E> it = iterator();
        if (o == null) {
            while (it.hasNext())
                if (it.next() == null)
                    return true;
        } else {
            while (it.hasNext())
                if (o.equals(it.next()))
                    return true;
        }
        return false;
    }

    public Object[] toArray() {
        Object[] r = new Object[size()];
        Iterator<E> it = iterator();
        for (int i = 0; i < r.length; i++) {
            if (! it.hasNext())
                return Arrays.copyOf(r, i);
            r[i] = it.next();
        }
        return it.hasNext() ? finishToArray(r, it) : r;
    }

    // require internal jdk
    private static <T> T[] finishToArray(T[] r, Iterator<?> it) {
        return null;
    }

    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    public boolean remove(Object o) {
        Iterator<E> it = iterator();
        if (o == null) {
            while (it.hasNext())
                if (it.next() == null) {
                    it.remove();
                    return true;
                }
        } else {
            while (it.hasNext())
                if (o.equals(it.next())) {
                    it.remove();
                    return true;
                }
        }
        return false;
    }

    // Bulk Operation

    public boolean containAll(MyCollection<?> c) {
        for (Object o : c)
            if (! contains(o))
                return false;
        return true;
    }

    public boolean addAll(MyCollection<? extends E> c) {
        boolean modified = false;
        for (E e : c)
            if (add(e))
                modified = true;
        return modified;
    }

    public boolean removeAll(MyCollection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;
        Iterator<?> it = iterator();
        while (it.hasNext()) {
            if (c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    public boolean retainAll(MyCollection<?> c) {
        Objects.requireNonNull(c);
        boolean modified = false;
        Iterator<?> it = iterator();
        while (it.hasNext()) {
            if (! c.contains(it.next())) {
                it.remove();
                modified = true;
            }
        }
        return modified;
    }

    public void clear() {
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            it.next();  // must prior to remove
            it.remove();
        }

    }

    public String toString() {
        Iterator<E> it = iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        while (it.hasNext()) {
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            sb.append(", ");
        }
        return sb.append(']').toString();
    }

}
