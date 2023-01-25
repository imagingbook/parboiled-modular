/*
 * Copyright (C) 2009-2011 Mathias Doenitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.parboiled.scala.rules

import imagingbook.parboiled.matchers._
import java.lang.String
import Rule._
import imagingbook.parboiled.support.IndexRange

/**
 * The base class of all rules pushing a certain number of elements onto the parser value stack.
 */
sealed abstract class PushRule extends Rule

/**
 * A rule pushing one new value of a given type onto the parsers value stack.
 */
class Rule1[+A](val matcher: Matcher) extends PushRule {
  def ~[Y, Z, AA >: A](other: PopRule3[Y, Z, AA]) = new PopRule2[Y, Z](append(other))
  def ~[Z, AA >: A](other: PopRule2[Z, AA]) = new PopRule1[Z](append(other))
  def ~[AA >: A](other: PopRule1[AA]) = new Rule0(append(other))
  def ~[Y, Z, AA >: A, R](other: ReductionRule3[Y, Z, AA, R]) = new ReductionRule2[Y, Z, R](append(other))
  def ~[Y, Z, AA >: A, RA, B](other: ReductionRule3_2[Y, Z, AA, RA, B]) = new ReductionRule2_2[Y, Z, RA, B](append(other))
  def ~[Y, Z, AA >: A, RA, B, C](other: ReductionRule3_3[Y, Z, AA, RA, B, C]) = new ReductionRule2_3[Y, Z, RA, B, C](append(other))
  def ~[Y, Z, AA >: A, RA, B, C, D](other: ReductionRule3_4[Y, Z, AA, RA, B, C, D]) = new ReductionRule2_4[Y, Z, RA, B, C, D](append(other))
  def ~[Y, Z, AA >: A, RA, B, C, D, E](other: ReductionRule3_5[Y, Z, AA, RA, B, C, D, E]) = new ReductionRule2_5[Y, Z, RA, B, C, D, E](append(other))
  def ~[Y, Z, AA >: A, RA, B, C, D, E, F](other: ReductionRule3_6[Y, Z, AA, RA, B, C, D, E, F]) = new ReductionRule2_6[Y, Z, RA, B, C, D, E, F](append(other))
  def ~[Y, Z, AA >: A, RA, B, C, D, E, F, G](other: ReductionRule3_7[Y, Z, AA, RA, B, C, D, E, F, G]) = new ReductionRule2_7[Y, Z, RA, B, C, D, E, F, G](append(other))
  def ~[Z, AA >: A, RA](other: ReductionRule2[Z, AA, RA]) = new ReductionRule1[Z, RA](append(other))
  def ~[Z, AA >: A, RA, B](other: ReductionRule2_2[Z, AA, RA, B]) = new ReductionRule1_2[Z, RA, B](append(other))
  def ~[Z, AA >: A, RA, B, C](other: ReductionRule2_3[Z, AA, RA, B, C]) = new ReductionRule1_3[Z, RA, B, C](append(other))
  def ~[Z, AA >: A, RA, B, C, D](other: ReductionRule2_4[Z, AA, RA, B, C, D]) = new ReductionRule1_4[Z, RA, B, C, D](append(other))
  def ~[Z, AA >: A, RA, B, C, D, E](other: ReductionRule2_5[Z, AA, RA, B, C, D, E]) = new ReductionRule1_5[Z, RA, B, C, D, E](append(other))
  def ~[Z, AA >: A, RA, B, C, D, E, F](other: ReductionRule2_6[Z, AA, RA, B, C, D, E, F]) = new ReductionRule1_6[Z, RA, B, C, D, E, F](append(other))
  def ~[Z, AA >: A, RA, B, C, D, E, F, G](other: ReductionRule2_7[Z, AA, RA, B, C, D, E, F, G]) = new ReductionRule1_7[Z, RA, B, C, D, E, F, G](append(other))
  def ~[AA >: A, RA](other: ReductionRule1[AA, RA]) = new Rule1[RA](append(other))
  def ~[AA >: A, RA, B](other: ReductionRule1_2[AA, RA, B]) = new Rule2[RA, B](append(other))
  def ~[AA >: A, RA, B, C](other: ReductionRule1_3[AA, RA, B, C]) = new Rule3[RA, B, C](append(other))
  def ~[AA >: A, RA, B, C, D](other: ReductionRule1_4[AA, RA, B, C, D]) = new Rule4[RA, B, C, D](append(other))
  def ~[AA >: A, RA, B, C, D, E](other: ReductionRule1_5[AA, RA, B, C, D, E]) = new Rule5[RA, B, C, D, E](append(other))
  def ~[AA >: A, RA, B, C, D, E, F](other: ReductionRule1_6[AA, RA, B, C, D, E, F]) = new Rule6[RA, B, C, D, E, F](append(other))
  def ~[AA >: A, RA, B, C, D, E, F, G](other: ReductionRule1_7[AA, RA, B, C, D, E, F, G]) = new Rule7[RA, B, C, D, E, F, G](append(other))
  def ~[B](other: Rule1[B]) = new Rule2[A, B](append(other))
  def ~[B, C](other: Rule2[B, C]) = new Rule3[A, B, C](append(other))
  def ~[B, C, D](other: Rule3[B, C, D]) = new Rule4[A, B, C, D](append(other))
  def ~[B, C, D, E](other: Rule4[B, C, D, E]) = new Rule5[A, B, C, D, E](append(other))
  def ~[B, C, D, E, F](other: Rule5[B, C, D, E, F]) = new Rule6[A, B, C, D, E, F](append(other))
  def ~[B, C, D, E, F, G](other: Rule6[B, C, D, E, F, G]) = new Rule7[A, B, C, D, E, F, G](append(other))
  def ~:>[R](f: Char => R) = new Rule2[A, R](append(push(exec(GetMatchedChar, f))))
  def ~>>[R](f: IndexRange => R) = new Rule2[A, R](append(push(exec(GetMatchRange, f))))
  def ~>[R](f: String => R) = new Rule2[A, R](append(push(exec(GetMatch, f))))
  def ~~>[R](f: A => R) = new Rule1[R](append(push(exec(stack1(Pop), f))))
  def ~~>[Z, R](f: (Z, A) => R) = new ReductionRule1[Z, R](append(push(exec(stack2(Pop), f))))
  def ~~>[Y, Z, R](f: (Y, Z, A) => R) = new ReductionRule2[Y, Z, R](append(push(exec(stack3(Pop), f))))
  def ~~>[X, Y, Z, R](f: (X, Y, Z, A) => R) = new ReductionRule3[X, Y, Z, R](append(push(exec(stack4(Pop), f))))
  def ~~?(f: A => Boolean) = new Rule0(append(exec(stack1(Pop), f)))
  def ~~?[Z](f: (Z, A) => Boolean) = new PopRule1[Z](append(exec(stack2(Pop), f)))
  def ~~?[Y, Z](f: (Y, Z, A) => Boolean) = new PopRule2[Y, Z](append(exec(stack3(Pop), f)))
  def ~~?[X, Y, Z](f: (X, Y, Z, A) => Boolean) = new PopRule3[X, Y, Z](append(exec(stack4(Pop), f)))
  def ~~%(f: A => Unit) = new Rule0(append(ok(exec(stack1(Pop), f))))
  def ~~%[Z](f: (Z, A) => Unit) = new PopRule1[Z](append(ok(exec(stack1(Pop), f))))
  def ~~%[Y, Z](f: (Y, Z, A) => Unit) = new PopRule2[Y, Z](append(ok(exec(stack3(Pop), f))))
  def ~~%[X, Y, Z](f: (X, Y, Z, A) => Unit) = new PopRule3[X, Y, Z](append(ok(exec(stack4(Pop), f))))
  def ~~~>[R](f: A => R) = new Rule2[A, R](append(push(exec(stack1(Peek), f))))
  def ~~~?(f: A => Boolean) = withMatcher(append(exec(stack1(Peek), f)))
  def ~~~%(f: A => Unit) = withMatcher(append(ok(exec(stack1(Peek), f))))
  def |[AA >: A](other: Rule1[AA]) = new Rule1[AA](appendChoice(other))
  protected def withMatcher(matcher: Matcher) = new Rule1[A](matcher).asInstanceOf[this.type]
}

