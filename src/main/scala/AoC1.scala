import java.io.{BufferedWriter, PrintWriter}
import scala.io.Source
import scala.util.{Failure, Success, Try}
object AoC1p1 {
  def AoC1(args: Array[String]): Unit = {
    val source = Source.fromFile("src/main/scala/myfile.txt")
    val DigitMatcher = """^\D*(\d).*?(\d)?\D*$""".r
    val result = source.getLines().map ( line =>
        line match {
          case DigitMatcher(digit, null) => s"$digit$digit".toInt
          case DigitMatcher(startDigit, endDigit) => s"$startDigit$endDigit".toInt
          case _ => 0
        }
    ).fold(0){ (sum,element) => sum + element }
    println(result)
    source.close()
  }
}