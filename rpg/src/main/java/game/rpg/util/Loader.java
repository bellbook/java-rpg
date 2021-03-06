package game.rpg.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URL;

public class Loader {

    public static URL getResource(String resource) {
        if (resource == null)
            throw new NullPointerException("resource must not be null");
        if (resource.isEmpty())
            throw new IllegalArgumentException("resource must not be empty");
        if (resource.trim().isEmpty())
            throw new IllegalArgumentException("resource must not be blank");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (resource.charAt(0) == '/')
            return classLoader.getResource(resource.substring(1));
        else
            return classLoader.getResource(resource);
    }

    public static File getResourceAsFile(String resource) throws FileNotFoundException {
        URL url = getResource(resource);
        if (url == null)
            throw new FileNotFoundException(resource);
        else
            return new File(URI.create(url.toString()));
    }

    public static String getResourceAsString(String resource) throws FileNotFoundException {
        return getResourceAsFile(resource).toString();
    }

}
