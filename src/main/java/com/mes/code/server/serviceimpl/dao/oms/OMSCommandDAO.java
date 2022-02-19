package com.mes.code.server.serviceimpl.dao.oms;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.mes.code.server.service.mesenum.MESDBSource;
import com.mes.code.server.service.mesenum.MESException;
import com.mes.code.server.service.po.OutResult;
import com.mes.code.server.service.po.ServiceResult;
import com.mes.code.server.service.po.bms.BMSEmployee;
import com.mes.code.server.service.po.oms.OMSCommand;
import com.mes.code.server.service.utils.StringUtils;
import com.mes.code.server.serviceimpl.dao.BaseDAO;

public class OMSCommandDAO extends BaseDAO {

	private static Logger logger = LoggerFactory.getLogger(OMSCommandDAO.class);

	private static OMSCommandDAO Instance = null;

	/**
	 * 添加或修改
	 * 
	 * @param wOMSCommand
	 * @return
	 */
	public long Update(BMSEmployee wLoginUser, OMSCommand wOMSCommand, OutResult<Integer> wErrorCode) {
		int wResult = 0;
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return wResult;
			}

			if (wOMSCommand == null)
				return 0;

			String wSQL = "";
			if (wOMSCommand.getID() <= 0) {
				wSQL = MessageFormat.format("INSERT INTO {0}.oms_command(CustomerID,ContactCode,No,Status,"
						+ "LinkManID,EditorID,EditTime,Active,AuditorID,AuditTime,"
						+ "CreatorID,CreateTime,FactoryID,BusinessUnitID,FQTYPlan,"
						+ "FQTYActual) VALUES(:CustomerID,:ContactCode,:No,:Status,"
						+ ":LinkManID,:EditorID,:EditTime,:Active,:AuditorID,"
						+ ":AuditTime,:CreatorID,:CreateTime,:FactoryID,:BusinessUnitID," + ":FQTYPlan,:FQTYActual);",
						wInstance.Result);
			} else {
				wSQL = MessageFormat.format("UPDATE {0}.oms_command SET CustomerID = :CustomerID,"
						+ "ContactCode = :ContactCode,No = :No,Status = :Status,"
						+ "LinkManID = :LinkManID,EditorID = :EditorID,EditTime = :EditTime,"
						+ "Active = :Active,AuditorID = :AuditorID,AuditTime = :AuditTime,"
						+ "CreatorID = :CreatorID,CreateTime = :CreateTime,"
						+ "FactoryID = :FactoryID,BusinessUnitID = :BusinessUnitID,"
						+ "FQTYPlan = :FQTYPlan,FQTYActual = :FQTYActual WHERE ID = :ID;", wInstance.Result);
			}

			wSQL = this.DMLChange(wSQL);

			Map<String, Object> wParamMap = new HashMap<String, Object>();

			wParamMap.put("ID", wOMSCommand.ID);
			wParamMap.put("CustomerID", wOMSCommand.CustomerID);
			wParamMap.put("ContactCode", wOMSCommand.ContactCode);
			wParamMap.put("No", wOMSCommand.No);
			wParamMap.put("Status", wOMSCommand.Status);
			wParamMap.put("LinkManID", wOMSCommand.LinkManID);
			wParamMap.put("EditorID", wOMSCommand.EditorID);
			wParamMap.put("EditTime", wOMSCommand.EditTime);
			wParamMap.put("Active", wOMSCommand.Active);
			wParamMap.put("AuditorID", wOMSCommand.AuditorID);
			wParamMap.put("AuditTime", wOMSCommand.AuditTime);
			wParamMap.put("CreatorID", wOMSCommand.CreatorID);
			wParamMap.put("CreateTime", wOMSCommand.CreateTime);
			wParamMap.put("FactoryID", wOMSCommand.FactoryID);
			wParamMap.put("BusinessUnitID", wOMSCommand.BusinessUnitID);
			wParamMap.put("FQTYPlan", wOMSCommand.FQTYPlan);
			wParamMap.put("FQTYActual", wOMSCommand.FQTYActual);

			KeyHolder keyHolder = new GeneratedKeyHolder();
			SqlParameterSource wSqlParameterSource = new MapSqlParameterSource(wParamMap);

			nameJdbcTemplate.update(wSQL, wSqlParameterSource, keyHolder);

