import scala.beans.BeanProperty
import scala.math.sqrt

class Person(val givenName: String, @BeanProperty var surname: String, protected val id: String){
  def name(): String = return givenName + " " + surname
}

class Int2DVec private (val x: Int, val y: Int) {//Nie może być private, bo wywołujemy bezpośrednio w main
  def +(other: Int2DVec) = new Int2DVec(x + other.x, y + other.y)
  def unary_- = new Int2DVec(-x, -y)
  override def toString() = "(" + x.toString + ',' + y.toString + ")"
  def -(other: Int2DVec) = -other+this
  def *(other: Int2DVec) = x*other.x+y*other.y
  def |-?(other: Int2DVec) = (this*other == 0)

  /*def this() = {
    this(0, 0)//Nie można zamienić, this musi być 1
    println("Creating Int2DVec(0,0)")
  }
  
  def this(prototype: Int2DVec){
    this(prototype.x, prototype.y)//Nie można zamienić, this musi być 1
    println("Copy-constructor working")
  }*/
}

object Int2DVec {//Umożliwia wołanie prywatnych operatorów, ponieważ jest obiektem "towarzyszącym". Wszystkie metody i pola wyświetlane przez javap Int2DVec.class są publiczne mimo bycia klasą prywatną, dzieki objektowi towarzyszącemu
  def apply(x: Int, y: Int) = new Int2DVec(x, y)
  def apply() = new Int2DVec(0, 0)
  def apply(prototype: Int2DVec) = new Int2DVec(prototype.x, prototype.y)
  //def unitVectorOf(dir: Int2DVec) = {val sqr=sqrt(dir.x*dir.x+dir.y*dir.y); return new Int2DVec((dir.x/sqr).toInt, (dir.y/sqr).toInt)}
  def unitVectorOf(dir: Int2DVec) = new Int2DVec((dir.x/sqrt(dir.x*dir.x+dir.y*dir.y)).round.toInt, (dir.y/sqrt(dir.x*dir.x+dir.y*dir.y)).round.toInt)
}

object Appl {
  def main(agrs: Array[String])={
    val v1 = Int2DVec(1, 2)
    val v2 = Int2DVec(10, 20)
    val v3 = v1 + v2
    val v4 = -v2
    println(v3)
    println(v4)
    println(v3-v4)
    println(v3*v4)
    println(Int2DVec())
    println(Int2DVec(v1 + v1 +v2))
    println(Int2DVec.unitVectorOf(v3))
    println(Int2DVec.unitVectorOf(v1))
    println(v3 |-? v4)
    println(Int2DVec(1, 2) |-? Int2DVec(-4,2))
  }
}

/*object Appl extends App{
  val p = new Person("Jan", "Kowalski", "1234567890")
  println(p.name)
  p.setSurname("Nowak")//używamy dodatkowy(drugi setter)
  println(p.getSurname)//używamy dodatkowy(drugi getter)
  //w sumie po 2 settery i gettery
  //println(p.id) -> pole protected -> błąd - nie mamy dostępu
}*/