object Rule1 {
  implicit def toRule(rule: Rule1[_]): imagingbook.parboiled.Rule = rule.matcher
}

/**
 * A rule pushing two new values of given types onto the parsers value stack.
 */
class Rule2[+A, +B](val matcher: Matcher) extends PushRule {
  def ~[Z, AA >: A, BB >: B](other: PopRule3[Z, AA, BB]) = new PopRule1[Z](append(other))
  def ~[AA >: A, BB >: B](other: PopRule2[AA, BB]) = new Rule0(append(other))
  def ~[BB >: B](other: PopRule1[BB]) = new Rule1[A](append(other))
  def ~[Z, AA >: A, BB >: B, RA](other: ReductionRule3[Z, AA, BB, RA]) = new ReductionRule1[Z, RA](append(other))
  def ~[Z, AA >: A, BB >: B, RA, RB](other: ReductionRule3_2[Z, AA, BB, RA, RB]) = new ReductionRule1_2[Z, RA, RB](append(other))
  def ~[Z, AA >: A, BB >: B, RA, RB, C](other: ReductionRule3_3[Z, AA, BB, RA, RB, C]) = new ReductionRule1_3[Z, RA, RB, C](append(other))
  def ~[Z, AA >: A, BB >: B, RA, RB, C, D](other: ReductionRule3_4[Z, AA, BB, RA, RB, C, D]) = new ReductionRule1_4[Z, RA, RB, C, D](append(other))
  def ~[Z, AA >: A, BB >: B, RA, RB, C, D, E](other: ReductionRule3_5[Z, AA, BB, RA, RB, C, D, E]) = new ReductionRule1_5[Z, RA, RB, C, D, E](append(other))
  def ~[Z, AA >: A, BB >: B, RA, RB, C, D, E, F](other: ReductionRule3_6[Z, AA, BB, RA, RB, C, D, E, F]) = new ReductionRule1_6[Z, RA, RB, C, D, E, F](append(other))
  def ~[Z, AA >: A, BB >: B, RA, RB, C, D, E, F, G](other: ReductionRule3_7[Z, AA, BB, RA, RB, C, D, E, F, G]) = new ReductionRule1_7[Z, RA, RB, C, D, E, F, G](append(other))
  def ~[AA >: A, BB >: B, RA](other: ReductionRule2[AA, BB, RA]) = new Rule1[RA](append(other))
  def ~[AA >: A, BB >: B, RA, RB](other: ReductionRule2_2[AA, BB, RA, RB]) = new Rule2[RA, RB](append(other))
  def ~[AA >: A, BB >: B, RA, RB, C](other: ReductionRule2_3[AA, BB, RA, RB, C]) = new Rule3[RA, RB, C](append(other))
  def ~[AA >: A, BB >: B, RA, RB, C, D](other: ReductionRule2_4[AA, BB, RA, RB, C, D]) = new Rule4[RA, RB, C, D](append(other))
  def ~[AA >: A, BB >: B, RA, RB, C, D, E](other: ReductionRule2_5[AA, BB, RA, RB, C, D, E]) = new Rule5[RA, RB, C, D, E](append(other))
  def ~[AA >: A, BB >: B, RA, RB, C, D, E, F](other: ReductionRule2_6[AA, BB, RA, RB, C, D, E, F]) = new Rule6[RA, RB, C, D, E, F](append(other))
  def ~[AA >: A, BB >: B, RA, RB, C, D, E, F, G](other: ReductionRule2_7[AA, BB, RA, RB, C, D, E, F, G]) = new Rule7[RA, RB, C, D, E, F, G](append(other))
  def ~[BB >: B, RB](other: ReductionRule1[BB, RB]) = new Rule2[A, RB](append(other))
  def ~[BB >: B, RB, C](other: ReductionRule1_2[BB, RB, C]) = new Rule3[A, RB, C](append(other))
  def ~[BB >: B, RB, C, D](other: ReductionRule1_3[BB, RB, C, D]) = new Rule4[A, RB, C, D](append(other))
  def ~[BB >: B, RB, C, D, E](other: ReductionRule1_4[BB, RB, C, D, E]) = new Rule5[A, RB, C, D, E](append(other))
  def ~[BB >: B, RB, C, D, E, F](other: ReductionRule1_5[BB, RB, C, D, E, F]) = new Rule6[A, RB, C, D, E, F](append(other))
  def ~[BB >: B, RB, C, D, E, F, G](other: ReductionRule1_6[BB, RB, C, D, E, F, G]) = new Rule7[A, RB, C, D, E, F, G](append(other))
  def ~[C](other: Rule1[C]) = new Rule3[A, B, C](append(other))
  def ~[C, D](other: Rule2[C, D]) = new Rule4[A, B, C, D](append(other))
  def ~[C, D, E](other: Rule3[C, D, E]) = new Rule5[A, B, C, D, E](append(other))
  def ~[C, D, E, F](other: Rule4[C, D, E, F]) = new Rule6[A, B, C, D, E, F](append(other))
  def ~[C, D, E, F, G](other: Rule5[C, D, E, F, G]) = new Rule7[A, B, C, D, E, F, G](append(other))
  def ~:>[R](f: Char => R) = new Rule3[A, B, R](append(push(exec(GetMatchedChar, f))))
  def ~>>[R](f: IndexRange => R) = new Rule3[A, B, R](append(push(exec(GetMatchRange, f))))
  def ~>[R](f: String => R) = new Rule3[A, B, R](append(push(exec(GetMatch, f))))
  def ~~>[R](f: B => R) = new Rule2[A, R](append(push(exec(stack1(Pop), f))))
  def ~~>[R](f: (A, B) => R) = new Rule1[R](append(push(exec(stack2(Pop), f))))
  def ~~>[Z, R](f: (Z, A, B) => R) = new ReductionRule1[Z, R](append(push(exec(stack3(Pop), f))))
  def ~~>[Y, Z, R](f: (Y, Z, A, B) => R) = new ReductionRule2[Y, Z, R](append(push(exec(stack4(Pop), f))))
  def ~~>[X, Y, Z, R](f: (X, Y, Z, A, B) => R) = new ReductionRule3[X, Y, Z, R](append(push(exec(stack5(Pop), f))))
  def ~~?(f: B => Boolean) = new Rule1[A](append(exec(stack1(Pop), f)))
  def ~~?(f: (A, B) => Boolean) = new Rule0(append(exec(stack2(Pop), f)))
  def ~~?[Z](f: (Z, A, B) => Boolean) = new PopRule1[Z](append(exec(stack3(Pop), f)))
  def ~~?[Y, Z](f: (Y, Z, A, B) => Boolean) = new PopRule2[Y, Z](append(exec(stack4(Pop), f)))
  def ~~?[X, Y, Z](f: (X, Y, Z, A, B) => Boolean) = new PopRule3[X, Y, Z](append(exec(stack5(Pop), f)))
  def ~~%(f: B => Unit) = new Rule1[A](append(ok(exec(stack1(Pop), f))))
  def ~~%(f: (A, B) => Unit) = new Rule0(append(ok(exec(stack2(Pop), f))))
  def ~~%[Z](f: (Z, A, B) => Unit) = new PopRule1[Z](append(ok(exec(stack3(Pop), f))))
  def ~~%[Y, Z](f: (Y, Z, A, B) => Unit) = new PopRule2[Y, Z](append(ok(exec(stack4(Pop), f))))
  def ~~%[X, Y, Z](f: (X, Y, Z, A, B) => Unit) = new PopRule3[X, Y, Z](append(ok(exec(stack5(Pop), f))))
  def ~~~>[R](f: B => R) = new Rule3[A, B, R](append(push(exec(stack1(Peek), f))))
  def ~~~>[R](f: (A, B) => R) = new Rule3[A, B, R](append(push(exec(stack2(Peek), f))))
  def ~~~?(f: B => Boolean) = withMatcher(append(exec(stack1(Peek), f)))
  def ~~~?(f: (A, B) => Boolean) = withMatcher(append(exec(stack2(Peek), f)))
  def ~~~%(f: B => Unit) = withMatcher(append(ok(exec(stack1(Peek), f))))
  def ~~~%(f: (A, B) => Unit) = withMatcher(append(ok(exec(stack2(Peek), f))))
  def |[AA >: A, BB >: B](other: Rule2[AA, BB]) = new Rule2[AA, BB](appendChoice(other))
  protected def withMatcher(matcher: Matcher) = new Rule2[A, B](matcher).asInstanceOf[this.type]
}

