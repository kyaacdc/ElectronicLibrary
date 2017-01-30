package com.el.spring.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyAccessor {

    private static Properties prop;

    public static String getPathForFileUpload() {
        prop = new  Properties();
        try
        {
            try (InputStream in =
                         Files.newInputStream(Paths.get("/home/kya/IdeaProjects" +
                                 "/new/ElectronicLibrary/src/main/resources/path.properties")))
            {
                prop.load(in);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return prop.getProperty("pathForUploads");
    }
}
