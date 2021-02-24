package com.srz.project.method;

import com.srz.project.utility.SXMatchupTable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TwiceDisposeSXData {

    /**
     * 对陕西数据做特殊处理
     *
     * @param disposeList 存储处理后的数据
     * @return CopyOnWriteArrayList 将结果数据返回，返回值类型为：CopyOnWriteArrayList
     */
    public static CopyOnWriteArrayList<String> twiceDisposeSXData(List<String> disposeList) {
        // 存储处理后的数据
        CopyOnWriteArrayList<String> disposeData = new CopyOnWriteArrayList<>();
        // 存储`部门档案名称`与`所属地市`的对应关系的数据
        List<String> list = new ArrayList<>();
        SXMatchupTable.matchupTable(list);
        // 将所属地市为`中移建设`的数据的部门档案名称改为其他地市的
        for (String line : disposeList) {
            if (line.split("\\|", -1)[1].equals("中移建设"))
                for (String name : list) {
                    if (line.split("\\|", -1)[2].equals(name.split("\\|", -1)[1])) {
                        line = line.replaceFirst(line.split("\\|", -1)[1], name.split("\\|", -1)[0]);
                        break;
                    }
                }
            // 将部门档案名称为西咸营销服务中心的归为咸阳市
            if (line.split("\\|", -1)[2].equals("西咸营销服务中心"))
                line = line.replaceFirst("西安市", "咸阳市");
            disposeData.add(line);
        }
        return disposeData;
    }
}
