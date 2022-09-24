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
object TransactionRequest extends GenericRequest {
  private val transactionUrl = conf.getString("transactionUrl")
  private val userIdsFeeder: BatchableFeederBuilder[String] = csv(
    "./src/test/resources/feeders/userIds.csv").circular

  var createTransaction: ChainBuilder = feed(userIdsFeeder)
    .exec(
      http(transactionUrl)
        .post(baseUrl + transactionUrl)
        .body(StringBody("{\"userId\": \"${userId}\", \"amount\": 100.00}")).asJson
        .headers(
          Map(
            "Accept" -> "*/*",
            "Accept-Encoding" -> "gzip, deflate, br",
            "Connection" -> "keep-alive",
            "Cache-Control" -> "no-cache",
            "Content-Type" -> "application/json")
        )
        .check(status.in(200))
    )
}
