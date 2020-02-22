public class queue
{
  int[] que;
  int size = 0;
  int head = 0;
  int tail = -1;

  queue() {
    this.que = new int[10];
  }
  
  queue(int size) {
    this.que = new int[size];
  }

  public boolean isEmpty() {
    return this.size == 0;
  }

  public void display()
  {
    for(int i = 0;i < this.size;i++)
    {
      int value = this.que[(this.head + i) % que.length];
      System.out.println(value + " ");
    }
    System.out.println();
  }

  public void push(int data)
  {
    if(this.size == que.length)
    {
      System.out.println("Queue is Full");
      return;
    }

    this.tail = (this.tail + 1) % this.que.length;
    que[this.tail] = data;
    this.size ++;
  }

  public int front()
  {
    if(this.size == 0)
    {
      System.out.println("Queue is Empty:");
      return -1;
    }

    return this.que[this.head];
  }

  public int pop()
  {
    if(this.size == 0)
    {
      System.out.println("Queue is Empty:");
      return -1;
    }

    int removeData = this.que[this.head];
    this.que[this.head] = 0;
    this.size --;

    // ! It is used to make the queue as a Circular Queue.

    this.head = (this.head + 1) % this.que.length;

    return this.head;     
  }
}