package ru.burmistrov.tm.command.system;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.util.ReadManifestUtil;

import java.io.IOException;
import java.util.Map;

@Component
public class PrintManifestCommand extends AbstractCommand {

    @Autowired
    private ReadManifestUtil readManifestUtil;

    @NotNull
    @Override
    public String getName() {
        return "-about";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Print info from manifest";
    }

    @Override
    public void execute() throws IOException {
        for(Map.Entry entry : readManifestUtil.getManifest().getMainAttributes().entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
