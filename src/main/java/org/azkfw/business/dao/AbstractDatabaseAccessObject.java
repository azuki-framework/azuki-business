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
	 * @param aName 名前
	 */
	public AbstractDatabaseAccessObject(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractDatabaseAccessObject(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	public final void setConnection(final DatabaseConnection aConnection) {
		connection = aConnection;
	}

	/**
	 * コネクションを取得する。
	 * 
	 * @return コネクション
	 */
	protected final DatabaseConnection getConnection() {
		return connection;
	}

}
