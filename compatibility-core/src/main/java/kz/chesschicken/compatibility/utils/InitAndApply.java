package kz.chesschicken.compatibility.utils;

import org.jetbrains.annotations.NotNull;

public class InitAndApply {
    public interface ConsumerT<T> {
        void apply(@NotNull T t);
    }

    public static <T> @NotNull T apply(@NotNull T t, @NotNull ConsumerT<T> c) {
        c.apply(t);
        return t;
    }
}
