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

import java.util.List;
import java.util.Map;

import org.azkfw.business.paging.Paging;
import org.azkfw.lang.LoggingObject;

/**
 * このクラスは、データアクセス機能の実装を行うための基底クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/14
 * @author Kawakicchi
 */
public abstract class AbstractDataAccessObject extends LoggingObject implements DataAccessObject {

	/**
	 * コンストラクタ
	 */
	public AbstractDataAccessObject() {
		super(DataAccessObject.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractDataAccessObject(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractDataAccessObject(final Class<?> aClass) {
		super(aClass);
	}

	@Override
	public final boolean execute() throws DataAccessServiceException {
		return doExecute();
	}

	@Override
	public final int update() throws DataAccessServiceException {
		return doUpdate();
	}

	@Override
	public final long count() throws DataAccessServiceException {
		return doCount();
	}

	@Override
	public final Map<String, Object> get() throws DataAccessServiceException {
		return doGet();
	}

	@Override
	public final List<Map<String, Object>> query() throws DataAccessServiceException {
		return doQuery();
	}

	@Override
	public final List<Map<String, Object>> query(final Paging aPaging) throws DataAccessServiceException {
		return doQuery(aPaging);
	}

	/**
	 * データに処理を実行する。
	 * 
	 * @return 実行結果
	 * @throws DataAccessServiceException データへのアクセス時に問題が発生した場合
	 */
	protected abstract boolean doExecute() throws DataAccessServiceException;

	/**
	 * データを更新する。
	 * 
	 * @return 更新件数
	 * @throws DataAccessServiceException データへのアクセス時に問題が発生した場合
	 */
	protected abstract int doUpdate() throws DataAccessServiceException;

	/**
	 * データの件数を取得する。
	 * 
	 * @return 件数
	 * @throws DataAccessServiceException データへのアクセス時に問題が発生した場合
	 */
	protected abstract long doCount() throws DataAccessServiceException;

	/**
	 * データを取得する。
	 * 
	 * @return データ
	 * @throws DataAccessServiceException データへのアクセス時に問題が発生した場合
	 */
	protected abstract Map<String, Object> doGet() throws DataAccessServiceException;

	/**
	 * データを取得する。
	 * 
	 * @return データ
	 * @throws DataAccessServiceException データへのアクセス時に問題が発生した場合
	 */
	protected abstract List<Map<String, Object>> doQuery() throws DataAccessServiceException;

	/**
	 * データを取得する。
	 * 
	 * @param aPaging ページ情報
	 * @return データ
	 * @throws DataAccessServiceException データへのアクセス時に問題が発生した場合
	 */
	protected abstract List<Map<String, Object>> doQuery(final Paging aPaging) throws DataAccessServiceException;
}
