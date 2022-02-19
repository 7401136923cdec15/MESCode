package com.mes.code.server.serviceimpl.dao.sch;

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
import com.mes.code.server.service.po.bms.BMSEmployee;
import com.mes.code.server.service.po.sch.SCHSecondment;
import com.mes.code.server.service.utils.StringUtils;
import com.mes.code.server.serviceimpl.dao.BaseDAO;

public class SCHSecondmentDAO extends BaseDAO {

	private static Logger logger = LoggerFactory.getLogger(SCHSecondmentDAO.class);

	private static SCHSecondmentDAO Instance = null;

	/**
	 * 添加或修改
	 * 
	 * @param wSCHSecondment
	 * @return
	 */
	public int Update(BMSEmployee wLoginUser, SCHSecondment wSCHSecondment, OutResult<Integer> wErrorCode) {
		int wResult = 0;
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return wResult;
			}

			if (wSCHSecondment == null)
				return 0;

			String wSQL = "";
			if (wSCHSecondment.getID() <= 0) {
				wSQL = MessageFormat.format("INSERT INTO {0}.sch_secondment(SendID,SendTime,"
						+ "SecondDepartmentID,IsOverArea,SecondAuditID,"
						+ "SendAuditTime,BeSecondAuditID,BeSecondAuditTime,"
						+ "SecondPersonID,BeSecondDepartmentID,Status,ValidDate) VALUES(:SendID,:SendTime,"
						+ ":SecondDepartmentID,:IsOverArea,:SecondAuditID,"
						+ ":SendAuditTime,:BeSecondAuditID,:BeSecondAuditTime,"
						+ ":SecondPersonID,:BeSecondDepartmentID,:Status,:ValidDate);", wInstance.Result);
			} else {
				wSQL = MessageFormat.format("UPDATE {0}.sch_secondment SET SendID = :SendID,"
						+ "SendTime = :SendTime,SecondDepartmentID = :SecondDepartmentID,"
						+ "IsOverArea = :IsOverArea,SecondAuditID = :SecondAuditID," + "SendAuditTime = :SendAuditTime,"
						+ "BeSecondAuditID = :BeSecondAuditID," + "BeSecondAuditTime = :BeSecondAuditTime,"
						+ "SecondPersonID = :SecondPersonID,BeSecondDepartmentID=:BeSecondDepartmentID,Status = :Status,ValidDate=:ValidDate  "
						+ "WHERE ID = :ID;", wInstance.Result);
			}

			wSQL = this.DMLChange(wSQL);

			Map<String, Object> wParamMap = new HashMap<String, Object>();

			wParamMap.put("ID", wSCHSecondment.ID);
			wParamMap.put("SendID", wSCHSecondment.SendID);
			wParamMap.put("SendTime", wSCHSecondment.SendTime);
			wParamMap.put("SecondDepartmentID", wSCHSecondment.SecondDepartmentID);
			wParamMap.put("IsOverArea", wSCHSecondment.IsOverArea ? 1 : 0);
			wParamMap.put("SecondAuditID", wSCHSecondment.SecondAuditID);
			wParamMap.put("SendAuditTime", wSCHSecondment.SendAuditTime);
			wParamMap.put("BeSecondAuditID", wSCHSecondment.BeSecondAuditID);
			wParamMap.put("BeSecondAuditTime", wSCHSecondment.BeSecondAuditTime);
			wParamMap.put("SecondPersonID", wSCHSecondment.SecondPersonID);
			wParamMap.put("BeSecondDepartmentID", wSCHSecondment.BeSecondDepartmentID);
			wParamMap.put("Status", wSCHSecondment.Status);
			wParamMap.put("ValidDate", wSCHSecondment.ValidDate);

			KeyHolder keyHolder = new GeneratedKeyHolder();
			SqlParameterSource wSqlParameterSource = new MapSqlParameterSource(wParamMap);

			nameJdbcTemplate.update(wSQL, wSqlParameterSource, keyHolder);

