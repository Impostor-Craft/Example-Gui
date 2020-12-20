package fr.lockface.examplegui;

import fr.lockface.impostorcraft.compatibility.material.XMaterial;
import fr.lockface.impostorcraft.game.runninggame.RunningGame;
import fr.lockface.impostorcraft.game.sabotage.Sabotage;
import fr.lockface.impostorcraft.gui.sabotagegui.AbstractSabotageGui;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ButtonGui extends AbstractSabotageGui {

    private ItemStack[] itemsArray = null;
    private int clicks = 4;

    public ButtonGui(Sabotage sabotage, Player p, RunningGame game) {
        super(sabotage, p, game);
        initItemsArray();
    }

    private void initItemsArray() {
        // create the item array to be the size of getRowNumber * 9
        itemsArray = new ItemStack[getRowNumber() * 9];

        // we fill the itemsArray with black glass panes
        for (int i = 0; i < itemsArray.length; i++)
            itemsArray[i] = getBlackGlassPane();

        // we put our red wool at the middle of the chest:
        itemsArray[13] = createGuiItem(XMaterial.RED_WOOL, "", new String[0]);
        // we create it with the method createGuiItem, as material we put our red wool an empty name and an empty lore.
    }

    @Override
    public int getRowNumber() {
        return 3;
    }

    @Override
    public ItemStack[] getItemsArray() {
        return itemsArray;
    }

    @Override
    public void onClick(Player player, int slot) {
        // we ignore all clicks that are not on our wool (the wool slot is 13):
        if (slot == 13) {
            // we remove 1 from clicks
            clicks--;

            // we change our itemsArray so he is on the right wool
            switch (clicks) {
                case 3:
                    // the orange wool
                    itemsArray[13] = createGuiItem(XMaterial.ORANGE_WOOL, "", new String[0]);
                    break;
                case 2:
                    // the yellow wool
                    itemsArray[13] = createGuiItem(XMaterial.YELLOW_WOOL, "", new String[0]);
                    break;
                case 1:
                    // the green wool
                    itemsArray[13] = createGuiItem(XMaterial.GREEN_WOOL, "", new String[0]);
                    break;
                case 0:
                    // case 0, it mean that he has done his 4 clicks and the task is finished
                    finish();
                    // we return because we don't need to go further
                    return;
            }

            // we update the inventory for the player so he is able to see the changes to his wool
            // please note that in the 0 case, we return when the task is finished, because we must not update if it is
            // finished (the inventory of the player is closed)
            updateInventoryPlayer(player);
        }
    }
}
