object Appl45 {
  import scala.annotation.tailrec

  def sumArrayRec2(elems: Array[Int]) = {
    @tailrec
    def goFrom(i: Int, size: Int, elms: Array[Int]): Int = {
      if (i < size) elms(i) + goFrom(i + 1, size, elms)
      else 0
    }
    goFrom(0, elems.size, elems)
  }

  def sumArrayRec3(elems: Array[Int]) = {
    @tailrec
    def goFrom(i: Int,
               size: Int,
               elms: Array[Int],
               acc: Int): Int = {
      if (i < size) goFrom(i + 1, size, elms, acc + elms(i))
      else acc
    }
    goFrom(0, elems.size, elems, 0)
  }

  def main(args: Array[String]) = {
    println("sumArrayRec3 = " +
            sumArrayRec3((0 until 30000).toArray))
  }
}

// nie mam przepelnienia stosu, poniewaz jest rekursja ogonowa, a ona zmienia rekurencje w petle

/*
lab45.scala:7: error: could not optimize @tailrec annotated method goFrom: it contains a recursive call not in tail position
      if (i < size) elms(i) + goFrom(i + 1, size, elms)
                            ^
one error found
*/ //wykonanie rekurensyjne musi byc na samym koncu aby mozna bylo skorzystac z optymalizacji tailrec

//roznica polega na miejscu dodawania elementu z tablicy, element acc przechowuje dotychczasowa sume