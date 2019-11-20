# Genesis
Periodically testing the effectiveness of the defensive security measures that are in place, is required. Over time, rules or products that used to work, might not function as expected. Creating effective tests is a tedious and time consuming process which also requires in-depth technical knowledge. Even then, most tests are written once and used in all future tests. This might result in the detection of the test, rather than the used technique(s). To combat this, Genesis has been developed. It serves as a framework to create, store, and generate test cases. Additionally, Genesis can serve as an extension of existing platforms such as Cobalt Strike or the Metasploit Framework.

![Genesis homepage](https://github.com/thisislibra/genesis/raw/master/git-images/home.png "The Genesis home page")

Genesis is a framework that is used to repeatedly generate customised yet unique test cases, based on a given snippet. A snippet is a template that contains meta data and code. The meta data is used when searching through all snippets, whereas the code is used to create “benign malware”. All the snippets are mapped to the MITRE ATT&CK framework. The generated test cases can then be executed on the targeted machine, after which the rules of the in-place security measures should provide alerts regarding the test case.

In short, Genesis is focused on a few key points. First off, simplicity is key. Be it the user experience of the end user, or someone who looks into the source code: everything should be self-explanatory.

Secondly, the framework is accessible via a front-end and an API. The front-end works via the API as well, but does not utilise all the API endpoints that are exposed.

Thirdly, obfuscation based on the given snippet's code. A variety of techniques are embedded within Genesis. Out of this pool of techniques, a random subset is taken for each snippet generation, based on the given obfuscation profile. The obfuscation profiles that one can choose from, are `none`, `low`, `medium`, or `high`. Currently, there are not enough obfuscation modules to create a pool, but the current architecture supports at least one method for each obfuscation profile.

Fourthly, customisation. Marking any part of the code between `<<` and `>>` will instruct Genesis to request user input for these fields. The text in between the markings are displayed to the user when the snippet is generated, so be sure to use meaningful tags! These tags are replaced before the obfuscation process begins. As such, a snippet can be generic, whilst a small part of the code changes to influence the actions that are taken. A good example of this is a generic download script. By providing a different URL in each test case, one is sure that the downloader is detected based upon techniques, rather than on the URL that is used.

Fifthly, multiple languages are supported! Currently, three languages are supported: `VBA`, `Javascript`, and `Powershell`. The architecture of Genesis is set-up in such a way, that the addition of another language is easily done.

Lastly, privacy! Genesis does not keep track of anything. There is no usage history within Genesis, nor are there any hidden features. This can be verified within the source code itself, as Genesis is completely open-source!

## Audience and use cases
There are multiple different use cases for Genesis. A SIEM use case design team can utilise it to test a rule that is being constructed. A blue team can utilise it to see if the rules that are in place function correctly. A red team can utilise it to create a payload, as every generation  is unique (although the eventual output is not changed).

## Operating system support
Since Genesis is written in plain Java, the framework itself can run on any Java version. To expose the API, Java 8 EE is used, meaning that one has to use Java 8 EE for the back-end, which runs on any major operating system. The front-end is written in VueJS, which requires NPM to be installed. NPM, and Javascript in general, can run on any major operating system.

## API
As stated before, the API exposes more information that is currently available in the front-end. The API documentation (in the form of Javadoc) can be found [here](https://github.com/thisislibra/genesis/releases). Note that the complete program is documented in here, and only the controller classes contain API endpoints. Please see the `apidocs/controller/package-summary.html` file within the latest release for a summary of all exposed API classes. In each class, a detailed explantion is given for every function that is present.

Currently, one can search through the metadata of snippets, MITRE techniques, and MITRE tactics for any term using the API, whilst the front-end itself cannot.

## Demo
When visiting the home page of the websites, some statistics are given, as can be seen in the image below.

![Genesis homepage](https://github.com/thisislibra/genesis/raw/master/git-images/home.png "The Genesis home page")

To add a snippet, visit the `Add Snippet` page and provide the details as they are shown on the screenshot below. The techniques list contains all MITRE ATT&CK techniques, out of which one or more can be added to the snippet by using the `+` button on the right. The platform can be generic (such as `WINDOWS_ANY` or `ANY`), or rather specific (such as `WINDOWS_7_X64` or `WINDOWS_10_X86`). The language needs to be supported by Genesis.

![Creation of a snippet, part 1](https://github.com/thisislibra/genesis/raw/master/git-images/add-1.png "Add the meta data to the snippet")

The second part of adding a snippet involves the snippet's code, as well as global variables. Note that variables are used as-is within the snippet. This means that `7` is treated as an integer. For a string, one has to add the quotation marks manually. Note that the value of a variable and/or any part of the code can be marked with `<<` and `>>`. Any value between these markings is requested during the generation. A reverse shell's IP would be an example of something to place between the markings. More information about this is available in the next step.

In the coming example, the following Javascript code is used:
```javascript
console.log("<<MessageA>>");
console.log("<<MessageB>>");
```

![Creation of a snippet, part 2](https://github.com/thisislibra/genesis/raw/master/git-images/add-2.png "Add the code to the snippet")

When clicking on the `Generate snippet` menu item, a list of all loaded snippets is shown.

![Snippet overview](https://github.com/thisislibra/genesis/raw/master/git-images/generate-1.png "An overview of all loaded snippets")

Upon selecting a snippet, the snippet ID is shown. The remaining options are shown to the user. The obfuscation profile (either `none`, `low`, `medium`, or `high`) needs to be selected, as well as the value for the given `alterators`. These are values within the script that need to be provided. These values are then replaced within the script, prior to the obfuscation. This way, a test case serve a generic purpose with an even higher focus on techniques: a generic downloader script can be generated with a different URL in every generation.

![Generation of a snippet](https://github.com/thisislibra/genesis/raw/master/git-images/generate-2.png "Generating a snippet")

At last, a piece of Javascript is returned to the user. A generation of the above given snippet (with two messages using the `low` obfuscation profile) is given below. Execute it to see the original input!

```javascript
function jsFunction() {
console.log(atob(atob(atob(atob(("V" + "m" + "x" + "S" + "S" + "k" + "5" + "G" + "b" + "3" + "l" + "W" + "b" + "G" + "h" + "Q" + "V" + "k" + "V" + "a" + "S" + "1" + "V" + "q" + "Q" + "m" + "F" + "j" + "V" + "n" + "B" + "G" + "Y" + "U" + "Z" + "k" + "a" + "F" + "I" + "w" + "c" + "E" + "l" + "a" + "V" + "V" + "J" + "X" + "U" + "2" + "x" + "J" + "e" + "F" + "d" + "U" + "Q" + "l" + "h" + "i" + "R" + "1" + "J" + "Q" + "V" + "F" + "Z" + "k" + "U" + "1" + "N" + "G" + "W" + "n" + "R" + "k" + "R" + "X" + "B" + "U" + "U" + "m" + "t" + "w" + "M" + "l" + "Y" + "y" + "e" + "E" + "9" + "R" + "M" + "l" + "Z" + "z" + "Y" + "T" + "N" + "w" + "V" + "G" + "J" + "r" + "S" + "n" + "F" + "V" + "M" + "F" + "p" + "H" + "Y" + "n" + "c" + "9" + "P" + "Q" + "=" + "=" ))))));
console.log(atob(atob(atob(atob(("V" + "m" + "t" + "S" + "T" + "1" + "N" + "s" + "b" + "3" + "h" + "j" + "R" + "W" + "h" + "p" + "U" + "j" + "N" + "S" + "S" + "1" + "U" + "w" + "Z" + "D" + "R" + "N" + "b" + "H" + "B" + "H" + "V" + "G" + "t" + "O" + "b" + "F" + "Z" + "u" + "Q" + "l" + "p" + "W" + "M" + "j" + "E" + "0" + "Y" + "W" + "1" + "K" + "c" + "k" + "5" + "Y" + "T" + "l" + "R" + "W" + "V" + "1" + "J" + "6" + "V" + "F" + "V" + "a" + "Q" + "0" + "0" + "x" + "Q" + "l" + "V" + "N" + "R" + "D" + "A" + "9" ))))));
}

jsFunction();
```

# Installation
To install Genesis, one needs to install Java 8 together with the Java Enterprise Edition 8. The framework is not meant to be run in a place where it is directly exposed to the internet. Aside from that, the only part that relies on the Java EE framework, are the exposed API endpoints which are located in the `controllers` package. Every other part of Genesis works on any Java version (OpenJDK or Oracle's JDK), as this was kept in mind during the design.

## Back-end installation
The WAR file that is given in the release section needs to be loaded by a [Glassfish](https://javaee.github.io/glassfish/ "Glassfish") or [Payara](https://www.payara.fish/ "Payara") server. Regardless of the operating system, an accessible folder at `/json/` should be created. On Linux or Unix, this can be done using `mkdir /json`. If need be, some permissions might need to be changed using `chmod`. On Windows, one needs to create the `C:\json`, unless another drive is used as the primary drive.

## Front-end installation
Make sure to install `npm` and run `npm install` in the `front-end` directory. After that, one simply needs to execute the following script:

Linux/Unix:
```bash
#!/bin/bash
export VUE_APP_API_URL="http://localhost:8080/genesis/api/v1"
npm start
```

To generate a production variant of the VueJS site, use `npm run-script build` instead of `npm start`. This will create a static site in the `dist` folder within the `front-end` folder.

Windows machines need to export the value in their PATH variable, after which the `npm start` needs to be issued.

**Note to change the local host URL to the one that your Java server uses.**

After these steps, one can start to use Genesis via the front-end or via the API on the specified ports!

# Special thanks
The creation of Genesis could not have taken place without the support from ABN AMRO. More specifically, I’d like to thank my colleagues Armand Piers and Eveline van Hout for picking up my day-to-day tasks during some of the development. I’d like to thank my managers Lalit Bhakuni and Irina van Elst for supporting the idea after my initial proposal. Additionally, I’d like to thank a very good friend of mine named Mike, with whom I brainstormed about some of the obfuscation techniques. Lastly, I’d like to thank Maarten Schermer, who was hired to alter the front-end template, based upon the requirements that we set.
