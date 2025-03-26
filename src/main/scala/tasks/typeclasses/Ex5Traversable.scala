package u04lab
import u03.Sequences.* 
import Sequence.*
import u03.Optionals.*
import Optional.*

/*  Exercise 5: 
 *  - Generalise by ad-hoc polymorphism logAll, such that:
 *  -- it can be called on Sequences but also on Optional, or others... 
 *  -- it does not necessarily call log, but any function with analogous type
 *  - Hint: introduce a type class Traversable[T[_]]], capturing the ability of calling a
 *    "consumer function" on all elements (with type A) of a datastructure T[A] 
 *    Note Traversable is a 2-kinded trait (similar to Filterable, or Monad)
 *  - Write givens for Traversable[Optional] and Traversable[Sequence]
 *  - Show you can use the generalisation of logAll to:
 *  -- log all elements of an Optional, or of a Traversable
 *  -- println(_) all elements of an Optional, or of a Traversable
 */

object Ex5Traversable:

  def log[A](a: A): Unit = println("The next element is: " + a)

  def logAll[T[_]: Traversable, A](t: T[A]): Unit =
    val traversable = summon[Traversable[T]]
    traversable.consumer(t)(log(_))

  def printAll[T[_]: Traversable, A](t: T[A]): Unit =
    val traversable = summon[Traversable[T]]
    traversable.consumer(t)(println(_))

  trait Traversable[T[_]]:
    def consumer[A](t: T[A])(c: A => Unit): Unit

  given Traversable[Sequence] with
    def consumer[A](seq: Sequence[A])(c: A => Unit): Unit = seq match
      case Cons(h, t) => c(h); consumer(t)(c)
      case _ => ()

  given Traversable[Optional] with
    def consumer[A](opt: Optional[A])(c: A => Unit): Unit = opt match
      case Just(a) => c(a)
      case _ => ()

@main def tryTraversable =
  import Ex5Traversable.*

  val seq = Cons(10, Cons(20, Cons(30, Nil())))
  val opt = Just("test")
  val empty = Empty()
  println:
    logAll(seq)
    logAll(opt)
    logAll(empty)

  printAll(seq)
  printAll(opt)
  printAll(empty)
