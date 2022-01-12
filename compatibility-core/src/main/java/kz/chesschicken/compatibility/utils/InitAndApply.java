package kz.chesschicken.compatibility.utils;

import org.jetbrains.annotations.NotNull;

/**
 * Another cursed class, helping you to one line initialization and load of the specific instance.
 * @author ChessChicken-KZ
 */
public class InitAndApply {
    /**
     * Just a lighter version of {@link java.util.function.Consumer}.
     * @param <T> Some value.
     */
    public interface ConsumerT<T> {
        void apply(@NotNull T t);
    }

    /**
     * The main method of this class.
     * @param t Instance to be applied.
     * @param c {@link ConsumerT} with applying code.
     * @param <T> Some value.
     * @return Instance to be applied, but applied.
     */
    public static<T> @NotNull T apply(@NotNull T t, @NotNull ConsumerT<T> c) {
        c.apply(t);
        return t;
    }
}
