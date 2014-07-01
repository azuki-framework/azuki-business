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

/**
 * このインターフェースは、データアクセス機能を表現したインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/14
 * @author Kawakicchi
 */
public interface DataAccessObject {

	/**
	 * データに処理を実行する。
	 * 
	 * @return 実行結果
	 * @throws DataAccessServiceException データへのアクセス時に問題が発生した場合
	 */
	public boolean execute() throws DataAccessServiceException;

	/**
	 * データを更新する。
	 * 
	 * @return 更新件数
	 * @throws DataAccessServiceException データへのアクセス時に問題が発生した場合
	 */
	public int update() throws DataAccessServiceException;

	/**
	 * データの件数を取得する。
	 * 
	 * @return 件数
	 * @throws DataAccessServiceException データへのアクセス時に問題が発生した場合
	 */
	public long count() throws DataAccessServiceException;

	/**
	 * データを取得する。
	 * 
	 * @return データ
	 * @throws DataAccessServiceException データへのアクセス時に問題が発生した場合
	 */
	public Map<String, Object> get() throws DataAccessServiceException;

	/**
	 * データを取得する。
	 * 
	 * @return データ
	 * @throws DataAccessServiceException データへのアクセス時に問題が発生した場合
	 */
	public List<Map<String, Object>> query() throws DataAccessServiceException;

	/**
	 * データを取得する。
	 * 
	 * @param aPaging ページング情報
	 * @return データ
	 * @throws DataAccessServiceException データへのアクセス時に問題が発生した場合
	 */
	public List<Map<String, Object>> query(final Paging aPaging) throws DataAccessServiceException;
}
