import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;

public class BinarySearchTree
{
  public static class Node
  {
    int data = 0;
    Node left = null;
    Node right = null;

    public Node() {}
    public Node( int data) {
      this.data = data;
    }

    public Node( int data, Node left, Node right)
    {
      this.data = data;
      this.left = left;
      this.right = right;
    }

    @Override
    public String toString() 
    {
      // return data + "";
      String str = "";
      str += (left != null ? left.data:".");
      str += (" -> " + data + " <- ");
      str += (right != null ? right.data:".");
      str += "\n";

      if(left != null)
        str += left.toString();
      if(right != null)
        str += right.toString();

      return str;    
    }
  }
  
  // ! BST -> Binary Search Tree. 

  public static Node BST_Create(int[] arr,int startIndex,int endIndex) 
  {
    if(startIndex > endIndex)
      return null;

    // ! mid = {startIndex + (endIndex - startIndex)/2} -> Alternative to find and is the best case to overcome overflow case.   

    int mid = (startIndex + endIndex) >> 1;
    Node node = new Node(arr[mid]);

    node.left = BST_Create(arr,startIndex,mid - 1);
    node.right = BST_Create(arr,mid + 1,endIndex);

    return node; 
  }  
  
  public static Node BST()
  {
    int[] arr = new int[10];

    for(int i = 0; i < 10; i++)
      arr[i] = (i + 1) * 10;

    Node root = BST_Create(arr,0,arr.length - 1);

    return root;  
  }
  
  public static boolean find_01(Node node, int data)
  {
    if(node == null)
      return false;

    if(node.data == data)
      return true;
    
    else if(data < node.data)
      return find_01(node.left,data);
    
    else
      return find_01(node.right,data);      
  }   
   
  public static boolean find_02(Node node, int data)
  {
    while(node != null)
    {
      if(node.data == data)
        return true;
      else if(data < node.data)
        node = node.left;
      else
        node = node.right;      
    }

    return false;
  } 

  public static Node addData(Node node,int data)
  {
    if(node == null)
      return new Node(data);

    if(data < node.data)
      node.left = addData(node.left,data);
    else
      node.right = addData(node.right,data);      
  
    return node;
  }
 
  // TODO ================================================================================================================================ 

  public static class allSoln
  {
    int height = -1;
    int size = 0;
    boolean find = false;

    Node predecessor = null;
    Node successor = null;
    Node previous = null;
    
    int ceil = Integer.MAX_VALUE;
    int floor = Integer.MIN_VALUE;
  }

  // ! ceil,floor,predecessor & successor are all performed in InOrder Traversal to get the value of the selected node.

  public static void allSolution(Node node, int data, int level, allSoln pair)
  {
    if(node == null)
      return;

    pair.height = Math.max(pair.height,level);
    pair.size++;
    pair.find = pair.find || node.data == data;

    if(node.data > data)
      pair.ceil = Math.min(pair.ceil,node.data);

    if(node.data < data)
      pair.floor = Math.max(pair.floor,node.data);

    allSolution(node.left,data,level + 1,pair);

    if(node.data == data && pair.predecessor == null)
      pair.predecessor = pair.previous;
    
    else if(pair.previous != null && pair.previous.data == data && pair.successor == null)
      pair.successor = node;

    pair.previous = node;  

    allSolution(node.right,data,level + 1,pair);   
  } 

  public static void pred_Succ_ForBST(Node node, int data)
  {
    Node successor = null;
    Node predecessor = null;

    while(node != null)
    {
      if(node.data == data)
      {
        if(node.right != null)
        {
          successor = node.right;
          
          while(successor.left != null)
            successor = successor.left; 
        }

        if(node.left != null)
        {
          predecessor = node.left;
          
          while(predecessor.right != null)
            predecessor = predecessor.right; 
        }

        if(predecessor!=null)
          System.out.println("Predecessor: " + predecessor.data);
        else
          System.out.println("Predecessor: " + -1);
        
        if(successor!=null)
          System.out.println("Successor: " + successor.data);
        else
          System.out.println("Successor: " + -1);
        
        return;
      }  

      else if(data < node.data)
      {
        successor = node;
        node = node.left;
      } 
      else
      {
        predecessor = node;
        node = node.right;
      }
    }
  } 

  // TODO ================================================================================================================================ 

  // ! rnode -> Remove node. 

  public static int minimumInTree( Node root)
  {
    if(root == null)
      return Integer.MAX_VALUE;

    Node rnode = root;

    while(rnode.right != null)
      rnode = rnode.left;

    return rnode.data; 
  }  
  
  public static int maximumInTree( Node root)
  {
    if(root == null)
      return Integer.MIN_VALUE;

    Node rnode = root;

    while(rnode.left != null)
      rnode = rnode.right;

    return rnode.data; 
  }  

