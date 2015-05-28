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
package org.azkfw.business;

import java.io.InputStream;

import junit.framework.TestCase;

import org.azkfw.context.Context;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author Kawakicchi
 *
 */
@RunWith(JUnit4.class)
public abstract class BusinessTestCase extends TestCase {

	protected final Context getTestContext() {
		return new TestContext();
	}

	private class TestContext implements Context {

		@Override
		public String getAbstractPath(final String name) {
			return null;
		}

		@Override
		public InputStream getResourceAsStream(final String name) {
			return this.getClass().getResourceAsStream(name);
		}

	}
}
