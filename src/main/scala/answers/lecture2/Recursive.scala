package answers.lecture2

object Recursive {

  def multiply(li: List[Int]): Int = li match {
    case Nil => 1
    case i :: is => i * multiply(is)
  }

  def numbersBelow5(li: List[Int]): List[Int] = li match {
    case Nil => Nil
    case i :: is if i < 5 => i :: numbersBelow5(is)
    case i :: is =>  numbersBelow5(is)
  }

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

  def max(is: List[Int]): Option[Int] = is match {
    case Nil => None
    case i :: Nil => Some(i)
    case i :: tail => max(tail).map(t => t max i)
  }

  //    1
  //   1 1
  //  1 2 1
  // 1 3 3 1
  //1 4 6 4 1
  def pascalsTriangle(level: Int): List[List[Int]] = level match {
    case 0 => List(List())
    case 1 => List(List(1))
    case n =>
      val prevLevels = pascalsTriangle(n - 1)
      val prevLevel = prevLevels.last
      val result = (0 until level).map(i => getElement(prevLevel, i - 1) + getElement(prevLevel, i))
      prevLevels ++ List(result.toList)
  }

  def getElement(li: List[Int], index: Int): Int =
    try {
      li(index)
    } catch {
      case e: IndexOutOfBoundsException => 0
    }


  def factorialTailRecursive(number: BigInt): BigInt = {

    @scala.annotation.tailrec
    def go(acc: BigInt, n: BigInt): BigInt =
      if (n == 0) acc
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
