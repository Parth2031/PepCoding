public class dynamicStack extends stack
{
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

    // ! It works on inheritance and polymorphism.
    // ! It only works at run time of code where it chooses whether to call dynamic or not depending on it's need. 
    // TODO:: super.push(data) is used to make this push function as a part of stack class instead of copying all the functions from stack class.

    super.push(data);
  }
} 