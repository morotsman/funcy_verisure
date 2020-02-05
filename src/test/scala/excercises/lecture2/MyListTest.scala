package excercises.lecture2

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll

object MyListSpecification extends Properties("MyList") {

  property("map identity") = forAll { (la: List[Int]) =>
    val myList = MyList.fromScalaList(la)
    myList.map(a => a) == myList
  }


}
