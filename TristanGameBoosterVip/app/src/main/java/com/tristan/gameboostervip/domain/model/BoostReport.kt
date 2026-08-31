package com.tristan.gameboostervip.domain.model

enum class BoostStage {
    INITIALIZING,
    SCANNING_DEVICE,
    ANALYZING_RAM,
    ANALYZING_CPU,
    CHECKING_THERMAL,
    CHECKING_NETWORK,
    PREPARING_SESSION,
    READY
}

enum class ComponentStatus { OPTIMAL, OK, ATTENTION, NOT_AVAILABLE }

data class BoostComponentResult(
    val label: String,
    val status: ComponentStatus,
    val detail: String
)

data class BoostReport(
    val ramResult: BoostComponentResult,
    val cpuResult: BoostComponentResult,
    val thermalResult: BoostComponentResult,
    val networkResult: BoostComponentResult,
    val deviceResult: BoostComponentResult,
    val performanceScore: Int, // 0..100, derived only from real readings
    val generatedAtMillis: Long
)

data class DiagnosticIssue(
    val category: String,
    val severity: DiagnosticSeverity,
    val description: String,
    val possibleCause: String,
    val suggestedAction: String
)

data class DiagnosticsReport(
    val issues: List<DiagnosticIssue>,
    val generatedAtMillis: Long
) {
    val hasProblems: Boolean get() = issues.isNotEmpty()
}
