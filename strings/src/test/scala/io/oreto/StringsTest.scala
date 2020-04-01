package io.oreto

import io.oreto.num.Numbers
import io.oreto.str.Strings._
import org.scalatest._
import org.scalatest.matchers.should.Matchers

class StringsTest extends FlatSpec with Matchers {

  "A number string" should "be a rational number" in {
    "1".isNumber() should be (true)
    "+10".isNumber() should be (true)
    "33.00".isNumber() should be (true)
    "-2.0".isNumber() should be (true)
    ".5".isNumber() should be (true)
    "-.3140".isNumber() should be (true)
    "-19.222222".isNumber() should be (true)

    "two".isNumber() should be (false)
    "thirty-one".isNumber() should be (false)
    "5.0.0".isNumber() should be (false)
    "-2.-".isNumber() should be (false)
    "10.".isNumber() should be (false)
    "-3-2".isNumber() should be (false)
    "0-".isNumber() should be (false)
    "-".isNumber() should be (false)
    "+".isNumber() should be (false)
    ".".isNumber() should be (false)
    "-.".isNumber() should be (false)
    "+.".isNumber() should be (false)
    "".isNumber() should be (false)
    "    ".isNumber() should be (false)
  }
  
  "An integer" should "be in the set { -n...0...n }" in {
    "9".isInteger should be (true)
    "9.1".isInteger should be (false)
  }

  "A natural number" should "be in the set { 1, 2, 3.....n }" in {
    "1".isNumber(Numbers.Type.natural) should be (true)
    "0".isNumber(Numbers.Type.natural) should be (false)
  }

  "A whole number" should "be in the set { 0, 1, 2, 3.....n }" in {
    "0".isNumber(Numbers.Type.whole) should be (true)
    "-1".isNumber(Numbers.Type.whole) should be (false)
  }

  "A boolean" should "be in the set { true, false }" in {
    "true".isBoolean should be (true)
    "false".isBoolean should be (true)
    "yes".isBoolean should be (false)
  }

  "A byte" should "be in the set { -128, -127...0...127 }" in {
    "1".isByte should be (true)
    "-128".isByte should be (true)
    "127".isByte should be (true)
    "1.1".isByte should be (false)
    "128".isByte should be (false)
  }
}
