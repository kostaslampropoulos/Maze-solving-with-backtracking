import java.util.*;
import java.io.*;

public class StringQueueWithOnePointer<T> implements StringQueue<T>{
    private Node<T> rear; //the rear of the queue (behind all of the other nodes, newest item)
    private int size = 0;

    public boolean isEmpty(){
        return size==0; //true if empty
    }

    public void put(T item){
        Node<T> newNode = new Node<>(item);
        if (isEmpty()){
            rear = newNode; //if empty then the rear of the queue is the newNode
        }
        else if (size==1){ //if theres only 1 node inside the queue
            rear.setNext(newNode); //the newNode is next in the queue after the current rear
            newNode.setNext(rear); //the pointer of the newNode is pointing to the current rear
            rear = newNode; //the newNode is the new rear
        }
        else{//if there are atleast 2 nodes in the queue
            newNode.setNext(rear.getNext());
            //rear.getNext() gives us the front of the queue 
            //we set the pointer of the newNode to be the front of the queue

            rear.setNext(newNode);
            //we set the current rear node's pointer to point to the newNode (next in queue)

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
            data = rear.getNext().getData(); //get the data from the front (rear.getNext()) of the queue
            rear = null; //the queue is empty
        }
        else{
            data = rear.getNext().getData(); //get the data from the front (rear.getNext()) of the queue
            rear.setNext(rear.getNext().getNext()); 
            //rear.getNext() gives us the front of the queue 
            //rear.getNext().getNext() == front.getNext(), which gives us the second node in the queue
            //rear.setNext(rear.getNext().getNext()) == rear.setNext(front.getNext()) == rear.setNext('second in the queue');
            //we set the pointer of the rear to point to the new front of the queue
        }
        size-=1;
        return data;

    }

    public T peek() throws NoSuchElementException{
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return rear.getNext().getData(); //get the data from the front (rear.getNext()) of the queue
    }
    
    public void printQueue(PrintStream stream){
        String msg = "";
        if (isEmpty()==false){ //if the queue isnt empty
            Node<T> runner = rear.getNext(); //a runner that will go through the queue, without changing the rear (starting from the front  (rear.getNext()) of the queue)
            for (int i=0; i<size; i++){ //for every item in the queue
                msg += runner.getData(); //add the data from the current node to the final message
                runner = runner.getNext(); //the runner continues to the next node in the queue
            }
        }
        stream.println(msg);
    }
    
    public int size(){
        return size;
    }
}