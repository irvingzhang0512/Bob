package com.emmairving.bob.web.controller;

import com.emmairving.bob.server.model.RawLocalData;
import com.emmairving.bob.server.model.RawLocalData_Select;
import com.emmairving.bob.server.service.RawLocalDataService;
import com.emmairving.bob.web.model.ShowRawLocalDataListResult;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by irving on 17/2/27.
 */
@RestController
@RequestMapping("data")
public class DataWebController {
    private static Logger logger = LogManager.getLogger(DataWebController.class);

    @Autowired
    private RawLocalDataService rawLocalDataService;

    @RequestMapping("rawlocaldatalist")
    @ApiOperation(
        httpMethod = "POST",
            response = ShowRawLocalDataListResult.class,
            value = "显示rawlocaldata列表"
    )
    public ShowRawLocalDataListResult showRawLocalDataList(
            Integer start,
            Integer length,
            HttpServletRequest req
    ) {
        logger.debug("Use ShowRawLocalDataResult");
        logger.debug("start = "+start+", length = "+length);

        ShowRawLocalDataListResult rst = new ShowRawLocalDataListResult();
        RawLocalData_Select rawLocalData_select = new RawLocalData_Select();
//        rawLocalData_select.setMeter_number((String)(req.getSession().getAttribute("meter_number")));
        rawLocalData_select.setMeter_number("000001094917");

        rst.setRecordsTotal( rawLocalDataService.getCount(rawLocalData_select));
        rst.setRecordsFiltered( rst.getRecordsTotal() );
        logger.debug("recordsTotal = "+rst.getRecordsTotal());


        rawLocalData_select.setPageStart(start);
        rawLocalData_select.setPageSize(length);
        rawLocalData_select.setSort("d_id");
        rst.setData( rawLocalDataService.getList(rawLocalData_select) );
        logger.debug("data.size() = "+rst.getData().size());

        return rst;
    }
}
