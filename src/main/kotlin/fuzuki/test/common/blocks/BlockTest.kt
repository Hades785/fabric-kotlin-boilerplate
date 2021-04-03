package fuzuki.test.common.blocks

import net.minecraft.block.Block
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Items
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.IntProperty
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.BlockView
import net.minecraft.world.World

class BlockTest(settings: Settings?) : Block(settings), BlockEntityProvider {
    companion object {
        val COLOURFIXED: BooleanProperty = BooleanProperty.of("colour_fixed")
        val COLOUR: IntProperty = IntProperty.of("colour", 0, 4)
    }

    init {
        defaultState = stateManager.defaultState.with(COLOURFIXED, false).with(COLOUR, 0)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>?) {
        builder?.add(COLOURFIXED)
        builder?.add(COLOUR)
    }

    override fun createBlockEntity(world: BlockView?): BlockEntity = BlockTestEntity()

    override fun onUse(
        state: BlockState?,
        world: World?,
        pos: BlockPos?,
        player: PlayerEntity?,
        hand: Hand?,
        hit: BlockHitResult?
    ): ActionResult {
        if (!world?.isClient!!) {
            if (player?.getStackInHand(hand)?.item?.name?.string.equals(Items.FLINT_AND_STEEL.name.string)) {
                world.setBlockState(pos, state?.with(COLOURFIXED, !state.get(COLOURFIXED)))
                return ActionResult.SUCCESS
            }
        }
        return ActionResult.PASS
    }
}