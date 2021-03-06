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
package org.azkfw.business.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.azkfw.business.paging.Paging;
import org.azkfw.dsql.DynamicSQL;
import org.azkfw.util.StringUtility;

/**
 * このクラスは、ダイナミックSQLでのデータアクセス機能を実装したDAOクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/02/14
 * @author Kawakicchi
 */
public class DynamicSQLAccessObject extends AbstractDatabaseAccessObject {

	/**
	 * DynamicSQL情報
	 */
	private DynamicSQL dsql;

	/**
	 * コンストラクタ
	 * 
	 * @param dynamicSQL DynamicSQL情報
	 */
	public DynamicSQLAccessObject(final DynamicSQL dynamicSQL) {
		super(DynamicSQLAccessObject.class);
		dsql = dynamicSQL;
	}

	@Override
	protected boolean doExecute() throws DataAccessServiceException {
		boolean result = false;

		PreparedStatement stat = null;
		try {
			stat = getConnection().getConnection().prepareStatement(dsql.getExecuteSQL());
			List<Object> parameters = dsql.getParameters();
			if (null != parameters) {
				for (int i = 0; i < parameters.size(); i++) {
					stat.setObject(i + 1, parameters.get(i));
				}
			}

			result = stat.execute();
		} catch (SQLException ex) {
			fatal(String.format("%s : %s", dsql.getName(), dsql.getExecuteSQL()), ex);
			throw new DataAccessServiceException(ex);
		} finally {
			release(stat);
		}

		return result;
	}

	@Override
	protected int doUpdate() throws DataAccessServiceException {
		int result = -1;

		PreparedStatement stat = null;
		try {
			stat = getConnection().getConnection().prepareStatement(dsql.getExecuteSQL());
			List<Object> parameters = dsql.getParameters();
			if (null != parameters) {
				for (int i = 0; i < parameters.size(); i++) {
					stat.setObject(i + 1, parameters.get(i));
				}
			}

			result = stat.executeUpdate();
		} catch (SQLException ex) {
			fatal(String.format("%s : %s", dsql.getName(), dsql.getExecuteSQL()), ex);
			throw new DataAccessServiceException(ex);
		} finally {
			release(stat);
		}

		return result;
	}

	@Override
	protected long doCount() throws DataAccessServiceException {
		long result = -1;

		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = getConnection().getConnection().prepareStatement(dsql.getExecuteSQL());
			List<Object> parameters = dsql.getParameters();
			if (null != parameters) {
				for (int i = 0; i < parameters.size(); i++) {
					stat.setObject(i + 1, parameters.get(i));
				}
			}

			rs = stat.executeQuery();

			if (rs.next()) {
				result = rs.getLong(1);
			}
		} catch (SQLException ex) {
			fatal(String.format("%s : %s", dsql.getName(), dsql.getExecuteSQL()), ex);
			throw new DataAccessServiceException(ex);
		} finally {
			release(rs);
			release(stat);
		}

		return result;
	}

	@Override
	protected Map<String, Object> doGet() throws DataAccessServiceException {
		Map<String, Object> result = new HashMap<String, Object>();

		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = getConnection().getConnection().prepareStatement(dsql.getExecuteSQL());
			List<Object> parameters = dsql.getParameters();
			if (null != parameters) {
				for (int i = 0; i < parameters.size(); i++) {
					stat.setObject(i + 1, parameters.get(i));
				}
			}

			rs = stat.executeQuery();

			ResultSetMetaData md = rs.getMetaData();
			List<String> columns = new ArrayList<String>();
			List<String> keys = new ArrayList<String>();
			for (int i = 0; i < md.getColumnCount(); i++) {
				String column = md.getColumnName(i + 1);
				columns.add(column);
				keys.add(StringUtility.toCamelcase(column));
			}

			if (rs.next()) {
				for (int i = 0; i < columns.size(); i++) {
					result.put(keys.get(i), rs.getObject(columns.get(i)));
				}
			}
		} catch (SQLException ex) {
			fatal(String.format("%s : %s", dsql.getName(), dsql.getExecuteSQL()), ex);
			throw new DataAccessServiceException(ex);
		} finally {
			release(rs);
			release(stat);
		}

		return result;
	}

	@Override
	protected List<Map<String, Object>> doQuery() throws DataAccessServiceException {
		return doQuery(null);
	}

	@Override
	protected List<Map<String, Object>> doQuery(final Paging paging) throws DataAccessServiceException {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = getConnection().getConnection().prepareStatement(dsql.getExecuteSQL());
			List<Object> parameters = dsql.getParameters();
			if (null != parameters) {
				for (int i = 0; i < parameters.size(); i++) {
					stat.setObject(i + 1, parameters.get(i));
				}
			}

			rs = stat.executeQuery();

			ResultSetMetaData md = rs.getMetaData();
			List<String> columns = new ArrayList<String>();
			List<String> keys = new ArrayList<String>();
			for (int i = 0; i < md.getColumnCount(); i++) {
				String column = md.getColumnName(i + 1);
				columns.add(column);
				keys.add(StringUtility.toCamelcase(column));
			}

			// TODO ページング対応
			if (null != paging) {
				if (StringUtility.isNotEmpty(paging.getKey())) {
					if (StringUtility.isNotEmpty(paging.getSinceId())) {
						throw new DataAccessServiceException("Unsupported since pageing.");
					} else if (StringUtility.isNotEmpty(paging.getMaxId())) {
						throw new DataAccessServiceException("Unsupported max pageing.");
					} else {
						throw new DataAccessServiceException("Unsupported pageing setting.");
					}
				} else {
					long start = paging.getPage() * paging.getSize();
					long end = (paging.getPage() + 1) * paging.getSize();
					long count = 0;
					while (rs.next()) {
						if (count >= end) {
							break;
						} else if (count >= start) {
							Map<String, Object> data = new HashMap<String, Object>();
							for (int i = 0; i < columns.size(); i++) {
								data.put(keys.get(i), rs.getObject(columns.get(i)));
							}
							result.add(data);
						}
						count++;
					}
				}
			} else {
				while (rs.next()) {
					Map<String, Object> data = new HashMap<String, Object>();
					for (int i = 0; i < columns.size(); i++) {
						data.put(keys.get(i), rs.getObject(columns.get(i)));
					}
					result.add(data);
				}
			}
		} catch (SQLException ex) {
			fatal(String.format("%s : %s", dsql.getName(), dsql.getExecuteSQL()), ex);
			throw new DataAccessServiceException(ex);
		} finally {
			release(rs);
			release(stat);
		}

		return result;
	}

}
