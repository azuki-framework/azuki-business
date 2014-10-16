package org.azkfw.business.task;

/**
 * このクラスは、タスクのイベント情報を保持したクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2014/10/16
 * @author Kawakicchi
 */
public class TaskEvent {

	/** タスク */
	private Task task;

	/**
	 * コンストラクタ
	 * 
	 * @param aTask タスク
	 */
	public TaskEvent(final Task aTask) {
		task = aTask;
	}

	/**
	 * タスクを取得する。
	 * 
	 * @return タスク
	 */
	public Task getTask() {
		return task;
	}
}
