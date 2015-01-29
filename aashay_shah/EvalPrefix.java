public class EvalPrefix extends DoubleStack{
  DoubleStack<String> dS;
  public static String operator;
  public static int operand1;
  public static int operand2;
  public static String[] in;
  public EvalPrefix(int N){
    super(N);
  }
  public void calculate(String x, int y, int z){
    int result;
    if(x.equals("+")){
      result = (y+z);
      dS.push((""+result), Color.RED);
    }else if(x.equals("-")){
      result = (y-z);
      dS.push((""+result), Color.RED);
    }else if(x.equals("*")){
      result = (y*z);
      dS.push((""+result), Color.RED);
    }else if(x.equals("/")){
      result = (y/z);
      dS.push((""+result), Color.RED);
    }
  }
  public boolean operator(String s){
    if(s.equals("+")){
      return true;
    }else if(s.equals("-")){
      return true;
    }else if(s.equals("*")){
      return true;
    }else if(s.equals("/")){
      return true;
    }else{return false;}
  }
  public void input(){
   dS = new DoubleStack<String>(in.length);
   for(int i=((in.length)-1);i>=0;i--){
    if(!(operator(in[i]))){
     dS.push((""+in[i]), Color.RED);
    }else if(operator(in[i])){
      operator = ""+in[i];
      operand1 = Integer.parseInt(dS.pop(Color.RED));
      operand2 = Integer.parseInt(dS.pop(Color.RED));
      calculate(operator, operand1, operand2);
    }
   }
  }
  public void print(){
   StdOut.print(Integer.parseInt(dS.pop(Color.RED)));
   StdOut.println();
  }
  public static void main(String[] args){
   while(!StdIn.isEmpty()){
    String input = StdIn.readLine();
    in = input.split(" ");
    EvalPrefix eP = new EvalPrefix(in.length);
    eP.input();
    eP.print();
   }
  }
}