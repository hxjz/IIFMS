package com.iif.cases.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hxjz.common.core.orm.BaseService;
import com.iif.cases.service.ICasesService;

/**
 * 案件管理Service
 * @author GaoG
 * @date   2017
 * @version V0.1
 */
@Service
@Transactional()
public class CasesServiceImpl extends BaseService implements ICasesService{
}
