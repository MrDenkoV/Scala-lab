def mapList[A, B](xs: List[A], op: A => B): List[B] = xs match {
  case List() => List()
  case h :: t => op(h)::mapList(t, op)
}

mapList((1::2::3::Nil), (e:Int)=>e*e)
mapList(("asda"::"asdasd"::"basda"::Nil), (e:String)=>e.length)