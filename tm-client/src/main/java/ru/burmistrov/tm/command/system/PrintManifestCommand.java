package ru.burmistrov.tm.command.system;

import org.jetbrains.annotations.NotNull;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.utils.ReadManifestUtil;

import java.util.Map;

public class PrintManifestCommand extends AbstractCommand {

    public PrintManifestCommand() {
    }

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
    public void execute() {
        @NotNull final ReadManifestUtil readManifestUtil = new ReadManifestUtil();
        for(Map.Entry entry : readManifestUtil.getManifest().getMainAttributes().entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}
