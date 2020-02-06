package excercises.lecture2

object Recursive {

  def sum[A](li: List[Int]): Int = li match {
    case Nil => 0
    case i :: tail => i + sum(tail)
  }

  //0! = 1
  //n! = n * (n-1) * (n-2) * ... * 2 * 1
  def factorial(n: Long): Long = ???

  //F(0) = 0,
  //F(1) = 1,
  //F(N) = F(N-1) + F(N-2)
  def fibonacciSlow(levels: Long): List[Long] = ???

  // an Opton can be either None or Some
  def max(is: List[Int]): Option[Int] = is match {
    case Nil => ???
    case i :: Nil => ???
    case i :: tail => ???
  }

  //    1
  //   1 1
  //  1 2 1
  // 1 3 3 1
  //1 4 6 4 1
  def pascalsTriangle(levels: Int): List[List[Int]] = ???

  def fibonacciFast(level: Long): Long = ???

  def factorialTailRecursive(n: Long): Long = ???

}
