import java.util.Stack;
import java.util.ArrayList;

public class stackMin
{
  static class MinStack 
  {
    // ! minst -> Min Stack.

    Stack<Integer> st;
    Stack<Integer> minSt;
    int min = Integer.MAX_VALUE;

    public MinStack() 
    {
      st = new Stack<>();
      minSt = new Stack<>();
    }

    public void push(int x) 
    {
      st.push(x);
      minSt.push(Math.min(min, x));
      min = minSt.peek();
    }

    public void pop() 
    {
      if (st.size() == 0)
        return;

      st.pop();
      minSt.pop();

      if (minSt.size() != 0)
        min = minSt.peek();
      
      else
        min = Integer.MAX_VALUE;
    }

    public int top() {
      return st.peek();
    }

    public int getMin() {
      return minSt.peek();
    }  
  }

  static class MinStack_2 
  {
    // ! minsf -> Minimum So Far.

    Stack<Long> st;
    long minsf = 0;

    public MinStack_2() { 
      st = new Stack<>();
    }

    public void push(long x) 
    {
      long val = x;
            
      if (st.size() == 0) 
      {
        minsf = val;
        st.push(val);
        return;
      }

      if (val > minsf) 
        st.push(val);
            
      else 
      {
        st.push(val - minsf + val);
        minsf = val;
      }
    }

    public void pop() 
    {
      if (st.peek() <= minsf) 
        minsf = minsf - st.peek() + minsf;
    
      st.pop();
    }

    public int top() 
    {
      if (st.peek() <= minsf)
        return (int) minsf;

      long val = st.peek();
      
      return (int) val;
    }

    public int getMin() {
      return (int) minsf;
    }    
  }

  // TODO ========================================================================================================================== 

  public static void solve()
  {
    MinStack_2 st = new MinStack_2();
        
    st.push(4);
    st.push(0);
    st.push(2);

    // st.pop();
    System.out.println("Minimum Value in stack: " + st.getMin());     
  }

  public static void main(String[] args) {
    solve();
  }
}