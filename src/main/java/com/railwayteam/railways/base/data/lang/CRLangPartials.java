package com.railwayteam.railways.base.data.lang;

import com.google.common.base.Supplier;
import com.google.gson.JsonElement;
import com.railwayteam.railways.Railways;
import com.simibubi.create.AllSoundEvents;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.advancement.AllAdvancements;
import com.simibubi.create.foundation.ponder.PonderLocalization;
import com.simibubi.create.foundation.utility.FilesHelper;
import com.simibubi.create.foundation.utility.Lang;

public enum CRLangPartials {
	INTERFACE("UI & Messages"),
//	TOOLTIPS("Item Descriptions"),

	;

	private String display;
	private Supplier<JsonElement> provider;

	private CRLangPartials(String display) {
		this.display = display;
		this.provider = this::fromResource;
	}

	private CRLangPartials(String display, Supplier<JsonElement> customProvider) {
		this.display = display;
		this.provider = customProvider;
	}

	public String getDisplay() {
		return display;
	}

	public JsonElement provide() {
		return provider.get();
	}

	private JsonElement fromResource() {
		String fileName = Lang.asId(name());
		String filepath = "assets/" + Railways.MODID + "/lang/default/" + fileName + ".json";
		JsonElement element = FilesHelper.loadJsonResource(filepath);
		if (element == null)
			throw new IllegalStateException(String.format("Could not find default lang file: %s", filepath));
		return element;
	}

}
