package com.coolfish.gmserver;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.coolfish.gmserver.mvc.interceptor.UserAuthIncerceptor;

//@EnableWebMvc //增加该注解之后 WebMvcAutoConfiguration中的默认配置将会失效，用户需要自己配置静态资源的路径
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("com.coolfish.gmserver.mvc") 
@MapperScan("com.coolfish.gmserver.mybatis.mapper")//mybatis mapper路径
public class GmserverApplication extends WebMvcConfigurerAdapter {
	

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//添加拦截器，同时设置拦截条件
		registry.addInterceptor(new UserAuthIncerceptor()).excludePathPatterns("/").excludePathPatterns("/loginRequest");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(GmserverApplication.class, args);
	}
	
    //DataSource配置
	//表示将根据前缀“spring.datasource”从application.properties中匹配相关属性值。
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return new org.apache.tomcat.jdbc.pool.DataSource();
    }
	
    //提供SqlSeesion
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
 
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
    
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
    
    
}
