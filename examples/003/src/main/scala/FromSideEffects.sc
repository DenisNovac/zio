import java.io.IOException

import zio.{IO, Task, UIO, ZIO}

import scala.io.StdIn

// Синхронные эффекты ковертируются в ZIO с использованием ZIO.effect
// Эффекты всегда бросают Throwable
val getStrLn: Task[String] = ZIO.effect(StdIn.readLine())


// Если известно, что эффект не бросает исключения, можно обернуть в effectTotal:
def putStrLn(line: String): UIO[Unit] = ZIO.effectTotal(println(line))

// Если неизвестно, бросает ли исключения эффект - всегда лучше использовать ZIO.effect.

// Можно уточнить тип ошибки эффекта (остальные ошибки будут фатальны):
val getStrLn2 = ZIO.effect(StdIn.readLine()).refineToOrDie[IOException]

// асинхронные эффекты на коллбеках можно конвертировать в ZIO через effectAsync

object legacy {
  def login(onSuccess: String => Unit, onFailure: Exception => Unit): Unit = ???
}

val login = IO.effectAsync[Exception, String] { callback =>

  legacy.login(
    user => callback(IO.succeed(user)),
    err => callback(IO.fail(err))
  )

}









