import zio.{IO, Task, UIO, ZIO}

// succeed для значений без побочных эффектов
val s1: UIO[Int] = ZIO.succeed(42)
val s2: UIO[Int] = Task.succeed(42)

// effectTotal для значений с эффектами
val now = ZIO.effectTotal(System.currentTimeMillis())

// тип ошибки в ZIO может быть любым
val f1: IO[String, Nothing] = ZIO.fail("Uh oh")
// шорткат для использования ошибки типа Throwable
val f2: Task[Nothing] = Task.fail(new Exception("Uh oh!"))

// UIO.fail такого метода нет т.к. UIO не может фейлить



