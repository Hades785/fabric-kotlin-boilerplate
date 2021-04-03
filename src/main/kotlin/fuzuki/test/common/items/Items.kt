package fuzuki.test.common.items

import fuzuki.test.common.NAMESPACE
import fuzuki.test.common.blocks.Blocks
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object Items {
    val TEST = register("block_test", BlockItem(Blocks.TEST, Item.Settings().group(ItemGroup.MISC)))

    private fun register(id: String, item: Item): Item =
        Registry.register(Registry.ITEM, Identifier(NAMESPACE, id), item)
}