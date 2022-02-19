package com.mes.code.server.serviceimpl.dao.aps;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.mes.code.server.service.po.aps.APSTaskPart;
import com.mes.code.server.service.po.bms.BMSEmployee;
import com.mes.code.server.service.utils.StringUtils;
import com.mes.code.server.serviceimpl.dao.BaseDAO;

public class APSTaskPartDAO extends BaseDAO {

	private static Logger logger = LoggerFactory.getLogger(APSTaskPartDAO.class);

	private static APSTaskPartDAO Instance = null;

	/**
	 * 添加或修改
	 * 
	 * @param wAPSTaskPart
	 * @return
	 */
	public long Update(BMSEmployee wLoginUser, APSTaskPart wAPSTaskPart, OutResult<Integer> wErrorCode) {
		int wResult = 0;
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return wResult;
			}

			if (wAPSTaskPart == null)
				return 0;

			String wSQL = "";
			if (wAPSTaskPart.getID() <= 0) {
				wSQL = MessageFormat.format("INSERT INTO {0}.aps_taskpart(OrderID,PartNo,TaskLineID,WorkShopID,"
						+ "LineID,PartID,WorkHour,CraftMinutes,ShiftID,PlanerID,ShiftPeriod,"
						+ "SubmitTime,StartTime,EndTime,ProductNo,MaterialNo,BOMNo,Priority,Active,"
						+ "TaskText,DelayHours,Status) VALUES(:OrderID,:PartNo,:TaskLineID,"
						+ ":WorkShopID,:LineID,:PartID,:WorkHour,:CraftMinutes,:ShiftID,"
						+ ":PlanerID,:ShiftPeriod,:SubmitTime,:StartTime,:EndTime,:ProductNo,:MaterialNo,"
						+ ":BOMNo,:Priority,:Active,:TaskText,:DelayHours,:Status);", wInstance.Result);
			} else {
				wSQL = MessageFormat.format("UPDATE {0}.aps_taskpart SET OrderID = :OrderID,PartNo = :PartNo,"
						+ "TaskLineID = :TaskLineID,WorkShopID = :WorkShopID,LineID = :LineID,"
						+ "PartID = :PartID,WorkHour = :WorkHour,CraftMinutes = :CraftMinutes,"
						+ "ShiftID = :ShiftID,PlanerID = :PlanerID,ShiftPeriod = :ShiftPeriod,"
						+ "SubmitTime = :SubmitTime,StartTime=:StartTime,EndTime = :EndTime,ProductNo = :ProductNo,"
						+ "MaterialNo = :MaterialNo,BOMNo = :BOMNo,Priority = :Priority,"
						+ "Active = :Active,TaskText = :TaskText,DelayHours = :DelayHours,"
						+ "Status = :Status WHERE ID = :ID;", wInstance.Result);
			}

			wSQL = this.DMLChange(wSQL);

			Map<String, Object> wParamMap = new HashMap<String, Object>();

			wParamMap.put("ID", wAPSTaskPart.ID);
			wParamMap.put("OrderID", wAPSTaskPart.OrderID);
			wParamMap.put("PartNo", wAPSTaskPart.PartNo);
			wParamMap.put("TaskLineID", wAPSTaskPart.TaskLineID);
			wParamMap.put("WorkShopID", wAPSTaskPart.WorkShopID);
			wParamMap.put("LineID", wAPSTaskPart.LineID);
			wParamMap.put("PartID", wAPSTaskPart.PartID);
			wParamMap.put("WorkHour", wAPSTaskPart.WorkHour);
			wParamMap.put("CraftMinutes", wAPSTaskPart.CraftMinutes);
			wParamMap.put("ShiftID", wAPSTaskPart.ShiftID);
			wParamMap.put("PlanerID", wAPSTaskPart.PlanerID);
			wParamMap.put("ShiftPeriod", wAPSTaskPart.ShiftPeriod);
			wParamMap.put("SubmitTime", wAPSTaskPart.SubmitTime);
			wParamMap.put("StartTime", wAPSTaskPart.StartTime);
			wParamMap.put("EndTime", wAPSTaskPart.EndTime);
			wParamMap.put("ProductNo", wAPSTaskPart.ProductNo);
			wParamMap.put("MaterialNo", wAPSTaskPart.MaterialNo);
			wParamMap.put("BOMNo", wAPSTaskPart.BOMNo);
			wParamMap.put("Priority", wAPSTaskPart.Priority);
			wParamMap.put("Active", wAPSTaskPart.Active);
			wParamMap.put("TaskText", wAPSTaskPart.TaskText);
			wParamMap.put("DelayHours", wAPSTaskPart.DelayHours);
			wParamMap.put("Status", wAPSTaskPart.Status);

