package com.javachen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class AppGateway {

//    // 获取ApolloConfig
//    @ApolloConfig
//    private Config appConfig;

    public static void main(String[] args) {
        SpringApplication.run(AppGateway.class, args);
    }

//    // 添加文档来源
//    @Component
//    @Primary
//    class DocumentationConfig implements SwaggerResourcesProvider {
//        @Override
//        public List<SwaggerResource> get() {
//            // 开启监听，配置文件发生改变需要更改
//            appConfig.addChangeListener(new ConfigChangeListener() {
//
//                @Override
//                public void onChange(ConfigChangeEvent changeEvent) {
//                    get();
//                }
//            });
//            return resources();
//        }
//
//        /**
//         * 从阿波罗服务器中获取resources
//         *
//         * @return
//         */
//        private List<SwaggerResource> resources() {
//
//            List resources = new ArrayList<>();
//            // eureka-itmayiedu-order
//            // 网关使用服务别名获取远程服务的SwaggerApi
//            String swaggerDocJson = swaggerDocument();
//            JSONArray jsonArray = JSONArray.parseArray(swaggerDocJson);
//            for (Object object : jsonArray) {
//                JSONObject jsonObject = (JSONObject) object;
//                String name = jsonObject.getString("name");
//                String location = jsonObject.getString("location");
//                String version = jsonObject.getString("version");
//                resources.add(swaggerResource(name, location, version));
//            }
//            return resources;
//        }
//
//        /**
//         * 获取swaggerDocument配置
//         *
//         * @return
//         */
//        private String swaggerDocument() {
//            String property = appConfig.getProperty("javachen.zuul.swaggerDocument", "");
//            return property;
//        }
//
//        private SwaggerResource swaggerResource(String name, String location, String version) {
//            SwaggerResource swaggerResource = new SwaggerResource();
//            swaggerResource.setName(name);
//            swaggerResource.setLocation(location);
//            swaggerResource.setSwaggerVersion(version);
//            return swaggerResource;
//        }
//
//    }

}