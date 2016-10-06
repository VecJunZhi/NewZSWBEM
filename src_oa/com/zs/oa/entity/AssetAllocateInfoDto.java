package com.zs.oa.entity;

public class AssetAllocateInfoDto {
	public String asset_id;
	public String asset_name;
	public String asset_models;
	public String asset_type;
	public String asset_count;
	public String asset_allocate_reason;
	
	public AssetAllocateInfoDto(String asset_id, String asset_name,
			String asset_models, String asset_type, String asset_count,
			String asset_allocate_reason) {
		super();
		this.asset_id = asset_id;
		this.asset_name = asset_name;
		this.asset_models = asset_models;
		this.asset_type = asset_type;
		this.asset_count = asset_count;
		this.asset_allocate_reason = asset_allocate_reason;
	}
	public AssetAllocateInfoDto() {
		super();
	}
	public String getAsset_id() {
		return asset_id;
	}
	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}
	public String getAsset_name() {
		return asset_name;
	}
	public void setAsset_name(String asset_name) {
		this.asset_name = asset_name;
	}
	public String getAsset_type() {
		return asset_type;
	}
	public void setAsset_type(String asset_type) {
		this.asset_type = asset_type;
	}
	public String getAsset_count() {
		return asset_count;
	}
	public void setAsset_count(String asset_count) {
		this.asset_count = asset_count;
	}
	public String getAsset_allocate_reason() {
		return asset_allocate_reason;
	}
	public void setAsset_allocate_reason(String asset_allocate_reason) {
		this.asset_allocate_reason = asset_allocate_reason;
	}
	public String getAsset_models() {
		return asset_models;
	}
	public void setAsset_models(String asset_models) {
		this.asset_models = asset_models;
	}
	

}
