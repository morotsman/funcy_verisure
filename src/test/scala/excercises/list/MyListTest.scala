package excercises.list

import org.scalacheck.Prop.forAll
import org.scalacheck.Properties

//https://www.scala-exercises.org/scalacheck/properties
//https://www.scala-exercises.org/scalacheck/generators
//https://fsharpforfunandprofit.com/posts/property-based-testing-2/
object MyListSpecification extends Properties("MyList") {

  def id[A](a: A): A = a

  property("map identity") = forAll { (la: List[Int]) =>
    val myList = MyList.fromScalaList(la)
    myList.map(id) == myList
  }

  property("map associative") = forAll { (li: List[Int]) =>
    val myList = MyList.fromScalaList(li)
    myList.map(id).map(id) == myList.map(i => id(id(i)))
  }

  property("length") = forAll { (la: List[Int]) =>
    val myList = MyList.fromScalaList(la)

    var expectedNumber = 0
    var tmp = myList
    while (tmp != Nil) {
      expectedNumber = expectedNumber + 1
      tmp = tmp.tail
    }

    myList.lenght() == expectedNumber
  }

  property("tail") = forAll { (la: List[Int]) =>
    val mla = MyList.fromScalaList(la)
    mla.drop(1) == mla.tail()
  }

  property("drop") = forAll { (la: List[Int], lb: List[Int]) =>
    val mla = MyList.fromScalaList(la)
    val mlb = MyList.fromScalaList(lb)
    mla.append(mlb).drop(mla.lenght()) == mlb
  }

  property("startsWith") = forAll { (la: List[Int], lb: List[Int]) =>
    val mla = MyList.fromScalaList(la)
    val mlb = MyList.fromScalaList(lb)
    mla.append(mlb).startWith(mla)
  }

  property("append") = forAll { (la: List[Int], lb: List[Int]) =>
    val mla = MyList.fromScalaList(la)
    val mlb = MyList.fromScalaList(lb)
    mla.lenght() + mlb.lenght() == mla.append(mlb).lenght()
  } && forAll { (la: List[Int], lb: List[Int]) =>
    val mla = MyList.fromScalaList(la)
    val mlb = MyList.fromScalaList(lb)
    mla.append(mlb).startWith(mla)
  } && forAll { (la: List[Int], lb: List[Int]) =>
    val mla = MyList.fromScalaList(la)
    val mlb = MyList.fromScalaList(lb)
    mla.append(mlb).drop(mla.lenght()) == mlb
  }

}
