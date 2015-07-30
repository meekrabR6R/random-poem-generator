package randompoem

import scala.io.Source
import scala.util.Random

/**
 * Created by nmiano on 7/29/15.
 */
class RandomPoemGenerator(src: String) {
  //seeded rand
  private val rand = new Random(System.currentTimeMillis())

  //regex patterns for parsing poem rules
  private val refPattern   = """(<[A-Z]+>)""".r
  private val groupPattern = """([\$<>a-zA-Z]+\|+[\$<>A-Za-z\|]+)""".r
  private val wordPattern  = """([a-z]+)""".r

  //poem generated from parsed grammar
  val poem: String = parse("<POEM>")

  /**
   * Rule parser
   * @param tok
   * @return
   */
  def parse(tok: String): String = tok match {
    case refPattern(s)   => ruleMap.getOrElse(s.replaceAll("<|>", ""), List())
                                   .foldRight("") { (x, acc) => parse(x) + acc }
    case groupPattern(s) => ({ (opts: String) =>
      val items: List[String] = opts.toString.split( """\|""").toList
      parse(items(rand.nextInt(items.size)))
    })(s)
    case wordPattern(s)  => s + " "
    case "$LINEBREAK"    => "\n"
    case _               => ""
  }


  /**
   * Returns a map in which each key is the name of a grammar rule,
   * and the corresponding value is a string representation of the rule's
   * definition.
   * @return map of rules
   */
  def ruleMap: Map[String, List[String]] = {
    //nested helper function for generating rule map from line
    def helper(line: String): Map[String, List[String]] = line.split(": ") match {
      case Array(k, v) => Map(k -> v.split(" ").toList)
      case _ => Map()
    }
    //read rules from file, and convert each line into a key value pair
    Source.fromFile(src).getLines().foldRight(Map[String, List[String]]()) { (x, acc) => acc ++ helper(x) }
  }
}
