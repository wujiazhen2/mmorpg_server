package com.qworldr.mmorpg;

import com.baidu.bjf.remoting.protobuf.ProtobufIDLGenerator;
import com.qworldr.mmorpg.annotation.Protocal;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author wujiazhen
 */
public class ProtoBufFileGenerator {
    public static final String PATH = "proto";
    private static  final Logger logger = LoggerFactory.getLogger(ProtobufIDLGenerator.class);
    public static final String COM_QWORLDR_MMORPG_CLASS = "classpath:com/qworldr/mmorpg/**/*.class";

    public  static  void main(String[] args) throws IOException, ClassNotFoundException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources(COM_QWORLDR_MMORPG_CLASS);
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
        MetadataReader metadataReader;
        Class<?> cls;
        File file;
        String idl;
        Set<Class<?>> types = new HashSet<>();
        Set<Class<?>> sets = new HashSet<>();
        for (Resource resource : resources) {
            metadataReader = metadataReaderFactory.getMetadataReader(resource);
            if(metadataReader.getAnnotationMetadata().hasAnnotation(Protocal.class.getName())){
                cls = Class.forName(metadataReader.getClassMetadata().getClassName());
                idl = ProtobufIDLGenerator.getIDL(cls,types,sets,true);
                file =new File(PATH +File.separator+cls.getSimpleName()+".proto");
                if(!file.getParentFile().exists()){
                    file.getParentFile().mkdirs();
                }
                boolean newFile = file.createNewFile();
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(idl);
                fileWriter.flush();
                fileWriter.close();
                logger.debug(String.format("生成protobuf文件：%s",file.getAbsoluteFile()));
            }
        }
    }
}
