object ThreeColors extends Enumeration {
   type ThreeColors = Value
   val Blue, White, Red = Value
}

import ThreeColors._

object utils {
   def leadingActor(part: ThreeColors) = part match {
     case Blue => "Juliette Binoche"
     case White => "Zbigniew Zamachowski"
     case Red => "Irene Jacob"
   }
}

object Appl {
   def main(args: Array[String]) = {
     for (part <- ThreeColors.values)
       println(part.toString() + ": " + utils.leadingActor(part))
   }
}

/*                                                  bardzo dużo się dzieje, dla prostej enumeracji(w scali nie tak prostej)
Compiled from "main6.scala"
public final class ThreeColors {
  public static scala.Enumeration$Value Red();
  public static scala.Enumeration$Value White();
  public static scala.Enumeration$Value Blue();
  public static scala.Enumeration$ValueSet$ ValueSet();
  public static scala.Enumeration$ValueOrdering$ ValueOrdering();
  public static scala.Enumeration$Value withName(java.lang.String);
  public static scala.Enumeration$Value apply(int);
  public static int maxId();
  public static scala.Enumeration$ValueSet values();
  public static java.lang.String toString();
}
*/