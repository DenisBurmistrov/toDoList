package ru.burmistrov.tm.entity;

import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "app_session")
public class Session extends AbstractEntity implements Cloneable {

    @Nullable
    @Column(name = "user_id")
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private String userId;

    @Nullable
    @Column(name = "signature")
    private String signature;

    @Column(name = "timesTamp")
    private long timesTamp = new Date().getTime();

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
