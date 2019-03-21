package ru.burmistrov.tm.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

@Data
public class Session extends AbstractEntity implements Cloneable {

    @Nullable
    private String userId;

    @Nullable
    private String signature;

    @NotNull
    private Date timesTemp = new Date();

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
