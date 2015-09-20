import java.io.File
import java.io.PrintWriter

//import org.json4s._
//import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.JsonMethods

import scala.io.Source

object Lab1
{
  def main(args: Array[String])
  {
//     val writer = new PrintWriter( new File("output.txt") )
//     writer.write("Following is the content read:" )
//     Source.fromFile("tweets-set1").foreach { x => writer.write(x) }
//     writer.close()
    val source = io.Source.fromFile(args(0))
    val lines = try source.mkString finally source.close()
    val parsed_json = JsonMethods.parse(lines)

  }

}
