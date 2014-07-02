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

import org.azkfw.lang.PrimitiveException;

/**
 * このクラスは、ビジネスサービス層に起因する例外クラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2012/12/30
 * @author Kawakicchi
 */
public class BusinessServiceException extends PrimitiveException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3052128492508534650L;

	/**
	 * コンストラクタ
	 */
	public BusinessServiceException() {
		super();
	}

	/**
	 * コンストラクタ
	 * 
	 * @param message Message
	 */
	public BusinessServiceException(final String message) {
		super(message);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param throwable Throwable
	 */
	public BusinessServiceException(final Throwable throwable) {
		super(throwable);
	}

	/**
	 * コンストラクタ
	 * 
	 * @param message Message
	 * @param throwable Throwable
	 */
	public BusinessServiceException(final String message, final Throwable throwable) {
		super(message, throwable);
	}
}
