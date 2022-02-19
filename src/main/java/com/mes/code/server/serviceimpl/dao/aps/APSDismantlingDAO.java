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
import com.mes.code.server.service.po.aps.APSDismantling;
import com.mes.code.server.service.po.bms.BMSEmployee;
import com.mes.code.server.service.utils.StringUtils;
import com.mes.code.server.serviceimpl.dao.BaseDAO;

/**
 * 
 * @author PengYouWang
 * @CreateTime 2019年12月27日12:44:48
 * @LastEditTime 2019年12月27日12:44:56
 *
 */
public class APSDismantlingDAO extends BaseDAO {

	private static Logger logger = LoggerFactory.getLogger(APSDismantlingDAO.class);

	private static APSDismantlingDAO Instance = null;

	/**
	 * 添加或修改
	 * 
	 * @param wAPSDismantling
	 * @return
	 */
	public long Update(BMSEmployee wLoginUser, APSDismantling wAPSDismantling, OutResult<Integer> wErrorCode) {
		int wResult = 0;
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return wResult;
			}

			if (wAPSDismantling == null)
				return 0;

			String wSQL = "";
			if (wAPSDismantling.getID() <= 0) {
				wSQL = MessageFormat.format(
						"INSERT INTO {0}.aps_dismantling(LineID,PartID,ProductID,MaterialID,SupplierID,LinkManID,ProPurchaseDur,SupPurchaseDur,CreatorID,CreateTime,EditorID,EditTime,AuditorID,AuditTime,Status) VALUES(:LineID,:PartID,:ProductID,:MaterialID,:SupplierID,:LinkManID,:ProPurchaseDur,:SupPurchaseDur,:CreatorID,:CreateTime,:EditorID,:EditTime,:AuditorID,:AuditTime,:Status);",
						wInstance.Result);
			} else {
				wSQL = MessageFormat.format(
						"UPDATE {0}.aps_dismantling SET LineID = :LineID,PartID = :PartID,ProductID = :ProductID,MaterialID = :MaterialID,SupplierID = :SupplierID,LinkManID = :LinkManID,ProPurchaseDur = :ProPurchaseDur,SupPurchaseDur = :SupPurchaseDur,CreatorID = :CreatorID,CreateTime = :CreateTime,EditorID = :EditorID,EditTime = :EditTime,AuditorID = :AuditorID,AuditTime = :AuditTime,Status = :Status WHERE ID = :ID;",
						wInstance.Result);
			}

			wSQL = this.DMLChange(wSQL);

			Map<String, Object> wParamMap = new HashMap<String, Object>();

			wParamMap.put("ID", wAPSDismantling.ID);
			wParamMap.put("LineID", wAPSDismantling.LineID);
			wParamMap.put("PartID", wAPSDismantling.PartID);
			wParamMap.put("ProductID", wAPSDismantling.ProductID);
			wParamMap.put("MaterialID", wAPSDismantling.MaterialID);
			wParamMap.put("SupplierID", wAPSDismantling.SupplierID);
			wParamMap.put("LinkManID", wAPSDismantling.LinkManID);
			wParamMap.put("ProPurchaseDur", wAPSDismantling.ProPurchaseDur);
			wParamMap.put("SupPurchaseDur", wAPSDismantling.SupPurchaseDur);
			wParamMap.put("CreatorID", wAPSDismantling.CreatorID);
			wParamMap.put("CreateTime", wAPSDismantling.CreateTime);
			wParamMap.put("EditorID", wAPSDismantling.EditorID);
			wParamMap.put("EditTime", wAPSDismantling.EditTime);
			wParamMap.put("AuditorID", wAPSDismantling.AuditorID);
			wParamMap.put("AuditTime", wAPSDismantling.AuditTime);
			wParamMap.put("Status", wAPSDismantling.Status);

			KeyHolder keyHolder = new GeneratedKeyHolder();
			SqlParameterSource wSqlParameterSource = new MapSqlParameterSource(wParamMap);

			nameJdbcTemplate.update(wSQL, wSqlParameterSource, keyHolder);

