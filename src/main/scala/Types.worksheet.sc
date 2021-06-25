import scala.util.Try
import zio._

ZIO.fromOption(Some(2)) // ZIO[Any, Option[Nothing], Int]

ZIO.fromOption(Some(2)).mapError(_ => new Error("Option is empty")) // ZIO[Any, Error, Int]

ZIO.fromEither[Int, String](Left(-1)) // ZIO[Any, Int, String] => IO[Int, String]

ZIO.fromTry(Try(42 / 0)) // IO[Throwable, Int]

// ???
val noFail = ZIO.fromFunction((i: Int) => i / 0) // ZIO[Int, Nothing, Int]

val withFail = ZIO.fromFunctionM[Int, Throwable, Int]((i: Int) => ZIO.fromTry(Try(i / 0))) // ZIO[Int, Throwable, Int]


for {
  n <- noFail
  w <- withFail
} yield n + w // ZIO[Int, Throwable, Int]
