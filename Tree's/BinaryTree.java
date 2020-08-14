import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

public class BinaryTree
{
  public static class Node
  {
    int data = 0;
    Node left = null;
    Node right = null;

    public Node() {}

    public Node(int data) {
      this.data = data;
    }

    public Node(int data,Node left,Node right)
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

  public static void display(Node node)
  {
    if(node == null)
      return;

    System.out.print(node.left != null ? node.left.data:".");
    System.out.print(" -> " + node.data + " <- ");
    System.out.println(node.right != null ? node.right.data:".");
                                             
    display(node.left);      // ! Display in terms of Index:  display(2*idx+1);   <-- Avoided for saving space.
    display(node.right);     // & Display in terms of Index:  display(2*idx+2);   <-- Avoided for saving space.
  } 

  static int idx = 0;

  public static Node createTree(int[] arr)
  {
    if(idx == arr.length || arr[idx] == -1)
    {
      idx++;
      return null;
    }
  
    Node node = new Node(arr[idx]);
    idx++;

    node.left = createTree(arr);
    node.right = createTree(arr);

    return node;
  }

  public static int maximumInTree(Node node) 
  {
    if (node == null)
      return (int) -1e8;

    int leftMax = maximumInTree(node.left);
    int rightMax = maximumInTree(node.right);
    int overallMax = Math.max(leftMax, rightMax);

    return Math.max(node.data, overallMax);
  }

  public static boolean find(Node node, int data) 
  {
    if (node == null)
      return false;

    if (node.data == data)
      return true;
        
    boolean result = false;
  
    result = result || find(node.left, data);
    result = result || find(node.right, data);
    
    return result;
  }

  public static int size(Node node) 
  {
    if (node == null)
      return 0;
    
    return size(node.left) + size(node.right) + 1;
  }

  public static int height(Node node) 
  {
    if (node == null)          // ! In this, we returned "-1" as we wanted to get the height of tree in terms of edges. 
      return -1;                       //  & If returned "0", we get the height of tree in terms of vertex. 
            
    return Math.max(height(node.left), height(node.right)) + 1;
  }

  // ^ ================================================================================================================================ 

  public static ArrayList<Node> rootToNodePath(Node node,int data)
  {
    if(node == null)
    {
      ArrayList<Node> base = new ArrayList<>();
      return base;                                 // ! For the above two lines, return null;
    }

    if(node.data == data)
    {
      ArrayList<Node> ans = new ArrayList<>();
      ans.add(node);
      return ans; 
    }
  
    ArrayList<Node> left = rootToNodePath(node.left,data);
    if(left.size() != 0)                                          // ~ if(left.size() != null) for above case
    {
      left.add(node);
      return left;
    }

    ArrayList<Node> right = rootToNodePath(node.right,data);
    if(right.size() != 0)
    {
      right.add(node);
      return right;
    }

    return new ArrayList<>();        
  }

  // ^ LCA -> Lowest Common Ancester of two nodes means, common parent of the two nodes.

  public static int LCA_01(Node node,int data_1,int data_2)
  {
    ArrayList<Node> list_1 = rootToNodePath(node,data_1);
    ArrayList<Node> list_2 = rootToNodePath(node,data_2);

    int ans = -1;
    int i = list_1.size() - 1;
    int j = list_2.size() - 1;

    while(i >= 0 && j >= 0)
    {
      if(list_1.get(i) != list_2.get(j))
        break;

      ans = list_1.get(i).data;
      i--;
      j--;   
    }

    return ans;
  }

  // ^ ================================================================================================================================ 

  public static void kDown(Node node,Node prevent_node,int level) 
  {
    if(node == null || prevent_node != null && node == prevent_node)
      return;

    if(level == 0)
      System.out.println(node.data + " ");

    kDown(node.left,prevent_node,level-1);
    kDown(node.right,prevent_node,level-1);  
  } 

  public static void kAway_01(Node node,int data,int level_k)
  {
    ArrayList<Node> list_1 = rootToNodePath(node,data);
    Node prevent_node = null;
  
    for(int i = 0;i < list_1.size();i++)
    {
      kDown(list_1.get(i),prevent_node,level_k - i);
      prevent_node = list_1.get(i);
    }    
  }

  public static int kAway_02(Node node,int data,int level_k)
  {
    if(node == null)
      return -1;

    if(node.data == data)
    {
      kDown(node,null,level_k);
      return 1;
    }  

    int leftDistance = kAway_02(node.left,data,level_k);
    if(leftDistance != -1)
    {
      kDown(node,node.left,level_k - leftDistance);
      return (leftDistance + 1);
    } 

    int rightDistance = kAway_02(node.right,data,level_k);
    if(rightDistance != -1)
    {
      kDown(node,node.right,level_k - rightDistance);
      return (rightDistance + 1);
    }

    return -1; 
  }

  // ^ ================================================================================================================================

  // ! Diameter in tree means maximum distance between two leaf nodes.  

  public static int maxDiameter_01(Node node)    
  {                                                                        // ! It has Complexity: O(n^2) which is worse.
    if(node == null) 
      return 0;

    int leftDiameter = maxDiameter_01(node.left);
    int rightDiameter = maxDiameter_01(node.right);

    int leftHeight = height(node.left);  
    int rightHeight = height(node.right);

    // * In this, "+2" is for adding two edges from left tree and right tree to root while returning.

    return Math.max(Math.max(leftDiameter,rightDiameter),leftHeight + rightHeight + 2);    
  }  
  
