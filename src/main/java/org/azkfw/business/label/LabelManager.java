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

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.azkfw.business.BusinessServiceException;
import org.azkfw.business.manager.AbstractManager;
import org.azkfw.configuration.ConfigurationFormatException;
import org.azkfw.context.Context;
import org.azkfw.util.StringUtility;

/**
 * このクラスは、ラベルの管理を行うマネージャークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/07/02
 * @author Kawakicchi
 */
public final class LabelManager extends AbstractManager {

	/**
	 * デフォルト名前空間
	 */
	private static final String NAMESPACE_DEFAULT = StringUtility.EMPTY;

	/**
	 * Instance
	 */
	private static final LabelManager INSTANCE = new LabelManager();

	/**
	 * ラベル情報マップ
	 */
	private Map<String, Map<String, Label>> labels;

	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止する。
	 * </p>
	 */
	private LabelManager() {
		super(LabelManager.class);
		labels = new HashMap<String, Map<String, Label>>();
	}

	/**
	 * インスタンスを取得する。
	 * 
	 * @return インスタンス
	 */
	public static final LabelManager getInstance() {
		return INSTANCE;
	}

	/**
	 * ラベルを取得する。
	 * 
	 * @param id ラベルID
	 * @return ラベル情報。ラベル情報が存在しない場合、<code>null</code>を返す。
	 */
	public static Label getLabel(final String id) {
		return INSTANCE.doGetLabel(LabelManager.NAMESPACE_DEFAULT, id);
	}

	/**
	 * ラベルを取得する。
	 * 
	 * @param aNamespace 名前空間
	 * @param aId ラベルID
	 * @return ラベル情報。ラベル情報が存在しない場合、<code>null</code>を返す。
	 */
	public static Label getLabel(final String aNamespace, final String aId) {
		return INSTANCE.doGetLabel(aNamespace, aId);
	}

	/**
	 * 初期化処理を行う。
	 */
	public void initialize() {
		INSTANCE.doInitialize();
	}

	/**
	 * 解放処理を行う。
	 */
	public void destroy() {
		INSTANCE.doDestroy();
	}
	/**
	 * ラベル情報をロードします。
	 * 
	 * @param name 名前
	 * @param context コンテキスト情報
	 * @throws BusinessServiceException ビジネスサービスに起因する問題が発生した場合
	 * @throws ConfigurationFormatException 設定ファイルに問題がある場合
	 * @throws IOException 入出力操作に起因する問題が発生した場合
	 */
	public void load(final String name, final Context context) throws BusinessServiceException,
			ConfigurationFormatException, IOException {
		INSTANCE.doLoad(LabelManager.NAMESPACE_DEFAULT, context.getResourceAsStream(name), context);
	}
	
	/**
	 * ラベル情報をロードします。
	 * 
	 * @param ns 名前空間
	 * @param name 名前
	 * @param context コンテキスト情報
	 * @throws BusinessServiceException ビジネスサービスに起因する問題が発生した場合
	 * @throws ConfigurationFormatException 設定ファイルに問題がある場合
	 * @throws IOException 入出力操作に起因する問題が発生した場合
	 */
	public void load(final String ns, final String name, final Context context) throws BusinessServiceException,
			ConfigurationFormatException, IOException {
		INSTANCE.doLoad(ns, context.getResourceAsStream(name), context);
	}
	
	/**
	 * ラベル情報をロードします。
	 * 
	 * @param stream Propertiesファイルストリーム
	 * @param context コンテキスト情報
	 * @throws BusinessServiceException ビジネスサービスに起因する問題が発生した場合
	 * @throws ConfigurationFormatException 設定ファイルに問題がある場合
	 * @throws IOException 入出力操作に起因する問題が発生した場合
	 */
	public void load(final InputStream stream, final Context context) throws BusinessServiceException, ConfigurationFormatException, IOException {
		INSTANCE.doLoad(LabelManager.NAMESPACE_DEFAULT, stream, context);
	}

	/**
	 * ラベル情報をロードします。
	 * 
	 * @param ns 名前空間
	 * @param stream Propertiesファイルストリーム
	 * @param context コンテキスト情報
	 * @throws BusinessServiceException ビジネスサービスに起因する問題が発生した場合
	 * @throws ConfigurationFormatException 設定ファイルに問題がある場合
	 * @throws IOException 入出力操作に起因する問題が発生した場合
	 */
	public void load(final String ns, final InputStream stream, final Context context) throws BusinessServiceException,
			ConfigurationFormatException, IOException {
		INSTANCE.doLoad(ns, stream, context);
	}

	/**
	 * 処理化処理を行う。
	 */
	private void doInitialize() {
		synchronized (labels) {
			labels.clear();
		}
	}

	/**
	 * 解放処理を行う。
	 */
	private void doDestroy() {
		synchronized (labels) {
			labels.clear();
		}
	}

	/**
	 * ラベル情報をロードする。
	 * 
	 * @param ns 名前空間
	 * @param stream ラベルファイルストリーム
	 * @param context コンテキスト情報
	 * @throws ConfigurationFormatException 設定ファイルに問題がある場合
	 * @throws IOException IO操作時に問題が発生した場合
	 */
	private void doLoad(final String ns, final InputStream stream, final Context context) throws ConfigurationFormatException, IOException {
		synchronized (labels) {
			Properties p = new Properties();
			p.load(stream);
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

				Map<String, Label> lbls = null;
				if (labels.containsKey(ns)) {
					lbls = labels.get(ns);
				} else {
					lbls = new HashMap<String, Label>();
					labels.put(ns, lbls);
				}

				if (lbls.containsKey(k)) {
					String msg = String.format("Duplicate　label key.[namespace=%s, key=%s]", ns, k);
					throw new ConfigurationFormatException(msg);
				}

				Label lbl = new Label(k, v);
				lbls.put(k, lbl);
			}
		}
	}

	/**
	 * ラベルを取得する。
	 * 
	 * @param ns 名前空間
	 * @param id ラベルID
	 * @return ラベル情報。ラベル情報が存在しない場合、<code>null</code>を返す。
	 */
	private Label doGetLabel(final String ns, final String id) {
		Label lbl = null;
		if (labels.containsKey(ns)) {
			Map<String, Label> ms = labels.get(ns);
			if (ms.containsKey(id)) {
				lbl = ms.get(id);
			}
		}
		return lbl;
	}
}
