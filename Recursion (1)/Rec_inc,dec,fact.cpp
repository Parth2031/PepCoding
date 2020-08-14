// ^ In this Question, we are increasing and decresing the number from start to end and also printing factorial.
// ? In this Question, we need to print the cases in which from step to target like 0 to 3 gives 25 cases. 

#include <iostream>
using namespace std;

void printinc(int st, int end)
{
  if (st == end + 1)
      return;
  else
  {
      cout << st << endl;
      printinc(st + 1, end);
  }
}

int printincr(int start,int end)
{
  if (start == end)
  {  
    cout<<start<<endl;  
    return start-1;
  } 
 
  cout << start << endl;
  printincr(start + 1, end);
}

int fact(int n)
{
  if(n<=1) return 1;
  
  return(n*fact(n-1));
}

void printdec(int st, int end)
{
  if (st == end + 1)
    return;
  else
  {
    printdec(st + 1, end);
    cout << st << endl;
  }
}

int printdecr(int start,int end)
{ 
  if (start == end)
  {   
    cout<<start<<endl;  
    return start-1;  
  } 

  printdecr(start + 1, end);
  cout<<start<<endl; 
}

int noOfSteps(int step,int target)
{
  int count=0;
  if(step<=target)
  {
    count+=noOfSteps(step+1,target);
    count+=noOfSteps(step+2,target);
    count+=noOfSteps(step+3,target);  
  }

  return count+1;
}


int main(int args, char **argv)
{
  //int st, end;
  //cin >> st >> end;
  //printinc(st, end);
  //printdec(st, end);
  //cout<<fact(10);
  //printincr(st,end);
  //printdecr(st,end);
  cout<<"Total Number of cases to reach from step/jump to target: "<<noOfSteps(0,3);
}