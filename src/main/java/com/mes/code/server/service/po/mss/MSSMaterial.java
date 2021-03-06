package com.mes.code.server.service.po.mss;

import java.io.Serializable;
import java.util.Calendar;

import com.mes.code.server.service.po.mrp.MRPMaterial;

public class MSSMaterial implements Serializable {

	private static final long serialVersionUID = 1L;

	public int ID = 0;
	
 
	public String MaterialNo = "";

	/**
	 * //物料名称
	 */
	public String MaterialName = "";

	public int CYUnitID = 0; // 常用计量单位ID

	public int TypeID = 0; // 自制件|外购件|委外件

	public int StockID = 0; // 默认仓库

	public int LocationID = 0; // 默认仓位

	public int Status = 0;

	public String Author = "";

	public String Auditor = "";

	public Calendar EditTime = Calendar.getInstance();

	public Calendar AuditTime = Calendar.getInstance();

	public String CYUnitText = ""; // 常用计量单位文本
 
	public String Type = "";

	public String StockText = "";

	public String LocationText = "";

	public int BoxTypeID = 0; // 料盒类型ID

	public String BoxType = ""; // 料盒类型

	public float BoxFQTY = 0.0f; // 料盒物料容量

	public int BatchEnable = 0; // 批号启用

	public float SafeFQTY = 0.0f; // 安全库存

	public float ShiftFQTY = 0.0f; // 默认班产

	public int SafeMode = 0; // 工厂保障|供应商保障

	public int BuyDays = 0; // 采购提前时间

	public int BOMID = 0; // 默认生产BOM(实际制造车间单元)

	public String BOMNo = ""; // 默认生产BOM(实际制造车间单元)

	public String WorkShopText = ""; // 默认生产车间(实际制造车间单元)

	public int LineID = 0; // 默认生产车间(产线)

	public String PartName = ""; // 默认生产车间(工序段)

	public int SupplierID = 0; // 默认供应商

	public String SupplierName = ""; // 供应商

	public int LocationBoxs = 0; // 仓位料盒容量

	public int CGUnitID = 0; // 采购计量单位ID

	public int SCUnitID = 0; // 生产计量单位ID

	public int XSUnitID = 0; // 销售计量单位ID

	public int KCUnitID = 0; // 库存计量单位ID

	public String CGUnitText = ""; // 采购计量单位文本

	public String SCUnitText = ""; // 生产计量单位文本

	public String XSUnitText = ""; // 销售计量单位文本

	public String KCUnitText = ""; // 库存计量单位文本

	public int ERPMaterialID = 0;

	public int CheckVersionID = 0;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getMaterialNo() {
		return MaterialNo;
	}

	public void setMaterialNo(String materialNo) {
		MaterialNo = materialNo;
	}

	public String getMaterialName() {
		return MaterialName;
	}

	public void setMaterialName(String materialName) {
		MaterialName = materialName;
	}

	public int getCYUnitID() {
		return CYUnitID;
	}

	public void setCYUnitID(int cYUnitID) {
		CYUnitID = cYUnitID;
	}

	public int getTypeID() {
		return TypeID;
	}

	public void setTypeID(int typeID) {
		TypeID = typeID;
	}

	public int getStockID() {
		return StockID;
	}

	public void setStockID(int stockID) {
		StockID = stockID;
	}

	public int getLocationID() {
		return LocationID;
	}

