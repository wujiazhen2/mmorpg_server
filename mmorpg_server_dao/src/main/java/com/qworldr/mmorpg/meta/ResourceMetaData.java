package com.qworldr.mmorpg.meta;

import com.qworldr.mmorpg.anno.Resource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 *  路径从ClassPath开始
 */
public class ResourceMetaData extends ClassPathResource {

    private String fileName;
    private String suffix;
    private String readerType;
    private String filePath;
    public static ResourceMetaData valueOf(Class resourceClass){

        Resource annotation = (Resource) resourceClass.getAnnotation(Resource.class);
        //文件名为空，默认使用类名作为文件名
        String fileName= StringUtils.isEmpty(annotation.value())?resourceClass.getSimpleName():annotation.value();
        //Reader的类型
        String readerType=annotation.reader();
        String suffix=annotation.suffix();
        String path = annotation.path();
        String location;

        StringBuilder stringBuilder=new StringBuilder();
        if(StringUtils.isEmpty(path)){
            stringBuilder.append(path).append(File.separator);
        }
        if (fileName.lastIndexOf(".")==-1) {
            location = stringBuilder.append(fileName).append(".").append(suffix).toString();
        } else {
            location =stringBuilder.append(fileName).toString();
        }
        return new ResourceMetaData(location,path,fileName,suffix,readerType);
    }
    public ResourceMetaData(String location,String filePath,String fileName,String suffix,String readerType){
        super(location);
        this.filePath=filePath;
        this.fileName=fileName;
        this.suffix=suffix;
        this.readerType=readerType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getReaderType() {
        return readerType;
    }

    public void setReaderType(String readerType) {
        this.readerType = readerType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
