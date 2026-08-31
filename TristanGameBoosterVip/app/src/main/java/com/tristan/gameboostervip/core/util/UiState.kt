package com.tristan.gameboostervip.core.util

/** Generic loading/data/error state shared by simple screens (Master Prompt §21). */
sealed interface UiState<out T> {
    data object Loading : UiState<Nothing>
    data class Data<T>(val value: T) : UiState<T>
    data class Error(val message: String) : UiState<Nothing>
}
