package com.midknight.owmix.block.state;
import net.minecraft.util.StringRepresentable;

import javax.annotation.Nonnull;

public enum CrucibleLift implements StringRepresentable {

    NONE("none"),
    LIFTED("lifted")
    ;

    private final String isLifted;

    CrucibleLift(String liftState) {
        this.isLifted = liftState;
    }

    @Override
    public String toString() {
        return this.getSerializedName();
    }

    @Override @Nonnull
    public String getSerializedName() {
        return this.isLifted;
    }


}