  public static int[] maxDiameter_02(Node node)
  {
    if(node == null)                                                       // ! It has Complexity: O(n).
      return new int[] {0,-1};                               // ^ In this, the parenthesis contains default {diameter,height}

    int[] leftDiameter = maxDiameter_02(node.left);
    int[] rightDiameter = maxDiameter_02(node.right);

    int[] myAns = new int[2];

    myAns[0] = Math.max(Math.max(leftDiameter[0],rightDiameter[0]),leftDiameter[1] + rightDiameter[1] + 2);
    myAns[1] = Math.max(leftDiameter[1],rightDiameter[1]) + 1;
  
    return myAns;
  }  

  static int maxSum = Integer.MIN_VALUE; 

  public static int leafToLeafSum(Node node)
  {
    if(node == null)
      return Integer.MIN_VALUE;

    if(node.left == null && node.right == null)                         // ! Leaf Identification
      return node.data;  

    int leftMaxSum = leafToLeafSum(node.left);
    int rightMaxSum = leafToLeafSum(node.right);

    if(node.left != null && node.right != null)
      maxSum = Math.max(maxSum,leftMaxSum + rightMaxSum + node.data);
  
    return Math.max(leftMaxSum,rightMaxSum) + node.data;
  }

  static int maxSum_01 = Integer.MIN_VALUE; 

  public static int nodeTONodeSum(Node node)
  {
    if(node == null)
      return 0;

    int leftMaxSum = nodeTONodeSum(node.left);
    int rightMaxSum = nodeTONodeSum(node.right);

    int maxBranch = Math.max(leftMaxSum,rightMaxSum);    
 
    maxSum_01 = Math.max(Math.max(maxSum_01,node.data),Math.max(node.data,leftMaxSum + rightMaxSum + node.data));
  
    return Math.max(maxBranch + node.data,node.data);
  }

  // ^ ==================================================================================================================================== 
   
  // * In order to get Minimum Cameras, the node is dependent on it's parent to have the camera as it will cover it's child nodes and it's parent. 

  // ! -1 : I need a Camera.
  // ! 0 : I am already covered.
  // ! 1 : I am Camera. 

  static int camera = 0;

  public static int minCameras_Main(Node node)
  {
    if(node == null)
      return 0;

    int left = minCameras_Main(node.left);
    int right = minCameras_Main(node.right);

    if(left == -1 || right == -1)
    {
      camera++;
      return 1;
    }

    if(left == 1 || right == 1)
      return 0;
      
    return -1;  
  } 

  public static int minCameras(Node node)
  {
    int value = minCameras_Main(node);
    
    if(value == -1)
      camera++;

    return camera;
  }

  // ^ ================================================================================================================================ 

  int leftMost = -1;
  int rightMost = -1;

  public static void width(Node node,int level)
  {
    if(node == null)
      return;

    leftMost = Math.min(leftMost,level);
    rightMost = Math.max(rightMost,level);

    width(node.left,level - 1);
    width(node.right,level + 1);  
  }

  // ! rn -> Remone node.
  // ! rd -> Remove Data.

  public static void VerticalOrder(Node node)
  {
    LinkedList<Node> que_1 = new LinkedList<>();
    LinkedList<Integer> que_2 = new LinkedList<>();

    width(node,0);

    // & ArrayList <ArrayList> of the next line given code.

    ArrayList<Integer>[] list = new ArrayList[rightMost - leftMost];
    int sum[] = new int[rightMost - leftMost];

    que_1.addLast(node);
    que_2.addLast(- leftMost);

    while(que_1.size() != 0)
    {
      int size = que_1.size();
      
      while(size-- > 0)
      {
        Node rn = que_1.removeFirst();
        int rd = que_2.removeFirst();

        list[rd].add(rn.data);
        sum[rd] += rn.data;

        if(node.left != null)
        {
          que_1.addLast(rn.right);
          que_2.addLast(rd + 1);
        } 
      }
    }

    for(int i = 0;i < list.length;i++)
      System.out.print(que_1);
  }

  // ^ ================================================================================================================================ 

  public static void solve()
  {
    // int[] arr = {10,20,30,40,-1,-1,50,-1,-1,60,-1,70,-1,-1,80,90,100,120,-1,-1,130,-1,-1,110,-1,-1,140,-1,-1};
    // Node root = createTree(arr);
    // display(root);                                     // ! Alternative to root toString() print but takes more time complexity.
    // System.out.println("Binary Tree: \n");
    // System.out.println(root);
    
    // System.out.println("Maximum Value of a node in tree: " + maximumInTree(root));
    // System.out.println("Searched Value of a node in tree: " + find(root,60));
    // System.out.println("Total Size of tree: " + size(root));
    // System.out.println("Total Height of tree: " +  height(root));
     
    // System.out.println("Common Parent of two selected nodes: " + LCA_01(root,30,60)); 
    // System.out.println("Node to a particular level childs: ");
    // kAway_01(root,80,2);
    // System.out.println("Count: " + kAway_02(root,80,2));
    
    // System.out.println("Maximum Diameter from Leaf to leaf: " + maxDiameter_01(root));
    // int[] ar = maxDiameter_02(root);
    // System.out.println("Maximum Diameter from Leaf to leaf: " + ar[0]);     // ! "0" indicates diameter and "1" indicates height.

    // System.out.println("Maximum Sum from Leaf to Leaf: " + leafToLeafSum(root));
    // System.out.println("Maximum Sum from Node to Node: " + nodeTONodeSum(root));

    // System.out.println("Minimum number of cameras to cover all nodes: " + minCameras(root));
  
    VerticalOrder(root);
  }

  public static void main(String[] args) {
    solve();
  }
}