			KeyHolder keyHolder = new GeneratedKeyHolder();
			SqlParameterSource wSqlParameterSource = new MapSqlParameterSource(wParamMap);

			nameJdbcTemplate.update(wSQL, wSqlParameterSource, keyHolder);

			if (wAPSTaskPart.getID() <= 0) {
				wResult = keyHolder.getKey().intValue();
				wAPSTaskPart.setID(wResult);
			} else {
				wResult = wAPSTaskPart.getID();
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
	public ServiceResult<Integer> DeleteList(BMSEmployee wLoginUser, List<APSTaskPart> wList,
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
			for (APSTaskPart wItem : wList) {
				wIDList.add(String.valueOf(wItem.ID));
			}
			String wSql = MessageFormat.format("delete from {1}.aps_taskpart WHERE ID IN({0}) ;",
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
	public APSTaskPart SelectByID(BMSEmployee wLoginUser, int wID, OutResult<Integer> wErrorCode) {
		APSTaskPart wResult = new APSTaskPart();
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return wResult;
			}

			List<APSTaskPart> wList = SelectList(wLoginUser, wID, -1, -1, -1, -1, -1, -1, null, -1, null, null,
					wErrorCode);
			if (wList == null || wList.size() != 1)
				return wResult;
			wResult = wList.get(0);
		} catch (Exception e) {
			wErrorCode.set(MESException.DBSQL.getValue());
			logger.error(e.toString());
		}
		return wResult;
	}

	public List<APSTaskPart> SelectListByOrderIDList(BMSEmployee wLoginUser, List<Integer> wStatusIDList,
			List<Integer> wOrderIDList, OutResult<Integer> wErrorCode) {
		List<APSTaskPart> wResult = new ArrayList<APSTaskPart>();
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return wResult;
			}

			if (wLoginUser == null || wStatusIDList == null || wStatusIDList.size() <= 0 || wOrderIDList == null
					|| wOrderIDList.size() <= 0)
				return wResult;

			String wSQL = MessageFormat.format(
					"SELECT * FROM {0}.aps_taskpart WHERE  1=1  " + "and ( Status in ({1}) ) "
							+ "and ( OrderID in ({2}) ) ;",
					wInstance.Result, StringUtils.Join(",", wStatusIDList), StringUtils.Join(",", wOrderIDList));

			Map<String, Object> wParamMap = new HashMap<String, Object>();

			wSQL = this.DMLChange(wSQL);

			List<Map<String, Object>> wQueryResult = nameJdbcTemplate.queryForList(wSQL, wParamMap);

			for (Map<String, Object> wReader : wQueryResult) {
				APSTaskPart wItem = new APSTaskPart();

				wItem.ID = StringUtils.parseInt(wReader.get("ID"));
				wItem.OrderID = StringUtils.parseInt(wReader.get("OrderID"));
				wItem.PartNo = StringUtils.parseString(wReader.get("PartNo"));
				wItem.TaskLineID = StringUtils.parseInt(wReader.get("TaskLineID"));
				wItem.WorkShopID = StringUtils.parseInt(wReader.get("WorkShopID"));
				wItem.LineID = StringUtils.parseInt(wReader.get("LineID"));
				wItem.PartID = StringUtils.parseInt(wReader.get("PartID"));
				wItem.WorkHour = StringUtils.parseDouble(wReader.get("WorkHour"));
				wItem.CraftMinutes = StringUtils.parseInt(wReader.get("CraftMinutes"));
				wItem.ShiftID = StringUtils.parseInt(wReader.get("ShiftID"));
				wItem.PlanerID = StringUtils.parseInt(wReader.get("PlanerID"));
				wItem.ShiftPeriod = StringUtils.parseInt(wReader.get("ShiftPeriod"));
				wItem.SubmitTime = StringUtils.parseCalendar(wReader.get("SubmitTime"));
				wItem.StartTime = StringUtils.parseCalendar(wReader.get("StartTime"));
				wItem.EndTime = StringUtils.parseCalendar(wReader.get("EndTime"));
				wItem.ProductNo = StringUtils.parseString(wReader.get("ProductNo"));
				wItem.MaterialNo = StringUtils.parseString(wReader.get("MaterialNo"));
				wItem.BOMNo = StringUtils.parseString(wReader.get("BOMNo"));
				wItem.Priority = StringUtils.parseInt(wReader.get("Priority"));
				wItem.Active = StringUtils.parseInt(wReader.get("Active"));
				wItem.TaskText = StringUtils.parseString(wReader.get("TaskText"));
				wItem.DelayHours = StringUtils.parseDouble(wReader.get("DelayHours"));
				wItem.Status = StringUtils.parseInt(wReader.get("Status"));

				wResult.add(wItem);
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return wResult;
	}

	/**
	 * 条件查询集合
	 * 
	 * @return
	 */
	public List<APSTaskPart> SelectList(BMSEmployee wLoginUser, int wID, int wOrderID, int wTaskLineID, int wLineID,
			int wPartID, int wActive, int wShiftPeriod, List<Integer> wStateIDList, int wShiftID, Calendar wStartTime,
			Calendar wEndTime, OutResult<Integer> wErrorCode) {
		List<APSTaskPart> wResultList = new ArrayList<APSTaskPart>();
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return wResultList;
			}

			if (wStateIDList == null)
				wStateIDList = new ArrayList<Integer>();

			Calendar wBaseTime = Calendar.getInstance();
			wBaseTime.set(2000, 1, 1);
			if (wStartTime == null || wStartTime.compareTo(wBaseTime) < 0)
				wStartTime = wBaseTime;
			if (wEndTime == null || wEndTime.compareTo(wBaseTime) < 0)
				wEndTime = wBaseTime;
			if (wStartTime.compareTo(wEndTime) > 0)
				return wResultList;

			String wSQL = MessageFormat.format("SELECT * FROM {0}.aps_taskpart WHERE  1=1  "
					+ "and ( :wID <= 0 or :wID = ID ) " + "and ( :wOrderID <= 0 or :wOrderID = OrderID ) "
					+ "and ( :wTaskLineID <= 0 or :wTaskLineID = TaskLineID ) "
					+ "and ( :wLineID <= 0 or :wLineID = LineID ) " + "and ( :wPartID <= 0 or :wPartID = PartID ) "
					+ "and ( :wActive < 0 or :wActive = Active ) " + "and ( :wShiftID <= 0 or :wShiftID = ShiftID ) "
					+ "and ( :wShiftPeriod <= 0 or :wShiftPeriod = ShiftPeriod ) "
					+ "and ( :wStartTime <= str_to_date(''2010-01-01'', ''%Y-%m-%d'') or :wStartTime <= StartTime) "
					+ "and ( :wEndTime <= str_to_date(''2010-01-01'', ''%Y-%m-%d'') or :wEndTime >= StartTime) "
					+ "and ( :wStatus is null or :wStatus = '''' or Status in ({1}));", wInstance.Result,
					wStateIDList.size() > 0 ? StringUtils.Join(",", wStateIDList) : "0");

			Map<String, Object> wParamMap = new HashMap<String, Object>();

			wParamMap.put("wID", wID);
			wParamMap.put("wOrderID", wOrderID);
			wParamMap.put("wTaskLineID", wTaskLineID);
			wParamMap.put("wLineID", wLineID);
			wParamMap.put("wPartID", wPartID);
			wParamMap.put("wActive", wActive);
			wParamMap.put("wShiftID", wShiftID);
			wParamMap.put("wShiftPeriod", wShiftPeriod);
			wParamMap.put("wStartTime", wStartTime);
			wParamMap.put("wEndTime", wEndTime);
			wParamMap.put("wStatus", StringUtils.Join(",", wStateIDList));

			wSQL = this.DMLChange(wSQL);

			List<Map<String, Object>> wQueryResult = nameJdbcTemplate.queryForList(wSQL, wParamMap);

			for (Map<String, Object> wReader : wQueryResult) {
				APSTaskPart wItem = new APSTaskPart();

				wItem.ID = StringUtils.parseInt(wReader.get("ID"));
				wItem.OrderID = StringUtils.parseInt(wReader.get("OrderID"));
				wItem.PartNo = StringUtils.parseString(wReader.get("PartNo"));
				wItem.TaskLineID = StringUtils.parseInt(wReader.get("TaskLineID"));
				wItem.WorkShopID = StringUtils.parseInt(wReader.get("WorkShopID"));
				wItem.LineID = StringUtils.parseInt(wReader.get("LineID"));
				wItem.PartID = StringUtils.parseInt(wReader.get("PartID"));
				wItem.WorkHour = StringUtils.parseDouble(wReader.get("WorkHour"));
				wItem.CraftMinutes = StringUtils.parseInt(wReader.get("CraftMinutes"));
				wItem.ShiftID = StringUtils.parseInt(wReader.get("ShiftID"));
				wItem.PlanerID = StringUtils.parseInt(wReader.get("PlanerID"));
				wItem.ShiftPeriod = StringUtils.parseInt(wReader.get("ShiftPeriod"));
				wItem.SubmitTime = StringUtils.parseCalendar(wReader.get("SubmitTime"));
				wItem.StartTime = StringUtils.parseCalendar(wReader.get("StartTime"));
				wItem.EndTime = StringUtils.parseCalendar(wReader.get("EndTime"));
				wItem.ProductNo = StringUtils.parseString(wReader.get("ProductNo"));
				wItem.MaterialNo = StringUtils.parseString(wReader.get("MaterialNo"));
				wItem.BOMNo = StringUtils.parseString(wReader.get("BOMNo"));
				wItem.Priority = StringUtils.parseInt(wReader.get("Priority"));
				wItem.Active = StringUtils.parseInt(wReader.get("Active"));
				wItem.TaskText = StringUtils.parseString(wReader.get("TaskText"));
				wItem.DelayHours = StringUtils.parseDouble(wReader.get("DelayHours"));
				wItem.Status = StringUtils.parseInt(wReader.get("Status"));

				wResultList.add(wItem);
			}
		} catch (Exception ex) {
			wErrorCode.set(MESException.DBSQL.getValue());
			logger.error(ex.toString());
		}
		return wResultList;
	}

	/**
	 * 根据ShiftID 查询工位任务
	 * 
	 * @return
	 */
	public List<APSTaskPart> SelectListByShiftID(BMSEmployee wLoginUser, int wShiftID, OutResult<Integer> wErrorCode) {
		List<APSTaskPart> wResultList = new ArrayList<APSTaskPart>();
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return wResultList;
			}

			String wSQL = MessageFormat.format("SELECT * FROM {0}.aps_taskpart WHERE  ShiftID=:wShiftID;",
					wInstance.Result);

			Map<String, Object> wParamMap = new HashMap<String, Object>();

			wParamMap.put("wShiftID", wShiftID);

			wSQL = this.DMLChange(wSQL);

			List<Map<String, Object>> wQueryResult = nameJdbcTemplate.queryForList(wSQL, wParamMap);

			for (Map<String, Object> wReader : wQueryResult) {
				APSTaskPart wItem = new APSTaskPart();

				wItem.ID = StringUtils.parseInt(wReader.get("ID"));
				wItem.OrderID = StringUtils.parseInt(wReader.get("OrderID"));
				wItem.PartNo = StringUtils.parseString(wReader.get("PartNo"));
				wItem.TaskLineID = StringUtils.parseInt(wReader.get("TaskLineID"));
				wItem.WorkShopID = StringUtils.parseInt(wReader.get("WorkShopID"));
				wItem.LineID = StringUtils.parseInt(wReader.get("LineID"));
				wItem.PartID = StringUtils.parseInt(wReader.get("PartID"));
				wItem.WorkHour = StringUtils.parseDouble(wReader.get("WorkHour"));
				wItem.CraftMinutes = StringUtils.parseInt(wReader.get("CraftMinutes"));
				wItem.ShiftID = StringUtils.parseInt(wReader.get("ShiftID"));
				wItem.PlanerID = StringUtils.parseInt(wReader.get("PlanerID"));
				wItem.ShiftPeriod = StringUtils.parseInt(wReader.get("ShiftPeriod"));
				wItem.SubmitTime = StringUtils.parseCalendar(wReader.get("SubmitTime"));
				wItem.StartTime = StringUtils.parseCalendar(wReader.get("StartTime"));
				wItem.EndTime = StringUtils.parseCalendar(wReader.get("EndTime"));
				wItem.ProductNo = StringUtils.parseString(wReader.get("ProductNo"));
				wItem.MaterialNo = StringUtils.parseString(wReader.get("MaterialNo"));
				wItem.BOMNo = StringUtils.parseString(wReader.get("BOMNo"));
				wItem.Priority = StringUtils.parseInt(wReader.get("Priority"));
				wItem.Active = StringUtils.parseInt(wReader.get("Active"));
				wItem.TaskText = StringUtils.parseString(wReader.get("TaskText"));
				wItem.DelayHours = StringUtils.parseDouble(wReader.get("DelayHours"));
				wItem.Status = StringUtils.parseInt(wReader.get("Status"));

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
				APSTaskPart wAPSTaskPart = SelectByID(wLoginUser, wItem, wErrorCode);
				if (wAPSTaskPart == null || wAPSTaskPart.ID <= 0)
					continue;
				wAPSTaskPart.Active = wActive;
				long wID = Update(wLoginUser, wAPSTaskPart, wErrorCode);
				if (wID <= 0)
					break;
			}
		} catch (Exception e) {
			wErrorCode.set(MESException.DBSQL.getValue());
			logger.error(e.toString());
		}
		return wResult;
	}

	private APSTaskPartDAO() {
		super();
	}

	public static APSTaskPartDAO getInstance() {
		if (Instance == null)
			Instance = new APSTaskPartDAO();
		return Instance;
	}
}
