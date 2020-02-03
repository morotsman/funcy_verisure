package lecture1

object UnPureExample {

  def addAndLog(a: Int, b: Int): Unit = {
    val result = a + b
    println(s"The result of a + b is $result")
  }

  def main(args: Array[String]): Unit = {
    addAndLog(2, 2)
  }

}

object PureExample {

  def add(a: Int, b: Int): Int =
    a + b

  def createMessage(r: Int): String =
    s"The result of a + b is $r"

  def main(args: Array[String]): Unit = {
    println(createMessage(add(2, 2)))
  }

}

object UnPureAssignment {

  

  def main(args: Array[String]): Unit = {

  }

}

object PureAssignment {

}
