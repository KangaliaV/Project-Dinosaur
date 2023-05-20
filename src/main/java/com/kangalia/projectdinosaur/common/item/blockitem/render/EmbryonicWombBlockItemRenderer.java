package com.kangalia.projectdinosaur.common.item.blockitem.render;

import com.kangalia.projectdinosaur.common.item.blockitem.EmbryonicWombBlockItem;
import com.kangalia.projectdinosaur.common.item.blockitem.model.EmbryonicWombBlockItemModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class EmbryonicWombBlockItemRenderer extends GeoItemRenderer<EmbryonicWombBlockItem> {

    public EmbryonicWombBlockItemRenderer() {
        super(new EmbryonicWombBlockItemModel());
    }
}
