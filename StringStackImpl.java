import java.util.*;
import java.io.*;

public class StringStackImpl<T> implements StringStack<T> {
    private Node<T> head; //the head of the stack (on top of all the other nodes)
    private int size = 0;

    public boolean isEmpty(){
        return size==0; //true if empty
    }

    public void push (T item){
        Node<T> newNode = new Node<> (item);
        if (isEmpty()){
            head = newNode; //if empty then the first node is the newNode
        }
        else{
            newNode.setNext(head); //else, the current head comes after the newNode (newNode is on top of the current head in the stack)
            head = newNode; //set the newNode as the new head of the stack
        }

        size+=1;

    }

    public T pop() throws NoSuchElementException{
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        T data = head.getData(); //data to be returned from the head
        if(size==1) {
            head = null; //empty the stack
        }
        else{
            head = head.getNext(); //the new head, is the node under the current head
        }
        size-=1;
        return data;
    }

    public T peek() throws NoSuchElementException{
        if (isEmpty()){
            throw new NoSuchElementException();
        }
        return head.getData(); // return the data from the head
    }

    public void printStack(PrintStream stream){
        Node<T> runner = head; //a runner that will go through the stack, without changing the head
        String msg = "";
        for (int i=0; i<size; i++){ //for every item in the stack
            msg += runner.getData(); //add the data from the current node to the final message
            runner = runner.getNext(); //the runner continues to the next node in the stack
        }
        stream.println(msg);
    }
    
    public int size(){
        return size;
    }
}