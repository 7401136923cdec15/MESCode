package com.mes.code.server.controller.mcs;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mes.code.server.controller.BaseController;
import com.mes.code.server.service.po.mcs.MCSDatabase;
import com.mes.code.server.service.po.mcs.MCSTableInfo;
import com.mes.code.server.service.utils.CloneTool;
import com.mes.code.server.service.utils.StringUtils;
import com.mes.code.server.serviceimpl.dao.mcs.DatabaseDAO;
import com.mes.code.server.serviceimpl.utils.CodeUtils;
import com.mes.code.server.utils.RetCode;

/**
 * 
 * @author PengYouWang
 * @CreateTime 2020-1-10 09:57:02
 * @LastEditTime 2020-1-11 17:41:17
 */
@RestController
@RequestMapping("/api/Database")
public class DatabaseController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(DatabaseController.class);

	/**
	 * 查所有表信息
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping("/All")
	public Object All(HttpServletRequest request) {
		Object wResult = new Object();
		try {
			if (this.CheckCookieEmpty(request)) {
				wResult = GetResult(RetCode.SERVER_CODE_UNLOGIN, "");
				return wResult;
			}

			List<MCSDatabase> wList = DatabaseDAO.getInstance().GetDatabaseList();

			if (wList == null || wList.size() <= 0)
				return GetResult(RetCode.SERVER_CODE_SUC, "", null, null);

			List<MCSTableInfo> wRst = wList.get(0).TableInfoList;

			if (wRst == null || wRst.size() <= 0)
				return GetResult(RetCode.SERVER_CODE_SUC, "", null, null);

			wResult = GetResult(RetCode.SERVER_CODE_SUC, "", wRst, null);
		} catch (Exception ex) {
			logger.error(ex.toString());
			wResult = GetResult(RetCode.SERVER_CODE_ERR, ex.toString(), null, null);
		}
		return wResult;
	}

	/**
	 * 生成JavaDAO
	 */
	@PostMapping("/JavaDAO")
	public Object JavaDAO(HttpServletRequest request, @RequestBody Map<String, Object> wParam,
			HttpServletResponse response) {
		Object wResult = new Object();
		try {
			if (this.CheckCookieEmpty(request)) {
				return GetResult(RetCode.SERVER_CODE_ERR, RetCode.SERVER_CODE_UNROLE);
			}

			if (!wParam.containsKey("TableInfoList") || !wParam.containsKey("Namespace")
					|| !wParam.containsKey("Prefix") || !wParam.containsKey("IsStatusQuery"))
				return GetResult(RetCode.SERVER_CODE_ERR, RetCode.SERVER_RST_ERROR_OUT);

			List<MCSTableInfo> wTableInfoList = CloneTool.CloneArray(wParam.get("TableInfoList"), MCSTableInfo.class);
			String wNamespace = StringUtils.parseString(wParam.get("Namespace"));
			String wPrefix = StringUtils.parseString(wParam.get("Prefix"));
			boolean wIsStatusQuery = StringUtils.parseBoolean(wParam.get("IsStatusQuery"));

			if (wTableInfoList == null || wTableInfoList.size() <= 0)
				return GetResult(RetCode.SERVER_CODE_ERR, RetCode.SERVER_RST_ERROR_OUT);

			String wJavaDaoString = CodeUtils.getInstance().GetJavaDAOClassString(wTableInfoList.get(0), wNamespace,
					wPrefix, wIsStatusQuery);

			wResult = GetResult(RetCode.SERVER_CODE_SUC, "", null, wJavaDaoString);
		} catch (Exception ex) {
			logger.error(ex.getStackTrace().toString());
		}
		return wResult;
	}

	/**
	 * 生成JavaService
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/JavaService")
	public Object JavaService(HttpServletRequest request, @RequestBody Map<String, Object> wParam,
			HttpServletResponse response) {
		Object wResult = new Object();
		try {
			if (this.CheckCookieEmpty(request)) {
				return GetResult(RetCode.SERVER_CODE_ERR, RetCode.SERVER_CODE_UNROLE);
			}

			if (!wParam.containsKey("TableInfoList") || !wParam.containsKey("Namespace")
					|| !wParam.containsKey("Prefix") || !wParam.containsKey("IsStatusQuery"))
				return GetResult(RetCode.SERVER_CODE_ERR, RetCode.SERVER_RST_ERROR_OUT);

			List<MCSTableInfo> wTableInfoList = CloneTool.CloneArray(wParam.get("TableInfoList"), MCSTableInfo.class);
			String wNamespace = StringUtils.parseString(wParam.get("Namespace"));
			String wPrefix = StringUtils.parseString(wParam.get("Prefix"));
			boolean wIsStatusQuery = StringUtils.parseBoolean(wParam.get("IsStatusQuery"));

			if (wTableInfoList == null || wTableInfoList.size() <= 0)
				return GetResult(RetCode.SERVER_CODE_ERR, RetCode.SERVER_RST_ERROR_OUT);

			String wJavaDaoString = CodeUtils.getInstance().GetJavaServiceString(wTableInfoList.get(0), wNamespace,
					wPrefix, wIsStatusQuery);

			wResult = GetResult(RetCode.SERVER_CODE_SUC, "", null, wJavaDaoString);
		} catch (Exception ex) {
			logger.error(ex.getStackTrace().toString());
		}
		return wResult;
	}

	/**
	 * 生成JavaServiceImpl
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/JavaServiceImpl")
	public Object JavaServiceImpl(HttpServletRequest request, @RequestBody Map<String, Object> wParam,
			HttpServletResponse response) {
		Object wResult = new Object();
		try {
			if (this.CheckCookieEmpty(request)) {
				return GetResult(RetCode.SERVER_CODE_ERR, RetCode.SERVER_CODE_UNROLE);
			}

			if (!wParam.containsKey("TableInfoList") || !wParam.containsKey("Namespace")
					|| !wParam.containsKey("Prefix") || !wParam.containsKey("IsStatusQuery"))
				return GetResult(RetCode.SERVER_CODE_ERR, RetCode.SERVER_RST_ERROR_OUT);

			List<MCSTableInfo> wTableInfoList = CloneTool.CloneArray(wParam.get("TableInfoList"), MCSTableInfo.class);
			String wNamespace = StringUtils.parseString(wParam.get("Namespace"));
			String wPrefix = StringUtils.parseString(wParam.get("Prefix"));
			boolean wIsStatusQuery = StringUtils.parseBoolean(wParam.get("IsStatusQuery"));

			if (wTableInfoList == null || wTableInfoList.size() <= 0)
				return GetResult(RetCode.SERVER_CODE_ERR, RetCode.SERVER_RST_ERROR_OUT);

			String wJavaDaoString = CodeUtils.getInstance().GetJavaServiceImplString(wTableInfoList.get(0), wNamespace,
					wPrefix, wIsStatusQuery);

			wResult = GetResult(RetCode.SERVER_CODE_SUC, "", null, wJavaDaoString);
		} catch (Exception ex) {
			logger.error(ex.getStackTrace().toString());
		}
		return wResult;
	}

	/**
	 * 生成JavaController
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/JavaController")
	public Object JavaController(HttpServletRequest request, @RequestBody Map<String, Object> wParam,
			HttpServletResponse response) {
		Object wResult = new Object();
		try {
			if (this.CheckCookieEmpty(request)) {
				return GetResult(RetCode.SERVER_CODE_ERR, RetCode.SERVER_CODE_UNROLE);
			}

			if (!wParam.containsKey("TableInfoList") || !wParam.containsKey("Namespace")
					|| !wParam.containsKey("Prefix") || !wParam.containsKey("IsStatusQuery"))
				return GetResult(RetCode.SERVER_CODE_ERR, RetCode.SERVER_RST_ERROR_OUT);

			List<MCSTableInfo> wTableInfoList = CloneTool.CloneArray(wParam.get("TableInfoList"), MCSTableInfo.class);
			String wNamespace = StringUtils.parseString(wParam.get("Namespace"));
			String wPrefix = StringUtils.parseString(wParam.get("Prefix"));
			boolean wIsStatusQuery = StringUtils.parseBoolean(wParam.get("IsStatusQuery"));

			if (wTableInfoList == null || wTableInfoList.size() <= 0)
				return GetResult(RetCode.SERVER_CODE_ERR, RetCode.SERVER_RST_ERROR_OUT);

			String wJavaDaoString = CodeUtils.getInstance().GetControllerString(wTableInfoList.get(0), wNamespace,
					wPrefix, wIsStatusQuery);

			wResult = GetResult(RetCode.SERVER_CODE_SUC, "", null, wJavaDaoString);
		} catch (Exception ex) {
			logger.error(ex.getStackTrace().toString());
		}
		return wResult;
	}
}
