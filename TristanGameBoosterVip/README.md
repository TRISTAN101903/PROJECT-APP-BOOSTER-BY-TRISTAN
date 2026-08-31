# TRISTAN GAME BOOSTER VIP

Designed & Founded by TRISTAN NGUYEN — Performance • Gaming • Technology

## Status: Foundation module (1 of ~8)

This is the **first delivered chunk** of a multi-part build. It is a real,
self-contained Android project — you can open it in Android Studio right now
and it will launch to a themed placeholder screen — but it does not yet
contain the feature screens (Home dashboard, Boost Engine, Game Center, Aim
Lab, etc.). Those are being built module-by-module in follow-up messages so
each piece can be reviewed rather than delivered as one unreviewable dump.

### What's in this module
- Gradle Kotlin DSL build (version catalog, AGP 8.5.2, Kotlin 2.0.21, Compose)
- `AndroidManifest.xml` with minimal permissions (INTERNET, network state) and
  a `<queries>` block for game detection instead of `QUERY_ALL_PACKAGES`
- Clean Architecture package skeleton: `core / data / domain / presentation / services`
- Domain models: `DevicePerformance`, `GameInfo`, `GameProfile`, `NetworkStats`,
  `BoostReport`, `DiagnosticsReport` — all nullable-by-design per the
  real-data-only requirement (no field defaults to a fake value)
- `DeviceCapabilities` — the capability-detection layer (`isThermalSupported()`,
  etc.) that every later module reads before touching a platform API
- `OperationResult<T>` — a 3-state result type (Success / Failure /
  **Unsupported**) so "not available on this device" is a first-class outcome,
  not an error path
- Material 3 dark theme system with three variants (TRISTAN DARK, AMOLED
  BLACK, CYBER MODE) and a neon cyan/violet/magenta palette
- Adaptive launcher icon (vector, no placeholder PNG)
- `MainActivity` / `TristanApplication` — minimal, so this module builds and
  launches on its own

### What's NOT in this module yet
Navigation graph, Home dashboard, Boost Engine, Game Center, Network Center,
Aim Lab, Device Center, Diagnostics, Settings, Room database, DataStore
wiring, and tests. Coming in the next messages.

## Build instructions
1. Open this folder in Android Studio (Ladybug or newer recommended).
2. Let Gradle sync — it will fetch dependencies from `gradle/libs.versions.toml`.
3. Run on a device/emulator running Android 8.0 (API 26) or newer.
4. Debug build: `./gradlew assembleDebug`. Release build:
   `./gradlew assembleRelease` (requires a signing config, not yet added).

## Important — read before assuming this is "verified"
**I have not run Gradle or compiled this project.** I don't have Android SDK
build tooling available in this environment, so I cannot claim a verified
green build (per the project's own rule against claiming compilation success
without the ability to run it). I've written the code to compile cleanly
against the pinned library versions above and reviewed it by eye, but the
first real compile needs to happen in your Android Studio / CI. If Gradle
sync flags a version mismatch, the version catalog (`gradle/libs.versions.toml`)
is the one file to adjust.
