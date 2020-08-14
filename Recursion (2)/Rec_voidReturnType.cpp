// ! Rec_Repeat, Recursion_Rat & Recursion_2D questions are done without return type.
// ? Board Path which uses the concept of Dice.
// !  Some new Questions on Permutaion like coinChange,Set questions,etc.
// ? In Coin Change Questions, let there are coins->{2,3,5,7} and using permutation & combination by consider all possible cases, we need to get target-->10.
// TODO:: N-Queen Problems and Set Problems

#include <iostream>
#include <vector>
#include <string>
#include <climits>
using namespace std;

// ^ ============================================================================================================================

void subseq(string ques, string ans)
{
  if (ques.length() == 0)
  {
    cout << ans << " ";
    return;
  }
  char ch = ques[0];
  string roq = ques.substr(1);

  subseq(roq, ans + ch);
  subseq(roq, ans);
}

// ! One Confusing case possible --> If we take 10110, it will consider 10,1,0 as 3 different posible cases and 01 is not possible.
string words[] = {".","abc","def","ghi","jkl","mno","pqrs","tuv","wx","yz","@$&","#%"}; 

int keyPad(string str,string ans)
{
  if(str.length()==0)
  {
    cout<<ans<<endl;
    return 1;      
  }

  int count = 0;
  int idx1 = str[0]-'0';
  string word = words[idx1];

  //if(idx == 0)
  //{
  //  count += keyPad(str.substr(1),ans);
  //  return;
  //}

  for(int i=0;i<word.length();i++)  
    count += keyPad(str.substr(1),ans + word[i]);  

  if(str.length() >= 2)
  {
    int idx = idx1*10 + (str[1]-'0');
    if(idx >= 10 && idx < 12)
    {
      word = words[idx];
      
      for(int i=0;i<word.length();i++)  
        count+= keyPad(str.substr(2),ans + word[i]);  
    }
  }

  return count;
}

void removeHi(string ques, string ans)
{
  if (ques.length() == 0)
  {
    cout << ans;
    return;
  }

  if (ques.length() > 1 && ques[0] == 'h' && ques[1] == 'i')
    removeHi(ques.substr(2), ans);
  else
    removeHi(ques.substr(1), ans + ques[0]);
}

void removeDupli(string ques, string ans)
{
  if (ques.length() == 0)
  {
    cout << ans;
    return;
  }

  if (ques[0] == ans[ans.length() - 1])
    removeDupli(ques.substr(1), ans);
  else
    removeDupli(ques.substr(1), ans + ques[0]);
}

void compression(string ques, string ans, int count)
{
  if (ques.length() == 0)
  {
    cout << ans;
    return;
  }

  char ch = ques[0];
  string roq = ques.substr(1);
  if (roq.length() != 0)
  {
    if (ch == roq[0])
      compression(roq, ans, count + 1);
    else
    {
      if (count > 1)
        compression(roq, ans + ch + to_string(count), 1);
      else
        compression(roq, ans + ch, 1);
    }
  }
  else
  {
    if (count > 1)
      compression(roq, ans + ch + to_string(count), 1);
    else
      compression(roq, ans + ch, 1);
  }
}

void basicQues()
{
  // subseq("abc", "");
  cout<<"Count: "<<keyPad("10110", "")<<endl;
  // removeHi("aaahihiaa","");
  // removeHi("hhhhihihihihiiiiihihiihhihih","");
  // compression("aaaabbbccd","",0);
  // string str;
  // cin >> str;
  // removeDupli(str, "");
}

// ^ ============================================================================================================================

int mazePathSimple(int sr, int sc, int er, int ec, string ans)
{
  if (sr == er && sc == ec)
  {
    cout << ans << endl;
    return 1;
  }

  // if(sr>er || sc>ec)
  // {
  //   cout<<"hi"<<endl;
  //   return 0;
  // }

  int count = 0;
  if (sr + 1 <= er)
    count += mazePathSimple(sr + 1, sc, er, ec, ans + "V");
  if (sc + 1 <= ec)
    count += mazePathSimple(sr, sc + 1, er, ec, ans + "H");

  return count;
}

