/*object Appl{
  def randomPasswd(passwdLen: Int) = {
    val passwd = new StringBuilder(passwdLen)
    val allowedChars = (('0' to '9') ++
                        ('A' to 'Z') ++
                        ('a' to 'z')).toArray ++
                        Array('-', '.', '_') // more special chars?
    for (i <- 0 to passwdLen)
      passwd += allowedChars(
        util.Random.nextInt(allowedChars.length))

    passwd.toString
  }

  def main(args: Array[String]):Unit = {
    println(randomPasswd(10))
    println(randomPasswd(15))
  }
}*/

//metoda randomPasswd dział

/*object Appl{
  def main(args: Array[String]):Unit = {
    import utils.PasswdGen
    println(PasswdGen.nextPasswd(10))
    println(PasswdGen.nextPasswd(15))
  }
}*/

// PasswdGen.nextPasswd() działa


object Appl{
  def main(args: Array[String]):Unit = {
    import utils._
    var inName="logins.txt"
    var outName="login-passwds.txt"
    if(args.length==2){
        inName=args(0)
        outName=args(1)
    }
    val inFile = scala.io.Source.fromFile(inName)
    val outFile = new java.io.PrintWriter(outName)
    try {
      for (login <- inFile.getLines) outFile.println(login + ":" + PasswdGen.nextPasswd(15))
    } catch {
      case ex: java.io.FileNotFoundException =>
        println(ex.getMessage)
      case ex: Throwable =>
        println("Default exception handler: "+ ex.getMessage)
    } finally {
      inFile.close
      outFile.close
    }
  }
}
