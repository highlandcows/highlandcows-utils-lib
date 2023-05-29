package highlandcows.utils.primes

import org.scalatest.flatspec.AnyFlatSpec

class TestPrimes extends AnyFlatSpec {
  val calculators: Seq[Calculator] =
    Seq(Calculator(Calculator.TEXTBOOK), Calculator(Calculator.GUILDENSTERN70), Calculator(Calculator.RECURSIVE))
  val basicPrimes: Seq[Int] =
    Seq(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97)

  "Simple primes test" should "identify primes correctly" in {
    assert(calculators.forall(calculator => basicPrimes.forall(calculator.isPrime)))
  }

  it should "reject 1 as a prime number" in {
    assert(calculators.forall(!_.isPrime(1)))
  }

}
