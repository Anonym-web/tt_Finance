package com.anonym.parsedome.model;

/**
 * @author 王培忠
 * @date 2020/4/17 15:15
 * @email 805705089@qq.com
 * @Description
 * @Reason ADDREASON
 * @since JDK 1.8
 */
public class ResultSet {

    private String RetCode;

    private String RetVal;

    private Object DataRows;

    public Object getDataRows() {
        return DataRows;
    }

    public void setDataRows(Object dataRows) {
        DataRows = dataRows;
    }

    public String getRetCode() {
        return RetCode;
    }

    public void setRetCode(String retCode) {
        RetCode = retCode;
    }

    public String getRetVal() {
        return RetVal;
    }

    public void setRetVal(String retVal) {
        RetVal = retVal;
    }


}
