package p1
package p11 {
  private[p1] class C1 {
    protected[p1] def protP1MC1() = {
      print("[protP1MC1]: "); privP11MC1()
    }
    private[p1] def privP1MC1() = {
      print("[privP1MC1]: ");  privP11MC1()
    }
    private[p11] def privP11MC1() = {
      print("[privP11MC1]: "); println((new C11).a13)
    }
    protected[p11] def protP11MC1() = {
      print("[protP11MC1]: "); privP11MC1()
    }
    private[C1] var a11 = 11
    private[this] var a12 = 12
    class C11 {
      private[C1] val a13 = 13
    }
    override def equals(other: Any): Boolean = other match {
      case that: C1 => (this.a11 == that.a11)
      // && (this.a12 == that.a12) // (1)
      case _ => false
    }
  }
}
package p12 {
  import p11._
  object O1 {
    private[p12] val ao1 = new C1
  }
  class C12 extends C1 {
    def mC121() = { print("[mC121]: "); super.protP11MC1() }
    def mC122() { print("[mC122]: "); super.privP11MC1() } // (2)
  }
}
object Appl {
  def main(args: Array[String]) = {
    val c1 = new p11.C1
    c1.protP1MC1()
    c1.privP1MC1()
    val c12 = new p12.C12
    c12.mC121()
  }
}

// Skompilowało się plik Appl.class znajduje się w folderze(pakecie) p1 - na samym początku main8.scala jest package p1 bez klamerek, więc obejmuje całą zawartość

// Scala Appl zwraca błąd Exception in thread "main" java.lang.NoClassDefFoundError: Appl (wrong name: p1/Appl)

/*
Compiled from "main8.scala"
public final class p1.Appl {
  public static void main(java.lang.String[]);
}
*/ // Nie ma Appl, jest p1.Appl

// No such file or class on classpath: p1.Appl
// Nie ma p1/p1/Appl

/*
[protP1MC1]: [privP11MC1]: 13
[privP1MC1]: [privP11MC1]: 13
[mC121]: [protP11MC1]: [privP11MC1]: 13
*/ //działa scala p1.Appl z nadrzędnego

/*
main8.scala:23: error: not found: value &&
      && (this.a12 == that.a12) // (1)
      ^
main8.scala:23: error: value a12 is not a member of p1.p11.C1
did you mean a11?
      && (this.a12 == that.a12) // (1)
                           ^
two errors found
*/ // private[this] var a12 -> dostęp tylko z instancji, więc przy porównywaniu nie możemy dostać wartości that.a12

/*
main8.scala:35: error: method privP11MC1 in class C1 cannot be accessed in p1.p11.C1
    def mC122() { print("[mC122]: "); super.privP11MC1() } // (2)
                                            ^
one error found
*/ // private[p11] def def privP11MC1() -> dostęp tylko z pakietu p11, a funkcja mC122 jest poza tym pakietem i tylko go importuje


/*
package z C1:
privP11MC1()
protP11MC1()
Protected z C1:
protP1MC1()
privP1MC1()
*/