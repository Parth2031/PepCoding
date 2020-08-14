// TODO:: In this file, there are two main algo: DSU(Disjoint Set Union)/Union Find & Kruskal Algo.
// & We make the Graph using kruskal Algorithm. 

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

void display(vector<vector<pair<int,int>>> &gp)
{
  for (int i = 0; i < gp.size(); i++)
  {
    cout << i << " -> ";
    for (pair<int,int> e : gp[i])
      cout << "(" << e.first << ", " << e.second << ") ";
    cout << endl;
  }
}

// ! Union / Merge here means that graph is merged if the two different have not same size.
// ? This one is based on size and not rank as size tells that smaller tree should be attached on larger tree and vice versa.

void Union(int parent_1, int parent_2, vector<int> &parent, vector<int> &size)
{
  if (size[parent_1] < size[parent_2])
  {
    parent[parent_1] = parent_2;
    size[parent_2] += size[parent_1];
  }
  else
  {
    parent[parent_2] = parent_1;
    size[parent_1] += size[parent_2];
  }
}

int findParent(int vtx, vector<int> &parent)
{
  if (parent[vtx] == vtx)
    return vtx;

  parent[vtx] = findParent(parent[vtx], parent);
  return parent[vtx];
}

void kruskalAlgo(vector<pair<int,pair<int,int>>> &arr, int graphSize, vector<int> &parent, vector<int> &size)
{
  vector<vector<pair<int,int>>> newGraph(graphSize, vector<pair<int,int>>());

  for (int i = 0; i < arr.size(); i++)
  {
    pair<int,pair<int,int>> rp = arr[i];
    int parent_1 = findParent(rp.second.first, parent);
    int parent_2 = findParent(rp.second.second, parent);

    if (parent_1 != parent_2)
    {
      Union(parent_1, parent_2, parent, size);

      newGraph[rp.second.first].push_back({rp.second.second, rp.first});
      newGraph[rp.second.second].push_back({rp.second.first, rp.first});
    }
  }

  display(newGraph);
}

void kruskal()
{
  int graphSize = 7;
  vector<pair<int,pair<int,int>>> arr;

  arr.push_back({10, {0, 1}});
  arr.push_back({10, {0, 3}});
  arr.push_back({10, {1, 2}});
  arr.push_back({40, {2, 3}});
  arr.push_back({2, {3, 4}});
  arr.push_back({2, {4, 5}});
  arr.push_back({3, {4, 6}});
  arr.push_back({8, {5, 6}});

  // ! In this, the third parameter is called "lamda" which is used to set the type of sort condition.
  // ? In this, the two arguments of pair "a" & "b" means "a" denotes this and "b" denotes other.

  sort(arr.begin(), arr.end(), [](pair<int,pair<int,int>> a, pair<int,pair<int,int>> b) {
    return a.first < b.first;
  });

  vector<int> parent(graphSize,0);
  vector<int> size(graphSize,1);
  
  for(int i=0;i<graphSize;i++)
    parent[i]=i;

  kruskalAlgo(arr,graphSize,parent,size);
}

void solve()
{
  //  vector<pii> arr;
  //  for(int i=0;i<10;i++)
  //    arr.push_back({10-i,i});

  //  sort(arr.begin(),arr.end(),[](pii a,pii b){
  //    return a.first > b.first;
  //  });

  //  for(pii ele: arr)
  //    cout<<ele.first <<" "<<ele.second<<endl;
   
  kruskal();
}

int main(int args,char** argv)
{
  solve();
  return 0;
}
