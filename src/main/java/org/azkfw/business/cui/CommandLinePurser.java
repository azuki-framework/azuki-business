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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * このクラスは、コマンドライン引数の解析を行うクラスです。
 * 
 * @since 1.0.1
 * @version 1.0.1 2014/06/10
 * @author Kawakicchi
 */
public class CommandLinePurser {

	private String escapeChar;

	private Map<String, Boolean> flags;

	private Map<String, String> options;

	private List<String> arguments;

	/**
	 * コンストラクタ
	 */
	public CommandLinePurser() {
		escapeChar = "-";
		flags = new HashMap<String, Boolean>();
		options = new HashMap<String, String>();
		arguments = new ArrayList<String>();
	}

	private String getKey(final String aKey) {
		return escapeChar + aKey;
	}

	private boolean isKey(final String aKey) {
		return aKey.startsWith(escapeChar);
	}

	/**
	 * フラグを指定する。
	 * 
	 * @param aKey
	 *            フラグキー
	 */
	public void setFlag(final String aKey) {
		flags.put(getKey(aKey), false);
	}

	/**
	 * フラグの有無を判定する。
	 * 
	 * @param aKey
	 *            キー
	 * @return 結果
	 */
	public boolean isFlag(final String aKey) {
		String key = getKey(aKey);
		if (flags.containsKey(key)) {
			return flags.get(key);
		} else {
			return false;
		}
	}

	/**
	 * オプション(必須)を指定する。
	 * 
	 * @param aKey
	 *            オプションキー
	 */
	public void setOption(final String aKey) {
		options.put(getKey(aKey), null);
	}

	/**
	 * オプションを指定する。
	 * 
	 * @param aKey
	 *            オプションキー
	 * @param aDefault
	 *            デフォルト値(nullは指定不可)
	 */
	public void setOption(final String aKey, final String aDefault) {
		if (null == aDefault) {
			throw new IllegalArgumentException(
					"Option default value cannot appoint Null.[" + aKey + "]");
		}
		options.put(getKey(aKey), aDefault);
	}

	/**
	 * オプションを取得する。
	 * 
	 * @param aKey
	 *            キー
	 * @return 値
	 */
	public String getOption(final String aKey) {
		String key = getKey(aKey);
		if (options.containsKey(key)) {
			return options.get(key);
		} else {
			return null;
		}
	}

	/**
	 * 引数を取得する。
	 * 
	 * @param index
	 *            インデックス
	 * @return 値
	 */
	public String getArgument(final int index) {
		return arguments.get(index);
	}

	/**
	 * コマンド引数を解析する。
	 * 
	 * @param args
	 *            引数
	 * @throws IllegalArgumentException
	 *             コマンド引数に不正があった場合
	 */
	public void purse(final String[] args) throws IllegalArgumentException {
		for (int i = 0; i < args.length; i++) {
			String str = args[i];

			if (flags.containsKey(str)) {
				flags.put(str, true);

			} else if (options.containsKey(str)) {
				if (i + 1 < args.length) {
					String value = args[i + 1];
					if (isKey(value)) {
						throw new IllegalArgumentException(
								"An optional value is not appointed.[" + str
										+ "]");

					} else {
						options.put(str, value);

						i++;
					}

				} else {
					throw new IllegalArgumentException(
							"An optional value is not appointed.[" + str + "]");
				}

			} else if (isKey(str)) {
				throw new IllegalArgumentException(
						"Undefined option was appointed.[" + str + "]");

			} else {
				arguments.add(str);

			}
		}

		for (String key : options.keySet()) {
			String value = options.get(key);
			if (null == value) {
				throw new IllegalArgumentException(
						"Required option is not appointed.[" + key + "]");
			}
		}
	}

}
