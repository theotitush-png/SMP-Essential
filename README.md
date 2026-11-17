# SMP Essentials (Paper 1.21.11)

A small Paper plugin that displays the supplied Discord banner as a custom-font glyph in a bossbar title.

## Included
- Java 21 / Paper API 1.21.11 plugin source
- `resource-pack/` with the supplied Discord banner and bitmap font

## Build
Requires Java 21 and Gradle 8. Run `gradle build`. The JAR will be at `build/libs/SMP-Essentials-1.0.0.jar`.

## Install
1. Put the built JAR in the server's `plugins/` folder.
2. Host the contents of `resource-pack/` as a ZIP with `pack.mcmeta` at its root.
3. Optionally set `resource-pack.auto-send: true` and configure the direct HTTPS URL and SHA-1 in `plugins/SMPEssentials/config.yml`.
4. Restart the server. Players need the resource pack to see the custom banner.

## Commands
- `/smpe toggle` — hide/show the banner for yourself.
- `/smpe reload` — reload config (requires `smpe.admin`, default OP).
- `/smpe help` — show help.

The plugin sets bossbar progress to zero to keep the vanilla bar track empty. The resource pack is required; without it, the private-use glyph will appear instead of the image.
