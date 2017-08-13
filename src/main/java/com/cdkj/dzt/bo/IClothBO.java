package com.cdkj.dzt.bo;

import java.util.List;

import com.cdkj.dzt.bo.base.IPaginableBO;
import com.cdkj.dzt.domain.Cloth;



public interface IClothBO extends IPaginableBO<Cloth> {


	public boolean isClothExist(String code);


	public String saveCloth(Cloth data);


	public int removeCloth(String code);


	public int refreshCloth(Cloth data);


	public List<Cloth> queryClothList(Cloth condition);


	public Cloth getCloth(String code);


}