package kz.chesschicken.compatibility.utils.cursedjava;

import org.jetbrains.annotations.NotNull;

/**
 * <p>Another cursed way to one line a code of initialization and load of an instance.</p>
 * @author ChessChicken-KZ
 */
public class InitAndApply {
    /**
     * Just a lighter version of {@link java.util.function.Consumer}.
     * @param <A> Some value.
     */
    public interface ConsumerT<A> {
        void apply(@NotNull A instance);
    }

    /**
     * <p>The main method of this class.</p>
     * <p>Great example of usage:</p>
     * <pre>{@code
     * Map<Integer, String> aMap = InitAndApply.apply(new HashMap<>(), instance -> {
     *      instance.put(0, "Sky");
     *      instance.put(5, "Cloud");
     * });
     * }</pre>
     *
     * @param instance Instance to be applied, should not be null.
     * @param consumerT {@link ConsumerT} with applying code.
     * @param <A> Some value.
     * @return Returns applied instance.
     */
    public static<A> @NotNull A apply(@NotNull A instance, @NotNull ConsumerT<A> consumerT) {
        consumerT.apply(instance);
        return instance;
    }
}
