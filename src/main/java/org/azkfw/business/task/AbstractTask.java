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
package org.azkfw.business.task;

import org.azkfw.lang.LoggingObject;

/**
 * このクラスは、タスク機能の実装を行うための基底クラスです。
 * 
 * @since 1.0.1
 * @version 1.0.1 2014/06/05
 * @author Kawakicchi
 */
public abstract class AbstractTask extends LoggingObject implements Task {

	/**
	 * コンストラクタ
	 */
	public AbstractTask() {
		super(Task.class);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractTask(final Class<?> aClass) {
		super(aClass);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractTask(final String aName) {
		super(aName);
	}

	@Override
	public final void execute() throws TaskServiceException {
		try {
			doInitialize();
			doExecute();
		} catch (TaskServiceException ex) {
			fatal(ex);
			throw ex;
		} finally {
			doRelease();
		}
	}

	/**
	 * 初期化処理を行う。
	 * 
	 * @throws TaskServiceException タスクサービスに起因する問題が発生した場合
	 */
	protected abstract void doInitialize() throws TaskServiceException;

	/**
	 * 解放処理を行う。
	 */
	protected abstract void doRelease();

	/**
	 * タスクを実行する。
	 * 
	 * @throws TaskServiceException タスクサービスに起因する問題が発生した場合
	 */
	protected abstract void doExecute() throws TaskServiceException;

}
