package com.mes.code.server.serviceimpl.dao.mcs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mes.code.server.service.po.mcs.MCSDatabaseInfo;
import com.mes.code.server.service.po.mcs.MCSTableInfo;
import com.mes.code.server.serviceimpl.dao.BaseDAO;

public class TableInfoDAO extends BaseDAO {
	private static Logger logger = LoggerFactory.getLogger(TableInfoDAO.class);

	private static TableInfoDAO Instance = null;

	private TableInfoDAO() {
		super();
	}

	public static TableInfoDAO getInstance() {
		if (Instance == null)
			Instance = new TableInfoDAO();
		return Instance;
	}

	public List<MCSTableInfo> GetTableInfoList(MCSDatabaseInfo wDatabaseInfo) {
		List<MCSTableInfo> wResultList = new ArrayList<MCSTableInfo>();
		try {
			String wSQL = "SHOW TABLE STATUS;";
			Map<String, Object> wParamMap = new HashMap<String, Object>();

			wSQL = this.DMLChange(wSQL);

			List<Map<String, Object>> wQueryResult = nameJdbcTemplate.queryForList(wSQL, wParamMap);

			for (Map<String, Object> wReader : wQueryResult) {
				MCSTableInfo wTableInfo = new MCSTableInfo();
				wTableInfo.TableName = wReader.get("Name").toString();
				wTableInfo.ColumnInfoList = ColumnInfoDAO.getInstance().GetColumnInfoList(wDatabaseInfo,
						wTableInfo.TableName);
				wResultList.add(wTableInfo);
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResultList;
	}
}
