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
public abstract class AbstractNotificationTask extends AbstractTask implements ProgressSupport {

	/** Progress event */
	private TaskProgressEvent progressEvent;

	/** Progress listener list */
	private List<ProgressListener> listeners;

	/**
	 * コンストラクタ
	 */
	public AbstractNotificationTask() {
		super();
		progressEvent = new TaskProgressEvent(this);
		listeners = new ArrayList<ProgressListener>();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractNotificationTask(final Class<?> aClass) {
		super(aClass);
		progressEvent = new TaskProgressEvent(this);
		listeners = new ArrayList<ProgressListener>();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractNotificationTask(final String aName) {
		super(aName);
		progressEvent = new TaskProgressEvent(this);
		listeners = new ArrayList<ProgressListener>();
	}

	@Override
	public final void addProgressListener(final ProgressListener listener) {
		synchronized (listeners) {
			listeners.add(listener);
		}
	}

	@Override
	public final void removeProgressListener(final ProgressListener listener) {
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}

	protected final void callProgress(final float aProgress) {
		synchronized (listeners) {
			progressEvent.setProgress(aProgress);
			for (ProgressListener listener : listeners) {
				try {
					listener.progress(progressEvent);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	protected final void callProgress(final String aMessage) {
		synchronized (listeners) {
			progressEvent.setProgress(aMessage);
			for (ProgressListener listener : listeners) {
				try {
					listener.progress(progressEvent);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	protected final void callProgress(final float aProgress, final String aMessage) {
		synchronized (listeners) {
			progressEvent.setProgress(aProgress, aMessage);
			for (ProgressListener listener : listeners) {
				try {
					listener.progress(progressEvent);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	protected final void callProgress(final float aProgress, final String aMessage, final Object aDetail) {
		synchronized (listeners) {
			progressEvent.setProgress(aProgress, aMessage, aDetail);
			for (ProgressListener listener : listeners) {
				try {
					listener.progress(progressEvent);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
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
