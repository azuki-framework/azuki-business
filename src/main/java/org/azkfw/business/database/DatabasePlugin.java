/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.azkfw.business.database;

import java.io.IOException;
import java.sql.SQLException;

import org.azkfw.database.DatabaseManager;
import org.azkfw.plugin.AbstractPlugin;
import org.azkfw.plugin.PluginServiceException;
import org.xml.sax.SAXException;

/**
 * このクラスは、データベース機能をサポートするためのプラグインクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/15
 * @author Kawakicchi
 */
public final class DatabasePlugin extends AbstractPlugin {

	/**
	 * コンストラクタ
	 */
	public DatabasePlugin() {

	}

	@Override
	protected void doInitialize() throws PluginServiceException {
		DatabaseManager.getInstance().initialize();
	}

	@Override
	protected void doDestroy() throws PluginServiceException {
		DatabaseManager.getInstance().destroy();
	}

	@Override
	protected void doLoad() throws PluginServiceException, IOException {
		try {
			DatabaseManager.getInstance().load(getConfiguration().getResourceAsStream());
		} catch (SAXException ex) {
			throw new PluginServiceException(ex);
		} catch (IOException ex) {
			throw new PluginServiceException(ex);
		}
	}

	@Override
	protected void doSupport(final Object obj) throws PluginServiceException {
		try {
			if (obj instanceof DatabaseConnectionSupport) {
				DatabaseConnectionSupport support = (DatabaseConnectionSupport) obj;
				if (null == support.getConnection()) {
					DatabaseConnection connection = new DatabaseConnection(DatabaseManager.getInstance().getConnection());
					support.setConnection(connection);
				}
			}
		} catch (SQLException ex) {
			throw new PluginServiceException(ex);
		}
	}
}
