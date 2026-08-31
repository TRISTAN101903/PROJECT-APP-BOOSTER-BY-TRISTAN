package com.tristan.gameboostervip

import android.app.Application

/**
 * App-wide entry point. Intentionally lightweight: heavy singletons (Room DB,
 * DataStore, capability detector) are constructed lazily via
 * [com.tristan.gameboostervip.core.di.AppContainer] on first use rather than
 * eagerly here, so cold start stays fast.
 */
class TristanApplication : Application()
