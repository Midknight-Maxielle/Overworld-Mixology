package com.midknight.juicebar.integration.jei;

import com.midknight.juicebar.Juicebar;
import com.midknight.juicebar.data.recipes.CrucibleRecipe;
import com.midknight.juicebar.registry.JuiceRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.stream.Collectors;

@JeiPlugin
public class JuiceJEI implements IModPlugin {

    protected static IJeiRuntime runtime;

    public static IJeiRuntime getJeiRuntime() {
        return runtime;
    }

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Juicebar.MOD_ID,"jei");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CrucibleRecipeCategory(registration.getJeiHelpers().getGuiHelper()));

    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        ClientWorld world = Minecraft.getInstance().world;
        RecipeManager recipeManager = world.getRecipeManager();

        List<IRecipe<?>> crucibleRecipes = recipeManager.getRecipes().stream()
            .filter(r -> r.getType() == CrucibleRecipe.TYPE).collect(Collectors.toList());

        registration.addRecipes(crucibleRecipes, JuiceRecipes.CRUCIBLE_RECIPE.getId());
    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {
        JuiceJEI.runtime = jeiRuntime;
    }
}