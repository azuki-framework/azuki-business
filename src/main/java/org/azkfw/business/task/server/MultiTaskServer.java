package org.azkfw.business.task.server;

import java.util.ArrayList;
import java.util.List;

import org.azkfw.business.task.Task;
import org.azkfw.lang.LoggingObject;

/**
 * このクラスは、複数タスクを管理するサーバクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2014/10/14
 * @author Kawakicchi
 */
public class MultiTaskServer extends LoggingObject implements Runnable {

	private Thread thread;
	private boolean stopRequestFlag;

	private int maxRunningTaskSize;
	private int runningTaskSize;

	private MultiTaskServerEvent event;
	private List<MultiTaskServerListener> listeners;

	private List<TaskThread> taskThreads;

	/**
	 * コンストラクタ
	 */
	public MultiTaskServer() {
		super(MultiTaskServer.class);

		event = new MultiTaskServerEvent(this);
		listeners = new ArrayList<MultiTaskServerListener>();
		maxRunningTaskSize = 2;
		taskThreads = new ArrayList<TaskThread>();
	}

	/**
	 * マルチタスクサーバへリスナーを追加する。
	 * 
	 * @param listener リスナー
	 */
	public final void addMultiTaskServerListener(final MultiTaskServerListener listener) {
		synchronized (listeners) {
			listeners.add(listener);
		}
	}

	/**
	 * マルチタスクサーバからリスナーを削除する。
	 * 
	 * @param listener リスナー
	 */
	public final void removeMultiTaskServerListener(final MultiTaskServerListener listener) {
		synchronized (listeners) {
			listeners.remove(listener);
		}
	}

	/**
	 * サーバを起動する。
	 * 
	 * @return 結果。起動済みの場合は、<code>false</code>を返す。
	 */
	public synchronized boolean start() {
		boolean result = false;
		if (null == thread) {
			info("MultiTaskServer start.");
			
			stopRequestFlag = false;
			runningTaskSize = 0;
			thread = new Thread(this);
			thread.start();
			result = true;
		}
		return result;
	}

	/**
	 * サーバを停止する。
	 * <p>
	 * サーバに対して停止要求を行う。<br/>
	 * サーバの停止判断は、
	 * {@link MultiTaskServerListener#multiTaskServerStopped(MultiTaskServerEvent)}
	 * にて行う。
	 * </p>
	 */
	public void stop() {
		info("MultiTaskServer stop.");
		stopRequestFlag = true;
	}

	/**
	 * サーバにタスクを依頼する。
	 * 
	 * @param aTask タスク
	 */
	public void queue(final Task aTask) {
		synchronized (taskThreads) {
			TaskThread taskThread = new TaskThread(aTask);

			taskThread.setTaskThreadListener(new TaskThreadListener() {
				@Override
				public void taskThreadStarted(final TaskThread taskThread) {
					synchronized (listeners) {
						for (MultiTaskServerListener listener : listeners) {
							try {
								listener.multiTaskServerStartedTask(event, aTask);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
				}

				@Override
				public void taskThreadStopped(final TaskThread taskThread) {
					runningTaskSize--;
					synchronized (listeners) {
						for (MultiTaskServerListener listener : listeners) {
							try {
								listener.multiTaskServerStoppedTask(event, aTask);
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
					}
				}
			});

			taskThreads.add(taskThread);

			synchronized (listeners) {
				info ("MultiTaskServer call lisetener.[multiTaskServerQueuedTask()]");
				for (MultiTaskServerListener listener : listeners) {
					try {
						listener.multiTaskServerQueuedTask(event, aTask);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void run() {
		synchronized (listeners) {
			info ("MultiTaskServer call lisetener.[multiTaskServerStarted()]");
			for (MultiTaskServerListener listener : listeners) {
				try {
					listener.multiTaskServerStarted(event);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}

		try {
			while (!stopRequestFlag) {
				runningTask();

				Thread.sleep(500);
			}

			while (0 < runningTaskSize) {

				Thread.sleep(500);
			}

		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		thread = null;

		synchronized (listeners) {
			info ("MultiTaskServer call lisetener.[multiTaskServerStopped()]");
			for (MultiTaskServerListener listener : listeners) {
				try {
					listener.multiTaskServerStopped(event);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private void runningTask() {
		while (runningTaskSize < maxRunningTaskSize) {
			TaskThread target = null;

			synchronized (taskThreads) {
				for (TaskThread tt : taskThreads) {
					if (0 == tt.getStatus()) {
						target = tt;
						break;
					}
				}
				if (null != target) {
					if (target.start()) {
						runningTaskSize++;
					}
				} else {
					break;
				}
			}
		}
	}

	private interface TaskThreadListener {
		public void taskThreadStarted(final TaskThread taskThread);

		public void taskThreadStopped(final TaskThread taskThread);
	}

	private class TaskThread implements Runnable {

		private Task task;

		private int status;

		private Thread thread;

		private TaskThreadListener listener;

		public TaskThread(final Task aTask) {
			task = aTask;
			status = 0;
		}

		public void setTaskThreadListener(final TaskThreadListener aListener) {
			listener = aListener;
		}

		public int getStatus() {
			return status;
		}

		public synchronized boolean start() {
			boolean result = false;
			if (0 == status) {
				status = 1;
				thread = new Thread(this);
				thread.start();
				result = true;
			}
			return result;
		}

		@Override
		public void run() {
			try {
				listener.taskThreadStarted(this);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			try {
				task.execute();
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			status = 2;

			try {
				listener.taskThreadStopped(this);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
