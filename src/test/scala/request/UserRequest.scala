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
object UserRequest extends GenericRequest {
  private val userUrl = conf.getString("userUrl")

  var getUsers: HttpRequestBuilder = http(userUrl)
    .get(baseUrl + userUrl)
    .headers(
      Map(
        "Accept" -> "*/*",
        "Accept-Encoding" -> "gzip, deflate, br",
        "Connection" -> "keep-alive",
        "Cache-Control" -> "no-cache",
        "Content-Type" -> "application/json")
    )
    .check(status.in(200))
}
