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
package org.azkfw.business.logic;

import org.azkfw.business.dao.DataAccessObject;
import org.azkfw.business.dao.DynamicSQLAccessObject;
import org.azkfw.dsql.DynamicSQL;
import org.azkfw.dsql.Group;
import org.azkfw.dsql.Parameter;
import org.azkfw.persistence.database.DatabaseConnectionSupport;

/**
 * このクラスは、ダイナミックSQL機能を実装したロジッククラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/15
 * @author Kawakicchi
 */
public abstract class AbstractDynamicSQLLogic extends AbstractDatabaseLogic {

	/**
	 * コンストラクタ
	 */
	public AbstractDynamicSQLLogic() {
		super();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractDynamicSQLLogic(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractDynamicSQLLogic(final Class<?> aClass) {
		super(aClass);
	}

	/**
	 * データアクセスオブジェクトを取得します。
	 * 
	 * @param aName ダイナミックSQL名
	 * @return データアクセスオブジェクト
	 */
	protected final DataAccessObject getDao(final String aName) {
		return getDao(aName, null, null);
	}

	/**
	 * データアクセスオブジェクトを取得します。
	 * 
	 * @param aName ダイナミックSQL名
	 * @param aGroup グループ
	 * @return データアクセスオブジェクト
	 */
	protected final DataAccessObject getDao(final String aName, final Group aGroup) {
		return getDao(aName, aGroup, null);
	}

	/**
	 * データアクセスオブジェクトを取得します。
	 * 
	 * @param aName ダイナミックSQL名
	 * @param aParameter パラメータ
	 * @return データアクセスオブジェクト
	 */
	protected final DataAccessObject getDao(final String aName, final Parameter aParameter) {
		return getDao(aName, null, aParameter);
	}

	/**
	 * データアクセスオブジェクトを取得します。
	 * 
	 * @param aName ダイナミックSQL名
	 * @param aGroup グループ
	 * @param aParameter パラメータ
	 * @return データアクセスオブジェクト
	 */
	protected final DataAccessObject getDao(final String aName, final Group aGroup, final Parameter aParameter) {
		DataAccessObject dao = null;
		DynamicSQL dsql = DynamicSQL.generate(aName, aGroup, aParameter);
		if (null != dsql) {
			dao = new DynamicSQLAccessObject(dsql);
			if (dao instanceof DatabaseConnectionSupport) {
				((DatabaseConnectionSupport) dao).setConnection(getConnection());
			}
		}
		return dao;
	}
}
