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

import org.azkfw.core.util.StringUtility;

/**
 * このクラスは、メッセージのインスタンス生成を行うファクトリークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/01/13
 * @author Kawakicchi
 */
public final class MessageFactory {

	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止する。
	 * </p>
	 */
	private MessageFactory() {

	}

	/**
	 * メッセージを生成する。
	 * 
	 * @param aId メッセージID
	 * @return メッセージ情報。メッセージ情報が存在しない場合、<code>null</code>を返す。
	 */
	public static Message create(final String aId) {
		return create(StringUtility.EMPTY, aId);
	}

	/**
	 * メッセージを生成する。
	 * 
	 * @param aNamespace 名前空間
	 * @param aId メッセージID
	 * @return メッセージ情報。メッセージ情報が存在しない場合、<code>null</code>を返す。
	 */
	public static Message create(final String aNamespace, final String aId) {
		return MessageManager.getMessage(aNamespace, aId);
	}
}
