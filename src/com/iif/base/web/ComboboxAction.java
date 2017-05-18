package com.iif.base.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iif.base.entity.Options;
import com.iif.base.service.IGenericService;
import com.iif.base.type.OptionType;

/**
 * 
 * @author LiuM
 * 
 */
@Controller
@RequestMapping(value = "combobox")
public class ComboboxAction {

	@Autowired
	private IGenericService genericService;

	@RequestMapping(value = "/select.action")
	@ResponseBody
	public List<? extends Options> combo(
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "kf", required = true) String kf,
			@RequestParam(value = "vf", required = true) String vf,
			String param, String param2, String param3) {

		List<? extends Options> options = null;
		try {
			OptionType ot = OptionType.valueOf(type.toUpperCase());
			options = genericService.findOptions(ot.dc(param, param2, param3),
					kf, vf, ot);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return options;
	}

}
