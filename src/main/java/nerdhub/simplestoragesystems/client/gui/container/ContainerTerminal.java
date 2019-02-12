package nerdhub.simplestoragesystems.client.gui.container;

import net.minecraft.container.Container;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerTerminal extends Container {

    public final PlayerInventory playerInventory;
    public final World world;

    public ContainerTerminal(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(null, syncId);
        this.playerInventory = playerInventory;
        this.world = playerInventory.player.world;

        //Terminal slots
        for (int row = 0; row < 3; row++) {
            for (int slot = 0; slot < 9; slot++) {
                this.addSlot(new Slot(inventory, (9 * row) + slot, 8 + slot * 18, 26 + row * 18));
            }
        }

        //Vanilla Player Slots
        int i;
        for(i = 0; i < 3; ++i) {
            for(int var4 = 0; var4 < 9; ++var4) {
                this.addSlot(new Slot(playerInventory, var4 + i * 9 + 9, 8 + var4 * 18, 94 + i * 18));
            }
        }

        for(i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 152));
        }
    }

    @Override
    public boolean canUse(PlayerEntity playerEntity) {
        return true;
    }

    @Override
    public ItemStack transferSlot(PlayerEntity playerEntity_1, int int_1) {
        return ItemStack.EMPTY;
    }
}