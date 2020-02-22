// ? In these Questions, We are doing 1-D Array Problems.
// ! In these Questions, "vidx" means "vector index". Where, "idx" means "index". 

#include<iostream>
#include<vector>
using namespace std;

void display(vector<int> &arr,int vidx)
{
    if(vidx==arr.size())
      return;
    cout<<arr[vidx]<<" ";
    display(arr,vidx+1);  
}
bool findData(vector<int> &arr,int vidx,int data)
{ 
  if(vidx==arr.size())
    return false;
  if(arr[vidx]==data)
    return true;
  findData(arr, vidx+1,data);    
}
int findMax(vector<int> &arr,int vidx)
{
  if(vidx==arr.size()-1)
    return arr[vidx];
  int recAns=findMax(arr,vidx+1);
  if(recAns>arr[vidx])
    return recAns;
  else
    return arr[vidx];   
}
int findMin(vector<int> &arr,int vidx)
{
  if(vidx==arr.size()-1)
    return arr[vidx];
  int recAns=findMin(arr,vidx+1);
  if(recAns<arr[vidx])
    return recAns;
  else
    return arr[vidx];  
}
int lastIndex(vector<int> &arr,int vidx,int data)
{
  if(vidx==arr.size()-1)
    return -1;
  int recAns=lastIndex(arr,vidx+1,data);
  if(recAns!=-1)
    return recAns;
  else
    return (arr[vidx]==data?vidx:-1);      
}
vector<int> allIndex(vector<int> &arr,int vidx,int data,int size)
{
  if(vidx==arr.size())
  {
     vector<int> baseArray(size,0);
     return baseArray; 
  }
  if(arr[vidx]==data)
    size++;

  vector<int> recAns=allIndex(arr,vidx+1,data,size);

  if(arr[vidx]==data)
    recAns[size-1]=vidx;

  return recAns;    
}

void solve()
{
  vector<int> arr={10,6,8,10,4,5,5,6,8,-3,2,12,8,3};
  display(arr,0);
  // TODO:: "boolalpha means it changes the output value of 0 & 1 to true & false as string in output."
  cout<<endl<<boolalpha<<findData(arr,0,12);
  cout<<endl<<"Last Index of the data: "<<lastIndex(arr,0,6);
  cout<<endl<<"Maximum element of the data: "<<findMax(arr,0);
  cout<<endl<<"Minimum element of the data: "<<findMin(arr,0);
  //cout<<endl<<"All Index of the data: "<<allIndex(arr,0,12,14);
}

int main(int args,char **argv)
{
  solve();
  return 0;
}