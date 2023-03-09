object Appl {
  def main(args: Array[String]) = {
    import p1._
    println(m1(2))
    println(m2(5.5))
  }
}

// kompiluje się i działa poprawnie

/*
Compiled from "main4.scala"
public final class Appl {
  public static void main(java.lang.String[]);
}
*/

/*
Compiled from "package.scala"
public final class p1.package {
  public static double m2(double);
  public static int m1(int);
}
*/

/*
4
8.25
*/