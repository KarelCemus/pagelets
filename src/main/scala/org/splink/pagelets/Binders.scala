package org.splink.pagelets

import play.api.mvc.PathBindable

object Binders {

  implicit object PathBindableSymbol extends PathBindable[Symbol] {
    def bind(key: String, value: String) = try {
      Right(Symbol(value))
    } catch {
      case _: Throwable =>
        Left(s"Can't create a Symbol from '$key'")
    }

    def unbind(key: String, value: Symbol): String = value.name
  }

}