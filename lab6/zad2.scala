def lengthOf[T](xs: List[T]) = {
  @annotation.tailrec
  def iter(ys: List[T], acc: Int): Int = ys match {
    case List() => acc
    case y :: ys1 => iter(ys1, acc + 1)
  }
  iter(xs, 0)
}

lengthOf(Nil)
lengthOf(1::2::3::Nil)
lengthOf(1::Nil)
