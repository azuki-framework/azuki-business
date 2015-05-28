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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.azkfw.business.BusinessTestCase;
import org.junit.Test;

/**
 * @author Kawakicchi
 *
 */
public class LabelTest extends BusinessTestCase {

	@Test
	public void load() throws Exception {
		LabelManager.getInstance().initialize();
		LabelManager.getInstance().load("/label.properties", getTestContext());

		List<Object> list = new ArrayList<Object>();
		list.add("ID");
		list.add("なまえ");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "ID");
		map.put("name", "なまえ");

		Label label = null;

		label = LabelManager.getLabel("label01");
		assertNotNull(label);
		assertEquals("label01", label.getId());
		assertEquals("ラベル01", label.getLabel());

		label = LabelManager.getLabel("label02");
		assertNotNull(label);
		assertEquals("label02", label.getId());
		assertEquals("ラベル02", label.getLabel());

		label = LabelManager.getLabel("label03");
		assertNotNull(label);
		assertEquals("label03", label.getId());
		assertEquals("ラベル${1}", label.getLabel());
		assertEquals("ラベルID", label.generate("ID"));
		assertEquals("ラベルID", label.generate(list));

		label = LabelManager.getLabel("label04");
		assertNotNull(label);
		assertEquals("label04", label.getId());
		assertEquals("ラベル${1}-${2}", label.getLabel());
		assertEquals("ラベルID-なまえ", label.generate("ID", "なまえ"));
		assertEquals("ラベルID-なまえ", label.generate(list));

		label = LabelManager.getLabel("label05");
		assertNotNull(label);
		assertEquals("label05", label.getId());
		assertEquals("ラベル${id}-${name}", label.getLabel());
		assertEquals("ラベルID-なまえ", label.generate(map));
		
		
		LabelManager.getInstance().destroy();
	}
}
