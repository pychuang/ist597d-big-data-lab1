
import java.io.File
import java.io.PrintWriter

import scala.io.Source

object Write
{
  def get_tweet_text(line: String): String = {
    return line.split("\t")(6)
  }

  def split_words(text: String): Array[String] = {
    val words = text.split("[ .,?!:\"]")
    return words.filter(_.size > 0)
  }

  def is_hashtag(word: String): Boolean = {
    return word(0) == '#'
  }

  def count_hashtags(hashtags: List[String]): Map[String, Int] = {
    return hashtags.groupBy(x=>x).map(x=>(x._1, x._2.size))
  }

  def main(args: Array[String]) {
    val inputfile = args(0)
    val outputfile = args(1)

    val lines = Source.fromFile(inputfile).getLines
    val texts = lines.map(get_tweet_text)
    val words = texts.flatMap(split_words)
    val hashtags = words.filter(is_hashtag).toList
    val stats = count_hashtags(hashtags)

    val writer = new PrintWriter(new File(outputfile))
    stats.foreach(x => writer.write(x._1 + "\t" + x._2 + "\n"))
    writer.close()
  }
}
