package com.qworldr.mmorpg.meta;

import com.qworldr.mmorpg.anno.Resource;
import com.qworldr.mmorpg.enu.ReaderType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import javax.persistence.Id;
import java.io.File;
import java.lang.reflect.Field;

/**
 * 路径从ClassPath开始
 */
public class ResourceMetaData extends ClassPathResource {

    private String fileName;
    private String suffix;
    private ReaderType readerType;
    private String filePath;
    private String keyCol;
    private Class reourceClass;
    private Class keyClass;

    public static ResourceMetaData valueOf(Class resourceClass, ResourceFormat resourceFormat) {
        Resource annotation = (Resource) resourceClass.getAnnotation(Resource.class);
        //文件名为空，默认使用类名作为文件名
        String fileName = StringUtils.isEmpty(annotation.value()) ? resourceClass.getSimpleName() : annotation.value();
        //Reader的类型
        ReaderType readerType = annotation.reader();
        String suffix = annotation.suffix();
        String path = annotation.path();
        //配置为null，则使用resourceFormat中的默认配置
        if (readerType.equals(ReaderType.NONE)) {
            readerType = resourceFormat.getReaderType();
        }
        if (StringUtils.isEmpty(path)) {
            path = resourceFormat.getPath();
        }
        if (StringUtils.isEmpty(suffix)) {
            suffix = resourceFormat.getSuffix();
        }
        //拼接完整路径
        String location;
        StringBuilder stringBuilder = new StringBuilder();
        if (!StringUtils.isEmpty(path)) {
            stringBuilder.append(path).append(File.separator);
        }
        if (fileName.lastIndexOf(".") == -1) {
            location = stringBuilder.append(fileName).append(".").append(suffix).toString();
        } else {
            location = stringBuilder.append(fileName).toString();
        }
        return new ResourceMetaData(location, path, fileName, suffix, readerType, resourceClass);
    }

    public ResourceMetaData(String location, String filePath, String fileName, String suffix, ReaderType readerType, Class reourceClass) {
        super(location);
        this.filePath = filePath;
        this.fileName = fileName;
        this.suffix = suffix;
        this.readerType = readerType;
        this.reourceClass = reourceClass;

        Field[] declaredFields = reourceClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.getAnnotation(Id.class) != null) {
                this.keyCol = declaredField.getName();
                this.keyClass = declaredField.getType();
                break;
            }
        }
        if (keyClass == null) {
            throw new IllegalArgumentException(String.format("%s资源类没有id字段，请通过@Id标识id字段", reourceClass.getName()));
        }
    }
    public String getKeyCol() {
        return keyCol;
    }


    public Class getReourceClass() {
        return reourceClass;
    }


    public Class getKeyClass() {
        return keyClass;
    }


    public String getFileName() {
        return fileName;
    }

    public String getSuffix() {
        return suffix;
    }

    public ReaderType getReaderType() {
        return readerType;
    }


    public String getFilePath() {
        return filePath;
    }


}
