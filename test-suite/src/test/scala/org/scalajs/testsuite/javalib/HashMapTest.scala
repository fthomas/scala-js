/*                     __                                               *\
**     ________ ___   / /  ___      __ ____  Scala.js Test Suite        **
**    / __/ __// _ | / /  / _ | __ / // __/  (c) 2013-2015, LAMP/EPFL   **
**  __\ \/ /__/ __ |/ /__/ __ |/_// /_\ \    http://scala-js.org/       **
** /____/\___/_/ |_/____/_/ | |__/ /____/                               **
**                          |/____/                                     **
\*                                                                      */
package org.scalajs.testsuite.javalib

import java.{util => ju}

object HashMapTest extends HashMapTest(new HashMapFactory)

class HashMapTest[F <: HashMapFactory](mapFactory: F)
    extends AbstractMapTest[F](mapFactory)

object HashMapFactory {
  def allFactories: Iterator[MapFactory] =
    Iterator(new HashMapFactory) ++ LinkedHashMapFactory.allFactories
}

class HashMapFactory extends AbstractMapFactory {
  override def implementationName: String =
    "java.util.HashMap"

  override def empty[K, V]: ju.HashMap[K, V] =
    new ju.HashMap[K, V]

  def allowsNullKeys: Boolean = true
  def allowsNullValues: Boolean = true
}
