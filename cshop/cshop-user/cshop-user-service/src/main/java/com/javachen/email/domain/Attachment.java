package com.javachen.email.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Attachment implements Serializable {
    private static final long serialVersionUID = 1L;
    private String filename;
    private byte[] data;
    private String mimeType;
}
