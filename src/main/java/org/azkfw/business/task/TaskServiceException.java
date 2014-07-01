package org.azkfw.business.task;

import org.azkfw.business.BusinessServiceException;

/**
 * このクラスは、タスクサービス層に起因する例外クラスです。
 * 
 * @since 1.0.1
 * @version 1.0.1 2014/06/05
 * @author Kawakicchi
 */
public class TaskServiceException extends BusinessServiceException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1729517231870861483L;

	/**
	 * コンストラクタ
	 */
	public TaskServiceException() {
		super();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param message Message
	 */
	public TaskServiceException(final String message) {
		super(message);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param throwable Throwable
	 */
	public TaskServiceException(final Throwable throwable) {
		super(throwable);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param message Message
	 * @param throwable Throwable
	 */
	public TaskServiceException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
}
