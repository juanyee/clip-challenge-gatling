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
object DisbursementRequest extends GenericRequest {
  private val disbursementUrl = conf.getString("disbursementUrl")
  private val transactionIdsFeeder: BatchableFeederBuilder[String] = csv(
    "./src/test/resources/feeders/transactionIds.csv").circular

  var getDisbursement: HttpRequestBuilder = http(disbursementUrl)
        .get(baseUrl + disbursementUrl)
        .headers(
          Map(
            "Accept" -> "*/*",
            "Accept-Encoding" -> "gzip, deflate, br",
            "Connection" -> "keep-alive",
            "Cache-Control" -> "no-cache",
            "Content-Type" -> "application/json")
        )
        .check(status.in(200))

  var getDisbursementById: ChainBuilder = feed(transactionIdsFeeder)
  .exec(
    http(disbursementUrl + "/{id}")
      .get(baseUrl + disbursementUrl + "${id}")
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