			if (wSCHSecondment.getID() <= 0) {
				wResult = keyHolder.getKey().intValue();
				wSCHSecondment.setID(wResult);
			} else {
				wResult = wSCHSecondment.getID();
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
	public ServiceResult<Integer> DeleteList(BMSEmployee wLoginUser, List<SCHSecondment> wList,
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
			for (SCHSecondment wItem : wList) {
				wIDList.add(String.valueOf(wItem.ID));
			}
			String wSql = MessageFormat.format("delete from {1}.sch_secondment WHERE ID IN({0}) ;",
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
	public SCHSecondment SelectByID(BMSEmployee wLoginUser, int wID, OutResult<Integer> wErrorCode) {
		SCHSecondment wResult = new SCHSecondment();
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return wResult;
			}

			List<SCHSecondment> wList = SelectList(wLoginUser, wID, -1, -1, -1, -1, -1, -1, null, null, null,
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

	/**
	 * 条件查询集合
	 * 
	 * @return
	 */
	public List<SCHSecondment> SelectList(BMSEmployee wLoginUser, int wID, int wSendID, int wSecondDepartmentID,
			int wSecondAuditID, int wBeSecondAuditID, int wSecondPersonID, int wBeSecondDepartmentID,
			List<Integer> wStateIDList, Calendar wStartTime, Calendar wEndTime, OutResult<Integer> wErrorCode) {
		List<SCHSecondment> wResultList = new ArrayList<SCHSecondment>();
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

			String wSQL = MessageFormat.format("SELECT * FROM {0}.sch_secondment WHERE  1=1  "
					+ "and ( :wID <= 0 or :wID = ID ) " + "and ( :wSendID <= 0 or :wSendID = SendID ) "
					+ "and ( :wSecondDepartmentID <= 0 or :wSecondDepartmentID = SecondDepartmentID ) "
					+ "and ( :wSecondAuditID <= 0 or :wSecondAuditID = SecondAuditID ) "
					+ "and ( :wBeSecondAuditID <= 0 or :wBeSecondAuditID = BeSecondAuditID ) "
					+ "and ( :wSecondPersonID <= 0 or :wSecondPersonID = SecondPersonID ) "
					+ "and ( :wBeSecondDepartmentID <= 0 or :wBeSecondDepartmentID = BeSecondDepartmentID ) "
					+ "and ( :wStartTime <= str_to_date(''2010-01-01'', ''%Y-%m-%d'') or :wStartTime <= SendTime) "
					+ "and ( :wEndTime <= str_to_date(''2010-01-01'', ''%Y-%m-%d'') or :wEndTime >= SendTime) "
					+ "and ( :wStatus is null or :wStatus = '''' or Status in ({1}));", wInstance.Result,
					wStateIDList.size() > 0 ? StringUtils.Join(",", wStateIDList) : "0");

			Map<String, Object> wParamMap = new HashMap<String, Object>();

			wParamMap.put("wID", wID);
			wParamMap.put("wSendID", wSendID);
			wParamMap.put("wSecondDepartmentID", wSecondDepartmentID);
			wParamMap.put("wSecondAuditID", wSecondAuditID);
			wParamMap.put("wBeSecondAuditID", wBeSecondAuditID);
			wParamMap.put("wSecondPersonID", wSecondPersonID);
			wParamMap.put("wBeSecondDepartmentID", wBeSecondDepartmentID);
			wParamMap.put("wStartTime", wStartTime);
			wParamMap.put("wEndTime", wEndTime);
			wParamMap.put("wStatus", StringUtils.Join(",", wStateIDList));

			wSQL = this.DMLChange(wSQL);

			List<Map<String, Object>> wQueryResult = nameJdbcTemplate.queryForList(wSQL, wParamMap);

			for (Map<String, Object> wReader : wQueryResult) {
				SCHSecondment wItem = new SCHSecondment();

				wItem.ID = StringUtils.parseInt(wReader.get("ID"));
				wItem.SendID = StringUtils.parseInt(wReader.get("SendID"));
				wItem.SendTime = StringUtils.parseCalendar(wReader.get("SendTime"));
				wItem.SecondDepartmentID = StringUtils.parseInt(wReader.get("SecondDepartmentID"));
				wItem.IsOverArea = StringUtils.parseInt(wReader.get("IsOverArea")) == 1 ? true : false;
				wItem.SecondAuditID = StringUtils.parseInt(wReader.get("SecondAuditID"));
				wItem.SendAuditTime = StringUtils.parseCalendar(wReader.get("SendAuditTime"));
				wItem.BeSecondAuditID = StringUtils.parseInt(wReader.get("BeSecondAuditID"));
				wItem.BeSecondAuditTime = StringUtils.parseCalendar(wReader.get("BeSecondAuditTime"));
				wItem.ValidDate = StringUtils.parseCalendar(wReader.get("ValidDate"));
				wItem.SecondPersonID = StringUtils.parseInt(wReader.get("SecondPersonID"));
				wItem.BeSecondDepartmentID = StringUtils.parseInt(wReader.get("BeSecondDepartmentID"));
				wItem.Status = StringUtils.parseInt(wReader.get("Status"));

				wResultList.add(wItem);
			}
		} catch (Exception ex) {
			wErrorCode.set(MESException.DBSQL.getValue());
			logger.error(ex.toString());
		}
		return wResultList;
	}

	private SCHSecondmentDAO() {
		super();
	}

	public static SCHSecondmentDAO getInstance() {
		if (Instance == null)
			Instance = new SCHSecondmentDAO();
		return Instance;
	}
}
