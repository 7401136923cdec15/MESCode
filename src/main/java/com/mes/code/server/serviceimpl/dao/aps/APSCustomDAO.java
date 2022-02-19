package com.mes.code.server.serviceimpl.dao.aps;

import com.mes.code.server.serviceimpl.dao.BaseDAO;

public class APSCustomDAO extends BaseDAO {
	private static APSCustomDAO Instance = null;

	private APSCustomDAO() {
		super();
	}

	public static APSCustomDAO getInstance() {
		if (Instance == null)
			Instance = new APSCustomDAO();
		return Instance;
	}

	// 测试接口
//	public APSEntryGant APS_GenerateGantEntry_Trial(int wCompanyID, int wLoginID, int wLineID, Calendar wShiftTime,
//			int wShiftPeriodID, int wWorkDayID, int wDays, OutResult<Integer> wErrorCode) {
//		wErrorCode.set(0);
//		APSEntryGant wEntryGant = new APSEntryGant();
//		@SuppressWarnings("unused")
//		List<APSTaskLine> wTaskLineList = new ArrayList<APSTaskLine>();
//		try {
//			// 调试用途
//			// Step01:提取计算数据源
//			List<APSOrder> wOrderList = this.APS_QueryOrderListByLineID(wCompanyID, wLoginID, wLineID, wErrorCode);
//			// Step02:生成排程条件
//			APSCalendar wUnitCondition = this.APS_GenerateConditionByUnitID(wCompanyID, wLoginID, wLineID,
//					APSUnitLevel.Line.getValue(), wShiftTime, wShiftPeriodID, 0, wDays, wWorkDayID, wErrorCode);
//			// Step03:计算排程结果
//			List<APSOrder> wOrderPlanList = this.APS_GenerateOrderPlanListByLineMode(wCompanyID, wLoginID, wOrderList,
//					wUnitCondition, wErrorCode);
//
//			// Step04:预览排程结果
//			wEntryGant = this.APS_GenerateGantEntryByOrderList(wCompanyID, wLoginID, wOrderPlanList, wUnitCondition,
//					wErrorCode);
//
//			this.APS_SaveGantEntryByShiftID(wCompanyID, wLoginID, wEntryGant, wUnitCondition.ShiftID, wErrorCode);
//		} catch (Exception ex) {
//			wErrorCode.set(MESException.Exception.getValue());
//			LoggerTool.SaveException("APSService", "APS_GenerateGantEntry_Trial",
//					"Function Exception:" + ex.toString());
//		}
//		return wEntryGant;
//	}
//
//	public APSEntryGant APS_GenerateGantEntryByTaskLineList_Trial(int wCompanyID, int wLoginID, int wLineID,
//			int wShiftID, OutResult<Integer> wErrorCode) {
//		wErrorCode.set(0);
//		APSEntryGant wEntryGant = new APSEntryGant();
//
//		try {
//			// 调试用途
//			List<APSTaskLine> wTaskLineList = APSTaskDAO.getInstance().APS_QueryTaskLineListByShiftID(wCompanyID,
//					wLoginID, wLineID, wShiftID, wErrorCode);
//			// Step04:预览排程结果
//			wEntryGant = this.APS_GenerateGantEntryByTaskLineList(wCompanyID, wLoginID, wTaskLineList, wErrorCode);
//		} catch (Exception ex) {
//			wErrorCode.set(MESException.Exception.getValue());
//			LoggerTool.SaveException("APSService", "APS_GenerateGantEntryByTaskLineList_Trial",
//					"Function Exception:" + ex.toString());
//		}
//		return wEntryGant;
//	}
//
//	// CRRC订单计划计算
//	// 内部逻辑函数
//	private APSEntryTask APS_GenerateEntryTaskWorkHours(int wCompanyID, APSEntryTask wEntryTask, int wShiftSeconds,
//			OutResult<Integer> wErrorCode) {
//		wErrorCode.set(0);
//		try {
//			wEntryTask.WorkHour = FPCProductDAO.getInstance().FPC_QueryWorkHourByProductCode(wCompanyID,
//					wEntryTask.ProductNo, APSUnitLevel.Line, wEntryTask.UnitID, 0, 0, wErrorCode);
//
//			/*
//			 * ServiceResult<String> wInstance = this.GetDataBaseName(wCompanyID,
//			 * MESDBSource.Basic); wErrorCode.set(wInstance.ErrorCode);
//			 */
//			if (wErrorCode.Result == 0) {
//				if (wEntryTask.WorkHour <= 1) {
//					// 添加错误信息提示
//					APSMessage wMessage = new APSMessage();
//					wMessage.MessageText = StringUtils.Format("编码为{0}的产品的标准工时设置为0", wEntryTask.ProductNo);
//					wEntryTask.MessageList.add(wMessage);
//					wEntryTask.WorkHour = 30;
//				}
//				wEntryTask.WorkHours = (int) (wEntryTask.WorkHour * wEntryTask.FQTY);
//				wEntryTask.Shifts = (float) wEntryTask.WorkHours / (float) wShiftSeconds;
//				wEntryTask.ShiftHours = wShiftSeconds;
//			} else {
//				APSMessage wMessage = new APSMessage();
//				switch (wErrorCode.Result) {
//				case 1:
//					wMessage.MessageText = StringUtils.Format("编码为{0}的产品不存在", wEntryTask.ProductNo);
//					break;
//				case 2:
//					wMessage.MessageText = StringUtils.Format("编码为{0}的产品工艺路线不存在", wEntryTask.ProductNo);
//					break;
//				case 3:
//					wMessage.MessageText = StringUtils.Format("产线{0}的工艺路线未设置", wEntryTask.UnitID);
//					break;
//				case 4:
//					wMessage.MessageText = StringUtils.Format("编码为{0}的产品的标准工时未设置", wEntryTask.ProductNo);
//					break;
//				case 5:
//					wMessage.MessageText = StringUtils.Format("编码为{0}的产品的工艺路线与产线配置不匹配", wEntryTask.ProductNo);
//					break;
//				}
//				wEntryTask.MessageList.add(wMessage);
//			}
//		} catch (Exception ex) {
//			wErrorCode.set(MESException.Exception.getValue());
//			LoggerTool.SaveException("APSService", "APS_GenerateEntryTaskWorkHours",
//					"Function Exception:" + ex.toString());
//		}
//		return wEntryTask;
//	}
//
//	private int APS_QueryPlanedWorkHoursByShiftID(int wCompanyID, int wLoginID, int wLineID, int wShiftID,
//			OutResult<Integer> wErrorCode) {
//		wErrorCode.set(0);
//		int wPlanedWorkHours = 0;
//		@SuppressWarnings("unused")
//		List<APSEntryTask> wEntryTaskList = new ArrayList<APSEntryTask>();
//		try {
//			if (wLineID > 0) {
//				List<APSTaskLine> wSavedTaskLineList = APSTaskDAO.getInstance().APS_QueryTaskLineListByStatus(
//						wCompanyID, wLoginID, wLineID, APSTaskStatus.Saved.getValue(), wErrorCode);
//				List<APSTaskLine> wIssuedTaskLineList = APSTaskDAO.getInstance().APS_QueryTaskLineListByStatus(
//						wCompanyID, wLoginID, wLineID, APSTaskStatus.Issued.getValue(), wErrorCode);
//				List<APSTaskLine> wStartedTaskLineList = APSTaskDAO.getInstance().APS_QueryTaskLineListByStatus(
//						wCompanyID, wLoginID, wLineID, APSTaskStatus.Started.getValue(), wErrorCode);
//
//				List<APSTaskLine> wTaskLineList = new ArrayList<APSTaskLine>();
//
//				wTaskLineList.addAll(wSavedTaskLineList);
//				wTaskLineList.addAll(wIssuedTaskLineList);
//				wTaskLineList.addAll(wStartedTaskLineList);
//
//				for (APSTaskLine wTaskLine : wTaskLineList) {
//					if (wTaskLine.ShiftID == wShiftID) {
//						switch (APSTaskStatus.getEnumType(wTaskLine.Status)) {
//						case Saved:
//							wPlanedWorkHours += wTaskLine.WorkHours;
//							break;
//						case Issued:
//							wPlanedWorkHours += wTaskLine.WorkHours;
//							break;
//						case Started:
//							int wToDoFQTY = wTaskLine.FQTYShift - wTaskLine.FQTYDone;
//							if (wToDoFQTY < 1)
//								wToDoFQTY = 0;
//							wPlanedWorkHours += wTaskLine.WorkHour * wToDoFQTY;
//							break;
//						default:
//							break;
//						}
//					}
//				}
//			}
//		} catch (Exception ex) {
//			wErrorCode.set(MESException.Exception.getValue());
//			LoggerTool.SaveException("APSService", "APS_QueryToDoWorkHoursByShiftID",
//					"Function Exception:" + ex.toString());
//		}
//		return wPlanedWorkHours;
//	}
//
//	private APSOrder APS_GenerateOrderToDoFQTYByPlanList(int wCompanyID, int wLoginID, APSOrder wOrder,
//			List<APSTaskLine> wTaskLineList, OutResult<Integer> wErrorCode) {
//		wErrorCode.set(0);
//		@SuppressWarnings("unused")
//		List<APSEntryTask> wEntryTaskList = new ArrayList<APSEntryTask>();
//		try {
//			if (wOrder.ID > 0) {
//				int wFQTYPlaned = 0;
//				for (APSTaskLine wTaskLine : wTaskLineList) {
//					if (wTaskLine.OrderID == wOrder.ID) {
//						switch (APSTaskStatus.getEnumType(wTaskLine.Status)) {
//						case Saved:
//							wFQTYPlaned += wTaskLine.FQTYShift;
//							break;
//						case Issued:
//							wFQTYPlaned += wTaskLine.FQTYShift;
//							break;
//						case Started:
//							int wToDoFQTY = wTaskLine.FQTYShift - wTaskLine.FQTYDone;
//							if (wToDoFQTY < 1)
//								wToDoFQTY = 0;
//							wFQTYPlaned += wToDoFQTY;
//							break;
//						default:
//							break;
//						}
//					}
//				}
//				wOrder.FQTYPlaned = wFQTYPlaned;
//				wOrder.FQTYMargin = wOrder.FQTY - wOrder.FQTYDone - wOrder.FQTYPlaned;
//				if (wOrder.FQTYPlaned < 1)
//					wOrder.FQTYPlaned = 0;
//			}
//		} catch (Exception ex) {
//			wErrorCode.set(MESException.Exception.getValue());
//			LoggerTool.SaveException("APSService", "APS_GenerateOrderToDoFQTYByPlanList",
//					"Function Exception:" + ex.toString());
//		}
//		return wOrder;
//	}
//
//	private List<APSTaskPart> APS_GenerateTaskPartListByOrder(int wCompanyID, APSOrder wOrder,
//			OutResult<Integer> wErrorCode) {
//		List<APSTaskPart> wTaskPartList = new ArrayList<APSTaskPart>();
//		try {
//			// Step01:生产排程工序、工步计划
//			List<FMCLineUnit> wLineUnitList = FMCLineDAO.getInstance().FMC_QueryLineUnitListByOrder(wCompanyID, wOrder,
//					wErrorCode);
//			// Step02:生成工段任务集合
//			for (FMCLineUnit wPartUnit : wLineUnitList) {
//				if (wPartUnit.LevelID == APSUnitLevel.Part.getValue()) {
//					APSTaskPart wPartTask = new APSTaskPart();
//					wPartTask.OrderID = wOrder.ID;
//					wPartTask.LineID = wOrder.LineID;
//					wPartTask.PartID = wPartUnit.UnitID;
//					wPartTask.FQTYShift = wOrder.FQTYShift;
//					wPartTask.WorkHour = wPartUnit.WorkHour;
//					wPartTask.PartOrderID = wPartUnit.OrderID;
//					wPartTask.DelayDays = wPartUnit.ShiftDays;
//					wPartTask.ShiftHours = wOrder.ShiftHours;
//					wPartTask.WorkHour = FPCProductDAO.getInstance().FPC_QueryWorkHourByProductCode(wCompanyID,
//							wOrder.ProductNo, APSUnitLevel.Part, wPartTask.LineID, wPartTask.PartID, 0, wErrorCode);
//					for (FMCLineUnit wStepUnit : wPartUnit.UnitList) {
//						APSTaskStep wStepTask = new APSTaskStep();
//						wStepTask.OrderID = wOrder.ID;
//						wStepTask.LineID = wOrder.LineID;
//						wStepTask.PartID = wPartUnit.UnitID;
//						wStepTask.PartPointID = wStepUnit.UnitID;
//						wStepTask.FQTYShift = wOrder.FQTYShift;
//						wStepTask.WorkHour = wStepUnit.WorkHour;
//						wStepTask.StepOrderID = wStepUnit.OrderID;
//						wStepTask.WorkHour = FPCProductDAO.getInstance().FPC_QueryWorkHourByProductCode(wCompanyID,
//								wOrder.ProductNo, APSUnitLevel.Step, wStepTask.LineID, wStepTask.PartID,
//								wStepTask.PartPointID, wErrorCode);
//						wPartTask.TaskStepList.add(wStepTask);
//					}
//					wTaskPartList.add(wPartTask);
//				}
//			}
//			wTaskPartList = wTaskPartList.stream().sorted(Comparator.comparing(p -> p.PartOrderID))
//					.collect(Collectors.toList());
//
//			// wTaskPartList = wTaskPartList.OrderBy(p => p.PartOrderID).ToList();
//		} catch (Exception ex) {
//			wErrorCode.set(MESException.Exception.getValue());
//			LoggerTool.SaveException("APSService", "APS_GenerateTaskPartListByOrder",
//					"Function Exception:" + ex.toString());
//		}
//		return wTaskPartList;
//	}
//
//	private APSTaskLine APS_GenerateTaskLineByOrder(int wCompanyID, APSOrder wOrder, APSCalendar wUnitCondition,
//			OutResult<Integer> wErrorCode) {
//		wErrorCode.set(0);
//		APSTaskLine wLineTask = new APSTaskLine();
//		try {
//			if (wOrder.LineID > 0 && wOrder.FQTYShift > 0) {
//				wLineTask = new APSTaskLine(wCompanyID, wOrder);
//				wLineTask.ShiftID = wUnitCondition.ShiftID;
//				// Step01:提取排程结果
//				wLineTask.ID = 0;
//
//				// Step02:分解成工段日计划
//				List<APSTaskPart> wTaskPartList = this.APS_GenerateTaskPartListByOrder(wCompanyID, wOrder, wErrorCode);
//				for (APSTaskPart wTaskPart : wTaskPartList) {
//					int wShiftParts = 0;
//					int wMarginParts = wTaskPart.FQTYShift;
//					boolean wFirstShift = true;
//					for (APSWorkCalendar wCalendar : wUnitCondition.WorkCalendarList) {
//						if (!wCalendar.Active)
//							continue;
//
//						for (APSWorkShift wWorkShift : wCalendar.WorkShiftList) {
//							if (!wWorkShift.Active)
//								continue;
//
//							if (wWorkShift.WorkDate.compareTo(wOrder.StartTime) < 0)
//								continue;
//
//							if (wFirstShift) {
//								wShiftParts = wOrder.StartMarginHours / wOrder.WorkHour;
//								wFirstShift = false;
//							} else {
//								wShiftParts = (int) (wOrder.ShiftHours * wUnitCondition.MaxLoadRate) / wOrder.WorkHour;
//							}
//							APSTaskPart wShiftTaskPart = wTaskPart.Clone();
//							wShiftTaskPart.TaskStepList.clear();
//
//							if (wShiftParts < wMarginParts)
//								wShiftTaskPart.FQTYShift = wShiftParts;
//							else
//								wShiftTaskPart.FQTYShift = wMarginParts;
//
//							wShiftTaskPart.WorkHour = wOrder.WorkHour;
//							wMarginParts = wMarginParts - wShiftTaskPart.FQTYShift;
//							wShiftTaskPart.ShiftDate = wWorkShift.WorkDate;
//							wShiftTaskPart.ShiftID = this.APS_GenerateShiftIDByShiftPeriod(wCompanyID, 0,
//									wWorkShift.WorkDate, APSShiftPeriod.Day.getValue(), wWorkShift.ShiftID, wErrorCode);
//							if (wShiftTaskPart.FQTYShift > 0) {
//								for (APSTaskStep wTaskStep : wTaskPart.TaskStepList) {
//									APSTaskStep wShiftTaskStep = wTaskStep.Clone();
//									wShiftTaskStep.FQTYShift = wShiftTaskPart.FQTYShift;
//									wShiftTaskStep.ShiftID = wShiftTaskPart.ShiftID;
//									wShiftTaskStep.WorkHour = wOrder.WorkHour;
//									wShiftTaskPart.TaskStepList.add(wShiftTaskStep);
//								}
//								// 日计划合并
//								boolean wNewPartDate = true;
//								for (APSTaskPart wDatePart : wLineTask.TaskPartList) {
//									if (wDatePart.OrderID == wShiftTaskPart.OrderID
//											&& wDatePart.LineID == wShiftTaskPart.LineID
//											&& wDatePart.PartID == wShiftTaskPart.PartID
//											&& wDatePart.ShiftDate.compareTo(wShiftTaskPart.ShiftDate) == 0) {
//										wDatePart.FQTYShift += wShiftTaskPart.FQTYShift;
//										for (APSTaskStep wShiftStep : wShiftTaskPart.TaskStepList) {
//											for (APSTaskStep wDateStep : wDatePart.TaskStepList) {
//												if (wDateStep.PartPointID == wShiftStep.PartPointID
//														&& wDateStep.PartID == wShiftStep.PartID
//														&& wDateStep.LineID == wShiftStep.LineID
//														&& wDateStep.OrderID == wShiftStep.OrderID) {
//													wDateStep.FQTYShift += wShiftStep.FQTYShift;
//													break;
//												}
//											}
//										}
//										wNewPartDate = false;
//									}
//								}
//								if (wNewPartDate)
//									wLineTask.TaskPartList.add(wShiftTaskPart);
//							}
//						}
//					}
//				}
//				// Step03.2: 按工段间的加工间隔平移整个工段的日计划日期
//				int wDelayDays = 0;
//				int wPartID = 0;
//				wLineTask.TaskPartList = wLineTask.TaskPartList.stream()
//						.sorted(Comparator.comparing(APSTaskPart::getPartOrderID)).collect(Collectors.toList());
//
//				// wLineTask.TaskPartList = wLineTask.TaskPartList.OrderBy(p =>
//				// p.PartOrderID).ToList();
//				for (APSTaskPart wTaskPart : wLineTask.TaskPartList) {
//					if (wPartID > 0 && wPartID != wTaskPart.PartID)
//						wDelayDays += wTaskPart.DelayDays;
//
//					wPartID = wTaskPart.PartID;
//
//					wTaskPart.ShiftDate = CFGCalendarDAO.getInstance().Cfg_CalendarSkipIdleDate(wCompanyID,
//							wTaskPart.ShiftDate, wDelayDays, wErrorCode);
//					wTaskPart.ShiftID = this.APS_GenerateShiftIDByShiftPeriod(wCompanyID, 0, wTaskPart.ShiftDate,
//							APSShiftPeriod.Day.getValue(), 0, wErrorCode);
//					for (APSTaskStep wTaskStep : wTaskPart.TaskStepList) {
//						wTaskStep.ShiftID = wTaskPart.ShiftID;
//					}
//				}
//				// Step04:生成物料计划
//				APSMRP wMRP = APSMRPDAO.getInstance().APS_GenerateMaterialListByOrder(wCompanyID, 0, 0, wOrder,
//						wErrorCode);
//				for (APSMaterial wMaterial : wMRP.MaterialList) {
//					for (APSTaskPart wPartTask : wLineTask.TaskPartList) {
//						for (APSTaskStep wStepTask : wPartTask.TaskStepList) {
//							if (wStepTask.PartPointID == wMaterial.PartPointID) {
//								wMaterial.LineID = wStepTask.LineID;
//								wMaterial.PartID = wStepTask.PartID;
//								wMaterial.TaskLineID = wStepTask.TaskLineID;
//								wMaterial.TaskPartID = wStepTask.TaskPartID;
//								wMaterial.TaskStepID = wStepTask.ID;
//								wMaterial.ShiftID = wLineTask.ShiftID;
//								break;
//							}
//						}
//					}
//					wLineTask.MaterialList.add(wMaterial);
//				}
//				wLineTask.MessageList.addAll(wMRP.MessageList);
//			}
//		} catch (Exception ex) {
//			wErrorCode.set(MESException.Exception.getValue());
//			LoggerTool.SaveException("APSService", "APS_GenerateTaskLineByOrder",
//					"Function Exception:" + ex.toString());
//		}
//		return wLineTask;
//	}
//
//	private int APS_QueryWorkHoursByUnitID(int wCompanyID, int wUnitID, APSCalendar wUnitCondition,
//			OutResult<Integer> wErrorCode) {
//		wErrorCode.set(0);
//		int wWorkHours = 0;
//		@SuppressWarnings("unused")
//		List<APSEntryTask> wEntryTaskList = new ArrayList<APSEntryTask>();
//		try {
//			int wShifts = 0;
//			if (wUnitCondition.UnitID == 0 || wUnitCondition.UnitID == wUnitID) {
//				for (APSWorkCalendar wCalendar : wUnitCondition.WorkCalendarList) {
//					if (wCalendar.Active) {
//						for (APSWorkShift wWorkShift : wCalendar.WorkShiftList) {
//							if (wWorkShift.Active) {
//								wShifts++;
//								wWorkHours += wWorkShift.WorkHours;
//							}
//						}
//					}
//				}
//			}
//			if (wWorkHours < 1)
//				wWorkHours = 3600 * 8 * wShifts;
//
//			wWorkHours = (int) (wWorkHours * wUnitCondition.MaxLoadRate);
//		} catch (Exception ex) {
//			wErrorCode.set(MESException.Exception.getValue());
//			LoggerTool.SaveException("APSService", "APS_QueryWorkHoursByUnitID", "Function Exception:" + ex.toString());
//		}
//		return wWorkHours;
//	}
//
//	private APSMarginHours APS_GenerateStartTimeByCalender(int wPlanedWorkHours, APSCalendar wUnitCondition,
//			OutResult<Integer> wErrorCode) {
//		APSMarginHours wMarginHours = new APSMarginHours();
//		wErrorCode.set(0);
//		try {
//			int wWorkHours = 0;
//			for (APSWorkCalendar wCalendar : wUnitCondition.WorkCalendarList) {
//				for (APSWorkShift wWorkShift : wCalendar.WorkShiftList) {
//					wWorkHours += (int) (wWorkShift.WorkHours * wUnitCondition.MaxLoadRate);
//					if (wPlanedWorkHours <= wWorkHours) {
//						wMarginHours.StartTime = wWorkShift.WorkDate;
//						wMarginHours.MarginHours = wWorkHours - wPlanedWorkHours;
//						break;
//					}
//				}
//				if (wPlanedWorkHours <= wWorkHours)
//					break;
//			}
//		} catch (Exception ex) {
//			wErrorCode.set(MESException.Exception.getValue());
//			LoggerTool.SaveException("APSService", "APS_GenerateStartTimeByCalender",
//					"Function Exception:" + ex.toString());
//		}
//		return wMarginHours;
//	}
//
//	private List<APSOrder> APS_CheckShiftOrderListWorkHours(int wCompanyID, List<APSOrder> wOrderList,
//			APSCalendar wCalendar, OutResult<Integer> wErrorCode) {
//		wErrorCode.set(0);
//		List<APSOrder> wSortOrderList = new ArrayList<APSOrder>();
//		try {
//			// Step01:按产线、优先级、开工日期
//			wSortOrderList = wOrderList.stream().sorted(
//					Comparator.comparing(APSOrder::getLineID).thenComparing(p -> p.getStartTime().getTime().getTime()))
//					.collect(Collectors.toList());
//
//			// wSortOrderList = wOrderList.OrderBy(p => p.LineID).ThenBy(p =>
//			// p.Priority).ThenBy(p => p.StartTime).ToList();
//
//			// Step02:按产线检查订单累计排班数是否超出产线最大排班数
//			if (wCalendar.CheckWorkHours) {
//				int wLineID = 0;
//				int wLineWorkHours = 0;
//				int wPlanedWorkHours = 0;
//				for (APSOrder wOrder : wSortOrderList) {
//					if (wOrder.WorkHours < 1)
//						continue;
//
//					if (wLineID != wOrder.LineID) {
//						wLineID = wOrder.LineID;
//						// Step03:检查排版周期内的现有订单的排产信息
//						if (wCalendar.CheckShiftHours)
//							wPlanedWorkHours = this.APS_QueryPlanedWorkHoursByShiftID(wCompanyID, 0, wLineID,
//									wCalendar.ShiftID, wErrorCode);
//
//						wLineWorkHours = APS_QueryWorkHoursByUnitID(wCompanyID, wLineID, wCalendar, wErrorCode);
//					}
//					int wStartMarginHours = 0;
//					APSMarginHours wStartMargin = this.APS_GenerateStartTimeByCalender(wPlanedWorkHours, wCalendar,
//							wErrorCode);
//
//					Calendar wStartTime = (Calendar) wStartMargin.StartTime.clone();
//					wStartMarginHours = wStartMargin.MarginHours;
//
//					if (wStartMargin.ErrorCode == 0) {
//						wOrder.StartTime = wStartTime;
//						wOrder.StartMarginHours = wStartMarginHours;
//					}
//					// 检查订单的可用工时
//					if (wPlanedWorkHours + wOrder.WorkHours > wLineWorkHours) {
//						wOrder.WorkHours = wLineWorkHours - wPlanedWorkHours;
//					}
//					if (wOrder.WorkHour > 0) {
//						int wFQTYShift = wOrder.WorkHours / wOrder.WorkHour;
//
//						if (wFQTYShift < wOrder.FQTYShift)
//							wOrder.FQTYShift = wFQTYShift;
//						else
//							wOrder.FQTYShift = wOrder.WorkHours / wOrder.WorkHour;
//
//						wOrder.WorkHours = wOrder.WorkHour * wOrder.FQTYShift;
//
//						wOrder.Shifts = (float) wOrder.WorkHours / (float) wCalendar.ShiftWorkHours;
//
//						// 修正计算已计划工时
//						if (wPlanedWorkHours + wOrder.WorkHours > wLineWorkHours) {
//							wPlanedWorkHours = wLineWorkHours;
//						} else {
//							wPlanedWorkHours += wOrder.WorkHours;
//						}
//
//						@SuppressWarnings("unused")
//						int wEndPlanedHours = 0;
//
//						APSMarginHours wEndMargin = this.APS_GenerateStartTimeByCalender(wPlanedWorkHours, wCalendar,
//								wErrorCode);
//						if (wEndMargin.ErrorCode == 0) {
//							wOrder.EndTime = (Calendar) wEndMargin.StartTime.clone();
//
//							wEndPlanedHours = wEndMargin.MarginHours;
//						}
//					}
//				}
//			}
//		} catch (Exception ex) {
//			wErrorCode.set(MESException.Exception.getValue());
//			LoggerTool.SaveException("APSService", "APS_CheckShiftOrderListWorkHours",
//					"Function Exception:" + ex.toString());
//		}
//		return wSortOrderList;
//	}
//
//	private boolean APS_CheckGantEntryAfterManualMode(int wCompanyID, APSEntryGant wEntryGant,
//			OutResult<Integer> wErrorCode) {
//		boolean wResult = true;
//		try {
//			// Step01:检查人工调整的数据是否存在逻辑错误（开工日期，任务数）
//			for (APSTaskLine wTaskLine : wEntryGant.TaskLineList) {
//				for (APSGantPart wGantPart : wEntryGant.GantPartList) {
//					if (wTaskLine.LineID == wGantPart.LineID && wTaskLine.OrderID == wGantPart.OrderID
//							&& wTaskLine.ID == wGantPart.TaskLineID) {
//						int wFQTYWeek = 0;
//						for (APSTaskPart wDayTaskPart : wGantPart.TaskPartList) {
//							boolean wIsNewTaskPart = true;
//							for (APSTaskPart wTaskPart : wTaskLine.TaskPartList) {
//								if (wTaskPart.LineID == wGantPart.LineID && wTaskPart.OrderID == wGantPart.OrderID
//										&& wTaskPart.PartID == wGantPart.PartID
//										&& wTaskPart.TaskLineID == wGantPart.TaskLineID) {
//									if (wTaskPart.ShiftDate.compareTo(wDayTaskPart.ShiftDate) == 0) {
//										wTaskPart.FQTYShift = wDayTaskPart.FQTYShift;
//										wFQTYWeek += wDayTaskPart.FQTYShift;
//										wIsNewTaskPart = false;
//										break;
//									}
//								}
//							}
//							if (wIsNewTaskPart && wDayTaskPart.FQTYShift > 0) {
//								wTaskLine.TaskPartList.add(wDayTaskPart);
//								wFQTYWeek += wDayTaskPart.FQTYShift;
//							}
//							if (wDayTaskPart.FQTYShift > 0 && wDayTaskPart.ShiftDate.compareTo(wTaskLine.StartTime) < 0)
//								wResult = false;
//						}
//						if (wTaskLine.FQTYShift != wFQTYWeek)
//							wResult = false;
//					}
//				}
//			}
//		} catch (Exception ex) {
//			LoggerTool.SaveException("APSService", "APS_CheckGantEntryAfterManualMode",
//					"Function Exception:" + ex.toString());
//		}
//		return wResult;
//	}
//
//	// 接口函数
//	public int APS_GenerateShiftIDByShiftPeriod(int wCompanyID, int wLoginID, Calendar wShiftTime, int wShiftPeriod,
//			int wZoneID, OutResult<Integer> wErrorCode) {
//		wErrorCode.set(0);
//		int wShiftID = 0;
//		try {
//			wShiftID = MESServer.MES_QueryShiftID(wCompanyID, wShiftTime, APSShiftPeriod.getEnumType(wShiftPeriod),
//					FMCShiftLevel.getEnumType(wZoneID), 0);
//		} catch (Exception ex) {
//			wErrorCode.set(MESException.Exception.getValue());
//			LoggerTool.SaveException("APSService", "APS_GenerateShiftIDByShiftPeriod",
//					"Function Exception:" + ex.toString());
//		}
//		return wShiftID;
//	}
//
//	/*
//	 * wUnitID: 排产实体ID wUnitLevel: 实体层级:产线，工序，工步 wShiftTime: 排产起始时刻 wShiftPeriod:
//	 * 排产精度，班，天，周，月(周期) wZoneID: 排产精度为为班次时，第几班 wShiftDays： 排产精度为天时，一周工作日天数（自由调整）
//	 * wWorkDayID: 工作日索引ID
//	 */
//	public APSCalendar APS_GenerateConditionByUnitID(int wCompanyID, int wLoginID, int wUnitID, int wUnitLevel,
//			Calendar wShiftTime, int wShiftPeriod, int wZoneID, int wShiftDays, int wWorkDayID,
//			OutResult<Integer> wErrorCode) {
//		wErrorCode.set(0);
//		APSCalendar wUnitCondition = new APSCalendar();
//		try {
//			wUnitCondition.UnitID = wUnitID;
//			wUnitCondition.UnitLevel = wUnitLevel;
//			wUnitCondition.MaxLoadRate = (float) 1.0;
//			wUnitCondition.ShiftPeriod = wShiftPeriod;
//			wUnitCondition.CheckShiftHours = true;
//			wUnitCondition.CheckWorkHours = true;
//			// Step01:计算排产班次
//			int wShiftID = this.APS_GenerateShiftIDByShiftPeriod(wCompanyID, wLoginID, wShiftTime, wShiftPeriod,
//					wZoneID, wErrorCode);
//			wUnitCondition.ShiftID = wShiftID;
//			// Step02:装载当前工作日班次信息
//			FMCWorkDay wWorkDay = FMCShiftDAO.getInstance().FMC_LoadWorkDayByID(wCompanyID, wWorkDayID, wErrorCode);
//
//			// Step03:计算起始排班日到wShiftDays的班次
//			switch (APSShiftPeriod.getEnumType(wShiftPeriod)) {
//			case Minute:
//				wShiftDays = 0;
//				break;
//			case Hour:
//				wShiftDays = 0;
//				break;
//			case Shift:
//				wShiftTime.set(Calendar.HOUR_OF_DAY, 0);
//				wShiftTime.set(Calendar.MINUTE, 0);
//				wShiftTime.set(Calendar.SECOND, 0);
//				wShiftTime.set(Calendar.MILLISECOND, 0);
//				wShiftDays = 0;
//				break;
//			case Day:
//				wShiftTime.set(Calendar.HOUR_OF_DAY, 0);
//				wShiftTime.set(Calendar.MINUTE, 0);
//				wShiftTime.set(Calendar.SECOND, 0);
//				wShiftTime.set(Calendar.MILLISECOND, 0);
//				wShiftDays = 1;
//				break;
//			case Week:
//				wShiftTime.set(Calendar.HOUR_OF_DAY, 0);
//				wShiftTime.set(Calendar.MINUTE, 0);
//				wShiftTime.set(Calendar.SECOND, 0);
//				wShiftTime.set(Calendar.MILLISECOND, 0);
//				if (wShiftDays < 1)
//					wErrorCode.set(MESException.Logic.getValue());
//				if (wShiftDays > 15)
//					wErrorCode.set(MESException.Logic.getValue());
//				if (wShiftTime.get(Calendar.DAY_OF_WEEK) + wShiftDays > 25)
//					wErrorCode.set(MESException.Logic.getValue());
//				break;
//			case Month:
//				wShiftTime.set(Calendar.HOUR_OF_DAY, 0);
//				wShiftTime.set(Calendar.MINUTE, 0);
//				wShiftTime.set(Calendar.SECOND, 0);
//				wShiftTime.set(Calendar.MILLISECOND, 0);
//				if (wShiftDays < 1)
//					wErrorCode.set(MESException.Logic.getValue());
//
//				if (wShiftDays > 31)
//					wErrorCode.set(MESException.Logic.getValue());
//				break;
//			}
//			int wWorkDays = 0;
//			int wWorkShifts = 0;
//			if (wShiftDays > 0) {
//				for (int i = 0; i < wShiftDays; i++) {
//					wWorkDays++;
//					// Calendar wWorkDate = wShiftTime.AddDays(i);
//					Calendar wWorkDate = (Calendar) wShiftTime.clone();
//					wWorkDate.add(Calendar.DATE, i);
//					APSWorkCalendar wWorkCalendar = new APSWorkCalendar(wWorkDays, wWorkDate);
//					for (FMCShift wShift : wWorkDay.ShiftList) {
//						wWorkShifts++;
//						APSWorkShift wWorkShift = new APSWorkShift(wWorkShifts, wWorkDate);
//						wWorkShift.ShiftID = wShift.LevelID;
//						wWorkShift.ShiftName = wShift.Name;
//						wWorkShift.WorkHours = wShift.WorkMinutes * 60;
//						if (wShift.LevelID == FMCShiftLevel.Day.getValue() && wUnitCondition.ShiftWorkHours < 1)
//							wUnitCondition.ShiftWorkHours = wShift.WorkMinutes * 60;
//
//						wWorkCalendar.WorkShiftList.add(wWorkShift);
//					}
//					wUnitCondition.WorkCalendarList.add(wWorkCalendar);
//				}
//			} else {
//				APSWorkCalendar wWorkCalendar = new APSWorkCalendar(wWorkDays, wShiftTime);
//				for (FMCShift wShift : wWorkDay.ShiftList) {
//					if (wShift.LevelID == wZoneID) {
//						wWorkShifts++;
//						APSWorkShift wWorkShift = new APSWorkShift(wWorkShifts, wShiftTime);
//						wWorkShift.ShiftID = wShift.LevelID;
//						wWorkShift.ShiftName = wShift.Name;
//						wWorkShift.WorkHours = wShift.WorkMinutes * 60;
//						wUnitCondition.ShiftWorkHours = wShift.WorkMinutes * 60;
//						wWorkCalendar.WorkShiftList.add(wWorkShift);
//					}
//				}
//				wUnitCondition.WorkCalendarList.add(wWorkCalendar);
//			}
//			if (wUnitCondition.ShiftWorkHours < 1)
//				wUnitCondition.ShiftWorkHours = 3600 * 8;
//		} catch (Exception ex) {
//			wErrorCode.set(MESException.Exception.getValue());
//			LoggerTool.SaveException("APSService", "APS_GenerateConditionByUnitID",
//					"Function Exception:" + ex.toString());
//		}
//		return wUnitCondition;
//	}
//
//	public List<APSOrder> APS_QueryOrderListByLineID(int wCompanyID, int wLoginID, int wLineID,
//			OutResult<Integer> wErrorCode) {
//		List<APSOrder> wOrderDBList = new ArrayList<APSOrder>();
//		List<APSOrder> wOrderList = new ArrayList<APSOrder>();
//
//		try {
//			ServiceResult<String> wInstance = this.GetDataBaseName(wCompanyID, MESDBSource.Basic);
//			wErrorCode.set(wInstance.ErrorCode);
//			if (wErrorCode.Result == 0) {
//				// Step01:条件
//				String wCondition = "";
//				if (wLineID > 0)
//					wCondition = StringUtils.Format(" and LineID={0}", wLineID);
//
//				String wSQLText = StringUtils.Format("Select * from {0}.oms_mesorder where Active=1 and Status=:Status",
//						wInstance.Result) + wCondition;
//				Map<String, Object> wParms = new HashMap<String, Object>();
//				wParms.put("Status", BPMStatus.Audited.getValue());
//				List<Map<String, Object>> wQueryResultList = nameJdbcTemplate.queryForList(wSQLText, wParms);
//				for (Map<String, Object> wSqlDataReader : wQueryResultList) {
//					APSOrder wOrder = new APSOrder();
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
//					wOrderDBList.add(wOrder);
//				}
//
//			}
//		} catch (Exception ex) {
//
//			LoggerTool.SaveException("APSService", "APS_QueryOrderListByLineID", "Function Exception:" + ex.toString());
//		}
//		try {
//			// Step01:检查待排产订单的工艺路径是否合法
//
//			for (APSOrder wOrderDB : wOrderDBList) {
//				wOrderDB.MessageList = APSMRPDAO.getInstance().APS_CheckOrderRoute(wCompanyID, wOrderDB, wErrorCode);
//			}
//			// Step02:提取已排产计划
//			List<APSTaskLine> wSavedTaskLineList = APSTaskDAO.getInstance().APS_QueryTaskLineListByStatus(wCompanyID,
//					wLoginID, wLineID, APSTaskStatus.Saved.getValue(), wErrorCode);
//			List<APSTaskLine> wIssuedTaskLineList = APSTaskDAO.getInstance().APS_QueryTaskLineListByStatus(wCompanyID,
//					wLoginID, wLineID, APSTaskStatus.Issued.getValue(), wErrorCode);
//			List<APSTaskLine> wStartedTaskLineList = APSTaskDAO.getInstance().APS_QueryTaskLineListByStatus(wCompanyID,
//					wLoginID, wLineID, APSTaskStatus.Started.getValue(), wErrorCode);
//
//			List<APSTaskLine> wTaskLineList = new ArrayList<APSTaskLine>();
//			wTaskLineList.addAll(wSavedTaskLineList);
//			wTaskLineList.addAll(wIssuedTaskLineList);
//			wTaskLineList.addAll(wStartedTaskLineList);
//
//			// Step03:计算待排产计划数
//			for (APSOrder wOrderDB : wOrderDBList) {
//				APSOrder wOrder = this.APS_GenerateOrderToDoFQTYByPlanList(wCompanyID, wLoginID, wOrderDB,
//						wTaskLineList, wErrorCode);
//				boolean wRangedLine = BMSRoleDAO.getInstance().BMS_CheckRangeByAuthorityID(wCompanyID, wLoginID,
//						BMSRange.Line, wOrder.LineID, wErrorCode);
//				if (wRangedLine)
//					wOrderList.add(wOrder);
//			}
//		} catch (Exception ex) {
//			LoggerTool.SaveException("APSService", "APS_QueryOrderListByLineID:Generate Order FQTY",
//					"Function Exception:" + ex.toString());
//		}
//		return wOrderList;
//	}
//
//	public List<APSOrder> APS_GenerateOrderPlanListByLineMode(int wCompanyID, int wLoginID, List<APSOrder> wOrderList,
//			APSCalendar wUnitCondition, OutResult<Integer> wErrorCode) {
//		List<APSOrder> wOrderShiftList = new ArrayList<APSOrder>();
//		try {
//			// Step01:按产线生成数据源
//			List<APSEntryUnit> wLineEntryUnitList = new ArrayList<APSEntryUnit>();
//			for (APSOrder wOrder : wOrderList) {
//				boolean wLineExist = false;
//				for (APSEntryUnit wEntryUnit : wLineEntryUnitList) {
//					if (wEntryUnit.UnitID == wOrder.LineID) {
//						wLineExist = true;
//						break;
//					}
//				}
//				if (!wLineExist) {
//					APSEntryUnit wEntryUnit = new APSEntryUnit();
//					wEntryUnit.UnitID = wOrder.LineID;
//					wEntryUnit.UnitLevel = APSUnitLevel.Line.getValue();
//					wLineEntryUnitList.add(wEntryUnit);
//				}
//				for (APSEntryUnit wEntryUnit : wLineEntryUnitList) {
//					if (wEntryUnit.UnitID == wOrder.LineID) {
//						APSEntryTask wEntryTask = new APSEntryTask();
//						wEntryTask.TaskID = wOrder.ID;
//						wEntryTask.UnitID = wOrder.LineID;
//						wEntryTask.UnitLevel = APSUnitLevel.Line.getValue();
//						wEntryTask.ProductNo = wOrder.ProductNo;
//						wEntryTask.FQTY = wOrder.FQTYMargin;
//						wEntryTask.StartTime = wOrder.StartTime;
//						wEntryTask.FinishedTime = wOrder.EndTime;
//						wEntryUnit.EntryTaskList.add(wEntryTask);
//						break;
//					}
//				}
//			}
//			// Step02:计算任务的工时
//			for (APSEntryUnit wEntryUnit : wLineEntryUnitList) {
//				for (APSEntryTask wEntryTask : wEntryUnit.EntryTaskList) {
//					this.APS_GenerateEntryTaskWorkHours(wCompanyID, wEntryTask, wUnitCondition.ShiftWorkHours,
//							wErrorCode);
//				}
//			}
//			// Step03:将加工时长信息回填到订单信息
//			for (APSOrder wOrder : wOrderList) {
//				for (APSEntryUnit wEntryUnit : wLineEntryUnitList) {
//					if (wOrder.LineID == wEntryUnit.UnitID) {
//						for (APSEntryTask wEntryTask : wEntryUnit.EntryTaskList) {
//							if (wOrder.ID == wEntryTask.TaskID) {
//								wOrder.WorkHour = wEntryTask.WorkHour;
//								wOrder.WorkHours = wEntryTask.WorkHours;
//								wOrder.ShiftHours = wEntryTask.ShiftHours;
//								wOrder.Shifts = wEntryTask.Shifts;
//								wOrder.MessageList.clear();
//								wOrder.MessageList.addAll(wEntryTask.MessageList);
//								break;
//							}
//						}
//					}
//				}
//			}
//			// Step04:计算可用排班时间
//			List<APSOrder> wSortOrderList = wOrderList.stream()
//					.sorted(Comparator.comparing((APSOrder p) -> p.getLineID()).thenComparing(APSOrder::getPriority)
//							.thenComparing(p -> p.getStartTime().getTime().getTime()))
//					.collect(Collectors.toList());
//			// List<APSOrder> wSortOrderList = wOrderList.OrderBy(p => p.LineID).ThenBy(p =>
//			// p.Priority).ThenBy(p => p.StartTime).ToList();
//			wOrderShiftList = this.APS_CheckShiftOrderListWorkHours(wCompanyID, wSortOrderList, wUnitCondition,
//					wErrorCode);
//		} catch (Exception ex) {
//			LoggerTool.SaveException("APSService", "APS_GenerateOrderPlanListByLineMode",
//					"Function Exception:" + ex.toString());
//		}
//		return wOrderShiftList;
//	}
//
//	public APSEntryGant APS_GenerateGantEntryByOrderList(int wCompanyID, int wLoginID, List<APSOrder> wOrderList,
//			APSCalendar wUnitCondition, OutResult<Integer> wErrorCode) {
//		APSEntryGant wEntryGant = new APSEntryGant();
//		wErrorCode.set(0);
//		try {
//			List<APSTaskLine> wTaskLineList = new ArrayList<APSTaskLine>();
//			// Step01:按产线、优先级、开工日期
//			List<APSOrder> wSortOrderList = wOrderList.stream()
//					.sorted(Comparator.comparing((APSOrder p) -> p.getLineID())// APSOrder::getLineID)
//							.thenComparing(APSOrder::getPriority)
//							.thenComparing(p -> p.getStartTime().getTime().getTime()))
//					.collect(Collectors.toList());
//			// List<APSOrder> wSortOrderList = wOrderList.OrderBy(p => p.LineID).ThenBy(p =>
//			// p.Priority).ThenBy(p => p.StartTime).ToList();
//			for (APSOrder wOrder : wSortOrderList) {
//				if (wOrder.FQTYShift > 0) {
//					wOrder.WorkHours = wOrder.FQTYShift * wOrder.WorkHour;
//				}
//			}
//			// Step02:按产线检查订单累计排班数是否超出产线最大排班数
//			List<APSOrder> wShiftOrderList = this.APS_CheckShiftOrderListWorkHours(wCompanyID, wSortOrderList,
//					wUnitCondition, wErrorCode);
//			// Step03:按订单生成产线任务、工序任务、工步任务
//			for (APSOrder wOrder : wShiftOrderList) {
//				if (wOrder.FQTYShift > 0) {
//					APSTaskLine wTaskLine = this.APS_GenerateTaskLineByOrder(wCompanyID, wOrder, wUnitCondition,
//							wErrorCode);
//					wTaskLine.StartTime = wOrder.StartTime;
//					wTaskLine.EndTime = wOrder.EndTime;
//					wTaskLineList.add(wTaskLine);
//				}
//			}
//			// Step03.1查询已排计划
//			List<APSTaskLine> wShiftTaskLineList = APSTaskDAO.getInstance().APS_QueryTaskLineListByShiftID(wCompanyID,
//					wLoginID, wUnitCondition.UnitID, wUnitCondition.ShiftID, wErrorCode);
//			if (wShiftTaskLineList.size() > 0) {
//				for (APSTaskLine wShiftTaskLine : wShiftTaskLineList) {
//					wShiftTaskLine.TaskPartList = APSTaskDAO.getInstance().APS_QueryTaskPartListByTaskLineID(wCompanyID,
//							wShiftTaskLine.ID, wErrorCode);
//				}
//				wTaskLineList.addAll(wShiftTaskLineList);
//			}
//			wTaskLineList = wTaskLineList.stream().sorted(Comparator.comparing(APSTaskLine::getLineID)
//					.thenComparing(p -> p.getStartTime().getTime().getTime())).collect(Collectors.toList());
//			// wTaskLineList = wTaskLineList.OrderBy(p => p.LineID).ThenBy(p
//			// =>p.StartTime).ToList();
//
//			wTaskLineList = APSTaskDAO.getInstance().APS_SetTaskLineListText(wCompanyID, wTaskLineList, wErrorCode);
//			// Step04:Gant List.
//			wEntryGant = this.APS_GenerateGantEntryByTaskLineList(wCompanyID, wLoginID, wTaskLineList, wErrorCode);
//		} catch (Exception ex) {
//			wErrorCode.set(MESException.Exception.getValue());
//			LoggerTool.SaveException("APSService", "APS_GenerateGantEntryByOrderList",
//					"Function Exception:" + ex.toString());
//		}
//		return wEntryGant;
//	}
//
//	public APSEntryGant APS_GenerateGantEntryByTaskLineList(int wCompanyID, int wLoginID,
//			List<APSTaskLine> wTaskLineList, OutResult<Integer> wErrorCode) {
//		wErrorCode.set(0);
//		APSEntryGant wEntryGant = new APSEntryGant();
//		try {
//			// wTaskLineList = wTaskLineList.OrderBy(p => p.LineID).ThenBy(p =>
//			// p.StartTime).ToList();
//			wTaskLineList = wTaskLineList.stream().sorted(Comparator.comparing(APSTaskLine::getLineID)
//					.thenComparing(p -> p.getStartTime().getTime().getTime())).collect(Collectors.toList());
//			int wGroupID = 0;
//			for (APSTaskLine wTaskLine : wTaskLineList) {
//				if (wTaskLine.TaskPartList.size() < 1)
//					wTaskLine.TaskPartList = APSTaskDAO.getInstance().APS_QueryTaskPartListByTaskLineID(wCompanyID,
//							wTaskLine.ID, wErrorCode);
//
//				if (wErrorCode.Result != 0)
//					break;
//
//				wGroupID++;
//				boolean wLocked = true;
//				for (APSTaskPart wTaskPart : wTaskLine.TaskPartList) {
//					boolean wIsNewDate = true;
//					for (APSColumn wColumn : wEntryGant.ColumnList) {
//						if (wColumn.WorkDate.compareTo(wTaskPart.ShiftDate) == 0) {
//							wIsNewDate = false;
//							break;
//						}
//					}
//					if (wIsNewDate) {
//						APSColumn wColumn = new APSColumn(wTaskPart);
//						wEntryGant.ColumnList.add(wColumn);
//					}
//					// Gant Part
//					boolean wIsNewPart = true;
//					for (APSGantPart wGantPart : wEntryGant.GantPartList) {
//						if (wGantPart.LineID == wTaskPart.LineID && wGantPart.OrderID == wTaskPart.OrderID
//								&& wGantPart.PartID == wTaskPart.PartID
//								&& wGantPart.TaskLineID == wTaskPart.TaskLineID) {
//							if (wGantPart.StartDate.compareTo(wTaskPart.ShiftDate) > 0)
//								wGantPart.StartDate = wTaskPart.ShiftDate;
//
//							if (wGantPart.EndDate.compareTo(wTaskPart.ShiftDate) < 0)
//								wGantPart.EndDate = wTaskPart.ShiftDate;
//
//							wGantPart.FQTYPlan += wTaskPart.FQTYShift;
//							boolean wNewTaskPart = true;
//							for (APSTaskPart wPart : wGantPart.TaskPartList) {
//								if (wPart.ShiftDate == wTaskPart.ShiftDate) {
//									wPart.FQTYShift += wTaskPart.FQTYShift;
//									wPart.FQTYDone += wTaskPart.FQTYDone;
//									wNewTaskPart = false;
//									break;
//								}
//							}
//							if (wNewTaskPart) {
//								wGantPart.TaskPartList.add(wTaskPart);
//								wGantPart.ShiftDays++;
//							}
//							wIsNewPart = false;
//						}
//					}
//					if (wIsNewPart) {
//						APSGantPart wGantPart = new APSGantPart(wTaskPart);
//						wGantPart.Locked = wLocked;
//						wGantPart.GroupID = wGroupID;
//						wGantPart.LineName = wTaskLine.LineName;
//						wGantPart.OrderNo = wTaskLine.OrderNo;
//						wGantPart.ProductNo = wTaskLine.ProductNo;
//						wGantPart.StartDate = wTaskPart.ShiftDate;
//						wGantPart.EndDate = wTaskPart.ShiftDate;
//						wEntryGant.GantPartList.add(wGantPart);
//						wLocked = false;
//					}
//				}
//				wEntryGant.TaskLineList.add(wTaskLine);
//			}
//			// Step05:列名按日期先后排序,填充空白单元格
//			// wEntryGant.ColumnList = wEntryGant.ColumnList.OrderBy(p =>
//			// p.WorkDate).ToList();
//			wEntryGant.ColumnList = wEntryGant.ColumnList.stream().sorted(Comparator.comparing(APSColumn::getWorkDate))
//					.collect(Collectors.toList());
//			int wColumnID = 0;
//			for (APSColumn wColumn : wEntryGant.ColumnList) {
//				wColumnID++;
//				wColumn.ColumnID = wColumnID;
//				wColumn.ColumnText = StringUtils.Format("{0}月{1}日", wColumn.WorkDate.get(Calendar.MONTH) + 1,
//						wColumn.WorkDate.get(Calendar.DATE));
//				for (APSGantPart wGantPart : wEntryGant.GantPartList) {
//					APSTaskPart wFillPart = new APSTaskPart();
//					boolean wIsNullColumn = true;
//					for (APSTaskPart wTaskPart : wGantPart.TaskPartList) {
//						wFillPart = wTaskPart.Clone();
//						if (wColumn.WorkDate.compareTo(wTaskPart.ShiftDate) == 0) {
//							wIsNullColumn = false;
//							break;
//						}
//					}
//					if (wIsNullColumn) {
//						wFillPart.ShiftID = this.APS_GenerateShiftIDByShiftPeriod(wCompanyID, 0, wColumn.WorkDate,
//								APSShiftPeriod.Day.getValue(), 0, wErrorCode);
//						wFillPart.LineID = wGantPart.LineID;
//						wFillPart.OrderID = wGantPart.OrderID;
//						wFillPart.PartID = wGantPart.PartID;
//						wFillPart.ShiftDate = wColumn.WorkDate;
//						wFillPart.FQTYShift = 0;
//						wGantPart.TaskPartList.add(wFillPart);
//					}
//				}
//			}
//
//			// Step06:设置文本
//			// Step06.1：工厂与事业部
//			MESEntry wFactoryModel = MESServer.MES_QueryEntryByMemory(wCompanyID, MESEntryEnum.FactoryModel);
//			// Step06.2：工艺模型
//			MESEntry wRouteModel = MESServer.MES_QueryEntryByMemory(wCompanyID, MESEntryEnum.RouteModel);
//			for (APSGantPart wGantPart : wEntryGant.GantPartList) {
//				wGantPart.LineName = FMCLineDAO.getInstance().FMC_QueryLineNameByID(wCompanyID, wGantPart.LineID,
//						wFactoryModel);
//				wGantPart.PartName = FPCPartDAO.getInstance().FPC_QueryPartNameByID(wCompanyID, wGantPart.PartID,
//						wRouteModel);
//				// wGantPart.TaskPartList = wGantPart.TaskPartList.OrderBy(p =>
//				// p.ShiftDate).ToList();
//
//				wGantPart.TaskPartList = wGantPart.TaskPartList.stream()
//						.sorted(Comparator.comparing(APSTaskPart::getShiftDate)).collect(Collectors.toList());
//			}
//		} catch (Exception ex) {
//			wErrorCode.set(MESException.Exception.getValue());
//			LoggerTool.SaveException("APSService", "APS_GenerateGantEntryByTaskLineList",
//					"Function Exception:" + ex.toString());
//		}
//		return wEntryGant;
//	}
//
//	public APSEntryGant APS_ChangeGantEntryDisplayMode(int wCompanyID, int wLoginID, APSEntryGant wEntryGant,
//			int wDisplayMode, OutResult<Integer> wErrorCode) {
//		wErrorCode.set(0);
//		try {
//			wErrorCode.set(MESException.Logic.getValue());
//			// Step01:检查人工调整的数据是否存在逻辑错误（开工日期，任务数）
//			boolean wCheckEntry = this.APS_CheckGantEntryAfterManualMode(wCompanyID, wEntryGant, wErrorCode);
//			if (wCheckEntry) {
//				wErrorCode.set(0);
//				// Step02:根据前段调整重现调整数据;
//				for (APSGantPart wGantPart : wEntryGant.GantPartList) {
//					List<APSTaskPart> wPlanedTaskPartList = new ArrayList<APSTaskPart>();
//					for (APSTaskPart wTaskPart : wGantPart.TaskPartList) {
//						if (wTaskPart.FQTYShift > 0) {
//							wPlanedTaskPartList.add(wTaskPart.Clone());
//							wTaskPart.FQTYShift = 0;
//						}
//					}
//					int wShifts = 0;
//					for (APSTaskPart wTaskPart : wGantPart.TaskPartList) {
//						if (wTaskPart.ShiftDate.compareTo(wGantPart.StartDate) >= 0
//								&& wTaskPart.ShiftDate.compareTo(wGantPart.EndDate) <= 0) {
//							if (wShifts < wPlanedTaskPartList.size())
//								wTaskPart.FQTYShift = wPlanedTaskPartList.get(wShifts).FQTYShift;
//
//							wShifts++;
//						}
//					}
//				}
//				// Step03:改变显示模式
//				wEntryGant.TaskLineList = wEntryGant.TaskLineList.stream().sorted(Comparator
//						.comparing(APSTaskLine::getLineID).thenComparing(p -> p.getStartTime().getTime().getTime()))
//						.collect(Collectors.toList());
//				// wEntryGant.TaskLineList = wEntryGant.TaskLineList.OrderBy(p =>
//				// p.LineID).ThenBy(p => p.StartTime).ToList();
//				if (wDisplayMode == 1) {
//					List<APSGantPart> wGantPartList = wEntryGant.GantPartList.stream()
//							.sorted(Comparator.comparing(APSGantPart::getLineID).thenComparing(p -> p.getPartOrderID()))
//							.collect(Collectors.toList());
//					// List<APSGantPart> wGantPartList = wEntryGant.GantPartList.OrderBy(p =>
//					// p.LineID).ThenBy(p => p.PartOrderID).ToList(); ;
//					wEntryGant.GantPartList.clear();
//					for (APSTaskLine wTaskLine : wEntryGant.TaskLineList) {
//						for (APSGantPart wGantPart : wGantPartList) {
//							if (wGantPart.LineID == wTaskLine.LineID && wGantPart.OrderID == wTaskLine.OrderID
//									&& wGantPart.TaskLineID == wTaskLine.ID) {
//								wEntryGant.GantPartList.add(wGantPart);
//							}
//						}
//					}
//				}
//				if (wDisplayMode == 2) {
//					wEntryGant.GantPartList = wEntryGant.GantPartList.stream()
//							.sorted(Comparator.comparing(APSGantPart::getLineID).thenComparing(p -> p.getPartOrderID()))
//							.collect(Collectors.toList());
//					// wEntryGant.GantPartList = wEntryGant.GantPartList.OrderBy(p =>
//					// p.LineID).ThenBy(p => p.PartOrderID).ToList();
//				}
//			}
//		} catch (Exception ex) {
//			wErrorCode.set(MESException.Exception.getValue());
//			LoggerTool.SaveException("APSService", "APS_ChangeGantEntryDisplayMode",
//					"Function Exception:" + ex.toString());
//		}
//		return wEntryGant;
//	}
//
//	public int APS_SaveGantEntryByShiftID(int wCompanyID, int wLoginID, APSEntryGant wEntryGant, int wShiftID,
//			OutResult<Integer> wErrorCode) {
//		wErrorCode.set(0);
//		int wID = 0;
//		try {
//			// Step01:检查人工调整的数据是否存在逻辑错误（开工日期，任务数）
//			wErrorCode.set(MESException.Logic.getValue());
//			boolean wCheckEntry = this.APS_CheckGantEntryAfterManualMode(wCompanyID, wEntryGant, wErrorCode);
//			if (wCheckEntry) {
//				wErrorCode.set(0);
//				// Step04:保存产线任务、工序任务、工步任务
//				for (APSTaskLine wLineItem : wEntryGant.TaskLineList) {
//					// if (wLineItem.ID > 0)
//					// continue;
//					if (wLineItem.FQTYShift > 0) {
//						// Step4.1存储产线计划
//						int wTaskLineID = APSTaskDAO.getInstance().APS_SaveTaskLine(wCompanyID, wLoginID, wLineItem,
//								wErrorCode);
//						if (wErrorCode.Result != 0)
//							break;
//						for (APSTaskPart wPartItem : wLineItem.TaskPartList) {
//							if (wPartItem.FQTYShift < 1)
//								continue;
//
//							wPartItem.OrderID = wLineItem.OrderID;
//							wPartItem.TaskLineID = wTaskLineID;
//							int wTaskPartID = APSTaskDAO.getInstance().APS_SaveTaskPart(wCompanyID, wLoginID, wPartItem,
//									wErrorCode);
//
//							if (wErrorCode.Result != 0)
//								break;
//							for (APSTaskStep wStepItem : wPartItem.TaskStepList) {
//								wStepItem.OrderID = wLineItem.OrderID;
//								wStepItem.TaskLineID = wTaskLineID;
//								wStepItem.TaskPartID = wTaskPartID;
//								wStepItem.ShiftDate = wPartItem.ShiftDate;
//								wStepItem.FQTYShift = wPartItem.FQTYShift;
//								int wTaskStepID = APSTaskDAO.getInstance().APS_SaveTaskStep(wCompanyID, wLoginID,
//										wStepItem, wErrorCode);
//								if (wErrorCode.Result != 0)
//									break;
//
//								wStepItem.ID = wTaskStepID;
//							}
//							wPartItem.ID = wTaskPartID;
//						}
//						if (wErrorCode.Result != 0)
//							break;
//
//						for (APSMaterial wMaterial : wLineItem.MaterialList) {
//							wMaterial.OrderID = wLineItem.OrderID;
//							wMaterial.TaskLineID = wTaskLineID;
//							wMaterial.ShiftID = wLineItem.ShiftID;
//							for (APSTaskPart wPartTask : wLineItem.TaskPartList) {
//								for (APSTaskStep wStepTask : wPartTask.TaskStepList) {
//									if (wStepTask.PartPointID == wMaterial.PartPointID) {
//										wMaterial.LineID = wStepTask.LineID;
//										wMaterial.PartID = wStepTask.PartID;
//										wMaterial.TaskLineID = wStepTask.TaskLineID;
//										wMaterial.TaskPartID = wStepTask.TaskPartID;
//										wMaterial.TaskStepID = wStepTask.ID;
//										break;
//									}
//								}
//							}
//							wID = APSMRPDAO.getInstance().APS_SaveMaterial(wCompanyID, wLoginID, wMaterial, wErrorCode);
//							if (wErrorCode.Result != 0)
//								break;
//						}
//					}
//				}
//			}
//		} catch (Exception ex) {
//			wErrorCode.set(MESException.Exception.getValue());
//			LoggerTool.SaveException("APSService", "APS_SaveOrderPlanListByShiftID",
//					"Function Exception:" + ex.toString());
//		}
//		return wID;
//	}
}
