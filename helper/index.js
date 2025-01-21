const fs = require('fs');
const path = require('path');

/**
 * Recursively get all files from a directory.
 * @param {string} dirPath - The starting directory path.
 * @param {string[]} filesList - Array to collect file paths.
 * @returns {string[]} List of file paths.
 */
function getAllFiles(dirPath, filesList = []) {
    const files = fs.readdirSync(dirPath);

    for (const file of files) {
        const fullPath = path.join(dirPath, file);

        if (fs.statSync(fullPath).isDirectory()) {
            getAllFiles(fullPath, filesList); // Recursive call for directories
        } else {
            filesList.push(fullPath); // Add file path to the list
        }
    }

    return filesList;
}

// Define the root directory
const rootDir = path.resolve('./../sesi-8/case/src/main'); // Change 'your-directory' to your target directory

try {
    // Fetch all file paths
    const allFiles = getAllFiles(rootDir);

    // Prepare to write content to result.txt
    const outputFilePath = path.join(__dirname, 'result.txt');
    const writeStream = fs.createWriteStream(outputFilePath, { flags: 'w', encoding: 'utf8' });

    // Process each file and append its content to result.txt
    allFiles.forEach((filePath) => {
        const content = fs.readFileSync(filePath, 'utf8');
        writeStream.write(`\n\n=== Start of ${filePath} ===\n`);
        writeStream.write(content);
        writeStream.write(`\n=== End of ${filePath} ===\n`);
    });

    writeStream.end();
    console.log(`All file contents have been combined into ${outputFilePath}`);
} catch (error) {
    console.error('An error occurred:', error.message);
}
