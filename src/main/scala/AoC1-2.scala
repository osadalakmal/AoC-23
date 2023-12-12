import scala.io.Source
import scala.util.matching.Regex.MatchIterator

object AoC1p2 {

  private val numLookupTable = collection.immutable.Map(("one",1), ("two",2), ("three",3), ("four",4), ("five",5), ("six",6), ("seven",7), ("eight",8), ("nine",9));
  private def numToInt(n1: String): Int = n1.length match {
    case len if len > 1 => numLookupTable.get(n1).getOrElse(0)
    case _ => n1.toInt
  }

  def getLastMatch(mi : MatchIterator) : String = {
    var res = ""
    while (mi.hasNext) {
      mi.next
      res = mi.group(1)
    }
    return res
  }

  def main(args: Array[String]): Unit = {
    val source = Source.fromFile("src/main/scala/myfile.txt")
    val FirstDigitMatcher = """^.*?(\d|one|two|three|four|five|six|seven|eight|nine)""".r
    val LastDigitMatcher = """^.*(\d|one|two|three|four|five|six|seven|eight|nine)""".r
    val result = source.getLines().map ( line => {
      val firstDigit = FirstDigitMatcher.findFirstMatchIn(line) match {
        case Some(digit) => { //println("First Digit is " + digit.group(1) + " and line is " + line);
          numToInt(digit.group(1)) }
        case None => 0
      }
      val lastDigit = getLastMatch(LastDigitMatcher.findAllIn(line)) match {
        case lDigit if lDigit.length == 1 => { //println("Last Digit is " + lDigit + " and line is " + line);
          lDigit.toInt}
        case lDigit: String => numToInt(lDigit)
      }
      //println("Final Coords is " + (firstDigit * 10 + lastDigit))
      firstDigit * 10 + lastDigit;
    }
    ).fold(0){ (sum,element) => sum + element }
    println(result)
    source.close()
  }
}
