package io.oreto.str

import io.oreto.num.Numbers

object Strings {

  final val EMPTY = ""

  object Chars {
    val NEGATIVE = '-'
    val POSITIVE = '+'
    val DECIMAL = '.'
  }

  implicit class Str(val s: String) {

    def isNumber(t: Numbers.Type.Value = Numbers.Type.rational): Boolean = {
      // do some initial sanity checking to catch easy issues quickly and with very little processing
      // also this makes the state tracking in the case statements
      var dotted = false
      val valid = !(s.isBlank
        || (s.length == 1 && !s.charAt(0).isDigit)
        || s.endsWith(String.valueOf(Chars.DECIMAL))) &&
        s.zipWithIndex.forall(c => {
        c._1 match {
          // negative can only be in the start position
          case Chars.NEGATIVE if c._2 == 0 && (t == Numbers.Type.rational || t == Numbers.Type.integer) => true
          case Chars.NEGATIVE => false
          case Chars.POSITIVE if c._2 == 0 => true
          case Chars.POSITIVE => false
          // only one decimal place allowed
          case Chars.DECIMAL if dotted || t != Numbers.Type.rational => false
          case Chars.DECIMAL => dotted = true; true
          // this better be a digit
          case _ if c._1.isDigit => true
          case _ => false
        }
      })
      // make sure natural number type isn't assigned a 0
      if (valid && t == Numbers.Type.natural && s.replaceAll("^0+", EMPTY).length == 0) false
      else valid
    }

    def isInteger: Boolean = {
      isNumber(Numbers.Type.integer)
    }

    def isBoolean: Boolean = {
      s == "true" || s == "false"
    }

    def isByte: Boolean = {
      if (isInteger) {
        val i = s.toInt
        i >= Byte.MinValue && i <= Byte.MaxValue
      } else false
    }
  }
}
