package com.mes.code.server.serviceimpl.dao.mcs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mes.code.server.service.po.mcs.MCSDatabase;
import com.mes.code.server.service.po.mcs.MCSDatabaseInfo;
import com.mes.code.server.serviceimpl.dao.BaseDAO;

public class DatabaseDAO extends BaseDAO {

	private static Logger logger = LoggerFactory.getLogger(DatabaseDAO.class);

	private static DatabaseDAO Instance = null;

	private DatabaseDAO() {
		super();
	}

	public static DatabaseDAO getInstance() {
		if (Instance == null)
			Instance = new DatabaseDAO();
		return Instance;
	}

	public List<MCSDatabase> GetDatabaseList() {
		List<MCSDatabase> wResultList = new ArrayList<MCSDatabase>();
		try {
			MCSDatabaseInfo wDatabaseInfo = DatabaseInfoDAO.getInstance().GetDatabaseInfo();
			MCSDatabase wDatabase = new MCSDatabase();
			wDatabase.Name = wDatabaseInfo.Database;
			wDatabase.TableInfoList = TableInfoDAO.getInstance().GetTableInfoList(wDatabaseInfo);
			wResultList.add(wDatabase);
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResultList;
	}

	public List<MCSDatabase> GetDatabaseListWithoutSub() {
		List<MCSDatabase> wResultList = new ArrayList<MCSDatabase>();
		try {
			MCSDatabaseInfo wDatabaseInfo = DatabaseInfoDAO.getInstance().GetDatabaseInfo();

			String wSQL = "SHOW DATABASES;";
			Map<String, Object> wParamMap = new HashMap<String, Object>();

			wSQL = this.DMLChange(wSQL);

			List<Map<String, Object>> wQueryResult = nameJdbcTemplate.queryForList(wSQL, wParamMap);

			for (Map<String, Object> wReader : wQueryResult) {
				MCSDatabase wDatabase = new MCSDatabase();
				wDatabase.Name = wReader.get("Database").toString();
				switch (wDatabase.Name) {
				case "information_schema":
				case "mysql":
				case "performance_schema":
				case "sakila":
				case "sys":
				case "world":
				case "mds_ute":
				case "mesbasic":
				case "mesdfssheet":
				case "mtsbasic":
				case "mesform":
				case "mesinstance":
					continue;
				}
				wDatabaseInfo.Database = wDatabase.Name;
				wResultList.add(wDatabase);
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResultList;
	}
}
