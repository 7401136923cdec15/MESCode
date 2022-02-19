package com.mes.code.server.serviceimpl.dao.aps;

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
import com.mes.code.server.service.po.aps.APSTaskStep;
import com.mes.code.server.service.po.bms.BMSEmployee;
import com.mes.code.server.service.utils.StringUtils;
import com.mes.code.server.serviceimpl.dao.BaseDAO;

public class APSTaskStepDAO extends BaseDAO {

	private static Logger logger = LoggerFactory.getLogger(APSTaskStepDAO.class);

	private static APSTaskStepDAO Instance = null;

	/**
	 * 添加或修改
	 * 
	 * @param wAPSTaskStep
	 * @return
	 */
	public int Update(BMSEmployee wLoginUser, APSTaskStep wAPSTaskStep, OutResult<Integer> wErrorCode) {
		int wResult = 0;
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return wResult;
			}

			if (wAPSTaskStep == null)
				return 0;

			String wSQL = "";
			if (wAPSTaskStep.getID() <= 0) {
				wSQL = MessageFormat.format("INSERT INTO {0}.aps_taskstep(OrderID,PartNo,TaskLineID,"
						+ "TaskPartID,WorkShopID,LineID,PartID,StepID,ShiftID,"
						+ "StartTime,EndTime,ReadyTime,Status,Active,ProductNo,MaterialNo,"
						+ "PlannerID,TaskText,WorkHour) VALUES(:OrderID,:PartNo,"
						+ ":TaskLineID,:TaskPartID,:WorkShopID,:LineID,:PartID,"
						+ ":StepID,:ShiftID,:StartTime,:EndTime,:ReadyTime,:Status,:Active,"
						+ ":ProductNo,:MaterialNo,:PlannerID,:TaskText,:WorkHour);", wInstance.Result);
			} else {
				wSQL = MessageFormat.format("UPDATE {0}.aps_taskstep SET OrderID = :OrderID,"
						+ "PartNo = :PartNo,TaskLineID = :TaskLineID,"
						+ "TaskPartID = :TaskPartID,WorkShopID = :WorkShopID,"
						+ "LineID = :LineID,PartID = :PartID,StepID = :StepID,"
						+ "ShiftID = :ShiftID,StartTime = :StartTime,EndTime = :EndTime,ReadyTime=:ReadyTime,"
						+ "Status = :Status,Active = :Active,ProductNo = :ProductNo,"
						+ "MaterialNo = :MaterialNo,PlannerID = :PlannerID,TaskText = :TaskText,WorkHour = :WorkHour WHERE ID = :ID;",
						wInstance.Result);
			}

			wSQL = this.DMLChange(wSQL);

			Map<String, Object> wParamMap = new HashMap<String, Object>();

			wParamMap.put("ID", wAPSTaskStep.ID);
			wParamMap.put("OrderID", wAPSTaskStep.OrderID);
			wParamMap.put("PartNo", wAPSTaskStep.PartNo);
			wParamMap.put("TaskLineID", wAPSTaskStep.TaskLineID);
			wParamMap.put("TaskPartID", wAPSTaskStep.TaskPartID);
			wParamMap.put("WorkShopID", wAPSTaskStep.WorkShopID);
			wParamMap.put("LineID", wAPSTaskStep.LineID);
			wParamMap.put("PartID", wAPSTaskStep.PartID);
			wParamMap.put("StepID", wAPSTaskStep.StepID);
			wParamMap.put("ShiftID", wAPSTaskStep.ShiftID);
			wParamMap.put("StartTime", wAPSTaskStep.StartTime);
			wParamMap.put("EndTime", wAPSTaskStep.EndTime);
			wParamMap.put("ReadyTime", wAPSTaskStep.ReadyTime);
			wParamMap.put("Status", wAPSTaskStep.Status);
			wParamMap.put("Active", wAPSTaskStep.Active);
			wParamMap.put("ProductNo", wAPSTaskStep.ProductNo);
			wParamMap.put("MaterialNo", wAPSTaskStep.MaterialNo);
			wParamMap.put("PlannerID", wAPSTaskStep.PlanerID);
			wParamMap.put("TaskText", wAPSTaskStep.TaskText);
			wParamMap.put("WorkHour", wAPSTaskStep.WorkHour);

			KeyHolder keyHolder = new GeneratedKeyHolder();
			SqlParameterSource wSqlParameterSource = new MapSqlParameterSource(wParamMap);

			nameJdbcTemplate.update(wSQL, wSqlParameterSource, keyHolder);

