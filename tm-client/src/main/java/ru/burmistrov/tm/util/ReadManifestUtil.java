package ru.burmistrov.tm.util;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Manifest;

public class ReadManifestUtil {

    public Manifest getManifest() throws IOException {

        Enumeration<URL> resources = null;
        try {
            resources = getClass().getClassLoader().getResources("META-INF/MANIFEST.MF");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (resources != null) {
            while (resources.hasMoreElements()) {
                    return new Manifest(resources.nextElement().openStream());
            }
        }
        return null;
    }

}
