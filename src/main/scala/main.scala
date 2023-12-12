import scala.io.Source
import scala.util.matching.Regex

object Main {

  def main(args: Array[String]): Unit = {
    val inputFileName = "src/main/scala/myfile.txt"
    val input = Source.fromFile(inputFileName).getLines().toList
    val breakLineRegex = """^Card\W+(\d+):\W+(\d+)\W+(\d+)\W+(\d+)\W+(\d+)\W+(\d+)\W+\|\W+(\d+)\W+(\d+)\W+(\d+)\W+(\d+)\W+(\d+)\W+(\d+)\W+(\d+)\W+(\d+)""".r
    var sum = 0
    for ((line, lineIndex) <- input.zipWithIndex) {
      println("line is :" + line + ":")
      val cardPart = line.substring(0, line.indexOf(':'))
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
      println("Card " + lineIndex + " result is " + result)
      sum += result
    }
    println("Sum is " + sum)
  }

}
