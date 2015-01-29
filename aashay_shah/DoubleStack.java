import java.util.EmptyStackException;
import java.lang.RuntimeException;
import java.lang.Throwable;
public class DoubleStack<T>{
   public static int intInput;
   public T[] doubleStack;
   public static int rFirst = -1;
   public static int bFirst;
   public Color c = Color.RED;
   
  public static int readint(){
    String iInput = StdIn.readLine();
    int xInput = Integer.parseInt(iInput);
    return xInput;
  }
  public enum Color{RED, BLUE}
  public DoubleStack(int N){
    bFirst = N;
    intInput = N;
    doubleStack = (T[]) new Object[N];
  }
  public int size(Color c){
    int size=0;
    switch(c){
      case RED:
        size = (rFirst+1);
        break;
      case BLUE:
        size = (intInput-bFirst);
        break;
    }
    return size;
  }
  public boolean isEmpty(Color c){
    if(c == Color.RED){
      if(size(Color.RED) == 0){
        return true;
      }else{return false;}
    }else if(c == Color.BLUE){
      if(size(Color.BLUE) == 0){
        return true;
      }else{return false;}
    }
    return false;
  }
  public boolean isFull(){
    if(size(Color.RED) + size(Color.BLUE) == intInput){
      return true;
    }else{return false;}
  }
  public void push(T s, Color c){
    if(!isFull()){ 
     if(c == Color.RED){
       doubleStack[++rFirst] = s;
     }else if(c == Color.BLUE){
       doubleStack[--bFirst] = s;
     }
    }else{throw new RuntimeException();}
  }
  public T pop(Color c){
    T pop=(T)"";
    if(!isEmpty(c)){
      if(c == Color.RED){
        pop = doubleStack[rFirst];
        doubleStack[rFirst] = null;
        rFirst--;
        //StdOut.print(pop+" ");
      }else if(c == Color.BLUE){
        pop = doubleStack[bFirst];
        doubleStack[bFirst] = null;
        bFirst++;
        //StdOut.print(pop+" ");
      }
    }else{throw new EmptyStackException();}
    return pop;
  }
  public T peek(Color c){
    T peek=(T)"";
     if(!isEmpty(c)){
      if(c == Color.RED){
        peek = doubleStack[rFirst];
       //StdOut.print(peek+" ");
      }else if(c == Color.BLUE){
        peek = doubleStack[bFirst];
        //StdOut.print(peek+" ");
      }
     }else{throw new EmptyStackException();}
    return peek;
  }
  public void input(){
   T in = (T) StdIn.readString();
     if(in.equals("--red")){
       c = Color.RED;
     }else if(in.equals("--blue")){
       c = Color.BLUE;
     }else if(in.equals("?")){
       StdOut.print(peek(c)+" ");
     }else if(in.equals("-")){
       StdOut.print(pop(c)+" ");
     }
     while(!(in.equals("--red") || in.equals("--blue") || in.equals("?") || in.equals("-"))){
       if(c == Color.RED){
         push(in, c);
         break;
       }else if(c == Color.BLUE){
         push(in, c);
         break;
       }
     }
  }
  public static void main(String[] args){
    intInput = readint();
    //bFirst = intInput;
    DoubleStack<String> dS = new DoubleStack<String>(intInput);
    while(!StdIn.isEmpty()){
      dS.input();
    }
    StdOut.println();
    StdOut.print("("+dS.size(Color.RED)+" left on RED stack, "+dS.size(Color.BLUE)+" left on BLUE stack)");
  } 
}