package ru.burmistrov.tm.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Session {

    private String userId;

    private String signature;

    private Date dateBegin;

}
