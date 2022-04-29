//package com.jhj.gateway.config;
//
//import com.jhj.common.enums.SwaggerType;
//import lombok.AllArgsConstructor;
//import org.springframework.cloud.gateway.config.GatewayProperties;
//import org.springframework.cloud.gateway.support.NameUtils;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import springfox.documentation.swagger.web.SwaggerResource;
//import springfox.documentation.swagger.web.SwaggerResourcesProvider;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * Swagger配置
// *
// * @author Jeremy
// * @date 2021/12/13 10:07
// */
//@Configuration
//@Primary
//@AllArgsConstructor
//public class SwaggerResourceConfig implements SwaggerResourcesProvider {
//
//    //private RouteLocator routeLocator;
//
//    private GatewayProperties gatewayProperties;
//
//    /**
//     * 需要生成swagger的服务路由Id
//     */
//    private static final List<String> routeIdList = Arrays.asList("wq-ems", "wq-bi");
//
//    /**
//     * 遍历枚举方式
//     *
//     * @return
//     */
//    @Override
//    @Bean
//    public List<SwaggerResource> get() {
//        List<SwaggerResource> resources = new ArrayList<>();
//        SwaggerType[] swaggerTypes = SwaggerType.values();
//        if (swaggerTypes != null && swaggerTypes.length > 0) {
//            for (int i = 0; i < swaggerTypes.length; i++) {
//                resources.add(buildSwaggerResource(swaggerTypes[i].getName(), swaggerTypes[i].getLocation(), swaggerTypes[i].getSwaggerVersion()));
//            }
//        }
//        return resources;
//    }
//
//    /**
//     * 遍历路由方式
//     *
//     * @return
//     */
//    /*@Override
//    @Bean
//    public List<SwaggerResource> get() {
//        List<SwaggerResource> resources = new ArrayList<>();
//        //过滤出配置文件中定义的路由->过滤出Path Route Predicate->根据路径拼接成api-docs路径->生成SwaggerResource
//        gatewayProperties.getRoutes().stream().filter(routeDefinition -> routeIdList.contains(routeDefinition.getId()))
//                .forEach(route -> {
//                    route.getPredicates().stream()
//                            .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
//                            .forEach(predicateDefinition -> resources.add(buildSwaggerResource(route.getId(),
//                                    predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")
//                                            .replace("**", "v2/api-docs"), "2.0")));
//                });
//        return resources;
//    }*/
//
//    /**
//     * 构建 SwaggerResource
//     *
//     * @param name           名称
//     * @param location       地址
//     * @param swaggerVersion 版本
//     * @return
//     */
//    private SwaggerResource buildSwaggerResource(String name, String location, String swaggerVersion) {
//        SwaggerResource swaggerResource = new SwaggerResource();
//        swaggerResource.setName(name);
//        swaggerResource.setLocation(location);
//        swaggerResource.setSwaggerVersion(swaggerVersion);
//        return swaggerResource;
//    }
//}
