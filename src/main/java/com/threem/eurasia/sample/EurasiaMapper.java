package com.threem.eurasia.sample;

import org.apache.ibatis.annotations.Select;


public interface EurasiaMapper {

	
	public String selectEurasia();
	
	@Select("select host_name from asset where ci_no = 'CI_20120718000015'")
	public String selectAnnotationEurasia();
	
}
