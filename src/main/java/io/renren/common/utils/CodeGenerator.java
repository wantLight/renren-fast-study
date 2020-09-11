package io.renren.common.utils;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;


/**
 * @author xyzzg
 * @version 1.0
 * @date 2019-8-5 19:59
 */
// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    public static void main(String[] args) {

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        //外层包名
        String packageName = "szsti.hotel";

        // 全局配置
        GlobalConfig gc = new GlobalConfig();

        //String projectPath = "D:/project"; //windows下生成文件的位置
        String projectPath = System.getProperty("user.dir");
        //String projectPath = "/Users/lixinyu/myspace/project"; //mac下生成文件的位置
        gc.setOutputDir(projectPath + "/src/main/java");

        gc.setFileOverride(true);
        gc.setAuthor("xyzzg"); //生成文件的作者名称
        gc.setOpen(false);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://113.108.119.116:3306/isolate_hotel_system?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("isolate");
        dsc.setPassword("qsR$FlckDC");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName("xyzzg");
        pc.setParent(packageName);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/" +
                         tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

//        cfg.setFileCreate(new IFileCreate() {
//            @Override
//            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
//                // 判断自定义文件夹是否需要创建
//                checkDir("调用默认方法创建的目录，自定义目录用");
//                if (fileType == FileType.MAPPER) {
//                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
//                    return !new File(filePath).exists();
//                }
//                // 允许生成模板文件
//                return true;
//            }
//        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        //控制 不生成 controller  空字符串就行
        mpg.setTemplate(new TemplateConfig().setXml(null).setController(""));

        // 策略配置 数据库表配置，通过该配置，可指定需要生成哪些表或者排除哪些表
        /**
         * |道路交通事故（每月）|accident_traffic_month|view_accident_traffic_month|交警局|
         * |道路交通事故（1-N月）|accident_traffic_cumulative|view_accident_traffic_cumulative|交警局|
         * |火灾事故（每月）|accident_fire_month|view_accident_fire_month|消防|
         * |火灾事故（1-N月）|accident_fire_cumulative|view_accident_fire_cumulative|消防|
         * |生产安全事故|accident_po_detail|view_accident_po_detail|安监局直报系统|
         *
         * EMERGENCY_MANAGEMENT
         */
        StrategyConfig strategy = new StrategyConfig();

        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // "accident_traffic_month","accident_traffic_cumulative","accident_fire_month","accident_fire_cumulative","accident_po_detail",

        // strategy.setInclude("view_accident_traffic_month","view_accident_traffic_cumulative","view_accident_fire_month","view_accident_fire_cumulative","view_accident_po_detail");

        strategy.setInclude("gov_person_log");


        //strategy.setExclude("%");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();

    }

}
