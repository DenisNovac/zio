import java.net.{ServerSocket, Socket}

import zio.{Task, UIO, ZIO}
import zio.blocking._

import scala.io.{Codec, Source}

// Выполнить блокер в отдельном пуле потоков (специальный пул для блокирующих эффектов)
val spleeping: ZIO[Blocking, Throwable, Unit] = effectBlocking(Thread.sleep(Long.MaxValue))
// Эффект можно прервать, вызвав Thread.interrupt в потоке
effectBlockingInterrupt(spleeping)

// Некоторые эффекты должны быть отменяемы. Их можно конвертировать так:
def accept(l: ServerSocket): ZIO[Blocking, Throwable, Socket] =
  effectBlockingCancelable(l.accept())(UIO.effectTotal(l.close()))

// Вытащить в блокирующий пул можно и уже готовый эффект:

def download(url: String): Task[String] =
  Task.effect {
    Source.fromURL(url)(Codec.UTF8).mkString
  }

def safeDownload(url: String): ZIO[Blocking, Throwable, String] =
  blocking(download(url))