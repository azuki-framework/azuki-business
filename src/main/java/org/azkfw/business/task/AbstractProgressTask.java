package org.azkfw.business.task;

import java.util.ArrayList;
import java.util.List;

import org.azkfw.business.progress.ProgressEvent;
import org.azkfw.business.progress.ProgressListener;
import org.azkfw.business.progress.ProgressSupport;

/**
 * このクラスは、プログレス機能をサポートしたタスククラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2014/10/14
 * @author Kawakicchi
 */
public abstract class AbstractProgressTask extends AbstractPersistenceTask implements ProgressSupport {

	/** Progress event */
	private TaskProgressEvent event;

	/** Progress listener list */
	private List<ProgressListener> listeners;

	/**
	 * コンストラクタ
	 */
	public AbstractProgressTask() {
		super();
		event = new TaskProgressEvent(this);
		listeners = new ArrayList<ProgressListener>();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractProgressTask(final Class<?> aClass) {
		super(aClass);
		event = new TaskProgressEvent(this);
		listeners = new ArrayList<ProgressListener>();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractProgressTask(final String aName) {
		super(aName);
		event = new TaskProgressEvent(this);
		listeners = new ArrayList<ProgressListener>();
	}

	@Override
	public final void addProgressListener(final ProgressListener listener) {
		listeners.add(listener);
	}

	protected final void callProgress(final float aProgress) {
		event.setProgress(aProgress);
		for (ProgressListener listener : listeners) {
			listener.progress(event);
		}
	}

	protected final void callProgress(final String aMessage) {
		event.setProgress(aMessage);
		for (ProgressListener listener : listeners) {
			listener.progress(event);
		}
	}

	protected final void callProgress(final float aProgress, final String aMessage) {
		event.setProgress(aProgress, aMessage);
		for (ProgressListener listener : listeners) {
			listener.progress(event);
		}
	}

	protected final void callProgress(final float aProgress, final String aMessage, final Object aDetail) {
		event.setProgress(aProgress, aMessage, aDetail);
		for (ProgressListener listener : listeners) {
			listener.progress(event);
		}
	}

	/**
	 * このクラスは、タスク用のプログレスイベント情報を保持したクラスです。
	 * 
	 * @since 1.0.0
	 * @version 1.0.0 2014/10/14
	 * @author Kawakicchi
	 */
	private class TaskProgressEvent extends ProgressEvent {

		/**
		 * コンストラクタ
		 * 
		 * @param aSource ソース(Task)
		 */
		public TaskProgressEvent(final Object aSource) {
			super(aSource);
		}

		public void setProgress(final float aProgress) {
			super.setProgress(aProgress);
		}

		public void setProgress(final String aMessage) {
			super.setMessage(aMessage);
		}

		public void setProgress(final float aProgress, final String aMessage) {
			super.setProgress(aProgress);
			super.setMessage(aMessage);
		}

		public void setProgress(final float aProgress, final String aMessage, final Object aDetail) {
			super.setProgress(aProgress);
			super.setMessage(aMessage);
			super.setDetail(aDetail);
		}
	}
}