	public void setLocationID(int locationID) {
		LocationID = locationID;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getAuditor() {
		return Auditor;
	}

	public void setAuditor(String auditor) {
		Auditor = auditor;
	}

	public Calendar getEditTime() {
		return EditTime;
	}

	public void setEditTime(Calendar editTime) {
		EditTime = editTime;
	}

	public Calendar getAuditTime() {
		return AuditTime;
	}

	public void setAuditTime(Calendar auditTime) {
		AuditTime = auditTime;
	}

	public String getCYUnitText() {
		return CYUnitText;
	}

	public void setCYUnitText(String cYUnitText) {
		CYUnitText = cYUnitText;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getStockText() {
		return StockText;
	}

	public void setStockText(String stockText) {
		StockText = stockText;
	}

	public String getLocationText() {
		return LocationText;
	}

	public void setLocationText(String locationText) {
		LocationText = locationText;
	}

	public int getBoxTypeID() {
		return BoxTypeID;
	}

	public void setBoxTypeID(int boxTypeID) {
		BoxTypeID = boxTypeID;
	}

	public String getBoxType() {
		return BoxType;
	}

	public void setBoxType(String boxType) {
		BoxType = boxType;
	}

	public float getBoxFQTY() {
		return BoxFQTY;
	}

	public void setBoxFQTY(float boxFQTY) {
		BoxFQTY = boxFQTY;
	}

	public int getBatchEnable() {
		return BatchEnable;
	}

	public void setBatchEnable(int batchEnable) {
		BatchEnable = batchEnable;
	}

	public float getSafeFQTY() {
		return SafeFQTY;
	}

	public void setSafeFQTY(float safeFQTY) {
		SafeFQTY = safeFQTY;
	}

	public float getShiftFQTY() {
		return ShiftFQTY;
	}

	public void setShiftFQTY(float shiftFQTY) {
		ShiftFQTY = shiftFQTY;
	}

	public int getSafeMode() {
		return SafeMode;
	}

	public void setSafeMode(int safeMode) {
		SafeMode = safeMode;
	}

	public int getBuyDays() {
		return BuyDays;
	}

	public void setBuyDays(int buyDays) {
		BuyDays = buyDays;
	}

	public int getBOMID() {
		return BOMID;
	}

	public void setBOMID(int bOMID) {
		BOMID = bOMID;
	}

	public String getBOMNo() {
		return BOMNo;
	}

	public void setBOMNo(String bOMNo) {
		BOMNo = bOMNo;
	}

	public String getWorkShopText() {
		return WorkShopText;
	}

	public void setWorkShopText(String workShopText) {
		WorkShopText = workShopText;
	}

	public int getLineID() {
		return LineID;
	}

	public void setLineID(int lineID) {
		LineID = lineID;
	}

	public String getPartName() {
		return PartName;
	}

	public void setPartName(String partName) {
		PartName = partName;
	}

	public int getSupplierID() {
		return SupplierID;
	}

	public void setSupplierID(int supplierID) {
		SupplierID = supplierID;
	}

	public String getSupplierName() {
		return SupplierName;
	}

	public void setSupplierName(String supplierName) {
		SupplierName = supplierName;
	}

	public int getLocationBoxs() {
		return LocationBoxs;
	}

	public void setLocationBoxs(int locationBoxs) {
		LocationBoxs = locationBoxs;
	}

	public int getCGUnitID() {
		return CGUnitID;
	}

	public void setCGUnitID(int cGUnitID) {
		CGUnitID = cGUnitID;
	}

	public int getSCUnitID() {
		return SCUnitID;
	}

	public void setSCUnitID(int sCUnitID) {
		SCUnitID = sCUnitID;
	}

	public int getXSUnitID() {
		return XSUnitID;
	}

	public void setXSUnitID(int xSUnitID) {
		XSUnitID = xSUnitID;
	}

	public int getKCUnitID() {
		return KCUnitID;
	}

	public void setKCUnitID(int kCUnitID) {
		KCUnitID = kCUnitID;
	}

	public String getCGUnitText() {
		return CGUnitText;
	}

	public void setCGUnitText(String cGUnitText) {
		CGUnitText = cGUnitText;
	}

	public String getSCUnitText() {
		return SCUnitText;
	}

	public void setSCUnitText(String sCUnitText) {
		SCUnitText = sCUnitText;
	}

	public String getXSUnitText() {
		return XSUnitText;
	}

	public void setXSUnitText(String xSUnitText) {
		XSUnitText = xSUnitText;
	}

	public String getKCUnitText() {
		return KCUnitText;
	}

	public void setKCUnitText(String kCUnitText) {
		KCUnitText = kCUnitText;
	}

	public int getERPMaterialID() {
		return ERPMaterialID;
	}

	public void setERPMaterialID(int eRPMaterialID) {
		ERPMaterialID = eRPMaterialID;
	}

	public int getCheckVersionID() {
		return CheckVersionID;
	}

	public void setCheckVersionID(int checkVersionID) {
		CheckVersionID = checkVersionID;
	}

	public MSSMaterial() {
		this.ShiftFQTY = 1000;
		this.MaterialNo = "";
		this.MaterialName = "";
		this.Author = "";
		this.Auditor = "";
		this.CYUnitText = "";
		this.Type = "";
		this.StockText = "";
		this.LocationText = "";
		this.BoxType = "";
		this.BOMNo = "";
		this.WorkShopText = "";
		this.PartName = "";
		this.SupplierName = "";
		this.CGUnitText = "";
		this.SCUnitText = "";
		this.XSUnitText = "";
		this.KCUnitText = "";

		this.EditTime = Calendar.getInstance();
		this.AuditTime = Calendar.getInstance();
	}

	public MSSMaterial(int wID, String wMaterialNo, String wMaterialName) {
		this.ID = wID;
		this.MaterialNo = wMaterialNo;
		this.MaterialName = wMaterialName;

		this.EditTime = Calendar.getInstance();
		this.AuditTime = Calendar.getInstance();
	}

	public MSSMaterial Clone() {
		MSSMaterial wMaterial = new MSSMaterial();
		wMaterial.ID = this.ID;
		wMaterial.MaterialNo = this.MaterialNo;
		wMaterial.MaterialName = this.MaterialName;
		wMaterial.ShiftFQTY = this.ShiftFQTY;
		wMaterial.CYUnitID = this.CYUnitID;
		wMaterial.TypeID = this.TypeID;
		wMaterial.StockID = this.StockID;
		return wMaterial;
	}

	public MRPMaterial MRPClone() {
		MRPMaterial wMaterial = new MRPMaterial();
		wMaterial.ID = this.ID;
		wMaterial.MaterialID = this.ID;
		wMaterial.MaterialNo = this.MaterialNo;
		wMaterial.MaterialName = this.MaterialName;
		wMaterial.UnitID = this.CYUnitID;
		wMaterial.TypeID = this.TypeID;
		wMaterial.StockID = this.StockID;
		return wMaterial;
	}
}
