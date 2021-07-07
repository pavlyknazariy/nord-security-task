package com.destro13.nord_task.model

import com.google.gson.annotations.SerializedName

data class ToDoResponse(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("meta")
	val meta: Meta
)

data class Pagination(

	@field:SerializedName("total")
	val total: Int,

	@field:SerializedName("pages")
	val pages: Int,

	@field:SerializedName("limit")
	val limit: Int,

	@field:SerializedName("page")
	val page: Int
)

data class DataItem(

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("user_id")
	val userId: Int,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("completed")
	val completed: Boolean,

	@field:SerializedName("title")
	val title: String
)

data class Meta(

	@field:SerializedName("pagination")
	val pagination: Pagination
)
