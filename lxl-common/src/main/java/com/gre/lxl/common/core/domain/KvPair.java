package com.gre.lxl.common.core.domain;

import com.gre.lxl.common.util.StringUtils;
import lombok.Getter;

import java.util.Objects;

/**
 * 键值对实体
 * 完全赋值赋值之后属性内容变为只读不允许修改，
 *
 * @author qixlin
 * @date 2021/04/07 22:17
 */
public class KvPair {

    private transient int writeFlag;

    /**
     * 键
     */
    @Getter
    private String key;
    /**
     * 值
     */
    @Getter
    private String value;

    public KvPair(String key, String value) {
        this.key = key;
        this.value = value;
        this.writeFlag = 0b11;
    }

    public KvPair() {
    }

    public boolean readOnly() {
        return this.writeFlag == 0b11;
    }

    public void setKey(String key) {
       if (this.readOnly()) {
           throw new IllegalStateException("只读！");
       }
        this.key = key;
       this.writeFlag |= 0b01;
    }

    public void setValue(String value) {
        if (this.readOnly()) {
            throw new IllegalStateException("只读！");
        }
        this.value = value;
        this.writeFlag |= 0b10;
    }

    public void mute() {
        this.writeFlag = 0b11;
    }

    /**
     * 按照key=value的格式解析为KvPair实例
     * @param source key=value
     * @return KvPair 对象
     */
    public static KvPair valueOf(String source) {
        int equalsIndex = Objects.requireNonNull(source).indexOf("=");
        if (equalsIndex == -1) {
            return new KvPair(source, null);
        }
        String key = source.substring(0, equalsIndex);
        String value = equalsIndex == source.length() - 1 ? "" : source.substring(equalsIndex + 1);

        return new KvPair(key, value);
    }

    @Override
    public int hashCode() {
        return key.hashCode() ^ value.hashCode();
    }

    @Override
    public boolean equals(Object that) {
        if (!(that instanceof KvPair)) {
            return false;
        }
        KvPair o = (KvPair) that;

        return key.equals(o.key) && value.equals(o.value);
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }

    public boolean existEmpty() {
        return StringUtils.isEmpty(key) || StringUtils.isEmpty(value);
    }
}
