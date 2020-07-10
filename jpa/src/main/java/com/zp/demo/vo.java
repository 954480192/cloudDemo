package com.zp.demo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class  vo{
    @Excel(name = "账号")
    public String zh;
}