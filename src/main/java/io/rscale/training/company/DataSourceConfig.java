package io.rscale.training.company;

import java.sql.Connection;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("cloud")
@Configuration
public class DataSourceConfig extends AbstractCloudConfig {

	private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    public DataSourceConfig() {
        logger.info(this.getClass() + " loaded");
    }

    @Bean
    public DataSource dataSource() throws Exception {
        DataSource dataSource = cloud().getSingletonServiceConnector(DataSource.class, null);
        logger.info(dataSource.getClass() + " loaded");
        if ( !isMySQL(dataSource)) {
            logger.error("MySQL required when running cloud profile.");
            throw new UnsatisfiedDependencyException("javax.sql.DataSource", "javax.sql.DataSource", "javax.sql.DataSource", "MySQL required when running cloud profile.");
        }
        return dataSource;
    }

    private boolean isMySQL(DataSource dataSource) throws Exception {
    	String con = dataSource.getConnection().getMetaData().getDriverName();
        logger.info(con + " loaded");
        return con.toLowerCase().contains("mysql");
    }


}
