package com.jhj.annotation;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;

import java.io.IOException;


public class ScanUtil {

    final static String ODS_PATTERN = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
            + ClassUtils.convertClassNameToResourcePath("com.jhj.common.model.test")
            + "/**/*.class";

    public static void main(String[] args) {

        getSynchInfoByTableSource();
    }


    public static void getSynchInfoByTableSource() {
        ResourcePatternResolver resourcePatternResolver = initResourcePatternResolver();
        MetadataReaderFactory readerFactory = createMetadataReaderFactory(resourcePatternResolver);
        try {
            Resource[] resources = resourcePatternResolver.getResources(ODS_PATTERN);
            for (Resource resource : resources) {
                if (resource.isReadable()) {
                    MetadataReader reader = readerFactory.getMetadataReader(resource);
                    String className = reader.getClassMetadata().getClassName();// 扫描到的class
                    Class clazz = Class.forName(className);// 判断是否有指定注解
                    if (clazz.isAnnotationPresent(TestAnnotation.class)) {

                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static ResourcePatternResolver initResourcePatternResolver() {
        return new PathMatchingResourcePatternResolver();
    }

    private static MetadataReaderFactory createMetadataReaderFactory(ResourcePatternResolver resourcePatternResolver) {
        return new CachingMetadataReaderFactory(resourcePatternResolver);
    }
}