int mazePathMultiMove(int sr, int sc, int er, int ec, string ans)
{    
  if(sr==er && sc==ec)
  {
    cout << ans << endl;
    return 1;
  }

  int count = 0;

  //for(int i=1;i<er+1;i++)
  for(int i=1 ; i+sr <= er; i++)
  {
   if (sr + i <= er)
     count += mazePathMultiMove(sr + i, sc, er, ec, ans + "V"+to_string(i));
  }

  //for(int i=1;i<ec+1;i++)
  for(int i=1;i+sc <= ec;i++)
  { 
   if (sc + i <= ec)
     count += mazePathMultiMove(sr, sc + i, er, ec, ans + "H"+to_string(i));
  }

  //for(int i=1;i<er+1 && i<ec+1;i++)
  for(int i=1; i+sr<=er && i+sc<=ec; i++)
  { 
   if (sr +i<=er && sc + i <= ec)
     count += mazePathMultiMove(sr + i, sc + i, er, ec, ans + "D"+to_string(i));
  }   
  
  return count;
}

int boardPath(int source, int destination,string ans)
{
  if(source==destination)
  {
    cout << ans << endl;
    return 1;
  }

  int count=0;

  for(int dice=1; dice<=6 && source+dice<=destination; dice++)
  {
    count += boardPath(source+dice, destination, ans + to_string(dice));
  }

  return count;
}

void pathTypeQues()
{
  //cout<<mazePathSimple(0, 0, 2, 2, "")<<endl;
  //cout<<mazePathMultiMove(0, 0, 2, 2, "")<<endl;
  cout<<boardPath(0,10,"")<<endl;
}

// ^ ============================================================================================================================

int Permutation_with_Repetition(string ques, string ans)
{
  if(ques.length()==0)
  {
    cout<<ans<<endl;
    return 1;
  }
  
  int count=0;
  for(int i=0;i<ques.length();i++)
  {
    char ch=ques[i];
    string roq = ques.substr(0,i) + ques.substr(i+1);
    count += Permutation_with_Repetition(roq,ans+ch);
  }

  return count;
}

int Permutation_without_Repetition(string ques, string ans)
{
  if(ques.length() == 0)
  {
    cout<<ans<<endl;
    return 1;
  }
  
  int count = 0;
  // vector<bool> mapping(26,false);   // ! It's time complexity is 26 * str.length() * 4 bytes of integer/bool which is quite high.
  int mapping = 0;                         // TODO:: <--- So, Using Bit manipulation where 1 int = 4 byte = 32 bits.   

  for(int i=0;i<ques.length();i++)
  {
    char ch = ques[i];
    int num = ch-'a';
    int mask = (1<<num);

    //if(mapping[num] == false)
    if((mapping & (mask)) == 0)
    {
      //mapping[num]=true;
      mapping |= mask;                        // ? In this we used "OR operation" to ON/true the condition by bit concept. 
      string roq = ques.substr(0,i) + ques.substr(i+1);
      count += Permutation_without_Repetition(roq,ans+ch);
    } 
  }

  return count;
}

// ! Infinite Coins Question -->

int coinChange_Permutation_1(vector<int> & arr,int vidx,int target,string ans)
{
  if(target==0)
  {
    cout<<ans<<" ";
    return 1;
  } 

  int count=0;

  for(int i=0;i<arr.size();i++)
  {
    if(target-arr[i]>=0)
      count+=coinChange_Permutation_1(arr,vidx,target-arr[i],ans+to_string(arr[i]));
  }

  return count;
}

int coinChange_Combination_1(vector<int> & arr,int vidx,int target,string ans)
{
  if(target==0)
  {
    cout<<ans<<" ";
    return 1;
  } 

  int count=0;

  for(int i=vidx;i<arr.size();i++)
  {
    if(target-arr[i]>=0)
      count+=coinChange_Combination_1(arr,i,target-arr[i],ans+to_string(arr[i]));
  }

  return count;
}

// ! Single coin (Using Coin Without Repetition) Used -->

int coinChange_Permutation_2(vector<int> & arr,vector<bool> check,int target,string ans)
{
  if(target==0)
  {
    cout<<ans<<" ";
    return 1;
  } 

  int count=0;

  for(int i=0;i<arr.size();i++)
  {
    if(target-arr[i]>=0 && !check[i])
    {
      check[i]=true;
      count+=coinChange_Permutation_2(arr,check,target-arr[i],ans+to_string(arr[i]));
      check[i]=false;  
    } 
  }

  return count;
}

