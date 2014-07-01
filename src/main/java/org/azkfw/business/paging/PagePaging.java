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
 * このクラスは、ページ機能を実装するクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/03
 * @author Kawakicchi
 */
public class PagePaging implements Paging {

	private long size;

	private long page;

	/**
	 * コンストラクタ
	 * 
	 * @param aSize ページサイズ
	 */
	public PagePaging(final long aSize) {
		size = aSize;
		page = 0;
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aPage ページ数(0始まり)
	 * @param aSize ページサイズ
	 */
	public PagePaging(final long aPage, final long aSize) {
		size = aSize;
		page = aPage;
	}

	@Override
	public long getSize() {
		return size;
	}

	@Override
	public long getPage() {
		return page;
	}

	@Override
	public String getSinceId() {
		return null;
	}

	@Override
	public String getMaxId() {
		return null;
	}

	@Override
	public String getKey() {
		return null;
	}

}
