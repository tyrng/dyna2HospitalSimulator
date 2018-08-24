
package adt;

import adt.interfaces.SortedArrayListInterface;

public class SortedArrayList<T extends Comparable<T>> 
        implements SortedArrayListInterface<T>{
    private T[] listArray;
    private int numberOfEntries;
    
    public SortedArrayList(){
        listArray = (T[]) new Comparable[10];
    }

    @Override
    public boolean add(T newEntry) {
        System.out.println("add");
        if(numberOfEntries == listArray.length)
            doubleArray();
        
        int position = 0;
        while(position < numberOfEntries &&
                listArray[position].compareTo(newEntry) > 0){
            position++;
        }
        makeRoom(position);
        listArray[position] = newEntry;
        numberOfEntries++;
        return position < numberOfEntries;
    }
    
    public void makeRoom(int index){
        for(int i = 0; i < numberOfEntries; i++){
            listArray[i] = listArray[i-1];
        }
    }
    
    private void doubleArray(){
        T[] tempArray = listArray;
        listArray = (T[]) new Comparable[2*listArray.length];
        System.arraycopy(tempArray, 0, listArray, 0, listArray.length);
    }
    
    @Override
    public boolean remove(T anEntry) {
        System.out.println("remove");
        int oldSize = numberOfEntries;
        
        if(!isEmpty()){
            for(int i = 0; i < listArray.length; i++){
                if(listArray[i].equals(anEntry)){
                    for (int j = i; j < listArray.length; j++)
                        listArray[j] = listArray[j+1];
                    numberOfEntries--;  
                    break;
                }
            }
        }
        return numberOfEntries < oldSize;
    }
    
    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public T getEntry(int givenPosition) {
        return listArray[numberOfEntries];
    }

    @Override
    public void clear() {
        int length = listArray.length;
        for(int i = 0; i < length; i++){
            listArray[i] = null;
        }
        numberOfEntries = -1;
    }

    @Override
    public int size() {
        return listArray.length;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries < 0;
    }

    @Override
    public boolean isFull() {
        return numberOfEntries == listArray.length;
    }
}