int coinChange_Combination_2(vector<int> & arr,int vidx,int target,string ans)
{
  if(target==0)
  {
    cout<<ans<<" ";
    return 1;
  } 

  int count=0;

  for(int i=vidx;i<arr.size();i++)
  {
    if(target-arr[i]>=0)
      count+=coinChange_Combination_2(arr,i+1,target-arr[i],ans+to_string(arr[i]));
  }

  return count;
}

// ! Infinite Coin Used with Subsequence -->

int coinChange_Permutation_3(vector<int> & arr,int vidx,int target,string ans)
{
  if(vidx==arr.size() || (target==0 && ans.length()!=0))  
  {
    if (target==0 && ans.length()!=0)
    {
     cout<<ans<<" ";
     return 1;
    }
    return 0; 
  }

  int count=0;

  if(target-arr[vidx]>=0)
    count+=coinChange_Permutation_3(arr,0,target-arr[vidx],ans+to_string(arr[vidx]));

  count+=coinChange_Permutation_3(arr,vidx+1,target,ans);    

  return count;
}

int coinChange_Combination_3(vector<int> & arr,int vidx,int target,string ans)
{
  if(vidx==arr.size() || (target==0 && ans.length()!=0))
  {
    if (target==0 && ans.length()!=0)
    {
     cout<<ans<<" ";
     return 1;
    }
    return 0; 
  } 

  int count=0;

  if(target-arr[vidx]>=0)
    count+=coinChange_Combination_3(arr,vidx,target-arr[vidx],ans+to_string(arr[vidx]));

  count+=coinChange_Combination_3(arr,vidx+1,target,ans); 

  return count;
}

// ! Single Coin (Using Coin Without Repetition) Used with Subsequence -->

int coinChange_Combination_4(vector<int> & arr,int vidx,int target,string ans)
{
  if(vidx==arr.size() || (target==0 && ans.length()!=0))
  {
    if (target==0 && ans.length()!=0)
    {
     cout<<ans<<" ";
     return 1;
    }
    return 0; 
  } 

  int count=0;

  if(target-arr[vidx]>=0)
    count+=coinChange_Combination_4(arr,vidx+1,target-arr[vidx],ans+to_string(arr[vidx]));

  count+=coinChange_Combination_4(arr,vidx+1,target,ans); 

  return count;
}

int coinChange_Alternative_Combination_4(vector<int> arr,vector<bool> check,int vidx,int target,string ans)
{
   if(target==0 || vidx==arr.size())
   {
     if(target==0)
     {
       cout<<ans<<" ";
       return 1;
     }
     return 0;
   }

   int count = 0;

   if(target-arr[vidx]>=0 && !check[vidx])
   {
     check[vidx]=true;
     count+=coinChange_Alternative_Combination_4(arr,check,vidx+1,target-arr[vidx],ans+to_string(arr[vidx]));
     check[vidx]=false; 
   }

    count+=coinChange_Alternative_Combination_4(arr,check,vidx+1,target,ans);

   return count;
}

void perm_comb_Ques()
{
  // cout<<"Count: "<<Permutation_with_Repetition("abc","")<<endl;
  // cout<<"Count: "<<Permutation_without_Repetition("aba","")<<endl;
  // vector<int> arr={2,3,5,7};
  // cout<<endl<<"Count: "<<coinChange_Permutation_1(arr,0,10,"")<<endl;
  // cout<<endl<<"Count: "<<coinChange_Combination_1(arr,0,10,"")<<endl;
  // vector<bool> check(4,false);     // ! Alternative: --> vector<bool> check={false,false,false,false};
  // cout<<endl<<"Count: "<<coinChange_Permutation_2(arr,check,10,"")<<endl;
  // cout<<endl<<"Count: "<<coinChange_Combination_2(arr,0,10,"")<<endl;
  // cout<<endl<<"Count: "<<coinChange_Permutation_3(arr,0,10,"")<<endl;  // ! Based on target choosing the numbers in Permutation (With Repetetion)
  // cout<<endl<<"Count: "<<coinChange_Combination_3(arr,0,10,"")<<endl;  // ! Based on target choosing the numbers in Combination  ("")
  // cout<<endl<<"Count: "<<coinChange_Combination_4(arr,0,10,"")<<endl;  // ! Based on target choosing the numbers in Combination (Without Repetetion)
  // cout<<endl<<"Count: "<<coinChange_Alternative_Combination_4(arr,check,0,10,"")<<endl; 
} 

