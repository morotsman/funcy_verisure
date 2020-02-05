package answers.lecture2

object Recursive {

  //0! = 1
  //n! = n * (n-1) * (n-2) * ... * 2 * 1
  def factorial(n: Long): Long = n match {
    case 0 => 1
    case n => n * factorial(n - 1)
  }

  //F(0) = 0,
  //F(1) = 1,
  //F(N) = F(N-1) + F(N-2)
  def fibonacciSlow(levels: Long): Long = levels match {
    case 0 => 0
    case 1 => 1
    case n => fibonacciSlow(n - 1) + fibonacciSlow(n - 2)
  }

  def max(is: List[Int]): Option[Int] = ???

  //    1
  //   1 1
  //  1 2 1
  // 1 3 3 1
  //1 4 6 4 1
  def pascalsTriangle(levels: Int): List[List[Int]] = ???


  def factorialTailRecursive(number: BigInt): BigInt = {

    @scala.annotation.tailrec
    def go(acc: BigInt, n: BigInt): BigInt =
      if(n == 0) acc
      else go(acc * n, n - 1)

    go(1, number)
  }


  def fibonacciFast(level: Long): Long = {

    @scala.annotation.tailrec
    def go(l: Long, n1: Long, n2: Long): Long =
      if (l == level) {
        n2
      } else {
        go(l + 1, n1 + n2, n1)
      }

    go(0, 1, 0)
  }

}
