package lecture1

import org.scalatest.FunSuite

class PureExampleTest extends FunSuite {

  test("add") {
    assert(PureExample.add(1,1) == 2)
  }

  test("createMessage") {
    assert(PureExample.createMessage(4) == "The result of a + b is 4")
  }

}