// ^ ====================================================================================================================================

// ? Some abbreviations full form-->>>
// ! tnq---> Total No. of Queens
// ! qloc--> Previous Location of Queen
// ! qnpsf--> Queen Position So Far
// ! loc--> Location 
// ! dir--> Direction
// ! r--> Row && c--> Column

int N_QueenPermutation(int boxes,int tnq,int qnpsf,vector<bool> loc,string ans)
{
  if(qnpsf==tnq)
  {
    cout<<ans<<endl;
    return 1;
  }
  
  int count=0;

  for(int i = 0; i <= boxes; i++)
  {
    if(!loc[i])
    {
      loc[i]=true;
      count+=N_QueenPermutation(boxes,tnq,qnpsf+1,loc,ans+"B"+to_string(i)+"Q"+to_string(qnpsf)+" ");
      loc[i]=false;
    }
  } 

  return count; 
}

int N_QueenCombination(int boxes,int tnq,int qloc,int qnpsf,string ans)
{
  //if(qnpsf==tnq || qloc>=boxes)
  //{
    if(qnpsf==tnq)
    {
      cout<<ans<<"\n";
      return 1;
    }
    //return 0;  
  //} 

  int count=0;

  for(int i = qloc + 1; i <= boxes; i++)
    count+=N_QueenCombination(boxes,tnq,i,qnpsf+1,ans+"B"+to_string(i)+"Q"+to_string(qnpsf)+" ");

  return count;
}

int N_QueenSubSequence_Permutation(int boxes,int tnq,int qloc,vector<bool> &loc,int qnpsf,string ans)
{
  if(qnpsf==tnq || qloc > boxes)
  {
   if(qnpsf==tnq)
    {
      cout<<ans<<"\n";
      return 1;
    }
    return 0;  
  }

  int count=0;
  
  if(!loc[qloc+1])
  {
    loc[qloc+1]=true;
    count+=N_QueenSubSequence_Permutation(boxes,tnq,0,loc,qnpsf+1,ans+"B"+to_string(qloc+1)+"Q"+to_string(qnpsf)+" ");
    loc[qloc+1]=false;
  }

  count+=N_QueenSubSequence_Permutation(boxes,tnq,qloc+1,loc,qnpsf,ans);
  
  return count;     
}

int N_QueenSubSequence_Combination(int boxes,int tnq,int qloc,int qnpsf,string ans)
{
  if(qnpsf==tnq || qloc == boxes)
  {
   if(qnpsf==tnq)
    {
      cout<<ans<<endl;
      return 1;
    }
    return 0;  
  }

  int count=0;

  count+=N_QueenSubSequence_Combination(boxes,tnq,qloc+1,qnpsf+1,ans+"B"+to_string(qloc+1)+"Q"+to_string(qnpsf)+" ");
  count+=N_QueenSubSequence_Combination(boxes,tnq,qloc+1,qnpsf,ans);

  return count;
}

int N_Queen_Combination_2D(vector<vector<bool>> &boxes,int tnq,int qloc,int qnpsf,string ans)
{
  if(qnpsf==tnq)
  {
    cout<<ans<<endl;
    return 1;
  }

  int count=0;

  for(int i = qloc; i < (boxes.size() * boxes[0].size()); i++)
  {
    int x = i / boxes.size();
    int y = i % boxes.size();

    count+=N_Queen_Combination_2D(boxes,tnq,i+1,qnpsf+1, ans+ "(" + to_string(x) + "," + to_string(y) + ")" + " @ " + to_string(qnpsf) + " ");
  }

  return count;
}

int N_Queen_Permutation_2D(vector<vector<bool>> &boxes,int tnq,int qnpsf,string ans)
{
  if(tnq==qnpsf)
  {
    cout<<ans<<endl;
    return 1;
  }

  int count=0;

  for(int i=0;i<boxes.size()*boxes[0].size();i++)
  {
    int x = i / boxes.size();
    int y = i % boxes[0].size();

    if(!boxes[x][y])
    {
      boxes[x][y]=true;
      count+=N_Queen_Permutation_2D(boxes,tnq,qnpsf+1,ans+ "(" + to_string(x) + "," + to_string(y) + ")" + " @ " + to_string(qnpsf) + " ");
      boxes[x][y]=false;
    }
  }

  return count;
}

