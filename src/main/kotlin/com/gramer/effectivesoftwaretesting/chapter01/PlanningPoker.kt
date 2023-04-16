package com.gramer.effectivesoftwaretesting.chapter01

data class Estimate(val developer: String, val estimate: Int)

class PlanningPoker {

    fun identifyExtremes(estimates: List<Estimate>): List<String?> {
        require(estimates.isNotEmpty())
        require(estimates.size > 1)

        var lowestEstimate: Estimate? = null
        var highestEstimate: Estimate? = null

        estimates.forEach { estimate ->
            if (highestEstimate == null || estimate.estimate > highestEstimate!!.estimate) {
                highestEstimate = estimate
            }
            if (lowestEstimate == null || estimate.estimate < lowestEstimate!!.estimate) {
                lowestEstimate = estimate
            }

        }

        if (lowestEstimate == highestEstimate) {
            return emptyList()
        }

        return listOf(lowestEstimate?.developer, highestEstimate?.developer)
    }
}
