/**
 * CompletedOrderedList represents an implementation of an ordered list that builds on
 * CompletedList.
 *
 * @author Maria Hoyos-Grijalva, Acuna
 * @version IntelliJ
 */
public class CompletedOrderedList<T extends Comparable<T>> extends CompletedList<T>
        implements OrderedListADT<T> {

    public CompletedOrderedList(){
        head = tail = null;
        count = 0;
    }

    @Override
    public T first() {
        return null;
    }

    @Override
    public void add(T element) throws NullPointerException {
        if (element == null) throw new NullPointerException();

        if (isEmpty()) {
            head = new DoubleLinearNode<>(element);
            tail = head;
            return;
        } else {
            DoubleLinearNode<T> first = head;
            DoubleLinearNode<T> last = tail;
            DoubleLinearNode<T> newElement = new DoubleLinearNode<>(element);

            if (element.compareTo(first.getNode()) <= 0) {
                newElement.setNext(first);
                head = newElement;
            } else if (element.compareTo(last.getNode()) >= 0) {
                newElement.setPrev(last);
                tail = newElement;
            }
            while ((first = first.getNext()) != (last = last.getPrev())) {
                if (element.compareTo(first.getNode()) <= 0) {
                    first.setPrev(newElement);
                } else if (element.compareTo(last.getNode()) >= 0) {
                    last.setNext(newElement);
                }
            }
            switch (element.compareTo(first.getNode())) {
                case -1:
                    first.getPrev().setNext(newElement);
                    break;
                case 0:
                    first.setPrev(newElement);
                    break;
                case 1:
                    first.getNext().setPrev(newElement);
                    break;
            }
        }
    }

    @Override
    public boolean contains(T target) {
        DoubleLinearNode<T> first = head;
        DoubleLinearNode<T> last = tail;
        while((first = first.getNext()) != (last = last.getPrev())){
            if(target.compareTo(first.getNode()) == 0 || target.compareTo(last.getNode()) == 0){
                return true;
            }
        }
        return false;
    }
}
