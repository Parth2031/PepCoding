// ^ In this Question, we are printing power of a number and betpow is increasing the range of writing power of number.

import java.util.Scanner;

public class Rec_pow 
{
  public static Scanner scn = new Scanner(System.in);
  public static void main(String[] args) 
  {
    int a, b;
    a = scn.nextInt();
    b = scn.nextInt();
    //System.out.println(pow(a, b));
    System.out.println(betpow(a, b));
  }
  public static int pow(int a, int b) 
  {
    if (b == 0)
      return 1;
    return (a * pow(a, b - 1));
  }

 // ! Since, Calling Stack limit is upto 65000 integer value and if exceeds it gives error of Stack Overflow.
 // & So, if we want to find 2^100 but calling stack limit will not allow so we use better(bet) power which uses b=b/2*b/2.

  public static int betpow(int a, int b) 
  {
    if (b == 0)
      return 1;
    int res=pow(a,b/2);
    if(b%2!=0)
      return (a*res*res);
    return(res*res);
  }
}