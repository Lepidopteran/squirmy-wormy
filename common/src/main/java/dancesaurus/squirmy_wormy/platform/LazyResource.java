package dancesaurus.squirmy_wormy.platform;

import dancesaurus.squirmy_wormy.SquirmyWormy;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public record LazyResource<T>(String path, Supplier<T> supplier) {
    public T get() {
        return supplier.get();
    }

    public ResourceLocation identifier() {
        return new ResourceLocation(SquirmyWormy.MOD_ID, path);
    }
}
