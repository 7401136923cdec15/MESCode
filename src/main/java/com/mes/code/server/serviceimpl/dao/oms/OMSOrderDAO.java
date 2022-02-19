package com.mes.code.server.serviceimpl.dao.oms;

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
import com.mes.code.server.service.po.oms.OMSOrder;
import com.mes.code.server.service.utils.StringUtils;
import com.mes.code.server.serviceimpl.dao.BaseDAO;

public class OMSOrderDAO extends BaseDAO {

	private static Logger logger = LoggerFactory.getLogger(OMSOrderDAO.class);

	private static OMSOrderDAO Instance = null;

	/**
	 * 添加或修改
	 * 
	 * @param wOMSOrder
	 * @return
	 */
	public long Update(BMSEmployee wLoginUser, OMSOrder wOMSOrder, OutResult<Integer> wErrorCode) {
		int wResult = 0;
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return wResult;
			}

			if (wOMSOrder == null)
				return 0;

			String wSQL = "";
			if (wOMSOrder.getID() <= 0) {
				wSQL = MessageFormat.format("INSERT INTO {0}.oms_order(CommandID,ERPID,OrderNo,LineID,"
						+ "ProductID,BureauSectionID,WBSNo,PartNo,BOMNo,Priority,"
						+ "Status,PlanReceiveDate,RealReceiveDate,PlanFinishDate,"
						+ "RealStartDate,RealFinishDate,RealSendDate,Remark,CreateID,"
						+ "CreateTime,EditID,EditTime,AuditorID,AuditTime,Active) "
						+ "VALUES(:CommandID,:ERPID,:OrderNo,:LineID,:ProductID,:BureauSectionID,:WBSNo,:PartNo,:BOMNo,:Priority,:Status,:PlanReceiveDate,:RealReceiveDate,:PlanFinishDate,:RealStartDate,:RealFinishDate,:RealSendDate,:Remark,:CreateID,:CreateTime,:EditID,:EditTime,:AuditorID,:AuditTime,:Active);",
						wInstance.Result);
			} else {
				wSQL = MessageFormat.format("UPDATE {0}.oms_order SET CommandID=:CommandID,"
						+ "ERPID = :ERPID,OrderNo = :OrderNo,LineID = :LineID,"
						+ "ProductID = :ProductID,BureauSectionID = :BureauSectionID,"
						+ "WBSNo = :WBSNo,PartNo = :PartNo,BOMNo = :BOMNo," + "Priority = :Priority,Status = :Status,"
						+ "PlanReceiveDate = :PlanReceiveDate,"
						+ "RealReceiveDate = :RealReceiveDate,PlanFinishDate = :PlanFinishDate,RealStartDate = :RealStartDate,RealFinishDate = :RealFinishDate,RealSendDate = :RealSendDate,Remark = :Remark,CreateID = :CreateID,CreateTime = :CreateTime,EditID = :EditID,EditTime = :EditTime,AuditorID = :AuditorID,AuditTime = :AuditTime,Active = :Active WHERE ID = :ID;",
						wInstance.Result);
			}

			wSQL = this.DMLChange(wSQL);

			Map<String, Object> wParamMap = new HashMap<String, Object>();

			wParamMap.put("ID", wOMSOrder.ID);
			wParamMap.put("CommandID", wOMSOrder.CommandID);
			wParamMap.put("ERPID", wOMSOrder.ERPID);
			wParamMap.put("OrderNo", wOMSOrder.OrderNo);
			wParamMap.put("LineID", wOMSOrder.LineID);
			wParamMap.put("ProductID", wOMSOrder.ProductID);
			wParamMap.put("BureauSectionID", wOMSOrder.BureauSectionID);
			wParamMap.put("WBSNo", wOMSOrder.WBSNo);
			wParamMap.put("PartNo", wOMSOrder.PartNo);
			wParamMap.put("BOMNo", wOMSOrder.BOMNo);
			wParamMap.put("Priority", wOMSOrder.Priority);
			wParamMap.put("Status", wOMSOrder.Status);
			wParamMap.put("PlanReceiveDate", wOMSOrder.PlanReceiveDate);
			wParamMap.put("RealReceiveDate", wOMSOrder.RealReceiveDate);
			wParamMap.put("PlanFinishDate", wOMSOrder.PlanFinishDate);
			wParamMap.put("RealStartDate", wOMSOrder.RealStartDate);
			wParamMap.put("RealFinishDate", wOMSOrder.RealFinishDate);
			wParamMap.put("RealSendDate", wOMSOrder.RealSendDate);
			wParamMap.put("Remark", wOMSOrder.Remark);
			wParamMap.put("CreateID", wOMSOrder.CreateID);
			wParamMap.put("CreateTime", wOMSOrder.CreateTime);
			wParamMap.put("EditID", wOMSOrder.EditID);
			wParamMap.put("EditTime", wOMSOrder.EditTime);
			wParamMap.put("AuditorID", wOMSOrder.AuditorID);
			wParamMap.put("AuditTime", wOMSOrder.AuditTime);
			wParamMap.put("Active", wOMSOrder.Active);