int N_Queen_Combination2D_Subsequence(vector<vector<bool>> &boxes,int tnq,int qloc,string ans)
{
  if(tnq==0 || qloc==(boxes.size() * boxes[0].size()))
  {
    if(tnq==0)
    {
      cout<<ans<<endl;
      return 1;
    }
    return 0;
  }

  int count=0;

  int x = qloc / boxes.size();
  int y = qloc % boxes[0].size(); 
  
  count+=N_Queen_Combination2D_Subsequence(boxes,tnq-1,qloc+1,ans+"("+to_string(x)+","+to_string(y)+")"+" ");

  count+=N_Queen_Combination2D_Subsequence(boxes,tnq,qloc+1,ans);  

  return count;
}

bool isSafeToPlace_Combination(vector<vector<bool>> &boxes,int x,int y)
{
  vector<vector<int>> dir = {{0,-1},{-1,-1},{-1,0},{-1,1}};
  
  for(int d=0;d<dir.size();d++)
  {
    for(int radius=1;radius<=max(boxes.size(),boxes[0].size());radius++)
    {
      int row = x + radius * dir[d][0];
      int col = y + radius * dir[d][1];

      if(row >= 0 && col >= 0 && row < boxes.size() && col < boxes[0].size() && boxes[row][col])
        return false;

      // ! Alternative If condition --> 
      
      // if(row < 0 || col < 0 || row >= boxes.size() || col >= boxes[0].size())
      //   break;

      // if(boxes[row][col])
      //   return false;    
    }  
  }
  return true;
}

int N_Queen_Combination2D_Main(vector<vector<bool>> &boxes,int tnq,int qloc,int qnpsf,string ans)
{
  if(qnpsf==tnq)
  {
    cout<<ans<<endl;
    return 1;
  }

  int count=0;

  for(int i = qloc; i < boxes.size() * boxes[0].size(); i++)
  {
    int x = i / boxes[0].size();
    int y = i % boxes[0].size();

    if(isSafeToPlace_Combination(boxes,x,y))
    {
      boxes[x][y]=true;
      count+=N_Queen_Combination2D_Main(boxes,tnq,i+1,qnpsf+1, ans+ "(" + to_string(x) + "," + to_string(y) + ")" + " @ " + to_string(qnpsf) + " ");
      boxes[x][y]=false; 
    }
  }

  return count;
}

