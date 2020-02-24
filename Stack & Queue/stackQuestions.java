import java.util.Stack;
import java.util.ArrayList;

public class stackQuestions
{
  public static void nextGreaterElement_Left(int[] arr)
  {
    Stack<Integer> st = new Stack<>();

    for(int i = arr.length - 1;i >= 0 ;i--)
    {
      if(st.size() == 0)
      {
        st.push(arr[i]);
        continue;
      }

      while(st.size() != 0 && st.peek() < arr[i]) 
        System.out.println(st.pop() + " -> " + arr[i]);

      st.push(arr[i]);
    }

    while(st.size() != 0)
      System.out.println(st.pop() + " -> " + -1 );
  }

  // ! Complexity : O(n). 
 
  public static void nextGreaterElement_Right(int[] arr)
  {
    Stack<Integer> st = new Stack<>();

    for(int i = 0;i < arr.length;i++)
    {
      if(st.size() == 0)
      {
        st.push(arr[i]);
        continue;
      }

      while(st.size() != 0 && st.peek() < arr[i]) 
        System.out.println(st.pop() + " -> " + arr[i]);

      st.push(arr[i]);
    }

    while(st.size() != 0)
      System.out.println(st.pop() + " -> " + -1 );
  }

  public static void nextSmallerElement_Left(int[] arr)
  {
    Stack<Integer> st = new Stack<>();

    for(int i = arr.length - 1;i >= 0;i--)
    {
      if(st.size() == 0)
      {
        st.push(arr[i]);
        continue;
      }

      while(st.size() != 0 && st.peek() > arr[i]) 
        System.out.println(st.pop() + " -> " + arr[i]);

      st.push(arr[i]);
    }

    while(st.size() != 0)
      System.out.println(st.pop() + " -> " + -1 );    
  }

  public static void nextSmallerElement_Right(int[] arr)
  {
    Stack<Integer> st = new Stack<>();

    for(int i = 0;i < arr.length;i++)
    {
      if(st.size() == 0)
      {
        st.push(arr[i]);
        continue;
      }

      while(st.size() != 0 && st.peek() > arr[i]) 
        System.out.println(st.pop() + " -> " + arr[i]);

      st.push(arr[i]);
    }

    while(st.size() != 0)
      System.out.println(st.pop() + " -> " + -1 );
  } 
  
  // TODO ==========================================================================================================================

  public static int[] asteroidCollision(int[] arr)
  {
    Stack<Integer> st = new Stack<>();
    
    for(int i = 0;i < arr.length;i++)
    {
      if(arr[i] > 0)
        st.push(arr[i]);

      else
      {
        while(st.size() != 0 && st.peek() > 0 && st.peek() < (-arr[i]))
          st.pop();

        if(st.size() != 0 && st.peek() == (-arr[i]))
          st.pop();

        else if(st.size() == 0 || st.peek() < 0)
          st.push(arr[i]);    
      }  
    }

    int[] ans = new int[st.size()];

    for(int i = ans.length - 1;i >= 0;i++)
      ans[i] = st.pop();

    return ans;  
  }

  public String removeKDigits(String num,int k)
  {
    // ! It converts string like a character array. 

    char[] arr = num.toCharArray();
    Stack<Character> st = new Stack<>();

    for(int i = 0;i < arr.length; i++)
    {
      while(st.size() != 0 && st.peek() > arr[i] && k != 0)
      {
        st.pop();
        k--;
      }

      st.push(arr[i]);
    }

    while(k-- > 0)
      st.pop();

    // ! StringBuilder reduces the time complexity but not space complexity.
    // ! It also string like an charater of array with more properties and similar to String of c++/ Arraylist of characters.

    StringBuilder sb = new StringBuilder();

    while(st.size() != 0)
      sb.append(st.pop());
 
    if (sb.length() != 0 && sb.charAt(sb.length() - 1) == '0') 
    { 
      for (int i = sb.length() - 2; i >= 0; i--) 
      {
        if (sb.charAt(i) != '0')
          break;
      }

      sb = new StringBuilder(sb.substring(0, i + 1));

    }

    sb.reverse();   
    String ans = sb.toString();

    return ans.length() != 0 ? ans : "0";
  }

  // TODO ========================================================================================================================== 

  public static void solve()
  {
    int[] arr = {9,1,7,3,11,-2,25,20,2,-4};
    
    // System.out.println("Next Greater Element on Left: \n");
    // nextGreaterElement_Left(arr);

    // System.out.println("Next Greater Element on Right: \n");
    // nextGreaterElement_Right(arr);

    // System.out.println("Next Smaller Element on Left: \n");
    // nextSmallerElement_Left(arr);

    // System.out.println("Next Smaller Element on Left: \n");
    // nextSmallerElement_Right(arr);

     
  }

  public static void main(String[] args) {
    solve();
  }
}