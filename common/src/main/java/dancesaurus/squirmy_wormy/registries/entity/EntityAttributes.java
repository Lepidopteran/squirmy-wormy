package dancesaurus.squirmy_wormy.registries.entity;

import dancesaurus.squirmy_wormy.platform.LazyResource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import net.minecraft.world.entity.LivingEntity;

public final class EntityAttributes {

    private static final Map<LazyResource<? extends EntityType<? extends LivingEntity>>, Supplier<AttributeSupplier.Builder>>
            ATTRIBUTES = new HashMap<>();

    public static <T extends LivingEntity> void register(
            LazyResource<EntityType<T>> type,
            Supplier<AttributeSupplier.Builder> attributes
    ) {
        ATTRIBUTES.put(type, attributes);
    }

    public static Map<LazyResource<? extends EntityType<? extends LivingEntity>>, Supplier<AttributeSupplier.Builder>>
    getAll() {
        return ATTRIBUTES;
    }

    private EntityAttributes() {}
}
