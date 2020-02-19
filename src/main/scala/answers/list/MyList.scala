package answers.list

case class Cons[+A](head: A, tail: MyList[A]) extends MyList[A]

case object Nil extends MyList[Nothing]

sealed trait MyList[+A] {
  def unit[B >: A](b: B): List[B] =
    List(b)

  def map[B](f: A => B): MyList[B] = this match {
    case Nil => Nil
    case Cons(a, as) => Cons(f(a), as.map(f))
  }

  def lenght(): Int = this match {
    case Nil => 0
    case Cons(a, as) => 1 + as.lenght()
  }

  def filter(f: A => Boolean): MyList[A] = this match {
    case Nil => Nil
    case Cons(a, as) if f(a) => Cons(a, as.filter(f))
    case Cons(a, as) => as.filter(f)
  }

  def take(number: Int): MyList[A] = this match {
    case Cons(a, as) if number > 0 => Cons(a, as.take(number - 1))
    case _ => Nil
  }

  def startWith[B >: A](la: MyList[B]): Boolean = (this, la) match {
    case (_, Nil) => true
    case (Cons(a, as), Cons(b, bs)) if a == b => as.startWith(bs)
    case _ => false
  }

  def takeWhile(f: A => Boolean): MyList[A] = ???

  def headOption: Option[A] = ???

  def map2[B, C](lb: MyList[B])(f: (A, B) => C): MyList[C] = ???

  def flatMap[B](a: A => MyList[B]): MyList[B] = ???
}

object MyList {
  def apply[A](as: A*): MyList[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  def fromScalaList[A](as: List[A]): MyList[A] =
    as.reverse.foldLeft(Nil: MyList[A])((b, a) => Cons(a, b))
}


