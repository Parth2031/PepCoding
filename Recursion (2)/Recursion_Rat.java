// TODO:: 2D Matrix Recursion Problems:-
// ? In this Question, 2-D matrix works as a Maze Path in which from (0,0) we need to reach (2,2).
// ? In this, we are doing FloodFill Algorithm which works similar to Maze Path Concept.
// ! In this floodFilldiag_block means there are 8 directions a person or dot can move in which there are some boxes which are blocked can be used as path.
// ! Knight problems use Horse moves. 
// ? In this, we are doing Knight Problems which are based on Floodfill algorithm.

import java.util.Scanner;
import java.util.ArrayList;

public class Recursion_Rat
{
  public static Scanner scn = new Scanner(System.in);
  public static void main(String[] args) 
  { 
    //System.out.println(mazePath(0,0,2,2));
    //System.out.println(mazePathdiag(0,0,2,2));
    //System.out.println(mazePathdiag_height(0,0,2,2));
    //System.out.println(mazePathdiag_MultiMove(0,0,2,2));
    //boolean[][] isdone=new boolean[3][3];
    //System.out.println(floodFill(0,0,2,2,isdone));
    //System.out.println(floodFilldiag(0,0,2,2,isdone));
    // boolean[][] path={{false,false,false},
    //                   {false,false,true},
    //                   {false,false,false},
    //                  };
    //System.out.println(floodFilldiag_block(0,0,2,2,path,isdone));
    //System.out.println(floodFill_Knight(0,0,4,4,new boolean[5][5]).size());
    //System.out.println(KnightPath(0,0,3,3,new boolean[4][4]));
    System.out.println(KnightTour(0,0,0,64,new boolean[8][8],new int[8][8]));
  }

  public static ArrayList<String> mazePath(int startrow,int startcolumn,int endrow,int endcolumn)
  {
    if(startrow==endrow && startcolumn==endcolumn)
    {
      ArrayList<String> base = new ArrayList<>();
      base.add("");
      return base;  
    }
    ArrayList<String> ans= new ArrayList<>();
    if(startrow+1<=endrow)
    {
      ArrayList<String> Vertical = mazePath(startrow+1,startcolumn,endrow,endcolumn);
      for(String s:Vertical)
        ans.add("V"+s);
    }

    if(startcolumn+1<=endcolumn)
    {
      ArrayList<String> Horizontal = mazePath(startrow,startcolumn+1,endrow,endcolumn);
      for(String s:Horizontal)
        ans.add("H"+s);
    }
    
    return ans;
  }

  public static ArrayList<String> mazePathdiag(int startrow,int startcolumn,int endrow,int endcolumn)
  {
    if(startrow==endrow && startcolumn==endcolumn)
    {
      ArrayList<String> base = new ArrayList<>();
      base.add("");
      return base;  
    }

    ArrayList<String> ans= new ArrayList<>();

    if(startrow+1<=endrow)
    {
      ArrayList<String> Vertical = mazePathdiag(startrow+1,startcolumn,endrow,endcolumn);
      for(String s:Vertical)
        ans.add("V"+s);
    }

    if(startrow+1<=endrow && startcolumn+1<=endcolumn)
    {
      ArrayList<String> Diagonal = mazePathdiag(startrow+1,startcolumn+1,endrow,endcolumn);
      for(String s:Diagonal)
        ans.add("D"+s);
    }

    if(startcolumn+1<=endcolumn)
    {
      ArrayList<String> Horizontal = mazePathdiag(startrow,startcolumn+1,endrow,endcolumn);
      for(String s:Horizontal)
        ans.add("H"+s);
    }
    
    return ans;
  } 

  public static int mazePathdiag_height(int startrow,int startcolumn,int endrow,int endcolumn)
  {
    if(startrow==endrow && startcolumn==endcolumn)
      return 0;  
    
    int maxHeight=0;
    if(startrow+1<=endrow)
      maxHeight=Math.max(maxHeight,mazePathdiag_height(startrow+1,startcolumn,endrow,endcolumn));

    if(startrow+1<=endrow && startcolumn+1<=endcolumn)
      maxHeight=Math.max(maxHeight,mazePathdiag_height(startrow+1,startcolumn+1,endrow,endcolumn));

    if(startcolumn+1<=endcolumn)
      maxHeight=Math.max(maxHeight,mazePathdiag_height(startrow,startcolumn+1,endrow,endcolumn));
    
    return maxHeight+1;
  }  

