import java.util.*;
import java.io.*;

public class StringQueueImpl<T> implements StringQueue<T> {
    private Node<T> front; //the front of the queue (in front of all the other nodes, oldest item)
    private Node<T> rear; //the rear of the queue (behind all of the other nodes, newest item)
    private int size = 0;

    public boolean isEmpty(){
        return size==0; //true if empty
    }

    public void put(T item){
        Node<T> newNode = new Node<>(item);
        if (isEmpty()){
            front = newNode; //if empty then both the rear and the front of the queue is the newNode
            rear = newNode;
        }
        else{
            rear.setNext(newNode); //else, the current rear sets the newNode to be next in queue
            rear = newNode; //the newNode is the new rear
        }
        size+=1;
    }

    public T get() throws NoSuchElementException{
        T data;
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        else if(size==1){
            data = front.getData(); //get the data from the front of the queue
            front = null; //the queue is now empty
            rear = null;
        }
        else{
            data = front.getData(); //get the data from the front of the queue
            front = front.getNext(); //the new front, is the node next in queue
        }
        size-=1;
        return data;

    }

    public T peek() throws NoSuchElementException{
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return front.getData(); //get the data from the front of the queue
    }
    
    public void printQueue(PrintStream stream){
        Node<T> runner = front; //a runner that will go through the queue, without changing the front or the rear (starting from the front of the queue)
        String msg = "";
        for (int i=0; i<size; i++){ //for every item in the queue
            msg += runner.getData(); //add the data from the current node to the final message
            runner = runner.getNext(); //the runner continues to the next node in the queue
        }
        stream.println(msg);
    }
    
    public int size(){
        return size;
    }
}
