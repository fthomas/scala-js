import sbt._

object ScalaJSEnvGenerator {

  /** Generate a *.scala file that contains the scalajsenv as literal string
   *
   *  We need this so the tools don't rely on I/O and/or resources.
   */
  def generateEnvHolder(baseDir: File, sourceDir: File): Seq[File] = {
    val trg = sourceDir / "ScalaJSEnvHolder.scala"
    val env = baseDir / "scalajsenv.js"
    val strongmodeenvFile = baseDir / "strongmodeenv.js"

    if (!trg.exists() || trg.lastModified() < env.lastModified() ||
        trg.lastModified() < strongmodeenvFile.lastModified()) {
      val scalajsenv = IO.read(env).replaceAllLiterally("$", "$$")
      val strongmodeenv = IO.read(strongmodeenvFile).replaceAllLiterally("$", "$$")

      val scalaCode =
        s"""
        package org.scalajs.core.tools.corelib

        private[corelib] object ScalaJSEnvHolder {
          final val scalajsenv = raw\"\"\"$scalajsenv\"\"\"

          final val strongmodeenv = raw\"\"\"$strongmodeenv\"\"\"
        }
        """

      IO.write(trg, scalaCode)
    }

    Seq(trg)
  }

}
