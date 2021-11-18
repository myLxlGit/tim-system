package com.gre.lxl.system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFairyAdmin is a Querydsl query type for FairyAdmin
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFairyAdmin extends EntityPathBase<FairyAdmin> {

    private static final long serialVersionUID = 848986293L;

    public static final QFairyAdmin fairyAdmin = new QFairyAdmin("fairyAdmin");

    public final StringPath adminId = createString("adminId");

    public final StringPath adminNickname = createString("adminNickname");

    public final StringPath adminNicpic = createString("adminNicpic");

    public final StringPath adminPassword = createString("adminPassword");

    public final StringPath adminUsername = createString("adminUsername");

    public QFairyAdmin(String variable) {
        super(FairyAdmin.class, forVariable(variable));
    }

    public QFairyAdmin(Path<? extends FairyAdmin> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFairyAdmin(PathMetadata metadata) {
        super(FairyAdmin.class, metadata);
    }

}

