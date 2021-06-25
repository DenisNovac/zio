import java.io.IOException
import zio._


// Sync
ZIO.effect(println("Hello"))  // ZIO[Any, Throwable, Unit]

// if effect can't have the exceptions
ZIO.effectTotal(println("No exceptions?")) // ZIO[Any, Nothing, Unit]

// refine the type error
ZIO.effect(println("Hello exception")).refineToOrDie[IOException] // ZIO[Any, IOException, Unit]



