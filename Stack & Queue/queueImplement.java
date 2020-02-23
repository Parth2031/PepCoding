import java.util.ArrayList;

public class queueImplement
{
  public static void solve(dynamicQueue que)                 // ! queue que as an argument to call queue object.
  {
    // System.out.println("Queue: \n");     
    // que.push(10);
    // que.push(20);
    // que.push(30);
    // que.push(40);
    // que.push(50);
    // que.display(); 

    // System.out.println("Size: " + que.front());
    // System.out.println("Popped Element: " + que.pop());
    // System.out.println();
    // que.display();

    System.out.println("Dynamic Queue: \n");     
    que.push(10);
    que.push(20);
    que.push(30);
    que.push(40);
    que.push(50);
    que.display(); 

    System.out.println("Size: " + que.front());
    System.out.println("Popped Element: " + que.pop());
    System.out.println();
    que.display();   
  }

  public static void main(String[] args) 
  {
    // queue que = new queue(5);
    // solve(que);   

    dynamicQueue que = new dynamicQueue();
    solve(que);
  }   
}