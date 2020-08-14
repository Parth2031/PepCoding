// TODO:: All the Questions done here are for Bidirectional Graph.

#include <iostream>
#include <vector>
#include <queue>
#include <list>
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

// ! "n" here is defined as the numver of vertex/nodes present in graph which is also the Graph Size.

int n=7;
vector<vector<Edge*>> graph(n,vector<Edge*>());

void display()
{
  for(int i = 0; i < graph.size(); i++)
  {
    cout<<i<<"=> ";

    for(Edge* e: graph[i])
      cout<<"("<<e->v<<","<<e->w<<") ";

    // for(int j = 0; j < graph[i].size(); j++)
    //   cout << "( " << graph[i][j]->v << ", " << graph[i][j]->w << ") ";
            
    cout<<endl;   
  }   
}

void addEdge(int u,int v,int w)
{
  if(u<0 || v<0 || u>=n || v>=n)
    return;

  // & We used "u" as starting vertex/node of our Graph and "v" as ending/destination vertx/node of Graph.
  // ~ We added Edge from both "u" and "v" because we want the Edge to be bidirectional if edge is added once then, ....
  // ~ .... it will unidirectional edge.

  graph[u].push_back(new Edge(v,w));
  graph[v].push_back(new Edge(u,w));
}

void constructGraph()
{
  // ! Main Graph :->

  addEdge(0,1,10);
  addEdge(0,3,10);
  // addEdge(0,6,10);   // ! This Edge is added for Hamiltonian Cycle.
  addEdge(1,2,10);
  addEdge(2,3,40);
  // addEdge(2,5,10);   // ! This Edge is added for Hamiltonian Cycle.
  addEdge(3,4,2);
  addEdge(4,5,2);
  addEdge(4,6,3);
  addEdge(5,6,8);

  // ! Bipartite Graph -->

  // addEdge(0,1,10);
  // addEdge(0,5,10);
  // addEdge(1,2,10);
  // addEdge(2,3,10);
  // addEdge(3,4,10);
  // addEdge(4,5,10);

//   display(); 
//   cout<<endl;   
}

void removeEdge(int u,int v)
{
  int i=0;
  while(i < graph[u].size())
  {
    Edge* e = graph[u][i];

    if(e->v == v)
      break;

    i++; 
  }

  int j=0;
  while(j < graph[v].size())
  {
    Edge* e = graph[v][j];

    if(e->v == u)
      break;

    j++; 
  }

  graph[u].erase(graph[u].begin() + i);
  graph[v].erase(graph[v].begin() + j);

//   display();
}

void removeVertex(int u)
{
  while(graph[u].size()!=0)
  {
    Edge *e = graph[u][graph[u].size()-1];
    removeEdge(u,e->v);
  }
}

// ^ =========================================================================================================================

// ! src --> Source
// ! dest --> Destination
// ! psf --> Path So Far
// ! vis --> Visited Vertex
// ! wsf --> Weight So Far
// ! swsf --> Smallest Weight So Far
// ! spsf --> Smallest Path So Far
// ! lwsf --> Largest Weight So Far
// ! lpsf --> Largest Path So Far

bool hasPath(int src,int dest,vector<bool> &vis,string psf)
{
//   if(src == dest)
//   {  
    cout<<psf<<endl;
//     return true;
//   }

  vis[src] = true;

  for(Edge* e : graph[src])
  {
    if(!vis[e->v])
    {
      bool ans= hasPath(e->v,dest,vis,psf + to_string(e->v));
      if(ans==true)
        return true;
    }
  }

  return false;
}

int allPath(int src,int dest,vector<bool> &vis,int wsf,string psf)
{
  if(src == dest)
  {  
    cout<<psf<<" @ "<<wsf<<endl;
    return 1;
  }

  int count = 0;  
  vis[src] = true;

  for(Edge* e : graph[src])
  {
    if(!vis[e->v])
      count += allPath(e->v,dest,vis,wsf + e->w,psf + to_string(e->v));
  }

  vis[src] = false;
  return count; 
}

int swsf = 1e8;
string spsf = "";
int lwsf = -1;
string lpsf = "";

