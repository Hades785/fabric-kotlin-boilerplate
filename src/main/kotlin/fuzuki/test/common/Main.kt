package fuzuki.test.common

import fuzuki.test.common.blocks.Blocks
import fuzuki.test.common.items.Items
import net.fabricmc.api.ModInitializer

const val NAMESPACE = "kotlin_test"

class Main : ModInitializer {
    override fun onInitialize() {
        // Load registries hack
        Blocks.TEST
        Items.TEST
    }
}