import p1.p2.C2._
object Appl {
  import p1._
  new C1
  import p2.{C21, C22=>MyC22, C23=>_}
  new C21
  // new C22 zmiana nazwy na MyC22 -> new MyC22 działa
  new MyC22
  // new C23 zmiana nazwy na _ -> nie można zrobić new _ -> wildcard
  def main(args: Array[String]) = {
    m1OC2()
    m2OC2(new C1) // najpierw robi new C1 później woła m20C2 z powstałą klasą
  }
}

/* // po poprawieniu działa tak jak powinno
C1
C21
C22
m1OC2
C1
m2OC2
m1C1()
*/
