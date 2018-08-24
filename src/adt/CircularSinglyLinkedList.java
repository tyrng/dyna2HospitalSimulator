package adt;

import adt.interfaces.CircularSinglyLinkedListInterface;

/*
 *  Java Program to Implement Circular Singly Linked List
 */
 /*  Class Node  */
 /* Class linkedList */
public class CircularSinglyLinkedList<T> implements CircularSinglyLinkedListInterface<T> {

    protected Node start;
    protected Node end;
    public int size;

    /* Constructor */
    public CircularSinglyLinkedList() {
        start = null;
        end = null;
        size = 0;
    }

    /* Function to check if list is empty */
    public boolean isEmpty() {
        return start == null;
    }

    /* Function to get size of the list */
    public int getSize() {
        return size;
    }

    /* Function to insert element at the begining */
    public void insertAtStart(T val) {
        Node nptr = new Node(val, null);
        nptr.setLink(start);
        if (start == null) {
            start = nptr;
            nptr.setLink(start);
            end = start;
        } else {
            end.setLink(nptr);
            start = nptr;
        }
        size++;
    }

    /* Function to insert element at end */
    public void insertAtEnd(T val) {
        Node nptr = new Node(val, null);
        nptr.setLink(start);
        if (start == null) {
            start = nptr;
            nptr.setLink(start);
            end = start;
        } else {
            end.setLink(nptr);
            end = nptr;
        }
        size++;
    }

    /* Function to insert element at position */
    public void insertAtPos(T val, int pos) {
        Node nptr = new Node(val, null);
        Node ptr = start;
        pos = pos - 1;
        for (int i = 1; i < size - 1; i++) {
            if (i == pos) {
                Node tmp = ptr.getLink();
                ptr.setLink(nptr);
                nptr.setLink(tmp);
                break;
            }
            ptr = ptr.getLink();
        }
        size++;
    }

    /* Function to delete element at position */
    public void deleteAtPos(int pos) {
        if (size == 1 && pos == 1) {
            start = null;
            end = null;
            size = 0;
            return;
        }
        if (pos == 1) {
            start = start.getLink();
            end.setLink(start);
            size--;
            return;
        }
        if (pos == size) {
            Node s = start;
            Node t = start;
            while (s != end) {
                t = s;
                s = s.getLink();
            }
            end = t;
            end.setLink(start);
            size--;
            return;
        }
        Node ptr = start;
        pos = pos - 1;
        for (int i = 1; i < size - 1; i++) {
            if (i == pos) {
                Node tmp = ptr.getLink();
                tmp = tmp.getLink();
                ptr.setLink(tmp);
                break;
            }
            ptr = ptr.getLink();
        }
        size--;
    }

    public T getEntry(int fromPosition) {
        T result = null;

        if (isEmpty()) {
            return null;
        } else if (fromPosition > size) {
            return null;
        }

        Node tmp = start;

        for (int i = 1; i <= size; i++) {
            if (i == fromPosition) {
                result = tmp.getData();
            }
            tmp = tmp.getLink();
        }

        return result;
    }

    /* Function to display contents */
    public void display() {
        System.out.print("\nCircular Singly Linked List = ");
        Node ptr = start;
        if (size == 0) {
            System.out.print("empty\n");
            return;
        }
        if (start.getLink() == start) {
            System.out.print(start.getData() + "->" + ptr.getData() + "\n");
            return;
        }
        System.out.print(start.getData() + "->");
        ptr = start.getLink();
        while (ptr.getLink() != start) {
            System.out.print(ptr.getData() + "->");
            ptr = ptr.getLink();
        }
        System.out.print(ptr.getData() + "->");
        ptr = ptr.getLink();
        System.out.print(ptr.getData() + "\n");
    }

    class Node {

        protected T data;
        protected Node link;

        /*  Constructor  */
        public Node() {
            link = null;
            data = null;
        }

        /*  Constructor  */
        public Node(T d, Node n) {
            data = d;
            link = n;
        }

        /*  Function to set link to next Node  */
        public void setLink(Node n) {
            link = n;
        }

        /*  Function to set data to current Node  */
        public void setData(T d) {
            data = d;
        }

        /*  Function to get link to next node  */
        public Node getLink() {
            return link;
        }

        /*  Function to get data from current Node  */
        public T getData() {
            return data;
        }
    }

}