			KeyHolder keyHolder = new GeneratedKeyHolder();
			SqlParameterSource wSqlParameterSource = new MapSqlParameterSource(wParamMap);

			nameJdbcTemplate.update(wSQL, wSqlParameterSource, keyHolder);

			if (wOMSOrder.getID() <= 0) {
				wResult = keyHolder.getKey().intValue();
				wOMSOrder.setID(wResult);
			} else {
				wResult = wOMSOrder.getID();
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
	public void DeleteList(BMSEmployee wLoginUser, List<OMSOrder> wList, OutResult<Integer> wErrorCode) {
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return;
			}

			if (wList == null || wList.size() <= 0)
				return;

			List<String> wIDList = new ArrayList<String>();
			for (OMSOrder wItem : wList) {
				wIDList.add(String.valueOf(wItem.ID));
			}
			String wSql = MessageFormat.format("delete {1}.from oms_order WHERE ID IN({0}) ;",
					String.join(",", wIDList), wErrorCode);
			this.ExecuteSqlTransaction(wSql);
		} catch (Exception ex) {
			wErrorCode.set(MESException.DBSQL.getValue());
			logger.error(ex.toString());
		}
	}

	/**
	 * 查单条
	 * 
	 * @return
	 */
	public OMSOrder SelectByID(BMSEmployee wLoginUser, int wID, OutResult<Integer> wErrorCode) {
		OMSOrder wResult = new OMSOrder();
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return wResult;
			}

			Calendar wBaseTime = Calendar.getInstance();
			wBaseTime.set(2000, 1, 1);

			List<OMSOrder> wList = SelectList(wLoginUser, wID, -1, "", -1, -1, -1, "", "", -1, null, wBaseTime,
					wBaseTime, wBaseTime, wBaseTime, wErrorCode);
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
	public List<OMSOrder> SelectList(BMSEmployee wLoginUser, int wID, int wCommandID, String wOrderNo, int wLineID,
			int wProductID, int wBureauSectionID, String wPartNo, String wBOMNo, int wActive,
			List<Integer> wStateIDList, Calendar wPreStartTime, Calendar wPreEndTime, Calendar wRelStartTime,
			Calendar wRelEndTime, OutResult<Integer> wErrorCode) {
		List<OMSOrder> wResultList = new ArrayList<OMSOrder>();
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
			if (wPreStartTime == null || wPreStartTime.compareTo(wBaseTime) < 0)
				wPreStartTime = wBaseTime;
			if (wPreEndTime == null || wPreEndTime.compareTo(wBaseTime) < 0)
				wPreEndTime = wBaseTime;
			if (wPreStartTime.compareTo(wPreEndTime) > 0)
				return wResultList;

			if (wRelStartTime == null || wRelStartTime.compareTo(wBaseTime) < 0)
				wRelStartTime = wBaseTime;
			if (wRelEndTime == null || wRelEndTime.compareTo(wBaseTime) < 0)
				wRelEndTime = wBaseTime;
			if (wRelStartTime.compareTo(wRelEndTime) > 0)
				return wResultList;

			String wSQL = MessageFormat.format("SELECT * FROM {0}.oms_order WHERE  1=1  "
					+ "and ( :wID <= 0 or :wID = ID ) " + "and ( :wCommandID <= 0 or :wCommandID = CommandID ) "
					+ "and ( :wOrderNo is null or :wOrderNo = '''' or :wOrderNo = OrderNo ) "
					+ "and ( :wLineID <= 0 or :wLineID = LineID ) "
					+ "and ( :wProductID <= 0 or :wProductID = ProductID ) "
					+ "and ( :wBureauSectionID <= 0 or :wBureauSectionID = BureauSectionID ) "
					+ "and ( :wPartNo is null or :wPartNo = '''' or :wPartNo = PartNo ) "
					+ "and ( :wBOMNo is null or :wBOMNo = '''' or :wBOMNo = BOMNo ) "
					+ "and ( :wStatus is null or :wStatus = '''' or Status in ({1}))"
					+ "and ( :wPreStartTime <= str_to_date(''2010-01-01'', ''%Y-%m-%d'') or :wPreStartTime <= PlanReceiveDate) "
					+ "and ( :wPreEndTime <= str_to_date(''2010-01-01'', ''%Y-%m-%d'') or :wPreEndTime >= PlanReceiveDate) "
					+ "and ( :wRelStartTime <= str_to_date(''2010-01-01'', ''%Y-%m-%d'') or :wRelStartTime <= RealFinishDate) "
					+ "and ( :wRelEndTime <= str_to_date(''2010-01-01'', ''%Y-%m-%d'') or :wRelEndTime >= RealReceiveDate) "
					+ "and ( :wActive <= 0 or :wActive = Active );", wInstance.Result,
					wStateIDList.size() > 0 ? StringUtils.Join(",", wStateIDList) : "0");

			Map<String, Object> wParamMap = new HashMap<String, Object>();

			wParamMap.put("wID", wID);
			wParamMap.put("wCommandID", wCommandID);
			wParamMap.put("wOrderNo", wOrderNo);
			wParamMap.put("wLineID", wLineID);
			wParamMap.put("wProductID", wProductID);
			wParamMap.put("wBureauSectionID", wBureauSectionID);
			wParamMap.put("wPartNo", wPartNo);
			wParamMap.put("wBOMNo", wBOMNo);
			wParamMap.put("wActive", wActive);
			wParamMap.put("wPreStartTime", wPreStartTime);
			wParamMap.put("wPreEndTime", wPreEndTime);
			wParamMap.put("wRelStartTime", wRelStartTime);
			wParamMap.put("wRelEndTime", wRelEndTime);
			wParamMap.put("wStatus", StringUtils.Join(",", wStateIDList));

			wSQL = this.DMLChange(wSQL);

			List<Map<String, Object>> wQueryResult = nameJdbcTemplate.queryForList(wSQL, wParamMap);

			for (Map<String, Object> wReader : wQueryResult) {
				OMSOrder wItem = new OMSOrder();

				wItem.ID = StringUtils.parseInt(wReader.get("ID"));
				wItem.CommandID = StringUtils.parseInt(wReader.get("CommandID"));
				wItem.ERPID = StringUtils.parseInt(wReader.get("ERPID"));
				wItem.OrderNo = StringUtils.parseString(wReader.get("OrderNo"));
				wItem.LineID = StringUtils.parseInt(wReader.get("LineID"));
				wItem.ProductID = StringUtils.parseInt(wReader.get("ProductID"));
				wItem.BureauSectionID = StringUtils.parseInt(wReader.get("BureauSectionID"));
				wItem.WBSNo = StringUtils.parseString(wReader.get("WBSNo"));
				wItem.PartNo = StringUtils.parseString(wReader.get("PartNo"));
				wItem.BOMNo = StringUtils.parseString(wReader.get("BOMNo"));
				wItem.Priority = StringUtils.parseInt(wReader.get("Priority"));
				wItem.Status = StringUtils.parseInt(wReader.get("Status"));
				wItem.PlanReceiveDate = StringUtils.parseCalendar(wReader.get("PlanReceiveDate"));
				wItem.RealReceiveDate = StringUtils.parseCalendar(wReader.get("RealReceiveDate"));
				wItem.PlanFinishDate = StringUtils.parseCalendar(wReader.get("PlanFinishDate"));
				wItem.RealStartDate = StringUtils.parseCalendar(wReader.get("RealStartDate"));
				wItem.RealFinishDate = StringUtils.parseCalendar(wReader.get("RealFinishDate"));
				wItem.RealSendDate = StringUtils.parseCalendar(wReader.get("RealSendDate"));
				wItem.Remark = StringUtils.parseString(wReader.get("Remark"));
				wItem.CreateID = StringUtils.parseInt(wReader.get("CreateID"));
				wItem.CreateTime = StringUtils.parseCalendar(wReader.get("CreateTime"));
				wItem.EditID = StringUtils.parseInt(wReader.get("EditID"));
				wItem.EditTime = StringUtils.parseCalendar(wReader.get("EditTime"));
				wItem.AuditorID = StringUtils.parseInt(wReader.get("AuditorID"));
				wItem.AuditTime = StringUtils.parseCalendar(wReader.get("AuditTime"));
				wItem.Active = StringUtils.parseInt(wReader.get("Active"));

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
	public void Active(BMSEmployee wLoginUser, List<Integer> wIDList, int wActive, OutResult<Integer> wErrorCode) {
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return;
			}

			if (wIDList == null || wIDList.size() <= 0)
				return;
			if (wActive != 0 && wActive != 1)
				return;
			for (Integer wItem : wIDList) {
				OMSOrder wOMSOrder = SelectByID(wLoginUser, wItem, wErrorCode);
				if (wOMSOrder == null || wOMSOrder.ID <= 0)
					continue;
				wOMSOrder.Active = wActive;
				long wID = Update(wLoginUser, wOMSOrder, wErrorCode);
				if (wID <= 0)
					break;
			}
		} catch (Exception e) {
			wErrorCode.set(MESException.DBSQL.getValue());
			logger.error(e.toString());
		}
	}

	private OMSOrderDAO() {
		super();
	}

	public static OMSOrderDAO getInstance() {
		if (Instance == null)
			Instance = new OMSOrderDAO();
		return Instance;
	}
}
