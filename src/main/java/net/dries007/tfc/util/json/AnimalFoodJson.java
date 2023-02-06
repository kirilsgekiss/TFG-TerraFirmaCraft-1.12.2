/*
 * Work under Copyright. Licensed under the EUPL.
 * See the project README.md and LICENSE.txt for more information.
 */

package net.dries007.tfc.util.json;

import com.google.gson.*;
import net.dries007.tfc.objects.entity.animal.TFCAnimalFood;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.JsonContext;

import java.lang.reflect.Type;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

public class AnimalFoodJson implements JsonDeserializer<TFCAnimalFood> {
    @Override
    public TFCAnimalFood deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = JsonUtils.getJsonObject(json, "entity");
        TFCAnimalFood animalFood = new TFCAnimalFood(jsonObject.get("eat_rotten").getAsBoolean());
        JsonObject food = JsonUtils.getJsonObject(jsonObject, "foods");
        food.entrySet().forEach(entry ->
        {
            Ingredient ingredient = CraftingHelper.getIngredient(entry.getValue(), new JsonContext(MOD_ID));
            animalFood.addFood(ingredient);
        });
        return animalFood;
    }
}