/**
 * A rule pushing 3 new values of given types onto the parsers value stack.
 */
class Rule3[+A, +B, +C](val matcher: Matcher) extends PushRule {
  def ~[AA >: A, BB >: B, CC >: C](other: PopRule3[AA, BB, CC]) = new Rule0(append(other))
  def ~[BB >: B, CC >: C](other: PopRule2[BB, CC]) = new Rule1[A](append(other))
  def ~[CC >: C](other: PopRule1[CC]) = new Rule2[A, B](append(other))
  def ~[AA >: A, BB >: B, CC >: C, RA](other: ReductionRule3[AA, BB, CC, RA]) = new Rule1[RA](append(other))
  def ~[AA >: A, BB >: B, CC >: C, RA, RB](other: ReductionRule3_2[AA, BB, CC, RA, RB]) = new Rule2[RA, RB](append(other))
  def ~[AA >: A, BB >: B, CC >: C, RA, RB, RC](other: ReductionRule3_3[AA, BB, CC, RA, RB, RC]) = new Rule3[RA, RB, RC](append(other))
  def ~[AA >: A, BB >: B, CC >: C, RA, RB, RC, D](other: ReductionRule3_4[AA, BB, CC, RA, RB, RC, D]) = new Rule4[RA, RB, RC, D](append(other))
  def ~[AA >: A, BB >: B, CC >: C, RA, RB, RC, D, E](other: ReductionRule3_5[AA, BB, CC, RA, RB, RC, D, E]) = new Rule5[RA, RB, RC, D, E](append(other))
  def ~[AA >: A, BB >: B, CC >: C, RA, RB, RC, D, E, F](other: ReductionRule3_6[AA, BB, CC, RA, RB, RC, D, E, F]) = new Rule6[RA, RB, RC, D, E, F](append(other))
  def ~[AA >: A, BB >: B, CC >: C, RA, RB, RC, D, E, F, G](other: ReductionRule3_7[AA, BB, CC, RA, RB, RC, D, E, F, G]) = new Rule7[RA, RB, RC, D, E, F, G](append(other))
  def ~[BB >: B, CC >: C, RB](other: ReductionRule2[BB, CC, RB]) = new Rule2[A, RB](append(other))
  def ~[BB >: B, CC >: C, RB, RC](other: ReductionRule2_2[BB, CC, RB, RC]) = new Rule3[A, RB, RC](append(other))
  def ~[BB >: B, CC >: C, RB, RC, D](other: ReductionRule2_3[BB, CC, RB, RC, D]) = new Rule4[A, RB, RC, D](append(other))
  def ~[BB >: B, CC >: C, RB, RC, D, E](other: ReductionRule2_4[BB, CC, RB, RC, D, E]) = new Rule5[A, RB, RC, D, E](append(other))
  def ~[BB >: B, CC >: C, RB, RC, D, E, F](other: ReductionRule2_5[BB, CC, RB, RC, D, E, F]) = new Rule6[A, RB, RC, D, E, F](append(other))
  def ~[BB >: B, CC >: C, RB, RC, D, E, F, G](other: ReductionRule2_6[BB, CC, RB, RC, D, E, F, G]) = new Rule7[A, RB, RC, D, E, F, G](append(other))
  def ~[CC >: C, RC](other: ReductionRule1[CC, RC]) = new Rule3[A, B, RC](append(other))
  def ~[CC >: C, RC, D](other: ReductionRule1_2[CC, RC, D]) = new Rule4[A, B, RC, D](append(other))
  def ~[CC >: C, RC, D, E](other: ReductionRule1_3[CC, RC, D, E]) = new Rule5[A, B, RC, D, E](append(other))
  def ~[CC >: C, RC, D, E, F](other: ReductionRule1_4[CC, RC, D, E, F]) = new Rule6[A, B, RC, D, E, F](append(other))
  def ~[CC >: C, RC, D, E, F, G](other: ReductionRule1_5[CC, RC, D, E, F, G]) = new Rule7[A, B, RC, D, E, F, G](append(other))
  def ~[D](other: Rule1[D]) = new Rule4[A, B, C, D](append(other))
  def ~[D, E](other: Rule2[D, E]) = new Rule5[A, B, C, D, E](append(other))
  def ~[D, E, F](other: Rule3[D, E, F]) = new Rule6[A, B, C, D, E, F](append(other))
  def ~[D, E, F, G](other: Rule4[D, E, F, G]) = new Rule7[A, B, C, D, E, F, G](append(other))
  def ~:>[R](f: Char => R) = new Rule4[A, B, C, R](append(push(exec(GetMatchedChar, f))))
  def ~>>[R](f: IndexRange => R) = new Rule4[A, B, C, R](append(push(exec(GetMatchRange, f))))
  def ~>[R](f: String => R) = new Rule4[A, B, C, R](append(push(exec(GetMatch, f))))
  def ~~>[R](f: C => R) = new Rule3[A, B, R](append(push(exec(stack1(Pop), f))))
  def ~~>[R](f: (B, C) => R) = new Rule2[A, R](append(push(exec(stack2(Pop), f))))
  def ~~>[R](f: (A, B, C) => R) = new Rule1[R](append(push(exec(stack3(Pop), f))))
  def ~~>[Z, R](f: (Z, A, B, C) => R) = new ReductionRule1[Z, R](append(push(exec(stack4(Pop), f))))
  def ~~>[Y, Z, R](f: (Y, Z, A, B, C) => R) = new ReductionRule2[Y, Z, R](append(push(exec(stack5(Pop), f))))
  def ~~>[X, Y, Z, R](f: (X, Y, Z, A, B, C) => R) = new ReductionRule3[X, Y, Z, R](append(push(exec(stack6(Pop), f))))
  def ~~?(f: C => Boolean) = new Rule2[A, B](append(exec(stack1(Pop), f)))
  def ~~?(f: (B, C) => Boolean) = new Rule1[A](append(exec(stack2(Pop), f)))
  def ~~?(f: (A, B, C) => Boolean) = new Rule0(append(exec(stack3(Pop), f)))
  def ~~?[Z](f: (Z, A, B, C) => Boolean) = new PopRule1[Z](append(exec(stack4(Pop), f)))
  def ~~?[Y, Z](f: (Y, Z, A, B, C) => Boolean) = new PopRule2[Y, Z](append(exec(stack5(Pop), f)))
  def ~~?[X, Y, Z](f: (X, Y, Z, A, B, C) => Boolean) = new PopRule3[X, Y, Z](append(exec(stack6(Pop), f)))
  def ~~%(f: C => Unit) = new Rule2[A, B](append(ok(exec(stack1(Pop), f))))
  def ~~%(f: (B, C) => Unit) = new Rule1[A](append(ok(exec(stack2(Pop), f))))
  def ~~%(f: (A, B, C) => Unit) = new Rule0(append(ok(exec(stack3(Pop), f))))
  def ~~%[Z](f: (Z, A, B, C) => Unit) = new PopRule1[Z](append(ok(exec(stack4(Pop), f))))
  def ~~%[Y, Z](f: (Y, Z, A, B, C) => Unit) = new PopRule2[Y, Z](append(ok(exec(stack5(Pop), f))))
  def ~~%[X, Y, Z](f: (X, Y, Z, A, B, C) => Unit) = new PopRule3[X, Y, Z](append(ok(exec(stack6(Pop), f))))
  def ~~~>[R](f: C => R) = new Rule4[A, B, C, R](append(push(exec(stack1(Peek), f))))
  def ~~~>[R](f: (B, C) => R) = new Rule4[A, B, C, R](append(push(exec(stack2(Peek), f))))
  def ~~~>[R](f: (A, B, C) => R) = new Rule4[A, B, C, R](append(push(exec(stack3(Peek), f))))
  def ~~~?(f: C => Boolean) = withMatcher(append(exec(stack1(Peek), f)))
  def ~~~?(f: (B, C) => Boolean) = withMatcher(append(exec(stack2(Peek), f)))
  def ~~~?(f: (A, B, C) => Boolean) = withMatcher(append(exec(stack3(Peek), f)))
  def ~~~%(f: C => Unit) = withMatcher(append(ok(exec(stack1(Peek), f))))
  def ~~~%(f: (B, C) => Unit) = withMatcher(append(ok(exec(stack2(Peek), f))))
  def ~~~%(f: (A, B, C) => Unit) = withMatcher(append(ok(exec(stack3(Peek), f))))
  def |[AA >: A, BB >: B, CC >: C](other: Rule3[AA, BB, CC]) = new Rule3[AA, BB, CC](appendChoice(other))
  protected def withMatcher(matcher: Matcher) = new Rule3[A, B, C](matcher).asInstanceOf[this.type]
}

