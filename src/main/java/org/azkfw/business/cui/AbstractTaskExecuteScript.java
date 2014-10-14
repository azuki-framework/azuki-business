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
package org.azkfw.business.cui;

import org.azkfw.business.progress.ProgressEvent;
import org.azkfw.business.progress.ProgressListener;
import org.azkfw.business.progress.ProgressSupport;
import org.azkfw.business.task.Task;
import org.azkfw.console.Console;
import org.azkfw.console.ConsoleFactory;

/**
 * このクラスは、タスク実行機能を実装したスクリプトクラスです。
 * 
 * @since 1.0.1
 * @version 1.0.1 2014/06/10
 * @author Kawakicchi
 */
public abstract class AbstractTaskExecuteScript implements Runnable {

	private Console console;

	private boolean running;

	private Task task;

	private long startTime;
	private long endTime;

	/**
	 * コンストラクタ
	 */
	public AbstractTaskExecuteScript() {
		console = ConsoleFactory.create();

		running = false;

		startTime = 0;
		endTime = -1;
	}

	private int progressSize;
	private String spaceLine;
	private String backLine;

	/**
	 * タスクを実行する。
	 * 
	 * @param aTask タスク
	 */
	public synchronized void execute(final Task aTask) {
		if (!running) {
			running = true;

			task = aTask;

			startTime = System.currentTimeMillis();
			endTime = -1;

			Thread t = new Thread(this);
			t.start();

			progressSize = 50;
			int totalSize = progressSize + 10 + 1 + 10;

			spaceLine = spaceLine(totalSize);
			backLine = backLine(totalSize);

			ProgressSupport progress = null;
			if (task instanceof ProgressSupport) {
				progress = (ProgressSupport) task;
				progress.addProgressListener(new ProgressListener() {

					@Override
					public void progress(final ProgressEvent event) {
						float prg = event.getProgress();
						StringBuilder s = new StringBuilder();

						if (0 <= prg) {
							int c = (int) Math.floor(prg / (100.f / (float) progressSize));
							for (int i = 0; i < c; i++) {
								s.append("=");
							}
							if (c < progressSize) {
								s.append(">");
							}
						}

						long time = System.currentTimeMillis() - startTime;

						console.print(backLine);
						String timeString = AbstractTaskExecuteScript.toString(time);
						if (0 <= prg) {
							console.print(" %5.1f%% [%-" + progressSize + "s] %s", prg, s.toString(), timeString);
						} else {
							console.print("  ??.?%% [%-" + progressSize + "s] %s", "", timeString);
						}
					}
				});
			}

			while (running) {
				try {
					Thread.sleep(100);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			if (null != progress) {
				console.print(backLine);
				console.print(spaceLine);
				console.print(backLine);
			}

			endTime = System.currentTimeMillis();
		}
	}

	/**
	 * 実行時間(ms)を取得する。
	 * 
	 * @return 時間(ms)
	 */
	protected final long getTime() {
		if (-1 == endTime) {
			return 0;
		} else {
			return endTime - startTime;
		}
	}

	private static String toString(final long aTime) {
		String s = null;
		if (1000 * 60 * 60 <= aTime) {
			// h
			long totalSec = aTime / 1000;
			long totalMin = totalSec / 60;
			long hour = totalMin / 60;
			long min = totalMin % 60;
			float disp = (float) hour + ((float) min / 60.f);
			s = String.format("%6.1f %-3s", disp, "h");
		} else if (1000 * 60 <= aTime) {
			// min
			long totalSec = aTime / 1000;
			long min = totalSec / 60;
			long sec = totalSec % 60;
			float disp = (float) min + ((float) sec / 60.f);
			s = String.format("%6.1f %-3s", disp, "min");
		} else {
			// sec
			float disp = (float) aTime / 1000.0f;
			s = String.format("%6.1f %-3s", disp, "sec");
		}
		return s;
	}

	private String spaceLine(final int size) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < size; i++) {
			s.append(" ");
		}
		return s.toString();
	}

	private String backLine(final int size) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < size; i++) {
			s.append("\b");
		}
		return s.toString();
	}

	public void run() {
		try {
			task.execute();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		running = false;
	}
}
