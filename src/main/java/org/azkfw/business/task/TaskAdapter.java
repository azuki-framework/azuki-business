package org.azkfw.business.task;

/**
 * このクラスは、タスクのリスナー機能を定義したアダプタークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2014/10/16
 * @author Kawakicchi
 */
public abstract class TaskAdapter implements TaskListener {

	@Override
	public void taskStarted(final TaskEvent event) {

	}

	@Override
	public void taskFinished(final TaskEvent event) {

	}
}
