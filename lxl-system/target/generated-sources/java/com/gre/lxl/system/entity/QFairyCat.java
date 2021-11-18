package com.gre.lxl.system.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFairyCat is a Querydsl query type for FairyCat
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFairyCat extends EntityPathBase<FairyCat> {

    private static final long serialVersionUID = -624812356L;

    public static final QFairyCat fairyCat = new QFairyCat("fairyCat");

    public final StringPath catId = createString("catId");

    public final StringPath catName = createString("catName");

    public final StringPath tabId = createString("tabId");

    public QFairyCat(String variable) {
        super(FairyCat.class, forVariable(variable));
    }

    public QFairyCat(Path<? extends FairyCat> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFairyCat(PathMetadata metadata) {
        super(FairyCat.class, metadata);
    }

}

