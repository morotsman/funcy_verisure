package excercises.recursion

import org.scalatest.FunSuite

class RecursiveTest extends FunSuite {

  test("sum") {
    assert(Recursive.sum(List(0)) == 0)
    assert(Recursive.sum(List(1)) == 1)
    assert(Recursive.sum(List(1, 2, 3)) == 6)
  }

}
