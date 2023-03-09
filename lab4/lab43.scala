object Appl43 {
  def checkPredicate(pred: Boolean,
                     predAsString: String,
                     prefix: String = "Checking if ") = {
    if (pred) println(prefix + predAsString + ": OK")
    else println(prefix + predAsString + ": Fail")
  }
  def maxIter(elems: Array[Int], f:(Int, Int) => Int) = {
    var max = elems(0)
    for (i <- elems) max = f(max, i)
    max
  }
  def maxRec1(i: Int, maxEl: Int, elems: Array[Int], f:(Int, Int) => Int): Int = {
    if (i < elems.size) maxRec1(i+1, f(maxEl, elems(i)), elems, f)
    else maxEl
  }
  def maxRec2(elems: Array[Int], f:(Int, Int) => Int) = {
    def goFrom(i: Int, maxEl: Int, size: Int): Int = {
      if (i < size) goFrom(i+1, f(maxEl, elems(i)), size)
      else maxEl
    }
    goFrom(0, elems(0), elems.size)
  }

  def max2Iter(elems: Array[Int], f:(Int, Int) => Int) = {
    assert(elems.size>=2)
    var max = elems(0)+elems(1)
    for (i <- 2.until(elems.size)) max = f(max, elems(i-1)+elems(i))
    max
  }
  def max2Rec1(i: Int, maxEl: Int, elems: Array[Int], f:(Int, Int) => Int): Int = {
    assert(elems.size>=2)
    if (i+1 < elems.size) maxRec1(i+1, f(maxEl, elems(i+1)+elems(i)), elems, f)
    else maxEl
  }
  def max2Rec2(elems: Array[Int], f:(Int, Int) => Int) = {
    assert(elems.size>=2)
    def goFrom(i: Int, maxEl: Int, size: Int): Int = {
      if (i+1 < size) goFrom(i+1, f(maxEl, elems(i)+elems(i+1)), size)
      else maxEl
    }
    goFrom(1, elems(0)+elems(1), elems.size)
  }

  def main(args: Array[String]) = {
    val arr1 = Array(3, 12, 43, 4, 10)
    println("Testing with arr1 = " +
            arr1.mkString("Array(", ", ", ") ..."))
    val expectResult = 43
    checkPredicate(maxIter(arr1, math.max) == expectResult,
                   "maxIter(arr1, math.max) == " + expectResult)
    checkPredicate(maxRec1(0, arr1(0), arr1, math.max) == expectResult,
                   "maxRec1(0, arr1(0), arr1, math.max) == " + expectResult)
    checkPredicate(maxRec2(arr1, math.max) == expectResult,
                   "maxRec2(arr1, math.max) == " + expectResult)

    val expectMinResult = 3
    checkPredicate(maxIter(arr1, math.min) == expectMinResult,
                   "maxIter(arr1, math.min) == " + expectMinResult)
    checkPredicate(maxRec1(0, arr1(0), arr1, math.min) == expectMinResult,
                   "maxRec1(0, arr1(0), arr1, math.min) == " + expectMinResult)
    checkPredicate(maxRec2(arr1, math.min) == expectMinResult,
                   "maxRec2(arr1, math.min) == " + expectMinResult)

    val expect2Result = 55
    checkPredicate(max2Iter(arr1, math.max) == expect2Result,
                   "max2Iter(arr1, math.max) == " + expect2Result)
    checkPredicate(max2Rec1(1, arr1(0)+arr1(1), arr1, math.max) == expect2Result,
                   "max2Rec1(0, arr1(0), arr1, math.max) == " + expect2Result)
    checkPredicate(max2Rec2(arr1, math.max) == expect2Result,
                   "max2Rec2(arr1, math.max) == " + expect2Result)
  }
}