import scala.io.Source
object AoC2p1 {
  def main(args: Array[String]): Unit = {
    val inputFileName = "src/main/scala/myfile.txt"
    val input = Source.fromFile(inputFileName).getLines().toList

    val PossibleColorMap = Map(("red",12), ("green", 13), ("blue", 14))

    def getCurrentMaxBalls(maxBallsMap: Map[String, Int], currentBallsMap: Map[String, Int]): Map[String, Int] = {
      (maxBallsMap.keySet ++ currentBallsMap.keySet).map { key =>
        key -> List(maxBallsMap.getOrElse(key, Int.MinValue), currentBallsMap.getOrElse(key, Int.MinValue)).max
      }.toMap
    }
    def getColorMapFromTurn(input: String): Map[String, Int] = {
      val pattern = "(\\d+)\\s+(\\w+)".r
      pattern.findAllMatchIn(input).map { m =>
        m.group(2) -> m.group(1).toInt
      }.toMap
    }

    def compareMaps(mapA: Map[String, Int], mapB: Map[String, Int]): Boolean = {
      !mapA.exists { case (key, valueA) =>
        mapB.get(key).exists(valueB => valueA > valueB)
      }
    }

    // Function to check if a game is possible with the given cube configuration
    def getPossibleId(rawGame: String): Int = {
      val tokens = rawGame.split(':').map(_.trim)
      var maxColorMap = Map(("blue", 0),("red",0),("red",0))
      for (turns <- tokens(1).toString.split(";")) {
        maxColorMap = getCurrentMaxBalls(maxColorMap, getColorMapFromTurn(turns))
      }
      println(maxColorMap.mkString(" : "))
      if (compareMaps(maxColorMap, PossibleColorMap) == true) {
        return tokens(0).toString.substring(5).toInt
      } else
        return 0
    }

    // Filter possible games and calculate the sum of their IDs
    val sumOfIDs = input.map(getPossibleId(_)).sum
    println(s"Sum of IDs: $sumOfIDs")
  }
}
