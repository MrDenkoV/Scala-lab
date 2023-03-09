/*class SuperA1
class A1 extends SuperA1
class SubA1 extends A1

class GenInVar[T]
class GenCoVar[+T]
class GenContrVar[-T]

object Appl {
  def mInVarA1(inVar: GenInVar[A1]) = {}
  def mCoVarA1(coVar: GenCoVar[A1]) = {}
  def mContrVarA1(contrVar: GenContrVar[A1]) = {}
  def main(args: Array[String]) = {
    // mInVarA1(new GenInVar[SuperA1]) // (1)
    mInVarA1(new GenInVar[A1])
    // mInVarA1(new GenInVar[SubA1]) // (2)

    // mCoVarA1(new GenCoVar[SuperA1]) // (3)
    mCoVarA1(new GenCoVar[A1])

    // GenCoVar[SubA1] <: GenCoVar[A1]
    mCoVarA1(new GenCoVar[SubA1])

    // GenContrVar[SuperA1] <: GenContrVar[A1]
    mContrVarA1(new GenContrVar[SuperA1])
    mContrVarA1(new GenContrVar[A1])
    mContrVarA1(new GenContrVar[SubA1]) // (4)  
  }
}*/

/*
main11.scala:14: error: type mismatch;
 found   : GenInVar[SuperA1]
 required: GenInVar[A1]
Note: SuperA1 >: A1, but class GenInVar is invariant in type T.
You may wish to define T as -T instead. (SLS 4.5)
    mInVarA1(new GenInVar[SuperA1]) // (1)
             ^
one error found
*/

/*
main11.scala:16: error: type mismatch;
 found   : GenInVar[SubA1]
 required: GenInVar[A1]
Note: SubA1 <: A1, but class GenInVar is invariant in type T.
You may wish to define T as +T instead. (SLS 4.5)
    mInVarA1(new GenInVar[SubA1]) // (2)
             ^
one error found
*/

/*
main11.scala:18: error: type mismatch;
 found   : GenCoVar[SuperA1]
 required: GenCoVar[A1]
    mCoVarA1(new GenCoVar[SuperA1]) // (3)
             ^
one error found
*/

/*
main11.scala:27: error: type mismatch;
 found   : GenContrVar[SubA1]
 required: GenContrVar[A1]
    mContrVarA1(new GenContrVar[SubA1]) // (4)
                ^
one error found
*/


class SuperA1
class A1 extends SuperA1
class SubA1 extends A1

class GenInVar[T](private var arg: T) {
  def in(a: T) = {}
  def inOut(a: T): T = a
  def out: T = arg
}

class GenCoVar[+T](private val arg: T) {
  // def in(a: T) {} // (1)
  def inOut[A >: T](a: A): A = a
  def out: T = arg
}

class GenContrVar[-T] /*(private val arg: T)*/ { //(2)
  def in(a: T) = {}
  // def inOut(a: T): T = a (3)
  //def out: T = // ?
}

object Appl {
  def main(args: Array[String]) = {
    val genInVarA1 = new GenInVar[A1](new A1)
    val genCoVarA1 = new GenCoVar[A1](new A1)
    val genContrVarA1 = new GenContrVar[A1]

    // genInVarA1.in(new SuperA1) // (4)
    genInVarA1.in(new A1)
    genInVarA1.in(new SubA1)

    // genInVarA1.inOut(new SuperA1) // (5)
    genInVarA1.inOut(new A1)
    genInVarA1.inOut(new SubA1)

    val r1: SuperA1 = genInVarA1.out
    val r2: A1 = genInVarA1.out
    // val r3: SubA1 = genInVarA1.out // (6)

    val r4: SuperA1 = genCoVarA1.inOut(new SuperA1)
    // val r5: A1 = genCoVarA1.inOut(new SuperA1) // (7)
    // val r6: SubA1 = genCoVarA1.inOut(new SuperA1) // (8)
    val r7: SuperA1 = genCoVarA1.inOut(new A1)
    val r8: A1 = genCoVarA1.inOut(new A1)
    // val r9: SubA1 = genCoVarA1.inOut(new A1) // (9)
    val r10: SuperA1 = genCoVarA1.inOut(new SubA1)
    val r11: A1 = genCoVarA1.inOut(new SubA1)
    // val r12: SubA1 = genCoVarA1.inOut(new SubA1) // (10)
    val r12: SuperA1 = genCoVarA1.out
    val r13: A1 = genCoVarA1.out
    // val r14: SubA1 = genCoVarA1.out // (11)

    // genContrVarA1.in(new SuperA1) // (12)  
    genContrVarA1.in(new A1)
    genContrVarA1.in(new SubA1)
  }
}


/*
main11.scala:83: error: covariant type T occurs in contravariant position in type T of value a
  def in(a: T) {} // (1)
         ^
one error found
*/

/*
main11.scala:98: error: not enough arguments for constructor GenContrVar: (arg: A1)GenContrVar[A1].
Unspecified value parameter arg.
    val genContrVarA1 = new GenContrVar[A1]
                        ^
one error found
*/

/*
main11.scala:90: error: T does not take parameters
  def inOut(a: T): T = a (3)
                         ^
one error found
*/

/*
main11.scala:100: error: type mismatch;
 found   : SuperA1
 required: A1
    genInVarA1.in(new SuperA1) // (4)
                  ^
one error found
*/

/*
main11.scala:104: error: type mismatch;
 found   : SuperA1
 required: A1
    genInVarA1.inOut(new SuperA1) // (5)
                     ^
one error found
*/

/*
main11.scala:110: error: type mismatch;
 found   : A1
 required: SubA1
    val r3: SubA1 = genInVarA1.out // (6)
                               ^
one error found
*/

/*
main11.scala:113: error: type mismatch;
 found   : SuperA1
 required: A1
    val r5: A1 = genCoVarA1.inOut(new SuperA1) // (7)
                                  ^
one error found
*/

/*main11.scala:114: error: type mismatch;
 found   : SuperA1
 required: SubA1
    val r6: SubA1 = genCoVarA1.inOut(new SuperA1) // (8)
                                     ^
one error found
*/

/*
main11.scala:117: error: type mismatch;
 found   : A1
 required: SubA1
    val r9: SubA1 = genCoVarA1.inOut(new A1) // (9)
                                     ^
one error found
*/

/*
main11.scala:121: error: r12 is already defined as value r12
    val r12: SuperA1 = genCoVarA1.out
        ^
main11.scala:120: error: type mismatch;
 found   : A1
 required: SubA1
    val r12: SubA1 = genCoVarA1.inOut(new SubA1) // (10)
                                     ^
two errors found
*/

/*
main11.scala:123: error: type mismatch;
 found   : A1
 required: SubA1
    val r14: SubA1 = genCoVarA1.out // (11)
                                ^
one error found
*/

/*
main11.scala:125: error: type mismatch;
 found   : SuperA1
 required: A1
    genContrVarA1.in(new SuperA1) // (12)
                     ^
one error found
*/
