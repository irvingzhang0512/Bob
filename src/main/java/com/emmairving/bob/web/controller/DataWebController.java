package com.emmairving.bob.web.controller;

import com.emmairving.bob.server.model.LocalDataEnergy_Select;
import com.emmairving.bob.server.model.RawLocalData_Select;
import com.emmairving.bob.server.service.LocalDataService;
import com.emmairving.bob.server.service.RawLocalDataService;
import com.emmairving.bob.web.model.*;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by irving on 17/2/27.
 */
@RestController
@RequestMapping("data")
public class DataWebController {
    private static Logger logger = LogManager.getLogger(DataWebController.class);

    @Autowired
    private RawLocalDataService rawLocalDataService;
    @Autowired
    private LocalDataService localDataService;

    /**
     *
     * 获取原始数据列表
     *
     * @param start
     * @param length
     * @param req
     * @return
     */
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
        rawLocalData_select.setMeter_number((String)(req.getSession().getAttribute("meter_number")));
//        rawLocalData_select.setMeter_number("000001094917");

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

//    @RequestMapping("localdatalist")
//    public ShowLocalDataListResult showLocalDataList(
//            Integer start,
//            Integer length,
//            HttpServletRequest req) {
//        logger.debug("Use ShowLocalDataResult");
//        logger.debug("start = "+start+", length = "+length);
//
//    }

    /**
     *
     * 获取昨日24小时，每小时的耗电量，单位为kwh
     *
     * @param request
     * @return
     */
    @RequestMapping("yesterday_energy")
    public List<HightChartsResult> getYesterdayEnergy(
            HttpServletRequest request
    ) {
        List<HightChartsResult> rstlist = new ArrayList<HightChartsResult>();
        HightChartsResult hightChartsResult = new HightChartsResult();
        List<Double> list = new ArrayList<Double>();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        LocalDataEnergy_Select localDataEnergy_select = new LocalDataEnergy_Select();
        localDataEnergy_select.setYear(year);
        localDataEnergy_select.setMonth(month);
        localDataEnergy_select.setDay(day);
        localDataEnergy_select.setUser_id(1);
//        localDataEnergy_select.setUser_id((Integer) request.getSession().getAttribute("id"));

        for( int i = 0 ; i < 24 ; i++ ) {
            localDataEnergy_select.setHour(i);
            Double d = localDataService.getEnergy(localDataEnergy_select);
            if( d == null ) list.add(.0);
            else {
                list.add(new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }
        }


        hightChartsResult.setData(list);
        hightChartsResult.setName("昨日");
        rstlist.add(hightChartsResult);
        return rstlist;
    }


    @RequestMapping("last_month")
    public LastMonthEnergyResult getLastMonthEnergy(
            HttpServletRequest request
    ) {
        LastMonthEnergyResult lastMonthEnergyResult = new LastMonthEnergyResult();

        // xAxis
        XAxis xAxis = new XAxis();
        List<String> categories = new ArrayList<String>();

        // series
        List<HightChartsResult> list = new ArrayList<HightChartsResult>();
        HightChartsResult hightChartsResult = new HightChartsResult();
        List<Double> dlist = new ArrayList<Double>();


        // 当天信息
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -30);

        //
        LocalDataEnergy_Select localDataEnergy_select = new LocalDataEnergy_Select();
        localDataEnergy_select.setUser_id(1);
//        localDataEnergy_select.setUser_id((Integer) request.getSession().getAttribute("id"));

        for( int i = 0 ; i < 30 ; i++, calendar.add(Calendar.DAY_OF_MONTH, 1) ) {
            localDataEnergy_select.setYear( calendar.get(Calendar.YEAR));
            localDataEnergy_select.setMonth( calendar.get(Calendar.MONTH)+1);
            localDataEnergy_select.setDay( calendar.get(Calendar.DAY_OF_MONTH) );
            Double d = localDataService.getEnergy(localDataEnergy_select);

            // 添加耗电量
            if( d == null ) dlist.add(.0);
            else {
                dlist.add(new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }

            // 添加categories
            categories.add((calendar.get(Calendar.MONTH)+1)+"."+calendar.get(Calendar.DAY_OF_MONTH) ) ;
        }


        xAxis.setCategories(categories);
        hightChartsResult.setData(dlist);
        hightChartsResult.setName("前一个月耗电量");
        list.add(hightChartsResult);
        lastMonthEnergyResult.setList(list);
        lastMonthEnergyResult.setxAxis(xAxis);
        return lastMonthEnergyResult;
    }


    @RequestMapping("test")
    public List<HightChartsResult> test() {
        List<HightChartsResult> rstlist = new ArrayList<HightChartsResult>();
        HightChartsResult hightChartsResult = new HightChartsResult();
        List<Double> list = new ArrayList<Double>();
        for(int i = 0 ; i < 24 ; i++) list.add((double)i);
        hightChartsResult.setData(list);
        hightChartsResult.setName("前日");
        rstlist.add(hightChartsResult);
        return rstlist;
    }

}
