package request

import io.gatling.core.Predef._
import io.gatling.core.feeder.BatchableFeederBuilder
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

/**
 *
 * @author juan.yee
 */
object ReportRequest extends GenericRequest {
  private val reportUrl = conf.getString("reportUrl")
  private val userIdsFeeder: BatchableFeederBuilder[String] = csv(
    "./src/test/resources/feeders/userIds.csv").circular

  var getReport: HttpRequestBuilder = http(reportUrl)
    .get(baseUrl + reportUrl)
    .headers(
      Map(
        "Accept" -> "*/*",
        "Accept-Encoding" -> "gzip, deflate, br",
        "Connection" -> "keep-alive",
        "Cache-Control" -> "no-cache",
        "Content-Type" -> "application/json")
    )
    .check(status.in(200))

  var getReportByUserId: ChainBuilder = feed(userIdsFeeder)
    .exec(
      http(reportUrl + "/{userId}")
        .get(baseUrl + reportUrl + "${userId}")
        .headers(
          Map(
            "Accept" -> "*/*",
            "Accept-Encoding" -> "gzip, deflate, br",
            "Connection" -> "keep-alive",
            "Cache-Control" -> "no-cache",
            "Content-Type" -> "application/json")
        )
        .check(status.in(200, 404))
    )
}
