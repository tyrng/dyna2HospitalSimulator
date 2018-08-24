/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt.interfaces;

/**
 *
 * @author ongty
 */
public interface CircularSinglyLinkedListInterface<T> {
    
    /* Function to check if list is empty */
    public boolean isEmpty();
    /* Function to get size of the list */
    public int getSize();
    /* Function to insert element at the begining */
    public void insertAtStart(T val);
    /* Function to insert element at end */
    public void insertAtEnd(T val);
    /* Function to insert element at position */
    public void insertAtPos(T val , int pos);
    /* Function to delete element at position */
    public void deleteAtPos(int pos);
    /* Function to display contents */
    public void display();
    
}
