package excercises.lecture2

case class Cons[+A](head: A, tail: MyList[A]) extends MyList[A]
case object Nil extends MyList[Nothing]

sealed trait MyList[+A] {
  def map[B](f: A => B): MyList[B] = this match {
    case Nil => Nil
    case Cons(a, as) => Cons(f(a), as.map(f))
  }

  def filter(f: A => Boolean): MyList[A] = ???

  def take(number: Int): List[A] = ???

  def takeWhile(f: A => Boolean): List[A] = ???

  def headOption: Option[A] = ???
}

object MyList {
  def apply[A](as: A*): MyList[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  def fromScalaList[A](as: List[A]): MyList[A] =
    as.reverse.foldLeft(Nil: MyList[A])((b , a) => Cons(a, b))
}


