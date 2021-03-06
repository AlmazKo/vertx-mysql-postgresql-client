package io.vertx.ext.asyncsql.impl

import io.vertx.core.{AsyncResult, Handler, Vertx}
import io.vertx.core.json.JsonObject
import io.vertx.ext.asyncsql.AsyncSQLClient
import io.vertx.ext.sql.SQLConnection

/**
 * @author <a href="http://www.campudus.com">Joern Bernhardt</a>.
 */
class AsyncSQLClientImpl(vertx: Vertx, config: JsonObject, mysql: Boolean) extends AsyncSQLClient {

  val baseClient: BaseSQLClient = {
    if (mysql) {
      new MySQLClient(vertx, config)
    } else {
      new PostgreSQLClient(vertx, config)
    }
  }

  override def close(whenDone: Handler[AsyncResult[Void]]): Unit = baseClient.close(whenDone)

  override def close(): Unit = baseClient.close(null)

  override def getConnection(handler: Handler[AsyncResult[SQLConnection]]): Unit = {
    baseClient.getConnection(handler)
  }
}
