import cats.effect.{IO, IOApp}
import fs2.{Stream, text}
import fs2.io.file.{Files, Path}

object FS2Main extends IOApp.Simple {
  val converter: Stream[IO, Unit] = {
    def fahrenheitToCelsius(f: Double): Double =
      (f - 32.0) * (5.0/9.0)

    Files[IO].readAll(Path("D:\\coding\\code\\my\\kt-test\\build.gradle.kts"))
      .through(text.utf8.decode)
      .through(text.lines)
      .filter(s => !s.trim.isEmpty && !s.startsWith("//"))
      .map(line => fahrenheitToCelsius(line.toDouble).toString)
      .intersperse("\n")
      .through(text.utf8.encode)
      .through(Files[IO].writeAll(Path("D:\\coding\\code\\my\\kt-test\\build.gradle.kts2")))
  }

  val toUpper: Stream[IO, Unit] = {


    Files[IO].readAll(Path("C:\\data\\DNALab\\uu.txt"))
      .through(text.utf8.decode)
      .through(text.lines)
      .map(line => line.toUpperCase())
      .intersperse("\n")
      .through(text.utf8.encode)
      .through(Files[IO].writeAll(Path("C:\\data\\DNALab\\user.txt")))
  }
  def run: IO[Unit] = toUpper.compile.drain >> IO{println("DONE")}

}