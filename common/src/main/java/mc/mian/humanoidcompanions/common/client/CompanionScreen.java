package mc.mian.humanoidcompanions.common.client;

import commonnetwork.api.Network;
import mc.mian.humanoidcompanions.common.container.CompanionContainer;
import mc.mian.humanoidcompanions.common.entity.custom.AbstractHumanCompanionEntity;
import com.mojang.blaze3d.systems.RenderSystem;
import mc.mian.humanoidcompanions.common.entity.custom.Arbalist;
import mc.mian.humanoidcompanions.common.entity.custom.Archer;
import mc.mian.humanoidcompanions.common.entity.custom.Knight;
import mc.mian.humanoidcompanions.common.network.custom.*;
import mc.mian.humanoidcompanions.common.util.HCUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;


public class CompanionScreen extends AbstractContainerScreen<CompanionContainer> implements MenuAccess<CompanionContainer> {
    private static final ResourceLocation CONTAINER_BACKGROUND = HCUtil.modLoc(
        "textures/inventory.png");
    private static final WidgetSprites ALERT_SPRITES = new WidgetSprites(
            HCUtil.modLoc( "widget/alert/enabled"),
            HCUtil.modLoc( "widget/alert/disabled"),
            HCUtil.modLoc( "widget/alert/enabled_focused"),
            HCUtil.modLoc( "widget/alert/disabled_focused")
    );

    private static final WidgetSprites HUNTING_SPRITES = new WidgetSprites(
            HCUtil.modLoc( "widget/hunting/enabled"),
            HCUtil.modLoc( "widget/hunting/disabled"),
            HCUtil.modLoc( "widget/hunting/enabled_focused"),
            HCUtil.modLoc( "widget/hunting/disabled_focused")
    );

    private static final WidgetSprites STATIONARY_SPRITES = new WidgetSprites(
            HCUtil.modLoc( "widget/stationary/enabled"),
            HCUtil.modLoc( "widget/stationary/disabled"),
            HCUtil.modLoc( "widget/stationary/enabled_focused"),
            HCUtil.modLoc( "widget/stationary/disabled_focused")
    );

    private static final WidgetSprites CLEAR_SPRITES = new WidgetSprites(
            HCUtil.modLoc( "widget/clear/enabled"),
            HCUtil.modLoc( "widget/clear/enabled_focused")
    );

    private static final WidgetSprites RELEASE_SPRITES = new WidgetSprites(
            HCUtil.modLoc( "widget/release/enabled"),
            HCUtil.modLoc( "widget/release/enabled_focused")
    );

    private static final WidgetSprites PATROL_SPRITES = new WidgetSprites(
            HCUtil.modLoc( "widget/patrol/enabled"),
            HCUtil.modLoc( "widget/patrol/enabled_focused")
    );

    private static final WidgetSprites GUARD_SPRITES = new WidgetSprites(
            HCUtil.modLoc( "widget/guard/enabled"),
            HCUtil.modLoc( "widget/guard/enabled_focused")
    );

    private static final WidgetSprites FOLLOW_SPRITES = new WidgetSprites(
            HCUtil.modLoc( "widget/follow/enabled"),
            HCUtil.modLoc( "widget/follow/enabled_focused")
    );

    private final int containerRows;
    private final AbstractHumanCompanionEntity companion;
    private ImageButton alertButton;
    private ImageButton huntingButton;
    private ImageButton patrolButton;
    private ImageButton clearButton;
    private ImageButton stationaryButton;
    private ImageButton releaseButton;
    DecimalFormat df = new DecimalFormat("#.#");
    int sidebarx;
    int rowHeight;
    int colwidth;
    int row1;
    int row2;
    int row3;
    int col1;
    int col2;

