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
package org.azkfw.business.constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.azkfw.business.BusinessServiceException;
import org.azkfw.business.manager.AbstractManager;
import org.azkfw.business.property.Property;
import org.azkfw.configuration.ConfigurationFormatException;
import org.azkfw.context.Context;
import org.azkfw.util.StringUtility;

/**
 * このクラスは、定数の管理を行うマネージャークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2015/02/18
 * @author Kawakicchi
 */
public class ConstantManager extends AbstractManager {

	/**
	 * Instance
	 */
	private static final ConstantManager INSTANCE = new ConstantManager();

	/**
	 * デフォルト名前空間
	 */
	private static final String NAMESPACE_DEFAULT = StringUtility.EMPTY;

	/**
	 * 定数情報マップ
	 */
	private Map<String, Property> constants;

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
	 * 定数情報をロードします。
	 * 
	 * @param ns 名前空間
	 * @param stream 定数ファイルストリーム
	 * @param context コンテキスト情報
	 * @throws BusinessServiceException ビジネスサービスに起因する問題が発生した場合
	 * @throws ConfigurationFormatException 設定ファイルに問題がある場合
	 * @throws IOException 入出力操作に起因する問題が発生した場合
	 */
	public static void load(final String ns, final InputStream stream, final Context context) throws BusinessServiceException,
			ConfigurationFormatException, IOException {
		INSTANCE.doLoad(ns, stream, context);
	}

	/**
	 * 定数情報をロードします。
	 * 
	 * @param stream 定数ファイルストリーム
	 * @param context コンテキスト情報
	 * @throws BusinessServiceException ビジネスサービスに起因する問題が発生した場合
	 * @throws ConfigurationFormatException 設定ファイルに問題がある場合
	 * @throws IOException 入出力操作に起因する問題が発生した場合
	 */
	public static void load(final InputStream stream, final Context context) throws BusinessServiceException, ConfigurationFormatException,
			IOException {
		INSTANCE.doLoad(ConstantManager.NAMESPACE_DEFAULT, stream, context);
	}

	/**
	 * 定数を取得する。
	 * 
	 * @param name 名前
	 * @return 定数
	 */
	public static String getString(final String name) {
		return INSTANCE.doGetString(ConstantManager.NAMESPACE_DEFAULT, name, null);
	}

	/**
	 * 定数を取得する。
	 * 
	 * @param ns 名前空間
	 * @param name 名前
	 * @return 定数
	 */
	public static String getString(final String ns, final String name) {
		return INSTANCE.doGetString(ns, name, null);
	}


	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止する。
	 * </p>
	 */
	private ConstantManager() {
		super(ConstantManager.class);
		constants = new HashMap<String, Property>();
	}

	/**
	 * 初期化処理を行う。
	 */
	private void doInitialize() {
		synchronized (constants) {
			constants.clear();
		}
	}

	/**
	 * 解放処理を行う。
	 */
	private void doDestroy() {
		synchronized (constants) {
			constants.clear();
		}
	}

	private void doLoad(final String ns, final InputStream stream, final Context context) throws ConfigurationFormatException, IOException {
		synchronized (constants) {
			if (constants.containsKey(ns)) {
				throw new ConfigurationFormatException("Duplicate constant namespace.[namespace=" + ns + "]");
			}

			Properties properties = new Properties();
			properties.load(stream);
			Property property = Property.Builder.build(properties);
			constants.put(ns, property);
		}
	}

	private String doGetString(final String ns, final String name, final String def) {
		String value = def;
		if (constants.containsKey(ns)) {
			Property p = constants.get(ns);
			value = p.getString(name, def);
		}
		return value;
	}
}
