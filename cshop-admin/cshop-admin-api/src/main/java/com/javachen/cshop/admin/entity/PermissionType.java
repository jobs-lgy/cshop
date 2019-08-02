package com.javachen.cshop.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class PermissionType implements Serializable, EnumerationType {

    private static final long serialVersionUID = 1L;

    private static final Map<String, PermissionType> TYPES = new LinkedHashMap<String, PermissionType>();

    public static final PermissionType READ  = new PermissionType("READ", "查询");
    public static final PermissionType CREATE  = new PermissionType("CREATE", "创建");
    public static final PermissionType UPDATE  = new PermissionType("UPDATE", "更新");
    public static final PermissionType DELETE  = new PermissionType("DELETE", "删除");
    public static final PermissionType ALL  = new PermissionType("ALL", "所有");
    public static final PermissionType OTHER  = new PermissionType("OTHER", "其他");

    public static PermissionType getInstance(final String type) {
        return TYPES.get(type);
    }

    private String type;
    private String name;

    private void setType(final String type) {
        this.type = type;
        if (!TYPES.containsKey(type)) {
            TYPES.put(type, this);
        } else {
            throw new RuntimeException("Cannot add the type: (" + type + "). It already exists as a type via " + getInstance(type).getClass().getName());
        }
    }

}
