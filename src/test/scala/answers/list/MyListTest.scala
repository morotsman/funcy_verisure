package answers.list

import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}

object MyListSpecification extends Properties("MyList") {

  property("map identity") = forAll { (li: List[Int]) =>
    val myList = MyList.fromScalaList(li)
    myList.map(i => i) == myList
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
