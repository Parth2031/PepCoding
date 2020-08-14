// ^ Print {0,1,0,0,1,1,1,0,1,0,1} as {0,0,0,0,0,1,1,1,1,1,1} using Quick Sort.
// ? Quick Sort
// ! li --> Left Index, ri --> Right Index

import java.util.Scanner;

public class QuickSort
{
  public static Scanner scn = new Scanner(System.in);

  public static void display(int[] ans) 
  {
    for(int i = 0; i < ans.length; i++)
       System.out.print(ans[i]+" ");

    System.out.println();   
  }

  public static void input(int[] arr) 
  {
    for (int i = 0; i < arr.length; i++) 
        arr[i] = scn.nextInt();
  }

  public static void swap(int[] arr, int ptr,int itr) 
  {
    int temp = arr[ptr];
    arr[ptr] = arr[itr];
    arr[itr] = temp;
  }

//   public static void sort0_1(int[] arr)
//   {
//     int ptr=0;
//     int itr=0;
//     while(itr<arr.length)
//     {
//       if(arr[itr]==0)
//       {
//         swap(arr,ptr,itr);
//         ptr++;  
//       }
//       itr++;
//     }

//     display(arr);
//   }

//   public static void sort0_1_2(int[] arr)
//   { 
//     int ptr=0;
//     int ptr1=arr.length - 1;
//     int itr=0;
//     while(itr<arr.length)
//     {
//       if(arr[itr] == 0)
//       {
//         swap(arr,ptr,itr);
//         ptr++;  
//       }
//       else if(arr[itr] == 2)
//       {
//         swap(arr,ptr1,itr);
//         ptr1--;
//       }
//       itr++;
//     }

//     display(arr);
//   }

  public static void pivot(int[] arr,int li,int ri,int pivot)
  {
    int ptr=li;
    int itr=li;
    while(itr<=ri)
    {
      if(arr[itr] <= pivot)
      {
        swap(arr,ptr,itr);
        ptr++;  
      }
      itr++;
    }

    display(arr);
  }


  public static int[] QuickedSort(int[] arr,int left,int right)
  {
     

    return arr;        
  }

  public static void main(String[] args) 
  {
    System.out.print("Enter the size of Array: ");  
    int n=scn.nextInt();
    int[] arr = new int[n];
    input(arr);
    // int[] ans = QuickedSort(arr,0,n-1);
    // display(ans);
    // display(pivot(arr,0,n-1,11));
    // pivot(arr,0,n-1,11);
    // sort0_1(arr);
    // sort0_1_2(arr);
  }
}