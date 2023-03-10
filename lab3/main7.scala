/*class Int2DVec(val x: Int, val y: Int){
  override def equals(other: Int2DVec): Boolean =
    this.x == other.x && this.y == other.y
}*/

/*class Int2DVec(val x: Int, val y: Int) {
  override def equals(other: Any): Boolean = {
    other match {
      case that: Int2DVec =>
        (this.x == that.x) && (this.y == that.y)
      case _ => false
    }
  }
  override def hashCode = 41 * (41 + x) + y
}

object Appl {
  def checkPredicate(pred: Boolean, predAsString: String) = {
    if (pred) println(predAsString + ": OK")
    else println(predAsString + ": Failed")
  }

  def main(args: Array[String]) = {
    val v1 = new Int2DVec(4, 5)
    val v2 = new Int2DVec(4, 5)
    checkPredicate(v1 equals v2, "v1 equals v2")
    checkPredicate(!(v1 eq v2), "!(v1 eq v2)")
    checkPredicate(v1 == v2, "v1 == v2")
  }
}*/

/*
v1 equals v2: Failed
!(v1 eq v2): OK
v1 == v2: Failed
*/ // żaden z tych sposobów porównania nie porównuje wartości obiektów tylko instance - bo nie mamy metody equals

/*
v1 equals v2: OK
!(v1 eq v2): OK
v1 == v2: Failed
*/ // po dodaniu metody equals, wywołujemy ją, jednak dopóki nie dodamy override equals == nie będzie działać,
// ponieważ nie nadpisujemy metody equals(:Any), z której korzysta ==

/*
main7.scala:2: error: method equals overrides nothing.
Note: the super classes of class Int2DVec contain the following, non final members named equals:
def equals(x$1: Object): Boolean
  override def equals(other: Int2DVec): Boolean =
               ^
one error found
*/ // nie możemy nadpisać nieistniejącej metody, a nie ma wbudowanej metody porównującej Int2DVec

/*
v1 equals v2: OK
!(v1 eq v2): OK
v1 == v2: OK
*/ // Wszystko działa po nadpisaniu equals(:Any)




class Int2DVec(val x: Int, val y: Int) {
   override def equals(other: Any): Boolean = {
     //print("[Int2DVec.equals(other: Any) called] ")
     other match {
       case that: Int2DVec =>
         (that canEqual this) && (this.x == that.x) && (this.y == that.y)
         case _ => false
     }
   }
   override def hashCode = {
     //print("[Int2DVec.hashCode called] ")
     41 * (41 + x) + y
   }
   def canEqual(other: Any) = other.isInstanceOf[Int2DVec]
 }

 class MutableInt2DVec(var x: Int, var y: Int) {
   override def equals(other: Any): Boolean = {
     //print("[MutableInt2DVec.equals(other: Any) called] ")
     other match {
       case that: MutableInt2DVec =>
         (that canEqual this) &&
         (this.x == that.x) &&
         (this.y == that.y)
       case _ => false
     }
   }
   override def hashCode = {
    //print("[MutableInt2DVec.hashCode called] ")
    41 * (41 + x) + y
   }
   def canEqual(other: Any) = other.isInstanceOf[MutableInt2DVec]
 }

 class Int2DArrow(x: Int, y: Int, val width: Int)
 extends Int2DVec(x, y) {
   override def equals(other: Any): Boolean = {
     //print("[Int2DArrow.equals(other: Any) called] ")
     other match {
       case that: Int2DArrow =>
         (that canEqual this) &&
         (this.width == that.width) &&
         super.equals(that)
       case _ => false
     }
   }
   override def hashCode = {
     //print("[Int2DArrow.hashCode called] ")
     41 * super.hashCode + width
   }
   override def canEqual(other: Any) = other.isInstanceOf[Int2DArrow]
 }

 object testSuite {
   private var fail = false
   private def reset() { fail = false }
   private def signallFail() { fail = true }
   private def printTestResult() {
     if (fail) println("******* TESTS FAILED *******")
     else println("******* TESTS PASSED *******")
   }

   import scala.collection.mutable._

   def checkPredicate(pred: Boolean, predAsString: String) {
     if (pred) {
       println(predAsString + ": OK")
     } else {
       println(predAsString + ": Failed")
       signallFail()
     }
   }
   def tc1() {
     val v1, v2 = new Int2DVec(4, 5)
     checkPredicate(v1 equals v2, "v1 equals v2")
     checkPredicate(!(v1 eq v2), "!(v1 eq v2)")
     checkPredicate(v1 == v2, "v1 == v2")
   }
   def tc2() {
     val v1, v2 = new Int2DVec(4, 5)
     val vectors = HashSet(v1)
     checkPredicate(vectors.contains(v1), "vectors.contains(v1)")
     checkPredicate(vectors.contains(v2), "vectors.contains(v2)")
   }
   def tc3() {
     val v1, v2 = new Int2DVec(4, 5)
     val v2AsAny: Any = v2
     checkPredicate(v1 equals v2AsAny, "v1 equals v2AsAny")
     checkPredicate(v1 == v2AsAny, "v1 == v2AsAny")
   }
   def tc4() {
     var mutV1 = new MutableInt2DVec(4, 5)
     val mutVectors = HashSet(mutV1)
     checkPredicate(mutVectors.contains(mutV1),
                    "[Before change] mutVectors.contains(mutV1)")
     mutV1.x *= 2
     checkPredicate((!mutVectors.contains(mutV1)),
                    "[After change] (!mutVectors.contains(mutV1))")
   }
   def tc5() {
     val v1 = new Int2DVec(4, 5)
     val a1 = new Int2DArrow(4, 5, 1)
     checkPredicate(!(v1 equals a1), "!(v1 equals a1)")
     checkPredicate(!(v1 == a1), "!(v1 == a1)")
     checkPredicate(!(a1 equals v1), "!(a1 equals v1)")
     checkPredicate(!(a1 == v1), "!(a1 == v1)")
     checkPredicate(!(HashSet[Int2DVec](v1) contains a1),
                    "!    (HashSet[Int2DVec](v1) contains a1)")
     checkPredicate(!(HashSet[Int2DVec](a1) contains v1), 
                    "!(HashSet[Int2DVec](a1) contains v1)")
   }
   def run() {
     reset()
     try {
       tc1(); tc2(); tc3(); tc4(); tc5()
     } finally { printTestResult() }
   }
 }

 object Appl {
   def main(args: Array[String]) {
     testSuite.run()
   }
 }

 /*
 v1 equals v2: OK
!(v1 eq v2): OK
v1 == v2: OK
vectors.contains(v1): OK
vectors.contains(v2): OK
v1 equals v2AsAny: OK
v1 == v2AsAny: OK
[Before change] mutVectors.contains(mutV1): OK
[After change] (!mutVectors.contains(mutV1)): OK
!(v1 equals a1): OK
!(v1 == a1): OK
!(a1 equals v1): OK
!(a1 == v1): OK
!    (HashSet[Int2DVec](v1) contains a1): OK
!(HashSet[Int2DVec](a1) contains v1): OK
******* TESTS PASSED *******
*/