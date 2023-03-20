## 🖥️ compiler
Welcome to compiler. A tool to combine multiple files into one. I know it might seem useless at first, but it is actually way more useful than you think it could be. I recently needed a tool/application to reduce the size of my gigantic html and css file. So I came up with compiler. It allows you to combine multiple files into one file and then reload the output file upon saving the file.

## 📰 Usage
For the usage, I provided an example in the example directory of this repository. This tool is so easy to use that it's not worth explaining here, you'll figure it out by looking at the example. If you don't understand anything, you can also just create an issue!

### Commands
- **help** Help displays a list of all the commands in the application.
- **methods** Methods displays a list of all the methods in the application.
- **start <directory>** Start scans the specified directory for templates, compiles them and watches for changes.
- **stop** Stop stops all the threads that are currently watching for file changes.

### Methods
Methods can be used to do various actions using a file. They can be used by typing @compiler:<method name>(<arguments>).
- **inject(file)** Replaces the line where the method is located with the content of the file specified.
