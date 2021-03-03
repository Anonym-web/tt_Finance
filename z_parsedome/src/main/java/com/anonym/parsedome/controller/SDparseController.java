//package com.anonym.parsedome.controller;
//
//import com.anonym.parsedome.mapper.shenfen.zczx_ys_shandongMapper;
//import com.anonym.parsedome.model.SDDataEnum;
//import com.anonym.parsedome.pojo.zczx_ys_shandong;
//import com.anonym.parsedome.service.ReadSDSupportCenterData;
//import com.anonym.parsedome.util.ExcelUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.annotation.Resource;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.util.List;
//
//@Controller
//public class SDparseController {
//
//    @Resource
//    private zczx_ys_shandongMapper zczx_ys_shandongMapper;
//
//    @RequestMapping("/tr")
//    public void SDparseData() throws Exception {
//        List<String> stringList = ReadSDSupportCenterData.readSDSupportCenterData("G:\\铁通工作\\睿 交接的项目\\省份文件  SupportDataDispose 工程\\山东数据\\山东十一月\\数据",0,14);
//        for (String s : stringList) {
//            System.out.println(s);
//        }
//        int temp = zczx_ys_shandongMapper.insertBatch(stringList);
//        if(temp <= 0){
//            throw new Exception("执行失败");
//        }
//    }
//
//    /*public static void main(String[] args) {
//        SDparseController sDparseController = new SDparseController();
//        try {
//            //解析山东的数据
//            sDparseController.SDparseData();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }*/
//}
