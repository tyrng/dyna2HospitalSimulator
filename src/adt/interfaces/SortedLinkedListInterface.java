package adt.interfaces;

public interface SortedLinkedListInterface<T extends Comparable<? super T>> {

    public boolean isEmpty();

    /*  Function to check size of list  */
    public int getSize();

    /*  Function to insert an element  */
    public void insert(T val);
    
    public T getEntry(int pos);

    /*  Function to delete an element at position  */
    public void deleteAtPos(int pos);

    /*  Function to display elements  */
    public void display();
}
