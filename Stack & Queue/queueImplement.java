import java.util.ArrayList;

public class queueImplement
{
  public static void main(String[] args) 
  {
    queue q = new queue(5);
   
    System.out.println("Queue: \n");     
    q.push(10);
    q.push(20);
    q.push(30);
    q.push(40);
    q.push(50);
    q.display(); 

    System.out.println("Size: " + q.front());
    System.out.println("Popped Element: " + q.pop());
    System.out.println();
    q.display();
  }   
}