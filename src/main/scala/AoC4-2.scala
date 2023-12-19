import scala.io.Source
import scala.util.matching.Regex
import scala.collection.mutable.ArrayBuffer

object AoC4p2 {

  def main(args: Array[String]): Unit = {
    val inputFileName = "src/main/scala/myfile.txt"
    val input = Source.fromFile(inputFileName).getLines().toList
    println("The number of cards is " +
      input.zipWithIndex.foldLeft(Map.empty[String, Int]) {
      case (countMap, (line, index)) => {
        val oneBasedIndex = index + 1
        val newCountMap = countMap.updated(oneBasedIndex.toString, countMap.getOrElse(oneBasedIndex.toString, 0) + 1)
        val winningNumbers = line.substring(line.indexOf(':') + 1, line.indexOf('|')).split("\\s+").filter(!_.isBlank).map(_.toInt)
        val lotteryNumbers = line.substring(line.indexOf('|') + 1).split("\\s+").filter(!_.isBlank).map(_.toInt)
        val matchesCount = lotteryNumbers.count(winningNumbers.contains)
        val cards = Range(oneBasedIndex + 1, oneBasedIndex + matchesCount + 1).map(_.toString).map(index => (index, 1 * newCountMap.getOrElse(oneBasedIndex.toString, 1)))
        newCountMap ++ cards.map { case (key, value) =>
          key -> (newCountMap.getOrElse(key, 0) + value)
        }
      }
    }.foldLeft(0) {
      case (count, (_, value)) => count + value
    })
  }

}