/**
 * A rule pushing 4 new values of given types onto the parsers value stack.
 */
class Rule4[+A, +B, +C, +D](val matcher: Matcher) extends PushRule {
  def ~[BB >: B, CC >: C, DD >: D](other: PopRule3[BB, CC, DD]) = new Rule1[A](append(other))
  def ~[CC >: C, DD >: D](other: PopRule2[CC, DD]) = new Rule2[A, B](append(other))
  def ~[DD >: D](other: PopRule1[DD]) = new Rule3[A, B, C](append(other))
  def ~[BB >: B, CC >: C, DD >: D, RB](other: ReductionRule3[BB, CC, DD, RB]) = new Rule2[A, RB](append(other))
  def ~[BB >: B, CC >: C, DD >: D, RB, RC](other: ReductionRule3_2[BB, CC, DD, RB, RC]) = new Rule3[A, RB, RC](append(other))
  def ~[BB >: B, CC >: C, DD >: D, RB, RC, RD](other: ReductionRule3_3[BB, CC, DD, RB, RC, RD]) = new Rule4[A, RB, RC, RD](append(other))
  def ~[BB >: B, CC >: C, DD >: D, RB, RC, RD, E](other: ReductionRule3_4[BB, CC, DD, RB, RC, RD, E]) = new Rule5[A, RB, RC, RD, E](append(other))
  def ~[BB >: B, CC >: C, DD >: D, RB, RC, RD, E, F](other: ReductionRule3_5[BB, CC, DD, RB, RC, RD, E, F]) = new Rule6[A, RB, RC, RD, E, F](append(other))
  def ~[BB >: B, CC >: C, DD >: D, RB, RC, RD, E, F, G](other: ReductionRule3_6[BB, CC, DD, RB, RC, RD, E, F, G]) = new Rule7[A, RB, RC, RD, E, F, G](append(other))
  def ~[CC >: C, DD >: D, RC](other: ReductionRule2[CC, DD, RC]) = new Rule3[A, B, RC](append(other))
  def ~[CC >: C, DD >: D, RC, RD](other: ReductionRule2_2[CC, DD, RC, RD]) = new Rule4[A, B, RC, RD](append(other))
  def ~[CC >: C, DD >: D, RC, RD, E](other: ReductionRule2_3[CC, DD, RC, RD, E]) = new Rule5[A, B, RC, RD, E](append(other))
  def ~[CC >: C, DD >: D, RC, RD, E, F](other: ReductionRule2_4[CC, DD, RC, RD, E, F]) = new Rule6[A, B, RC, RD, E, F](append(other))
  def ~[CC >: C, DD >: D, RC, RD, E, F, G](other: ReductionRule2_5[CC, DD, RC, RD, E, F, G]) = new Rule7[A, B, RC, RD, E, F, G](append(other))
  def ~[DD >: D, RD](other: ReductionRule1[DD, RD]) = new Rule4[A, B, C, RD](append(other))
  def ~[DD >: D, RD, E](other: ReductionRule1_2[DD, RD, E]) = new Rule5[A, B, C, RD, E](append(other))
  def ~[DD >: D, RD, E, F](other: ReductionRule1_3[DD, RD, E, F]) = new Rule6[A, B, C, RD, E, F](append(other))
  def ~[DD >: D, RD, E, F, G](other: ReductionRule1_4[DD, RD, E, F, G]) = new Rule7[A, B, C, RD, E, F, G](append(other))
  def ~[E](other: Rule1[E]) = new Rule5[A, B, C, D, E](append(other))
  def ~[E, F](other: Rule2[E, F]) = new Rule6[A, B, C, D, E, F](append(other))
  def ~[E, F, G](other: Rule3[E, F, G]) = new Rule7[A, B, C, D, E, F, G](append(other))
  def ~:>[R](f: Char => R) = new Rule5[A, B, C, D, R](append(push(exec(GetMatchedChar, f))))
  def ~>>[R](f: IndexRange => R) = new Rule5[A, B, C, D, R](append(push(exec(GetMatchRange, f))))
  def ~>[R](f: String => R) = new Rule5[A, B, C, D, R](append(push(exec(GetMatch, f))))
  def ~~>[R](f: D => R) = new Rule4[A, B, C, R](append(push(exec(stack1(Pop), f))))
  def ~~>[R](f: (C, D) => R) = new Rule3[A, B, R](append(push(exec(stack2(Pop), f))))
  def ~~>[R](f: (B, C, D) => R) = new Rule2[A, R](append(push(exec(stack3(Pop), f))))
  def ~~>[R](f: (A, B, C, D) => R) = new Rule1[R](append(push(exec(stack4(Pop), f))))
  def ~~>[Z, R](f: (Z, A, B, C, D) => R) = new ReductionRule1[Z, R](append(push(exec(stack5(Pop), f))))
  def ~~>[Y, Z, R](f: (Y, Z, A, B, C, D) => R) = new ReductionRule2[Y, Z, R](append(push(exec(stack6(Pop), f))))
  def ~~>[X, Y, Z, R](f: (X, Y, Z, A, B, C, D) => R) = new ReductionRule3[X, Y, Z, R](append(push(exec(stack7(Pop), f))))
  def ~~?(f: D => Boolean) = new Rule3[A, B, C](append(exec(stack1(Pop), f)))
  def ~~?(f: (C, D) => Boolean) = new Rule2[A, B](append(exec(stack2(Pop), f)))
  def ~~?(f: (B, C, D) => Boolean) = new Rule1[A](append(exec(stack3(Pop), f)))
  def ~~?(f: (A, B, C, D) => Boolean) = new Rule0(append(exec(stack4(Pop), f)))
  def ~~?[Z](f: (Z, A, B, C, D) => Boolean) = new PopRule1[Z](append(exec(stack5(Pop), f)))
  def ~~?[Y, Z](f: (Y, Z, A, B, C, D) => Boolean) = new PopRule2[Y, Z](append(exec(stack6(Pop), f)))
  def ~~?[X, Y, Z](f: (X, Y, Z, A, B, C, D) => Boolean) = new PopRule3[X, Y, Z](append(exec(stack7(Pop), f)))
  def ~~%(f: D => Unit) = new Rule3[A, B, C](append(ok(exec(stack1(Pop), f))))
  def ~~%(f: (C, D) => Unit) = new Rule2[A, B](append(ok(exec(stack2(Pop), f))))
  def ~~%(f: (B, C, D) => Unit) = new Rule1[A](append(ok(exec(stack3(Pop), f))))
  def ~~%(f: (A, B, C, D) => Unit) = new Rule0(append(ok(exec(stack4(Pop), f))))
  def ~~%[Z](f: (Z, A, B, C, D) => Unit) = new PopRule1[Z](append(ok(exec(stack5(Pop), f))))
  def ~~%[Y, Z](f: (Y, Z, A, B, C, D) => Unit) = new PopRule2[Y, Z](append(ok(exec(stack6(Pop), f))))
  def ~~%[X, Y, Z](f: (X, Y, Z, A, B, C, D) => Unit) = new PopRule3[X, Y, Z](append(ok(exec(stack7(Pop), f))))
  def ~~~>[R](f: D => R) = new Rule5[A, B, C, D, R](append(push(exec(stack1(Peek), f))))
  def ~~~>[R](f: (C, D) => R) = new Rule5[A, B, C, D, R](append(push(exec(stack2(Peek), f))))
  def ~~~>[R](f: (B, C, D) => R) = new Rule5[A, B, C, D, R](append(push(exec(stack3(Peek), f))))
  def ~~~>[R](f: (A, B, C, D) => R) = new Rule5[A, B, C, D, R](append(push(exec(stack4(Peek), f))))
  def ~~~?(f: D => Boolean) = withMatcher(append(exec(stack1(Peek), f)))
  def ~~~?(f: (C, D) => Boolean) = withMatcher(append(exec(stack2(Peek), f)))
  def ~~~?(f: (B, C, D) => Boolean) = withMatcher(append(exec(stack3(Peek), f)))
  def ~~~?(f: (A, B, C, D) => Boolean) = withMatcher(append(exec(stack4(Peek), f)))
  def ~~~%(f: D => Unit) = withMatcher(append(ok(exec(stack1(Peek), f))))
  def ~~~%(f: (C, D) => Unit) = withMatcher(append(ok(exec(stack2(Peek), f))))
  def ~~~%(f: (B, C, D) => Unit) = withMatcher(append(ok(exec(stack3(Peek), f))))
  def ~~~%(f: (A, B, C, D) => Unit) = withMatcher(append(ok(exec(stack4(Peek), f))))
  def |[AA >: A, BB >: B, CC >: C, DD >: D](other: Rule4[AA, BB, CC, DD]) = new Rule4[AA, BB, CC, DD](appendChoice(other))
  protected def withMatcher(matcher: Matcher) = new Rule4[A, B, C, D](matcher).asInstanceOf[this.type]
}

