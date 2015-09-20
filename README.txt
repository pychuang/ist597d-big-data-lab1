# Download minimal-json library from

http://mvnrepository.com/artifact/com.eclipsesource.minimal-json/minimal-json/0.9.4


# Use Parser.java to parse twitter data

$ cp tweets-set1.json tweets
$ java -classpath minimal-json-0.9.4.jar:. Parser

Two files will be generated: ParsedTweet1.txt and SourceTweet1.txt. We will use ParsedTweet1.txt to calculate hashtags.


# Use the Scala program to calculate hashtags

$ scala lab1.scala ParsedTweet1.txt output.txt
