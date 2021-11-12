package com.blend.ibt.springframework.core.convert.converter;

import cn.hutool.core.lang.Assert;

import java.util.Set;

/**
 * 通用的转换接口
 * @author tt
 */
public interface GenericConverter {

    /**
     * return the source and target types that this converter can convert between
     * @return
     */
    Set<ConvertiblePair> getConvertibleTypes();

    /**
     * convert the source object to the targetType
     * @param source
     * @param sourceType
     * @param targetType
     * @return
     */
    Object convert(Object source,Class sourceType,Class targetType);


    /**
     *
     * holder for a source-to-target class pair
     *
     */
    final class ConvertiblePair{
        private final Class<?> sourceType;

        private final Class<?> targetType;

        public ConvertiblePair(Class<?> sourceType, Class<?> targetType) {
            Assert.notNull(sourceType,"source type must not be null");
            Assert.notNull(targetType,"target type must not be null");
            this.sourceType = sourceType;
            this.targetType = targetType;
        }

        public Class<?> getSourceType() {
            return sourceType;
        }

        public Class<?> getTargetType() {
            return targetType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || ConvertiblePair.class != o.getClass()) return false;
            ConvertiblePair that = (ConvertiblePair) o;
            return this.sourceType.equals(that.sourceType) && this.targetType.equals(that.targetType);
        }

        @Override
        public int hashCode() {
            return this.sourceType.hashCode()*31 + this.targetType.hashCode();
        }
    }
}
