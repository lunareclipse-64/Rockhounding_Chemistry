package com.globbypotato.rockhounding_chemistry.machines.recipe.construction;

import net.minecraftforge.fluids.FluidStack;

public class GanPlantRecipe {

	private FluidStack input;
	private FluidStack output;

	public GanPlantRecipe(FluidStack input, FluidStack output){
		this.input = input;
		this.output = output;
	}

	public FluidStack getInput(){
		if(this.input != null) return this.input.copy();
		return null;
	}

	public FluidStack getOutput(){
		if(this.output != null) return this.output.copy();
		return null;
	}

}