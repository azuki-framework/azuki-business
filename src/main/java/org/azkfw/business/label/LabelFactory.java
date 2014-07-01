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
package org.azkfw.business.label;

import org.azkfw.core.util.StringUtility;

/**
 * このクラスは、ラベルのインスタンス生成を行うファクトリークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/07/02
 * @author Kawakicchi
 */
public final class LabelFactory {

	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止する。
	 * </p>
	 */
	private LabelFactory() {

	}

	/**
	 * ラベルを生成する。
	 * 
	 * @param aId ラベルID
	 * @return ラベル情報。ラベル情報が存在しない場合、<code>null</code>を返す。
	 */
	public static Label create(final String aId) {
		return create(StringUtility.EMPTY, aId);
	}

	/**
	 * ラベルを生成する。
	 * 
	 * @param aNamespace 名前空間
	 * @param aId ラベルID
	 * @return ラベル情報。ラベル情報が存在しない場合、<code>null</code>を返す。
	 */
	public static Label create(final String aNamespace, final String aId) {
		return LabelManager.getLabel(aNamespace, aId);
	}
}