/**
 * A rule pushing 5 new values of given types onto the parsers value stack.
 */
class Rule5[+A, +B, +C, +D, +E](val matcher: Matcher) extends PushRule {
  def ~[CC >: C, DD >: D, EE >: E](other: PopRule3[CC, DD, EE]) = new Rule2[A, B](append(other))
  def ~[DD >: D, EE >: E](other: PopRule2[DD, EE]) = new Rule3[A, B, C](append(other))
  def ~[EE >: E](other: PopRule1[EE]) = new Rule4[A, B, C, D](append(other))
  def ~[CC >: C, DD >: D, EE >: E, RC](other: ReductionRule3[CC, DD, EE, RC]) = new Rule3[A, B, RC](append(other))
  def ~[CC >: C, DD >: D, EE >: E, RC, RD](other: ReductionRule3_2[CC, DD, EE, RC, RD]) = new Rule4[A, B, RC, RD](append(other))
  def ~[CC >: C, DD >: D, EE >: E, RC, RD, RE](other: ReductionRule3_3[CC, DD, EE, RC, RD, RE]) = new Rule5[A, B, RC, RD, RE](append(other))
  def ~[CC >: C, DD >: D, EE >: E, RC, RD, RE, F](other: ReductionRule3_4[CC, DD, EE, RC, RD, RE, F]) = new Rule6[A, B, RC, RD, RE, F](append(other))
  def ~[CC >: C, DD >: D, EE >: E, RC, RD, RE, F, G](other: ReductionRule3_5[CC, DD, EE, RC, RD, RE, F, G]) = new Rule7[A, B, RC, RD, RE, F, G](append(other))
  def ~[DD >: D, EE >: E, RD](other: ReductionRule2[DD, EE, RD]) = new Rule4[A, B, C, RD](append(other))
  def ~[DD >: D, EE >: E, RD, RE](other: ReductionRule2_2[DD, EE, RD, RE]) = new Rule5[A, B, C, RD, RE](append(other))
  def ~[DD >: D, EE >: E, RD, RE, F](other: ReductionRule2_3[DD, EE, RD, RE, F]) = new Rule6[A, B, C, RD, RE, F](append(other))
  def ~[DD >: D, EE >: E, RD, RE, F, G](other: ReductionRule2_4[DD, EE, RD, RE, F, G]) = new Rule7[A, B, C, RD, RE, F, G](append(other))
  def ~[EE >: E, RE](other: ReductionRule1[EE, RE]) = new Rule5[A, B, C, D, RE](append(other))
  def ~[EE >: E, RE, F](other: ReductionRule1_2[EE, RE, F]) = new Rule6[A, B, C, D, RE, F](append(other))
  def ~[EE >: E, RE, F, G](other: ReductionRule1_3[EE, RE, F, G]) = new Rule7[A, B, C, D, RE, F, G](append(other))
  def ~[F](other: Rule1[F]) = new Rule6[A, B, C, D, E, F](append(other))
  def ~[F, G](other: Rule2[F, G]) = new Rule7[A, B, C, D, E, F, G](append(other))
  def ~:>[R](f: Char => R) = new Rule6[A, B, C, D, E, R](append(push(exec(GetMatchedChar, f))))
  def ~>>[R](f: IndexRange => R) = new Rule6[A, B, C, D, E, R](append(push(exec(GetMatchRange, f))))
  def ~>[R](f: String => R) = new Rule6[A, B, C, D, E, R](append(push(exec(GetMatch, f))))
  def ~~>[R](f: E => R) = new Rule5[A, B, C, D, R](append(push(exec(stack1(Pop), f))))
  def ~~>[R](f: (D, E) => R) = new Rule4[A, B, C, R](append(push(exec(stack2(Pop), f))))
  def ~~>[R](f: (C, D, E) => R) = new Rule3[A, B, R](append(push(exec(stack3(Pop), f))))
  def ~~>[R](f: (B, C, D, E) => R) = new Rule2[A, R](append(push(exec(stack4(Pop), f))))
  def ~~>[R](f: (A, B, C, D, E) => R) = new Rule1[R](append(push(exec(stack5(Pop), f))))
  def ~~>[Z, R](f: (Z, A, B, C, D, E) => R) = new ReductionRule1[Z, R](append(push(exec(stack6(Pop), f))))
  def ~~>[Y, Z, R](f: (Y, Z, A, B, C, D, E) => R) = new ReductionRule2[Y, Z, R](append(push(exec(stack7(Pop), f))))
  def ~~?(f: E => Boolean) = new Rule4[A, B, C, D](append(exec(stack1(Pop), f)))
  def ~~?(f: (D, E) => Boolean) = new Rule3[A, B, C](append(exec(stack2(Pop), f)))
  def ~~?(f: (C, D, E) => Boolean) = new Rule2[A, B](append(exec(stack3(Pop), f)))
  def ~~?(f: (B, C, D, E) => Boolean) = new Rule1[A](append(exec(stack4(Pop), f)))
  def ~~?(f: (A, B, C, D, E) => Boolean) = new Rule0(append(exec(stack5(Pop), f)))
  def ~~?[Z](f: (Z, A, B, C, D, E) => Boolean) = new PopRule1[Z](append(exec(stack6(Pop), f)))
  def ~~?[Y, Z](f: (Y, Z, A, B, C, D, E) => Boolean) = new PopRule2[Y, Z](append(exec(stack7(Pop), f)))
  def ~~%(f: E => Unit) = new Rule4[A, B, C, D](append(ok(exec(stack1(Pop), f))))
  def ~~%(f: (D, E) => Unit) = new Rule3[A, B, C](append(ok(exec(stack2(Pop), f))))
  def ~~%(f: (C, D, E) => Unit) = new Rule2[A, B](append(ok(exec(stack3(Pop), f))))
  def ~~%(f: (B, C, D, E) => Unit) = new Rule1[A](append(ok(exec(stack4(Pop), f))))
  def ~~%(f: (A, B, C, D, E) => Unit) = new Rule0(append(ok(exec(stack5(Pop), f))))
  def ~~%[Z](f: (Z, A, B, C, D, E) => Unit) = new PopRule1[Z](append(ok(exec(stack6(Pop), f))))
  def ~~%[Y, Z](f: (Y, Z, A, B, C, D, E) => Unit) = new PopRule2[Y, Z](append(ok(exec(stack7(Pop), f))))
  def ~~~>[R](f: E => R) = new Rule6[A, B, C, D, E, R](append(push(exec(stack1(Peek), f))))
  def ~~~>[R](f: (D, E) => R) = new Rule6[A, B, C, D, E, R](append(push(exec(stack2(Peek), f))))
  def ~~~>[R](f: (C, D, E) => R) = new Rule6[A, B, C, D, E, R](append(push(exec(stack3(Peek), f))))
  def ~~~>[R](f: (B, C, D, E) => R) = new Rule6[A, B, C, D, E, R](append(push(exec(stack4(Peek), f))))
  def ~~~>[R](f: (A, B, C, D, E) => R) = new Rule6[A, B, C, D, E, R](append(push(exec(stack5(Peek), f))))
  def ~~~?(f: E => Boolean) = withMatcher(append(exec(stack1(Peek), f)))
  def ~~~?(f: (D, E) => Boolean) = withMatcher(append(exec(stack2(Peek), f)))
  def ~~~?(f: (C, D, E) => Boolean) = withMatcher(append(exec(stack3(Peek), f)))
  def ~~~?(f: (B, C, D, E) => Boolean) = withMatcher(append(exec(stack4(Peek), f)))
  def ~~~?(f: (A, B, C, D, E) => Boolean) = withMatcher(append(exec(stack5(Peek), f)))
  def ~~~%(f: E => Unit) = withMatcher(append(ok(exec(stack1(Peek), f))))
  def ~~~%(f: (D, E) => Unit) = withMatcher(append(ok(exec(stack2(Peek), f))))
  def ~~~%(f: (C, D, E) => Unit) = withMatcher(append(ok(exec(stack3(Peek), f))))
  def ~~~%(f: (B, C, D, E) => Unit) = withMatcher(append(ok(exec(stack4(Peek), f))))
  def ~~~%(f: (A, B, C, D, E) => Unit) = withMatcher(append(ok(exec(stack5(Peek), f))))
  def |[AA >: A, BB >: B, CC >: C, DD >: D, EE >: E](other: Rule5[AA, BB, CC, DD, EE]) = new Rule5[AA, BB, CC, DD, EE](appendChoice(other))
  protected def withMatcher(matcher: Matcher) = new Rule5[A, B, C, D, E](matcher).asInstanceOf[this.type]
}