  public static Node removeNode( Node node, int data)
  {
    if(node == null)
      return null;

    if(node.data == data)
    {
      if(node.left == null || node.right == null)
        return node.left == null ? node.right : node.left;

       int maxData = maximumInTree(node.left);
      node.data = maxData;

      node.left = removeNode(node.left,maxData);
    }  

    else if(data < node.data)
      return removeNode(node.left,data);
    
    else
      return removeNode(node.right,data);      

    return node;  
  }

  // TODO ================================================================================================================================ 

  public static Node rightMostOfNextLeft(Node leftNode,Node current)
  {
    while(leftNode.right != null && leftNode.right != current)
      leftNode = leftNode.right;

    return leftNode;
  }
 
  public static void morrisIn_PreOrder(Node node)
  {
    Node current = node;

    while(current != null)
    {
      Node nextLeft = current.left;

      if(nextLeft == null)
      {
        System.out.print(current.data + " ");
        current = current.right;
      } 
      else
      {
        Node rightMost = rightMostOfNextLeft(nextLeft,current);

        if(rightMost.right == null)
        {
          rightMost.right = current;                                        // ! Create Thread.
          // System.out.print(current.data + " ");                // ! It is done here to show PreOrder Traversal.
          current = current.left;
        }
        else
        {
          System.out.print(current.data + " ");                // ! It is done here to show InOrder Traversal.
          rightMost.right = null;                                         // ! Breaking Thread
          current = current.right;
        }
      } 
    }
  }

  // TODO ================================================================================================================================ 

  // ! tnode -> Top Node.

  public static class Tpair
  {
    Node node = null;
    boolean selfDone = false;
    boolean leftDone = false;
    boolean rightDone =false;

    Tpair(Node node){
      this.node = node;
    }
  }

  public static void PreOrder(Node node)
  {
    Stack<Tpair> st = new Stack<>();
    st.add(new Tpair(node));

    while(st.size() != 0)
    {
      Tpair tnode = st.peek();

      if(!tnode.selfDone)
      {
        tnode.selfDone = true;
        System.out.print(tnode.node.data + " ");
      }
      else if(!tnode.leftDone)
      {
        tnode.leftDone = true;
        
        if (tnode.node.left != null) 
          st.add(new Tpair(tnode.node.left));
      }
      else if(!tnode.rightDone)
      {
        tnode.rightDone = true;
        
        if (tnode.node.right != null) 
          st.add(new Tpair(tnode.node.right));
      }
      else 
        st.pop();
    }
  }

  public static void InOrder(Node node)
  {
    Stack<Tpair> st = new Stack<>();
    st.add(new Tpair(node));

    while(st.size() != 0)
    {
      Tpair tnode = st.peek();

      if(!tnode.leftDone)
      {
        tnode.leftDone = true;
        
        if (tnode.node.left != null) 
          st.add(new Tpair(tnode.node.left));
      }
      else if(!tnode.selfDone)
      {
        tnode.selfDone = true;
        System.out.print(tnode.node.data + " ");
      }
      else if(!tnode.rightDone)
      {
        tnode.rightDone = true;
        
        if (tnode.node.right != null) 
          st.add(new Tpair(tnode.node.right));
      }
      else 
        st.pop();
    }
  }

  public static void PostOrder(Node node)
  {
    Stack<Tpair> st = new Stack<>();
    st.add(new Tpair(node));

    while(st.size() != 0)
    {
      Tpair tnode = st.peek();

      if(!tnode.leftDone)
      {
        tnode.leftDone = true;
        
        if (tnode.node.left != null) 
          st.add(new Tpair(tnode.node.left));
      }
      else if(!tnode.rightDone)
      {
        tnode.rightDone = true;
        
        if (tnode.node.right != null) 
          st.add(new Tpair(tnode.node.right));
      }
      else if(!tnode.selfDone)
      {
        tnode.selfDone = true;
        System.out.print(tnode.node.data + " ");
      }
      else 
        st.pop();
    }
  }

  // TODO ================================================================================================================================ 

  public static void solve()
  {
    Node root = BST();
    System.out.println("Binary Search Tree: \n");
    System.out.println(root);

    // System.out.println("Node is present or not: " + find_01(root,100));
    // System.out.println("Node is present or not: " + find_02(root,70));
     
    // Node root_1 = addData(root,210);
    // System.out.println("Binary Search Tree after addind new node: \n");
    // System.out.println(root_1);
        
    // allSolution(root,50,0,allSoln);
    // pred_Succ_ForBST(root,100);

    // removeNode(root,50);
    // System.out.println("Binary Search Tree after removing the nodes: \n");
    // System.out.println(root);

    // System.out.print("PreOrder/InOrder Traversal: "); 
    // morrisIn_PreOrder(root);     

    // System.out.print("PreOrder/InOrder/PostOrder Traversal: "); 
    // PreOrder(root);
    // InOrder(root);
    // PostOrder(root);

  }

  public static void main(String[] args) {
    solve();
  }
}