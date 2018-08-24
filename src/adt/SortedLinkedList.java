package adt;

import adt.interfaces.SortedLinkedListInterface;

public class SortedLinkedList<T extends Comparable<? super T>> implements SortedLinkedListInterface<T> {

    protected Node start;
    public int size;

    public SortedLinkedList() {
        start = null;
        size = 0;
    }

    /*  Function to check if list is empty  */
    public boolean isEmpty() {
        return start == null;
    }

    /*  Function to check size of list  */
    public int getSize() {
        return size;
    }

    /*  Function to insert an element  */
    public void insert(T val) {
        Node nptr, ptr, tmp = null;
        nptr = new Node(val, null);
        boolean ins = false;
        if (start == null) {
            start = nptr;
        } else if (val.compareTo(start.getData()) <= 0) {
            nptr.setLink(start);
            start = nptr;
        } else {
            tmp = start;
            ptr = start.getLink();
            while (ptr != null) {
                if (val.compareTo(tmp.getData()) >= 0 && val.compareTo(ptr.getData()) <= 0) {
                    tmp.setLink(nptr);
                    nptr.setLink(ptr);
                    ins = true;
                    break;
                } else {
                    tmp = ptr;
                    ptr = ptr.getLink();
                }
            }
            if (ins == false) {
                tmp.setLink(nptr);
            }
        }
        size++;
    }

    public T getEntry(int pos) {
        T result = null;

        if ((pos >= 1) && (pos <= size)) {
            Node currentNode = start;
            for (int i = 0; i < pos - 1; ++i) {
                currentNode = currentNode.getLink();		// advance currentNode to next node
            }
            result = currentNode.data;	// currentNode is pointing to the node at givenPosition
        }

        return result;
    }

    /*  Function to delete an element at position  */
    @Override
    public void deleteAtPos(int pos) {
        if (pos == 1) 
        {
            start = start.getLink();
            size--; 
            return ;
        }
        if (pos == size) 
        {
            Node ptr = start;
 
            for (int i = 1; i < size - 1; i++)
                ptr = ptr.getLink();
            ptr.setLink(null);            
            size --;
            return;
        }
        Node ptr = start;
        pos = pos - 1 ;
        for (int i = 1; i < size - 1; i++) 
        {
            if (i == pos) 
            {
                Node tmp = ptr.getLink();
                tmp = tmp.getLink();
                ptr.setLink(tmp);
                break;
            }
            ptr = ptr.getLink();
        }
        size-- ;
    }

    /*  Function to display elements  */
    public void display() {
        System.out.print("Sorted Singly Linked List = ");
        if (size == 0) {
            System.out.print("empty\n");
            return;
        }
        if (start.getLink() == null) {
            System.out.println(start.getData());
            return;
        }
        Node ptr = start;
        System.out.print(start.getData() + "->");
        ptr = start.getLink();
        while (ptr.getLink() != null) {
            System.out.print(ptr.getData() + "->");
            ptr = ptr.getLink();
        }
        System.out.print(ptr.getData() + "\n");
    }

    private class Node {

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
