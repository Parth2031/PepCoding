#include<iostream>
#include<vector>
using namespace std;

void input(vector<int> &arr)
{
  cout<<"Enter the Array: ";  
  for(int i=0;i<arr.size();i++)
    cin>>arr[i];

  cout<<endl;  
}

void display(vector<int> &ans)
{
  cout<<"Merge Sorted Array: "<<endl;  
  for(int i:ans)
    cout<<i<<" ";
  cout<<endl;
}

vector<int> Merge(vector<int> &arr1,vector<int> &arr2)
{
  int arr3_s=arr1.size()+arr2.size();
  int i=0,j=0,k=0; 
  vector<int> arr3(arr3_s,0); 
  while(i<arr1.size() || j<arr2.size())
  {
    if(i<arr1.size() && j<arr2.size())
    {
      if(arr1[i]<arr2[j])
      {
        arr3[k]=arr1[i];
        i++;
      }
      else
      {
        arr3[k]=arr2[j];
        j++;  
      }
      k++;
    }  
    else if(i<arr1.size())
    {
      arr3[k]=arr1[i];
      i++;
      k++;
    }
    else if(j<arr2.size())
    {
      arr3[k]=arr2[j];
      j++;
      k++;
    }
    else
    {
      continue; 
    }
  }

  return arr3;
}  

vector<int> MergeSort(vector<int> &arr,int left,int right)
{
  if(left == right)
  {
    vector<int> ans(1,0);
    ans[0]=arr[left];
    return ans;  
  }

  //  int mid = (left+right)/2;  
   int mid = (left+right)>>1;      // ! Using Bit Mainpulation

   vector<int> Left = MergeSort(arr,left,mid);
   vector<int> Right = MergeSort(arr,mid+1,right);   

   return Merge(Left,Right);
}

int main(int args, char** argv)
{
  int n,m;
  cout<<"Enter the size of array: "; 
  cin>>n;
  int left=0,right;
  right=n-1;
  vector<int> arr(n,0);
  input(arr);
  vector<int> ans = MergeSort(arr,left,right);
  display(ans);
  return 0;
}

// Self Code -->

// void input(vector<int> &arr1,vector<int> &arr2)
// {
//   cout<<"Enter the 1st Array: ";  
//   for(int i=0;i<arr1.size();i++)
//     cin>>arr1[i];

//   cout<<"Enter the 2nd Array: ";
//   for(int i=0;i<arr2.size();i++)
//     cin>>arr2[i];  

//   cout<<endl;  
// }


// void display(vector<int> &ans)
// {
//   cout<<"Merge Sorted Array: "<<endl;  
//   for(int i:ans)
//     cout<<i<<" ";
//   cout<<endl;
// }


// void Merge(vector<int> &arr1,vector<int> &arr2)
// {
//    int arr3_s=arr1.size()+arr2.size();
//    int i=0,j=0,k=0; 
//    vector<int> arr3(arr3_s,0); 
//    while(i<arr1.size() || j<arr2.size())
//    {
//      if(i<arr1.size() && j<arr2.size())
//      {
//        if(arr1[i]<arr2[j])
//        {
//          arr3[k]=arr1[i];
//          i++;
//        }
//        else
//        {
//          arr3[k]=arr2[j];
//          j++;  
//        }
//        k++;
//      }  
//      else if(i<arr1.size())
//      {
//        arr3[k]=arr1[i];
//        i++;
//        k++;
//      }
//      else if(j<arr2.size())
//      {
//        arr3[k]=arr2[j];
//        j++;
//        k++;
//      }
//      else
//      {
//        continue; 
//      }
//    }

//    display(arr3);
// }

// int main(int args, char** argv)
// {
//   int n,m;
//   cout<<"Enter the size of 1st array: "; 
//   cin>>n;
//   cout<<"Enter the size of 2nd array: "; 
//   cin>>m;
//   vector<int> arr1(n,0),arr2(m,0);
//   input(arr1,arr2);
//   Merge(arr1,arr2);
//   return 0;
// }