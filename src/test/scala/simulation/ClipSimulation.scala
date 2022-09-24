package simulation

import io.gatling.core.Predef._
import _root_.scenario.ClipScenario

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

/**
 *
 * @author juan.yee
 */
class ClipSimulation extends Simulation {
  setUp(
    ClipScenario.clipScenario
      .inject(
        constantConcurrentUsers(5) during (20 seconds)
      )
  ).assertions(global.successfulRequests.percent.gt(95)
  )
}
