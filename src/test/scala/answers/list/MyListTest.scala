package answers.list

import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}

object MyListSpecification extends Properties("MyList") {

  property("map identity") = forAll { (li: List[Int]) =>
    val myList = MyList.fromScalaList(li)
    myList.map(i => i) == myList
  }

  property("filter") = forAll { (li: List[Int]) =>
    val myList = MyList.fromScalaList(li)
    myList.filter(i => true) == myList
  } && forAll { (li: List[Int]) =>
    val myList = MyList.fromScalaList(li)
    myList.filter(i => false) == MyList()
  } && forAll { (li: List[Int]) =>
    val myList = MyList.fromScalaList(li)
    myList.filter(i => i % 2 == 0).filter(i => i % 2 == 0) == myList.filter(i => i % 2 == 0)
  }

  val numbers = Gen.choose(-4, 4)
  val list = Gen.listOf(Gen.choose(-1000, 1000))

  property("take") = forAll(list, numbers) { (li: List[Int], number: Int) =>
    val myList = MyList.fromScalaList(li)
    val expected =
      if (number > myList.lenght())
        myList.lenght()
      else if (number <= 0) {
        0
      } else {
        number
      }
    expected == myList.take(number).lenght()
  } && forAll(list, numbers)((li: List[Int], number: Int) => {
    val myList = MyList.fromScalaList(li)
    myList.startWith(myList.take(number))
  })
}
