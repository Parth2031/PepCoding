import java.util.Scanner;

public class stackImplement
{
  public static void solve(dynamicStack st)                            // ! Stack st as an argument to call stack object.
  {
    // System.out.println("Stack: \n");     
    // st.push(10);
    // st.push(20);
    // st.push(30);
    // st.display(); 

    // System.out.println("Size: " + st.size());
    // System.out.println("Popped Element: " + st.pop());
    // System.out.println("Size: " + st.size());
    // System.out.println("Is Stack Empty: " + st.isEmpty());
    // System.out.println("Top Element: " + st.top()); 

    System.out.println("Dynamic Stack: \n");
    st.push(100);
    st.display();
  }

  public static void main(String[] args) 
  {
    // stack st = new stack(3);
    // solve(st);

    // dynamicStack st = new dynamicStack();
    // solve(st); 

    // ! This case is wrong as all stack values are in dynamicStack but not all dynamicStack functions are in stack.
    // ^ So, dynamicStack points stack but memory is allocated for stack. 

    // dynamicStack st = new stack(10);     
    // solve(st);

    // ! This case is correct all LHS pointer functions are printed but if a conflict arises it refers to the RHS memory allocated.
    // ^ So, RHS value gets evaluated. 

    // stack st = new dynamicStack(10);
    // solve(st);

    // st.fun1();
    // st.fun2();                                       // ! This will not work as it is part of of dynamicStack class.
    // st.fun();

    // System.out.println(st.a);
    // System.out.println(st.b);
    // System.out.println(st.c);
  }
}