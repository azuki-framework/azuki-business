package org.azkfw.business.task;

/**
 * このインターフェースは、タスクのリスナー機能を定義したインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2014/10/16
 * @author Kawakicchi
 */
public interface TaskListener {

	/**
	 * タスクの処理開始時に呼び出される。
	 * 
	 * @param event イベント
	 */
	public void taskStarted(final TaskEvent event);

	/**
	 * タスクの処理終了時に呼び出される。
	 * 
	 * @param event イベント
	 */
	public void taskFinished(final TaskEvent event);
}
