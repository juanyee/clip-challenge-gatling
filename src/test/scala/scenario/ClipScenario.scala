package scenario

import io.gatling.core.Predef.scenario
import io.gatling.core.structure.ScenarioBuilder
import request._

/**
 *
 * @author juan.yee
 */
object ClipScenario {
  var clipScenario: ScenarioBuilder = scenario("Clip Scenario")
    .exec(DisbursementRequest.getDisbursement)
    .exec(DisbursementRequest.getDisbursementById)
    .exec(ReportRequest.getReport)
    .exec(ReportRequest.getReportByUserId)
    .exec(TransactionRequest.createTransaction)
    .exec(UserRequest.getUsers)
}
