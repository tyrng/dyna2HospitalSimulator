package adt;

import adt.interfaces.PriorityCircularLinkedQueueInterface;

public class PriorityCircularLinkedQueue<T extends Comparable<? super T>> implements PriorityCircularLinkedQueueInterface<T> {

    private Node firstNode, lastNode;
    private int length;

    public PriorityCircularLinkedQueue() {
        firstNode = null;
        lastNode = null;
        length = 0;
    }

    @Override
    public boolean queue(T newEntry) {

        Node nptr = new Node(newEntry, null, null);
        Node tmp, ptr;
        boolean ins = false;
        if (firstNode == null) {
            nptr.setLinkNext(nptr);
            nptr.setLinkPrev(nptr);
            firstNode = nptr;
            lastNode = nptr;
        } else if (newEntry.compareTo(firstNode.getData()) <= 0) {
            nptr.setLinkPrev(lastNode);
            lastNode.setLinkNext(nptr);
            firstNode.setLinkPrev(nptr);
            nptr.setLinkNext(firstNode);
            firstNode = nptr;
        } else if (newEntry.compareTo(lastNode.getData()) >= 0) {
            lastNode.setLinkNext(nptr);
            nptr.setLinkPrev(lastNode);
            nptr.setLinkNext(firstNode);
            firstNode.setLinkPrev(nptr);
            lastNode = nptr;
        } else {
            tmp = firstNode;
            ptr = firstNode.getLinkNext();
            while (ptr != null) {
                if (newEntry.compareTo(tmp.getData()) >= 0 && newEntry.compareTo(ptr.getData()) <= 0) {
                    tmp.setLinkNext(nptr);
                    nptr.setLinkPrev(tmp);
                    nptr.setLinkNext(ptr);
                    ptr.setLinkPrev(nptr);
                    ins = true;
                    break;
                } else {
                    tmp = ptr;
                    ptr = ptr.getLinkNext();
                }
            }
            if (!ins) {
                tmp.setLinkNext(nptr);
                nptr.setLinkPrev(tmp);
            }
        }
        length++;

        return true;
    }

    @Override
    public T dequeue() {
        if (length == 1) {
            T tmp = firstNode.data;
            firstNode = null;
            lastNode = null;
            length = 0;
            return tmp;
        }
        T tmp = firstNode.data;
        firstNode = firstNode.getLinkNext();
        firstNode.setLinkPrev(lastNode);
        lastNode.setLinkNext(firstNode);
        length--;
        return tmp;
    }

    public int getPosition(T anEntry) {
        if (length == 0) {
            return -1;
        }
        Node tmp = firstNode;
        for (int i = 0; i < length; i++) {
            if (tmp == anEntry) {
                return i + 1;
            }
            tmp = tmp.getLinkNext();
        }
        return -1;
    }

    @Override
    public T getEntry(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= length)) {
            Node currentNode = firstNode;
            for (int i = 0; i < givenPosition - 1; ++i) {
                currentNode = currentNode.next;		// advance currentNode to next node
            }
            result = currentNode.data;	// currentNode is pointing to the node at givenPosition
        }

        return result;
    }

    public boolean contains(T anEntry) {

        boolean found = false;
        Node tempNode = firstNode;
        int pos = 1;

        while (!found && (tempNode != null)) {
            if (anEntry.compareTo(tempNode.data) <= 0) {
                found = true;
            } else {
                tempNode = tempNode.next;
                pos++;
            }
        }
        System.out.println("\n***TRACE: tempNode.data is " + tempNode.data + " " + pos);
        if (tempNode != null && tempNode.data.equals(anEntry)) {
            return true;
        } else {
            return false;
        }
    }

//    public T remove(int givenPosition) {
//        T result = null;
//
//        if ((givenPosition >= 1) && (givenPosition <= length)) {
//            if (givenPosition == 1) {      	// CASE 1: remove first entry
//                result = firstNode.data;     	// save entry to be removed 
//                firstNode = firstNode.next;		// update firstNode to point to the next node
//            } else {                         	// CASE 2: remove interior entry or last entry
//                Node nodeBefore = firstNode;
//                for (int i = 1; i < givenPosition - 1; ++i) {
//                    nodeBefore = nodeBefore.next;		// advance nodeBefore to its next node
//                }
//                result = nodeBefore.next.data;  	// save entry to be removed	
//                nodeBefore.next = nodeBefore.next.next;	// make node before point to node after the 
//            } 															// one to be deleted (to disconnect node from chain)
//
//            length--;
//        }
//
//        return result;
//    }
    public final void clear() {
        firstNode = null;
        lastNode = null;
        length = 0;
    }

    public int getLength() {
        return length;
    }

    public boolean isEmpty() {
        return (firstNode == null) && (lastNode == null) && (length == 0);
    }

    public boolean isFull() {
        return false;
    }

    public String toString() {
        String outputStr = "";
        Node currentNode = firstNode;
        for (int n = 0; n < length; n++) {
            outputStr += currentNode.data + "\n";
            currentNode = currentNode.next;
        }
        return outputStr;
    }

    private class Node {

        private T data;
        private Node next, prev;

        Node() {
            next = null;
            prev = null;
            data = null;
        }

        Node(T dataPortion) {
            data = dataPortion;
            next = null;
            prev = null;
        }

        Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
            prev = null;
        }

        Node(T dataPortion, Node nextNode, Node prevNode) {
            data = dataPortion;
            next = nextNode;
            prev = prevNode;
        }

        void setLinkNext(Node n) {
            next = n;
        }

        void setLinkPrev(Node p) {
            prev = p;
        }

        Node getLinkNext() {
            return next;
        }

        Node getLinkPrev() {
            return prev;
        }

        void setData(T d) {
            data = d;
        }

        public T getData() {
            return data;
        }

    }
}
