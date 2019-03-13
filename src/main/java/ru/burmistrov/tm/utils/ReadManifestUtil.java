package ru.burmistrov.tm.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class ReadManifestUtil {

    public Manifest getManifest() {
        Enumeration<URL> resources = null;
        try {
            resources = getClass().getClassLoader().getResources("META-INF/MANIFEST.MF");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (resources != null) {
            while (resources.hasMoreElements()) {
                try {
                    return new Manifest(resources.nextElement().openStream());
                    // check that this is your manifest and do what you need or get the next one
                } catch (IOException E) {
                    // handle
                }
            }
        }
        return null;
    }

}
