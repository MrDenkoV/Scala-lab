def maxOf[T](xs: List[T], fMax: (T, T) => T) = {
  @annotation.tailrec
  def iter(xs: List[T], maxV: T): T = xs match {
    case List() => maxV
    case h :: t => iter(t, fMax(h, maxV))
  }
  iter(xs, xs(0))
}