			if (wAPSTaskStep.getID() <= 0) {
				wResult = keyHolder.getKey().intValue();
				wAPSTaskStep.setID(wResult);
			} else {
				wResult = wAPSTaskStep.getID();
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
	public ServiceResult<Integer> DeleteList(BMSEmployee wLoginUser, List<APSTaskStep> wList,
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
			for (APSTaskStep wItem : wList) {
				wIDList.add(String.valueOf(wItem.ID));
			}
			String wSql = MessageFormat.format("delete from {1}.aps_taskstep WHERE ID IN({0}) ;",
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
	public APSTaskStep SelectByID(BMSEmployee wLoginUser, int wID, OutResult<Integer> wErrorCode) {
		APSTaskStep wResult = new APSTaskStep();
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return wResult;
			}

			List<APSTaskStep> wList = SelectList(wLoginUser, wID, -1, -1, -1, -1, -1, -1, -1, -1, -1, null, wErrorCode);
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
	public List<APSTaskStep> SelectList(BMSEmployee wLoginUser, int wID, int wOrderID, int wTaskLineID, int wTaskPartID,
			int wWorkShopID, int wLineID, int wPartID, int wStepID, int wShiftID, int wActive,
			List<Integer> wStateIDList, OutResult<Integer> wErrorCode) {
		List<APSTaskStep> wResultList = new ArrayList<APSTaskStep>();
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return wResultList;
			}

			if (wStateIDList == null)
				wStateIDList = new ArrayList<Integer>();

			String wSQL = MessageFormat.format("SELECT * FROM {0}.aps_taskstep WHERE  1=1  "
					+ "and ( :wID <= 0 or :wID = ID ) " + "and ( :wOrderID <= 0 or :wOrderID = OrderID ) "
					+ "and ( :wTaskLineID <= 0 or :wTaskLineID = TaskLineID ) "
					+ "and ( :wTaskPartID <= 0 or :wTaskPartID = TaskPartID ) "
					+ "and ( :wWorkShopID <= 0 or :wWorkShopID = WorkShopID ) "
					+ "and ( :wLineID <= 0 or :wLineID = LineID ) " + "and ( :wPartID <= 0 or :wPartID = PartID ) "
					+ "and ( :wStepID <= 0 or :wStepID = StepID ) " + "and ( :wShiftID <= 0 or :wShiftID = ShiftID ) "
					+ "and ( :wActive <= 0 or :wActive = Active ) "
					+ "and ( :wStatus is null or :wStatus = '''' or Status in ({1}));", wInstance.Result,
					wStateIDList.size() > 0 ? StringUtils.Join(",", wStateIDList) : "0");

			Map<String, Object> wParamMap = new HashMap<String, Object>();

			wParamMap.put("wID", wID);
			wParamMap.put("wOrderID", wOrderID);
			wParamMap.put("wTaskLineID", wTaskLineID);
			wParamMap.put("wTaskPartID", wTaskPartID);
			wParamMap.put("wWorkShopID", wWorkShopID);
			wParamMap.put("wLineID", wLineID);
			wParamMap.put("wPartID", wPartID);
			wParamMap.put("wStepID", wStepID);
			wParamMap.put("wShiftID", wShiftID);
			wParamMap.put("wActive", wActive);
			wParamMap.put("wStatus", StringUtils.Join(",", wStateIDList));

			wSQL = this.DMLChange(wSQL);

			List<Map<String, Object>> wQueryResult = nameJdbcTemplate.queryForList(wSQL, wParamMap);

			for (Map<String, Object> wReader : wQueryResult) {
				APSTaskStep wItem = new APSTaskStep();

				wItem.ID = StringUtils.parseInt(wReader.get("ID"));
				wItem.OrderID = StringUtils.parseInt(wReader.get("OrderID"));
				wItem.PartNo = StringUtils.parseString(wReader.get("PartNo"));
				wItem.TaskLineID = StringUtils.parseInt(wReader.get("TaskLineID"));
				wItem.TaskPartID = StringUtils.parseInt(wReader.get("TaskPartID"));
				wItem.WorkShopID = StringUtils.parseInt(wReader.get("WorkShopID"));
				wItem.LineID = StringUtils.parseInt(wReader.get("LineID"));
				wItem.PartID = StringUtils.parseInt(wReader.get("PartID"));
				wItem.StepID = StringUtils.parseInt(wReader.get("StepID"));
				wItem.ShiftID = StringUtils.parseInt(wReader.get("ShiftID"));
				wItem.StartTime = StringUtils.parseCalendar(wReader.get("StartTime"));
				wItem.EndTime = StringUtils.parseCalendar(wReader.get("EndTime"));
				wItem.ReadyTime = StringUtils.parseCalendar(wReader.get("ReadyTime"));
				wItem.Status = StringUtils.parseInt(wReader.get("Status"));
				wItem.Active = StringUtils.parseInt(wReader.get("Active"));
				wItem.ProductNo = StringUtils.parseString(wReader.get("ProductNo"));
				wItem.MaterialNo = StringUtils.parseString(wReader.get("MaterialNo"));
				wItem.PlanerID = StringUtils.parseInt(wReader.get("PlannerID"));
				wItem.TaskText = StringUtils.parseString(wReader.get("TaskText"));
				wItem.WorkHour = StringUtils.parseInt(wReader.get("WorkHour"));

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
				APSTaskStep wAPSTaskStep = SelectByID(wLoginUser, wItem, wErrorCode);
				if (wAPSTaskStep == null || wAPSTaskStep.ID <= 0)
					continue;
				wAPSTaskStep.Active = wActive;
				long wID = Update(wLoginUser, wAPSTaskStep, wErrorCode);
				if (wID <= 0)
					break;
			}
		} catch (Exception e) {
			wErrorCode.set(MESException.DBSQL.getValue());
			logger.error(e.toString());
		}
		return wResult;
	}

	private APSTaskStepDAO() {
		super();
	}

	public static APSTaskStepDAO getInstance() {
		if (Instance == null)
			Instance = new APSTaskStepDAO();
		return Instance;
	}
}
