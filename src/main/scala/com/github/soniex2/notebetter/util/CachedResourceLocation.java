package com.github.soniex2.notebetter.util;

import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.Validate;

import javax.annotation.Nonnull;

/**
 * A resource location with interned internals. Used to cut down object allocation.
 *
 * @author soniex2
 */
public final class CachedResourceLocation extends ResourceLocation {
    private static String[] filter(String... data) {
        String resourceDomain = org.apache.commons.lang3.StringUtils.isEmpty(data[0]) ? "minecraft" : data[0].toLowerCase();
        String resourcePath = data[1];
        Validate.notNull(resourcePath);
        return new String[]{resourceDomain.intern(), resourcePath.intern()};
    }

    private final String str;
    private final int hash;

    private CachedResourceLocation(int p_i45928_1_, String... resourcePathIn) {
        super(p_i45928_1_, filter(resourcePathIn));
        str = super.toString().intern();
        hash = super.hashCode();
    }

    public CachedResourceLocation(@Nonnull String p_i1293_1_) {
        this(0, splitObjectName(p_i1293_1_));
    }

    public CachedResourceLocation(@Nonnull String p_i1292_1_, @Nonnull String p_i1292_2_) {
        this(0, p_i1292_1_, p_i1292_2_);
    }

    @Override
    @Nonnull
    public String toString() {
        return str;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof CachedResourceLocation) {
            CachedResourceLocation rl = (CachedResourceLocation) other;
            return this.str.equals(rl.str);
        } else {
            return super.equals(other);
        }
    }

    @Override
    public int hashCode() {
        return hash;
    }
}
