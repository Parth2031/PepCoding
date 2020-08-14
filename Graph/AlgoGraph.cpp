// TODO:: In this file, there are two algorithm: Dijikstra and Prim's Algo based on priority queue.

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

int n=7;
vector<vector<Edge *>> dgraph(n,vector<Edge*>());
vector<int> dShortestPath(n,0);
vector<int> pShortestPath(n,0);

void display(vector<vector<Edge*>> &gp)
{
  for(int i = 0; i < gp.size(); i++)
  {
    cout<<i<<"=>";

    for(Edge* e: gp[i])
      cout<<"("<<e->v<<","<<e->w<<") ";
            
    cout<<endl;   
  }   
}

void addEdge(vector<vector<Edge*>> &gp,int u,int v,int w)
{
  if(u<0 || v<0 || u>=n || v>=n)
    return;

  gp[u].push_back(new Edge(v,w));
  gp[v].push_back(new Edge(u,w));
}

void constructGraph()
{
  addEdge(dgraph, 0, 1, 11);
  addEdge(dgraph, 0, 3, 10);
  addEdge(dgraph, 1, 2, 20);
  addEdge(dgraph, 2, 3, 40);
  addEdge(dgraph, 3, 4, 2);
  addEdge(dgraph, 4, 5, 2);
  addEdge(dgraph, 4, 6, 3);
  addEdge(dgraph, 5, 6, 8);
  
  display(dgraph); 
  cout<<endl;   
}

// ^ ======================================================================================================================

class dPair
{
  public:
    int vtx = 0;               // ! In this, u<->v we are taking complete vertex. 
    int parent = 0;
    int wt = 0;
    int wsf = 0;

  dPair(int vtx,int parent,int wt,int wsf)
  {
    this->vtx = vtx;
    this->parent = parent;
    this->wt = wt;
    this->wsf = wsf; 
  }

  // & This is used to set the priority of priority queue where we assume ourselves as strong or weak. 

  bool operator<(dPair const &o) const
  {
    return this->wsf > o.wsf;
  }
};

// ! rp -> Remove Pair

void dijikstraAlgo(int src,vector<bool> &vis)
{
  priority_queue<dPair> que;
  que.push(dPair(src,-1,0,0));

  while(que.size() != 0)
  {
    dPair rp = que.top();
    que.pop();

    if(vis[rp.vtx])                                     // ! Cycle Check in the graph
      continue;

    if(vis[rp.vtx] != -1)
    {
      addEdge(dgraph,rp.vtx,rp.parent,rp.wt);
      dShortestPath[rp.vtx] = rp.wsf;
    } 

    vis[rp.vtx] = true;

    for(Edge* e : dgraph[rp.vtx])
    {
      if(!vis[e->v])
        que.push(dPair(e->v,rp.vtx,e->w,rp.wsf + e->w));
    }
  }

  cout<<"Dijikstra Algo Path: ";
  for(int ele: dShortestPath)
    cout<<ele<<" ";
}

// ^ =========================================================================================================================

class primPair
{
  public:
    int vtx = 0;       
    int parent = 0;
    int wt = 0;
    int wsf = 0;

  primPair(int vtx,int parent,int wt,int wsf)
  {
    this->vtx = vtx;
    this->parent = parent;
    this->wt = wt;
    this->wsf = wsf; 
  }

  bool operator<(primPair const &o) const
  {
    return this->wt > o.wt;
  }
};

void primsAlgo(int src,vector<bool> &vis)
{
  priority_queue<primPair> que;
  que.push(primPair(src,-1,0,0));

  while(que.size() != 0)
  {
    primPair rp = que.top();
    que.pop();

    if(vis[rp.vtx])       
      continue;

    if(vis[rp.vtx] != -1)
    {
      addEdge(dgraph,rp.vtx,rp.parent,rp.wt);
      pShortestPath[rp.vtx] = rp.wt;
    } 

    vis[rp.vtx] = true;

    for(Edge* e : dgraph[rp.vtx])
    {
      if(!vis[e->v])
        que.push(primPair(e->v,rp.vtx,e->w,rp.wsf + e->w));
    }
  }

  cout<<"Prims Algo Path: ";
  for(int ele: pShortestPath)
    cout<<ele<<" ";
}

// ^ ======================================================================================================================

void solve()
{
  constructGraph();
  vector<bool> vis(n,false);
  // dijikstraAlgo(2,vis);
  // primsAlgo(2,vis);
}

int main(int args,char** argv)
{
  solve(); 
  return 0;
}
