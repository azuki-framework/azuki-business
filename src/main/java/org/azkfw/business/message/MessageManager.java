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

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.azkfw.business.BusinessServiceException;
import org.azkfw.business.manager.AbstractManager;
import org.azkfw.core.util.StringUtility;
import org.azkfw.persistence.ConfigurationFormatException;
import org.azkfw.persistence.context.Context;

/**
 * このクラスは、メッセージの管理を行うマネージャークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/11
 * @author Kawakicchi
 */
public final class MessageManager extends AbstractManager {

	/**
	 * Instance
	 */
	private static final MessageManager INSTANCE = new MessageManager();

	/**
	 * デフォルト名前空間
	 */
	private static final String NAMESPACE_DEFAULT = StringUtility.EMPTY;

	/**
	 * 初期化処理を行う。
	 */
	public static void initialize() {
		INSTANCE.doInitialize();
	}

	/**
	 * 解放処理を行う。
	 */
	public static void destroy() {
		INSTANCE.doDestroy();
	}

	/**
	 * メッセージ情報をロードします。
	 * 
	 * @param aNamespace 名前空間
	 * @param aStream メッセージファイルストリーム
	 * @param aContext コンテキスト情報
	 * @throws BusinessServiceException ビジネスサービスに起因する問題が発生した場合
	 * @throws ConfigurationFormatException 設定ファイルに問題がある場合
	 * @throws IOException 入出力操作に起因する問題が発生した場合
	 */
	public static void load(final String aNamespace, final InputStream aStream, final Context aContext) throws BusinessServiceException,
			ConfigurationFormatException, IOException {
		INSTANCE.doLoad(aNamespace, aStream, aContext);
	}

	/**
	 * メッセージ情報をロードします。
	 * 
	 * @param aStream メッセージファイルストリーム
	 * @param aContext コンテキスト情報
	 * @throws BusinessServiceException ビジネスサービスに起因する問題が発生した場合
	 * @throws ConfigurationFormatException 設定ファイルに問題がある場合
	 * @throws IOException 入出力操作に起因する問題が発生した場合
	 */
	public static void load(final InputStream aStream, final Context aContext) throws BusinessServiceException, ConfigurationFormatException,
			IOException {
		INSTANCE.doLoad(MessageManager.NAMESPACE_DEFAULT, aStream, aContext);
	}

	/**
	 * メッセージを取得する。
	 * 
	 * @param aId メッセージID
	 * @return メッセージ情報。メッセージ情報が存在しない場合、<code>null</code>を返す。
	 */
	public static Message getMessage(final String aId) {
		return INSTANCE.doGetMessage(MessageManager.NAMESPACE_DEFAULT, aId);
	}

	/**
	 * メッセージを取得する。
	 * 
	 * @param aNamespace 名前空間
	 * @param aId メッセージID
	 * @return メッセージ情報。メッセージ情報が存在しない場合、<code>null</code>を返す。
	 */
	public static Message getMessage(final String aNamespace, final String aId) {
		return INSTANCE.doGetMessage(aNamespace, aId);
	}

	/**
	 * メッセージ情報マップ
	 */
	private Map<String, Map<String, Message>> messages;

	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止する。
	 * </p>
	 */
	private MessageManager() {
		super(MessageManager.class);
		messages = new HashMap<String, Map<String, Message>>();
	}

	/**
	 * 処理化処理を行う。
	 */
	private void doInitialize() {
		synchronized (messages) {
			messages.clear();
		}
	}

	/**
	 * 解放処理を行う。
	 */
	private void doDestroy() {
		synchronized (messages) {
			messages.clear();
		}
	}

	/**
	 * メッセージ情報をロードする。
	 * 
	 * @param aNamespace 名前空間
	 * @param aStream メッセージファイルストリーム
	 * @param aContext コンテキスト情報
	 * @throws ConfigurationFormatException 設定ファイルに問題がある場合
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	private void doLoad(final String aNamespace, final InputStream aStream, final Context aContext) throws ConfigurationFormatException, IOException {
		synchronized (messages) {
			Properties p = new Properties();
			p.load(aStream);
			for (Object key : p.keySet()) {
				Object value = p.get(key);

				String k, v;
				if (key instanceof String) {
					k = (String) key;
				} else {
					k = key.toString();
				}
				if (value instanceof String) {
					v = (String) value;
				} else {
					v = value.toString();
				}

				Map<String, Message> msgs = null;
				if (messages.containsKey(aNamespace)) {
					msgs = messages.get(aNamespace);
				} else {
					msgs = new HashMap<String, Message>();
					messages.put(aNamespace, msgs);
				}

				if (msgs.containsKey(k)) {
					throw new ConfigurationFormatException("Duplicate　message key.[namespace=" + aNamespace + ", key=" + k + "]");
				}

				Message msg = new Message(k, v);
				msgs.put(k, msg);
			}
		}
	}

	/**
	 * メッセージを取得する。
	 * 
	 * @param aNamespace 名前空間
	 * @param aId メッセージID
	 * @return メッセージ情報。メッセージ情報が存在しない場合、<code>null</code>を返す。
	 */
	private Message doGetMessage(final String aNamespace, final String aId) {
		Message msg = null;
		if (messages.containsKey(aNamespace)) {
			Map<String, Message> ms = messages.get(aNamespace);
			if (ms.containsKey(aId)) {
				msg = ms.get(aId);
			}
		}
		return msg;
	}
}
