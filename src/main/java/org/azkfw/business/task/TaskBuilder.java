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
package org.azkfw.business.task;

import org.azkfw.context.Context;
import org.azkfw.context.ContextSupport;
import org.azkfw.lang.LoggingObject;
import org.azkfw.persistence.parameter.Parameter;
import org.azkfw.persistence.parameter.ParameterSupport;
import org.azkfw.persistence.proterty.Property;
import org.azkfw.persistence.proterty.PropertyManager;
import org.azkfw.persistence.proterty.PropertySupport;

/**
 * このクラスは、タスクの作成を行うビルダークラスです。
 * 
 * @since 1.0.1
 * @version 1.0.1 2014/06/05
 * @author Kawakicchi
 */
public final class TaskBuilder extends LoggingObject {

	private Class<? extends Task> clazz;

	private Context context;

	private Parameter parameter;

	private Property property;

	public TaskBuilder(final Class<? extends Task> aClass) {
		super(TaskBuilder.class);
		clazz = aClass;
	}

	public void setContext(final Context aContext) {
		context = aContext;
	}

	public void setParameter(final Parameter aParameter) {
		parameter = aParameter;
	}

	public void setProperty(final Property aProperty) {
		property = aProperty;
	}

	public Task build() {
		Task task = null;

		try {
			Object o = clazz.newInstance();
			if (o instanceof Task) {
				task = (Task) o;

				// Context support
				if (task instanceof ContextSupport) {
					if (null != context) {
						((ContextSupport) task).setContext(context);
					} else {
						warn("Unset context.[" + clazz.getName() + "]");
					}
				}

				// Parameter support
				if (task instanceof ParameterSupport) {
					if (null != parameter) {
						((ParameterSupport) task).setParameter(parameter);
					} else {
						warn("Unset parameter.[" + clazz.getName() + "]");
					}
				}

				// Property support
				if (task instanceof PropertySupport) {
					Property p = null;
					if (null != property) {
						p = property;
					} else {
						p = PropertyManager.get(clazz);
						if (null == p) {
							if (null != context) {
								p = PropertyManager.load(clazz, context);
							} else {
								warn("Unset context.");
							}
						}
					}

					if (null != p) {
						((PropertySupport) task).setProperty(p);
					} else {
						warn("Unset property.[" + clazz.getName() + "]");
					}
				}
			}

		} catch (IllegalAccessException ex) {
			fatal(ex);
		} catch (InstantiationException ex) {
			fatal(ex);
		}

		return task;
	}

}
