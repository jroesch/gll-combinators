package edu.uwm.cs.gll

trait ImplicitConversions extends Parsers {
  implicit def disjunctiveSyntax[A](left: =>Parser[A]) = new RichParser(left)
  implicit def disjunctiveLiterals(left: String) = new RichParser(literal(left))
  
  implicit def funSyntax1[A](p: Parser[A]) = new RichSyntax1(p)
  implicit def funLitSyntax(p: String) = new RichSyntax1(literal(p))
  implicit def funSyntax2[A, B](p: Parser[A ~ B]) = new RichSyntax2(p)
  
  implicit def funSyntax3l[A, B, C](p: Parser[A ~ B ~ C]) = new RichSyntax3l(p)
  implicit def funSyntax3r[A, B, C](p: Parser[~[A, B ~ C]]) = new RichSyntax3r(p)
  
  implicit def funSyntax4ll[A, B, C, D](p: Parser[A ~ B ~ C ~ D]) = new RichSyntax4ll(p)
  implicit def funSyntax4lr[A, B, C, D](p: Parser[~[A, B ~ C] ~ D]) = new RichSyntax4lr(p)
  implicit def funSyntax4rl[A, B, C, D](p: Parser[~[A, B ~ C ~ D]]) = new RichSyntax4rl(p)
  implicit def funSyntax4rr[A, B, C, D](p: Parser[~[A, ~[B, C ~ D]]]) = new RichSyntax4rr(p)
  
  implicit def funSyntax5lll[A, B, C, D, E](p: Parser[A ~ B ~ C ~ D ~ E]) = new RichSyntax5lll(p)
  implicit def funSyntax5llr[A, B, C, D, E](p: Parser[~[A, B ~ C] ~ D ~ E]) = new RichSyntax5llr(p)
  implicit def funSyntax5lrl[A, B, C, D, E](p: Parser[~[A, B ~ C ~ D] ~ E]) = new RichSyntax5lrl(p)
  implicit def funSyntax5lrr[A, B, C, D, E](p: Parser[~[A, ~[B, C ~ D]] ~ E]) = new RichSyntax5lrr(p)
  implicit def funSyntax5rll[A, B, C, D, E](p: Parser[~[A ~ B, C ~ D ~ E]]) = new RichSyntax5rll(p)
  implicit def funSyntax5rlr[A, B, C, D, E](p: Parser[~[A ~ B, ~[C, D ~ E]]]) = new RichSyntax5rlr(p)
  implicit def funSyntax5rrl[A, B, C, D, E](p: Parser[~[A, ~[B, C ~ D ~ E]]]) = new RichSyntax5rrl(p)
  implicit def funSyntax5rrr[A, B, C, D, E](p: Parser[~[A, ~[B, ~[C, D ~ E]]]]) = new RichSyntax5rrr(p)
  
  implicit def funSyntax6[A, B, C, D, E, F](p: Parser[A ~ B ~ C ~ D ~ E ~ F]) = new RichSyntax6(p)
  implicit def funSyntax7[A, B, C, D, E, F, G](p: Parser[A ~ B ~ C ~ D ~ E ~ F ~ G]) = new RichSyntax7(p)

  class RichParser[A](left: =>Parser[A]) {
    def |[B >: A](right: =>Parser[B]): Parser[B] = new DisjunctiveParser(left, right)
  }
  
  // map syntax
  
  class RichSyntax1[A](p: Parser[A]) {
    def ^^[R](f: A=>R) = p map f
  }
  
  class RichSyntax2[A, B](p: Parser[A ~ B]) {
    def ^^[R](fun: (A, B)=>R) = p map { case a ~ b => fun(a, b) }
  }
  
  class RichSyntax3l[A, B, C](p: Parser[A ~ B ~ C]) {
    def ^^[R](fun: (A, B, C)=>R) = p map { case a ~ b ~ c => fun(a, b, c) }
  }
  
  class RichSyntax3r[A, B, C](p: Parser[~[A, B ~ C]]) {
    def ^^[R](fun: (A, B, C)=>R) = p map { case a ~ (b ~ c) => fun(a, b, c) }
  }
  
