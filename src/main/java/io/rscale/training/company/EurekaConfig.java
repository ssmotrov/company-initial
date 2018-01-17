package io.rscale.training.company;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@EnableDiscoveryClient
@Profile("cloud")
@Configuration
public class EurekaConfig {

}
