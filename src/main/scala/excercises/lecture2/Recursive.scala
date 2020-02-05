package excercises.lecture2

class Recursive {

  //F(0) = 0,
  //F(1) = 1,
  //F(N) = F(N-1) + F(N-2)
  def fibonacci(levels: Int): List[Int] = ???

  //n! = n * (n-1) * (n-2) * ... * 2 * 1
  //0! = 1
  def factorial(n: Int): Int = ???

  def max(is: List[Int]): Option[Int] = ???

  //    1
  //   1 1
  //  1 2 1
  // 1 3 3 1
  //1 4 6 4 1
  def pascalsTriangle(levels: Int): List[List[Int]] = ???

}
