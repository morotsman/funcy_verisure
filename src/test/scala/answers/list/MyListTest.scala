package answers.list

import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}

object MyListSpecification extends Properties("MyList") {


  def id[A](a: A): A = a

  property("map identity") = forAll { (li: List[Int]) =>
    val myList = MyList.fromScalaList(li)
    myList.map(id) == myList
  }

  /*
    For any functor f, and any functions foo and bar, map(foo, map(bar, f)) must be equal to map(contents => foo(bar(contents)), f).
 */
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

  property("take") = forAll { (la: List[Int], lb: List[Int]) =>
    val mla = MyList.fromScalaList(la)
    val mlb = MyList.fromScalaList(lb)
    mla.append(mlb).take(mla.lenght()) == mla
  }

  val positiveNumbers = Gen.listOf(Gen.choose(1, Int.MaxValue))
  val negativeNumbers = Gen.listOf(Gen.choose(Int.MinValue, -3))

  property("takeWhile") = forAll(positiveNumbers, negativeNumbers) { (la: List[Int], lb: List[Int]) =>
    val mla = MyList.fromScalaList(la)
    val mlb = MyList.fromScalaList(lb)
    mla.append(mlb).takeWhile(i => i > 0) == mla
  }

  property("headOption") = forAll { (la: List[Int]) =>
    val mla = MyList.fromScalaList(la)
    if (mla.lenght() > 0)
      MyList.unit(mla.headOption.get) == mla.take(1)
    else
      mla.headOption.isEmpty
  }
}
