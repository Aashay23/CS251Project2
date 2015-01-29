import java.util.Iterator;
import java.lang.RuntimeException;
import java.lang.Throwable;
public class MyQueue<T> implements Iterable<T>{
  private int N=1;
  public T[] number;
  private Node first;
  private Node last;
  private class Node{
    private T x;
    private Node next;
  }
  public MyQueue(){
    first = null;
    last = null;
  }
  public int size(){
    return (N-1);
   }
  public boolean isEmpty(){
    return first == null;
  }
  public boolean isFull(){
    return false;
  }
  public void enqueue(T e){
    Node oldlast = last;
    last = new Node();
    last.x = e;
    last.next = null;
    if(isEmpty()){
     first = last;
     number = (T[]) new Object[N];
     number[0] = last.x;
    }else{
      oldlast.next = last;
      //++N;
    }
    if((N) == (number.length)){
      resize(2*number.length);
      number[N] = last.x;
    }else{
      number[N] = last.x;
    }
    N++;
  }
  public void resize(int a){
    T[] temp = (T[]) new Object[a];
    for(int b=0;b<N;b++){
      temp[b] = number[b];
    }
    number = temp;
  }
  public T dequeue(){
    T[] shift = (T[]) new Object[number.length];
    if(isEmpty()){
      throw new RuntimeException();
    }
    T t = first.x;
    number[0] = null;
    N--;
    first = first.next;
    for(int j=0;j<N;j++){
      shift[j] = number[j+1];
    }
    number = shift;
    return t;
  }
  public T peek(){
    if(isEmpty()){
      throw new RuntimeException();
    }else{return first.x;}
  }
  public T lookup(int i){
    return number[i];
  }
  public Iterator<T> iterator(){
    return new MyIterator();
  }
  private class MyIterator implements Iterator<T>{
    Node current = first;
    public boolean hasNext(){
      if(current != null){
        return true;
      }else{return false;}
    }
    public void remove(){throw new RuntimeException();}
    public T next(){
      T t = current.x;
      current = current.next;
      return t;
    }
  }
  public boolean operator(T s){
    if(s.equals("-")){
      return true;
    }else if(s.equals("*")){
      return true;
    }else if(s.equals("?")){
      return true;
    }else{return false;}
  }
  public void input(){
   String out="";
   int element;
   T in = (T) StdIn.readString();
   if(!(operator(in))){
    enqueue(in);
   }
   if(in.equals("-")){
    out += (dequeue())+" ";
   }
   if(in.equals("*")){
    out += (peek())+" ";
   }
   if(in.equals("?")){
    out += (lookup(Integer.parseInt(StdIn.readString())));
   }
   StdOut.print(out);
  }
  public static void main(String[] args){
    MyQueue<String> q = new MyQueue<String>();
    while(!StdIn.isEmpty()){
     q.input();
    }
    StdOut.print(" ");
    StdOut.println();
    StdOut.println(q.size());
  }
}