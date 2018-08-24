/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt;

import adt.interfaces.ListInterface;
import java.io.Serializable;
import java.util.NoSuchElementException;

public class LinearDoublyLinkedList<T> implements ListInterface<T>, Serializable{

    private Node firstNode;
    private Node lastNode;
    private int count;

    public LinearDoublyLinkedList() {
        firstNode = null;
        lastNode = null;
        count = 0;
    }

    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);

        if (!isEmpty()) {
            newNode.prev = lastNode;
            lastNode.next = newNode;
        } else {
            firstNode = newNode;
        }

        lastNode = newNode;
        count++;
        return true;
    }

    @Override
    public boolean add(int positionNode, T newEntry) {
        Node tempNode;

        if (positionNode > count || positionNode < 0) {
            throw new NoSuchElementException("Please re-input. :)");
        }

        Node newNode = new Node(newEntry);
        if (!isEmpty()) {

            tempNode = firstNode;
            int i = positionNode;
            while (i != 0) {
                tempNode = tempNode.next;
                i--;
            }

            if (positionNode == 0) {
                tempNode = firstNode;

                firstNode = newNode;
                newNode.prev = firstNode;
                newNode.next = tempNode;
                tempNode.prev = newNode;

            } else if (positionNode == count) {
                tempNode = lastNode;

                lastNode = newNode;
                newNode.next = lastNode;
                newNode.prev = tempNode;
                tempNode.next = newNode;
            } else {
                newNode.prev = tempNode.prev;
                newNode.next = tempNode;

                tempNode.prev.next = newNode;
                tempNode.prev = newNode;
            }
        } else {
            firstNode = newNode;
            lastNode = newNode;
        }
        count++;
        return true;
    }

    @Override
    public T remove(int pos) {
        if (pos == 1) {
            if (count == 1) {
                T tmp = firstNode.data;
                firstNode = null;
                lastNode = null;
                count = 0;
                return tmp;
            }
            T tmp = firstNode.data;
            firstNode = firstNode.next;
            firstNode.prev = lastNode;
            lastNode.next = firstNode;
            count--;
            return tmp;
        }
        if (pos == count) {
            T tmp = firstNode.data;
            lastNode = lastNode.prev;
            lastNode.next = firstNode;
            firstNode.prev = lastNode;
            count--;
        }
        Node ptr = firstNode.next;
        for (int i = 2; i <= count; i++) {
            if (i == pos) {
                T tmp = firstNode.data;
                Node p = ptr.prev;
                Node n = ptr.next;

                p.next = n;
                n.prev = p;
                count--;
                return tmp;
            }
            ptr = ptr.next;
        }
        return null;
    }

    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
        count = 0;
    }

    @Override
    public boolean replace(int positionNode, T newEntry) {
        return true;
    }

    @Override
    public T getEntry(int positionNode) {
        T result = null;

        if ((positionNode >= 1) && (positionNode <= count)) {
          Node currentNode = firstNode;
          for (int i = 0; i < positionNode - 1; ++i) {
            currentNode = currentNode.next;		// advance currentNode to next node
          }
          result = currentNode.data;	// currentNode is pointing to the node at givenPosition
        }

        return result;
    }
    @Override
    public int getNumberOfEntries() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0 && firstNode == null && lastNode == null;
    }

    @Override
    public String toString() {
        Node tempNode;
        String str = "";

        if (count != 0) {
            tempNode = firstNode;

            for (int i = 0; i < count; i++) {

                str += tempNode.data;
                str += "\n";

                tempNode = tempNode.next;
            }

            str += "\b\b\b\b\b\b\b"; //remove the arrow output
        }

        return str;
    }

    @Override
    public boolean addToLast(T newEntry) {
        Node newNode = new Node(newEntry);

        if (!isEmpty()) {
            lastNode.next = newNode;
            newNode.prev = lastNode;

            lastNode = newNode;
            newNode.next = lastNode;
            count++;
        } else {
            add(newEntry);
        }
        return true;
    }

    // inner class node
    class Node implements Serializable{

        private Node next;
        private Node prev;
        private T data;

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(T data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
}
