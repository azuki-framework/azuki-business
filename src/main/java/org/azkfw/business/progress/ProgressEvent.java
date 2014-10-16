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
package org.azkfw.business.progress;

/**
 * このクラスは、プログレス用のイベント情報を保持したクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2014/10/14
 * @author Kawakicchi
 */
public abstract class ProgressEvent {

	/** ソース */
	private Object source;

	/** 進捗率(0.0 ~ 100.0%) */
	private float progress;

	/** メッセージ */
	private String message;

	/** 詳細オブジェクト */
	private Object detail;

	/**
	 * コンストラクタ
	 * 
	 * @param aSource ソース
	 */
	public ProgressEvent(final Object aSource) {
		source = aSource;
		progress = 0.f;
		message = null;
		detail = null;
	}

	/**
	 * ソースを取得する。
	 * 
	 * @return ソース
	 */
	public Object getSource() {
		return source;
	}

	/**
	 * 進捗率を設定する。
	 * 
	 * @param aProgress 進捗率(0.0 ~ 100.0%)
	 */
	protected void setProgress(final float aProgress) {
		progress = aProgress;
	}

	/**
	 * 進捗率を取得する。
	 * 
	 * @return 進捗率(0.0 ~ 100.0%)
	 */
	public float getProgress() {
		return progress;
	}

	/**
	 * メッセージを設定する。
	 * 
	 * @param aMessage メッセージ
	 */
	protected void setMessage(final String aMessage) {
		message = aMessage;
	}

	/**
	 * メッセージを取得する。
	 * 
	 * @return メッセージ
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 詳細を設定する。
	 * 
	 * @param aDetail 詳細
	 */
	protected void setDetail(final Object aDetail) {
		detail = aDetail;
	}

	/**
	 * 詳細を取得する。
	 * 
	 * @return 詳細
	 */
	public Object getDetail() {
		return detail;
	}
}
