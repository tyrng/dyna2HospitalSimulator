/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt.interfaces;

public interface SortedArrayListInterface<T extends Comparable<? super T>> {
     /**
     * Appends the specified newEntry to the end of this list.
     *
     * @param newEntry newEntry to be appended to this list
     * @return true if newEntry is added
     */
    public boolean add(T newEntry);
     /**
     * Removes the entry in this list that matches the given entry.
     * Shifts any subsequent elements to the left 
     *
     * @param anEntry the selected anEntry to be removed
     * @return true is newEntry is removed
     */    
    public boolean remove(T anEntry);
     /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */ 
    public int getNumberOfEntries();
     /**
     * Returns the specified entry with a specified position in this list,
     * or -1 if there is no such index.
     * 
     * @return the entry with a specified position in the list
     */
    public T getEntry(int givenPosition);
    /**
     * Remove all the entries in the list.
     */
    public void clear();
     /**
     * Returns the size in this list.
     *
     * @return the size in this list
     */
    public int size();
     /**
     * Detects if the list is empty.
     *
     * @return true if the list is empty
     */
    public boolean isEmpty();
     /**
     * Detects if the list is full.
     *
     * @return true if the list is full
     */
    public boolean isFull();
}
