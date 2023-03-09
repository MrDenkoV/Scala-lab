/*package p1.p2.p3 
class A3
Compiled from "main2.scala"
public class p1.p2.p3.A3 {
  public p1.p2.p3.A3();
}*/ // folder p1/p2/p3 i plik A3

/*package p1
package p2
package p3
class A3*/ // ten sam kod bajtowy co wyżej i ta sama struktura plików

/*package p1.p2.p3 {
  class A3
}*/ // ten sam kod bajtowy co wyżej i ta sama struktura plików

/*package p1 {
  package p2 {
    package p3 {
      class A3
    }
  }
}*/ // ten sam kod bajtowy co wyżej i ta sama struktura plików

//Wszystkie powyższe metody są równoważne


/*package p1 {
  class A1T { new A1B }
  package p2 {
    class A2T { new A2B; new A1T; new A1B }
    package p3 {
      class A3 { new A2T; /* new A2B; */ new A1T; /* new A1B */ }
    }
    class A2B { /* new A2T; new A1T; new A1B */ }
  }
  class A1B { new A1T } // Zkomentowane działa, po odkomentowaniu się zapętla
}

object Appl {
  def main(args: Array[String]):Unit = { 
    new p1.A1T
    new p1.A1B
    new p1.p2.A2T
    new p1.p2.A2B
    new p1.p2.p3.A3
  }
}*/

/*
Compiled from "main2.scala" // wszystie klasy mają analogiczny kod bajtowy
public class p1.p2.p3.A3 {
  public p1.p2.p3.A3();
}
*/


/*package p1 {
  class A1
  package p2 {
    class A21
  }
}

package p1.p2 {
  class A22 { new A1 } // A1 nie jest widoczne z tego miejsca
}*/

/*package p1 {
  class A1
  package p2 {
    class A21
  }
}

package p1 {
  package p2 {
    class A22 { new A1 } // A1 jest widoczne z tego miejsca
  }
}*/

package p1 {
  package p2 {
    class A1 { println("p1.p2.A1") }
    class A22 {
      println("Calling new p1.p2.A1") 
      new A1
      println("Calling new _root_.p1.A1")
      new _root_.p1.A1 // Wywołuje metodę w pliku file1.scala - poznaje po głębokości gdzie sięgać
      println("p1.p2.A22")
    }
  }
}

object Appl {
  def main(args: Array[String]):Unit = {
    new p1.A1 // wywołuje metode z pliku file1.scala
    new p1.p2.A21 // wywołuje metode z pliku file1.scala (poznaje po metodzie do którego pakietu sięgać)
    new p1.p2.A22 // wywołuje metode z tego pliku (poznaje po metodzie do którego pakietu sięgać)
  }
}

/*
p1.A1
p1.p2.A21
Calling new p1.p2.A1
p1.p2.A1
Calling new _root_.p1.A1
p1.A1
p1.p2.A22
*/

/*
├── p1
│   ├── A1.class
│   └── p2
│       ├── A1.class
│       ├── A21.class
│       └── A22.class
*/