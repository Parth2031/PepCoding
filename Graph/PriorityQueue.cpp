#include <iostream>
#include <queue>
using namespace std;

class Pair
{
  public:
    int age;
    int wt;

  Pair(int age, int wt)
  {
    this -> age = age;
    this -> wt = wt;
  }

  bool operator < (Pair const &p1) const
  {
    return this->age > p1.age;
  }
};

int main()
{
  priority_queue<Pair> pq;

  Pair p1(10, 10);
  pq.push(p1);

  Pair p2(2, 16);
  pq.push(p2);

  Pair p3(5, 560);
  pq.push(p3);

  Pair p4(20, 2340);
  pq.push(p4);

  Pair p5(-8, 340);
  pq.push(p5);

  while (pq.size() != 0)
  {
    cout << pq.top().age << " " << pq.top().wt;
    pq.pop();
    cout<<endl;
  }

  return 0;
}