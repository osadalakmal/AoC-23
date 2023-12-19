import scala.io.Source
import scala.util.matching.Regex
import scala.collection.mutable.ArrayBuffer

object AoC4p1 {

  def main(args: Array[String]): Unit = {
    val inputFileName = "src/main/scala/myfile.txt"
    val input = Source.fromFile(inputFileName).getLines().toList
    println("Sum is " + input.map( line => {
      println("line is :" + line + ":")
      val winningNumberPart = line.substring(line.indexOf(':') + 1, line.indexOf('|'))
      val lotteryNumberPart = line.substring(line.indexOf('|') + 1)

      val winningNumbers = winningNumberPart.split("\\s+").filter(!_.isBlank).map(_.toInt)
      println("winning numbers " + winningNumbers.mkString(" : "))
      val lotteryNumbers = lotteryNumberPart.split("\\s+").filter(!_.isBlank).map(_.toInt)
      println("lottery numbers " + lotteryNumbers.mkString(" : "))

      // Count the matches in lottery numbers
      val matchesCount = lotteryNumbers.count(winningNumbers.contains)

      // Calculate the answer using 2 to the power of matchesCount
      val result = math.pow(2, matchesCount - 1).toInt
      result
  }).fold(0)((a,b) => a + b))
  }

}
