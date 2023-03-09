package p1 {
  package p2 {
    class A1 { println("p1.p2.A1") }
    class A22 {
      println("Calling new p1.p2.A1") 
      new A1
      println("Calling new _root_.p1.A1")
      new _root_.p1.A1
      println("p1.p2.A22")
    }
  }
}

object Appl {
  def main(args: Array[String]):Unit = {
    new p1.A1
    new p1.p2.A21
    new p1.p2.A22
  }
}
