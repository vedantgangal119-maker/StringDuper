# StringDuper (Paper 1.21.8)

A simple Paper plugin that recreates the old "string duper" effect by letting cobwebs drop a **custom number of string**.

## Features
- Customizable string drop per cobweb (`drop-amount`).
- Optional requirements: shears / Silk Touch only.
- `/stringduper` admin command to toggle, reload config, or set drop amount.

## How to Build (via GitHub Actions)
1. Push this project to a GitHub repo.
2. Add `.github/workflows/build.yml` with a Maven build workflow.
3. GitHub will compile the plugin automatically on push.
4. Download the `.jar` from the Actions Artifacts.

## Usage
- Drop the built `.jar` into your Paper server's `plugins/` folder.
- Edit `plugins/StringDuper/config.yml` if needed.
- Use `/stringduper` to manage in-game.

## Permissions
- `stringduper.admin` (default: OP).
