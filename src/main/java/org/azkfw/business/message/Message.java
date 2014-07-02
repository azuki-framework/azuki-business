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
package org.azkfw.business.message;

import java.util.Map;

import org.azkfw.util.StringUtility;

/**
 * このクラスは、メッセージ情報を保持するクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/11
 * @author Kawakicchi
 */
public final class Message {

	/**
	 * メッセージＩＤ
	 */
	private String id;

	/**
	 * メッセージ
	 */
	private String message;

	/**
	 * コンストラクタ
	 * 
	 * @param aId メッセージＩＤ
	 * @param aMessage メッセージ
	 */
	public Message(final String aId, final String aMessage) {
		id = aId;
		message = aMessage;
	}

	/**
	 * メッセージIDを取得する。
	 * 
	 * @return メッセージID
	 */
	public String getId() {
		return id;
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
	 * メッセージを生成する。
	 * 
	 * @param args パラメーター
	 * @return メッセージ
	 */
	public String generate(final Object... args) {
		String msg = message;
		for (int i = 0; i < args.length; i++) {
			String word = "\\$\\{" + (i + 1) + "\\}";
			msg = msg.replaceAll(word, StringUtility.toStringEmpty(args[i]));
		}
		return msg;
	}

	/**
	 * メッセージを生成する。
	 * 
	 * @param args パラメーター
	 * @return メッセージ
	 */
	public String generate(final Map<String, Object> args) {
		String msg = message;
		for (String key : args.keySet()) {
			String word = "\\$\\{" + key + "\\}";
			msg = msg.replaceAll(word, StringUtility.toStringEmpty(args.get(key)));
		}
		return msg;
	}

}
