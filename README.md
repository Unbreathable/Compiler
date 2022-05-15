## 🖥️ compiler
Welcome to compiler. A tool to combine multiple files into one. I know it might seem useless at first, but it is actually way more useful than you think it could be. I recently needed a tool/application to reduce the size of my gigantic html and css file. So I came up with compiler. It allows you to combine multiple files into one file and then reload the output file upon saving the file.

## 📰 Usage
1. Download compiler from the latest release.
2. Start the jar either using the command prompt or by just double clicking it.
3. You'll be met with a command prompt. Type 'help' for a list of commands. Type 'methods' for a list of methods.
4. You now need some files. In my case I'll have 'template.html','index.html' and 'section1.html'. I want to combine 'section1.html' and 'template.html' to form one file (index.html). Now go into the template file, go to the line where you want the 'section1.html' file to be at and simply just add '@compiler:inject(section1.html)' to the line.
5. Now lets get onto compiling these files together into one. You open the cmd window and simply type 'compile template.html index.html'. If you haven't messed anything up and open the index.html file, you should now see that the 'section1.html' file replaced the line with '@compiler:inject(section1.html)'.

## 💻 Commands
- help - Help displays a list of all the commands in the application.
- methods - Methods displays a list of all the methods in the application.
- compile file1 file2 - Compiles file1 into file2 using methods given in file1. (See usage)
- watch file1 file2 - Does the same thing as the 'compile' command, but also recompiles every time a file is refreshed.

## 💻 Methods
- inject(file) - Replaces the line where the method is located with the content of the file.
