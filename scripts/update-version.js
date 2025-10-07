const fs = require('fs');
const path = require('path');
const semver = require('semver');

const gradleFile = path.resolve(__dirname, "../gradle.properties");
const newVersion = process.argv[2];

if (!newVersion) {
    console.error("❌ Version argument is missing");
    process.exit(1);
}

if (!fs.existsSync(gradleFile)) {
    console.error(`❌ gradle.properties not found at ${gradleFile}`);
    process.exit(1);
}

let content = fs.readFileSync(gradleFile, "utf8");

const match = content.match(/^version=(.+)$/m);
if (!match) {
    console.error("❌ gradle.properties does not contain version=");
    process.exit(1);
}

let currentVersion = match[1];
console.log(`ℹ️ Current version in gradle.properties: ${currentVersion}`);

if (semver.lt(newVersion, currentVersion)) {
    console.error(`❌ New version ${newVersion} is lower than current version ${currentVersion}`);
    process.exit(1);
}

content = content.replace(/^version=.+$/m, `version=${newVersion}`);
fs.writeFileSync(gradleFile, content);

console.log(`✅ Updated gradle.properties from ${currentVersion} to ${newVersion}`);
