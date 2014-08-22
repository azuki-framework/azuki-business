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
package org.azkfw.business.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.azkfw.context.Context;
import org.azkfw.lang.LoggingObject;
import org.azkfw.util.StringUtility;

/**
 * このクラスは、プロパティ管理を行うマネージャークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.1 2014/06/05
 * @author Kawakicchi
 */
public final class PropertyManager extends LoggingObject {

	/**
	 * Instance
	 */
	private static final PropertyManager INSTANCE = new PropertyManager();

	/**
	 * Properties
	 */
	private Map<Class<?>, Property> properties;

	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止する。
	 * </p>
	 */
	private PropertyManager() {
		super(PropertyManager.class);
		properties = new HashMap<Class<?>, Property>();
	}

	/**
	 * プロパティ情報を取得する。
	 * 
	 * @param aClass
	 *            クラス
	 * @return プロパティ情報。未読み込みの場合、<code>null</code>を返す。
	 */
	public static Property get(final Class<?> aClass) {
		return INSTANCE.doGet(aClass);
	}

	/**
	 * プロパティ情報をロードする。
	 * 
	 * @param aClass
	 *            クラス
	 * @param aContext
	 *            コンテキスト
	 * @return プロパティ情報
	 */
	public static Property load(final Class<?> aClass, final Context aContext) {
		return INSTANCE.doLoad(aClass, aContext);
	}

	private Property doGet(final Class<?> aClass) {
		return properties.get(aClass);
	}

	private synchronized Property doLoad(final Class<?> aClass,
			final Context aContext) {
		Property property = null;

		if (properties.containsKey(aClass)) {
			property = properties.get(aClass);
		} else {
			PropertyFile propertyFile = aClass
					.getAnnotation(PropertyFile.class);
			if (null != propertyFile) {
				String value = propertyFile.value();
				if (StringUtility.isNotEmpty(value)) {
					InputStream stream = aContext.getResourceAsStream(value);
					if (null != stream) {
						try {
							Properties p = new Properties();
							p.load(stream);
							property = Property.Builder.build(p);
						} catch (IOException ex) {
							error("Property file read error.[" + value + "]");
							error(ex);
							property = Property.Builder.build();
						}
					} else {
						error("Not found property file.[" + value + "]");
						property = Property.Builder.build();
					}
				} else {
					property = Property.Builder.build();
				}
			}

			properties.put(aClass, property);
		}

		return property;
	}
}
