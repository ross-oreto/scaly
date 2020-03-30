package io.oreto

import org.scalatest._
import io.oreto.str.Strings._
import org.scalatest.matchers.should.Matchers

class StringsTest extends FlatSpec with Matchers {

  "A number string" should "contain only positive or negative characters, natural numbers, or decimals" in {
    "1".isNumber should be (true)
    "10".isNumber should be (true)
    "33.00".isNumber should be (true)
    "-2.0".isNumber should be (true)
    ".5".isNumber should be (true)
    "-.3140".isNumber should be (true)
    "-19.222222".isNumber should be (true)

    "two".isNumber should be(false)
    "thirty-one".isNumber should be(false)
    "5.0.0".isNumber should be(false)
    "-2.-".isNumber should be(false)
    "10.".isNumber should be(false)
    "-3-2".isNumber should be(false)
    "0-".isNumber should be(false)
    "-".isNumber should be(false)
    ".".isNumber should be(false)
    "-.".isNumber should be(false)
    "".isNumber should be(false)
    "    ".isNumber should be(false)
  }
}
