
import java.io.File
import java.io.PrintWriter

import scala.io.Source

object Write
{
  def main(args: Array[String]) 
  {  
     val writer = new PrintWriter( new File("output.txt") )
     writer.write("Following is the content read:" )
     Source.fromFile("tweets-set1").foreach { x => writer.write(x) }
     writer.close()

   }

}
