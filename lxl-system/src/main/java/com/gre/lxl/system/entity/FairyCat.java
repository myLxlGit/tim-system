package com.gre.lxl.system.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author lxl
 * @date 2021/8/24 16:09
 */
@Getter
@Setter
@Entity
@Table(name = "tab_cat")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@NoArgsConstructor
public class FairyCat {
    @Id
    @Column(name = "cat_id",length = 32)
    @GeneratedValue(generator = "jpa-uuid")//主键由数据库自动生成（主要是自动增长型）
    private String catId;

    @Column(name = "cat_name", nullable = false)
    private String catName;

    @Column(name = "tab_id", nullable = false)
    private String tabId;
}
