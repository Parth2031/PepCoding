public class stack
{
  // ! tos -> Top of Stack
  // ! rv -> Return Value

  protected int[] st;
  protected int tos = -1;

  stack() {
    this.st = new int[10];
  }

  stack(int size) {
    this.st = new int[size];
  }

  stack(int[] arr) 
  {
    this.st = new int[2 * arr.length];

    for(int i = 0;i < arr.length;i++)
      this.st[i] = arr[i];

    this.tos = arr.length - 1;  
  }

  public int size() {
    return tos + 1;
  }

  public boolean isEmpty() 
  {
    if(tos == -1)
      return true;
    return false;  
  }

  public void display()
  {
    for(int i = 0;i < tos + 1;i++)
      System.out.println(st[i] + " ");
  }

  public void push(int data)
  {
    if(tos + 1 == st.length)
    {
      System.out.println("StackOverflow: " + tos);
      return;
    }

    st[++tos] = data;
  }

  public int top()
  {
    if(tos == -1)
    {
      System.out.println("Stack is Empty: " + tos);
      return -1;
    }

    return st[tos]; 
  } 

  public int pop()
  {
    if (tos == -1)
    {
      System.out.println("Stack is Empty: " + tos);
      return -1;      
    }

    int rv = st[tos];
    st[tos] = 0;
    tos --;
    return rv;
  }
} 