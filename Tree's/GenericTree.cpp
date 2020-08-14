#include <iostream>
#include <vector>
#include <stack>

using namespace std;

class Node
{
  public:
    int data = 0;
    vector<Node*> childs;
  
  Node() {}
  
  Node(int data) {
    this->data = data;
  } 
};

void display(Node* node)
{
  cout<<node->data<<" -> ";

  // if(node->childs.size() == 0)
  //   cout<<"null";

  for(Node* child : node->childs)
    cout<<child->data<<" , ";
  cout<<endl;

  for(Node* child : node->childs)
    display(child);  
}

// ! PreOrder Tree :- 

Node* createTree(vector<int> &arr)
{
  stack<Node*> st;

  for(int i = 0;i < arr.size() - 1;i++)
  { 
    if(arr[i] != -1)
    {
      Node* node = new Node(arr[i]);
      st.push(node);
    }
    else
    {
      Node* node = st.top();
      st.pop();
 
      // ~ "." means accessing a local variable.
      // & "->" means accessing/linking a stack from heap memory.

      st.top()->childs.push_back(node);
    }
  }

  return st.top();
}

// ^ ====================================================================================================================================

// ! sz -> size
// ! h -> height

int size(Node* node)
{
  int sz = 0;
  for(Node* child : node->childs)
    sz += size(child);

  return sz + 1;  
}

int height(Node* node)
{
  int h = -1;
  
  for(Node* child : node->childs)
    h = max(h,height(child));

  return h + 1;  
}

int maximum(Node* node)
{
  int maxData = node->data;

  for(Node* child : node->childs)
    maxData = max(maxData,maximum(child));

  return maxData;  
}

bool find(Node* node,int data)
{
  bool result = false;

  if(node->data == data)
    return true;

  for(Node* child : node->childs)
    result = result || find(child,data);

  return result;    
}

// ^ ==================================================================================================================================

Node* getTail(Node* node)
{
  if(node->childs.size() == 0)
    return node;

  return getTail(node->childs[0]);
}

void linearTree(Node* node)
{
  for(Node* child : node->childs)
    linearTree(child);

  for(int i = node->childs.size() - 2;i >= 0;i--)
  {
    Node* tail = getTail(node->childs[i]);
    tail->childs.push_back(node->childs[i + 1]);
 
    node->childs.pop_back();
  }  
}

Node* linearTree_2(Node* node)
{
  if(node->childs.size() == 0)                                // ! Alternative for getting tail.
    return node;

  Node* myTail = linearTree_2(node->childs.back()); 

  for(int i = node->childs.size() - 2;i >= 0;i--)
  {
    Node* tail = linearTree_2(node->childs[i]);
    tail->childs.push_back(node->childs.back());
 
    node->childs.pop_back();
  }  

  return myTail;
}

// ^ ==================================================================================================================================

void removeLeafNodes(Node* node)
{
  vector<Node*> newChilds;

  for(Node* child : node->childs)
  {
    if (child->childs.size() != 0)
      newChilds.push_back(child);
  }

  node->childs = newChilds;

  for(Node* child : node->childs)
    removeLeafNodes(child);
}

// ^ ==================================================================================================================================

class allSol
{
  int height = -1;
  int size = 0;
  bool find = false;
  
  Node *pred = nullptr;
  Node *succ = nullptr;
  Node *prev = nullptr;
  
  int ceil = 1e8;
  int floor = -1e8;
};

// ^ In this allSol class, is used as an address reference in allSolution function making it as an local variable to the function.
// & So, we can use the dot operator instead of arrow operator.

void allSolution(Node *node, int data, int level, allSol &pair)
{
  if (node == nullptr)
      return;

  pair.height = max(pair.height, level);
  pair.size++;
  pair.find = pair.find || node->data == data;

  if (node->data > data)
    pair.ceil = min(pair.ceil, node->data);

  if (node->data < data)
    pair.floor = max(pair.floor, node->data);

  if (node->data == data && pair.pred == nullptr)
    pair.pred = pair.prev;
 
  else if (pair.prev != nullptr && pair.succ == nullptr && pair.prev->data == data)
    pair.succ = node;

  pair.prev = node;

  for (Node *child : node->childs)
    allSolution(child, data, level + 1, pair);
}

// ^ ===================================================================================================================================

bool isSymmetric(Node* node_1,Node* node_2)
{
  if(node_1->childs.size() != node_2->childs.size())
    return false;

  for(int i = 0,j = node_1->childs.size() - 1;i <= j;i++,j--)     // ! Condition: j >= 0 instead of i <= j for different trees.
  {
    Node* child_1 = node_1->childs[i];
    Node* child_2 = node_2->childs[i];

    if(!isSymmetric(child_1,child_2))
      return false;
  } 

  return true;
}

bool isMirror(Node* node_1,Node* node_2)
{
  if(node_1->childs.size() != node_2->childs.size() || node_1->data != node_2->data)
    return false;

  for(int i = 0,j = node_2->childs.size() - 1;i <= node_1->childs.size() - 1 && j >= 0;i++,j--) 
  {
    Node* child_1 = node_1->childs[i];
    Node* child_2 = node_2->childs[j];

    if(!isMirror(child_1,child_2))
      return false;
  } 

  return true;  
}

// ^ ==================================================================================================================================

void solve()
{
  vector<int> arr = {10,20,50,-1,60,-1,-1,30,70,-1,80,100,-1,110,-1,-1,90,-1,-1,40,-1,-1};
  Node* root = createTree(arr);
  // cout<<"Generic Tree: "<<endl<<endl;
  // display(root);
  // cout<<endl;

  // cout<<"Size of Generic Tree: "<<size(root)<<endl;
  // cout<<"Height of Generic Tree: "<<height(root)<<endl;
  // cout<<"Maximum Height of Generic Tree: "<<maximum(root)<<endl;
  // cout<<"Searched Node of Generic Tree is present: "<<boolalpha<<find(root,60)<<endl;

  // linearTree(root);
  // linearTree_2(root);
  // display(root);

  // removeLeafNodes(root);
  // display(root); 

  // cout<<"Structure of two Generic Trees are same: "<<boolalpha<<isSymmetric(root,root)<<endl;

  // vector<int> ar_1 = {10,20,50,-1,60,-1,-1,30,70,-1,80,100,-1,110,-1,-1,90,-1,-1,40,120,-1,130,-1,-1}; 
  // Node* ro_1 = createTree(ar_1);   
  // display(ro_1);
  // vector<int> ar_2 = {10,40,130,-1,120,-1,-1,30,90,-1,80,110,-1,100,-1,-1,70,-1,-1,20,60,-1,50,-1,-1};
  // Node* ro_2 = createTree(ar_2);
  // display(ro_2);   
  // cout<<"Mirror Value of two Generic Trees are same: "<<boolalpha<<isMirror(ro_1,ro_2)<<endl;     
}

int main(int args,char** argv)
{
  solve();
  return 0;
}