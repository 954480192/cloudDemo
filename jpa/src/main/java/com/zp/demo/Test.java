package com.zp.demo;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.File;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        ImportParams importParams = new ImportParams();
        List<vo> list  = ExcelImportUtil.importExcel(new File("C:\\Users\\unimas\\Desktop\\test.xlsx"),vo.class,importParams);
        System.out.println(JSON.toJSONString(list));
    }
}
