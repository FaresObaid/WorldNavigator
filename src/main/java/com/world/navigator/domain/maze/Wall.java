package com.world.navigator.domain.maze;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.world.navigator.domain.cache.ChestKeyOneItem;
import com.world.navigator.domain.cache.ChestNoKeyOneItem;
import com.world.navigator.domain.frontage.MirrorWithOneKey;
import com.world.navigator.domain.frontage.PaintingWithOneKey;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Door.class, name = "door"),
        @JsonSubTypes.Type(value = MirrorWithOneKey.class, name = "mirrorWithOneKey"),
        @JsonSubTypes.Type(value = PaintingWithOneKey.class, name = "paintingWithOneKey"),
        @JsonSubTypes.Type(value = EmptyWall.class, name = "emptyWall"),
        @JsonSubTypes.Type(value = ChestKeyOneItem.class, name = "chestKeyOneItem"),
        @JsonSubTypes.Type(value = ChestNoKeyOneItem.class, name = "chestNoKeyOneItem"),
        @JsonSubTypes.Type(value = Seller.class, name = "seller")
})
public abstract class Wall {


}
