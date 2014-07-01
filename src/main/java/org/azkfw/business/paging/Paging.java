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
package org.azkfw.business.paging;

/**
 * このインターフェースは、ページング機能を表現したインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/21
 * @author Kawakicchi
 */
public interface Paging {

	/**
	 * ページサイズを取得する。
	 * 
	 * @return サイズ
	 */
	public long getSize();

	/**
	 * ページ数を取得する。
	 * 
	 * @return ページ数(0始まり)
	 */
	public long getPage();

	/**
	 * 開始IDを取得する。
	 * 
	 * @return ID
	 */
	public String getSinceId();

	/**
	 * 完了IDを取得する。
	 * 
	 * @return ID
	 */
	public String getMaxId();

	/**
	 * IDのキーを取得する。
	 * 
	 * @return キー
	 */
	public String getKey();
}