/**
 * A rule pushing 6 new values of given types onto the parsers value stack.
 */
class Rule6[+A, +B, +C, +D, +E, +F](val matcher: Matcher) extends PushRule {
  def ~[DD >: D, EE >: E, FF >: F](other: PopRule3[DD, EE, FF]) = new Rule3[A, B, C](append(other))
  def ~[EE >: E, FF >: F](other: PopRule2[EE, FF]) = new Rule4[A, B, C, D](append(other))
  def ~[FF >: F](other: PopRule1[FF]) = new Rule5[A, B, C, D, E](append(other))
  def ~[DD >: D, EE >: E, FF >: F, RD](other: ReductionRule3[DD, EE, FF, RD]) = new Rule4[A, B, C, RD](append(other))
  def ~[DD >: D, EE >: E, FF >: F, RD, RE](other: ReductionRule3_2[DD, EE, FF, RD, RE]) = new Rule5[A, B, C, RD, RE](append(other))
  def ~[DD >: D, EE >: E, FF >: F, RD, RE, RF](other: ReductionRule3_3[DD, EE, FF, RD, RE, RF]) = new Rule6[A, B, C, RD, RE, RF](append(other))
  def ~[DD >: D, EE >: E, FF >: F, RD, RE, RF, G](other: ReductionRule3_4[DD, EE, FF, RD, RE, RF, G]) = new Rule7[A, B, C, RD, RE, RF, G](append(other))
  def ~[EE >: E, FF >: F, RE](other: ReductionRule2[EE, FF, RE]) = new Rule5[A, B, C, D, RE](append(other))
  def ~[EE >: E, FF >: F, RE, RF](other: ReductionRule2_2[EE, FF, RE, RF]) = new Rule6[A, B, C, D, RE, RF](append(other))
  def ~[EE >: E, FF >: F, RE, RF, G](other: ReductionRule2_3[EE, FF, RE, RF, G]) = new Rule7[A, B, C, D, RE, RF, G](append(other))
  def ~[FF >: F, RF](other: ReductionRule1[FF, RF]) = new Rule6[A, B, C, D, E, RF](append(other))
  def ~[FF >: F, RF, G](other: ReductionRule1_2[FF, RF, G]) = new Rule7[A, B, C, D, E, RF, G](append(other))
  def ~[G](other: Rule1[G]) = new Rule7[A, B, C, D, E, F, G](append(other))
  def ~:>[R](f: Char => R) = new Rule7[A, B, C, D, E, F, R](append(push(exec(GetMatchedChar, f))))
  def ~>>[R](f: IndexRange => R) = new Rule7[A, B, C, D, E, F, R](append(push(exec(GetMatchRange, f))))
  def ~>[R](f: String => R) = new Rule7[A, B, C, D, E, F, R](append(push(exec(GetMatch, f))))
  def ~~>[R](f: E => R) = new Rule6[A, B, C, D, E, R](append(push(exec(stack1(Pop), f))))
  def ~~>[R](f: (E, F) => R) = new Rule5[A, B, C, D, R](append(push(exec(stack2(Pop), f))))
  def ~~>[R](f: (D, E, F) => R) = new Rule4[A, B, C, R](append(push(exec(stack3(Pop), f))))
  def ~~>[R](f: (C, D, E, F) => R) = new Rule3[A, B, R](append(push(exec(stack4(Pop), f))))
  def ~~>[R](f: (B, C, D, E, F) => R) = new Rule2[A, R](append(push(exec(stack5(Pop), f))))
  def ~~>[R](f: (A, B, C, D, E, F) => R) = new Rule1[R](append(push(exec(stack6(Pop), f))))
  def ~~>[Z, R](f: (Z, A, B, C, D, E, F) => R) = new ReductionRule1[Z, R](append(push(exec(stack7(Pop), f))))
  def ~~?(f: F => Boolean) = new Rule5[A, B, C, D, E](append(exec(stack1(Pop), f)))
  def ~~?(f: (E, F) => Boolean) = new Rule4[A, B, C, D](append(exec(stack2(Pop), f)))
  def ~~?(f: (D, E, F) => Boolean) = new Rule3[A, B, C](append(exec(stack3(Pop), f)))
  def ~~?(f: (C, D, E, F) => Boolean) = new Rule2[A, B](append(exec(stack4(Pop), f)))
  def ~~?(f: (B, C, D, E, F) => Boolean) = new Rule1[A](append(exec(stack5(Pop), f)))
  def ~~?(f: (A, B, C, D, E, F) => Boolean) = new Rule0(append(exec(stack6(Pop), f)))
  def ~~?[Z](f: (Z, A, B, C, D, E, F) => Boolean) = new PopRule1[Z](append(exec(stack7(Pop), f)))
  def ~~%(f: F => Unit) = new Rule5[A, B, C, D, E](append(ok(exec(stack1(Pop), f))))
  def ~~%(f: (E, F) => Unit) = new Rule4[A, B, C, D](append(ok(exec(stack2(Pop), f))))
  def ~~%(f: (D, E, F) => Unit) = new Rule3[A, B, C](append(ok(exec(stack3(Pop), f))))
  def ~~%(f: (C, D, E, F) => Unit) = new Rule2[A, B](append(ok(exec(stack4(Pop), f))))
  def ~~%(f: (B, C, D, E, F) => Unit) = new Rule1[A](append(ok(exec(stack5(Pop), f))))
  def ~~%(f: (A, B, C, D, E, F) => Unit) = new Rule0(append(ok(exec(stack6(Pop), f))))
  def ~~%[Z](f: (Z, A, B, C, D, E, F) => Unit) = new PopRule1[Z](append(ok(exec(stack7(Pop), f))))
  def ~~~>[R](f: F => R) = new Rule7[A, B, C, D, E, F, R](append(push(exec(stack1(Peek), f))))
  def ~~~>[R](f: (E, F) => R) = new Rule7[A, B, C, D, E, F, R](append(push(exec(stack2(Peek), f))))
  def ~~~>[R](f: (D, E, F) => R) = new Rule7[A, B, C, D, E, F, R](append(push(exec(stack3(Peek), f))))
  def ~~~>[R](f: (C, D, E, F) => R) = new Rule7[A, B, C, D, E, F, R](append(push(exec(stack4(Peek), f))))
  def ~~~>[R](f: (B, C, D, E, F) => R) = new Rule7[A, B, C, D, E, F, R](append(push(exec(stack5(Peek), f))))
  def ~~~>[R](f: (A, B, C, D, E, F) => R) = new Rule7[A, B, C, D, E, F, R](append(push(exec(stack6(Peek), f))))
  def ~~~?(f: F => Boolean) = withMatcher(append(exec(stack1(Peek), f)))
  def ~~~?(f: (E, F) => Boolean) = withMatcher(append(exec(stack2(Peek), f)))
  def ~~~?(f: (D, E, F) => Boolean) = withMatcher(append(exec(stack3(Peek), f)))
  def ~~~?(f: (C, D, E, F) => Boolean) = withMatcher(append(exec(stack4(Peek), f)))
  def ~~~?(f: (B, C, D, E, F) => Boolean) = withMatcher(append(exec(stack5(Peek), f)))
  def ~~~?(f: (A, B, C, D, E, F) => Boolean) = withMatcher(append(exec(stack6(Peek), f)))
  def ~~~%(f: F => Unit) = withMatcher(append(ok(exec(stack1(Peek), f))))
  def ~~~%(f: (E, F) => Unit) = withMatcher(append(ok(exec(stack2(Peek), f))))
  def ~~~%(f: (D, E, F) => Unit) = withMatcher(append(ok(exec(stack3(Peek), f))))
  def ~~~%(f: (C, D, E, F) => Unit) = withMatcher(append(ok(exec(stack4(Peek), f))))
  def ~~~%(f: (B, C, D, E, F) => Unit) = withMatcher(append(ok(exec(stack5(Peek), f))))
  def ~~~%(f: (A, B, C, D, E, F) => Unit) = withMatcher(append(ok(exec(stack6(Peek), f))))
  def |[AA >: A, BB >: B, CC >: C, DD >: D, EE >: E, FF >: F](other: Rule6[AA, BB, CC, DD, EE, FF]) = new Rule6[AA, BB, CC, DD, EE, FF](appendChoice(other))
  protected def withMatcher(matcher: Matcher) = new Rule6[A, B, C, D, E, F](matcher).asInstanceOf[this.type]
}

