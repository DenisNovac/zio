package app

import java.io.IOException

import zio.{ExitCode, ZIO}
import zio.console._

object Main extends zio.App {

  override def run(args: List[String]): ZIO[zio.ZEnv, Nothing, ExitCode] =
    myAppLogic.exitCode

  val myAppLogic: ZIO[Console, IOException, Unit] =
    for {
      _    <- putStrLn("Hello")
      name <- getStrLn
      _    <- putStrLn(s"Hello, $name")
    } yield ()
}
