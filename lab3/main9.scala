// class CC(x: Int)

// kompiluje się i tworzą się dwa pliki

/*
ompiled from "main9.scala"
public class CC {
  public CC(int);
}
*/

// case class CC(x: Int)

// kompiluje się i tworzą się dwa pliki

/*
Compiled from "main9.scala"
public class CC implements scala.Product,java.io.Serializable {
  private final int x;
  public static scala.Option<java.lang.Object> unapply(CC);
  public static CC apply(int);
  public static <A> scala.Function1<java.lang.Object, A> andThen(scala.Function1<CC, A>);
  public static <A> scala.Function1<A, CC> compose(scala.Function1<A, java.lang.Object>);
  public scala.collection.Iterator<java.lang.String> productElementNames();
  public int x();
  public CC copy(int);
  public int copy$default$1();
  public java.lang.String productPrefix();
  public int productArity();
  public java.lang.Object productElement(int);
  public scala.collection.Iterator<java.lang.Object> productIterator();
  public boolean canEqual(java.lang.Object);
  public java.lang.String productElementName(int);
  public int hashCode();
  public java.lang.String toString();
  public boolean equals(java.lang.Object);
  public CC(int);
}
*/

/*
Compiled from "main9.scala"
public final class CC$ extends scala.runtime.AbstractFunction1<java.lang.Object, CC> implements java.io.Serializable {
  public static final CC$ MODULE$;
  public static {};
  public final java.lang.String toString();
  public CC apply(int);
  public scala.Option<java.lang.Object> unapply(CC);
  private java.lang.Object writeReplace();
  public java.lang.Object apply(java.lang.Object);
  private CC$();
}
*/


// object O1 { val x = 2 }

// kompiluje się i tworzą się dwa pliki

/*
Compiled from "main9.scala"
public final class O1 {
  public static int x();
}
*/

/*
Compiled from "main9.scala"
public final class O1$ {
  public static final O1$ MODULE$;
  private static final int x;
  public static {};
  public int x();
  private O1$();
}
*/


// case object O1 { val x = 2 }

// kompiluje się i tworzą się dwa pliki

/*
Compiled from "main9.scala"
public final class O1 {
  public static java.lang.String toString();
  public static int hashCode();
  public static boolean canEqual(java.lang.Object);
  public static scala.collection.Iterator<java.lang.Object> productIterator();
  public static java.lang.Object productElement(int);
  public static int productArity();
  public static java.lang.String productPrefix();
  public static int x();
  public static scala.collection.Iterator<java.lang.String> productElementNames();
  public static java.lang.String productElementName(int);
}

*/

/*
Compiled from "main9.scala"
public final class O1$ implements scala.Product,java.io.Serializable {
  public static final O1$ MODULE$;
  private static final int x;
  public static {};
  public java.lang.String productElementName(int);
  public scala.collection.Iterator<java.lang.String> productElementNames();
  public int x();
  public java.lang.String productPrefix();
  public int productArity();
  public java.lang.Object productElement(int);
  public scala.collection.Iterator<java.lang.Object> productIterator();
  public boolean canEqual(java.lang.Object);
  public int hashCode();
  public java.lang.String toString();
  private java.lang.Object writeReplace();
  private O1$();
}
*/


// class C1(val x: Int)
// class SubC1(x: Int) extends C1(x)

// skompilowało się każdy


// class C1(val x: Int)
// case class SubC1(x: Int) extends C1(x)

// nie skompilowało się, bo  w definicji case class nie może być ta sama zmienna co przy definicji klasy rozszerzanej, która jest stała

// class C1(val x: Int)
// case class SubC1(y: Int) extends C1(y)

// skompilowało się

// case class C1(x: Int)
// class SubC1(x: Int) extends C1(x)

// skompilowało się

// case class C1(x: Int)
// case class SubC1(x: Int) extends C1(x)

// nie skompilowało się, bo nie można rozszerzyć case klasy, case klasą




/*class C1 extends T1 {
  def f(i: Int) = { println(i) }
}*/
object Appl {
  def main(args: Array[String]) = {
    (new C1).f(3)
  }
}

// wszystko działa i wypisuje 3

/*
main9.scala:151: error: illegal inheritance from sealed trait T1
class C1 extends T1 {
                 ^
one error found
*/ // nie można rozszerzyć sealed traita

// zadziałało i zwróciło 3, jak klasa C1 rozszerza sealed traita, w pliku w którym ten trait się znajduje
