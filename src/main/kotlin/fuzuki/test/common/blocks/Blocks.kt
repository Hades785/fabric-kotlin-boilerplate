package fuzuki.test.common.blocks

import fuzuki.test.common.NAMESPACE
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import java.util.function.Supplier

object Blocks {
    val TEST = register("block_test", BlockTest(FabricBlockSettings.of(Material.LEAVES).hardness(1f)))

    private fun register(id: String, block: Block): Block = Registry.register(Registry.BLOCK, Identifier(NAMESPACE, id), block)

    object Entities {
        val TEST = register("test", ::BlockTestEntity, Blocks.TEST)
        private fun <T: BlockEntity> register(id: String, supplier: Supplier<T>, vararg blocks: Block): BlockEntityType<T> = Registry.register(
            Registry.BLOCK_ENTITY_TYPE, Identifier(NAMESPACE, id), BlockEntityType.Builder.create(supplier, *blocks).build(null)
        )
    }
}