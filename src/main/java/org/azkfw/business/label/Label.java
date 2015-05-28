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

import java.util.List;
import java.util.Map;

import org.azkfw.util.StringUtility;

/**
 * このクラスは、ラベル情報を保持するクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/07/02
 * @author Kawakicchi
 */
public final class Label {

	/**
	 * ラベルＩＤ
	 */
	private String id;

	/**
	 * ラベル
	 */
	private String label;

	/**
	 * コンストラクタ
	 * 
	 * @param aId ラベルＩＤ
	 * @param aLabel ラベル
	 */
	public Label(final String aId, final String aLabel) {
		id = aId;
		label = aLabel;
	}

	/**
	 * ラベルIDを取得する。
	 * 
	 * @return ラベルID
	 */
	public String getId() {
		return id;
	}

	/**
	 * ラベルを取得する。
	 * 
	 * @return ラベル
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * ラベルを生成する。
	 * 
	 * @return ラベル
	 */
	public String generate() {
		return label;
	}

	/**
	 * ラベルを生成する。
	 * 
	 * @param args パラメーター
	 * @return ラベル
	 */
	public String generate(final Object... args) {
		String lbl = label;
		for (int i = 0; i < args.length; i++) {
			String word = "\\$\\{" + (i + 1) + "\\}";
			lbl = lbl.replaceAll(word, StringUtility.toStringEmpty(args[i]));
		}
		return lbl;
	}

	/**
	 * ラベルを生成する。
	 * 
	 * @param args パラメーター
	 * @return ラベル
	 */
	public String generate(final List<Object> args) {
		String lbl = label;
		for (int i = 0; i < args.size(); i++) {
			String word = "\\$\\{" + (i + 1) + "\\}";
			lbl = lbl.replaceAll(word, StringUtility.toStringEmpty(args.get(i)));
		}
		return lbl;
	}

	/**
	 * ラベルを生成する。
	 * 
	 * @param args パラメーター
	 * @return ラベル
	 */
	public String generate(final Map<String, Object> args) {
		String lbl = label;
		for (String key : args.keySet()) {
			String word = "\\$\\{" + key + "\\}";
			lbl = lbl.replaceAll(word, StringUtility.toStringEmpty(args.get(key)));
		}
		return lbl;
	}

}
