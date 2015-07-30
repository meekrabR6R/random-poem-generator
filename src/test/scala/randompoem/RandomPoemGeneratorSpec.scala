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
}