  public static ArrayList<String> mazePathdiag_MultiMove(int startrow,int startcolumn,int endrow,int endcolumn)
  {
    if(startrow==endrow && startcolumn ==endcolumn)
    {
      ArrayList<String> base = new ArrayList<>();
      base.add(" ");
      return base;  
    }

    ArrayList<String> ans = new ArrayList<>();
    
    for(int i = 1; (startrow + i) <= endrow; i++)
    {    
      ArrayList<String> Vertical = mazePathdiag_MultiMove(startrow+i,startcolumn,endrow,endcolumn);
      for(String s:Vertical)
        ans.add("V"+i+s);
    }

    for(int i = 1;(startrow+i) <= endrow && (startcolumn+i) <= endcolumn;i++)
    {
      ArrayList<String> Diagonal = mazePathdiag_MultiMove(startrow+i,startcolumn+i,endrow,endcolumn);
      for(String s:Diagonal)
        ans.add("D"+i+s);
    }

    for(int i = 1;(startcolumn+i) <= endcolumn;i++)
    {
      ArrayList<String> Horizontal = mazePathdiag_MultiMove(startrow,startcolumn+i,endrow,endcolumn);
      for(String s:Horizontal)
        ans.add("H"+i+s);
    }
    
    return ans; 
  }

  public static ArrayList<String> floodFill(int startrow,int startcolumn,int endrow,int endcolumn,boolean[][] isdone)
  {
    if(startrow==endrow && startcolumn==endcolumn)
    {
      ArrayList<String> base= new ArrayList<>();
      base.add("");
      return base;
    }

    ArrayList<String> ans= new ArrayList<>();

    isdone[startrow][startcolumn]=true;

    if(startrow+1<=endrow && !isdone[startrow+1][startcolumn])
    {
      ArrayList<String> Right = floodFill(startrow+1,startcolumn,endrow,endcolumn,isdone);
      for(String s:Right)
        ans.add("R"+s);
    }

    if(startcolumn+1<=endcolumn && !isdone[startrow][startcolumn+1])
    {
      ArrayList<String> Down = floodFill(startrow,startcolumn+1,endrow,endcolumn,isdone);
      for(String s:Down)
        ans.add("D"+s);
    }
    
    if(startrow-1>=0 && !isdone[startrow-1][startcolumn])
    {
      ArrayList<String> Left = floodFill(startrow-1,startcolumn,endrow,endcolumn,isdone);
      for(String s:Left)
        ans.add("L"+s);
    }

    if(startcolumn-1>=0 && !isdone[startrow][startcolumn-1])
    {
      ArrayList<String> Up = floodFill(startrow,startcolumn-1,endrow,endcolumn,isdone);
      for(String s:Up)
        ans.add("U"+s);
    }

    isdone[startrow][startcolumn]=false;
    return ans;  
  }

  public static ArrayList<String> floodFilldiag(int startrow,int startcolumn,int endrow,int endcolumn,boolean[][] isdone)
  {
    if(startrow==endrow && startcolumn==endcolumn)
    {
      ArrayList<String> base= new ArrayList<>();
      base.add("");
      return base;
    }

    ArrayList<String> ans= new ArrayList<>();

    isdone[startrow][startcolumn]=true;

    if(startrow+1<=endrow && !isdone[startrow+1][startcolumn])
    {
      ArrayList<String> Right = floodFilldiag(startrow+1,startcolumn,endrow,endcolumn,isdone);
      for(String s:Right)
        ans.add("R"+s);
    }

    if(startcolumn+1<=endcolumn && !isdone[startrow][startcolumn+1])
    {
      ArrayList<String> Down = floodFilldiag(startrow,startcolumn+1,endrow,endcolumn,isdone);
      for(String s:Down)
        ans.add("D"+s);
    }
    
    if(startrow-1>=0 && !isdone[startrow-1][startcolumn])
    {
      ArrayList<String> Left = floodFilldiag(startrow-1,startcolumn,endrow,endcolumn,isdone);
      for(String s:Left)
        ans.add("L"+s);
    }

    if(startcolumn-1>=0 && !isdone[startrow][startcolumn-1])
    {
      ArrayList<String> Up = floodFilldiag(startrow,startcolumn-1,endrow,endcolumn,isdone);
      for(String s:Up)
        ans.add("U"+s);
    }

    if(startrow+1<=endrow && startcolumn+1<=endcolumn)
    {
      ArrayList<String> South_East = floodFilldiag(startrow,startcolumn,endrow,endcolumn,isdone);
      for(String s:South_East)
        ans.add("4"+s);
    }

    if(startrow+1<=endrow && startcolumn-1>=0)
    {
      ArrayList<String> North_East = floodFilldiag(startrow,startcolumn,endrow,endcolumn,isdone);
      for(String s:North_East)
        ans.add("1"+s);
    }

    if(startrow-1>=0 && startcolumn+1<endcolumn)
    {
      ArrayList<String> South_West = floodFilldiag(startrow,startcolumn,endrow,endcolumn,isdone);
      for(String s:South_West)
        ans.add("3"+s);
    }

    if(startrow-1>=0 && startcolumn-1>=0)
    {
     ArrayList<String> North_West = floodFilldiag(startrow,startcolumn,endrow,endcolumn,isdone);
     for(String s:North_West)
       ans.add("2"+s);
    }

    isdone[startrow][startcolumn]=false;
    return ans;   
  }

