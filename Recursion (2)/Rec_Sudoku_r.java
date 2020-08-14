import java.util.Scanner;
import java.util.ArrayList;

public class Rec_Sudoku
{
  public static Scanner scn=new Scanner (System.in);  
  public static void main(String[] args)
  {
    //solve();
    int[][] arr={
      {3,4,5,6,7,8,9,1,2},
      {6,7,8,9,1,2,3,4,5},
      {9,1,2,3,4,5,6,7,8},
      {1,2,3,4,5,6,7,8,9},
      {4,5,6,7,8,9,1,2,3},
      {7,8,9,1,2,3,4,5,6},
      {2,3,4,5,6,7,8,9,1},
      {5,6,7,8,9,1,2,3,4},
      {8,9,1,2,3,4,5,6,7}
    };
    System.out.println(isValid_Sudoku(arr,4,4,2));
  }

  void display(ArrayList<Integer> arr,int vidx)
  {
    if(vidx==arr.size())
      return ""; 
    System.out.println(arr.get(vidx));
    display(arr,vidx+1);  
  }

  public static void solve()
  {
    ArrayList<Integer> arr="10,6,8,10,4,5,5,6,8,-3,2,12,8,3";
    display(arr,0); 
  }

  public static int[] allIndex(int[] arr,int vidx,int data,int size)
  {
    if(vidx==arr.length)
    {
      int[] baseArray=new int[size];
      return baseArray;
    }
    
    if((arr.get(vidx)==data))
       size++;

    int[] recAns=allIndex(arr,vidx+1,data,size);

    if(arr.get(vidx)==data)
      recAns[size-1]=vidx;

    return recAns;    
  }

  public static Boolean isValid_Sudoku(int[][] board,int row,int col,int num)
  {
    // ! Row:
    for(int idx=0;idx<board.length;idx++)
    {
      if(board[idx][col]==num)
        return false;
    }

    // ! Column:
    for(int idx=0;idx<board.length;idx++)
    {
      if(board[row][idx]==num)
        return false;
    }

    // ! Matrix:
    int rmat=(row/3)*3;
    int cmat=(col/3)*3;
    for(int i=0;i<3;i++)
    {
      for(j=0;j<3;j++)
      {
        if(board[rmat+i][cmat+j]==num)
          return false;
      }
    }

    return true; 
  }
}