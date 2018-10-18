package com.qworldr.mmorpg.reader;

import com.qworldr.mmorpg.convert.DefaultConvert;
import com.qworldr.mmorpg.enu.ReaderType;
import com.qworldr.mmorpg.meta.ResourceMetaData;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class ExcelReader implements Reader {
    public static final String SERVER = "SERVER";
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelReader.class);

    @Override
    public Map read(ResourceMetaData resourceMetaData) throws Exception {
        Resource[] resources = resourceMetaData.getResources();
        Map map = new HashMap<>();
        Arrays.stream(resources).forEach(resource -> {
            XSSFWorkbook wb = null;
            try {
                wb = new XSSFWorkbook(resource.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            XSSFSheet sheet = null;
            XSSFRow row = null;
            XSSFCell cell = null;
            Map<Integer, Field> keys = null;
            String key;
            Class resourceClass = resourceMetaData.getReourceClass();
            DefaultConvert convert = new DefaultConvert();
            Object object;
            Field field;
            Field keyField = null;
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                sheet = wb.getSheetAt(i);
                boolean startFlag = false;
                for (int r = 0; r <= sheet.getLastRowNum(); r++) {
                    row = sheet.getRow(r);
                    if (row == null) {
                        continue;
                    }
                    cell = row.getCell(0);
                    if (cell != null && SERVER.equalsIgnoreCase(cell.getStringCellValue())) {
                        keys = new HashMap<>();
                        for (int k = 1; k <= row.getLastCellNum(); k++) {
                            cell = row.getCell(k);
                            if (cell == null) {
                                continue;
                            }
                            cell.setCellType(CellType.STRING);
                            key = cell.getStringCellValue();
                            if (!StringUtils.isEmpty(key)) {
                                try {
                                    field = resourceClass.getDeclaredField(key);
                                    field.setAccessible(true);
                                    keys.put(k, field);
                                    if (resourceMetaData.getKeyCol().equals(key)) {
                                        keyField = field;
                                    }
                                } catch (NoSuchFieldException e) {
                                    LOGGER.error(String.format("[%s]没有字段[%s]", resourceClass.getName(), key), e);
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                        if (keyField == null) {
                            throw new RuntimeException(String.format("配置文件[%s]缺少主键列[%s]", resourceMetaData.getPath(), resourceMetaData.getKeyCol()));
                        }
                        startFlag = true;
                        continue;
                    }
                    //开始读取数据
                    if (startFlag && keys != null && keys.size() > 0) {
                        try {
                            object = resourceClass.newInstance();
                            for (Map.Entry<Integer, Field> integerFieldEntry : keys.entrySet()) {
                                cell = row.getCell(integerFieldEntry.getKey());
                                if (cell == null) {
                                    continue;
                                }
                                cell.setCellType(CellType.STRING);
                                key = cell.getStringCellValue();
                                field = integerFieldEntry.getValue();
                                field.set(object, convert.covert(key, field.getType()));
                            }
                            Object key1 = keyField.get(object);
                            if (key1 == null) {
                                throw new FileFormatException(String.format("%s文件第%d行缺少主键字段%s", resourceMetaData.getPath(), r + 1, resourceMetaData.getKeyCol()));
                            }
                            map.put(key1, object);
                        } catch (InstantiationException e) {
                            throw new RuntimeException(e);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        } catch (FileFormatException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }
            }
        });
        return map;
    }

    @Override
    public ReaderType getReaderType() {
        return ReaderType.EXCEL;
    }
}
