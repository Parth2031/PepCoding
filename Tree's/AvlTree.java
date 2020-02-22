import java.util.Scanner;

public class AvlTree
{
  public static class Node
  {
    int data = 0;
    Node left = null;
    Node right = null;

    int height = -1;
    int balance = 0;

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

  public static Node left_Left(Node x)
  {
    Node y = x.left;
    Node y_right = y.right;

    y.right = x;
    x.left = y_right;

    updateHeightAndBalance(x);
    updateHeightAndBalance(y);

    return y;
  }

  public static Node right_Right(Node x)
  {
    Node y = x.right;
    Node y_left = y.left;

    y.left = x;
    x.right = y_left;

    // ! In this, "x" is the root and "y" is it's child.
    // ! In this, we update the height of "x" first as "x" becomes the child of "y" so the height of "x" affect the height of "y".

    updateHeightAndBalance(x);
    updateHeightAndBalance(y);

    return y;
  }

  public static void updateHeightAndBalance(Node node)
  {
    int leftHeight = -1;
    int rightHeight = -1;

    if(node.left != null)
      leftHeight = node.left.height;

    if(node.right != null)
      rightHeight = node.right.height;

    node.height = Math.max(leftHeight,rightHeight) + 1;
    node.balance = leftHeight - rightHeight;        
  }

  public static Node rotation(Node node)
  {
    // TODO:: In this, we update height and balance in order to see whether the rotation is needed or not. 

    updateHeightAndBalance(node);

    // ! Either left-left or left-right rotation.

    if(node.balance == 2)
    {
      if(node.left.balance == 1)                                      // ! It is left-left rotation.
        return left_Left(node);

      else                                                            // ! It is left-right rotation. 
      {
        node.left = right_Right(node.left);
        return left_Left(node);
      }    
    }

    // ! Either right-right or right-left rotation.

    else if(node.balance == -2)
    {
      if(node.right.balance == -1)                                      // ! It is right-right rotation.
        return right_Right(node);

      else                                                              // ! It is right-left rotation. 
      {
        node.right = left_Left(node.right);
        return right_Right(node);
      }    
    }

    return node;
  }
  
  public static Node addData(Node node,int data)
  {
    if(node == null)
      return new Node(data);

    if(data < node.data)
      node.left = addData(node.left,data);
    else
      node.right = addData(node.right,data);      
  
    node = rotation(node);

    return node;
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

  public static Node removeData( Node node, int data)
  {
    updateHeightAndBalance(node);

    if(node == null)
      return null;

    if(node.data == data)
    {
      if(node.left == null || node.right == null)
        return node.left == null ? node.right : node.left;

       int maxData = maximumInTree(node.left);
      node.data = maxData;

      node.left = removeData(node.left,maxData);
    }  

    else if(data < node.data)
      return removeData(node.left,data);
    
    else
      return removeData(node.right,data);      

    node = rotation(node);

    return node;  
  }

  // TODO ================================================================================================================================ 





  // TODO ================================================================================================================================ 

  public static void solve()
  {
    Node root = null;
    for(int i = 1;i <= 20;i++)
      root = addData(root,i * 10);

    // root = removeData(root,80);

    System.out.println("AVL Tree: \n");
    System.out.println(root);
    
    
  } 

  public static void main(String[] args) {
    solve();
  }
}