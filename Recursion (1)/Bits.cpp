// ? In this Question, There are "n numbers" which are repeated by "k times" and "1 number" wil be repeated only "one time".  
// ! Example -> 2,3,5,10 are repeated by 3 times and 14 is repeated only once.
 
#include <iostream>
#include <vector>
#include <string>
#include <climits>
using namespace std;

int uniqueInK(vector<int> &arr,int k)
{
  vector<int> bits(32,0);
  for(int ele:arr)
  {
    for(int i=0;i<32;i++)
    {
      int mask=(1<<i);
      if((ele & mask)!=0){  // ! Checking On condition 
        bits[i]++;
        }

    }
  }

  int ans=0;

  for(int ele: bits)
    cout<<ele<<" ";

  for(int i=0;i<32;i++)
  {
    if(bits[i] % k !=0)
     ans|=(1<<i);
  }

  return ans;
} 

void solve()
{
  vector<int> arr={2,3,2,3,4,2,3};  
  uniqueInK(arr,3);
}

int main(int args,char** argv)
{
  solve();
  return 0;
}