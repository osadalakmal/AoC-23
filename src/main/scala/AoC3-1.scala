import scala.io.Source
import scala.util.matching.Regex

object AoC3p1 {

  def getTopPart(start: Int, end: Int, lineIndex: Int, input: List[String]): String = {
    if (lineIndex > 0) {
      val aboveLine = input(lineIndex - 1)
      aboveLine.substring(start, end)
    } else {
      ""
    }
  }

  def getLeftPart(start: Int, lineIndex: Int, line: String): String = {
    if (start > 0) {
      line.substring(start - 1, start)
    } else {
      ""
    }
  }

  def getRightPart(end: Int, lineIndex: Int, line: String): String = {
    if (line.size > end) {
      line.substring(end, end + 1)
    } else {
      ""
    }
  }

  def getBottomPart(start: Int, end: Int, lineIndex: Int, input: List[String]): String = {
    if (input.size > (lineIndex + 1)) {
      val belowLine = input(lineIndex + 1)
      belowLine.substring(start, end)
    } else {
      ""
    }
  }

  def getTopLeftPart(start: Int, end: Int, lineIndex: Int, input: List[String]): String = {
    if (lineIndex > 0 && start > 0) {
      val aboveLine = input(lineIndex - 1)
      aboveLine.substring(start - 1, start)
    } else {
      ""
    }
  }

  def getTopRightPart(start: Int, end: Int, lineIndex: Int, input: List[String]): String = {
    if (lineIndex > 0 && input(lineIndex - 1).size > end) {
      val aboveLine = input(lineIndex - 1)
      aboveLine.substring(end, end + 1)
    } else {
      ""
    }
  }

  def getBottomRightPart(start: Int, end: Int, lineIndex: Int, input: List[String]): String = {
    if (input.size > (lineIndex + 1) && input(lineIndex + 1).size > end) {
      val belowLine = input(lineIndex + 1)
      belowLine.substring(end, end + 1)
    } else {
      ""
    }
  }

  def getBottomLeftPart(start: Int, end: Int, lineIndex: Int, input: List[String]): String = {
    if (input.size > (lineIndex + 1) && start > 0) {
      val belowLine = input(lineIndex + 1)
      belowLine.substring(start - 1, start)
    } else {
      ""
    }
  }

  private def findIfAdjToAnySymbols(input: List[String], regexForSym: Regex, line: String, lineIndex: Int, num: Regex.Match) = {
    val topPart = getTopPart(num.start, num.end, lineIndex, input)
    val topLeftPart = getTopLeftPart(num.start, num.end, lineIndex, input)
    val leftPart = getLeftPart(num.start, lineIndex, line)
    val topRightPart = getTopRightPart(num.start, num.end, lineIndex, input)
    val rightPart = getRightPart(num.end, lineIndex, line)
    val bottomRightPart = getBottomRightPart(num.start, num.end, lineIndex, input)
    val bottomPart = getBottomPart(num.start, num.end, lineIndex, input)
    val bottomLeftPart = getBottomLeftPart(num.start, num.end, lineIndex, input)

    val fullAdjCells = topLeftPart + topPart + topRightPart + leftPart + rightPart + bottomLeftPart + bottomPart + bottomRightPart
    regexForSym.findFirstIn(fullAdjCells).isDefined
  }

  def main(args: Array[String]): Unit = {
    val inputFileName = "src/main/scala/myfile.txt"
    val input = Source.fromFile(inputFileName).getLines().toList
    val regexForNum = """\d+""".r
    val regexForSym = """[^0-9.]""".r
    var sum = 0
    for ((line, lineIndex) <- input.zipWithIndex) {
      for (num <- regexForNum.findAllMatchIn(line)) {
        if (findIfAdjToAnySymbols(input, regexForSym, line, lineIndex, num)) sum += num.group(0).toInt
      }
    }
    println("Sum is " + sum)
  }

}
