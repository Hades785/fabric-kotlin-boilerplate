package fuzuki.test.common.blocks

import net.minecraft.block.entity.BlockEntity
import net.minecraft.util.Tickable
import java.util.*

class BlockTestEntity : BlockEntity(Blocks.Entities.TEST), Tickable {
    private val rng = Random()
    override fun tick() {
        if (!cachedState.get(BlockTest.COLOURFIXED)) Objects.requireNonNull(getWorld())
            ?.setBlockState(pos, cachedState.with(BlockTest.COLOUR, rng.nextInt(5)))
    }
}