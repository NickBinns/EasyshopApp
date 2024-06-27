package org.yearup.configurations;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.data.mysql.MySqlProfileDao;
import org.yearup.data.mysql.MySqlUserDao;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig
{
    private BasicDataSource basicDataSource;

    @Bean
    public BasicDataSource dataSource()
    {
        return basicDataSource;
    }

    @Bean
    public UserDao userDao(DataSource dataSource) {
        return new MySqlUserDao(dataSource);
    }

    @Bean
    public ProfileDao profileDao (DataSource dataSource) {
        return new MySqlProfileDao(dataSource);
    }

    @Autowired
    public DatabaseConfig(@Value("${datasource.url}") String url,
                          @Value("${datasource.username}") String username,
                          @Value("${datasource.password}") String password)
    {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

    }

}