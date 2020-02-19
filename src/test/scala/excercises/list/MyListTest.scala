package excercises.list

import org.scalacheck.Prop.forAll
import org.scalacheck.Properties

//https://www.scala-exercises.org/scalacheck/properties
//https://www.scala-exercises.org/scalacheck/generators
//https://fsharpforfunandprofit.com/posts/property-based-testing-2/
object MyListSpecification extends Properties("MyList") {

  property("map identity") = forAll { (la: List[Int]) =>
    val myList = MyList.fromScalaList(la)
    myList.map(a => a) == myList
  }

  property("length") = forAll { (la: List[Int]) =>
    val myList = MyList.fromScalaList(la)

    var expectedNumber = 0
    var tmp = myList
    while(tmp != Nil) {
      expectedNumber = expectedNumber + 1
      tmp = tmp.tail
    }

    myList.lenght() == expectedNumber
  }

  property("startWith") = forAll { (la: List[Int]) =>
    val myList = MyList.fromScalaList(la)
    true
  }

}
