package uk.asheiou.bungeecompass;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.SmartInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import javax.tools.JavaCompiler;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

public class CompassGUI implements InventoryProvider {
    public static final SmartInventory CompassGui = SmartInventory.builder()
            .id("compassGui")
            .provider(new CompassGUI())
            .size(3,9)
            .title("Server Compass")
            .build();


    @Override
    public void init(Player player, InventoryContents contents) {

        JavaPlugin plugin = JavaPlugin.getProvidingPlugin(BungeeCompass.class);

        ItemStack borderGlass = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta borderGlassMeta = borderGlass.getItemMeta();
        borderGlassMeta.setDisplayName(ChatColor.DARK_GRAY + " ");
        borderGlass.setItemMeta(borderGlassMeta);

        contents.fillBorders(ClickableItem.empty(borderGlass));

        ItemStack skinsItem = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skinsMeta = (SkullMeta) skinsItem.getItemMeta();
        skinsMeta.setOwningPlayer(Bukkit.getOfflinePlayer(UUID.fromString("4f6a3a30-7663-405b-a2b3-8aa4667057c9")));
        skinsItem.setItemMeta(skinsMeta);

        LinkedHashMap<String[], ItemStack> items = new LinkedHashMap<>();
        items.put(new String[]{ChatColor.AQUA+ "Hub", "hub"}, new ItemStack(Material.COMPASS));
        items.put(new String[]{ChatColor.GOLD+"Creative", "creative"}, new ItemStack(Material.PEONY));
        items.put(new String[]{ChatColor.GREEN+"Survival", "survival"}, new ItemStack(Material.DIAMOND_PICKAXE));
        items.put(new String[]{ChatColor.RED+"Factions", "factions"}, new ItemStack(Material.DIAMOND_SWORD));
        items.put(new String[]{ChatColor.YELLOW+"Skyblock", "skyblock"}, new ItemStack(Material.OAK_SAPLING));
        items.put(new String[]{ChatColor.LIGHT_PURPLE+"Minigames", "minigames"}, new ItemStack(Material.WOODEN_HOE));
        items.put(new String[]{ChatColor.DARK_AQUA+"Skin Wardrobe", "skins"}, skinsItem);

        for (String[] i : items.keySet()) {
            ItemStack itemStack = items.get(i);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(i[0]);
            itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            itemStack.setItemMeta(itemMeta);
            contents.add(ClickableItem.of(itemStack, e -> {
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                DataOutputStream out = new DataOutputStream(b);
                try {
                    out.writeUTF("Connect");
                    out.writeUTF(i[1]);
                } catch (Exception ex) { plugin.getLogger().info(ex.getMessage()); }
                player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
            }));
        }

    }

    @Override
    public void update(Player player, InventoryContents inventoryContents) {
        //TODO
    }
}