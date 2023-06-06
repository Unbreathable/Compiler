## 🖥️ compiler
Welcome to compiler. A tool to combine multiple files into one. I know it might seem useless at first, but it is actually way more useful than you think it could be. I recently needed a tool/application to reduce the size of my gigantic html and css file. So I came up with compiler. It allows you to combine multiple files into one file and then reload the output file upon saving the file.

### Note: This project is currently under no development! Any bugs you might encounter are not going to be fixed. This repository will probably be archived sometime soon!

## 📰 Usage
Alright, I'll only explain a few things, but I hope this works. First of all the way compiler works is that it scans for template files in a directory. Template files are files that contain "@output [output_file]" in the first line. In these files you can use any compiler methods. There is an example in the example directory of this repository in case you want to see it in practice.

### Commands
When starting the JAR there will be a command prompt. You can use it to run the following commands:
- **help** Help displays a list of all the commands in the application.
- **methods** Methods displays a list of all the methods in the application.
- **start [directory]** Start scans the specified directory for templates, compiles them and watches for changes.
- **stop** Stop stops all the threads that are currently watching for file changes.

### Methods
Methods can be used to do various actions using a file. They can be used by typing @compiler:<method name>(<arguments>).
- **inject(file)** Replaces the line where the method is located with the content of the file specified.
