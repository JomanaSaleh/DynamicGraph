class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;


        Node(T data) {
        this.data = data;
        this.next = null;
        this.prev=null;
        }

        Node() {
            this.data=null;
            this.next=null;
            this.prev=null;
        }

    public T getData() {return this.data;}

    public Node<T> getPrev(){ return this.prev;}

    public void setPrev(Node<T> prev){ this.prev=prev;}

    public Node<T> getNext() { return this.next;}

    public void setNext(Node<T> next) { this.next = next;}



}

class list<T> {
    Node<T> head;
    Node<T> tail;
    int size;

    list() {
        this.size=0;
        this.head=new Node<T>();
        this.tail=new Node<T>();
        this.head.setNext(this.tail);
        this.tail.setPrev(this.head);

    }

    public int getSize(){return this.size;}


    public Node<T> getFirst(){ return this.head.getNext();}

    public Node<T> getTail(){ return this.tail;}

    void insert1(T elem) {
        Node<T> element = new Node<T>(elem);
        insert(element);
    }


    void insert(Node<T> element){
        element.setPrev(this.tail.getPrev());
        element.setNext(this.tail);
        this.tail.getPrev().setNext(element);
        this.tail.setPrev(element);
        this.size++;
    }

    void insert_first(Node<T> element){
        element.setNext(this.head.getNext());
        element.setPrev(this.head);
        this.head.getNext().setPrev(element);
        this.head.setNext(element);
        this.size++;
    }
    T pop_first(){
        Node<T> first=head.getNext();
        T data =first.getData();
        removee(first);
        return data;
    }

    void insert_before1(T elem,Node<T> next){
        Node<T> element=new Node<T>(elem);
        insert_before(element,next);
    }
    void insert_before(Node<T> element,Node<T> next){
        element.setPrev(next.getPrev());
        element.setNext(next);
        next.setPrev(element);
        element.getPrev().setNext(element);
        this.size++;
    }
    void removee(Node<T> element){
        element.getPrev().setNext(element.getNext());
        element.getNext().setPrev(element.getPrev());
        element.setPrev(null);
        element.setNext(null);
        this.size--;
    }


}