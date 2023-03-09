class Outer {
  class Inner {
    def m1In(arg: Outer) = arg
  }
  def m1Out(inArg : this.Inner) = inArg
  def m2Out(outArg : Outer#Inner) = outArg
}

// dwa pliki

/*
Compiled from "main10.scala"
public class Outer {
  public Outer$Inner m1Out(Outer$Inner);
  public Outer$Inner m2Out(Outer$Inner);
  public Outer();
}
*/

/*
Compiled from "main10.scala"
public class Outer$Inner {
  public final Outer $outer;
  public Outer m1In(Outer);
  public Outer Outer$Inner$$$outer();
  public Outer$Inner(Outer);
}
*/