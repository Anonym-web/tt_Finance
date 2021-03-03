//package com.anonym.parsedome.config;
//
//import com.alibaba.druid.filter.config.ConfigTools;
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.util.DruidPasswordCallback;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
///**
// * @author 魏永杰
// * @Email yongj_wei@163.com
// * @date 2020/3/19 11:09
// * @Description
// * @Reason ADDREASON
// * @since JDK 1.8
// */
//@Configuration
//@MapperScan(basePackages ="com.anonym.parsedome.mapper.shenfen", sqlSessionTemplateRef  = "ShenfenSqlSessionTemplate")
//public class ShenfenConfig {
//
//    @Value("${spring.datasource.shenfen.jdbc-url}")
//    private String dbUrl;
//    @Value("${spring.datasource.shenfen.username}")
//    private String userName;
//    @Value("${spring.datasource.shenfen.password}")
//    private String passWord;
//    @Value("${spring.datasource.shenfen.type}")
//    private String type;
//
//    @Bean(name = "ShenfenDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.shenfen")
//    public DataSource testDataSource() {
//        DruidDataSource dataSource=new DruidDataSource();
//        try {
//            dataSource.setPassword(passWord);
//            dataSource.setUrl(dbUrl);
//            dataSource.setUsername(userName);
//            dataSource.setDbType(type);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return dataSource;
//    }
//    @Bean(name = "ShenfenSqlSessionFactory")
//    public SqlSessionFactory testSqlSessionFactory(@Qualifier("ShenfenDataSource") DataSource dataSource) throws Exception {
//            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//            bean.setDataSource(dataSource);
//            bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/ShenfenMapper/*.xml"));
//            return bean.getObject();
//    }
//    @Bean(name = "ShenfenTransactionManager")
//    public DataSourceTransactionManager testTransactionManager(@Qualifier("ShenfenDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//    @Bean(name = "ShenfenSqlSessionTemplate")
//    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("ShenfenSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//}