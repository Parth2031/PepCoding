import java.util.ArrayList;

public class stackImplement
{
  public static void solve(dynamicStack st)                            // ! stack st as an argument to call stack object.
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

    dynamicStack dst = new dynamicStack();
    solve(dst); 
  }
}