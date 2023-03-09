object Appl531 {
  def genSumArray(elems: Array[Int], f: (Int) => Int) = {
    var sum = 0
    for (e <- elems) sum += f(e)
    sum
  }

  def sum(elems: Array[Int], log: (String) => Unit) = {
    log("Normal") 
    genSumArray(elems, e=>e)
  }
  def sumSqr(elems: Array[Int], log: (String) => Unit) = {
    log("Square")
    genSumArray(elems, e=>e*e)
  }
  def sumCube(elems: Array[Int], log: (String) => Unit) = {
    log("Cubic")
    genSumArray(elems, e => e*e*e)
  }
  def sumAbs(elems: Array[Int], log: (String) => Unit) = {
    log("Absolute")
    genSumArray(elems, Math.abs(_))
  }

  def main(args: Array[String]): Unit = {
    val a = Array(1,2,3,4,-5)
    val logToStdOut = (msg: String) => { println(msg) }
    val noLogo = (msg: String) => {}

    println("sum(a) = " + sum(a, noLogo))
    println("sumSqr(a) = " + sumSqr(a, logToStdOut))
    println("sumCube(a) = " + sumCube(a, logToStdOut))
    println("sumAbs(a) = " + sumAbs(a, logToStdOut))
  }
}