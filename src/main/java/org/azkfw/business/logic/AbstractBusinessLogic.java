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
package org.azkfw.business.logic;

import java.util.Map;

import org.azkfw.business.message.Message;
import org.azkfw.business.message.MessageManager;

/**
 * @since 1.0.0
 * @version 1.0.0 2015/02/08
 * @author Kawakicchi
 */
public abstract class AbstractBusinessLogic extends AbstractDynamicSQLLogic {

	/**
	 * コンストラクタ
	 */
	public AbstractBusinessLogic() {
		super();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aName 名前
	 */
	public AbstractBusinessLogic(final String aName) {
		super(aName);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aClass クラス
	 */
	public AbstractBusinessLogic(final Class<?> aClass) {
		super(aClass);
	}

	protected String getMessage(final String id) {
		Message msg = MessageManager.getMessage(id);
		return msg.generate(id);
	}

	protected final String getMessage(final String id, final Map<String, Object> params) {
		Message msg = MessageManager.getMessage(id);
		return msg.generate(params);
	}
}
