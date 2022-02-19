package com.mes.code.server.serviceimpl.dao.mcs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mes.code.server.service.po.mcs.MCSDatabase;
import com.mes.code.server.service.po.mcs.MCSDatabaseInfo;
import com.mes.code.server.service.utils.XmlTool;
import com.mes.code.server.serviceimpl.dao.BaseDAO;
import com.mes.code.server.utils.Configuration;

public class DatabaseInfoDAO extends BaseDAO {
	private static Logger logger = LoggerFactory.getLogger(DatabaseInfoDAO.class);

	private static DatabaseInfoDAO Instance = null;

	private DatabaseInfoDAO() {
		super();
	}

	public static DatabaseInfoDAO getInstance() {
		if (Instance == null)
			Instance = new DatabaseInfoDAO();
		return Instance;
	}

	public void CreateDatabaseInfoConfig() {
		try {
			MCSDatabaseInfo wDatabaseInfo = new MCSDatabaseInfo();
			wDatabaseInfo.Server = "localhost";
			wDatabaseInfo.Database = "sop_db";
			wDatabaseInfo.User = "root";
			wDatabaseInfo.Password = "123456";
			XmlTool.SaveXml("", wDatabaseInfo);
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
	}

	public MCSDatabaseInfo GetDatabaseInfo() {
		MCSDatabaseInfo wResult = new MCSDatabaseInfo();
		try {
			String wInfo = Configuration.readConfigString("DatabaseInfo", "config/config");
			String[] wStrs = wInfo.split(";");
			wResult.Server = wStrs[0];
			wResult.Database = wStrs[1];
			wResult.User = wStrs[2];
			wResult.Password = wStrs[3];
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	public MCSDatabaseInfo GetDatabaseInfoByDatabase(MCSDatabase wDatabase) {
		MCSDatabaseInfo wResult = new MCSDatabaseInfo();
		try {
			wResult = GetDatabaseInfo();
			wResult.Database = wDatabase.Name;
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}
}