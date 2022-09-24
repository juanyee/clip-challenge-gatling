package request

import com.typesafe.config.{Config, ConfigFactory}

/**
 *
 * @author juan.yee
 */
trait GenericRequest {
  val conf: Config = ConfigFactory.load()
  val baseUrl: String = conf.getString("baseUrl")
}
