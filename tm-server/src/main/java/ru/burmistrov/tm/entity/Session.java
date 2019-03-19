package ru.burmistrov.tm.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
public class Session extends AbstractEntity {

    private String userId;

    //private String signature;

    //private Date dateBegin = new Date();

}
