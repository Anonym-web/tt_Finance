package com.anonym.parsedome.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author anonym
 * @Email yongj_wei@163.com
 * @date 2020/3/19 11:17
 * @Description
 * @Reason ADDREASON
 * @since JDK 1.8
 */
@Configuration
@MapperScan(basePackages = "com.anonym.parsedome.mapper.ttwhmis.master", sqlSessionTemplateRef  = "ttwhmisSqlSessionTemplate")
public class TTwhmisConfig {

    @Value("${spring.datasource.ttwhmis.jdbc-url}")
    private String dbUrl;
    @Value("${spring.datasource.ttwhmis.username}")
    private String userName;
    @Value("${spring.datasource.ttwhmis.password}")
    private String passWord;
    @Value("${spring.datasource.ttwhmis.type}")
    private String type;

    @Bean(name = "ttwhmisDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.ttwhmis")
    public DataSource testDataSource() {
        DruidDataSource dataSource=new DruidDataSource();
        try {
            dataSource.setPassword(passWord);
            dataSource.setUrl(dbUrl);
            dataSource.setUsername(userName);
            dataSource.setDbType(type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }
    @Bean(name = "ttwhmisSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("ttwhmisDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/ttwhmisMapper/master/*.xml"));
        return bean.getObject();
    }
    @Bean(name = "ttwhmisTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("ttwhmisDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean(name = "ttwhmisSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("ttwhmisSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}