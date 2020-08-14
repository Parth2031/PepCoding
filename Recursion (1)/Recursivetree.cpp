#include<iostream>
#include<vector>
using namespace std;

int calls(int n)
{
  if(n<=1)
  {
    cout<<"Base:"<<n<<endl;;
    return n+1;
  }

  int count=0;
  cout<<"Pre:"<<n<<endl;
  count+=calls(n-1);

  cout<<"In:"<<n<<endl;;

  count+=calls(n-2);
  cout<<"Post:"<<n<<endl;

  return count+3;
}

int calls_01(int n,int level)
{
  if(n<=1)
  {
    cout<<"Base: "<<n<<" @ "<<level<<endl;
    return n+1;
  }

  int count=0;
  cout<<"Pre: "<<n<<" @ "<<level<<endl;
  count+=calls_01(n-1,level+1);

  cout<<"In 1: "<<n<<" @ "<<level<<endl;
  count+=calls_01(n-2,level+1);

  cout<<"In 2: "<<n<<" @ "<<level<<endl;

  count+=calls_01(n-3,level+1);
  cout<<"Post: "<<n<<" @ "<<level<<endl;

  return count+3;
}

int main(int args,char **argv)
{
  int n,level;
  cout<<"Enter a number:";
  cin>>n;
  cout<<"Enter the level:";
  cin>>level;
  int count1=calls(n);
  cout<<"Count of 1st tree : "<<count1<<endl;
  int count2=calls_01(n,level); 
  cout<<"Count of 2nd tree : "<<count2<<endl;
  return 0;
}