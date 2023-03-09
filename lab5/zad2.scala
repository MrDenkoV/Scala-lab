val pred = (a: Int) => a-1
val pred: Int => Int = (a: Int) => a-1
val pred: (Int) => Int = (a: Int) => a-1

val maxFrom3: (Double, Double, Double) => Double = (d1:Double, d2:Double, d3:Double) => Math.max(d1, Math.max(d2, d3))