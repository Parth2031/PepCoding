// TODO:: Selection Sort and Bubble Sort works on time complexity of O(n^2) but in this program we have done in O(n)

import java.util.Scanner;
public class Selection_Sort 
{
  public static Scanner scn = new Scanner(System.in);

  public static void main(String[] args) 
  {
    int[] arr = new int[10];
    input(arr);
    sort(arr);
    display(arr);
  }

  public static void display(int[] arr) 
  {
    for (int i = 0; i < arr.length; i++) 
      System.out.print(arr[i] + ", ");
  }

  public static void input(int[] arr) 
  {
    for (int i = 0; i < arr.length; i++) 
      arr[i] = scn.nextInt();
  }

  public static void swap(int[] arr, int j, int i) 
  {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static void  sort(int[] arr) 
  {
    for (int i = 0; i < arr.length; i++) 
    {
      for (int j = i; j < arr.length; j++) 
      {
        if (arr[i] > arr[j]) 
          swap(arr, j, i);
        else
         continue;
      }
    }
  }
}