import zio.console._
import zio._
import java.io.IOException

object GettingStarted extends zio.App {

  // All exceptions are handled so URIO can't fail, so it's type is ZIO[R, Nothing, A]
  override def run(args: List[String]): URIO[ZEnv, ExitCode] = myAppLogic.exitCode

  val myAppLogic: ZIO[Console, IOException, Unit] =
    for {
      _    <- putStr("hello: ")
      name <- getStrLn
      _    <- putStrLn(s"Hello, $name")
      //_    <- ZIO.fail(new IOException("heh"))
    } yield ()
}