bool isSafeToPlace_Permutation(vector<vector<bool>> &boxes,int x,int y)
{
  vector<vector<int>> direction={{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
  
  for(int i=0;i<direction.size();i++)
  {
    for(int rad=1;rad<=max(boxes.size(),boxes[0].size());rad++)
    {
      int row = x + rad * direction[i][0];
      int col = y + rad * direction[i][1];

      if(row<0 || col<0 || row>=boxes.size() || col>=boxes[0].size())
        break;

      if(boxes[row][col])
        return false;  
    }
  }
  return true;  
}

int N_Queen_Permutation2D_Main(vector<vector<bool>> &boxes,int tnq,int qnpsf,string ans)
{
  if(qnpsf==tnq)
  {
    cout<<ans<<endl;
    return 1;
  }

  int count=0;

  for(int i = 0; i < boxes.size() * boxes[0].size(); i++)
  {
    int x = i / boxes[0].size();
    int y = i % boxes[0].size();

    if(!boxes[x][y] && isSafeToPlace_Permutation(boxes,x,y))
    {
      boxes[x][y]=true;
      count+=N_Queen_Permutation2D_Main(boxes,tnq,qnpsf+1, ans+ "(" + to_string(x) + "," + to_string(y) + ")" + " @ " + to_string(qnpsf) + " ");
      boxes[x][y]=false; 
    }
  }

  return count;
}

int calls_bestcase = 0;

int N_Queen_BestCase(int row,vector<bool> &col,vector<bool> &diag,vector<bool> &adjdiag,int tnq, string ans)
{
  if(row==col.size() || tnq==0)
  {
    if(tnq==0)
    {
      cout << ans << endl;
      return 1;
    }
    return 0;
  }

  int count=0;
  calls_bestcase++;

  for(int c=0;c<col.size();c++)
  {
    if(!col[c] && !diag[row + c] && !adjdiag[row - c + col.size() - 1])
    {
      col[c] = true;
      diag[row + c] = true;
      adjdiag[row - c + col.size() - 1] = true;

      count += N_Queen_BestCase(row+1,col,diag,adjdiag,tnq-1,ans+"("+to_string(row)+" , "+to_string(c)+")"+" ");

      col[c] = false;
      diag[row + c] = false;
      adjdiag[row - c + col.size() - 1] = false;
    }
  }

return count;
}

int calls_bestcase = 0;

int N_Queen_BestCase(int row,vector<bool> &col,vector<bool> &diag,vector<bool> &adjdiag,int tnq, string ans)
{
  if(row==col.size() || tnq==0)
  {
    if(tnq==0)
    {
      cout << ans << endl;
      return 1;
    }
    return 0;
  }

  int count=0;
  calls_bestcase++;

  for(int c=0;c<col.size();c++)
  {
    if(!col[c] && !diag[row + c] && !adjdiag[row - c + col.size() - 1])
    {
      col[c] = true;
      diag[row + c] = true;
      adjdiag[row - c + col.size() - 1] = true;

      count += N_Queen_BestCase(row+1,col,diag,adjdiag,tnq-1,ans+"("+to_string(row)+" , "+to_string(c)+")"+" ");

      col[c] = false;
      diag[row + c] = false;
      adjdiag[row - c + col.size() - 1] = false;
    }
  }

return count;
}

int N_Queen_BestCase_Bit(int n,int row,int &col,int &diag,int &adjdiag,int tnq,string ans)
{
  if(row==n || tnq==0)
  {
    if(tnq==0)
    {
      cout << ans << endl;
      return 1;
    }
    return 0;
  }

  int count=0;
  calls_bestcase++;

  for(int c=0;c<n;c++)
  {
    int x = (1 << c);
    int y = (1 << (row + c));
    int z = (1 << (row - c + n -1));

    if(!(col & x) && !(diag & y) && !(adjdiag & z))
    {
      col ^= x;
      diag ^= y;
      adjdiag ^= z;

      count += N_Queen_BestCase_Bit(n,row+1,col,diag,adjdiag,tnq-1,ans+"("+to_string(row)+","+to_string(c)+")"+" ");

      col ^= x;
      diag ^= y;
      adjdiag ^= z;
    }
  }

return count;
}

void NqueenWaysQues()
{
  // vector<bool> check(8,false);
  // cout<<"Count: "<<N_QueenPermutation(7,3,0,check,"")<<endl;
  // cout<<"Count: "<<N_QueenCombination(7,3,-1,0,"")<<endl;
  // cout<<"Count: "<<N_QueenSubSequence_Permutation(7,3,-1,check,0,"")<<endl;
  // cout<<"Count: "<<N_QueenSubSequence_Combination(7,3,-1,0,"")<<endl;
  vector<vector<bool>> boxes(4, vector<bool>(4,false));
  // cout<<"Count: "<<N_Queen_Combination_2D(boxes,4,0,0,"")<<endl;
  // cout<<"Count: "<<N_Queen_Permutation_2D(boxes,4,0,"")<<endl;
  // cout<<"Count: "<<N_Queen_Combination2D_Subsequence(boxes,4,0,"")<<endl;
  // cout<<"Count: "<<N_Queen_Combination2D_Main(boxes,4,0,0,"")<<endl;
  // cout<<"Count: "<<N_Queen_Permutation2D_Main(boxes,4,0,"")<<endl;

 // // ---------------------        ---------------------------------------------------------------------------------------------- 
   
  // int r=4,c=4;
  // vector<bool> column(c,false);
  // vector<bool> diagonal(r+c-1,false);
  // vector<bool> adjacent_diagonal(r+c-1,false);
  // cout<<N_Queen_BestCase(0,column,diagonal,adjacent_diagonal,4,"")<<endl;         
  // cout<<calls_bestcase<<endl;                // ! Compare with N Queen 2D Combination Case calls 
   
 // // ---------------------        ---------------------------------------------------------------------------------------------- 

  // int r = 4;
  // int col = 0;  
  // int diag = 0;
  // int adjdiag = 0;
  // cout<<N_Queen_BestCase_Bit(r,0,col,diag,adjdiag,r,"")<<endl;
  // cout<<calls_bestcase<<endl;
}

// ^ ============================================================================================================================

// ! Decoding Questions are based on frequency mapping of characters,
// ! And Then assigning each character a single value to make a sum so that resultant string has assigned value of that character.

string str1="send";
string str2="more";
string str3="money";

vector<int> mapping(26,-1);
vector<bool> numUsed(10,false);

int Decode(string str,vector<int> mapping)
{
  // ! m here denotes that instead of decoding character from  start we go from backwards and then keep multiplying to get 0,10,100,.... 
  int res=0,m=1;
  for(int i=str.length()-1;i>=0;i--)
  {
    int num = mapping[str[i] - 'a'];
    res = res  + m * num;
    m*=10;
  }

  return res;
}

int CryptoArithmetic(string str,int idx)
{
  if(idx==str.length())
  {
    // ! This if statement is used to restrict all the strings starting character to not have "0" value as its frequency. 
    if(mapping[str1[0]-'a'] ==0 || mapping[str2[0]-'a'] ==0 ||mapping[str3[0]-'a'] ==0  )
       return 0;

    int num1 = Decode(str1,mapping);
    int num2 = Decode(str2,mapping);
    int num3 = Decode(str3,mapping);

    if(num1 + num2 == num3)
    {
      cout<<num1<<" + "<<num2<<" = "<<num3<<endl;
      return 1;
    }  
    return 0;   
  }

  char ch = str[idx];
  int count=0;

  for(int i=0;i<10;i++)
  {
    if(!numUsed[i])
    {
      numUsed[i]=true;
      mapping[ch - 'a'] = i;

      count+=CryptoArithmetic(str,idx+1);

      numUsed[i]=false;      
      mapping[ch - 'a'] = -1;
    } 
  }

  return count;
}

void Crypto()
{
  string str=str1+str2+str3;
  vector<int> freqMap(26,0);

  for(int i=0;i<str.length();i++)
  {
    int idx=str[i]-'a';
    freqMap[idx]++;
  }

  string ans="";

  for(int i=0;i<26;i++)
  {
    if(freqMap[i]!=0)
      ans+= (char)(i + 'a');
  }
 
  cout<<"Count: "<<CryptoArithmetic(ans,0)<<endl;  
}

// // ------------------------------------------------------------------------------------------------------------------------

vector<string> dictionary= {"i","ili","ilike","like","man","go","mango","and","sam","sung","samsung"};

bool checkWordIsPresent(string word)
{
  for(string s:dictionary)
  {
    if(s.compare(word) == 0)
      return true;
  }
  return false;
}

int wordBreak(string word,string ans)
{
  if(word.length()==0)
  {
    cout<<ans<<endl;
    return 1;
  }

  string temp="";
  int count=0;

  for(int i=0;i<word.length();i++)
  {
    temp += word[i];
    if(checkWordIsPresent(temp)) 
      count+=wordBreak(word.substr(i+1),ans + temp + " "); 
  }

  return count;
}

// // ------------------------------------------------------------------------------------------------------------------

void cryptoQues()
{
  // Crypto();
  cout<<"Count: "<<wordBreak("ilikemangoandsamsung","");
}

// ^ ============================================================================================================================

int equiSet(vector<int> &arr,int vidx, int set1,int set2,string ans1,string ans2)
{
  if(vidx==arr.size())
  {
    if(set1 == set2)
    {
      cout<<ans1<<" = "<<ans2<<endl;
      return 1;
    }
    return 0;
  } 

  int count=0;

  count+=equiSet(arr,vidx+1,set1+arr[vidx],set2,ans1+to_string(arr[vidx])+" ",ans2);
  count+=equiSet(arr,vidx+1,set1,set2+arr[vidx],ans1,ans2+to_string(arr[vidx])+" ");

  return count;
}

void setQues()
{
  vector<int> arr={10,20,30,40,50,60,70};
  //cout<<"Count: "<<equiSet(arr,0,0,0,"","")<<endl;
  // ! In order to remove Mirror Duplicacy, we diddn't give option to values so that it will either go to set 1 or set 2.  
  cout<<"Count: "<<equiSet(arr,1,10,0,"10 ","")<<endl; 
}

// ^ ============================================================================================================================

void solve()
{
  // basicQues();       
  // pathTypeQues();
  // perm_comb_Ques();
  // NqueenWaysQues();
  // cryptoQues();
  // setQues();
}

int main(int args,char** argv)
{
  solve();
  return 0;
}

