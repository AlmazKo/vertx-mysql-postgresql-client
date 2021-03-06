/*
 * Copyright 2014 Red Hat, Inc.
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  and Apache License v2.0 which accompanies this distribution.
 *
 *  The Eclipse Public License is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  The Apache License v2.0 is available at
 *  http://www.opensource.org/licenses/apache2.0.php
 *
 *  You may elect to redistribute this code under either of these licenses.
 */

package io.vertx.ext.asyncsql;

import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.impl.ClientHelper;

import java.util.UUID;

/**
 *
 * Represents an asynchronous MySQL client
 *
 * @author <a href="http://www.campudus.com">Joern Bernhardt</a>.
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
@VertxGen
public interface MySQLClient extends AsyncSQLClient {

  public static final String DEFAULT_POOL_NAME = "DEFAULT_MYSQL_POOL";

  /**
   * Create a MySQL client which maintains its own pool.
   *
   * @param vertx  the Vert.x instance
   * @param config  the configuration
   * @return the client
   */
  static AsyncSQLClient createNonShared(Vertx vertx, JsonObject config) {
    return ClientHelper.getOrCreate(vertx, config, UUID.randomUUID().toString(), true);
  }

  /**
   * Create a MySQL client which shares its data source with any other MySQL clients created with the same
   * data source name
   *
   * @param vertx  the Vert.x instance
   * @param config  the configuration
   * @param poolName  the pool name
   * @return the client
   */
  static AsyncSQLClient createShared(Vertx vertx, JsonObject config, String poolName) {
    return ClientHelper.getOrCreate(vertx, config, poolName, true);
  }

  /**
   * Like {@link #createShared(io.vertx.core.Vertx, JsonObject, String)} but with the default pool name
   * @param vertx  the Vert.x instance
   * @param config  the configuration
   * @return the client
   */
  static AsyncSQLClient createShared(Vertx vertx, JsonObject config) {
    return ClientHelper.getOrCreate(vertx, config, DEFAULT_POOL_NAME, true);
  }



}
