package ru.burmistrov.tm.command.system;

import org.jetbrains.annotations.Nullable;
import ru.burmistrov.tm.command.AbstractCommand;
import ru.burmistrov.tm.utils.ReadManifestUtil;

public class PrintManifestCommand extends AbstractCommand {

    public PrintManifestCommand() {

    }

    @Override
    public String getName() {
        return "-about";
    }

    @Override
    public String getDescription() {
        return "Print info from manifest";
    }

    @Override
    public void execute() {
        @Nullable final ReadManifestUtil readManifestUtil = new ReadManifestUtil();
        System.out.println(readManifestUtil.getManifestInfo());
    }

    @Override
    public boolean isSecure() {
        return false;
    }
}