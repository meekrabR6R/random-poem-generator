import randompoem.RandomPoemGenerator

/**
 * Convenience for running poem generator
 * Created by nmiano on 7/30/15.
 */
object RandomPoem extends App {
  val poemGenerator = new RandomPoemGenerator("src/rules/poem.rules")
  println(poemGenerator.poem)
}
