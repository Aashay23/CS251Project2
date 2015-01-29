public class Infix2Prefix extends DoubleStack{
  public static DoubleStack<String> ds;
  public static String[] in;
  public static String rTemp;
  public static String output;
  public static String temp;
  public Infix2Prefix(int N){
    super(N);
  }
  public static boolean operator(String c){
    if(c.equals("+")){
      return true;
    }else if(c.equals("-")){
      return true;
    }else if(c.equals("*")){
      return true;
    }else if(c.equals("/")){
      return true;
    }else{
    return false;
    }
  }
  public static int precedence(String a, String b){
    int value=0;
    if(((a.equals("*") || a.equals("/")) && (b.equals("+") || b.equals("-")))){
      value = -1;
    }else if(((a.equals("+") || a.equals("-")) && (b.equals("*") || b.equals("/")))){
      value = 1;
    }else if(((a.equals("*") || a.equals("/")) && (b.equals("*") || b.equals("/")))){
      value = 0;
    }else if(((a.equals("+") || a.equals("-")) && (b.equals("+") || b.equals("-")))){
      value = 0;
    }
    return value;
  }
  public static void output(){
   while((ds.size(Color.BLUE)) != 0){
    output = output+ds.pop(Color.BLUE)+" ";
   }
   while((ds.size(Color.RED)) > 0){
    temp = temp+ds.pop(Color.RED)+" ";
   }
   for(int j=0;j<((temp.length())-1);j++){
    rTemp = temp.charAt(j)+rTemp;
   }
  }
  public static void inputCheck(){
   rTemp="";
   temp="";
   output="";
   for(int i=(in.length-1);i>=0;i--){
    if((!operator(in[i])) && (!in[i].equals(")")) && (!in[i].equals("("))){
     ds.push(in[i], Color.BLUE);   
    }else if(in[i].equals(")")){
      ds.push(in[i], Color.RED);
    }
    if(operator(in[i])){
     if(ds.isEmpty(Color.RED)){
      ds.push(in[i], Color.RED);
     }else{
       if(((ds.peek(Color.RED)).equals(")"))){
        ds.push(in[i], Color.RED);
       }else if(((precedence((ds.peek(Color.RED)), in[i])) == 1 || (precedence((ds.peek(Color.RED)), in[i])) == 0)){
         ds.push(in[i], Color.RED);
         break;
       }else if((precedence((ds.peek(Color.RED)), in[i])) == -1){
         ds.push((ds.pop(Color.RED)), Color.BLUE);
         ds.push(in[i], Color.RED);
       }
      }
    }
    if(in[i].equals("(")){
     while(!(ds.peek(Color.RED)).equals(")")){
      ds.push((ds.pop(Color.RED)), Color.BLUE);
     }
     ds.pop(Color.RED);
    }
    if(i == 0){
     output();
    }
   }
  }
  public static void main(String[] args){
    while(!StdIn.isEmpty()){
      String input = StdIn.readLine();
      in = input.split(" ");
      ds = new DoubleStack<String>(in.length);
      inputCheck();
      StdOut.println((rTemp+" "+output).trim());
    }
  }
}