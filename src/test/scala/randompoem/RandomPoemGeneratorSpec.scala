package randompoem

import org.scalatest._

class RandomPoemGeneratorSpec extends FlatSpec with Matchers {
  val poemMaker = new RandomPoemGenerator("src/rules/poem.rules")

  "ruleMap" should "have the following keys: POEM, LINE, ADJECTIVE, NOUN, PRONOUN, VERB, PREPOSITION" in {
    poemMaker.ruleMap.keys should be === Set("POEM", "LINE", "ADJECTIVE", "NOUN", "PRONOUN", "VERB", "PREPOSITION")
  }

  "Poem" should "have 5 lines" in {
    poemMaker.poem.lines.count(_ => true) should be === 5
  }

  "Parsing a word" should "return the word prepended with a space" in {
    poemMaker.parse("spongebob") should be === "spongebob "
  }

  "Parsing a $LINEBREAK" should "return a newline" in {
    poemMaker.parse("$LINEBREAK") should be === "\n"
  }

  "Parsing a $END" should "return a 0 length String" in {
    poemMaker.parse("$END") should be === ""
  }
}
