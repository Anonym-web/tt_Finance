package project.method;

import project.utility.ExcelUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理`自建组织架构与人员中心映射表`的内容
 * param1： inputPath 文件输入路径
 * param2： sheetIndex 读取excel文件中的sheet页的索引
 * param3： lastCellNum 该sheet页中文件的列数
 */
public class DisposeFiles {
    public static List<String> disposeFiles(String inputPath, int sheetIndex, int lastCellNum) {
        // 存储处理后的excel数据
        List<String> result = new ArrayList<>();
        try {
            // 存储excel读取的结果
            List<StringBuilder> list = ExcelUtil.excelUtil(inputPath, sheetIndex, lastCellNum);
            // 开关，过滤表头（值为1则正式读取数据）
            int OFF = 0;
            for (StringBuilder line : list) {
                if (OFF == 1 && !line.toString().startsWith("|||")) {
                    // 处理第二个字段可按分隔符分割成3条及以上数据的数据
                    if (line.toString().split("\\|", -1)[1].split("/", -1).length > 2) {
                        if (line.toString().split("\\|", -1)[1].split("/", -1)[1].contains("分公司")) {
                            if (line.toString().split("\\|", -1)[1].split("/", -1)[2].contains("分公司")) {
                                if (line.toString().split("\\|", -1)[1].split("/", -1)[2].contains("中移铁通有限公司")) {
                                    result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[2].replace("分公司", "").replace("中移铁通有限公司", "")));
                                } else if (line.toString().split("\\|", -1)[1].split("/", -1)[2].contains("中移建设有限公司")) {
                                    if (line.toString().split("\\|", -1)[1].split("/", -1).length > 3) {
                                        if (line.toString().split("\\|", -1)[1].split("/", -1)[3].contains("项目部")) {
                                            if (line.toString().split("\\|", -1)[1].split("/", -1)[3].contains("直属")) {
                                                result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "").concat("|本级")));
                                            } else {
                                                result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[3].replace("机关项目部", "本级").replace("项目部", "")));
                                            }
                                        } else {
                                            if (line.toString().split("\\|", -1)[1].split("/", -1)[3].contains("工程事业部")) {
                                                result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[3].replace("工程事业部", "")));
                                            } else if (line.toString().split("\\|", -1)[1].split("/", -1)[3].contains("中移建设有限公司")) {
                                                result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[3].replace("中移建设有限公司", "").replace("分公司", "")));
                                            } else {
                                                result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|本级"));
                                            }
                                        }
                                    } else {
                                        result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|本级"));
                                    }
                                } else {
                                    if (line.toString().split("\\|", -1)[1].split("/", -1)[2].contains("分公司")) {
                                        if (line.toString().split("\\|", -1)[1].split("/", -1)[2].replace("分公司", "").length() > 5) {
                                            if (line.toString().split("\\|", -1)[1].split("/", -1).length > 3) {
                                                if (line.toString().split("\\|", -1)[1].split("/", -1)[3].contains("工程项目部")) {
                                                    result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[3].replace("工程项目部", "").replace("分公司", "").replace("改造", "")));
                                                } else {
                                                    if (line.toString().split("\\|", -1)[1].split("/", -1)[3].contains("设备安装项目部")) {
                                                        result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[3].replace("设备安装项目部", "").replace("接入层传输", "")));
                                                    } else if (line.toString().split("\\|", -1)[1].split("/", -1)[2].contains("吴忠分公司") || line.toString().split("\\|", -1)[1].split("/", -1)[2].contains("固原分公司")) {
                                                        result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[2].replace("分公司", "").replace("（吴忠支撑服务中心）", "").replace("（固原支撑服务中心）", "")));
                                                    } else if (line.toString().split("\\|", -1)[1].split("/", -1)[3].contains("项目部")) {
                                                        if (line.toString().split("\\|", -1)[1].split("/", -1)[3].contains("沈阳") || line.toString().split("\\|", -1)[1].split("/", -1)[3].contains("锦州") || line.toString().split("\\|", -1)[1].split("/", -1)[3].contains("大连") || line.toString().split("\\|", -1)[1].split("/", -1)[3].contains("武汉") || line.toString().split("\\|", -1)[1].split("/", -1)[3].contains("青山")) {
                                                            result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[3].replace("项目部", "")));
                                                        } else {
                                                            result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|本级"));
                                                        }
                                                    } else {
                                                        result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|本级"));
                                                    }
                                                }
                                            } else {
                                                if (line.toString().split("\\|", -1)[1].split("/", -1)[2].contains("吴忠分公司") || line.toString().split("\\|", -1)[1].split("/", -1)[2].contains("固原分公司")) {
                                                    result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[2].replace("吴忠分公司（吴忠支撑服务中心）", "吴忠").replace("固原分公司（固原支撑服务中心）", "固原")));
                                                } else {
                                                    result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|本级"));
                                                }
                                            }
                                        } else {
                                            result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[2].replace("分公司", "")));
                                        }
                                    }
                                }
                            } else if (line.toString().split("\\|", -1)[1].split("/", -1)[2].equals("信息和产品开发中心")) {
                                result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|本级"));
                            } else if (line.toString().split("\\|", -1)[1].split("/", -1)[2].equals("阿拉善支撑服务中心退休部门")) {
                                result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[2].replace("支撑服务中心退休部门", "")));
                            } else {
                                if (line.toString().split("\\|", -1)[1].split("/", -1)[2].contains("支撑服务中心")) {
                                    if (line.toString().split("\\|", -1)[1].split("/", -1)[2].contains("城一") || line.toString().split("\\|", -1)[1].split("/", -1)[2].contains("城二") || line.toString().split("\\|", -1)[1].split("/", -1)[2].contains("城三")) {
                                        result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|本级"));
                                    } else {
                                        result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[2].replace("支撑服务中心", "")));
                                    }
                                } else {
                                    if (line.toString().split("\\|", -1)[1].split("/", -1).length > 4) {
                                        if (line.toString().split("\\|", -1)[1].split("/", -1)[4].contains("事业部")) {
                                            if (line.toString().split("\\|", -1)[1].split("/", -1)[4].equals("轨道事业部")) {
                                                result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|本级"));
                                            } else if (line.toString().split("\\|", -1)[1].split("/", -1)[4].equals("智能产品事业部-工程条线")) {
                                                result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|本级"));
                                            } else {
                                                result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[4].replace("事业部-工程条线", "").replace("事业部-市场条线", "").replace("事业部-政企条线", "").replace("事业部-维护条线", "").replace("事业部-仓储物流", "").replace("事业部-信息条线", "").replace("事业部-综合条线", "").replace("事业部-销售条线", "")));
                                            }
                                        } else {
                                            result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|本级"));
                                        }
                                    } else if (line.toString().split("\\|", -1)[1].split("/", -1).length > 3) {
                                        if (line.toString().split("\\|", -1)[1].split("/", -1)[3].contains("事业部")) {
                                            if (line.toString().split("\\|", -1)[1].split("/", -1)[3].contains("销售业务事业部") || line.toString().split("\\|", -1)[1].split("/", -1)[3].contains("智能产品事业部") || line.toString().split("\\|", -1)[1].split("/", -1)[3].contains("维护事业部")) {
                                                result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|本级"));
                                            } else {
                                                result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[3].replace("事业部", "")));
                                            }
                                        } else if (line.toString().split("\\|", -1)[1].split("/", -1)[3].contains("项目部")) {
                                            if (line.toString().split("\\|", -1)[1].split("/", -1)[3].replace("项目部", "").length() == 2) {
                                                if (line.toString().split("\\|", -1)[1].split("/", -1)[3].equals("直属项目部") || line.toString().split("\\|", -1)[1].split("/", -1)[3].equals("高铁项目部")) {
                                                    result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|本级"));
                                                } else {
                                                    result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[3].replace("项目部", "")));
                                                }
                                            } else {
                                                if (line.toString().split("\\|", -1)[1].split("/", -1)[3].equals("防城港项目部")) {
                                                    result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[3].replace("项目部", "")));
                                                } else {
                                                    result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|本级"));
                                                }
                                            }
                                        } else {
                                            result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|本级"));
                                        }
                                    } else {
                                        if (line.toString().split("\\|", -1)[1].split("/", -1)[2].equals("沈阳华铁通信工程监理有限公司")) {
                                            result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[2].replace("沈阳华铁通信工程监理有限公司", "沈阳")));
                                        } else {
                                            result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|本级"));
                                        }
                                    }
                                }
                            }
                        } else if (line.toString().split("\\|", -1)[1].split("/", -1)[1].contains("中移通信技术工程有限公司")) {
                            if (line.toString().split("\\|", -1)[1].split("/", -1)[2].contains("网络维护中心")) {
                                result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("中移通信技术工程有限公司", "工程")).concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[2].replace("网络维护中心", "")));
                            } else {
                                result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("中移通信技术工程有限公司", "工程")).concat("|本级"));
                            }
                        } else if (line.toString().split("\\|", -1)[1].split("/", -1)[1].contains("通信技术中心")) {
                            result.add(line.toString().concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("通信技术中心", "通技")).concat("|本级"));
                        } else {
                            result.add(line.toString().concat("|信产|本级"));
                        }
                        // 处理第二个字段可按分隔符分割成2条数据的数据
                    } else if (line.toString().split("\\|", -1)[1].split("/", -1).length > 1) {
                        if (line.toString().split("\\|", -1)[1].split("/", -1)[1].contains("分公司")) {
                            result.add(line.toString().split("\\|", -1)[0].concat("|").concat(line.toString().split("\\|", -1)[1]).concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("分公司", "")).concat("|本级"));
                        } else if (line.toString().split("\\|", -1)[1].split("/", -1)[1].contains("中移通信技术工程有限公司")) {
                            result.add(line.toString().split("\\|", -1)[0].concat("|").concat(line.toString().split("\\|", -1)[1]).concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("中移通信技术工程有限公司", "工程")).concat("|本级"));
                        } else if (line.toString().split("\\|", -1)[1].split("/", -1)[1].contains("通信技术中心")) {
                            result.add(line.toString().split("\\|", -1)[0].concat("|").concat(line.toString().split("\\|", -1)[1]).concat("|").concat(line.toString().split("\\|", -1)[1].split("/", -1)[1].replace("通信技术中心", "通技")).concat("|本级"));
                        } else {
                            if (line.toString().split("\\|", -1)[1].split("/", -1)[1].equals("信息和产品开发中心")) {
                                result.add(line.toString().concat("|信产|本级"));
                            } else {
                                result.add(line.toString().concat("||本级"));
                            }
                        }
                        // 处理第二个字段可按分隔符分割成1条数据的数据
                    } else {
                        result.add(line.toString().concat("||本级"));
                    }
                }
                // 过滤表头
                if (line.toString().startsWith("org_id|org_full_path")) {
                    OFF = 1;
                }
            }
            // 异常处理
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}