package com.mes.code.server.serviceimpl.utils;

import java.io.File;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mes.code.server.service.po.mcs.MCSColumnInfo;
import com.mes.code.server.service.po.mcs.MCSTableInfo;
import com.mes.code.server.service.utils.StringUtils;
import com.mes.code.server.utils.Constants;

/**
 * 代码生成工具类
 * 
 * @author YouWang·Peng
 * @CreateTime 2020-1-21 15:49:58
 * @LastEditTime 2020-1-21 15:50:02
 *
 */
public class CodeUtils {
	private static Logger logger = LoggerFactory.getLogger(CodeUtils.class);

	public CodeUtils() {
	}

	private static CodeUtils Instance;

	public static CodeUtils getInstance() {
		if (Instance == null)
			Instance = new CodeUtils();
		return Instance;
	}

	/**
	 * JavaDAO
	 */
	public String GetJavaDAOClassString(MCSTableInfo wMCSTableInfo, String wNamespace, String wPrefix,
			boolean wIsStatusQuery) {
		String wResult = "";
		try {
			String wFilePath = Constants.getConfigPath() + "Java/JavaDAOTemplate.txt";
			File wFile = new File(wFilePath);
			if (!wFile.exists())
				return wResult;
			wResult = StringUtils.ReadAllText(wFilePath);
			if (StringUtils.isEmpty(wResult))
				return wResult;
			String ProjectName = "com.mes." + wPrefix.toLowerCase();
			String Package = wPrefix.toLowerCase();
			String ClassName = wMCSTableInfo.ClassName;
			String InsertSql = GetJavaInsertSql(wMCSTableInfo);
			String UpdateSql = GetJavaUpdateSql(wMCSTableInfo);
			String UpdateMapPut = GetJavaUpdateMapPut(wMCSTableInfo);
			String TableName = wMCSTableInfo.TableName;
			String QueryParms = GetJavaQueryParms(wMCSTableInfo, wIsStatusQuery);
			String QuerySql = GetQuerySql(wMCSTableInfo, wIsStatusQuery);
			String QueryMapPut = GetQueryMapPut(wMCSTableInfo, wIsStatusQuery);
			String QueryGet = GetQueryGet(wMCSTableInfo);

			wResult = wResult.replace("$ProjectName", ProjectName);
			wResult = wResult.replace("$Package", Package);
			wResult = wResult.replace("$ClassName", ClassName);
			wResult = wResult.replace("$InsertSql", InsertSql);
			wResult = wResult.replace("$UpdateSql", UpdateSql);
			wResult = wResult.replace("$UpdateMapPut", UpdateMapPut);
			wResult = wResult.replace("$TableName", TableName);
			wResult = wResult.replace("$QueryParms", QueryParms);
			wResult = wResult.replace("$QuerySql", QuerySql);
			wResult = wResult.replace("$QueryMapPut", QueryMapPut);
			wResult = wResult.replace("$QueryGet", QueryGet);

			String packageName = wNamespace;
			String keyType = wMCSTableInfo.ColumnInfoList.stream().filter(p -> p.IsPrimaryKey).findFirst()
					.get().ClassType;
			String xxqianzui = wPrefix.toLowerCase();
			String canshu = Getcanshu(wMCSTableInfo);
			String DefaultSelectValue = GetDefaultSelectValue(wMCSTableInfo);
			wResult = wResult.replace("$packageName", packageName);
			wResult = wResult.replace("$keyType", keyType);
			wResult = wResult.replace("$xxqianzui", xxqianzui);
			wResult = wResult.replace("$canshu", canshu);
			wResult = wResult.replace("$DefaultSelectValue", DefaultSelectValue);
			wResult = wResult.replace("String", "String");

			if (!wIsStatusQuery)
				wResult = wResult.replace(",wStateIDList.size() > 0 ? StringUtils.Join(\",\", wStateIDList) : \"0\"",
						"");
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	/**
	 * JavaController
	 */
	public String GetControllerString(MCSTableInfo wMCSTableInfo, String wNamespace, String wPrefix,
			boolean wIsStatusQuery) {
		String wResult = "";
		try {
			String wFilePath = Constants.getConfigPath() + "Java/APSManuCapacityController.txt";
			File wFile = new File(wFilePath);
			if (!wFile.exists())
				return wResult;
			wResult = StringUtils.ReadAllText(wFilePath);

			String qianzui = wPrefix;
			String leiming = wMCSTableInfo.ClassName.replace(qianzui, "");
			String chaxuncanshu = Getchaxuncanshu(wMCSTableInfo);
			String QueryParms = GetJavaQueryParms(wMCSTableInfo, wIsStatusQuery);
			String ClassName = wMCSTableInfo.ClassName;

			wResult = wResult.replace("$qianzui", qianzui);
			wResult = wResult.replace("$leiming", leiming);
			wResult = wResult.replace("$chaxuncanshu", chaxuncanshu);
			wResult = wResult.replace("$QueryParms", QueryParms);
			wResult = wResult.replace("$ClassName", ClassName);

			String packageName = wNamespace;
			String keyType = wMCSTableInfo.ColumnInfoList.stream().filter(p -> p.IsPrimaryKey).findFirst()
					.get().ClassType;
			String xxqianzui = wPrefix.toLowerCase();
			String canshu = Getcanshu(wMCSTableInfo);
			wResult = wResult.replace("$packageName", packageName);
			wResult = wResult.replace("$keyType", keyType);
			wResult = wResult.replace("$xxqianzui", xxqianzui);
			wResult = wResult.replace("$canshu", canshu);
			wResult = wResult.replace("String", "String");
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	/**
	 * JavaService
	 */
	public String GetJavaServiceString(MCSTableInfo wMCSTableInfo, String wNamespace, String wPrefix,
			boolean wIsStatusQuery) {
		String wResult = "";
		try {

			String wFilePath = Constants.getConfigPath() + "Java/APSService.txt";
			File wFile = new File(wFilePath);
			if (!wFile.exists())
				return wResult;
			wResult = StringUtils.ReadAllText(wFilePath);

			String qianzui = wPrefix;
			String leiming = wMCSTableInfo.ClassName.replace(qianzui, "");
			String chaxuncanshu = Getchaxuncanshu(wMCSTableInfo);
			String QueryParms = GetJavaQueryParms(wMCSTableInfo, wIsStatusQuery);
			String ClassName = wMCSTableInfo.ClassName;

			wResult = wResult.replace("$qianzui", qianzui);
			wResult = wResult.replace("$leiming", leiming);
			wResult = wResult.replace("$chaxuncanshu", chaxuncanshu);
			wResult = wResult.replace("$QueryParms", QueryParms);
			wResult = wResult.replace("$ClassName", ClassName);

			String packageName = wNamespace;
			String keyType = wMCSTableInfo.ColumnInfoList.stream().filter(p -> p.IsPrimaryKey).findFirst()
					.get().ClassType;
			String xxqianzui = wPrefix.toLowerCase();
			String canshu = Getcanshu(wMCSTableInfo);
			wResult = wResult.replace("$packageName", packageName);
			wResult = wResult.replace("$keyType", keyType);
			wResult = wResult.replace("$xxqianzui", xxqianzui);
			wResult = wResult.replace("$canshu", canshu);
			wResult = wResult.replace("String", "String");
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	/**
	 * JavaServiceImpl
	 */
	public String GetJavaServiceImplString(MCSTableInfo wMCSTableInfo, String wNamespace, String wPrefix,
			boolean wIsStatusQuery) {
		String wResult = "";
		try {
			String wFilePath = Constants.getConfigPath() + "Java/APSServiceImpl.txt";
			File wFile = new File(wFilePath);
			if (!wFile.exists())
				return wResult;
			wResult = StringUtils.ReadAllText(wFilePath);

			String qianzui = wPrefix;
			String leiming = wMCSTableInfo.ClassName.replace(qianzui, "");
			String chaxuncanshu = Getchaxuncanshu(wMCSTableInfo);
			String QueryParms = GetJavaQueryParms(wMCSTableInfo, wIsStatusQuery);
			String ClassName = wMCSTableInfo.ClassName;

			wResult = wResult.replace("$qianzui", qianzui);
			wResult = wResult.replace("$leiming", leiming);
			wResult = wResult.replace("$chaxuncanshu", chaxuncanshu);
			wResult = wResult.replace("$QueryParms", QueryParms);
			wResult = wResult.replace("$ClassName", ClassName);

			String packageName = wNamespace;
			String keyType = wMCSTableInfo.ColumnInfoList.stream().filter(p -> p.IsPrimaryKey).findFirst()
					.get().ClassType;
			String xxqianzui = wPrefix.toLowerCase();
			String canshu = Getcanshu(wMCSTableInfo);
			wResult = wResult.replace("$packageName", packageName);
			wResult = wResult.replace("$keyType", keyType);
			wResult = wResult.replace("$xxqianzui", xxqianzui);
			wResult = wResult.replace("$canshu", canshu);
			wResult = wResult.replace("String", "String");
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	/**
	 * C#DAO
	 */
	public String GetDAOClassString(MCSTableInfo wMCSTableInfo, String wNamespace, String wPrefix) {
		String wResult = "";
		try {
			String wFilePath = Constants.getConfigPath() + "DAO模板.txt";
			File wFile = new File(wFilePath);
			if (!wFile.exists())
				return wResult;
			wResult = StringUtils.ReadAllText(wFilePath);
			if (StringUtils.isEmpty(wResult))
				return wResult;
			String w命名空间 = wNamespace;
			String w类名 = wMCSTableInfo.ClassName;
			String w主键类型 = GetPrimaryKeyType(wMCSTableInfo);
			String w前缀名 = wPrefix;
			String w主键名 = GetPrimaryKeyName(wMCSTableInfo);
			String w表名 = wMCSTableInfo.TableName;
			String w表字段列表除主键外 = GetTableColumnListWithoutPrimaryKey(wMCSTableInfo);
			String w表字段参数 = GetTableColumnParms(wMCSTableInfo);
			String w参数赋值没主键 = GetParmsAsignWithoutPrimaryKey(wMCSTableInfo);
			String w主键转换类型 = GetPrimaryKeyTransformType(wMCSTableInfo);
			String w更新字符串 = GetUpdateString(wMCSTableInfo);
			String w参数赋值有主键 = GetParmsAsignWithPrimaryKey(wMCSTableInfo);
			String w查询条件字段 = GetQueryConditionColumn(wMCSTableInfo);
			String w查询条件SQL = GetQueryConditionSQL(wMCSTableInfo);
			String w条件赋值 = GetConditionAsign(wMCSTableInfo);
			String w属性获取 = GetAttributeObtain(wMCSTableInfo);
			wResult = wResult.replace("@命名空间", w命名空间);
			wResult = wResult.replace("@类名", w类名);
			wResult = wResult.replace("@主键类型", w主键类型);
			wResult = wResult.replace("@前缀名", w前缀名);
			wResult = wResult.replace("@主键名", w主键名);
			wResult = wResult.replace("@表名", w表名);
			wResult = wResult.replace("@表字段列表除主键外", w表字段列表除主键外);
			wResult = wResult.replace("@表字段参数", w表字段参数);
			wResult = wResult.replace("@参数赋值没主键", w参数赋值没主键);
			wResult = wResult.replace("@主键转换类型", w主键转换类型);
			wResult = wResult.replace("@更新字符串", w更新字符串);
			wResult = wResult.replace("@参数赋值有主键", w参数赋值有主键);
			wResult = wResult.replace("@查询条件字段", w查询条件字段);
			wResult = wResult.replace("@查询条件SQL", w查询条件SQL);
			wResult = wResult.replace("@条件赋值", w条件赋值);
			wResult = wResult.replace("@属性获取", w属性获取);
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	/**
	 * C#Model
	 */
	public String GetModelString(MCSTableInfo wMCSTableInfo, String wNamespace) {
		String wResult = "";
		try {
			StringBuilder wStringBuilder = new StringBuilder();
			wStringBuilder.append("using System;\n");
			wStringBuilder.append("using System.Collections.Generic;\n");
			wStringBuilder.append("using System.Linq;\n");
			wStringBuilder.append("using System.Runtime.Serialization;\n");
			wStringBuilder.append("using System.Web;\n\n");
			wStringBuilder.append(MessageFormat.format("namespace {0}\n", wNamespace));
			wStringBuilder.append("{\n");
			wStringBuilder.append("[DataContract]\n");
			wStringBuilder.append(MessageFormat.format("public class {0}\n", wMCSTableInfo.ClassName));
			wStringBuilder.append("{\n");
			for (MCSColumnInfo wMCSColumnInfo : wMCSTableInfo.ColumnInfoList) {
				wStringBuilder.append("[DataMember]\n");
				wStringBuilder.append(MessageFormat.format("public {0} {1} {{ get; set; }}\n\n",
						wMCSColumnInfo.ClassType, wMCSColumnInfo.Name));
			}
			wStringBuilder.append("}\n");
			wStringBuilder.append("}");
			wResult = wStringBuilder.toString();
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	private String GetDefaultSelectValue(MCSTableInfo wMCSTableInfo) {
		String wResult = "";
		try {
			if (wMCSTableInfo.ColumnInfoList.stream().anyMatch(p -> p.IsQuery && p.ClassType == "DateTime")) {
				List<MCSColumnInfo> wList = wMCSTableInfo.ColumnInfoList.stream()
						.filter(p -> !p.IsPrimaryKey && p.IsQuery).collect(Collectors.toList());
				for (int i = 0; i < wList.size(); i++) {
					if (i < wList.size() - 1) {
						switch (wList.get(i).DatabaseType) {
						case "int":
						case "smallint":
							wResult += "-1,";
							break;
						case "double":
							break;
						case "char":
						case "varchar":
						case "longtext":
							wResult += "\"\",";
							break;
						case "float":
							break;
						case "tinyint":
							break;
						case "datetime":
							break;
						case "enum":
							break;
						case "bigint":
						case "bigint unsigned":
							wResult += "-1,";
							break;
						}
					} else {
						switch (wList.get(i).DatabaseType) {
						case "int":
						case "smallint":
							wResult += "-1";
							break;
						case "double":
							break;
						case "char":
						case "varchar":
						case "longtext":
							wResult += "\"\"";
							break;
						case "float":
							break;
						case "tinyint":
							break;
						case "datetime":
							break;
						case "enum":
							break;
						case "bigint":
						case "bigint unsigned":
							wResult += "-1";
							break;
						}
					}
					wResult += "wCalendar,wCalendar";
				}
			} else {
				List<MCSColumnInfo> wList = wMCSTableInfo.ColumnInfoList.stream()
						.filter(p -> !p.IsPrimaryKey && p.IsQuery).collect(Collectors.toList());
				for (int i = 0; i < wList.size(); i++) {
					if (i < wList.size() - 1) {
						switch (wList.get(i).DatabaseType) {
						case "int":
						case "smallint":
							wResult += "-1,";
							break;
						case "double":
							break;
						case "char":
						case "varchar":
						case "longtext":
							wResult += "\"\",";
							break;
						case "float":
							break;
						case "tinyint":
							break;
						case "datetime":
							break;
						case "enum":
							break;
						case "bigint":
						case "bigint unsigned":
							wResult += "-1,";
							break;
						}
					} else {
						switch (wList.get(i).DatabaseType) {
						case "int":
						case "smallint":
							wResult += "-1";
							break;
						case "double":
							break;
						case "char":
						case "varchar":
						case "longtext":
							wResult += "\"\"";
							break;
						case "float":
							break;
						case "tinyint":
							break;
						case "datetime":
							break;
						case "enum":
							break;
						case "bigint":
						case "bigint unsigned":
							wResult += "-1";
							break;
						}
					}
				}
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	private String Getcanshu(MCSTableInfo wMCSTableInfo) {
		String wResult = "";
		try {
			if (wMCSTableInfo.ColumnInfoList.stream().anyMatch(p -> p.IsQuery && p.ClassType == "DateTime")) {
				wResult = String.join(",",
						wMCSTableInfo.ColumnInfoList.stream().filter(p -> p.IsQuery && p.ClassType != "DateTime")
								.collect(Collectors.toList()).stream().map(p -> "w" + p.Name)
								.collect(Collectors.toList()));
				wResult += ",wStartTime,wEndTime";
			} else
				wResult = String.join(",", wMCSTableInfo.ColumnInfoList.stream().filter(p -> p.IsQuery)
						.collect(Collectors.toList()).stream().map(p -> "w" + p.Name).collect(Collectors.toList()));
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	private String Getchaxuncanshu(MCSTableInfo wMCSTableInfo) {
		String wResult = "";
		try {
			List<MCSColumnInfo> wInfoList = wMCSTableInfo.ColumnInfoList.stream().filter(p -> p.IsQuery)
					.collect(Collectors.toList());
			if (wInfoList == null || wInfoList.size() <= 0)
				return wResult;

			StringBuilder wSB = new StringBuilder();
			if (wInfoList.stream().anyMatch(p -> p.ClassType == "DateTime")) {
				wInfoList.removeIf(p -> p.ClassType == "DataTime");
				for (MCSColumnInfo wItem : wInfoList) {
					switch (wItem.DatabaseType) {
					case "int":
					case "smallint":
						wSB.append(MessageFormat.format(
								"int w{0} = StringUtils.parseInt(request.getParameter(\"{0}\"));\n", wItem.Name));
						break;
					case "double":
						wSB.append(MessageFormat.format(
								"double w{0} = StringUtils.parseDouble(request.getParameter(\"{0}\"));\n", wItem.Name));
						break;
					case "char":
					case "varchar":
					case "longtext":
						wSB.append(MessageFormat.format(
								"String w{0} = StringUtils.parseString(request.getParameter(\"{0}\"));\n", wItem.Name));
						break;
					case "float":
						wSB.append(MessageFormat.format(
								"String w{0} = StringUtils.parseFloat(request.getParameter(\"{0}\"));\n", wItem.Name));
						break;
					case "tinyint":
						break;
					case "datetime":
						break;
					case "enum":
						break;
					case "bigint":
					case "bigint unsigned":
						wSB.append(MessageFormat.format(
								"long w{0} = StringUtils.parseLong(request.getParameter(\"{0}\"));\n", wItem.Name));
						break;
					}
				}
				wSB.append("Calendar wStartTime = StringUtils.parseCalendar(request.getParameter(\"StartTime\"));\n");
				wSB.append("Calendar wEndTime = StringUtils.parseCalendar(request.getParameter(\"EndTime\"));\n");
			} else {
				for (MCSColumnInfo wItem : wInfoList) {
					switch (wItem.DatabaseType) {
					case "int":
					case "smallint":
						wSB.append(MessageFormat.format(
								"int w{0} = StringUtils.parseInt(request.getParameter(\"{0}\"));\n", wItem.Name));
						break;
					case "double":
						wSB.append(MessageFormat.format(
								"double w{0} = StringUtils.parseDouble(request.getParameter(\"{0}\"));\n", wItem.Name));
						break;
					case "char":
					case "varchar":
					case "longtext":
						wSB.append(MessageFormat.format(
								"String w{0} = StringUtils.parseString(request.getParameter(\"{0}\"));\n", wItem.Name));
						break;
					case "float":
						wSB.append(MessageFormat.format(
								"String w{0} = StringUtils.parseFloat(request.getParameter(\"{0}\"));\n", wItem.Name));
						break;
					case "tinyint":
						break;
					case "datetime":
						break;
					case "enum":
						break;
					case "bigint":
					case "bigint unsigned":
						wSB.append(MessageFormat.format(
								"long w{0} = StringUtils.parseLong(request.getParameter(\"{0}\"));\n", wItem.Name));
						break;
					}
				}
			}
			wResult = wSB.toString();
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	private String GetQueryGet(MCSTableInfo wMCSTableInfo) {
		String wResult = "";
		try {
			StringBuilder wSB = new StringBuilder();
			for (MCSColumnInfo wItem : wMCSTableInfo.ColumnInfoList) {
				switch (wItem.DatabaseType) {
				case "int":
				case "smallint":
					wSB.append(MessageFormat.format("wItem.{0} = StringUtils.parseInt(wReader.get(\"{0}\"));\n",
							wItem.Name));
					break;
				case "double":
					wSB.append(MessageFormat.format("wItem.{0} = StringUtils.parseDouble(wReader.get(\"{0}\"));\n",
							wItem.Name));
					break;
				case "float":
					wSB.append(MessageFormat.format("wItem.{0} = StringUtils.parseFloat(wReader.get(\"{0}\"));\n",
							wItem.Name));
					break;
				case "char":
				case "varchar":
				case "longtext":
					wSB.append(MessageFormat.format("wItem.{0} = StringUtils.parseString(wReader.get(\"{0}\"));\n",
							wItem.Name));
					break;
				case "tinyint":
					wSB.append(MessageFormat.format("wItem.{0} = StringUtils.parseBoolean(wReader.get(\"{0}\"));\n",
							wItem.Name));
					break;
				case "datetime":
					wSB.append(MessageFormat.format("wItem.{0} = StringUtils.parseCalendar(wReader.get(\"{0}\"));\n",
							wItem.Name));
					break;
				case "enum":
					break;
				case "bigint":
				case "bigint unsigned":
					wSB.append(MessageFormat.format("wItem.{0} = StringUtils.parseLong(wReader.get(\"{0}\"));\n",
							wItem.Name));
					break;
				default:
					break;
				}
			}
			wResult = wSB.toString();
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	private String GetQueryMapPut(MCSTableInfo wMCSTableInfo, boolean wIsStatusQuery) {
		String wResult = "";
		try {
			String wOne = String.join("\n",
					wMCSTableInfo.ColumnInfoList.stream().filter(p -> p.IsQuery).collect(Collectors.toList()).stream()
							.map(p -> MessageFormat.format("wParamMap.put(\"w{0}\", w{0});", p.Name))
							.collect(Collectors.toList()));
			if (wMCSTableInfo.ColumnInfoList.stream().filter(p -> p.IsQuery && p.ClassType.equals("DateTime"))
					.findFirst().isPresent())
				wOne += "\nwParamMap.put(\"wStartTime\", wStartTime);\nwParamMap.put(\"wEndTime\", wEndTime);";
			if (wIsStatusQuery)
				wOne += "\nwParamMap.put(\"wStatus\", StringUtils.Join(\",\", wStateIDList));";
			wResult = wOne;
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	private String GetQuerySql(MCSTableInfo wMCSTableInfo, boolean wIsStatusQuery) {
		String wResult = "";
		try {
			StringBuilder wSB = new StringBuilder();
			for (MCSColumnInfo wItem : wMCSTableInfo.ColumnInfoList.stream().filter(p -> p.IsQuery)
					.collect(Collectors.toList())) {
				switch (wItem.DatabaseType) {
				case "int":
				case "smallint":
					wSB.append(MessageFormat.format(" and ( :w{0} <= 0 or :w{0} = {0} )", wItem.Name));
					break;
				case "double":
					break;
				case "char":
				case "varchar":
				case "longtext":
					wSB.append(
							MessageFormat.format(" and ( :w{0} is null or :w{0} = '''''''' or :w{0} = {0} )", wItem.Name));
					break;
				case "tinyint":
					break;
				case "datetime":
					wSB.append(MessageFormat.format(
							"and ( :wStartTime <=str_to_date(''''2010-01-01'''', ''''%Y-%m-%d'''')  or :wStartTime <=  {0} ) "
									+ "and ( :wEndTime <=str_to_date(''''2010-01-01'''', ''''%Y-%m-%d'''')  or :wEndTime >=  {0} )",
							wItem.Name));
					break;
				case "enum":
					break;
				case "bigint":
				case "bigint unsigned":
					wSB.append(MessageFormat.format(" and ( :w{0} <= 0 or :w{0} = {0} )", wItem.Name));
					break;
				}
			}
			if (wIsStatusQuery)
				wSB.append(" and ( :wStatus is null or :wStatus = '''' or Status in ({1}))");

			wResult = MessageFormat.format("SELECT * FROM '{'0'}'.{0} WHERE  1=1 {1};", wMCSTableInfo.TableName,
					wSB.toString());
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	private String GetJavaQueryParms(MCSTableInfo wMCSTableInfo, boolean wIsStatusQuery) {
		String wResult = "";
		try {
			String wOne = String.join(",", wMCSTableInfo.ColumnInfoList.stream()
					.filter(p -> p.IsQuery && !p.ClassType.equals("DateTime")).collect(Collectors.toList()).stream()
					.map(p -> MessageFormat.format("{0} w{1}", p.ClassType, p.Name)).collect(Collectors.toList()));
			if (wMCSTableInfo.ColumnInfoList.stream().filter(p -> p.ClassType.equals("DateTime") && p.IsQuery)
					.findFirst().isPresent())
				wOne += ",Calendar wStartTime,Calendar wEndTime";
			if (wIsStatusQuery)
				wOne += ", List<Integer> wStateIDList";
			wResult = wOne;
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	private String GetJavaUpdateMapPut(MCSTableInfo wMCSTableInfo) {
		String wResult = "";
		try {
			wResult = String.join("\n", wMCSTableInfo.ColumnInfoList.stream().map(
					p -> MessageFormat.format("wParamMap.put(\"{0}\", w{1}.{0});", p.Name, wMCSTableInfo.ClassName))
					.collect(Collectors.toList()));
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	private String GetJavaUpdateSql(MCSTableInfo wMCSTableInfo) {
		String wResult = "";
		try {
			MCSColumnInfo wKeyInfo = wMCSTableInfo.ColumnInfoList.stream().filter(p -> p.IsPrimaryKey).findFirst()
					.get();
			String wOne = String.join(",",
					wMCSTableInfo.ColumnInfoList.stream().filter(p -> !p.IsPrimaryKey).collect(Collectors.toList())
							.stream().map(p -> p.Name + " = :" + p.Name).collect(Collectors.toList()));
			wResult = MessageFormat.format("UPDATE '{'0'}'.{0} SET {1} WHERE {2};", wMCSTableInfo.TableName, wOne,
					wKeyInfo.Name + " = :" + wKeyInfo.Name);
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	private String GetJavaInsertSql(MCSTableInfo wMCSTableInfo) {
		String wResult = "";
		try {
			String wOne = String.join(",", wMCSTableInfo.ColumnInfoList.stream().filter(p -> !p.IsPrimaryKey)
					.collect(Collectors.toList()).stream().map(p -> p.Name).collect(Collectors.toList()));
			String wTwo = String.join(",", wMCSTableInfo.ColumnInfoList.stream().filter(p -> !p.IsPrimaryKey)
					.collect(Collectors.toList()).stream().map(p -> ":" + p.Name).collect(Collectors.toList()));
			wResult = MessageFormat.format("INSERT INTO '{'0'}'.{2}({0}) VALUES({1});", wOne, wTwo,
					wMCSTableInfo.TableName);
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	private String GetAttributeObtain(MCSTableInfo wMCSTableInfo) {
		String wResult = "";
		try {
			StringBuilder wStringBuilder = new StringBuilder();
			for (MCSColumnInfo wMCSColumnInfo : wMCSTableInfo.ColumnInfoList) {
				switch (wMCSColumnInfo.DatabaseType) {
				case "int":
				case "smallint":
					wStringBuilder.append(MessageFormat.format(
							"\t\t\t\t\tw{0}.{1} = StringUtils.ParseInt(wSqlDataReader[\"{1}\"]);\n",
							wMCSTableInfo.ClassName, wMCSColumnInfo.Name));
					break;
				case "double":
					wStringBuilder.append(MessageFormat.format(
							"\t\t\t\t\tw{0}.{1} = StringUtils.ParseDouble(wSqlDataReader[\"{1}\"]);\n",
							wMCSTableInfo.ClassName, wMCSColumnInfo.Name));
					break;
				case "float":
					wStringBuilder.append(MessageFormat.format(
							"\t\t\t\t\tw{0}.{1} = StringUtils.ParseFloat(wSqlDataReader[\"{1}\"]);\n",
							wMCSTableInfo.ClassName, wMCSColumnInfo.Name));
					break;
				case "char":
				case "varchar":
				case "longtext":
					wStringBuilder.append(MessageFormat.format(
							"\t\t\t\t\tw{0}.{1} = StringUtils.ParseString(wSqlDataReader[\"{1}\"]);\n",
							wMCSTableInfo.ClassName, wMCSColumnInfo.Name));
					break;
				case "tinyint":
					wStringBuilder.append(MessageFormat.format(
							"\t\t\t\t\tw{0}.{1} = StringUtils.ParseBoolean(wSqlDataReader[\"{1}\"]);\n",
							wMCSTableInfo.ClassName, wMCSColumnInfo.Name));
					break;
				case "datetime":
					wStringBuilder.append(MessageFormat.format(
							"\t\t\t\t\tw{0}.{1} = StringUtils.ParseDate(wSqlDataReader[\"{1}\"]);\n",
							wMCSTableInfo.ClassName, wMCSColumnInfo.Name));
					break;
				case "enum":
					wStringBuilder.append(MessageFormat.format(
							"\t\t\t\t\tw{0}.{1} = StringUtils.ParseEnum(wSqlDataReader[\"{1}\"]);\n",
							wMCSTableInfo.ClassName, wMCSColumnInfo.Name));
					break;
				case "bigint":
				case "bigint unsigned":
					wStringBuilder.append(MessageFormat.format(
							"\t\t\t\t\tw{0}.{1} = StringUtils.ParseLong(wSqlDataReader[\"{1}\"]);\n",
							wMCSTableInfo.ClassName, wMCSColumnInfo.Name));
					break;
				default:
					break;
				}
			}
			wResult = wStringBuilder.toString();
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	private String GetConditionAsign(MCSTableInfo wMCSTableInfo) {
		String wResult = "";
		try {
			StringBuilder wStringBuilder = new StringBuilder();
			List<MCSColumnInfo> wMCSColumnInfoList = wMCSTableInfo.ColumnInfoList.stream()
					.filter(p -> p.IsQuery && p.ClassType != "DateTime").collect(Collectors.toList());
			if (wMCSColumnInfoList != null && wMCSColumnInfoList.size() > 0) {
				for (MCSColumnInfo wMCSColumnInfo : wMCSColumnInfoList)
					wStringBuilder.append(MessageFormat.format("\t\t\t\twSqlCommand.AddWithValue(\"@w{0}\", w{0});\n",
							wMCSColumnInfo.Name));
			}
			if (wMCSTableInfo.ColumnInfoList.stream().anyMatch(p -> p.IsQuery && p.ClassType == "DateTime")) {
				wStringBuilder.append("\t\t\t\twSqlCommand.AddWithValue(\"@wStartTime\", wStartTime);\n");
				wStringBuilder.append("\t\t\t\twSqlCommand.AddWithValue(\"@wEndTime\", wEndTime);\n");
			}
			wResult = wStringBuilder.toString();
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	private String GetQueryConditionSQL(MCSTableInfo wMCSTableInfo) {
		String wResult = "";
		try {
			StringBuilder wStringBuilder = new StringBuilder();

			if (wMCSTableInfo.ColumnInfoList.stream().anyMatch(p -> p.IsQuery))
				wStringBuilder.append(
						MessageFormat.format("\t\t\t\twSqlCommand.CommandText = \"SELECT * FROM {0} WHERE 1=1\"\n",
								wMCSTableInfo.TableName));
			else
				wStringBuilder.append(
						MessageFormat.format("\t\t\t\twSqlCommand.CommandText = \"SELECT * FROM {0} WHERE 1=1\";\n",
								wMCSTableInfo.TableName));
			List<MCSColumnInfo> wInfoList = wMCSTableInfo.ColumnInfoList.stream()
					.filter(p -> p.IsQuery && p.ClassType != "DateTime").collect(Collectors.toList());
			for (int i = 0; i < wInfoList.size(); i++) {
				if (i < wInfoList.size() - 1) {
					switch (wInfoList.get(i).DatabaseType) {
					case "int":
					case "smallint":
						wStringBuilder.append(MessageFormat.format(
								"\t\t\t\t\t\t\t\t\t+ \" and(@w{0} <=0 or {0}= @w{0})\"\n", wInfoList.get(i).Name));
						break;
					case "double":
						wStringBuilder.append(MessageFormat.format(
								"\t\t\t\t\t\t\t\t\t+ \" and(@w{0} <=0 or {0}= @w{0})\"\n", wInfoList.get(i).Name));
						break;
					case "char":
					case "varchar":
					case "longtext":
						wStringBuilder.append(MessageFormat.format(
								"\t\t\t\t\t\t\t\t\t+ \" and(@w{0} is null or @w{0} = '' or {0}= @w{0})\"\n",
								wInfoList.get(i).Name));
						break;
					case "tinyint":
						wStringBuilder.append(MessageFormat.format(
								"\t\t\t\t\t\t\t\t\t+ \" and(@w{0} <=0 or {0}= @w{0})\"\n", wInfoList.get(i).Name));
						break;
					case "datetime":
						break;
					case "enum":
						wStringBuilder.append(MessageFormat.format(
								"\t\t\t\t\t\t\t\t\t+ \" and(@w{0} <=0 or {0}= @w{0})\"\n", wInfoList.get(i).Name));
						break;
					case "bigint":
					case "bigint unsigned":
						wStringBuilder.append(MessageFormat.format(
								"\t\t\t\t\t\t\t\t\t+ \" and(@w{0} <=0 or {0}= @w{0})\"\n", wInfoList.get(i).Name));
						break;
					}
				} else {
					switch (wInfoList.get(i).DatabaseType) {
					case "int":
					case "smallint":
					case "double":
					case "tinyint":
					case "enum":
					case "bigint":
					case "bigint unsigned":
						if (wMCSTableInfo.ColumnInfoList.stream().anyMatch(p -> p.IsQuery && p.ClassType == "DateTime"))
							wStringBuilder.append(MessageFormat.format(
									"\t\t\t\t\t\t\t\t\t+ \" and(@w{0} <=0 or {0}= @w{0})\"\n", wInfoList.get(i).Name));
						else
							wStringBuilder.append(MessageFormat.format(
									"\t\t\t\t\t\t\t\t\t+ \" and(@w{0} <=0 or {0}= @w{0})\";\n", wInfoList.get(i).Name));
						break;
					case "char":
					case "varchar":
					case "longtext":
						if (wMCSTableInfo.ColumnInfoList.stream().anyMatch(p -> p.IsQuery && p.ClassType == "DateTime"))
							wStringBuilder.append(MessageFormat.format(
									"\t\t\t\t\t\t\t\t\t+ \" and(@w{0} is null or @w{0} = '' or {0}= @w{0})\"\n",
									wInfoList.get(i).Name));
						else
							wStringBuilder.append(MessageFormat.format(
									"\t\t\t\t\t\t\t\t\t+ \" and(@w{0} is null or @w{0} = '' or {0}= @w{0})\";\n",
									wInfoList.get(i).Name));
						break;
					case "datetime":
						break;
					}
				}
			}
			if (wMCSTableInfo.ColumnInfoList.stream().anyMatch(p -> p.IsQuery && p.ClassType == "DateTime")) {
				MCSColumnInfo wMCSColumnInfo = wMCSTableInfo.ColumnInfoList.stream()
						.filter(p -> p.IsQuery && p.ClassType == "DateTime").findFirst().get();
				wStringBuilder.append(MessageFormat.format(
						"\t\t\t\t\t\t\t\t\t+ \" and(@wStartTime <= '2010-1-1' or {0}>= @wStartTime)\"\n",
						wMCSColumnInfo.Name));
				wStringBuilder.append(MessageFormat.format(
						"\t\t\t\t\t\t\t\t\t+ \" and(@wEndTime <= '2010-1-1' or {0}<= @wEndTime)\";\n",
						wMCSColumnInfo.Name));
			}
			wResult = wStringBuilder.toString();
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	private String GetQueryConditionColumn(MCSTableInfo wMCSTableInfo) {
		String wResult = "";
		try {
			List<MCSColumnInfo> wList = wMCSTableInfo.ColumnInfoList.stream()
					.filter(p -> p.IsQuery && p.ClassType != "DateTime").collect(Collectors.toList());
			StringBuilder wStringBuilder = new StringBuilder();
			if (wList.size() > 0) {
				for (int i = 0; i < wList.size(); i++) {
					if (i < wList.size() - 1)
						wStringBuilder
								.append(MessageFormat.format("{0} w{1},", wList.get(i).ClassType, wList.get(i).Name));
					else
						wStringBuilder
								.append(MessageFormat.format("{0} w{1},", wList.get(i).ClassType, wList.get(i).Name));
				}
			}
			if (wMCSTableInfo.ColumnInfoList.stream().anyMatch(p -> p.IsQuery && p.ClassType == "DateTime"))
				wStringBuilder.append("DateTime wStartTime,DateTime wEndTime,");
			wResult = wStringBuilder.toString();
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	private String GetParmsAsignWithPrimaryKey(MCSTableInfo wMCSTableInfo) {
		String wResult = "";
		try {
			List<MCSColumnInfo> wMCSColumnInfoList = wMCSTableInfo.ColumnInfoList;
			if (wMCSColumnInfoList != null && wMCSColumnInfoList.size() > 0) {
				StringBuilder wStringBuilder = new StringBuilder();
				for (int i = 0; i < wMCSColumnInfoList.size(); i++)
					wStringBuilder
							.append(MessageFormat.format("\t\t\t\t\twSqlCommand.AddWithValue(\"@w{0}\", w{1}.{0});\n",
									wMCSColumnInfoList.get(i).Name, wMCSTableInfo.ClassName));
				wResult = wStringBuilder.toString();
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	private String GetUpdateString(MCSTableInfo wMCSTableInfo) {
		String wResult = "";
		try {
			List<MCSColumnInfo> wMCSColumnInfoList = wMCSTableInfo.ColumnInfoList.stream().filter(p -> !p.IsPrimaryKey)
					.collect(Collectors.toList());
			if (wMCSColumnInfoList != null && wMCSColumnInfoList.size() > 0) {
				StringBuilder wStringBuilder = new StringBuilder();
				for (int i = 0; i < wMCSColumnInfoList.size(); i++) {
					if (i < wMCSColumnInfoList.size() - 1)
						wStringBuilder.append(MessageFormat.format("{0}=@w{0},", wMCSColumnInfoList.get(i).Name));
					else
						wStringBuilder.append(MessageFormat.format("{0}=@w{0}", wMCSColumnInfoList.get(i).Name));
				}
				wResult = wStringBuilder.toString();
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	private String GetPrimaryKeyTransformType(MCSTableInfo wMCSTableInfo) {
		String wResult = "";
		try {
			MCSColumnInfo wMCSColumnInfo = wMCSTableInfo.ColumnInfoList.stream().filter(p -> p.IsPrimaryKey).findFirst()
					.get();
			switch (wMCSColumnInfo.DatabaseType) {
			case "int":
			case "smallint":
				wResult = "ParseInt";
				break;
			case "double":
				wResult = "ParseDouble";
				break;
			case "float":
				wResult = "ParseFloat";
				break;
			case "char":
			case "varchar":
			case "longtext":
				wResult = "ParseString";
				break;
			case "tinyint":
				wResult = "ParseBoolean";
				break;
			case "datetime":
				wResult = "ParseDate";
				break;
			case "enum":
				wResult = "ParseEnum";
				break;
			case "bigint":
			case "bigint unsigned":
				wResult = "ParseLong";
				break;
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	private String GetParmsAsignWithoutPrimaryKey(MCSTableInfo wMCSTableInfo) {
		String wResult = "";
		try {
			List<MCSColumnInfo> wMCSColumnInfoList = wMCSTableInfo.ColumnInfoList.stream().filter(p -> !p.IsPrimaryKey)
					.collect(Collectors.toList());
			if (wMCSColumnInfoList != null && wMCSColumnInfoList.size() > 0) {
				StringBuilder wStringBuilder = new StringBuilder();
				for (int i = 0; i < wMCSColumnInfoList.size(); i++)
					wStringBuilder
							.append(MessageFormat.format("\t\t\t\t\twSqlCommand.AddWithValue(\"@w{0}\", w{1}.{0});\n",
									wMCSColumnInfoList.get(i).Name, wMCSTableInfo.ClassName));
				wResult = wStringBuilder.toString();
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	private String GetTableColumnParms(MCSTableInfo wMCSTableInfo) {
		String wResult = "";
		try {
			List<MCSColumnInfo> wMCSColumnInfoList = wMCSTableInfo.ColumnInfoList.stream().filter(p -> !p.IsPrimaryKey)
					.collect(Collectors.toList());
			if (wMCSColumnInfoList != null && wMCSColumnInfoList.size() > 0) {
				StringBuilder wStringBuilder = new StringBuilder();
				for (int i = 0; i < wMCSColumnInfoList.size(); i++) {
					if (i < wMCSColumnInfoList.size() - 1)
						wStringBuilder.append("@w" + wMCSColumnInfoList.get(i).Name + ",");
					else
						wStringBuilder.append("@w" + wMCSColumnInfoList.get(i).Name);
				}
				wResult = wStringBuilder.toString();
			}
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	private String GetTableColumnListWithoutPrimaryKey(MCSTableInfo wMCSTableInfo) {
		String wResult = "";
		try {
			List<MCSColumnInfo> wMCSColumnInfoList = wMCSTableInfo.ColumnInfoList.stream().filter(p -> !p.IsPrimaryKey)
					.collect(Collectors.toList());
			if (wMCSColumnInfoList != null && wMCSColumnInfoList.size() > 0)
				wResult = String.join(",", wMCSColumnInfoList.stream().map(p -> p.Name).collect(Collectors.toList()));
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	private String GetPrimaryKeyName(MCSTableInfo wMCSTableInfo) {
		String wResult = "";
		try {
			MCSColumnInfo wMCSColumnInfo = wMCSTableInfo.ColumnInfoList.stream().filter(p -> p.IsPrimaryKey).findFirst()
					.get();
			wResult = wMCSColumnInfo.Name;
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}

	private String GetPrimaryKeyType(MCSTableInfo wMCSTableInfo) {
		String wResult = "";
		try {
			MCSColumnInfo wMCSColumnInfo = wMCSTableInfo.ColumnInfoList.stream().filter(p -> p.IsPrimaryKey).findFirst()
					.get();
			wResult = wMCSColumnInfo.ClassType;
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
		return wResult;
	}
}