  class RichSyntax4ll[A, B, C, D](p: Parser[A ~ B ~ C ~ D]) {
    def ^^[R](fun: (A, B, C, D)=>R) = p map { case a ~ b ~ c ~ d => fun(a, b, c, d) }
  }
  
  class RichSyntax4lr[A, B, C, D](p: Parser[~[A, B ~ C] ~ D]) {
    def ^^[R](fun: (A, B, C, D)=>R) = p map { case a ~ (b ~ c) ~ d => fun(a, b, c, d) }
  }
  
  class RichSyntax4rl[A, B, C, D](p: Parser[~[A, B ~ C ~ D]]) {
    def ^^[R](fun: (A, B, C, D)=>R) = p map { case a ~ ((b ~ c) ~ d) => fun(a, b, c, d) }
  }
  
  class RichSyntax4rr[A, B, C, D](p: Parser[~[A, ~[B, C ~ D]]]) {
    def ^^[R](fun: (A, B, C, D)=>R) = p map { case a ~ (b ~ (c ~ d)) => fun(a, b, c, d) }
  }
  
  class RichSyntax5lll[A, B, C, D, E](p: Parser[A ~ B ~ C ~ D ~ E]) {
    def ^^[R](fun: (A, B, C, D, E)=>R) = p map { case a ~ b ~ c ~ d ~ e => fun(a, b, c, d, e) }
  }
  
  class RichSyntax5llr[A, B, C, D, E](p: Parser[~[A, B ~ C] ~ D ~ E]) {
    def ^^[R](fun: (A, B, C, D, E)=>R) = p map { case (a ~ (b ~ c)) ~ d ~ e => fun(a, b, c, d, e) }
  }
  
  class RichSyntax5lrl[A, B, C, D, E](p: Parser[~[A, B ~ C ~ D] ~ E]) {
    def ^^[R](fun: (A, B, C, D, E)=>R) = p map { case (a ~ (b ~ c ~ d)) ~ e => fun(a, b, c, d, e) }
  }
  
  class RichSyntax5lrr[A, B, C, D, E](p: Parser[~[A, ~[B, C ~ D]] ~ E]) {
    def ^^[R](fun: (A, B, C, D, E)=>R) = p map { case (a ~ (b ~ (c ~ d))) ~ e => fun(a, b, c, d, e) }
  }
  
  class RichSyntax5rll[A, B, C, D, E](p: Parser[~[A ~ B, C ~ D ~ E]]) {
    def ^^[R](fun: (A, B, C, D, E)=>R) = p map { case (a ~ b) ~ (c ~ d ~ e) => fun(a, b, c, d, e) }
  }
  
  class RichSyntax5rlr[A, B, C, D, E](p: Parser[~[A ~ B, ~[C, D ~ E]]]) {
    def ^^[R](fun: (A, B, C, D, E)=>R) = p map { case (a ~ b) ~ (c ~ (d ~ e)) => fun(a, b, c, d, e) }
  }
  
  class RichSyntax5rrl[A, B, C, D, E](p: Parser[~[A, ~[B, C ~ D ~ E]]]) {
    def ^^[R](fun: (A, B, C, D, E)=>R) = p map { case a ~ (b ~ (c ~ d ~ e)) => fun(a, b, c, d, e) }
  }
  
  class RichSyntax5rrr[A, B, C, D, E](p: Parser[~[A, ~[B, ~[C, D ~ E]]]]) {
    def ^^[R](fun: (A, B, C, D, E)=>R) = p map { case a ~ (b ~ (c ~ (d ~ e))) => fun(a, b, c, d, e) }
  }
  
  class RichSyntax6[A, B, C, D, E, F](p: Parser[A ~ B ~ C ~ D ~ E ~ F]) {
    def ^^[R](fun: (A, B, C, D, E, F)=>R) = p map { case a ~ b ~ c ~ d ~ e ~ f => fun(a, b, c, d, e, f) }
  }
  
  class RichSyntax7[A, B, C, D, E, F, G](p: Parser[A ~ B ~ C ~ D ~ E ~ F ~ G]) {
    def ^^[R](fun: (A, B, C, D, E, F, G)=>R) = p map { case a ~ b ~ c ~ d ~ e ~ f ~ g => fun(a, b, c, d, e, f, g) }
  }
}

object ImplicitConversions extends ImplicitConversions
