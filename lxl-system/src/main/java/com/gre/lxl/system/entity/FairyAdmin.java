package com.gre.lxl.system.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author lxl
 * @date 2021/8/24 15:58
 */
@Getter
@Setter
@Entity//交给jpa管理实体，并设置映射到数据库的表名
@Table(name = "tab_admin")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@NoArgsConstructor
public class FairyAdmin {

    @Id //主键
    @GeneratedValue(generator = "jpa-uuid")//主键由数据库自动生成（主要是自动增长型）
    @Column(name = "admin_id",nullable = false,length = 32) //name属性不写默认和字段一样
    private String adminId;

    @Column(name = "admin_username",length = 100,unique = true)//设置长度和唯一性
    private String adminUsername;

    @Column(name = "admin_password",length = 100)
    private String adminPassword;

    @Column(name = "admin_nickname",length = 100)
    private String adminNickname;

    @Column(name = "admin_nicpic",length = 255)
    private String adminNicpic;
}
