package com.qworldr.mmorpg;

import com.baidu.bjf.remoting.protobuf.ProtobufIDLGenerator;
import com.qworldr.mmorpg.annotation.Protocal;
import com.qworldr.mmorpg.common.anno.Desc;
import com.qworldr.mmorpg.common.protocal.ProtocalId;
import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;
import org.hibernate.annotations.common.reflection.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ReflectionUtils;

import java.io.*;
import java.util.*;

/**
 * @author wujiazhen
 */
public class ProtoBufFileGenerator {
    public static final String PATH = "proto";
    private static  final Logger logger = LoggerFactory.getLogger(ProtobufIDLGenerator.class);
    public static final String COM_QWORLDR_MMORPG_CLASS = "classpath:com/qworldr/mmorpg/**/*.class";
    private static Map<Short,String> descs= new HashMap<>();

    public  static  void main(String[] args) throws IOException, ClassNotFoundException {
        loadDesc();
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources(COM_QWORLDR_MMORPG_CLASS);
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
        MetadataReader metadataReader;
        Class<?> cls;
        File file;
        String idl;
        Set<Class<?>> types = new HashSet<>();
        Set<Class<?>> sets = new HashSet<>();
        Map<Short,String> packets= new HashMap<>();
        for (Resource resource : resources) {
            metadataReader = metadataReaderFactory.getMetadataReader(resource);
            if(metadataReader.getAnnotationMetadata().hasAnnotation(Protocal.class.getName())){
                cls = Class.forName(metadataReader.getClassMetadata().getClassName());
                Protocal annotation = cls.getAnnotation(Protocal.class);
                packets.put(annotation.value(),cls.getSimpleName());
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
        //输出模板
        generateProtoJS(packets);
        return;
    }

    public static void loadDesc() {
        //加载描述
        ReflectionUtils.doWithFields(ProtocalId.class, field -> {
            Desc annotation = field.getAnnotation(Desc.class);
            field.setAccessible(true);
            descs.put((Short) field.get(null),annotation.value());
        });
    }

    public static void generateProtoJS(Map<Short, String> packets) throws IOException {
        Properties p = new Properties();
        p.put("file.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(p);
        Template template = Velocity.getTemplate("vm/protocalManager.vm","UTF-8");
        Context context = new VelocityContext();
        context.put("PACKETS",packets);
        context.put("DESC",descs);
        File fileJS=new File(PATH+File.separator+"protocalManager.js");
        FileOutputStream outStream = new FileOutputStream(fileJS);
        OutputStreamWriter writer =  new OutputStreamWriter(outStream,"UTF-8");
        BufferedWriter sw = new BufferedWriter(writer);
        template.merge(context,sw);//根据模板文件以及输入的contezt生成代码
        sw.flush();
        sw.close();
        outStream.close();
    }
}
