public class DoubleLinearNode<Element> {

    private Element node;
    private DoubleLinearNode<Element> next, prev;

    public DoubleLinearNode(Element node) {
        this.node = node;
        this.next = this.prev = null;
    }

    public Element getNode() {
        return node;
    }

    public DoubleLinearNode<Element> getNext() {
        return next;
    }

    public DoubleLinearNode<Element> getPrev() {
        return prev;
    }

    public void setNext(DoubleLinearNode<Element> next) {
        this.next = next;
        if(next != null) next.prev = this.next;
        if(this.next != null) {
            this.next.prev = next;
            next.next = this.next;
        }
    }

    public void setPrev(DoubleLinearNode<Element> prev) {
        this.prev = prev;
        if(prev != null) prev.next = this.prev;
        if(this.prev != null) {
            this.prev.next = prev;
            prev.prev = this.prev;
        }
    }

    public Element remove(){
        if(this.next != null) this.next.prev = this.prev;
        if(this.prev != null) this.prev.next = this.next;
        return this.node;
    }
}
