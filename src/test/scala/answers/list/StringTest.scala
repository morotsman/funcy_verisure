package answers.list

import org.scalacheck.Prop.forAll
import org.scalacheck.Properties

object StringSpecification extends Properties("String") {

  property("startsWith") = forAll { (a: String, b: String) =>
    (a+b).startsWith(a)
  }

  property("concatenate") = forAll { (a: String, b: String) =>
    (a+b).length == a.length + b.length
  }

  property("substring") = forAll { (a: String, b: String, c: String) =>
    (a+b+c).substring(a.length, a.length+b.length) == b
  }

  property("endsWith") = forAll { (a: String, b: String) =>
    (a+b).endsWith(b)
  }

  property("contains") = forAll { (a: String, b: String, c: String) =>
    (a+b+c).contains(b)
  }

  property("not contains") = forAll { (a: String, b: String, c: String) =>
    !(a+b+c).contains(a + b + c + "a")
  }
}
