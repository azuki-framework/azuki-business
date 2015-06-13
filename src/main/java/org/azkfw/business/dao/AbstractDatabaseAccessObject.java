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
package org.azkfw.business.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.azkfw.persistence.database.DatabaseConnection;
import org.azkfw.persistence.database.DatabaseConnectionSupport;

/**
 * このクラスは、データベース機能を備えたデータアクセスオブジェクトクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/14
 * @author Kawakicchi
 */
public abstract class AbstractDatabaseAccessObject extends AbstractDataAccessObject implements DatabaseConnectionSupport {

	/**
	 * コネクション
	 */
	private DatabaseConnection connection;

	/**
	 * コンストラクタ
	 */
	public AbstractDatabaseAccessObject() {
		super();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param name 名前
	 */
	public AbstractDatabaseAccessObject(final String name) {
		super(name);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param clazz クラス
	 */
	public AbstractDatabaseAccessObject(final Class<?> clazz) {
		super(clazz);
	}

	@Override
	public final void setConnection(final DatabaseConnection connection) {
		this.connection = connection;
	}

	/**
	 * コネクションを取得する。
	 * 
	 * @return コネクション
	 */
	protected final DatabaseConnection getConnection() {
		return connection;
	}

	/**
	 * {@link ResultSet}を解放する。
	 * 
	 * @param resultSet {@link ResultSet}
	 */
	protected final void release(final ResultSet resultSet) {
		if (null != resultSet) {
			try {
				if (!resultSet.isClosed()) {
					resultSet.close();
				}
			} catch (SQLException ex) {
				warn("ResultSet release error.", ex);
			}
		}
	}

	/**
	 * {@link PreparedStatement}を解放する。
	 * 
	 * @param preparedStatement {@link PreparedStatement}
	 */
	protected final void release(final PreparedStatement preparedStatement) {
		if (null != preparedStatement) {
			try {
				if (!preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException ex) {
				warn("PreparedStatement release error.", ex);
			}
		}
	}
}
