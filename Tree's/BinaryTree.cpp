#include <iostream>
#include <vector>
#include <string>
using namespace std;

class Node
{
  public:
    int data = 0;
    Node* left = nullptr;
    Node* right = nullptr;
  
  Node() {}
  Node(int data) {
    this->data = data;
  } 
  Node(int data,Node* left,Node* right)
  {
    this->data = data;
    this->left = left;
    this->right = right;
  }  
};

void display(Node* node)
{
  if(node == null)
    return;

  cout<<(node->left != null ? node->left->data:".");
  cout<<" -> " + node->data + " <- ";
  cout<<(node->right != null ? node->right->data:".")<<endl;

  display(node->left);      
  display(node->right);     
} 

  int idx = 0;

Node createTree(vector<int> arr)
{
  if(idx == arr.size() || arr[idx] == -1)
  {
    idx++;
    return nullptr;
  }
  
  Node* node = new Node(arr[idx]);
  idx++;

  node->left = createTree(arr);
  node->right = createTree(arr);

  return node;
}

void solve()
{
  vector<int> arr = {10,20,30,40,-1,-1,50,-1,-1,60,-1,70,-1,-1,80,90,100,120,-1,-1,130,-1,-1,110,-1,-1,140,-1,-1};
  Node* root = createTree(arr);
  display(root);

}

int main(int args,char** argv)
{
  solve();
  return 0;
}