void min_maxWeightPath(int src,int dest,vector<bool> &vis,int wsf,string psf)
{
  if(src == dest)
  {
    if(wsf < swsf)
    {
      swsf = wsf;
      spsf = psf;
    }

    if(wsf > lwsf)
    {
      lwsf = wsf;
      lpsf = psf;
    }
  }

  vis[src]=true;

  for(Edge* e : graph[src])
  {
    if(!vis[e->v])
      min_maxWeightPath(e->v,dest,vis,wsf + e->w,psf + to_string(src) + "->");
  }

  vis[src] = false;
}

// ^ =========================================================================================================================

// ? dfs --> Depth First Search 
// ! Get Connected Component Question is used to tell the number of graphs present at that time as every time.
// ! The dfs(Depth First Search) is called it counts as a new graph.

void dfs(int src,vector<bool> &vis)
{
  vis[src]=true;
  
  for(Edge* e : graph[src])
  {
    if(!vis[e->v])
      dfs(e->v,vis);
  }    
}

void getConnectedComponent(int src,vector<bool> &vis)
{ 
  int component = 0;

  for(int i=0;i<n;i++)
  {
    if(!vis[i])
    {
      component++;
      dfs(i,vis);
    }
  }
  cout<<endl<<"Total No. of Graphs: "<<component<<endl;
}

// ^ ============================================================================================================================

// ? bfs --> Breadth First Search
// ! rvtx --> Remove Vertex

void bfs(int src,int dest,vector<bool> &vis)
{
  queue<int> que;
  que.push(src);
  que.push(-1);

  int cycle = 0;
  int level = 0; 

  while(que.size() != 1)
  {
    // ! In this rvtx, we get the element in it and then pop the element from queue.

    int rvtx = que.front();
    que.pop();

    if(rvtx == -1)
    {
      level++;
      que.push(-1);
      continue;
    }    

    if(vis[rvtx])
    {
      cout<<"Cycle Graph: "<< ++cycle <<" @ "<<rvtx<<endl;
      continue;
    }

    if(rvtx == dest)
      cout<<"Goal: "<<level << endl;

    vis[rvtx] = true;
   
    for(Edge* e : graph[rvtx])
    {
      if(!vis[e->v])
        que.push(e->v);
    }    

    // !  If we only have to find the bfs and not the cycle then we can apply this condition at buttom else not.

    // if(que.front() == -1)
    // {
    //   level++;
    //   que.pop();
    //   que.push(-1);
    // }    
  }
}

void bfs_alternative(int src,int dest,vector<bool> &vis)
{
  queue<int> que;
  que.push(src);

  bool isDest = false;
  int cycle = 0;
  int level = 0;

  while (que.size() != 0)
  {
    int size = que.size();

    // ? We applied size-- loop so as to run the the queue where the neighbours are visited and as soon as level gets complete.
    // * Alternative to -1 to see as level completion.

    while (size-- > 0)
    {
      // ! In this, rvtx is used to find the element which is in the front of queue and it is popped making it delete from queue.
      // & But then, the other loops are used to add its neighbours making the queue not empty and 
      // ~ It gets finally after all the level elements are popped.

      int rvtx = que.front();
      que.pop();

      if (vis[rvtx])
      {
        cout << "Cycle: " << ++cycle << " @ " << rvtx << endl;
        continue;
      }

      if (rvtx == dest && !isDest)
      {
        cout << "Lowest No of Edges from Source: " << level << endl;
        isDest = true;
      }
         
      vis[rvtx] = true;

      for (Edge* e : graph[rvtx])
      {
        if (!vis[e->v])
          que.push(e->v);
      } 
    }

    level++;
  } 
}

// ^ ===========================================================================================================================

// ! vtx --> Vertex
// ! osrc --> Original Source

int hamiltonianPath(int vtx,int osrc,int vtxCount,vector<bool> &vis,string ans)
{
  if(vtxCount == graph.size() - 1)
  {
    bool flag = false;

    for(Edge* e : graph[vtx])
    {
      if(e->v == osrc)
      {
        cout<<"Hamiltonian Cycle: " + ans + to_string(vtx)<<endl;
        flag = true; 
      }
    }

    if(!flag)
      cout<<"Hamiltonain Path: "<<ans + to_string(vtx)<<endl;

    return 1;  
  }
  
  int count = 0;
  vis[vtx] = true;
  
  for(Edge* e : graph[vtx])
  {
    if(!vis[e->v])
      count += hamiltonianPath(e->v,osrc,vtxCount+1,vis,ans + to_string(vtx) + " ");
  } 

  vis[vtx] = false;
  return count;
}

