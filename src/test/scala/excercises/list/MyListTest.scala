package excercises.list

import org.scalacheck.Prop.forAll
import org.scalacheck.Properties

object MyListSpecification extends Properties("MyList") {

  property("map identity") = forAll { (la: List[Int]) =>
    val myList = MyList.fromScalaList(la)
    myList.map(a => a) == myList
  }


}
