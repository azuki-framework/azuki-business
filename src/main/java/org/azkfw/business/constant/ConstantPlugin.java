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

import org.azkfw.business.BusinessServiceException;
import org.azkfw.configuration.ConfigurationFormatException;
import org.azkfw.plugin.AbstractPlugin;
import org.azkfw.plugin.PluginServiceException;

/**
 * このクラスは、定数機能をサポートするためのプラグインクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2015/02/18
 * @author Kawakicchi
 */
public class ConstantPlugin extends AbstractPlugin {

	/**
	 * コンストラクタ
	 */
	public ConstantPlugin() {
		super(ConstantPlugin.class);
	}

	@Override
	protected void doInitialize() throws PluginServiceException {
		ConstantManager.initialize();
	}

	@Override
	protected void doDestroy() throws PluginServiceException {
		ConstantManager.destroy();
	}

	@Override
	protected void doLoad() throws PluginServiceException, ConfigurationFormatException, IOException {
		try {
			ConstantManager.load(getConfiguration().getResourceAsStream(), getContext());
		} catch (BusinessServiceException ex) {
			throw new PluginServiceException(ex);
		}
	}
}
