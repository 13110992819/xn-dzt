package com.cdkj.dzt.bo;

import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.SizeData;



public interface ISizeDataBO extends IPaginableBO<SizeData> {


	public boolean isSizeDataExist(String code);


	public String saveSizeData(SizeData data);


	public int removeSizeData(String code);


	public int refreshSizeData(SizeData data);


	public List<SizeData> querySizeDataList(SizeData condition);


	public SizeData getSizeData(String code);


}