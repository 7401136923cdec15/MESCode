package com.mes.code.server.serviceimpl.dao.mcs;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mes.code.server.service.po.mcs.MCSClassInfo;
import com.mes.code.server.service.utils.XmlTool;

public class ClassInfoDAO {

	private static Logger logger = LoggerFactory.getLogger(ClassInfoDAO.class);

	private static ClassInfoDAO Instance = null;

	private ClassInfoDAO() {
		super();
	}

	public static ClassInfoDAO getInstance() {
		if (Instance == null)
			Instance = new ClassInfoDAO();
		return Instance;
	}

	public void CreateConfig() {
		try {
			MCSClassInfo wClassInfo = new MCSClassInfo();
			wClassInfo.Namespace = "Shris.WPF.SOP.Service";
			wClassInfo.PrefixName = "SOP";
			List<String> wStringList = new ArrayList<String>();
			wStringList.add("Approval");
			wStringList.add("SOPBindingBeat");
			wStringList.add("SOPProcess");
			wStringList.add("SOPProcessApproval");
			wStringList.add("SOPBindingManage");
			wStringList.add("SOPBindingProject");
			wStringList.add("SOPProject");
			wStringList.add("SOPStep");
			wStringList.add("SOPUnit");
			wStringList.add("SOPStepBindingManage");
			wStringList.add("SOPTask");
			wStringList.add("SOPTaskDevice");
			wClassInfo.ClassNameList = wStringList;
			XmlTool.SaveXml("", wClassInfo);
		} catch (Exception ex) {
			logger.error(ex.toString());
		}
	}
}
