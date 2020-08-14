// ^ In this Question-1, we need to remove all "hi" and print the remaining string back.
// & In this Question-2, we need to remove all duplicates in "aaaabbbcccddd" and print back "abcd".
// ~ In this Question-3, we need to remove all "hi" except "hit". 
// ? In this Question-4, we need to count all the number of alphabets used like "aaaabbbccde" and print back "a4b3c2de".
// * In this Question-5, we need to print a string from "abc" to [ , c , b , bc , ab , ac , abc].  
// ! In these Questions, "idx" means "index" and "ques" means "question". 

import java.util.Scanner;
import java.util.ArrayList;

public class Rec_Repeat
{
  public static Scanner scn = new Scanner(System.in);
  public static void main(String[] args) 
  { 
    String str=scn.nextLine();
    //System.out.println(removeHi(str));
    //System.out.println(removeDuplicates(str));
    //System.out.println(removeHibutnotHit(str));
    //System.out.println(compression(str,1,1));
    String Str="ABCD"; 
    System.out.println(subsequence(Str));
  }

  public static String removeHi(String ques)
  {
    if(ques.length()==0)
      return "";

    // if(ques.length()>1 && ques.charAt(0)=='h' && ques.charAt(0)=='i')
    if(ques.length()>1 && ques.substring(0,2).equals("hi"))
       return removeHi(ques.substring(2));
    else
    {
      char ch=ques.charAt(0);
      return ch+removeHi(ques.substring(1));
    }          
  }

  public static String removeHibutnotHit(String word)
  {
    if(word.length()==0)
      return "";

    if(word.length()>=2 && word.substring(0,2).equals("hi"))
    {
      if(word.length()>=3 && word.substring(0,3).equals("hit"))
        return "hit"+removeHibutnotHit(word.substring(3));
      else
        return removeHibutnotHit(word.substring(2));
    } 
    else
    {
      char ch=word.charAt(0);
      return ch+removeHi(word.substring(1));
    }
  } 

  public static String removeDuplicates(String dup)
  {
    if(dup.length()==1)
      return dup;

    char ch=dup.charAt(0);
    String recAns=removeDuplicates(dup.substring(1));
   
    if(ch==recAns.charAt(0)) 
      return recAns;
    else  
      return ch+recAns;  
  }

  public static String compression(String charcount,int idx,int count)
  {
    if(idx==charcount.length())
    {
      String ans = charcount.charAt(idx-1) + (count>1?count+"":"");
      return ans;
    }
    
    if(charcount.charAt(idx-1)==charcount.charAt(idx))
      return compression(charcount,idx+1,count+1);
    else
    {
      String ans=charcount.charAt(idx-1) + (count>1?count+"":"");
      return ans + compression(charcount,idx+1,1);
    }  
  }

  public static ArrayList<String> subsequence(String str)
  {
    if(str.length()==0)
    {
      ArrayList<String> base=new ArrayList<>();
      base.add(" ");
      return base;
    }    
    
    char ch=str.charAt(0);
    String ros=str.substring(1);

    ArrayList<String> recAns=subsequence(ros);

    ArrayList<String> myAns=new ArrayList<>();
    // ! This gives the addition of rest of the 'BCD' 
    myAns.addAll(recAns);

    // ! This for loop helps in adding 'A' to 'BCD' from the above recursion

    // ^ For Loop is a forEach loop & its Syntax: --> for(type variable:---){------} 
    // * ALternative: for(i=;i<recAns.length();i++)
    
    for(String s:recAns){
      myAns.add(ch+s);
    }

    return myAns;
  }
}