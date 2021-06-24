import zio.{IO, Task, URIO, ZIO}

import scala.concurrent.Future
import scala.util.Try

val zoption: IO[Unit, Int] = ZIO.fromOption(Some(2))
// тип ошибки у эффекта Unit потому что None в Option не даёт информации о том, почему значения нет
// Unit можно привести в другой тип через mapError
val zoption2: ZIO[Any, String, Int] = zoption.mapError(_ => "It wasn't here :(")

val zeither: IO[Nothing, String] = ZIO.fromEither(Right("Yisss!"))
val zeither2: IO[String, Nothing] = ZIO.fromEither(Left("Error message"))
val zeither3: IO[Throwable, String] = ZIO.fromEither[Throwable, String](Right("right"))

// Try может фейлить только с Throwable, поэтому всегда Task
val ztry: Task[Int] = ZIO.fromTry(Try(42/0))


// Можно получить ZIO из функции
// Тип окружения этого эффекта - A, в данном случае Int, потому что
// int требуется этой функции на вход
val zfun: ZIO[Int, Nothing, Int] = ZIO.fromFunction((i: Int) => i * i)

lazy val future = Future.successful("Hello")
// fromeFuture - сигнатура типа ExecutionContext => Future[A]
val zfuture: Task[String] = ZIO.fromFuture { implicit ec =>
  future.map(_ => "Goodbye")
}
// Future может падать только с Throwable, поэтому Task


