package ru.burmistrov.tm.command.system;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.utils.ReadManifestUtil;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Map;

public class PrintManifestCommand extends AbstractCommand {

    @Inject
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
