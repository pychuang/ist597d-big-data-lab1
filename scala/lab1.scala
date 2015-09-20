import java.io.File
import java.io.PrintWriter

import org.json4s._
//import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.JsonMethods

import scala.io.Source

object Lab1
{
  def handle_tweet(t:Map[String, String]): Unit = {
    print("T: ")
    println(t)
    print("E: ")
    println(t("enetities"))
  }

  def main(args: Array[String]) {
//     val writer = new PrintWriter( new File("output.txt") )
//     writer.write("Following is the content read:" )
//     Source.fromFile("tweets-set1").foreach { x => writer.write(x) }
//     writer.close()
    println("args")
    args.foreach(println)
//    Source.fromFile(args(0)).getLines.foreach(x=>println("LINE: " + x))
    val lines = Source.fromFile(args(0)).getLines

    val tweets_json = lines.map(line=>line.split("[|]")).filter(_.size > 1).map(_(1))
    val tweets = tweets_json.map(j=>JsonMethods.parse(j).asInstanceOf[JObject].values)
//    val tweets = tweets_json.map(j=>JsonMethods.parse(j).values)
//    tweets.foreach(handle_tweet)
    tweets.foreach(t=>println("\nT: " + t))
//    tweets.foreach(t=>JsonMethods.pretty(JsonMethods.render(t.asInstanceOf[JValue])))
//    val parsed_json = JsonMethods.parse(lines)

  }

}
