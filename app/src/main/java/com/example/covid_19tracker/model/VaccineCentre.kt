package com.example.covid_19tracker.model

data class VaccineCentre(
	val centers: ArrayList<CentersItem>? = null
)

data class CentersItem(
	val pincode: Int? = null,
	val sessions: List<SessionsItem?>,
	val address: String,
	val fee_type: String,
	val jsonMemberLong: Int? = null,
	val district_name: String,
	val blockName: String? = null,
	val centerId: Int? = null,
	val state_name: String,
	val name: String,
	val from: String,
	val to: String,
	val lat: Int? = null,
	val long:Int? = null
)

data class SessionsItem(
	val date: String? = null,
	val vaccine: String,
	val slots: List<String?>,
	val min_age_limit: Int,
	val sessionId: String? = null,
	val availableCapacity: Int? = null,
	val availableCapacityDose2: Int? = null,
	val availableCapacityDose1: Int? = null
)

