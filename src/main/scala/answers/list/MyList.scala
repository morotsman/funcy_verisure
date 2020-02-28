package answers.list

case class Cons[+A](head: A, override val tail: MyList[A]) extends MyList[A]

case object Nil extends MyList[Nothing]

sealed trait MyList[+A] {
  def map[B](f: A => B): MyList[B] = this match {
    case Nil => Nil
    case Cons(a, as) => Cons(f(a), as.map(f))
  }

  def tail(): MyList[A] = this match {
    case Nil => Nil
    case Cons(a, as) => as
  }

  def lenght(): Int = this match {
    case Nil => 0
    case Cons(_, as) => 1 + as.lenght()
  }

  def append[B >: A](lb: MyList[B]): MyList[B] = (this, lb) match {
    case (_, Nil) => this
    case (Nil, _) => lb
    case (Cons(a, as), _) => Cons(a, as.append(lb))
  }

  def drop(number: Int): MyList[A] = this match {
    case Cons(_, as) if number > 0 => as.drop(number - 1)
    case _ => this
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

  def takeWhile(f: A => Boolean): MyList[A] = this match {
    case Cons(a, as) if f(a) => Cons(a, as.takeWhile(f))
    case _ => Nil
  }

  def headOption: Option[A] = this match {
    case Nil => None
    case Cons(a, as) => Some(a)
  }

  def map2[B, C](lb: MyList[B])(f: (A, B) => C): MyList[C] = (this, lb) match {
    case (Cons(a, as), Cons(b, bs)) => Cons(f(a, b), as.map2(bs)(f))
    case _ => Nil
  }



  def flatMap[B](a: A => MyList[B]): MyList[B] = ???

  def reduce[B](z: B)(f: A => B): B = ???
}

object MyList {
  def apply[A](as: A*): MyList[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  def fromScalaList[A](as: List[A]): MyList[A] =
    as.reverse.foldLeft(Nil: MyList[A])((b, a) => Cons(a, b))

  def unit[A](a: A): MyList[A] =
    MyList(a)

  def apply[A, B](fab: MyList[A => B])(fa: MyList[A]): MyList[B] =
    fab.map2(fa)((ab, a) => ab(a))
}


