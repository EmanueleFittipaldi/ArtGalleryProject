package ManagementQuadri.Controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ImageSaver
{
    private String path;

    public ImageSaver(String path)
    {
        if (path.contains("/"))
            this.path = path;
        else
            this.path = path + "/";
    }


    public void save(InputStream in, String name) throws IOException
    {
        File file = new File(path + name);
        Files.copy(in, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public void delete(String name) throws IOException
    {
        File file = new File(this.path + name);
        Files.deleteIfExists(file.toPath());
    }
}
