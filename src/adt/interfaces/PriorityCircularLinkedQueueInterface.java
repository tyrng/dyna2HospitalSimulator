package adt.interfaces;

public interface PriorityCircularLinkedQueueInterface<T extends Comparable<? super T>> {

  public boolean queue(T newEntry);
  
  public T dequeue();

  public int getPosition(T anEntry);

  public T getEntry(int givenPosition);

  public boolean contains(T anEntry);

  public void clear();

  public int getLength();

  public boolean isEmpty();

  public boolean isFull();
}
