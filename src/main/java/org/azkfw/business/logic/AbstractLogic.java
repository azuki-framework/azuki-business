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

import org.azkfw.lang.LoggingObject;

/**
 * このクラスは、ロジック機能を実装する基底クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/12/30
 * @author Kawakicchi
 */
public abstract class AbstractLogic extends LoggingObject implements Logic {

	/**
	 * コンストラクタ
	 */
	public AbstractLogic() {
		super(Logic.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractLogic(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractLogic(final Class<?> aClass) {
		super(aClass);
	}

	/**
	 * 初期化処理を行う。
	 */
	public final void initialize() {
		doInitialize();
	}

	/**
	 * 解放処理を行う。
	 */
	public final void destroy() {
		doDestroy();
	}

	/**
	 * 初期化処理を行う。
	 */
	protected abstract void doInitialize();

	/**
	 * 解放処理を行う。
	 */
	protected abstract void doDestroy();

}
