public class dynamicStack extends stack
{
  // ! If we don't use these constructor's with super() then the code after this function in stack will not be called/fetched to be further used.

  dynamicStack() {
    super();
  }

  dynamicStack(int size) {
    super(size);
  }

  dynamicStack(int[] arr) {
    super(arr);
  }

  @Override
  public void push(int data)
  {
    if(this.tos + 1 == st.length)
    {
      int[] temp = st;
      st = new int[2 * temp.length];

      for(int i = 0;i < temp.length;i++)
        st[i] = temp[i];
    }

    // * It works on inheritance and polymorphism.
    // & It only works at run time of code where it chooses whether to call dynamic or not depending on it's need. 
    // ~ super.push(data) is used to make this push function as a part of stack class instead of copying all the functions from stack class.

    super.push(data);
  }

  // ^ =================================================================================================================================

  // ! In this, we are understanding polymorphism and inheritance case.

  int b = 30;
  int c = 40;

  public void fun2() {
      System.out.println("hi!!!");
  }

  public void fun() 
  {
    System.out.println(b);
    System.out.println("hello Dynamic Stack");
  }
} 