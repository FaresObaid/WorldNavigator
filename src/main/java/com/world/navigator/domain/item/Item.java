package com.world.navigator.domain.item;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "@item"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Flashlight.class, name = "flashlight"),
        @JsonSubTypes.Type(value = Gold.class, name = "gold"),
        @JsonSubTypes.Type(value = Key.class, name = "key")
})
public abstract class Item {

}
