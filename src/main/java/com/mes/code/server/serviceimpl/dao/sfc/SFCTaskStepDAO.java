package com.mes.code.server.serviceimpl.dao.sfc;

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
import com.mes.code.server.service.po.sfc.SFCTaskStep;
import com.mes.code.server.service.utils.StringUtils;
import com.mes.code.server.serviceimpl.dao.BaseDAO;
import com.mes.code.server.serviceimpl.dao.aps.APSTaskStepDAO;

public class SFCTaskStepDAO extends BaseDAO {

	private static Logger logger = LoggerFactory.getLogger(SFCTaskStepDAO.class);

	private static SFCTaskStepDAO Instance = null;

	/**
	 * 添加或修改
	 * 
	 * @param wSFCTaskStep
	 * @return
	 */
	public int Update(BMSEmployee wLoginUser, SFCTaskStep wSFCTaskStep, OutResult<Integer> wErrorCode) {
		int wResult = 0;
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return wResult;
			}

			if (wSFCTaskStep == null)
				return 0;

			String wSQL = "";
			if (wSFCTaskStep.getID() <= 0) {
				wSQL = MessageFormat.format(
						"INSERT INTO {0}.sfc_taskstep(TaskStepID,ShiftID,MonitorID,WorkHour,"
								+ "OperatorID,CreateTime,ReadyTime) VALUES(:TaskStepID,"
								+ ":ShiftID,:WorkHour,:MonitorID,:OperatorID,:CreateTime,:ReadyTime);",
						wInstance.Result);
			} else {
				wSQL = MessageFormat.format("UPDATE {0}.sfc_taskstep SET TaskStepID = :TaskStepID,"
						+ "ShiftID = :ShiftID,MonitorID=:MonitorID,WorkHour = :WorkHour,OperatorID = :OperatorID,"
						+ "CreateTime = :CreateTime,ReadyTime = :ReadyTime WHERE ID = :ID;", wInstance.Result);
			}

			wSQL = this.DMLChange(wSQL);

			Map<String, Object> wParamMap = new HashMap<String, Object>();

			wParamMap.put("ID", wSFCTaskStep.ID);
			wParamMap.put("TaskStepID", wSFCTaskStep.TaskStepID);
			wParamMap.put("ShiftID", wSFCTaskStep.ShiftID);
			wParamMap.put("WorkHour", wSFCTaskStep.WorkHour);
			wParamMap.put("MonitorID", wSFCTaskStep.MonitorID);
			wParamMap.put("OperatorID", wSFCTaskStep.OperatorID);
			wParamMap.put("CreateTime", wSFCTaskStep.CreateTime);
			wParamMap.put("ReadyTime", wSFCTaskStep.ReadyTime);

			KeyHolder keyHolder = new GeneratedKeyHolder();
			SqlParameterSource wSqlParameterSource = new MapSqlParameterSource(wParamMap);

			nameJdbcTemplate.update(wSQL, wSqlParameterSource, keyHolder);

			if (wSFCTaskStep.getID() <= 0) {
				wResult = keyHolder.getKey().intValue();
				wSFCTaskStep.setID(wResult);
			} else {
				wResult = wSFCTaskStep.getID();
			}
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
	public SFCTaskStep SelectByID(BMSEmployee wLoginUser, int wID, OutResult<Integer> wErrorCode) {
		SFCTaskStep wResult = new SFCTaskStep();
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return wResult;
			}

			List<SFCTaskStep> wList = SelectList(wLoginUser, wID, -1, -1, -1, -1, wErrorCode);
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
	public List<SFCTaskStep> SelectList(BMSEmployee wLoginUser, int wID, int wTaskStepID, int wShiftID, int wOperatorID,
			int wMonitorID, OutResult<Integer> wErrorCode) {
		List<SFCTaskStep> wResultList = new ArrayList<SFCTaskStep>();
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return wResultList;
			}

			String wSQL = MessageFormat.format("SELECT * FROM {0}.sfc_taskstep WHERE  1=1  "
					+ "and ( :wID <= 0 or :wID = ID ) " + "and ( :wTaskStepID <= 0 or :wTaskStepID = TaskStepID ) "
					+ "and ( :wOperatorID <= 0 or :wOperatorID = OperatorID ) "
					+ "and ( :wMonitorID <= 0 or :wMonitorID = MonitorID ) "
					+ "and ( :wShiftID <= 0 or :wShiftID = ShiftID );", wInstance.Result);

			Map<String, Object> wParamMap = new HashMap<String, Object>();

			wParamMap.put("wID", wID);
			wParamMap.put("wTaskStepID", wTaskStepID);
			wParamMap.put("wOperatorID", wOperatorID);
			wParamMap.put("wMonitorID", wMonitorID);
			wParamMap.put("wShiftID", wShiftID);

			wSQL = this.DMLChange(wSQL);

			List<Map<String, Object>> wQueryResult = nameJdbcTemplate.queryForList(wSQL, wParamMap);

			for (Map<String, Object> wReader : wQueryResult) {
				SFCTaskStep wItem = new SFCTaskStep();

				wItem.ID = StringUtils.parseInt(wReader.get("ID"));
				wItem.TaskStepID = StringUtils.parseInt(wReader.get("TaskStepID"));
				wItem.ShiftID = StringUtils.parseInt(wReader.get("ShiftID"));
				wItem.WorkHour = StringUtils.parseInt(wReader.get("WorkHour"));
				wItem.MonitorID = StringUtils.parseInt(wReader.get("MonitorID"));
				wItem.OperatorID = StringUtils.parseInt(wReader.get("OperatorID"));
				wItem.CreateTime = StringUtils.parseCalendar(wReader.get("CreateTime"));
				wItem.ReadyTime = StringUtils.parseCalendar(wReader.get("ReadyTime"));

				if (wItem.TaskStepID > 0) {
					APSTaskStep wTaskStep = APSTaskStepDAO.getInstance().SelectByID(wLoginUser, wItem.TaskStepID,
							wErrorCode);
					if (wTaskStep != null && wTaskStep.ID > 0) {
						wItem.LineID = wTaskStep.LineID;
						wItem.LineName = wTaskStep.LineName;
						wItem.MaterialNo = wTaskStep.MaterialNo;
						wItem.MaterialName = wTaskStep.MaterialName;
						wItem.OrderID = wTaskStep.OrderID;
						wItem.OrderNo = wTaskStep.OrderNo;
						wItem.PartID = wTaskStep.PartID;
						wItem.PartName = wTaskStep.PartName;
						wItem.PartNo = wTaskStep.PartNo;
						wItem.PlanerID = wTaskStep.PlanerID;
						wItem.PlanerName = wTaskStep.PlanerName;
						wItem.ProductNo = wTaskStep.ProductNo;
						wItem.StepID = wTaskStep.StepID;
						wItem.TaskLineID = wTaskStep.TaskLineID;
						wItem.TaskPartID = wTaskStep.TaskPartID;
						wItem.TaskText = wTaskStep.TaskText;
						wItem.WorkShopID = wTaskStep.WorkShopID;
						wItem.Status = wTaskStep.Status;
					}
				}

				wResultList.add(wItem);
			}
		} catch (Exception ex) {
			wErrorCode.set(MESException.DBSQL.getValue());
			logger.error(ex.toString());
		}
		return wResultList;
	}

	private SFCTaskStepDAO() {
		super();
	}

	public static SFCTaskStepDAO getInstance() {
		if (Instance == null)
			Instance = new SFCTaskStepDAO();
		return Instance;
	}
}
