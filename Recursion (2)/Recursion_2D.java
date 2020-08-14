// ^ In this Question-1, if we input "245" we will print all possible words from it (Nokia Keypad).
// & In this Question-2, if we input "abc" we will get all possible permutation as [c,bc,cb,abc,bac,bca,acb,cab,cba].
// ~ In this Question-3, if we input "1123" where {1=a,2=b,--,26=z} we will get [1 as a,11 as k,etc as all possible combinations]. 
// ? In this Question-4, we are doing Sudoku Problem.
// ! In this, "ros" means "rest of string" & instead of "index" we can write "idx".

import java.util.Scanner;
import java.util.ArrayList;

public class Recursion_2D
{
  public static Scanner scn = new Scanner(System.in);
  public static void main(String[] args) 
  { 
    //String[] keys={".","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    //System.out.println(KeypadDial("245",keys));
    //System.out.println(stringPerm("abc"));
    //System.out.println(Encoding("1123"));
    ////System.out.println(stringPermwwithoutDuplicates("aabbc"));
    
    int[][] board =
    {
      {3,0,6,5,0,8,4,0,0},
      {5,2,0,0,0,0,0,0,0},
      {0,8,7,0,0,0,0,3,1},
      {0,0,3,0,1,0,0,8,0},
      {9,0,0,8,6,3,0,0,5},
      {0,5,0,0,9,0,6,0,0},
      {1,3,0,0,0,0,2,5,0},
      {0,0,0,0,0,0,0,7,4},
      {0,0,5,2,0,6,3,0,0}
    };

    // System.out.println(isValid_Sudoku(board,2,4,4));
    System.out.println(Sudoku(board,0));
  }

  public static ArrayList<String> KeypadDial(String ques,String[] keys) 
  {
    if(ques.length()==0)
    {
      ArrayList<String> base=new ArrayList<>();
      base.add("");
      return base;
    }

    char ch=ques.charAt(0);
    String ros=ques.substring(1);
    int index=ch-'0';
    String word= keys[index];
    
    ArrayList<String> ans = new ArrayList<>();
    ArrayList<String> recAns = KeypadDial(ros,keys); 
    
    for(String s:recAns)
    {
      for(int i=0;i<word.length();i++)
        ans.add(word.charAt(i)+s);
    }

    return ans;
  }  

  public static ArrayList<String> stringPerm(String str)
  {
    if(str.length()==0)
      return new ArrayList<>();
    
    if(str.length()==1)
    {
      ArrayList<String> base=new ArrayList<>();
      base.add(str);
      return base;
    }

    char ch=str.charAt(0);
    String ros=str.substring(1);

    ArrayList<String> recAns=stringPerm(ros);
    ArrayList<String> myAns=new ArrayList<>();
 
    for(String s:recAns)
    {
      for(int i=0;i<=s.length();i++)
      {
        String ans=s.substring(0,i)+ch+s.substring(i);
        myAns.add(ans);
      }  
    }

    return myAns;
  } 

  public static ArrayList<String> Encoding(String str)
  {
    if(str.length()==0)
    {
      ArrayList<String> base=new ArrayList<>();
      base.add("");
      return base;
    } 

    //String[] alphabets={" ","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};   
    char ch=str.charAt(0);
    String ros=str.substring(1);
    
    ArrayList<String> myAns=new ArrayList<>();

    if(ch=='0')
      return Encoding(str.substring(1));
    else
    {
      ArrayList<String> recAns=Encoding(str.substring(1));
      for(String s:recAns)
      {
        char ch1=(char)('a'+ch-'1');
        myAns.add(ch1+s);
      }
    }

    if(str.length()>1)    
    {
      char ch1=str.charAt(1);
      int num=((ch-'0')*10+(ch1-'0'));
      //int num = Integer.parseInt(str.substring(0,2));

      if(num<27)
      {
        ArrayList<String> recAns=Encoding(str.substring(2));
        for(String s:recAns)
        {
          char ch2=(char)('a'+num-1);
          myAns.add(ch2+s);
        }
      }
    } 
  
    return myAns;
  }

  public static Boolean isValid_Sudoku(int[][] board,int num,int row,int col)
  {
    boolean flag=false;
    // ! Row:
    for(int idx=0;idx<board.length;idx++)
    {
      if(board[idx][col]==num)
        flag=true;
    }

    // ! Column:
    for(int idx=0;idx<board.length;idx++)
    {
      if(board[row][idx]==num)
        flag=true;
    }

    // ! Matrix:
    int rmat=(row/3);
    int cmat=(col/3);
    for(int i=(rmat*3);i<(rmat*3+3);i++)
    {
      for(int j=(cmat*3);j<(cmat*3+2);j++)
      {
        if(board[i][j]==num)
          flag=true;
      }
    }

    if(flag==false)
      return true;
       
    return false; 
  }

  public static Boolean Sudoku(int[][] board,int vidx)
  {
    if(vidx==board.length*board[0].length)
    {
      for(int[] ar:board)
      {
        for(int ele:ar)
          System.out.print(ele+" ");
        System.out.println();            
      }
      System.out.println();
      return true;
    }
 
    int r=vidx/9;
    int c=vidx%9;
    boolean res=false;

    if(board[r][c]!=0)
       res=res || Sudoku(board,vidx+1);               // ! Bit Masking to reduce memory space.
    else
    {
      for(int num=1;num<=9;num++)
      {
        if(isValid_Sudoku(board,num,r,c))
        {
          board[r][c]=num;
          res=res || Sudoku(board,vidx+1);
          board[r][c]=0;
        }
      }
    }

    return res;
  }
}  

