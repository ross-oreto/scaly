package io.oreto.str

import io.oreto.num.Numbers

object Strings {

  final val EMPTY = ""
  final val SPACE = " "

  object Chars {
    val NEGATIVE = '-'
    val POSITIVE = '+'
    val DECIMAL = '.'
  }

  implicit class CharSeq(val s: CharSequence) {
    def isNumber(t: Numbers.Type.Value = Numbers.Type.rational): Boolean = {
      // do some initial sanity checking to catch easy issues quickly and with very little processing
      // also this makes the state tracking in the case statements
      val length = s.length
      if(s.isBlank
        || (length == 1 && !s.charAt(0).isDigit)
        || s.charAt(length - 1) == Chars.DECIMAL)
        return false

      var dotted = false
      val valid =
        0.until(length).forall(i => {
          val c = s.charAt(i)
        c match {
          // negative can only be in the start position
          case Chars.NEGATIVE if i == 0 && (t == Numbers.Type.rational || t == Numbers.Type.integer) => true
          case Chars.NEGATIVE => false
          case Chars.POSITIVE if i == 0 => true
          case Chars.POSITIVE => false
          // only one decimal place allowed
          case Chars.DECIMAL if dotted || t != Numbers.Type.rational => false
          case Chars.DECIMAL => dotted = true; true
          // this better be a digit
          case _ if c.isDigit => true
          case _ => false
        }
      })
      // make sure natural number type isn't assigned a 0
      if (valid && t == Numbers.Type.natural && s.chars.allMatch(_ == '0')) false
      else valid
    }

    def isInteger: Boolean = {
      isNumber(Numbers.Type.integer)
    }

    def isBoolean: Boolean = {
      s == "true" || s == "false"
    }

    def isBlank: Boolean = {
      s.length == 0 || s.chars().allMatch(Character.isWhitespace(_))
    }
  }

  implicit class Str(val s: String) {
    def isByte: Boolean = {
      if (s.isInteger) {
        val i = s.toInt
        i >= Byte.MinValue && i <= Byte.MaxValue
      } else false
    }
  }
}
