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
package org.azkfw.business.task.server;

import org.azkfw.business.task.Task;

/**
 * このインターフェースは、タスクマルチサーバのリスナーインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2014/10/14
 * @author Kawakicchi
 */
public interface MultiTaskServerListener {

	/**
	 * マルチタスクサーバの起動時に呼び出される。
	 * 
	 * @param event イベント情報
	 */
	public void multiTaskServerStarted(final MultiTaskServerEvent event);

	/**
	 * マルチタスクサーバの停止時に呼び出される。
	 * 
	 * @param event イベント情報
	 */
	public void multiTaskServerStopped(final MultiTaskServerEvent event);

	/**
	 * タスクがキュー時に呼び出される。
	 * 
	 * @param event イベント情報
	 * @param aTask タスク
	 */
	public void multiTaskServerQueuedTask(final MultiTaskServerEvent event, final Task aTask);

	/**
	 * タスクの実行時に呼び出される。
	 * 
	 * @param event イベント情報
	 * @param aTask タスク
	 */
	public void multiTaskServerStartedTask(final MultiTaskServerEvent event, final Task aTask);

	/**
	 * タスクの終了時に呼び出される。
	 * 
	 * @param event イベント情報
	 * @param aTask タスク
	 */
	public void multiTaskServerStoppedTask(final MultiTaskServerEvent event, final Task aTask);

}
