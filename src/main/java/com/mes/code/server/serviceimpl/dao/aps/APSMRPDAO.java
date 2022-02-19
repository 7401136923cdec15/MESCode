package com.mes.code.server.serviceimpl.dao.aps;

import com.mes.code.server.serviceimpl.dao.BaseDAO;

public class APSMRPDAO extends BaseDAO {
	private static APSMRPDAO Instance = null;

	private APSMRPDAO() {
		super();
	}

	public static APSMRPDAO getInstance() {
		if (Instance == null)
			Instance = new APSMRPDAO();
		return Instance;
	}

	// ERP关联接口
//	public int APS_QueryERPEntryIDByTaskLineID(int wCompanyID, int wLoginID, int wTaskLineID,
//			OutResult<Integer> wErrorCode) {
//		int wEntryID = 0;
//		try {
//			ServiceResult<String> wInstance = this.GetDataBaseName(wCompanyID, MESDBSource.Basic);
//			wErrorCode.set(wInstance.ErrorCode);
//			if (wErrorCode.Result == 0) {
//				String wSQLText = StringUtils.Format("Select o.EntryID from {0}.aps_taskline t,{1}.oms_mesorder o",
//						wInstance.Result, wInstance.Result) + " where t.ID=:ID and t.OrderID=o.ID";
//				Map<String, Object> wParms = new HashMap<String, Object>();
//				wParms.put("ID", wTaskLineID);
//
//				List<Map<String, Object>> wQueryResultList = nameJdbcTemplate.queryForList(wSQLText, wParms);
//				for (Map<String, Object> wSqlDataReader : wQueryResultList) {
//					wEntryID = StringUtils.parseInt(wSqlDataReader.get("EntryID"));
//				}
//			}
//		} catch (Exception ex) {
//			LoggerTool.SaveException("APSService", "APS_QueryERPEntryIDByTaskLineID",
//					"Function Exception:" + ex.toString());
//		}
//		return wEntryID;
//	}
//
//	// 静态函数（内部交互）
//	public int APS_SaveMaterial(int wCompanyID, int wLoginID, APSMaterial wMaterial, OutResult<Integer> wErrorCode) {
//		wErrorCode.set(0);
//		int wID = 0;
//		try {
//			ServiceResult<String> wInstance = this.GetDataBaseName(wCompanyID, MESDBSource.Basic, wLoginID, 500301);
//
//			wErrorCode.set(wInstance.ErrorCode);
//
//			if (wErrorCode.Result == 0) {
//				Map<String, Object> wParms = new HashMap<String, Object>();
//				String wSQLText = "";
//
//				if (wMaterial.ID < 1) {
//					wSQLText = StringUtils.Format("Insert Into {0}.aps_material", wInstance.Result)
//							+ "(OrderID,TaskLineID,TaskPartID,TaskStepID,MaterialID,LineID,PartID,PartPointID,FQTYShift,FQTYDemand,FQTYOnFactory,"
//							+ " FQTYMargin,ShiftID,Status,PlanerID,SubmitTime,SessionTime,Active)  Values(:OrderID,:TaskLineID,:TaskPartID,:TaskStepID,:MaterialID,:LineID,:PartID,:PartPointID,:FQTYShift,:FQTYDemand,"
//							+ " :FQTYOnFactory,:FQTYMargin,:ShiftID,:Status,:PlanerID,:SubmitTime,:SessionTime,:Active);";
//					wParms.clear();
//
//					wParms.put("OrderID", wMaterial.OrderID);
//					wParms.put("TaskLineID", wMaterial.TaskLineID);
//					wParms.put("TaskPartID", wMaterial.TaskPartID);
//					wParms.put("TaskStepID", wMaterial.TaskStepID);
//					wParms.put("MaterialID", wMaterial.MaterialID);
//					wParms.put("LineID", wMaterial.LineID);
//					wParms.put("PartID", wMaterial.PartID);
//					wParms.put("PartPointID", wMaterial.PartPointID);
//					wParms.put("ShiftID", wMaterial.ShiftID);
//					wParms.put("FQTYShift", wMaterial.FQTYShift);
//					wParms.put("FQTYDemand", wMaterial.FQTYDemand);
//					wParms.put("FQTYOnFactory", wMaterial.FQTYOnFactory);
//					wParms.put("FQTYMargin", wMaterial.FQTYMargin);
//
//					wParms.put("Status", 1);
//					wParms.put("PlanerID", wLoginID);
//					wParms.put("SubmitTime", Calendar.getInstance());
//					wParms.put("SessionTime", Calendar.getInstance());
//					wParms.put("Active", 0);
//					wSQLText = this.DMLChange(wSQLText);
//					KeyHolder keyHolder = new GeneratedKeyHolder();
//
//					SqlParameterSource wSqlParameterSource = new MapSqlParameterSource(wParms);
//					nameJdbcTemplate.update(wSQLText, wSqlParameterSource, keyHolder);
//
//					wID = keyHolder.getKey().intValue();
//				} else {
//					wSQLText = StringUtils.Format("Update {0}.aps_material", wInstance.Result)
//							+ " set FQTYShift=:FQTYShift,PlanerID=:PlanerID " + " where ID=:ID and ShiftID=:ShiftID";
//					wParms.clear();
//
//					wParms.put("FQTYShift", wMaterial.FQTYShift);
//					wParms.put("PlanerID", wLoginID);
//
//					wParms.put("ID", wMaterial.ID);
//					wParms.put("ShiftID", wMaterial.ShiftID);
//					wSQLText = this.DMLChange(wSQLText);
//					nameJdbcTemplate.update(wSQLText, wParms);
//					FMCFactoryDAO.getInstance().FMC_LoadFactoyEntry(wCompanyID, wErrorCode);
//				}
//			}
//		} catch (Exception ex) {
//			wErrorCode.set(MESException.DBSQL.getValue());
//
//			LoggerTool.SaveException("APSService", "APS_SaveMaterial", "Function Exception:" + ex.toString());
//		}
//		return wID;
//	}
//
//	public List<APSMaterial> APS_SetMaterialListText(int wCompanyID, List<APSMaterial> wMaterialList,
//			OutResult<Integer> wErrorCode) {
//		try {
//			// Step01：人员姓名
//			@SuppressWarnings("unused")
//			MESEntry wEntryEmployee = MESServer.MES_QueryEntryByMemory(wCompanyID, MESEntryEnum.BMSModel);
//			// Step02：物料档案
//			@SuppressWarnings("unused")
//			MESEntry wMSSModel = MESServer.MES_QueryEntryByMemory(wCompanyID, MESEntryEnum.MSSModel);
//			// Step03：工艺模型
//			MESEntry wRouteModel = MESServer.MES_QueryEntryByMemory(wCompanyID, MESEntryEnum.RouteModel);
//			// Step04：工厂与事业部
//			MESEntry wFactoryModel = MESServer.MES_QueryEntryByMemory(wCompanyID, MESEntryEnum.FactoryModel);
//			for (APSMaterial wMaterial : wMaterialList) {
//				MSSMaterial wItem = MSSMaterialDAO.getInstance().MSS_QueryMaterialByID(wCompanyID, 0,
//						wMaterial.MaterialID, wErrorCode);
//				wMaterial.MaterialNo = wItem.MaterialNo;
//				wMaterial.MaterialName = wItem.MaterialName;
//
//				wMaterial.LineName = FMCLineDAO.getInstance().FMC_QueryLineNameByID(wCompanyID, wMaterial.LineID,
//						wFactoryModel);
//				wMaterial.PartName = FPCPartDAO.getInstance().FPC_QueryPartNameByID(wCompanyID, wMaterial.PartID,
//						wRouteModel);
//				wMaterial.PartPointName = FPCPartDAO.getInstance().FPC_QueryPartPointNameByID(wCompanyID,
//						wMaterial.PartPointID, wRouteModel);
//			}
//		} catch (Exception ex) {
//			wErrorCode.set(MESException.Exception.getValue());
//			LoggerTool.SaveException("APSService", "APS_SetMaterialListText", "Function Exception:" + ex.toString());
//		}
//		return wMaterialList;
//	}
//
//	public List<APSMaterial> APS_QueryMaterialListByShiftID(int wCompanyID, int wLoginID, int wLineID, int wShiftID,
//			OutResult<Integer> wErrorCode) {
//		wErrorCode.set(0);
//		List<APSMaterial> wMaterialList = new ArrayList<APSMaterial>();
//		ServiceResult<String> wInstance = this.GetDataBaseName(wCompanyID, MESDBSource.Basic);
//		if (wInstance.Result.length() < 1)
//			return wMaterialList;
//
//		try {
//
//			String wCondition = "";
//			if (wLineID > 0)
//				wCondition = StringUtils.Format(" {0} and t.LineID={1}", wCondition, wLineID);
//
//			String wSQLText = StringUtils.Format(
//					"Select O.OrderNo,O.ProductNo,O.MaterialNo,O.MaterialName,O.FQTY,O.BOMNo,O.Priority,t.* from {0}.aps_material t,{1}.oms_mesorder O",
//					wInstance.Result, wInstance.Result) + " where t.OrderID=O.ID and t.ShiftID=:ShiftID" + wCondition
//					+ " Order By t.TaskPartID ";
//			Map<String, Object> wParms = new HashMap<String, Object>();
//			wParms.put("ShiftID", wShiftID);
//
//			List<Map<String, Object>> wQueryResultList = nameJdbcTemplate.queryForList(wSQLText, wParms);
//			for (Map<String, Object> wSqlDataReader : wQueryResultList) {
//				APSMaterial wMaterial = new APSMaterial();
//				wMaterial.ID = StringUtils.parseInt(wSqlDataReader.get("ID"));
//				wMaterial.MaterialID = StringUtils.parseInt(wSqlDataReader.get("MaterialID"));
//				wMaterial.OrderID = StringUtils.parseInt(wSqlDataReader.get("OrderID"));
//				wMaterial.OrderNo = StringUtils.parseString(wSqlDataReader.get("OrderNo"));
//				wMaterial.ProductNo = StringUtils.parseString(wSqlDataReader.get("ProductNo"));
//				wMaterial.TaskLineID = StringUtils.parseInt(wSqlDataReader.get("TaskLineID"));
//				wMaterial.TaskPartID = StringUtils.parseInt(wSqlDataReader.get("TaskPartID"));
//				wMaterial.TaskStepID = StringUtils.parseInt(wSqlDataReader.get("TaskStepID"));
//				wMaterial.LineID = StringUtils.parseInt(wSqlDataReader.get("LineID"));
//
//				wMaterial.PartID = StringUtils.parseInt(wSqlDataReader.get("PartID"));
//				wMaterial.PartPointID = StringUtils.parseInt(wSqlDataReader.get("PartPointID"));
//				wMaterial.FQTYShift = StringUtils.parseFloat(wSqlDataReader.get("FQTYShift"));
//				wMaterial.FQTYDemand = StringUtils.parseFloat(wSqlDataReader.get("FQTYDemand"));
//
//				wMaterial.FQTYOnFactory = StringUtils.parseFloat(wSqlDataReader.get("FQTYOnFactory"));
//				wMaterial.FQTYMargin = StringUtils.parseFloat(wSqlDataReader.get("FQTYMargin"));
//				wMaterial.ShiftID = StringUtils.parseInt(wSqlDataReader.get("ShiftID"));
//				wMaterial.SubmitTime = StringUtils.parseCalendar(wSqlDataReader.get("SubmitTime"));
//
//				wMaterialList.add(wMaterial);
//			}
//
//			wMaterialList = this.APS_SetMaterialListText(wCompanyID, wMaterialList, wErrorCode);
//		} catch (Exception ex) {
//			wErrorCode.set(MESException.DBSQL.getValue());
//			LoggerTool.SaveException("APSService", "APS_QueryMaterialListByShiftID",
//					"Function Exception:" + ex.toString());
//		}
//		return wMaterialList;
//	}
//
//	// 静态函数（服务交互）
//	public APSOrder APS_QueryOrderByID(int wCompanyID, int wID, OutResult<Integer> wErrorCode) {
//		wErrorCode.set(0);
//		APSOrder wOrder = new APSOrder();
//
//		try {
//			ServiceResult<String> wInstance = this.GetDataBaseName(wCompanyID, MESDBSource.Basic);
//			wErrorCode.set(wInstance.ErrorCode);
//			if (wErrorCode.Result == 0) {
//				// wSqlDataReader\[(\"\w+\")\] wSqlDataReader.get($1)
//				// Step01:条件
//				String wSQLText = StringUtils.Format("Select * from {0}.oms_mesorder where ID=:ID ", wInstance.Result);
//				Map<String, Object> wParms = new HashMap<String, Object>();
//				wParms.put("ID", wID);
//				List<Map<String, Object>> wQueryResultList = nameJdbcTemplate.queryForList(wSQLText, wParms);
//				for (Map<String, Object> wSqlDataReader : wQueryResultList) {
//
//					wOrder.ID = StringUtils.parseInt(wSqlDataReader.get("ID"));
//					wOrder.OrderNo = StringUtils.parseString(wSqlDataReader.get("OrderNo"));
//					wOrder.ProductNo = StringUtils.parseString(wSqlDataReader.get("ProductNo"));
//					wOrder.MaterialNo = StringUtils.parseString(wSqlDataReader.get("MaterialNo"));
//					wOrder.MaterialName = StringUtils.parseString(wSqlDataReader.get("MaterialName"));
//
//					wOrder.FQTY = StringUtils.parseInt(wSqlDataReader.get("FQTY"));
//					wOrder.FQTYDone = StringUtils.parseInt(wSqlDataReader.get("FQTYDone"));
//					wOrder.WorkShopID = StringUtils.parseInt(wSqlDataReader.get("WorkShopID"));
//					wOrder.LineID = StringUtils.parseInt(wSqlDataReader.get("LineID"));
//					wOrder.PartID = StringUtils.parseInt(wSqlDataReader.get("PartID"));
//					wOrder.BOMNo = StringUtils.parseString(wSqlDataReader.get("BOMNo"));
//
//					wOrder.Priority = StringUtils.parseInt(wSqlDataReader.get("Priority"));
//					wOrder.Status = StringUtils.parseInt(wSqlDataReader.get("Status"));
//					wOrder.Type = StringUtils.parseShort(wSqlDataReader.get("Type"));
//					wOrder.StartTime = StringUtils.parseCalendar(wSqlDataReader.get("StartTime"));
//					wOrder.EndTime = StringUtils.parseCalendar(wSqlDataReader.get("FinishTime"));
//
//					wOrder.CreatorID = StringUtils.parseInt(wSqlDataReader.get("CreatorID"));
//					wOrder.AuditorID = StringUtils.parseInt(wSqlDataReader.get("AuditorID"));
//					wOrder.CreateTime = StringUtils.parseCalendar(wSqlDataReader.get("CreateTime"));
//					wOrder.AuditTime = StringUtils.parseCalendar(wSqlDataReader.get("AuditTime"));
//					boolean wDBActive = StringUtils.parseBoolean(wSqlDataReader.get("Active"));
//					wOrder.Active = wDBActive ? 1 : 0;
//				}
//
//			}
//		} catch (Exception ex) {
//
//			LoggerTool.SaveException("APSService", "APS_QueryOrderByID", "Function Exception:" + ex.toString());
//		}
//		return wOrder;
//	}
//
//	public APSMRP APS_GenerateMaterialListByBOMItem(int wCompanyID, String wBOMNo, int wGradeID,
//			APSMaterial wParentMaterial, List<MSSBOMItem> wBOMItemList, OutResult<Integer> wErrorCode) {
//		wErrorCode.set(0);
//		APSMRP wMRP = new APSMRP();
//		try {
//			// Step01:订单一级物料计算
//			wGradeID++;
//			for (MSSBOMItem wItem : wBOMItemList) {
//				if (wItem.MaterialUnitRatio > 0 && wItem.MaterialUnit > 0) {
//					float wDemandFQTY = wParentMaterial.FQTYDemand * wItem.MaterialUnit / wItem.MaterialUnitRatio;
//					MSSMaterial wBOM_Material = MSSMaterialDAO.getInstance().MSS_QueryMaterialByID(wCompanyID,
//							wItem.MaterialID, wErrorCode);
//					// Step01:需求日期、数量
//					APSMaterial wMaterial = new APSMaterial();
//					wMaterial.FQTYDemand = wDemandFQTY;
//					wMaterial.MaterialID = wBOM_Material.ID;
//					wMaterial.MaterialNo = wBOM_Material.MaterialNo;
//					wMaterial.MaterialName = wBOM_Material.MaterialName;
//					wMaterial.PartPointID = wItem.PartPointID;
//					if (wItem.ItemList.size() < 1)
//						wMRP.MaterialList.add(wMaterial);
//					else {
//						APSMRP wSonMRP = this.APS_GenerateMaterialListByBOMItem(wCompanyID, wBOMNo, wGradeID, wMaterial,
//								wItem.ItemList, wErrorCode);
//						wMRP.MaterialList.addAll(wSonMRP.MaterialList);
//						wMRP.MessageList.addAll(wSonMRP.MessageList);
//					}
//				} else {
//					// Exception Text
//					APSMessage wMessage = new APSMessage();
//					wMessage.MessageText = StringUtils.Format("BOM:{0} 子项物料号{1},物料名称{2}，用量非法", wBOMNo, wItem.MaterialNo,
//							wItem.MaterialName);
//					wMRP.MessageList.add(wMessage);
//				}
//			}
//		} catch (Exception ex) {
//			wErrorCode.set(MESException.Exception.getValue());
//			LoggerTool.SaveException("APSService", "APS_GenerateMaterialListByBOMItem",
//					"Function Exception:" + ex.toString());
//		}
//		return wMRP;
//	}
//
//	public APSMRP APS_GenerateMaterialListByOrder(int wCompanyID, int wLoginID, int wGradeID, APSOrder wOrder,
//			OutResult<Integer> wErrorCode) {
//		wErrorCode.set(0);
//		APSMRP wMRP = new APSMRP();
//		try {
//			// Step01:订单一级物料计算
//			wGradeID++;
//			MSSBOM wBOM = MSSMaterialDAO.getInstance().MSS_QueryBOMByBOMNo(wCompanyID, wOrder.BOMNo, wErrorCode);
//
//			if (wBOM.ID > 0) {
//				MSSMaterial wOrder_Material = MSSMaterialDAO.getInstance().MSS_QueryMaterialByID(wCompanyID,
//						wBOM.MaterialID, wErrorCode);
//				APSMaterial wMaterial = new APSMaterial();
//				wMaterial.FQTYDemand = wOrder.FQTYShift;
//				wMaterial.MaterialID = wOrder_Material.ID;
//				wMaterial.MaterialNo = wOrder_Material.MaterialNo;
//				wMaterial.MaterialName = wOrder_Material.MaterialName;
//
//				wMRP = this.APS_GenerateMaterialListByBOMItem(wCompanyID, wOrder.BOMNo, wGradeID, wMaterial,
//						wBOM.ItemList, wErrorCode);
//				for (APSMaterial wItem : wMRP.MaterialList) {
//					wItem.LineID = wOrder.LineID;
//					wItem.OrderID = wOrder.ID;
//				}
//				for (APSMessage wMessage : wMRP.MessageList) {
//					wMessage.LineID = wOrder.LineID;
//				}
//			}
//		} catch (Exception ex) {
//			wErrorCode.set(MESException.Exception.getValue());
//			LoggerTool.SaveException("APSService", "APS_GenerateMaterialListByOrder",
//					"Function Exception:" + ex.toString());
//		}
//		return wMRP;
//	}
//
//	public APSMRP APS_GenerateMaterialListByTaskPart(int wCompanyID, int wLoginID, int wGradeID, SFCTaskPart wTaskPart,
//			OutResult<Integer> wErrorCode) {
//		wErrorCode.set(0);
//		APSMRP wMRP = new APSMRP();
//		try {
//			// Step01:订单一级物料计算
//			wGradeID++;
//			MSSBOM wBOM = MSSMaterialDAO.getInstance().MSS_QueryBOMByBOMNo(wCompanyID, wTaskPart.BOMNo, wErrorCode);
//			if (wBOM.ID > 0) {
//				MSSMaterial wOrder_Material = MSSMaterialDAO.getInstance().MSS_QueryMaterialByID(wCompanyID,
//						wBOM.MaterialID, wErrorCode);
//				APSMaterial wMaterial = new APSMaterial();
//				wMaterial.FQTYDemand = wTaskPart.FQTYShift;
//				wMaterial.MaterialID = wOrder_Material.ID;
//				wMaterial.MaterialNo = wOrder_Material.MaterialNo;
//				wMaterial.MaterialName = wOrder_Material.MaterialName;
//
//				wMRP = this.APS_GenerateMaterialListByBOMItem(wCompanyID, wTaskPart.BOMNo, wGradeID, wMaterial,
//						wBOM.ItemList, wErrorCode);
//				for (APSMaterial wItem : wMRP.MaterialList) {
//					wItem.LineID = wTaskPart.LineID;
//					wItem.OrderID = wTaskPart.OrderID;
//				}
//				for (APSMessage wMessage : wMRP.MessageList) {
//					wMessage.LineID = wTaskPart.LineID;
//				}
//			}
//		} catch (Exception ex) {
//			wErrorCode.set(MESException.Exception.getValue());
//			LoggerTool.SaveException("APSService", "APS_GenerateMaterialListByTaskPart",
//					"Function Exception:" + ex.toString());
//		}
//		return wMRP;
//	}
//
//	public APSMRP APS_GenerateMaterialListByBOMNO(int wCompanyID, int wLoginID, int wGradeID, String wBOMNO,
//			OutResult<Integer> wErrorCode) {
//		wErrorCode.set(0);
//		APSMRP wMRP = new APSMRP();
//		try {
//			// Step01:订单一级物料计算
//			wGradeID++;
//			MSSBOM wBOM = MSSMaterialDAO.getInstance().MSS_QueryBOMByBOMNo(wCompanyID, wBOMNO, wErrorCode);
//			if (wBOM.ID > 0) {
//				MSSMaterial wOrderMaterial = MSSMaterialDAO.getInstance().MSS_QueryMaterialByID(wCompanyID,
//						wBOM.MaterialID, wErrorCode);
//				APSMaterial wMaterial = new APSMaterial();
//				wMaterial.FQTYDemand = (float) 1.0;
//				wMaterial.MaterialID = wOrderMaterial.ID;
//				wMaterial.MaterialNo = wOrderMaterial.MaterialNo;
//				wMaterial.MaterialName = wOrderMaterial.MaterialName;
//
//				wMRP = this.APS_GenerateMaterialListByBOMItem(wCompanyID, wBOMNO, wGradeID, wMaterial, wBOM.ItemList,
//						wErrorCode);
//			}
//		} catch (Exception ex) {
//			wErrorCode.set(MESException.Exception.getValue());
//			LoggerTool.SaveException("APSService", "APS_GenerateMaterialListByBOMNO",
//					"Function Exception:" + ex.toString());
//		}
//		return wMRP;
//	}
//
//	public List<APSMessage> APS_CheckOrderRoute(int wCompanyID, APSOrder wOrder, OutResult<Integer> wErrorCode) {
//		List<APSMessage> wMessageList = new ArrayList<APSMessage>();
//		try {
//			if (wOrder.LineID > 0) {
//				FPCRoute wRoute = FPCRouteDAO.getInstance().FPC_QueryRouteByLineID(wCompanyID, wOrder.LineID,
//						wOrder.ProductNo, wErrorCode);
//				if (wRoute.ID < 1) {
//					APSMessage wMessage = new APSMessage();
//					wMessage.MessageText = StringUtils.Format("{0}的工艺路线不存在", wOrder.ProductNo);
//					wMessageList.add(wMessage);
//				}
//				MESEntry wFactoryModel = MESServer.MES_QueryEntryByMemory(wCompanyID, MESEntryEnum.FactoryModel);
//				FMCLine wLine = FMCLineDAO.getInstance().FMC_QueryLineByID(wCompanyID, wOrder.LineID, wFactoryModel);
//				if (wLine.ID < 1) {
//					APSMessage wMessage = new APSMessage();
//					wMessage.MessageText = StringUtils.Format("ID={0}产线不存在", wOrder.LineID);
//					wMessageList.add(wMessage);
//				}
//				// Step01:Check Product Route
//				if (wRoute.PartList.size() < 1) {
//					APSMessage wMessage = new APSMessage();
//					wMessage.MessageText = StringUtils.Format("{0}的工艺路线，工序为空", wRoute.VersionNo);
//					wMessageList.add(wMessage);
//				}
//				for (FPCRoutePart wRoutePart : wRoute.PartList) {
//					if (wRoutePart.PartPointList.size() < 1) {
//						APSMessage wMessage = new APSMessage();
//						wMessage.MessageText = StringUtils.Format("{0}的工艺路线，工序{1}的工步为空", wRoute.VersionNo,
//								wRoutePart.Name);
//						wMessageList.add(wMessage);
//					}
//				}
//				// Step02:Check Product Route& Line Route
//				if (wLine.ID > 0) {
//					wErrorCode.set(0);
//					List<FMCLineUnit> wLineUnitList = FMCLineDAO.getInstance().FMC_QueryLineUnitListByLineID(wCompanyID,
//							wLine.ID, false, wErrorCode);
//					if (wLineUnitList.size() < 1) {
//						APSMessage wMessage = new APSMessage();
//						wMessage.MessageText = StringUtils.Format("ID={0}产线的工艺路线未设置", wOrder.LineID);
//						wMessageList.add(wMessage);
//					}
//					if (wErrorCode.Result != 0) {
//						APSMessage wMessage = new APSMessage();
//						wMessage.MessageText = StringUtils.Format("查询{0}的工艺路线时出错", wRoute.VersionNo, wLine.Name);
//						wMessageList.add(wMessage);
//					}
//					int wPartOrderID = 0;
//					for (FPCRoutePart wRoutePart : wRoute.PartList) {
//						boolean wExistPart = false;
//						for (FMCLineUnit wPartUnit : wLineUnitList) {
//							if (wPartUnit.UnitID == wRoutePart.PartID) {
//								wExistPart = true;
//								@SuppressWarnings("unused")
//								int wStepOrderID = 0;
//								if (wPartUnit.OrderID >= wPartOrderID) {
//									wPartOrderID = wPartUnit.OrderID;
//									for (FPCRoutePartPoint wPartPoint : wRoutePart.PartPointList) {
//										boolean wExistStep = false;
//										for (FMCLineUnit wStepUnit : wPartUnit.UnitList) {
//											if (wStepUnit.UnitID == wPartPoint.PartPointID) {
//												wExistStep = true;
//												if (wStepUnit.OrderID >= wPartOrderID) {
//													wStepOrderID = wStepUnit.OrderID;
//												} else {
//													APSMessage wMessage = new APSMessage();
//													wMessage.MessageText = StringUtils.Format(
//															"工艺路线：{0},工序：{1}的工步{2}在{3}定义的顺序不一致", wRoute.VersionNo,
//															wRoutePart.Name, wPartPoint.PartPointName, wLine.Name);
//													wMessageList.add(wMessage);
//												}
//											}
//										}
//										if (!wExistStep) {
//											APSMessage wMessage = new APSMessage();
//											wMessage.MessageText = StringUtils.Format("工艺路线：{0},工序：{1}的工步：{2}在{3}不存在",
//													wRoute.VersionNo, wRoutePart.Name, wPartPoint.PartPointName,
//													wLine.Name);
//											wMessageList.add(wMessage);
//										}
//									}
//								} else {
//									APSMessage wMessage = new APSMessage();
//									wMessage.MessageText = StringUtils.Format("工艺路线{0}的{1}与{2}顺序不一致", wRoute.VersionNo,
//											wRoutePart.Name, wLine.Name);
//									wMessageList.add(wMessage);
//								}
//							}
//							if (!wExistPart) {
//								APSMessage wMessage = new APSMessage();
//								wMessage.MessageText = StringUtils.Format("工艺路线{0}的{1}在{2}不存在", wRoute.VersionNo,
//										wRoutePart.Name, wLine.Name);
//								wMessageList.add(wMessage);
//							}
//						}
//					}
//				}
//			}
//		} catch (Exception ex) {
//			wErrorCode.set(MESException.Exception.getValue());
//			LoggerTool.SaveException("APSService", "APS_CheckOrderRoute", "Function Exception:" + ex.toString());
//		}
//		return wMessageList;
//	}
}
