package cn.edu.cuit.travel_agency_management_system;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.sun.javaws.jnl.ResourcesDesc;

public class CodeGenerator {
    public static void main(String[] args){
        AutoGenerator mpg=new AutoGenerator();
        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java");
        globalConfig.setAuthor("时伟建");
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(false);
        globalConfig.setServiceName("%sService");
        globalConfig.setIdType(IdType.ASSIGN_UUID);
        globalConfig.setDateType(DateType.ONLY_DATE);
        globalConfig.setSwagger2(true);
        mpg.setGlobalConfig(globalConfig);

        //配置数据源
        DataSourceConfig dsc=new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3307/travel_agency_management_system?useSSL=false&allowMultiQueries=true&useUnicode=true&characterEncoding=utf-8");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("1356");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        //包配置
        PackageConfig pc=new PackageConfig();
        pc.setModuleName("travel_agency_management_system");
        pc.setParent("cn.edu.cuit");
        mpg.setPackageInfo(pc);

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude("answer","evaluation","inventory","orders","question","role","setting","trip","user","user_role");
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setControllerMappingHyphenStyle(true);
        strategyConfig.setLogicDeleteFieldName("status");
        mpg.setStrategy(strategyConfig);

        mpg.execute();
    }
}