/**
 * A rule pushing 7 new values of given types onto the parsers value stack.
 */
class Rule7[+A, +B, +C, +D, +E, +F, +G](val matcher: Matcher) extends PushRule {
  def ~[EE >: E, FF >: F, GG >: G](other: PopRule3[EE, FF, GG]) = new Rule4[A, B, C, F](append(other))
  def ~[FF >: F, GG >: G](other: PopRule2[FF, GG]) = new Rule5[A, B, C, D, F](append(other))
  def ~[GG >: G](other: PopRule1[GG]) = new Rule6[A, B, C, D, E, F](append(other))
  def ~[EE >: E, FF >: F, GG >: G, RE](other: ReductionRule3[EE, FF, GG, RE]) = new Rule5[A, B, C, D, RE](append(other))
  def ~[EE >: E, FF >: F, GG >: G, RE, RF](other: ReductionRule3_2[EE, FF, GG, RE, RF]) = new Rule6[A, B, C, D, RE, RF](append(other))
  def ~[EE >: E, FF >: F, GG >: G, RE, RF, RG](other: ReductionRule3_3[EE, FF, GG, RE, RF, RG]) = new Rule7[A, B, C, D, RE, RF, RG](append(other))
  def ~[FF >: F, GG >: G, RF](other: ReductionRule2[FF, GG, RF]) = new Rule6[A, B, C, D, E, RF](append(other))
  def ~[FF >: F, GG >: G, RF, RG](other: ReductionRule2_2[FF, GG, RF, RG]) = new Rule7[A, B, C, D, E, RF, RG](append(other))
  def ~[GG >: G, RG](other: ReductionRule1[GG, RG]) = new Rule7[A, B, C, D, E, F, RG](append(other))
  def ~~>[R](f: G => R) = new Rule7[A, B, C, D, E, F, R](append(push(exec(stack1(Pop), f))))
  def ~~>[R](f: (F, G) => R) = new Rule6[A, B, C, D, E, R](append(push(exec(stack2(Pop), f))))
  def ~~>[R](f: (E, F, G) => R) = new Rule5[A, B, C, D, R](append(push(exec(stack3(Pop), f))))
  def ~~>[R](f: (D, E, F, G) => R) = new Rule4[A, B, C, R](append(push(exec(stack4(Pop), f))))
  def ~~>[R](f: (C, D, E, F, G) => R) = new Rule3[A, B, R](append(push(exec(stack5(Pop), f))))
  def ~~>[R](f: (B, C, D, E, F, G) => R) = new Rule2[A, R](append(push(exec(stack6(Pop), f))))
  def ~~>[R](f: (A, B, C, D, E, F, G) => R) = new Rule1[R](append(push(exec(stack7(Pop), f))))
  def ~~?(f: G => Boolean) = new Rule6[A, B, C, D, E, F](append(exec(stack1(Pop), f)))
  def ~~?(f: (F, G) => Boolean) = new Rule5[A, B, C, D, E](append(exec(stack2(Pop), f)))
  def ~~?(f: (E, F, G) => Boolean) = new Rule4[A, B, C, D](append(exec(stack3(Pop), f)))
  def ~~?(f: (D, E, F, G) => Boolean) = new Rule3[A, B, C](append(exec(stack4(Pop), f)))
  def ~~?(f: (C, D, E, F, G) => Boolean) = new Rule2[A, B](append(exec(stack5(Pop), f)))
  def ~~?(f: (B, C, D, E, F, G) => Boolean) = new Rule1[A](append(exec(stack6(Pop), f)))
  def ~~?(f: (A, B, C, D, E, F, G) => Boolean) = new Rule0(append(exec(stack7(Pop), f)))
  def ~~%(f: G => Unit) = new Rule6[A, B, C, D, E, F](append(ok(exec(stack1(Pop), f))))
  def ~~%(f: (F, G) => Unit) = new Rule5[A, B, C, D, E](append(ok(exec(stack2(Pop), f))))
  def ~~%(f: (E, F, G) => Unit) = new Rule4[A, B, C, D](append(ok(exec(stack3(Pop), f))))
  def ~~%(f: (D, E, F, G) => Unit) = new Rule3[A, B, C](append(ok(exec(stack4(Pop), f))))
  def ~~%(f: (C, D, E, F, G) => Unit) = new Rule2[A, B](append(ok(exec(stack5(Pop), f))))
  def ~~%(f: (B, C, D, E, F, G) => Unit) = new Rule1[A](append(ok(exec(stack6(Pop), f))))
  def ~~%(f: (A, B, C, D, E, F, G) => Unit) = new Rule0(append(ok(exec(stack7(Pop), f))))
  def ~~~?(f: G => Boolean) = withMatcher(append(exec(stack1(Peek), f)))
  def ~~~?(f: (F, G) => Boolean) = withMatcher(append(exec(stack2(Peek), f)))
  def ~~~?(f: (E, F, G) => Boolean) = withMatcher(append(exec(stack3(Peek), f)))
  def ~~~?(f: (D, E, F, G) => Boolean) = withMatcher(append(exec(stack4(Peek), f)))
  def ~~~?(f: (C, D, E, F, G) => Boolean) = withMatcher(append(exec(stack5(Peek), f)))
  def ~~~?(f: (B, C, D, E, F, G) => Boolean) = withMatcher(append(exec(stack6(Peek), f)))
  def ~~~?(f: (A, B, C, D, E, F, G) => Boolean) = withMatcher(append(exec(stack7(Peek), f)))
  def ~~~%(f: G => Unit) = withMatcher(append(ok(exec(stack1(Peek), f))))
  def ~~~%(f: (F, G) => Unit) = withMatcher(append(ok(exec(stack2(Peek), f))))
  def ~~~%(f: (E, F, G) => Unit) = withMatcher(append(ok(exec(stack3(Peek), f))))
  def ~~~%(f: (D, E, F, G) => Unit) = withMatcher(append(ok(exec(stack4(Peek), f))))
  def ~~~%(f: (C, D, E, F, G) => Unit) = withMatcher(append(ok(exec(stack5(Peek), f))))
  def ~~~%(f: (B, C, D, E, F, G) => Unit) = withMatcher(append(ok(exec(stack6(Peek), f))))
  def ~~~%(f: (A, B, C, D, E, F, G) => Unit) = withMatcher(append(ok(exec(stack7(Peek), f))))
  def |[AA >: A, BB >: B, CC >: C, DD >: D, EE >: E, FF >: F, GG >: G](other: Rule7[AA, BB, CC, DD, EE, FF, GG]) = new Rule7[AA, BB, CC, DD, EE, FF, GG](appendChoice(other))
  protected def withMatcher(matcher: Matcher) = new Rule7[A, B, C, D, E, F, G](matcher).asInstanceOf[this.type]
}