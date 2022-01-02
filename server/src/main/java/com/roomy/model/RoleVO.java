package com.roomy.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_role")
public class RoleVO {
    @Id
    @Column(name = "role_name")
    private String roleName;

    protected RoleVO() {
    }

    public RoleVO(String roleName) {
        this.roleName = roleName;
    }
}
