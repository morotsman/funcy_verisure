package answers.lecture2

import org.scalatest.FunSuite
import Recursive._

class RecursiveTest extends FunSuite {

  test("testMax") {
    assert(max(List()).isEmpty)
    assert(max(List(1)).contains(1))
    assert(max(List(1, 2)).contains(2))
    assert(max(List(2, 1)).contains(2))
    assert(max(List(-2, 1)).contains(1))
  }

  test("testPascalsTriangle") {

  }

  test("testFactorial") {
    assert(factorial(0) == 1)
    assert(factorial(1) == 1)
    assert(factorial(2) == 2)
    assert(factorial(3) == 6)
    assert(factorial(4) == 24)
    //println(factorial(100000))
  }

  test("testFactorialTailRecursive") {
    assert(factorialTailRecursive(0) == 1)
    assert(factorialTailRecursive(1) == 1)
    assert(factorialTailRecursive(2) == 2)
    assert(factorialTailRecursive(3) == 6)
    assert(factorialTailRecursive(4) == 24)
    println(factorialTailRecursive(100000))
  }

  test("testFibonacciSlow") {
    assert(fibonacciSlow(0) == 0)
    assert(fibonacciSlow(1) == 1)
    assert(fibonacciSlow(2) == 1)
    assert(fibonacciSlow(3) == 2)
    assert(fibonacciSlow(4) == 3)
    //assert(fibonacciSlow(50) == 12586269025L)
  }

  test("testFibonacciFast") {
    assert(fibonacciFast(0) == 0)
    assert(fibonacciFast(1) == 1)
    assert(fibonacciFast(2) == 1)
    assert(fibonacciFast(3) == 2)
    assert(fibonacciFast(4) == 3)
    assert(fibonacciFast(1000) == 817770325994397771L)
  }
}
