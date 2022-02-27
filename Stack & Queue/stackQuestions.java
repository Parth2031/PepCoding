import java.util.Stack;
import java.util.ArrayList;

public class stackQuestions
{
  public static void nextGreaterElement_Left(int[] arr)
  {
    Stack<Integer> st = new Stack<>();

    for(int i = arr.length - 1;i >= 0 ;i--)
    {
      // ! This could be avoided as we put the check condition that st.size() != 0 so it will not pop until there is an element pushed. 

      // if(st.size() == 0)                     
      // {
      //   st.push(arr[i]);
      //   continue;
      // }

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
  
  // ^ ==========================================================================================================================

  // * LeetCode Questions :--->

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

    for(int i = ans.length - 1;i >= 0;i--)
      ans[i] = st.pop();

    return ans;  
  }

  public static String removeKDigits(String num,int k)                       // ? Name of function is : removeKdigits() in LeetCode.
  {
    // & It converts string like a character array. 

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
    // ^ It also string like an charater of array with more properties and similar to String of c++/ Arraylist of characters.

    StringBuilder sb = new StringBuilder();

    while(st.size() != 0)
      sb.append(st.pop());
 
    if (sb.length() != 0 && sb.charAt(sb.length() - 1) == '0') 
    { 
      int i = sb.length() - 2;
      for (; i >= 0; i--) 
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

  // ~ largestRectangleArea(int[] heights) <-- Name of function in LeetCode.

  public static int maxAreaInHistogram(int[] arr)         // ? TestCase in checking all conditions : {1,2,3,4,4,6,6,6,5,2,4,6,5}. 
  {
    int maxArea = 0;
    Stack<Integer> st = new Stack<>();

    st.push(-1);

    for(int i = 0;i < arr.length; i++)
    {
      while(st.peek() != -1 && arr[st.peek()] >= arr[i])
      {
        int height = arr[st.pop()];
        int leftBoundary = st.peek();
        int width = (i - leftBoundary - 1);
        int area = width * height;

        if(area > maxArea)
          maxArea = area;
      }

      st.push(i);
    }

    while(st.peek() != -1)
    {
      int height = arr[st.pop()];
      int leftBoundary = st.peek();
      int width = (arr.length - leftBoundary - 1);
      int area = width * height;

      if(area > maxArea)
        maxArea = area;      
    }

    return maxArea;
  }

  public static int maximalRectangle(char[][] matrix)
  {
    if(matrix.length == 0)
      return 0;

    int[] height = new int[matrix[0].length];
    int area = 0;

    for(int i = 0;i < matrix.length; i++)
    {
      for(int j = 0;j < matrix[0].length; j++)
      {
        int value = matrix[i][j] - '0';
        
        if(value == 1)
          height[i] += matrix[i][j];
      
        else
          height[i] = 0; 
      }
      
      area = Math.max(area,maxAreaInHistogram(height));
    }
    
    return area; 
  }

  public static boolean balanceBrackets(char[] arr) 
  {
    Stack<Character> st = new Stack<>();
        
    for (int i = 0; i < arr.length; i++) 
    {
      char ch = arr[i];
      
      if (ch == '(' || ch == '{' || ch == '[' || ch == '<')
        st.push(ch);
      
      else 
      {
        if (st.size() == 0)
            return false;
        else if (st.peek() == '(' && ch != ')')
            return false;
        else if (st.peek() == '{' && ch != '}')
            return false;
        else if (st.peek() == '[' && ch != ']')
            return false;
        else if (st.peek() == '<' && ch != '>')
            return false;

        st.pop();
      }
    }

    return st.size() == 0;
  }

  // ! trap(int[] height) <-- Name of function in LeetCode.

  public static int rainWater(int[] arr)                      
  {
    Stack<Integer> st = new Stack<>();
    
    int ans = 0;
    st.push(-1);

    for (int i = 0; i < arr.length; i++) 
    {
      while (st.peek() != -1 && arr[i] > arr[st.peek()]) 
      {
        int h = arr[st.pop()];
                
        if (st.peek() == -1)
          break;
                
        int width = i - st.peek() - 1;
        int height = (Math.min(arr[st.peek()], arr[i]) - h);
                
        ans += width * height;
      }

      st.push(i);
    }

    return ans;
  }

  // ^ ========================================================================================================================== 

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