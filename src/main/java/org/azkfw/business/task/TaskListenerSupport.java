package org.azkfw.business.task;

/**
 * このインターフェースは、タスクリスナーをサポートするためのインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2014/10/16
 * @author kawakita
 */
public interface TaskListenerSupport {

	/**
	 * タスクリスナーを追加する。
	 * 
	 * @param listener リスナー
	 */
	public void addTaskListener(final TaskListener listener);

	/**
	 * タスクリスナーを削除する。
	 * 
	 * @param listener リスナー
	 */
	public void removeTaskListener(final TaskListener listener);
}
