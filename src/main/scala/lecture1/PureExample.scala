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

  def bmiCalculator(weight: Int, heightInCm: Int): Unit = {
    val heightInMeters = heightInCm.toDouble / 100
    weight / (heightInMeters * heightInMeters) match {
      case bmi@_ if bmi < 18.5 => println(s"Sorry, your underweight, your bmi is: $bmi")
      case bmi@_ if bmi >= 18.5 && bmi < 25 => println(s"Your normal and your bmi is: $bmi")
      case bmi@_ if bmi >= 25 => println(s"Your overweight, your bmi is: $bmi")
    }
  }

  def main(args: Array[String]): Unit = {
    bmiCalculator(70, 178)
  }

}

object PureAssignment {

}
