package highlandcows.utils

import scala.annotation.tailrec

package object primes {

  trait Calculator {
    val name: String
    def isPrime(n: Int): Boolean
  }

  /** Textbook definition of what it means to be a prime number */
  class TextBookCalculator extends Calculator {
    override val name: String = Calculator.TEXTBOOK

    def isPrime(n: Int): Boolean =
      n > 1 && ((2 until n) forall (d => n % d != 0L))
  }

  /** https://dev.to/guildenstern70/a-pure-functional-primality-test-in-scala-3gif */
  class Guildenstern70Calculator extends Calculator {
    override val name: String = Calculator.GUILDENSTERN70

    def isPrime(n: Int): Boolean =
      isPrime(n, 5)

    private def isPrime(n: Int, i: Int): Boolean = {
      if (n <= 3)
        return n > 1
      if (n % 2 == 0 || n % 3 == 0)
        return false
      while (i * i <= n) {
        if (n % i == 0 || n % (i + 2) == 0)
          return false
        return isPrime(n, i + 6)
      }
      true
    }
  }

  /** Recursive implementation of the Guildenstern70Calculator */
  class RecursiveCalculator extends Calculator {
    override val name: String = Calculator.RECURSIVE

    def isPrime(n: Int): Boolean = {
      @tailrec
      def loop(n: Int, i: Int = 5): Boolean =
        if (i * i <= n)
          if (n % i == 0 || n % (i + 2) == 0) false
          else
            loop(n, i + 6)
        else
          true

      if (n <= 3) n > 1
      else if (n % 2 == 0 || n % 3 == 0) false
      else loop(n)
    }
  }

  object Calculator {
    val TEXTBOOK       = "textbook"
    val GUILDENSTERN70 = "guildenstern70"
    val RECURSIVE      = "recursive"

    def apply(name: String = RECURSIVE): Calculator =
      name.toLowerCase match {
        case TEXTBOOK       => new TextBookCalculator
        case GUILDENSTERN70 => new Guildenstern70Calculator
        case RECURSIVE      => new RecursiveCalculator
        case unknown =>
          throw new IllegalArgumentException(s"Unsupported calculator: $unknown")
      }
  }
}