  public static boolean isValid(int x,int y,boolean[][] path,boolean[][] isdone)
  {
    if(x>=0 && y>=0 && x<isdone.length && y<isdone[0].length && !isdone[x][y] && !path[x][y]) 
      return true;
    else  
      return false;
  }
 
  public static ArrayList<String> floodFilldiag_block(int startrow,int startcolumn,int endrow,int endcolumn,boolean[][] path,boolean[][] isdone)
  {
    if(startrow==endrow && startcolumn==endcolumn)
    {
      ArrayList<String> base=new ArrayList<>();
      base.add("");
      return base;
    }

    int[][] direction={{1,0},{0,1},{-1,0},{0,-1},{1,1},{-1,-1},{-1,1},{1,-1}};
    String[] directionName={"D","R","U","L","1","3","4","2"};

    ArrayList<String> ans= new ArrayList<>();

    isdone[startrow][startcolumn]=true;

    for(int d=0;d<direction.length;d++)
    {
      int x=startrow+direction[d][0];
      int y=startcolumn+direction[d][1];
      if(isValid(x,y,path,isdone))
      {
       ArrayList<String> calls=floodFilldiag_block(x,y,endrow,endcolumn,path,isdone);
       for(String s:calls)
         ans.add(directionName[d] + s);
      }
    }

    isdone[startrow][startcolumn]=false;
    return ans;
  }

  public static boolean isValid1(int x,int y,boolean[][] isdone)
  {
    if(x>=0 && y>=0 && x<isdone.length && y<isdone[0].length && !isdone[x][y]) 
      return true;
    else  
      return false;
  } 

  public static ArrayList<String> floodFill_Knight(int startrow,int startcolumn,int endrow,int endcolumn,boolean[][] isdone) 
  {
    if(startrow==endrow && startcolumn==endcolumn)
    {
      ArrayList<String> base=new ArrayList<>();
      base.add("");
      return base;
    }

    int[][] dirHorse={{1,2},{2,1},{-1,2},{-2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};
    String[] dirHorseName={"UR","RU","UL","LU","RD","DR","DL","LD"};

    ArrayList<String> ans= new ArrayList<>();

    isdone[startrow][startcolumn]=true;

    for(int d=0;d<dirHorse.length;d++)
    {
      int x=startrow+dirHorse[d][0];
      int y=startcolumn+dirHorse[d][1];
      if(isValid1(x,y,isdone))
      {
       ArrayList<String> calls=floodFill_Knight(x,y,endrow,endcolumn,isdone);
       for(String s:calls)
         ans.add(dirHorseName[d] + s);
      }
    }

    isdone[startrow][startcolumn]=false;
    return ans;
  }

  public static int KnightPath(int startrow,int startcolumn,int endrow,int endcolumn,boolean[][] isdone) 
  {
    if(startrow==endrow && startcolumn==endcolumn)
      return 1;

    int[][] dirHorse={{1,2},{2,1},{-1,2},{-2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};

    int count=0;
    isdone[startrow][startcolumn]=true;

    for(int d=0;d<dirHorse.length;d++)
    {
      int x=startrow+dirHorse[d][0];
      int y=startcolumn+dirHorse[d][1];
      if(isValid1(x,y,isdone))
       count+=KnightPath(x,y,endrow,endcolumn,isdone);
    }

    isdone[startrow][startcolumn]=false;
    return count;
  }

  public static boolean KnightTour(int startrow,int startcolumn,int count,int boxSize,boolean[][] isdone,int[][] ans) 
  {
    isdone[startrow][startcolumn]=true;
    ans[startrow][startcolumn]=count;

    if(count==boxSize-1)
    {
      for(int[] ar:ans)
      {
        for(int element: ar)
          System.out.print(element+" ");
        System.out.println(); 
      }
      return true;
    }

    int[][] dirHorse={{1,2},{2,1},{-1,2},{-2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};
    boolean res=false;

    for(int d=0; d < dirHorse.length && !res; d++)  // ! "&& !res" it is used to break the statement when the result is obtained, it could be done by break statement. 
    {
      int x=startrow+dirHorse[d][0];
      int y=startcolumn+dirHorse[d][1];
      if(isValid1(x,y,isdone)){
        res=res || KnightTour(x,y,count+1,boxSize,isdone,ans);}
    }

    isdone[startrow][startcolumn]=false;
    ans[startrow][startcolumn]=0;
    return res;
  }
}

