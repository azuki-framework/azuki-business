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

import org.azkfw.business.BusinessServiceException;
import org.azkfw.configuration.ConfigurationFormatException;
import org.azkfw.plugin.AbstractPlugin;
import org.azkfw.plugin.PluginServiceException;

/**
 * このクラスは、ラベル機能をサポートするためのプラグインクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/07/02
 * @author Kawakicchi
 */
public final class LabelPlugin extends AbstractPlugin {

	/**
	 * コンストラクタ
	 */
	public LabelPlugin() {
		super(LabelPlugin.class);
	}

	@Override
	protected void doInitialize() throws PluginServiceException {
		LabelManager.getInstance().initialize();
	}

	@Override
	protected void doDestroy() throws PluginServiceException {
		LabelManager.getInstance().destroy();
	}

	@Override
	protected void doLoad() throws PluginServiceException, ConfigurationFormatException, IOException {
		try {
			LabelManager.getInstance().load(getConfiguration().getResourceAsStream(), getContext());
		} catch (BusinessServiceException ex) {
			throw new PluginServiceException(ex);
		}
	}
}
