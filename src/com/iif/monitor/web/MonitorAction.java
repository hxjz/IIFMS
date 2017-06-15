package com.iif.monitor.web;

import com.hxjz.common.utils.HttpTool;
import com.iif.common.enums.FinanceStateEnum;
import com.iif.common.enums.FinanceTypeEnum;
import com.iif.common.util.InitSelect;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName: MonitorAction
 * @Description: 实时监控
 * @Author: GaoGang
 * @Date: 2017-06-13 23:22
 * @Version: V1.0
 */
@Controller
@RequestMapping("/monitor/*")
public class MonitorAction {
    @RequestMapping("listMonitor.action")
    public String listFinances() {
        String videoUrl="http://vjs.zencdn.net/v/oceans.mp4";
        String videoType=videoUrl.substring(videoUrl.lastIndexOf(".")+1);
        HttpTool.setAttribute("videoUrl",videoUrl);
        HttpTool.setAttribute("videoType",videoType);
        return "jsp/monitor/listMonitors";
    }
}
