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

import java.util.ArrayList;
import java.util.List;

import org.azkfw.lang.LoggingObject;

/**
 * このクラスは、タスク機能の実装を行うための基底クラスです。
 * 
 * @since 1.0.1
 * @version 1.0.1 2014/06/05
 * @author Kawakicchi
 */
public abstract class AbstractTask extends LoggingObject implements Task, TaskListenerSupport {

	/** task event */
	private TaskEvent taskEvent;
	/** task listener */
	private List<TaskListener> listeners;

	/**
	 * コンストラクタ
	 */
	public AbstractTask() {
		super(Task.class);
		taskEvent = new TaskEvent(this);
		listeners = new ArrayList<TaskListener>();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractTask(final Class<?> aClass) {
		super(aClass);
		taskEvent = new TaskEvent(this);
		listeners = new ArrayList<TaskListener>();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractTask(final String aName) {
		super(aName);
		taskEvent = new TaskEvent(this);
		listeners = new ArrayList<TaskListener>();
	}

	@Override
	public final void execute() throws TaskServiceException {
		try {
			synchronized (listeners) {
				for (TaskListener listener : listeners) {
					try {
						listener.taskStarted(taskEvent);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}

			doInitialize();
			doExecute();
		} catch (TaskServiceException ex) {
			fatal(ex);
			throw ex;
		} finally {
			doRelease();

			synchronized (listeners) {
				for (TaskListener listener : listeners) {
					try {
						listener.taskFinished(taskEvent);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public final void addTaskListener(final TaskListener listener) {
		synchronized (listeners) {
			listeners.add(listener);
		}
	}

	@Override
	public final void removeTaskListener(final TaskListener listener) {
		synchronized (listeners) {
			listeners.remove(listener);
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