// ^ =================================================================================================================================

bool isBipartiteGraph(int i,vector<int> &vis)
{

  // ! Pair is used to see that whether an vertex that was visited before has same color or value if not than it is not  Bipartite Graph. 

  queue<pair<int,int>> que;
  bool flag = true;

  que.push({i,0});

  while(que.size() != 0)
  {
    pair<int,int> rvtx = que.front();
    que.pop();
  
  // & "first" is used to access the vertex of pair and "second" is used to access the color or value visited before has same value or not.

    if(vis[rvtx.first] != -1) 
    {
      if(vis[rvtx.first] != rvtx.second)
      {
        cout<<"Conflict: ";
        flag = false;
      }
      continue;
    }  

    vis[rvtx.first] = rvtx.second;

    for(Edge* e : graph[rvtx.first])
    {
      if(vis[e->v] == -1)
        que.push({e->v,(rvtx.second + 1) % 2});
    }
  }
  
  return flag;  
}

void isBipartite()
{
  vector<int> visited(graph.size(),-1);
  int count = 0;
  
  for(int i=0; i<graph.size(); i++)
  {
    if(visited[i] == -1)
    {
      // ! Count is used to see the no. of graphs. 

      cout<<"Count: "<<count<<"   "<<"Is it Bipartite: "<<(boolalpha)<<isBipartiteGraph(i,visited)<<endl;
      count++;
    }  
  }  
}

// ^ ============================================================================================================================


// ! AP -> Articulation Points
// ! AB -> Articulation Bridge
// ! osrc -> Original Source

vector<int> low(n,-1);
vector<int> discover(n,-1);
vector<bool> ApPoints(n, false);

int timee = 0;
int callsForRoot = 0;

void dfs_AP(int src,int parent,int osrc)
{
  low[src] = discover[src] = timee++;

  for(Edge* e : graph[src])
  {
    if(discover[e->v ] == -1)                                        // ! Unvisited Vertex
    {
      if(src == osrc)
        callsForRoot++;

      dfs_AP(e->v,src,osrc);

      if(discover[src] <= low[e->v])                                     // & AP Point
        ApPoints[src] = true;
      if(discover[src] < low[e->v])                                   // ~ AP Bridge (Edge)
        cout <<"AP Edge: " << src << " to " << e->v << endl;  
    
      low[src] = min(low[src],low[e->v]);
    }
    else if(e->v != parent)                                            // * Visited Vertex 
      low[src] = min(low[src],discover[e->v]);                       // ? Region for Back Edge 
  }
}

void AP()
{
  dfs_AP(0,-1,0);

  cout<<"Articulation Points(AP): "<<endl;
  if(callsForRoot > 1)
    cout<<"0"<<endl;  

  for(int i = 0;i < ApPoints.size(); i++)
  {
    if(ApPoints[i] && i != 0)
      cout<<i<<endl;
  }  
}

// ^ ============================================================================================================================

void solve()
{
  constructGraph();
  // removeEdge(3,4);
  // removeVertex(3);
  display();   
  cout<<endl;

  // vector<bool> vis(n,false);
  // cout<<hasPath(0,6,vis,to_string(0) + "");
  // cout<<allPath(0,6,vis,0,to_string(0)+"");  
  // min_maxWeightPath(0,6,vis,0,"");
  // cout<<spsf<<" @ "<<swsf<<endl;
  // cout<<lpsf<<" @ "<<lwsf<<endl; 
  // getConnectedComponent(0,vis);
  // bfs(0,6,vis);
  // bfs_alternative(0,6,vis);
  // cout<<"Count: "<<hamiltonianPath(0,0,0,vis,"")<<endl;
  // isBipartite();
  AP();
}

int main(int args,char** argv)
{
  solve(); 
  return 0;
}
