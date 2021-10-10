package cs.extension

extension[T] (t: T )
  def |>[U] (f: T => U) = f(t)