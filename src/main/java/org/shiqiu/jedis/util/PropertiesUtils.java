package org.shiqiu.jedis.util;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class PropertiesUtils {
    private static final Logger LOGGER = LogManager.getLogger(PropertiesUtils.class);

    private static final String FILE_SPLIT = "/";

    private static final String FILE_PREFIX = "file:";

    /**
     * 获取配置文件中的指定参数值
     *
     * @param filePath 文件路径 （自动拼接到web根路径--->WEB-INF/classes）示例：config/redis.properties
     * @return String 参数值
     */
    public static Properties getFile(String filePath)
    {
        Properties properties = new Properties();
        InputStream in = null;
        try
        {
            LOGGER.info("PropertiesUtils.getFile start! filePath is :{}", filePath);
            Class<PropertiesUtils> clazz = PropertiesUtils.class;
            URL curUrl = clazz.getResource(FILE_SPLIT);
            LOGGER.info("prefix file path is:{}", curUrl.getPath());
            String prefix;
            prefix = curUrl.getPath();

            String newPath = filePath;
            //读取jar包内置的配置文件
            if (prefix.startsWith(FILE_PREFIX))
            {
                LOGGER.info("read file from jar:{}", newPath);
                in = clazz.getClassLoader().getResourceAsStream(newPath);
            }
            else
            {
                newPath = prefix + filePath;
                LOGGER.info("read file from dir:{}", newPath);
                in = new FileInputStream(newPath);
            }
            properties.load(in);
            LOGGER.info("PropertiesUtils.getFile end! filePath is:{}", newPath);
        }
        catch (Exception e)
        {
            LOGGER.error("getFile fail !", e);
        }
        finally
        {
            IOUtils.closeQuietly(in);
        }
        return properties;
    }

    public static String getProperty(Properties properties, String key)
    {
        return properties.getProperty(key);
    }

    private PropertiesUtils()
    {
    }
}
