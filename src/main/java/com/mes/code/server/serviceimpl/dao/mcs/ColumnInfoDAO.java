package com.mes.code.server.serviceimpl.dao.mcs;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mes.code.server.service.po.mcs.MCSColumnInfo;
import com.mes.code.server.service.po.mcs.MCSDatabaseInfo;
import com.mes.code.server.serviceimpl.dao.BaseDAO;

public class ColumnInfoDAO extends BaseDAO {
	private static Logger logger = LoggerFactory.getLogger(ColumnInfoDAO.class);

	private static ColumnInfoDAO Instance = null;

	private ColumnInfoDAO() {
		super();
	}

	public static ColumnInfoDAO getInstance() {
		if (Instance == null)
			Instance = new ColumnInfoDAO();
		return Instance;
	}

	public List<MCSColumnInfo> GetColumnInfoList(MCSDatabaseInfo wDatabaseInfo, String wTableName) {
		List<MCSColumnInfo> wResultList = new ArrayList<MCSColumnInfo>();
		try {
			String wSQL = MessageFormat.format("SHOW COLUMNS FROM {1}.{0};", wTableName, wDatabaseInfo.Database);

			Map<String, Object> wParamMap = new HashMap<String, Object>();

			wSQL = this.DMLChange(wSQL);

			List<Map<String, Object>> wQueryResult = nameJdbcTemplate.queryForList(wSQL, wParamMap);

			for (Map<String, Object> wReader : wQueryResult) {
				MCSColumnInfo wColumnInfo = new MCSColumnInfo();
				wColumnInfo.Name = wReader.get("Field").toString();
				wColumnInfo.DatabaseType = wReader.get("Type").toString().replaceAll("\\(.*\\)", "");
				if (wReader.get("Key").toString().equals("PRI"))
					wColumnInfo.IsPrimaryKey = true;
				else
					wColumnInfo.IsPrimaryKey = false;
				switch (wColumnInfo.DatabaseType) {
				case "int":
				case "smallint":
					wColumnInfo.ClassType = "int";
					break;
				case "double":
					wColumnInfo.ClassType = "double";
					break;
				case "char":
				case "varchar":
				case "longtext":
					wColumnInfo.ClassType = "String";
					break;
				case "tinyint":
					wColumnInfo.ClassType = "boolean";
					break;
				case "datetime":
					wColumnInfo.ClassType = "Calendar";
					break;
				case "enum":
					wColumnInfo.ClassType = "Enum";
					break;
				case "float":
					wColumnInfo.ClassType = "float";
					break;
				case "bigint":
				case "bigint unsigned":
					wColumnInfo.ClassType = "long";
					break;
				}
				wResultList.add(wColumnInfo);
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResultList;
	}
}
