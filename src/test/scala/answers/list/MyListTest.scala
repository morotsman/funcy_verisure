package answers.list

import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, Properties}

object MyListSpecification extends Properties("MyList") {

  def id[A](a: A): A = a

  property("map identity") = forAll { (li: List[Int]) =>
    val myList = MyList.fromScalaList(li)
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
    val positive = MyList.fromScalaList(la)
    val negative = MyList.fromScalaList(lb)
    positive.append(negative).takeWhile(i => i > 0) == positive
  }

  property("headOption") = forAll { (la: List[Int]) =>
    val mla = MyList.fromScalaList(la)
    if (mla.lenght() > 0)
      MyList.unit(mla.headOption.get) == mla.take(1)
    else
      mla.headOption.isEmpty
  }

  property("map2") =
    forAll { (la: List[Int], lb: List[Int]) =>
      val mla = MyList.fromScalaList(la)
      val mlb = MyList.fromScalaList(lb)
      val expectedLength = mlb.lenght() min mla.lenght()
      product(mla, mlb).lenght() == expectedLength
    } && forAll { (la: List[Int], lb: List[Int]) =>
      val mla = MyList.fromScalaList(la)
      val mlb = MyList.fromScalaList(lb)
      val expectedLength = mlb.lenght() min mla.lenght()
      mlb.map2(mla)((_, a) => a) == mla.take(expectedLength)
    } && forAll { (la: List[Int], lb: List[Int]) =>
      val mla = MyList.fromScalaList(la)
      val mlb = MyList.fromScalaList(lb)
      val expectedLength = mlb.lenght() min mla.lenght()
      mlb.map2(mla)((b, _) => b) == mlb.take(expectedLength)
    } && forAll { (la: List[Int], lb: List[Int], lc: List[Int]) =>
      val mla = MyList.fromScalaList(la)
      val mlb = MyList.fromScalaList(lb)
      val mlc = MyList.fromScalaList(lc)
      product(product(mla, mlb), mlc) == product(mla, product(mlb, mlc)).map(assoc)
    } && forAll { (la: List[Int], lb: List[Int]) =>
      val mla = MyList.fromScalaList(la)
      val mlb = MyList.fromScalaList(lb)

      def f(a: Int): Int = a
      def g(a: Int): Int = a
      mla.map2(mlb)(productF(f, g)) == product(mla.map(f), mlb.map(g))
    }

  def product[A, B](fa: MyList[A], fb: MyList[B]): MyList[(A, B)] =
    fa.map2(fb)((a, b) => (a, b))

  def assoc[A, B, C](p: (A, (B, C))): ((A, B), C) = p match {
    case (a, (b, c)) => ((a, b), c)
  }

  def productF[I1, O1, I2, O2](f: I1 => O1, g: I2 => O2): (I1, I2) => (O1, O2) =
    (i1, i2) => (f(i1), g(i2))

  property("unit") =
    forAll { (i: Int) =>
      MyList.unit(i) == Cons(i, Nil)
    }

  property("flatMap") =
    forAll { (li: List[Int]) =>
      val mli = MyList.fromScalaList(li)
      mli.flatMap(MyList.unit) == mli
    } && forAll { (i: Int) =>
      val f: Int => MyList[Int] =
        in => MyList.unit(in)
      MyList.unit(i).flatMap(f) == f(i)
    } && forAll { (li: List[Int]) =>
      val mli = MyList.fromScalaList(li)
      val f: Int => MyList[Int] = in => MyList.unit(in)
      val g: Int => MyList[Int] = in => MyList.unit(in)

      mli.flatMap(f).flatMap(g) == mli.flatMap(i => f(i).flatMap(g))
    }

}