			if (wOMSCommand.getID() <= 0) {
				wResult = keyHolder.getKey().intValue();
				wOMSCommand.setID(wResult);
			} else {
				wResult = wOMSCommand.getID();
			}
		} catch (Exception ex) {
			wErrorCode.set(MESException.DBSQL.getValue());
			logger.error(ex.toString());
		}
		return wResult;
	}

	/**
	 * 删除集合
	 * 
	 * @param wList
	 */
	public ServiceResult<Integer> DeleteList(BMSEmployee wLoginUser, List<OMSCommand> wList,
			OutResult<Integer> wErrorCode) {
		ServiceResult<Integer> wResult = new ServiceResult<Integer>(0);
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return wResult;
			}

			if (wList == null || wList.size() <= 0)
				return wResult;

			List<String> wIDList = new ArrayList<String>();
			for (OMSCommand wItem : wList) {
				wIDList.add(String.valueOf(wItem.ID));
			}
			String wSql = MessageFormat.format("delete from {1}.oms_command WHERE ID IN({0}) ;",
					String.join(",", wIDList), wInstance.Result);
			this.ExecuteSqlTransaction(wSql);
		} catch (Exception ex) {
			wErrorCode.set(MESException.DBSQL.getValue());
			logger.error(ex.toString());
		}
		return wResult;
	}

	/**
	 * 查单条
	 * 
	 * @return
	 */
	public OMSCommand SelectByID(BMSEmployee wLoginUser, int wID, OutResult<Integer> wErrorCode) {
		OMSCommand wResult = new OMSCommand();
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return wResult;
			}

			List<OMSCommand> wList = SelectList(wLoginUser, wID, -1, -1, -1, wErrorCode);
			if (wList == null || wList.size() != 1)
				return wResult;
			wResult = wList.get(0);
		} catch (Exception e) {
			wErrorCode.set(MESException.DBSQL.getValue());
			logger.error(e.toString());
		}
		return wResult;
	}

	/**
	 * 条件查询集合
	 * 
	 * @return
	 */
	public List<OMSCommand> SelectList(BMSEmployee wLoginUser, int wID, int wActive, int wFactoryID,
			int wBusinessUnitID, OutResult<Integer> wErrorCode) {
		List<OMSCommand> wResultList = new ArrayList<OMSCommand>();
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return wResultList;
			}

			String wSQL = MessageFormat.format("SELECT * FROM {0}.oms_command WHERE  1=1  "
					+ "and ( :wID <= 0 or :wID = ID ) " + "and ( :wFactoryID <= 0 or :wFactoryID = FactoryID ) "
					+ "and ( :wBusinessUnitID <= 0 or :wBusinessUnitID = BusinessUnitID );", wInstance.Result);

			Map<String, Object> wParamMap = new HashMap<String, Object>();

			wParamMap.put("wID", wID);
			wParamMap.put("wActive", wActive);
			wParamMap.put("wFactoryID", wFactoryID);
			wParamMap.put("wBusinessUnitID", wBusinessUnitID);

			wSQL = this.DMLChange(wSQL);

			List<Map<String, Object>> wQueryResult = nameJdbcTemplate.queryForList(wSQL, wParamMap);

			for (Map<String, Object> wReader : wQueryResult) {
				OMSCommand wItem = new OMSCommand();

				wItem.ID = StringUtils.parseInt(wReader.get("ID"));
				wItem.CustomerID = StringUtils.parseInt(wReader.get("CustomerID"));
				wItem.ContactCode = StringUtils.parseString(wReader.get("ContactCode"));
				wItem.No = StringUtils.parseString(wReader.get("No"));
				wItem.Status = StringUtils.parseInt(wReader.get("Status"));
				wItem.LinkManID = StringUtils.parseInt(wReader.get("LinkManID"));
				wItem.EditorID = StringUtils.parseInt(wReader.get("EditorID"));
				wItem.EditTime = StringUtils.parseCalendar(wReader.get("EditTime"));
				wItem.Active = StringUtils.parseInt(wReader.get("Active"));
				wItem.AuditorID = StringUtils.parseInt(wReader.get("AuditorID"));
				wItem.AuditTime = StringUtils.parseCalendar(wReader.get("AuditTime"));
				wItem.CreatorID = StringUtils.parseInt(wReader.get("CreatorID"));
				wItem.CreateTime = StringUtils.parseCalendar(wReader.get("CreateTime"));
				wItem.FactoryID = StringUtils.parseInt(wReader.get("FactoryID"));
				wItem.BusinessUnitID = StringUtils.parseInt(wReader.get("BusinessUnitID"));
				wItem.FQTYPlan = StringUtils.parseInt(wReader.get("FQTYPlan"));
				wItem.FQTYActual = StringUtils.parseInt(wReader.get("FQTYActual"));

				wResultList.add(wItem);
			}
		} catch (Exception ex) {
			wErrorCode.set(MESException.DBSQL.getValue());
			logger.error(ex.toString());
		}
		return wResultList;
	}

	/**
	 * 批量激活或禁用
	 */
	public ServiceResult<Integer> Active(BMSEmployee wLoginUser, List<Integer> wIDList, int wActive,
			OutResult<Integer> wErrorCode) {
		ServiceResult<Integer> wResult = new ServiceResult<Integer>(0);
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return wResult;
			}

			if (wIDList == null || wIDList.size() <= 0)
				return wResult;
			if (wActive != 0 && wActive != 1)
				return wResult;
			for (Integer wItem : wIDList) {
				OMSCommand wOMSCommand = SelectByID(wLoginUser, wItem, wErrorCode);
				if (wOMSCommand == null || wOMSCommand.ID <= 0)
					continue;
				wOMSCommand.Active = wActive;
				long wID = Update(wLoginUser, wOMSCommand, wErrorCode);
				if (wID <= 0)
					break;
			}
		} catch (Exception e) {
			wErrorCode.set(MESException.DBSQL.getValue());
			logger.error(e.toString());
		}
		return wResult;
	}

	private OMSCommandDAO() {
		super();
	}

	public static OMSCommandDAO getInstance() {
		if (Instance == null)
			Instance = new OMSCommandDAO();
		return Instance;
	}
}
