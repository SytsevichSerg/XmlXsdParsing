
package com.epam.task2.util;

import com.epam.task2.exception.ParsingXMLException;
import java.net.URL;


public class ResourcePathUtil {
     public static String getResourcePath(String resourceName) throws ParsingXMLException {
        final int pathStartPosition = 6;
        ClassLoader loader = ResourcePathUtil.class.getClassLoader();
        URL resource = loader.getResource(resourceName);
        if (resource == null) {
            throw new ParsingXMLException("Resource " + resourceName + " is not found");
        }
        return resource.toString().substring(pathStartPosition);
    }
}
