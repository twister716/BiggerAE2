package cn.dancingsnow.bigger_ae2.item.cell;

import appeng.api.stacks.AEKey;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.math.BigInteger;
import java.util.Objects;

@Getter
public class DigitalSingularityStorage {
    public static final Codec<DigitalSingularityStorage> CODEC =
            RecordCodecBuilder.create(instance -> instance
                    .group(
                            AEKey.CODEC.fieldOf("key").forGetter(DigitalSingularityStorage::getStoredItem),
                            Codec.STRING
                                    .flatXmap(
                                            it -> {
                                                try {
                                                    return DataResult.success(new BigInteger(it));
                                                } catch (NumberFormatException e) {
                                                    return DataResult.success(BigInteger.ZERO);
                                                }
                                            },
                                            it -> DataResult.success(it.toString()))
                                    .fieldOf("count")
                                    .forGetter(DigitalSingularityStorage::getCount))
                    .apply(instance, DigitalSingularityStorage::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, DigitalSingularityStorage> STREAM_CODEC =
            StreamCodec.ofMember(DigitalSingularityStorage::encode, DigitalSingularityStorage::new);

    @Nullable private final AEKey storedItem;

    private final BigInteger count;

    public DigitalSingularityStorage(@Nullable AEKey storedItem, BigInteger count) {
        this.storedItem = storedItem;
        this.count = count;
    }

    public DigitalSingularityStorage(RegistryFriendlyByteBuf buf) {
        storedItem = AEKey.readOptionalKey(buf);
        String integerStr = buf.readUtf();
        BigInteger c;
        try {
            c = new BigInteger(integerStr);
        } catch (NumberFormatException e) {
            c = BigInteger.ZERO;
        }
        count = c;
    }

    public void encode(RegistryFriendlyByteBuf buf) {
        AEKey.writeOptionalKey(buf, storedItem);
        buf.writeUtf(count.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj instanceof DigitalSingularityStorage storage) {
            return Objects.equals(storage.getStoredItem(), getStoredItem())
                    && Objects.equals(storage.getCount(), getCount());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStoredItem(), getCount());
    }
}
