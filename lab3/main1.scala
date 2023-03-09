object Appl {
  /*def main(args: Array[String]) = {
    println("Opening the file...")
    val inFile = scala.io.Source.fromFile("logins.txt")
    for (line <- inFile.getLines) println(line)
    println("Closing the file...")
    inFile.close
  }*/ // file not found exception
  /*def main(args: Array[String]) = {
    try {
      println("Opening the file...")
      val inFile = scala.io.Source.fromFile("logins.txt")
      for (line <- inFile.getLines) println(line)
      val x = 100 / inFile.getLines.length
      println("Closing the file...")
      inFile.close
    } catch {
      case ex: java.io.FileNotFoundException =>
        println(ex.getMessage)
    }
  }*/ // plik nie został zamknięty po arithmetic exception
  def readFile(fileName: String) = try { // plik został zamknięty
    println("Opening the file...")
    val inFile = scala.io.Source.fromFile(fileName)
    try {
      for (line <- inFile.getLines) println(line)
      val x = 100 / inFile.getLines.length
    } finally {
      println("Closing the file...")
      inFile.close
    }
  } catch {
    case ex: java.io.FileNotFoundException =>
      println(ex.getMessage)
    case ex: Throwable =>
      println("Default exception handler: "+ ex.getMessage)
  }

  def main(args: Array[String]) = {
    readFile("logins.txt")
  }
}