import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * CompletedList represents an implementation of a list.
 *
 * @author Maria Hoyos-Grijalva, Acuna
 * @version IntelliJ
 */
public class CompletedList<T> implements ListADT<T>, Iterable<T> {

    protected int count;
    protected int modChange;
    protected DoubleLinearNode<T> head, tail;

    public CompletedList(){
        head = tail = null;
        count = 0;
    }

    @Override
    public T removeFirst() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        else {
            T currentElement = head.getNode();
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.getNext();
                head.getPrev().remove();
            }
            count--;
            modChange++;
            return currentElement;
        }
    }

    @Override
    public T removeLast() throws NoSuchElementException {
        if(isEmpty())
            throw new NoSuchElementException();

        else {
            DoubleLinearNode<T> currentElement = tail;
            DoubleLinearNode<T> previousElement = tail;
            for(int i = 0; i< count - 1; i++){
                previousElement = currentElement;
                currentElement = currentElement.getNext();
            }
            previousElement.getNext();
            count--;
            return currentElement.getNode();
        }
    }

    @Override
    public T remove(T element) {
        if (tail.getNode().equals(element)) {
            return removeLast();
        }
        DoubleLinearNode<T> current;
        for(current = head; current != null; current.getNext()){
            if(current.getNode().equals(element)){
                    return removeFirst();
                }else{
                    count--;
                    modChange++;
                    return current.remove();
                }
            }
        throw new NoSuchElementException();
    }

    @Override
    public T first() {
        if(isEmpty()) throw new NoSuchElementException();
        return head.getNode();
    }

    @Override
    public T last() {
        if(isEmpty()) throw new NoSuchElementException();
        return tail.getNode();
    }

    @Override
    public boolean contains(T target) {
        DoubleLinearNode<T> current;
        for(current = head; current != null; current = current.getNext()){
            if(current.getNode().equals(target)){
                    return true;
                }
            }
        return false;
    }

    @Override
    public boolean isEmpty() {
        if(head == null || tail == null) return true;
        return false;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator(head);
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        DoubleLinearNode<T> element = head;

        while(element != null){
            str.append(element.getNode()).append(" ");
            element = element.getNext();
        }
        return str.toString();
    }

    private class ListIterator implements Iterator<T>{
        private int newModChange;
        private DoubleLinearNode<T> currentElement;

        public ListIterator(DoubleLinearNode<T> currentElement) {
            this.newModChange = CompletedList.this.modChange;
            this.currentElement = currentElement;
        }

        @Override
        public void remove() {
            if(currentElement == head){
                newModChange++;
                removeFirst();
            }else if(currentElement == tail){
                newModChange++;
                removeLast();
               }else{
                newModChange++;
                modChange++;
                currentElement = currentElement.getNext();
                currentElement.getPrev().remove();
           }
        }

        @Override
        public boolean hasNext() {
            if(isEmpty()) throw new NoSuchElementException();

            return this.currentElement.getNext() != null;
            }

        @Override
        public T next() {
            this.currentElement = this.currentElement.getNext();
            return this.currentElement.getNode();
        }
    }
}
