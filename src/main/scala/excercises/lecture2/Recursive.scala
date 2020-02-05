package excercises.lecture2

class Recursive {

  //F(0) = 0,
  //F(1) = 1,
  //F(N) = F(N-1) + F(N-2)
  def fibonacciSlow(levels: Long): List[Long] = ???

  //n! = n * (n-1) * (n-2) * ... * 2 * 1
  //0! = 1
  def factorial(n: Long): Long = ???

  def max(is: List[Int]): Option[Int] = ???

  //    1
  //   1 1
  //  1 2 1
  // 1 3 3 1
  //1 4 6 4 1
  def pascalsTriangle(levels: Int): List[List[Int]] = ???

  def fibonacciFast(level: Long): Long = ???

  def factorialTailRecursive(n: Long): Long = ???

}
