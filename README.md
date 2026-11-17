# SMP Essentials (Paper 1.21.11)

A small Paper plugin that displays the supplied Discord banner as a custom-font glyph in a bossbar title.

## Included
- Java 21 / Paper API 1.21.11 plugin source
- `resource-pack/` with the Discord banner PNG and bitmap font
- The build embeds `SMP-Essentials-Resource-Pack.zip` inside the plugin JAR
- GitHub Actions also publishes the standalone resource-pack ZIP

## Install
1. Put the built plugin JAR in the server's `plugins/` folder and restart.
2. Get `SMP-Essentials-Resource-Pack.zip` from the build's resource-pack artifact (or extract it from the plugin JAR).
3. Each player installs that ZIP in their Minecraft `resourcepacks` folder and enables **SMP Essentials** in Options > Resource Packs.
4. Join the server. The plugin displays the Discord bossbar automatically; it does not send a server resource-pack prompt or require editing the server config.

**Important:** The custom Discord artwork is a font glyph supplied by the resource pack. Players who have not installed and enabled the pack will still see the bossbar, but the custom artwork may appear as a missing/blank glyph. The pack must be installed locally by each player; a server plugin cannot silently enable a pack in a player's Minecraft client.

## Commands
- `/smpe toggle` — hide/show the banner for yourself.
- `/smpe reload` — reload config (requires `smpe.admin`, default OP).
- `/smpe help` — show help.

The bossbar progress is set to zero to keep the vanilla bar track empty.
