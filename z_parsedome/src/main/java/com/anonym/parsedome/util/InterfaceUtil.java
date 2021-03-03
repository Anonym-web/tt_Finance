package com.anonym.parsedome.util;

import java.util.List;

public class InterfaceUtil {


    public static boolean pathJudgment(String path,int type){
        boolean flag = false;
        /*判断路径合法性,type为一证明接口中传的路径中应该有xlsx,为2证明不应该有xlsx,为3证明判断普通方法中的路径合法性*/
        if(type == 1){
            if(path.contains("xlsx") || path.contains("xls")){
                flag = true;
                return flag;
            }
        }else if(type == 2){
            if(!(path.contains("xlsx") && path.contains("xls"))){
                flag = true;
                return flag;
            }
        }else if(type == 3){
            if(!path.contains("@")){
                flag = true;
                return flag;
            }
        }
        return flag;
    }

    public static String[][] parseArrayListToArray(List<String> arrayListTemp,int dbFieldTotal){
        String[][] arrayList = new String[arrayListTemp.size()][dbFieldTotal];
        for (int i = 0;i < arrayList.length;i++) {
            String[] temp = arrayListTemp.get(i).split(",");
            for(int j = 0;j<temp.length;j++){
                arrayList[i][j] = temp[j];
            }
        }
        return arrayList;
    }
}