			if (wAPSDismantling.getID() <= 0) {
				wResult = keyHolder.getKey().intValue();
				wAPSDismantling.setID(wResult);
			} else {
				wResult = wAPSDismantling.getID();
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
	public ServiceResult<Integer> DeleteList(BMSEmployee wLoginUser, List<APSDismantling> wList,
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
			for (APSDismantling wItem : wList) {
				wIDList.add(String.valueOf(wItem.ID));
			}
			String wSql = MessageFormat.format("delete from {1}.aps_dismantling WHERE ID IN({0}) ;",
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
	public APSDismantling SelectByID(BMSEmployee wLoginUser, int wID, OutResult<Integer> wErrorCode) {
		APSDismantling wResult = new APSDismantling();
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return wResult;
			}

			List<APSDismantling> wList = SelectList(wLoginUser, wID, -1, -1, -1, -1, wErrorCode);
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
	public List<APSDismantling> SelectList(BMSEmployee wLoginUser, int wID, int wLineID, int wPartID, int wProductID,
			int wMaterialID, OutResult<Integer> wErrorCode) {
		List<APSDismantling> wResultList = new ArrayList<APSDismantling>();
		try {
			ServiceResult<String> wInstance = this.GetDataBaseName(wLoginUser.getCompanyID(), MESDBSource.APS,
					wLoginUser.getID(), 0);
			wErrorCode.set(wInstance.ErrorCode);
			if (wErrorCode.Result != 0) {
				return wResultList;
			}

			String wSQL = MessageFormat.format(
					"SELECT * FROM {0}.aps_dismantling WHERE  1=1  and ( :wID <= 0 or :wID = ID ) and ( :wLineID <= 0 or :wLineID = LineID ) and ( :wPartID <= 0 or :wPartID = PartID ) and ( :wProductID <= 0 or :wProductID = ProductID ) and ( :wMaterialID <= 0 or :wMaterialID = MaterialID );",
					wInstance.Result);

			Map<String, Object> wParamMap = new HashMap<String, Object>();

			wParamMap.put("wID", wID);
			wParamMap.put("wLineID", wLineID);
			wParamMap.put("wPartID", wPartID);
			wParamMap.put("wProductID", wProductID);
			wParamMap.put("wMaterialID", wMaterialID);

			wSQL = this.DMLChange(wSQL);

			List<Map<String, Object>> wQueryResult = nameJdbcTemplate.queryForList(wSQL, wParamMap);

			for (Map<String, Object> wReader : wQueryResult) {
				APSDismantling wItem = new APSDismantling();

				wItem.ID = StringUtils.parseInt(wReader.get("ID"));
				wItem.LineID = StringUtils.parseInt(wReader.get("LineID"));
				wItem.PartID = StringUtils.parseInt(wReader.get("PartID"));
				wItem.ProductID = StringUtils.parseInt(wReader.get("ProductID"));
				wItem.MaterialID = StringUtils.parseInt(wReader.get("MaterialID"));
				wItem.SupplierID = StringUtils.parseInt(wReader.get("SupplierID"));
				wItem.LinkManID = StringUtils.parseInt(wReader.get("LinkManID"));
				wItem.ProPurchaseDur = StringUtils.parseDouble(wReader.get("ProPurchaseDur"));
				wItem.SupPurchaseDur = StringUtils.parseDouble(wReader.get("SupPurchaseDur"));
				wItem.CreatorID = StringUtils.parseInt(wReader.get("CreatorID"));
				wItem.CreateTime = StringUtils.parseCalendar(wReader.get("CreateTime"));
				wItem.EditorID = StringUtils.parseInt(wReader.get("EditorID"));
				wItem.EditTime = StringUtils.parseCalendar(wReader.get("EditTime"));
				wItem.AuditorID = StringUtils.parseInt(wReader.get("AuditorID"));
				wItem.AuditTime = StringUtils.parseCalendar(wReader.get("AuditTime"));
				wItem.Status = StringUtils.parseInt(wReader.get("Status"));

				wResultList.add(wItem);
			}
		} catch (Exception ex) {
			wErrorCode.set(MESException.DBSQL.getValue());
			logger.error(ex.toString());
		}
		return wResultList;
	}

	private APSDismantlingDAO() {
		super();
	}

	public static APSDismantlingDAO getInstance() {
		if (Instance == null)
			Instance = new APSDismantlingDAO();
		return Instance;
	}
}
