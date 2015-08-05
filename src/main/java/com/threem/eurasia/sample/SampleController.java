/*
 * @(#)SampleController.java $version 2015. 8. 5.
 *
 * Copyright 2015 NHN Ent. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.threem.eurasia.sample;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author Kanghoon Choi
 */
@Controller
public class SampleController {
	
	@RequestMapping("/sample")
	public String sample() {
		return "sample";
	}
	
}
