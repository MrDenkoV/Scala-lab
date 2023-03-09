class Pred extends Function1[Int, Int] {
  override def apply(a: Int) = a - 1
}

class NumOps {
  def succ(a: Int) = a + 1
  def pred(a: Int) = a - 1
  def maxFrom3(d1: Double, d2: Double, d3:Double): Double = Math.max(d1, Math.max(d2, d3))
}