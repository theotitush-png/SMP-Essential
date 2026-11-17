# SMP Essentials (Paper 1.21.11)

A small Paper plugin that displays the supplied Discord banner as a custom-font glyph in a bossbar title.

## Included
- Java 21 / Paper API 1.21.11 plugin source
- `resource-pack/` with the Discord banner PNG and bitmap font
- The build embeds `SMP-Essentials-Resource-Pack.zip` inside the plugin JAR
- GitHub Actions also publishes the standalone resource-pack ZIP and SHA-1

## Build
Requires Java 21 and Gradle 8. Run `gradle clean build`.

The plugin JAR is at `build/libs/SMP-Essentials-1.0.0.jar`. It contains the resource-pack ZIP at its root. The standalone ZIP is at `build/generated/resource-pack/SMP-Essentials-Resource-Pack.zip`.

## Install and configure the join prompt
1. Put the built JAR in the server's `plugins/` folder and restart once.
2. Host the standalone `SMP-Essentials-Resource-Pack.zip` at a direct HTTPS download URL. Minecraft clients must download resource packs from a URL; putting the ZIP inside the plugin JAR alone cannot make clients fetch it.
3. Copy the ZIP's SHA-1 hash (the GitHub Actions artifact includes a `.sha1` file) into `plugins/SMPEssentials/config.yml`.
4. Set `resource-pack.auto-send: true`, the direct HTTPS `resource-pack.url`, and the matching `resource-pack.sha1`.
5. Restart the server. Players will receive the required resource-pack prompt when joining.

The bossbar now displays even when the resource-pack URL has not been configured. Without accepting/installing the pack, Minecraft cannot render the custom Discord artwork glyph correctly.

## Commands
- `/smpe toggle` — hide/show the banner for yourself.
- `/smpe reload` — reload config (requires `smpe.admin`, default OP).
- `/smpe help` — show help.

The bossbar progress is set to zero to keep the vanilla bar track empty.