    public CompanionScreen(CompanionContainer container, Inventory pPlayerInventory,
                           AbstractHumanCompanionEntity companion) {
        super(container, pPlayerInventory, companion.getName());
        this.companion = companion;
        this.containerRows = container.getRowCount();
        this.imageHeight = 114 + this.containerRows * 18;
        this.inventoryLabelY = this.imageHeight - 94;
        this.imageWidth = 226;
        df.setRoundingMode(RoundingMode.CEILING);
        sidebarx = 174;
        rowHeight = 15;
        colwidth = 19;
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, CONTAINER_BACKGROUND);
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        pGuiGraphics.blit(CONTAINER_BACKGROUND, i, j, 0, 0, this.imageWidth, this.containerRows * 18 + 17);
        pGuiGraphics.blit(CONTAINER_BACKGROUND, i, j + this.containerRows * 18 + 17, 0, 126, this.imageWidth, 96);
    }

    @Override
    protected void init() {
        super.init();
        row1 = topPos + 66;
        row2 = row1 + rowHeight;
        row3 = row2 + rowHeight;
        col1 = leftPos + sidebarx + 3;
        col2 = col1 + colwidth;
        this.alertButton = addRenderableWidget(new CompanionButton(this.companion::isAlert, col1, row1, 16,
                12,
                ALERT_SPRITES,
                btn -> {
                    Network.getNetworkHandler().sendToServer(new SetAlertPacket(companion.getId()));
                }));
        this.huntingButton = addRenderableWidget(new CompanionButton(this.companion::isHunting, col2, row1,
                16,
                12,
                HUNTING_SPRITES,
                btn -> {
                    Network.getNetworkHandler().sendToServer(new SetHuntingPacket(companion.getId()));
                }));
        this.patrolButton = addRenderableWidget(new DynamicWidgetCompanionButton(
                () -> true,
                col1,
                row2,
                16,
                12,
                () -> {
                    if (companion.isGuarding())
                        return GUARD_SPRITES;
                    if (companion.isPatrolling())
                        return PATROL_SPRITES;
                    return FOLLOW_SPRITES;
                },
                btn -> {
                    Network.getNetworkHandler().sendToServer(new SetPatrollingPacket(companion.getId()));
                }));
        if (companion instanceof Archer || companion instanceof Arbalist) {
            this.stationaryButton = addRenderableWidget(new CompanionButton(this.companion::isStationary, col2,
                    row2,
                    16,
                    12,
                    STATIONARY_SPRITES,
                    btn -> {
                        Network.getNetworkHandler().sendToServer(new SetStationaryPacket(companion.getId()));
                    }));
        }
        this.clearButton = addRenderableWidget(new ImageButton(leftPos + sidebarx + 5, row3, 31,
                12,
                CLEAR_SPRITES,
                btn -> {
                    Network.getNetworkHandler().sendToServer(new ClearTargetPacket(companion.getId()));
                }));
        this.releaseButton = addRenderableWidget(new ImageButton(leftPos + sidebarx + 3, topPos + 148,
                34,
                12,
                RELEASE_SPRITES,
                btn -> {
                    Network.getNetworkHandler().sendToServer(new ReleasePacket(companion.getId()));
                    this.onClose();
                }));
    }

    @Override
    protected void renderLabels(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        super.renderLabels(pGuiGraphics, pMouseX, pMouseY);
        int classHeight = this.titleLabelY + 14;
        int classLeft = sidebarx + 4;
        MutableComponent classTitle = Component.literal("Class");
        MutableComponent healthTitle = Component.literal("Health");
        MutableComponent health =
                Component.literal(df.format(companion.getHealth()) + "/" + (int) companion.getMaxHealth());

        pGuiGraphics.drawString(this.font, classTitle.withStyle(ChatFormatting.UNDERLINE), sidebarx + 4, this.titleLabelY + 3,
                4210752, false);
        if (companion instanceof Arbalist) {
            pGuiGraphics.drawString(this.font, "Arbalist", classLeft, classHeight, 4210752, false);
        } else if (companion instanceof Archer) {
            pGuiGraphics.drawString(this.font, "Archer", classLeft, classHeight, 4210752, false);
        } else if (companion instanceof Knight) {
            pGuiGraphics.drawString(this.font, "Knight", classLeft, classHeight, 4210752, false);
        } else {
            pGuiGraphics.drawString(this.font, "Axe", classLeft, classHeight, 4210752, false);
        }

        pGuiGraphics.drawString(this.font, healthTitle.withStyle(ChatFormatting.UNDERLINE), sidebarx + 4, this.titleLabelY + 26,
                4210752, false);
        pGuiGraphics.drawString(this.font, health, sidebarx + 4, this.titleLabelY + 37, 4210752, false);

        pGuiGraphics.drawString(this.font, "Level " + companion.getExpLvl(), sidebarx, this.titleLabelY + 49,
                4210752, false);
    }

    @Override
    protected void renderTooltip(GuiGraphics pGuiGraphics, int x, int y) {
        super.renderTooltip(pGuiGraphics, x, y);
        if (this.alertButton.isHovered()) {
            List<Component> tooltips = new ArrayList<>();
            if (this.companion.isAlert()) {
                tooltips.add(Component.literal("Alert mode: On"));
            } else {
                tooltips.add(Component.literal("Alert mode: Off"));
            }
            tooltips.add(Component.literal("Attacks nearby hostile mobs").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));

            pGuiGraphics.renderTooltip(this.font, tooltips, Optional.empty(), x, y);
        }
        if (this.huntingButton.isHovered()) {
            List<Component> tooltips = new ArrayList<>();
            if (this.companion.isHunting()) {
                tooltips.add(Component.literal("Hunting mode: On"));
            } else {
                tooltips.add(Component.literal("Hunting mode: Off"));
            }
            tooltips.add(Component.literal("Attacks nearby mobs for food").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));

            pGuiGraphics.renderTooltip(this.font, tooltips, Optional.empty(), x, y);
        }

        if (this.patrolButton.isHovered()) {
            List<Component> tooltips = new ArrayList<>();
            if (this.companion.isFollowing()) {
                tooltips.add(Component.literal("Follow"));
                tooltips.add(Component.literal("Follows you").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            } else if (this.companion.isPatrolling()) {
                tooltips.add(Component.literal("Patrol"));
                tooltips.add(Component.literal("Patrols a 4 block radius").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            } else {
                tooltips.add(Component.literal("Guard"));
                tooltips.add(Component.literal("Stands at its position ready for action").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
            }

            pGuiGraphics.renderTooltip(this.font, tooltips, Optional.empty(), x, y);
        }

        if (this.clearButton.isHovered()) {
            List<Component> tooltips = new ArrayList<>();
            tooltips.add(Component.literal("Clear target"));
            tooltips.add(Component.literal("Useful if it gets stuck attacking").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));

            pGuiGraphics.renderTooltip(this.font, tooltips, Optional.empty(), x, y);
        }

        if (this.releaseButton.isHovered()) {
            List<Component> tooltips = new ArrayList<>();
            tooltips.add(Component.literal("Release Companion"));
            tooltips.add(Component.literal("Releases companion from your command. It can be tamed again.").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));

            pGuiGraphics.renderTooltip(this.font, tooltips, Optional.empty(), x, y);
        }

        if (companion instanceof Archer || companion instanceof Arbalist) {
            if (this.stationaryButton.isHovered()) {
                List<Component> tooltips = new ArrayList<>();
                if (this.companion.isStationary()) {
                    tooltips.add(Component.literal("Stationery: On"));
                } else {
                    tooltips.add(Component.literal("Stationery: Off"));
                }
                tooltips.add(Component.literal("Companion will not move while attacking in guard mode").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));

                pGuiGraphics.renderTooltip(this.font, tooltips, Optional.empty(), x, y);
            }
        }
    }

    static class DynamicWidgetCompanionButton extends CompanionButton {
        private final Supplier<WidgetSprites> widgetSupplier;

        public DynamicWidgetCompanionButton(Supplier<Boolean> isActiveSupplier, int x, int y, int width, int height, Supplier<WidgetSprites> widgetSupplier, Button.OnPress onPress){
            super(isActiveSupplier, x, y, width, height, null, onPress);

            this.widgetSupplier = widgetSupplier;
        }

        @Override
        public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
            ResourceLocation resourcelocation = widgetSupplier.get().get(this.isActive(), this.isHovered());
            guiGraphics.blitSprite(resourcelocation, this.getX(), this.getY(), this.width, this.height);
        }
    }

    static class CompanionButton extends ImageButton {

        private Supplier<Boolean> isActiveSupplier;

        public CompanionButton(Supplier<Boolean> isActiveSupplier, int x, int y, int width, int height, WidgetSprites sprites, Button.OnPress onPress) {
            super(x, y, width, height, sprites, onPress);
            this.isActiveSupplier = isActiveSupplier;
        }

        @Override
        public boolean isActive() {
            return super.isActive() && this.isActiveSupplier.get();
        }

        @Override
        public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
            ResourceLocation resourcelocation = this.sprites.get(this.isActive(), this.isHovered());
            guiGraphics.blitSprite(resourcelocation, this.getX(), this.getY(), this.width, this.height);
        }
    }
}