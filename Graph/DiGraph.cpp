// TODO:: All the Questions done here are for Directional Graph.

#include <iostream>
#include <vector>
#include <queue>
using namespace std;

class Edge
{
  public:
    int v = 0;
    int w = 0;

  Edge(int v,int w)
  {
    this -> v = v;
    this -> w = w;
  }  
};

int n=8;
vector<vector<Edge*>> graph(n,vector<Edge*>());

void display()
{
  for(int i = 0; i < graph.size(); i++)
  {
    cout<<i<<"=>";

    for(Edge* e: graph[i])
      cout<<"("<<e->v<<","<<e->w<<") ";    
    cout<<endl;   
  }   
}

void addEdge(int u,int v,int w)
{
  if(u<0 || v<0 || u>=n || v>=n)
    return;

  graph[u].push_back(new Edge(v,w));
}

void constructGraph()
{
  // ! Graph for Topological Sort Main :--> 

  // addEdge(0,1,10);
  // addEdge(1,2,10);
  // addEdge(2,3,10);
  // addEdge(0,4,10);   
  // addEdge(7,4,10);
  // addEdge(7,6,10);
  // addEdge(6,5,10);
  // addEdge(5,3,10);

  // ! Cycle Graph :--> 

  // addEdge(0,1,10);
  // addEdge(1,2,10);
  // addEdge(2,3,10);
  // addEdge(3,0,10); 

  // ! Khan's Cycle :-->

  // addEdge(0,1,10); 
  // addEdge(0,4,10); 
  // addEdge(1,2,10); 
  // addEdge(2,3,10); 
  // addEdge(5,3,10);
  // addEdge(6,5,10); 
  // addEdge(7,4,10); 
  // addEdge(7,6,10); 
  // addEdge(7,8,10); 
  // addEdge(8,9,10);  
  // addEdge(9,7,10); 

   

  display(); 
  cout<<endl;   
}

// ^ ==============================================================================================================================

void topologicalSort_Main(int src,vector<bool> &vis,vector<int> &stack)
{
  vis[src] = true;
  for(Edge* e : graph[src])
  {
    if(!vis[e->v])
      topologicalSort_Main(e->v,vis,stack);
  }

  stack.push_back(src);
}

void topologicalSort()
{
  vector<bool> vis(graph.size(),false);
  vector<int> stack;
  
  for(int i=0;i<graph.size();i++)
  {
    if(!vis[i])
      topologicalSort_Main(i,vis,stack);
  }

  while (stack.size() != 0)
  {
    cout << stack.back() << " ";
    stack.pop_back();
  }
}

// ^ ==============================================================================================================================

// ! If a Cycle is detected in Directional Graph then, the Topological Sort is not possible.  

bool topologicalSort_Cycle(int src,vector<bool> &vis,vector<bool> &cycleDetection,vector<int> &stack)
{
  vis[src] = true;
  cycleDetection[src] = true;
  bool result = false;

  for(Edge* e : graph[src])
  {
    if(!vis[e->v])
      result = result || topologicalSort_Cycle(e->v,vis,cycleDetection,stack);

    else if(cycleDetection[e->v])
      return true;
  }

  stack.push_back(src);
  cycleDetection[src] = false;
  return result;
}

void topologicalSort_C()
{
  vector<bool> vis(graph.size(),false);
  vector<bool> cycleDetection(graph.size(),false);
  vector<int> stack;
  bool result = false;

  for(int i=0;i<graph.size();i++)
  {
    if(!vis[i])
      result = result || topologicalSort_Cycle(i,vis,cycleDetection,stack);
  }
  
  if(result)
    cout<<"Directional Cycle: "<<(boolalpha)<<result<<endl;  
  else
  {
    cout<<"Path: ";
    for(int i=0;i<graph.size() && !result;i++)
      cout<<stack[i]<<" ";
  } 
  cout<<endl;  
}

// ^ ==============================================================================================================================

// ! If the Graph has Path with No. of Vertex less than Total No. of Vertex then, it will contain a Cycle Graph.

void khansAlgo()
{
  vector<int> InDegree(graph.size(),0);

  for(int i=0;i<graph.size();i++)
  {
    for(Edge* e : graph[i])
      InDegree[e->v]++;
  } 

  queue<int> que;
  vector<int> ans;

  for(int i=0;i<graph.size();i++)
  {
    if(InDegree[i] == 0)
      que.push(i);
  } 

  while(que.size() != 0)
  {
    int rvtx = que.front();
    que.pop();
    ans.push_back(rvtx);

    for(Edge* e : graph[rvtx])
    {
      InDegree[e->v]--;
      if(InDegree[e->v] == 0)
        que.push(e->v);
    }
  }

  if(ans.size() != graph.size())
    cout<<"Cycle Graph"<<endl;
  else
  {
    cout<<"Path: ";
    for(int i=0;i<ans.size();i++)
      cout<<ans[i]<<" ";
  }               
}

// ^ ==============================================================================================================================

void solve()
{
  constructGraph();
  // topologicalSort();
  // topologicalSort_C();
  // khansAlgo();

}

int main(int args,char** argv)
{
  solve(); 
  return 0;
}
