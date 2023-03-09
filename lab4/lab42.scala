object Appl42 {
  def checkPredicate(pred: Boolean,
                     predAsString: String,
                     prefix: String = "Checking if ") = {
    if (pred) println(prefix + predAsString + ": OK")
    else println(prefix + predAsString + ": Fail")
  }
  def sumArrayIter(elems: Array[Int], start: Int = 0, op:(Int, Int) => Int = (x: Int, y:Int) => x+y, mod:(Int) => Int = identity) = {
    var sum = start
    for (i <- elems) sum = op(mod(i), sum)
    sum
  }
  def sumArrayRec1(i: Int, elems: Array[Int], start: Int = 0, op:(Int, Int) => Int = (x: Int, y:Int) => x+y, mod:(Int) => Int = identity): Int = {
    if (i < elems.size) op(mod(elems(i)), sumArrayRec1(i + 1, elems, start, op, mod))
    else start
  }
  def sumArrayRec2(elems: Array[Int], start: Int = 0, op:(Int, Int) => Int = (x: Int, y:Int) => x+y, mod:(Int) => Int = identity) = {
    val size = elems.size
    def goFrom(i: Int): Int = {
        if (i < size) op(mod(elems(i)), goFrom(i+1))
        else start
    }
    goFrom(0)
  }

  def sumSqrArrayRec3(elems: Array[Int]) = { //zad 4.4
    import scala.annotation.tailrec
    val size = elems.size
    @tailrec
    def goFrom(i: Int, acc: Int): Int = {
      if (i < size) goFrom(i + 1, acc + elems(i)*elems(i))
      else acc
    }
    goFrom(0, 0)
  }

  def main(args: Array[String]) = {
    val a1To5 = (1 to 5).toArray
    println("Testing with a1To5 = " +
            a1To5.mkString("Array(", ", ", ") ..."))
    val expectResult = 15
    checkPredicate(sumArrayIter(a1To5) == expectResult,
                   "sumArrayIter(a1To5) == " + expectResult)
    checkPredicate(sumArrayRec1(0, a1To5) == expectResult,
                   "sumArrayRec1(0, a1To5) == " + expectResult)
    checkPredicate(sumArrayRec2(a1To5) == expectResult,
                   "sumArrayRec2(a1To5) == " + expectResult)

    val expectSqrResult = 55
    checkPredicate(sumArrayIter(a1To5, mod=(x:Int)=>x*x) == expectSqrResult,
                   "sumArrayIter(a1To5, mod=(x:Int)=>x*x) == " + expectSqrResult)
    checkPredicate(sumArrayRec1(0, a1To5, mod=(x:Int)=>x*x) == expectSqrResult,
                   "sumArrayRec1(0, a1To5, mod=(x:Int)=>x*x) == " + expectSqrResult)
    checkPredicate(sumArrayRec2(a1To5, mod=(x:Int)=>x*x) == expectSqrResult,
                   "sumArrayRec2(a1To5, mod=(x:Int)=>x*x) == " + expectSqrResult)
    // checkPredicate(sumSqrArrayRec3(a1To5) == expectSqrResult,
    //                "sumSqrArrayRec3(a1To5, mod=(x:Int)=>x*x) == " + expectSqrResult)                   
    println(sumSqrArrayRec3(a1To5))

    val expectProdResult = 120
    checkPredicate(sumArrayIter(a1To5, 1, (x:Int, y:Int)=>x*y) == expectProdResult,
                   "sumArrayIter(a1To5, 1, (x:Int, y:Int)=>x*y) == " + expectProdResult)
    checkPredicate(sumArrayRec1(0, a1To5, 1, (x:Int, y:Int)=>x*y) == expectProdResult,
                   "sumArrayRec1(0, a1To5, 1, (x:Int, y:Int)=>x*y) == " + expectProdResult)
    checkPredicate(sumArrayRec2(a1To5, 1, (x:Int, y:Int)=>x*y) == expectProdResult,
                   "sumArrayRec2(a1To5, 1, (x:Int, y:Int)=>x*y) == " + expectProdResult)
    
    val am3To4 = (-3 to 4).toArray
    println("Testing with a1To5 = " +
            am3To4.mkString("Array(", ", ", ") ..."))
    val expectAbsResult = 16
    checkPredicate(sumArrayIter(am3To4, mod=(x:Int)=>x.abs) == expectAbsResult,
                   "sumArrayIter(am3To4, mod=(x:Int)=>x.abs) == " + expectAbsResult)
    checkPredicate(sumArrayRec1(0, am3To4, mod=(x:Int)=>x.abs) == expectAbsResult,
                   "sumArrayRec1(0, am3To4, mod=(x:Int)=>x.abs) == " + expectAbsResult)
    checkPredicate(sumArrayRec2(am3To4, mod=(x:Int)=>x.abs) == expectAbsResult,
                   "sumArrayRec2(am3To4, mod=(x:Int)=>x.abs) == " + expectAbsResult)
  }
}

// sumArrayRec1 i sumArrayRec2 są niezdefiniowane

// sumArrayRec1 i sumArrayRec2 zwracają 0 więc wyniki nie są równe 15
