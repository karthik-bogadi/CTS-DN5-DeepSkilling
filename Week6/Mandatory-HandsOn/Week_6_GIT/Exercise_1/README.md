# Week 6 - Git Hands-on Lab

## Exercise 1 - Git Configuration and Creating Your First Local Repository

> **Response 1 of 3**

---

# Table of Contents

1. Introduction
2. Learning Objectives
3. Prerequisites
4. Background Concepts

   * Version Control System
   * Why Version Control is Needed
   * Local Version Control
   * Centralized Version Control
   * Distributed Version Control
   * Git
   * GitHub
   * GitLab
   * Repository
   * Working Directory
   * Staging Area
   * Local Repository
   * Remote Repository
   * Commit
   * Branch
   * Git Configuration
5. Project Overview
6. High-Level Architecture
7. Technologies Used
8. Project Structure

---

# 1. Introduction

## What is this Exercise?

This exercise introduces the fundamentals of **Git**, the world's most popular Distributed Version Control System (DVCS). The hands-on focuses on configuring Git, setting up a default text editor, creating a Git repository, adding files, committing changes, and synchronizing the repository with a remote server. These objectives align with the lab instructions, which require learners to configure Git, integrate a text editor, add a file to a repository, and use core Git commands such as `git init`, `git status`, `git add`, `git commit`, `git pull`, and `git push`. 

Instead of only learning commands, this exercise helps you understand **how Git internally manages files and tracks project history**.

---

## What Problem Does This Exercise Solve?

Before Version Control Systems existed, developers faced many problems:

* Multiple people edited the same file.
* Old versions were lost.
* There was no history of changes.
* Accidentally deleting files meant losing work forever.
* Team collaboration was extremely difficult.

Git solves all these problems by keeping a complete history of every change.

---

## Why is Git Important?

Today, almost every software company uses Git.

Examples include:

* Google
* Microsoft
* Amazon
* Meta
* Netflix
* Adobe
* Cognizant
* Infosys
* TCS
* Accenture

Whether you become a:

* Java Developer
* Python Developer
* Full Stack Developer
* DevOps Engineer
* Cloud Engineer
* Data Scientist

Git is one of the first tools you will use every day.

---

## Where is Git Used?

Git is widely used in:

* Software Development
* Web Development
* Mobile App Development
* Machine Learning Projects
* Data Science
* DevOps
* Cloud Infrastructure
* Open Source Contributions
* Research Projects
* College Projects
* Enterprise Applications

---

# 2. Learning Objectives

After completing this exercise, you should be able to:

* Understand Version Control Systems.
* Explain why Git is required.
* Configure Git for first-time use.
* Set user name and email.
* Configure a default text editor.
* Create a Git repository.
* Understand the `.git` folder.
* Create files inside a repository.
* Track file changes.
* Stage changes.
* Create commits.
* Understand local and remote repositories.
* Push code to GitHub/GitLab.
* Pull changes from remote repositories.
* Understand Git's internal workflow.

---

# 3. Prerequisites

Before starting this exercise, ensure you have:

| Requirement                  | Purpose              |
| ---------------------------- | -------------------- |
| Windows/Linux/macOS          | Operating System     |
| Git Installed                | Version Control Tool |
| Git Bash                     | Git Command Line     |
| VS Code or Notepad++         | Default Git Editor   |
| GitHub/GitLab Account        | Remote Repository    |
| Internet Connection          | Push/Pull Operations |
| Basic Command Line Knowledge | Running Git Commands |

---

# 4. Background Concepts

This section explains every concept from scratch.

---

# 4.1 What is Version Control?

## Definition

A **Version Control System (VCS)** is software that records changes made to files over time so that previous versions can be recovered whenever needed.

---

## Why does it exist?

Imagine writing a project report.

Every day you save:

```
Report.docx

Report_Final.docx

Report_Final2.docx

Report_Final_New.docx

Report_Final_New_Updated.docx

Report_Final_Last.docx

Report_Final_Last_Final.docx
```

After one month:

Nobody knows which file is actually the latest.

Git solves this problem automatically.

---

## Why is Version Control Needed?

Without Version Control:

* No backup
* No history
* No collaboration
* Difficult debugging
* Accidental overwrite
* Impossible rollback

With Version Control:

* Complete history
* Collaboration
* Backup
* Easy recovery
* Change tracking
* Safe experimentation

---

## Real-world Analogy

Imagine writing notes in a notebook.

Every time you make changes:

Instead of erasing,

you take a photocopy.

Now you have:

```
Notebook Version 1

↓

Notebook Version 2

↓

Notebook Version 3

↓

Notebook Version 4
```

If Version 4 has mistakes,

you simply return to Version 3.

Git works exactly like this.

---

# 4.2 Types of Version Control Systems

There are three major types.

---

## 1. Local Version Control System

Everything is stored on one computer.

```
Computer

│

├── Version 1

├── Version 2

└── Version 3
```

### Advantages

* Simple

### Disadvantages

* If computer crashes,
  everything is lost.

---

## 2. Centralized Version Control System (CVCS)

One central server stores everything.

```
           Central Server

          /      |      \

      User1   User2   User3
```

Examples:

* SVN
* CVS

### Problem

If the server crashes,

everyone is affected.

---

## 3. Distributed Version Control System (DVCS)

Every developer has a complete copy.

```
Developer A

↓

Complete Repository

↓

Developer B

↓

Complete Repository

↓

Developer C

↓

Complete Repository
```

Even if GitHub goes offline,

developers still have complete project history.

Git is a Distributed Version Control System.

---

# 4.3 What is Git?

## Definition

Git is a **Distributed Version Control System** created by **Linus Torvalds** in 2005 to efficiently manage and track changes in source code.

---

## Purpose

Git helps developers:

* Track changes
* Collaborate
* Manage project history
* Restore old versions
* Create branches
* Merge code safely

---

## Why was Git created?

Before Git,

Linux kernel developers struggled to coordinate changes among thousands of contributors.

Git solved this problem.

---

## Advantages

* Extremely fast
* Lightweight
* Free
* Open Source
* Secure
* Reliable
* Offline support
* Branching is easy
* Used worldwide

---

## Limitations

* Initially difficult for beginners.
* Large binary files are not Git's strength.
* Requires understanding of Git workflow.

---

## Real-world Analogy

Think of Git as:

> A CCTV camera for your project.

Every change is recorded forever.

If something goes wrong,

you can replay history.

---

# 4.4 What is GitHub?

GitHub is a cloud platform that stores Git repositories online.

Git manages versions.

GitHub stores repositories.

Think of:

Git

↓

Notebook

GitHub

↓

Cloud Storage

---

## Git vs GitHub

| Git               | GitHub                                |
| ----------------- | ------------------------------------- |
| Software          | Website                               |
| Installed locally | Cloud Platform                        |
| Tracks changes    | Stores repositories                   |
| Works offline     | Requires Internet for synchronization |
| Open Source Tool  | Hosting Service                       |

---

# 4.5 What is GitLab?

GitLab is another platform for hosting Git repositories.

The lab instructions use **GitLab** as the example remote repository for creating a project and pushing local changes. 

Like GitHub,

GitLab provides:

* Repository hosting
* Collaboration
* CI/CD
* Issue Tracking
* Merge Requests

---

# 4.6 What is a Repository?

A Repository (Repo) is the storage location where Git tracks all project files and their history.

Example:

```
StudentManagementSystem/

│

├── src/

├── pom.xml

├── README.md

└── .git/
```

Everything inside is managed by Git.

---

## Types of Repository

### Local Repository

Stored on your computer.

Example:

```
C:\Projects\GitDemo
```

---

### Remote Repository

Stored on GitHub/GitLab.

Example:

```
https://github.com/username/project
```

---

# 4.7 Working Directory

The Working Directory is where you actually create and modify files.

Example:

```
welcome.txt

README.md

Main.java
```

When editing files,

you are working inside the Working Directory.

---

# 4.8 Staging Area (Index)

The Staging Area acts as a **waiting room** between the Working Directory and the Local Repository.

```
Working Directory

↓

git add

↓

Staging Area

↓

git commit

↓

Repository
```

Git allows you to choose which changes should be committed.

---

## Real-world Analogy

Imagine submitting an assignment.

```
Write Assignment

↓

Review Assignment

↓

Submit Assignment
```

Git follows the same process:

```
Modify File

↓

Stage File

↓

Commit File
```

---

# 4.9 Commit

A Commit is a **snapshot** of your project at a specific point in time.

Each commit has:

* Unique ID (SHA)
* Author
* Email
* Date
* Time
* Commit Message

Example:

```
Commit

↓

Added Login Page
```

Later,

```
Commit

↓

Fixed Login Bug
```

Git stores both versions.

---

# 4.10 Git Configuration

Before using Git,

you configure:

```
Username

Email

Default Editor
```

These settings identify the author of each commit and determine which editor Git opens when creating messages or editing configuration. The hands-on exercise begins with configuring the user name and email, then setting a default editor before creating the repository. 

---

# Project Overview

In this exercise, we perform the initial setup required to start using Git effectively.

The exercise includes:

* Configuring Git with user information.
* Setting a default editor.
* Creating a local Git repository.
* Creating a text file (`welcome.txt`).
* Tracking the file using Git.
* Committing the file to the local repository.
* Connecting to a remote repository.
* Synchronizing changes using pull and push operations.

Although the lab uses a sample project named **GitDemo**, the same workflow applies to any software project. 

---

# High-Level Architecture

```
                 Developer

                     │

                     ▼

            Working Directory

                     │

           (Create / Edit Files)

                     │

                     ▼

              git add command

                     │

                     ▼

              Staging Area

                     │

           git commit command

                     │

                     ▼

            Local Git Repository

                     │

             git push command

                     │

                     ▼

        GitHub / GitLab Repository
```

---

# Technologies / Tools Used

| Technology                    | Purpose                            | Role in this Exercise                                     |
| ----------------------------- | ---------------------------------- | --------------------------------------------------------- |
| Git                           | Distributed Version Control System | Tracks project history and file changes                   |
| Git Bash                      | Command Line Interface             | Executes Git commands                                     |
| VS Code / Notepad++           | Text Editor                        | Used by Git for editing configuration and commit messages |
| GitHub / GitLab               | Remote Repository Hosting          | Stores the repository online and enables synchronization  |
| Windows Terminal / PowerShell | Terminal                           | Used to run Git commands on Windows                       |

---

# Project Structure

```text
Week_6_GIT/
│
└── Exercise_1/
    ├── README.md
    ├── welcome.txt
    ├── Git_Configuration.png
    ├── Notepad++_added_to_path.png
    ├── Notepad++_opened_using_git.png
    ├── Configure_Notepad++_as_Git_Default_Editor.png
    └── Exercise_1.docx
```

### Folder and File Explanation

| File / Folder     | Description                                        |
| ----------------- | -------------------------------------------------- |
| `README.md`       | Complete documentation for the exercise            |
| `welcome.txt`     | Sample text file created and tracked by Git        |
| `*.png`           | Screenshots demonstrating each implementation step |
| `Exercise_1.docx` | Original hands-on lab document used for reference  |
| `Exercise_1/`     | Contains all files related to Git Exercise 1       |

---
# Week 6 - Git Hands-on Lab

## Exercise 1 - Git Configuration and Creating Your First Local Repository

> **Response 2 of 3**

---

# Table of Contents

1. Implementation Overview
2. Step 1 – Verify Git Installation
3. Step 2 – Configure Git
4. Step 3 – Configure Default Editor
5. Step 4 – Create a Local Repository
6. Step 5 – Create `welcome.txt`
7. Step 6 – Check Repository Status
8. Step 7 – Add File to Staging Area
9. Step 8 – Commit Changes
10. Step 9 – Connect to Remote Repository
11. Step 10 – Pull and Push Changes
12. Git Internal Workflow
13. Command Summary

---

# 1. Implementation Overview

This exercise demonstrates the complete lifecycle of working with Git for the first time. According to the hands-on guide, the workflow includes:

* Configuring Git with user information.
* Integrating a text editor as the default Git editor.
* Creating a local repository.
* Creating and tracking a file (`welcome.txt`).
* Committing changes.
* Synchronizing the local repository with a remote repository using `git pull` and `git push`.  

The overall workflow is shown below:

```text
Install Git
      │
      ▼
Configure Username & Email
      │
      ▼
Configure Default Editor
      │
      ▼
Create Repository
      │
      ▼
Create welcome.txt
      │
      ▼
git status
      │
      ▼
git add
      │
      ▼
git commit
      │
      ▼
git pull
      │
      ▼
git push
```

---

# 2. Step 1 – Verify Git Installation

Before using Git, verify that it is installed correctly.

### Command

```bash
git --version
```

The lab begins by checking whether Git is installed and displaying the installed version. 

### Sample Output

```text
git version 2.50.1.windows.1
```

### Explanation

This command:

* Checks whether Git is installed.
* Displays the installed version.
* Confirms that Git is available from the command line.

If Git is not installed, the terminal displays an error such as:

```text
'git' is not recognized as an internal or external command.
```

---

# 3. Step 2 – Configure Git

Every commit stores the author's information. Therefore, Git should be configured before creating commits.

### Configure Username

```bash
git config --global user.name "Your Name"
```

Example:

```bash
git config --global user.name "G Sai Kiran"
```

---

### Configure Email

```bash
git config --global user.email "your@email.com"
```

Example:

```bash
git config --global user.email "example@gmail.com"
```

The hands-on instructs learners to configure both the user name and email, then verify the configuration. 

---

### Verify Configuration

```bash
git config --global --list
```

Sample Output

```text
user.name=G Sai Kiran
user.email=example@gmail.com
```

---

## Why is Configuration Required?

Every commit records:

```text
Commit ID

↓

Author Name

↓

Author Email

↓

Date

↓

Commit Message
```

Without configuration:

* Git cannot correctly identify the author.
* Collaboration becomes difficult.

---

# 4. Step 3 – Configure Default Editor

Git opens a text editor for tasks such as writing commit messages and editing configuration.

The lab demonstrates configuring Notepad++ as the default editor after ensuring it is available on the system path.  

In our implementation, we configured **Visual Studio Code** instead.

### VS Code Configuration

```bash
git config --global core.editor "code --wait"
```

### Verify

```bash
git config --global --edit
```

VS Code opens with the Git configuration file.

---

### Internal Working

```text
Git

↓

Needs Editor

↓

Checks core.editor

↓

Launches VS Code

↓

Waits until editor closes

↓

Continues execution
```

The `--wait` option tells Git to pause until the editor is closed.

---

# 5. Step 4 – Create a Local Repository

A Git repository stores the complete history of a project.

The lab creates a sample project (for example, `GitDemo`) and initializes it as a Git repository. 

### Create Project Folder

```bash
mkdir GitDemo
```

Move inside:

```bash
cd GitDemo
```

---

### Initialize Repository

```bash
git init
```

Sample Output

```text
Initialized empty Git repository
```

---

## What Happens Internally?

Git creates a hidden folder named:

```text
.git
```

Repository structure:

```text
GitDemo/

│

├── .git/

└── welcome.txt
```

The `.git` folder contains:

* Commit history
* Branches
* Configuration
* Object database
* References

Without this folder, Git cannot manage the project.

---

# 6. Step 5 – Create `welcome.txt`

The exercise creates a file named `welcome.txt` and adds sample content. 

Example content:

```text
Welcome to Git Hands-on Lab
```

Verify that the file exists:

```bash
ls
```

View its contents:

```bash
cat welcome.txt
```

These verification steps are also included in the lab instructions. 

---

# 7. Step 6 – Check Repository Status

Command:

```bash
git status
```

Output

```text
Untracked files:

welcome.txt
```

---

## What Does "Untracked" Mean?

Git knows the file exists,

but

Git is **not yet managing it**.

```text
File Created

↓

Git Detects File

↓

Not Tracking

↓

Status = Untracked
```

---

## Internal Working

When `git status` runs, Git compares:

```text
Working Directory

↓

Staging Area

↓

Latest Commit
```

Then it reports:

* New files
* Modified files
* Deleted files
* Staged changes

---

# 8. Step 7 – Add File to the Staging Area

Command:

```bash
git add welcome.txt
```

or

```bash
git add .
```

The lab instructs adding `welcome.txt` so that Git starts tracking it. 

---

## What is the Staging Area?

The staging area is a temporary place where selected changes are prepared before creating a commit.

```text
Working Directory

↓

git add

↓

Staging Area

↓

git commit

↓

Repository
```

---

## Why Doesn't Git Commit Immediately?

Imagine editing five files.

You may only want to commit two.

The staging area allows you to choose exactly what should become part of the next commit.

---

### Verify

```bash
git status
```

Output

```text
Changes to be committed:

new file: welcome.txt
```

---

# 9. Step 8 – Commit Changes

Create a snapshot of the staged changes.

### Command

```bash
git commit -m "Add welcome.txt"
```

The lab also demonstrates opening the configured editor to enter a multi-line commit message instead of using `-m`. 

---

## What Happens During a Commit?

Git performs the following:

```text
Reads Staging Area

↓

Creates Snapshot

↓

Generates SHA Identifier

↓

Stores Commit

↓

Updates Branch Pointer
```

Example:

```text
Commit

↓

6f8a1b3

↓

Message

↓

Add welcome.txt
```

---

### Verify

```bash
git log
```

Example

```text
commit 6f8a1b3

Author:

Date:

Message:

Add welcome.txt
```

---

# 10. Step 9 – Connect to a Remote Repository

After creating the local repository, create a remote repository (for example, on GitHub or GitLab) and connect it.

The lab demonstrates creating a remote repository and then synchronizing it with the local repository. 

### Add Remote

```bash
git remote add origin https://github.com/username/repository.git
```

Verify:

```bash
git remote -v
```

Output

```text
origin

fetch

origin

push
```

---

# 11. Step 10 – Pull and Push Changes

### Pull

```bash
git pull origin main
```

(or `master` if the remote repository uses that branch name, as shown in the lab). 

Pull performs:

```text
Download Changes

↓

Merge into Local Repository
```

---

### Push

```bash
git push origin main
```

This uploads local commits to the remote repository.

The lab includes both `git pull` and `git push` as the final synchronization steps. 

---

# 12. Git Internal Workflow

The following diagram summarizes Git's internal operation:

```text
Create File
     │
     ▼
Working Directory
     │
     │ git add
     ▼
Staging Area
     │
     │ git commit
     ▼
Local Repository
     │
     │ git push
     ▼
Remote Repository
     │
     │ git pull
     ▼
Local Repository
```

---

## Complete Git Lifecycle

```text
Developer

↓

Create File

↓

Modify File

↓

git status

↓

git add

↓

git commit

↓

git push

↓

GitHub Repository

↓

Another Developer

↓

git pull
```

---

# 13. Command Summary

| Command                                         | Purpose                                         |
| ----------------------------------------------- | ----------------------------------------------- |
| `git --version`                                 | Verify Git installation                         |
| `git config --global user.name`                 | Configure author name                           |
| `git config --global user.email`                | Configure author email                          |
| `git config --global --list`                    | Display global configuration                    |
| `git config --global core.editor "code --wait"` | Set VS Code as the default editor               |
| `git config --global --edit`                    | Open Git configuration in the configured editor |
| `mkdir GitDemo`                                 | Create a project directory                      |
| `cd GitDemo`                                    | Move into the project directory                 |
| `git init`                                      | Initialize a new Git repository                 |
| `ls`                                            | List files in the current directory             |
| `cat welcome.txt`                               | Display the contents of `welcome.txt`           |
| `git status`                                    | Show repository status                          |
| `git add welcome.txt`                           | Stage a specific file                           |
| `git add .`                                     | Stage all changes                               |
| `git commit -m "message"`                       | Create a commit                                 |
| `git log`                                       | Display commit history                          |
| `git remote add origin <url>`                   | Add a remote repository                         |
| `git remote -v`                                 | Display configured remotes                      |
| `git pull origin main`                          | Download and merge remote changes               |
| `git push origin main`                          | Upload local commits to the remote repository   |

---

# README

# Week 6 - Git Hands-on Lab

## Exercise 1 - Git Configuration and Creating Your First Local Repository

> **Response 3 of 3**

---

# Table of Contents

1. Expected Output
2. Verification
3. Common Errors and Troubleshooting
4. Best Practices
5. Advantages of Git
6. Limitations of Git
7. Real-World Applications
8. Interview Questions & Answers
9. Quick Revision
10. Conclusion
11. References

---

# 1. Expected Output

After completing this exercise successfully, the following should be achieved:

* Git is installed and accessible from the command line.
* User name and email are configured.
* A default editor (VS Code or Notepad++) is configured.
* A local Git repository is initialized.
* `welcome.txt` is created and tracked.
* A commit containing the file is created.
* The repository is connected to a remote repository.
* Changes are synchronized using `git pull` and `git push`. These are the final tasks described in the hands-on exercise. 

---

## Example Repository Status

### Before Staging

```bash
git status
```

Output

```text
On branch main

Untracked files:
    welcome.txt
```

---

### After Staging

```bash
git add welcome.txt
git status
```

Output

```text
Changes to be committed:

new file: welcome.txt
```

---

### After Commit

```bash
git commit -m "Add welcome file"
```

Output

```text
[main abc1234]

1 file changed

create mode 100644 welcome.txt
```

---

### Commit History

```bash
git log --oneline
```

Example

```text
abc1234 Add welcome file
```

---

### Push Successful

```bash
git push origin main
```

Output

```text
Enumerating objects...

Writing objects...

Total 3

Done
```

---

# 2. Verification

The following checklist can be used to verify the exercise.

| Verification            | Command                      | Expected Result                  |
| ----------------------- | ---------------------------- | -------------------------------- |
| Git Installed           | `git --version`              | Git version displayed            |
| User Configured         | `git config --global --list` | Name and email displayed         |
| Repository Created      | `git init`                   | Repository initialized           |
| Repository Status       | `git status`                 | Shows current repository state   |
| File Tracked            | `git status`                 | File listed under staged changes |
| Commit Created          | `git log`                    | Commit appears in history        |
| Remote Added            | `git remote -v`              | Remote repository listed         |
| Repository Synchronized | `git push`                   | Push completed successfully      |

---

# 3. Common Errors and Troubleshooting

## Error 1

```text
git is not recognized
```

### Reason

Git is not installed or not added to the system PATH.

### Solution

* Install Git.
* Restart the terminal.
* Verify using:

```bash
git --version
```

---

## Error 2

```text
Author identity unknown
```

### Reason

Git user name and email are not configured.

### Solution

```bash
git config --global user.name "Your Name"

git config --global user.email "your@email.com"
```

---

## Error 3

```text
nothing to commit
```

### Reason

No changes have been made since the last commit.

### Solution

Modify a file and run:

```bash
git add .

git commit -m "Updated files"
```

---

## Error 4

```text
fatal: not a git repository
```

### Reason

You are not inside a Git repository.

### Solution

Move into the repository directory:

```bash
cd GitDemo
```

or initialize one:

```bash
git init
```

---

## Error 5

```text
failed to push some refs
```

### Reason

The remote repository contains commits that are not present locally.

### Solution

```bash
git pull origin main --rebase

git push origin main
```

---

## Error 6

```text
Permission denied
```

### Reason

Authentication or authorization problem.

### Solution

* Verify repository access.
* Authenticate with GitHub/GitLab.
* Use a Personal Access Token (PAT) if required.

---

# 4. Best Practices

Follow these practices while working with Git:

* Write meaningful commit messages.
* Commit frequently with logical changes.
* Pull the latest changes before starting work.
* Push changes regularly to keep the remote repository updated.
* Use `.gitignore` to exclude unnecessary files.
* Avoid committing passwords, API keys, or confidential data.
* Keep commits focused on a single task whenever possible.

---

## Recommended `.gitignore`

```gitignore
# Java
*.class
target/

# VS Code
.vscode/

# IntelliJ
.idea/

# Logs
*.log

# Microsoft Office temporary files
~$*

# Operating System
.DS_Store
Thumbs.db
```

---

# 5. Advantages of Git

* Distributed Version Control System.
* Complete project history.
* Fast performance.
* Easy rollback to previous versions.
* Supports branching and merging.
* Enables collaboration among multiple developers.
* Works offline.
* Free and open source.
* Integrates with platforms such as GitHub and GitLab.

---

# 6. Limitations of Git

* Initial learning curve for beginners.
* Command-line usage can be challenging initially.
* Binary files are not efficiently versioned.
* Merge conflicts require careful resolution.
* Large repositories may consume significant disk space.

---

# 7. Real-World Applications

Git is used in:

* Enterprise Software Development
* Web Development
* Mobile Application Development
* Cloud Computing
* DevOps Pipelines
* Machine Learning Projects
* Data Science
* Artificial Intelligence
* Cybersecurity Projects
* Open Source Software
* Research and Academic Projects

Major organizations that use Git include:

* Google
* Microsoft
* Amazon
* Meta
* Netflix
* IBM
* Oracle
* Cognizant
* TCS
* Infosys

---

# 8. Interview Questions & Answers

## Q1. What is Git?

**Answer:**

Git is a Distributed Version Control System (DVCS) that tracks changes in files, maintains project history, and enables collaboration among developers.

---

## Q2. What is the difference between Git and GitHub?

**Answer:**

| Git                    | GitHub                      |
| ---------------------- | --------------------------- |
| Version Control System | Repository Hosting Platform |
| Installed locally      | Cloud service               |
| Tracks project history | Stores repositories online  |

---

## Q3. What is a repository?

**Answer:**

A repository is a directory managed by Git that contains project files and the hidden `.git` folder, which stores version history and repository metadata.

---

## Q4. What is the Staging Area?

**Answer:**

The staging area is an intermediate area where selected changes are prepared before creating a commit.

---

## Q5. What is a commit?

**Answer:**

A commit is a snapshot of the project's staged changes at a specific point in time.

---

## Q6. Difference between `git add` and `git commit`?

| `git add`      | `git commit`                                             |
| -------------- | -------------------------------------------------------- |
| Stages changes | Saves staged changes permanently in the local repository |

---

## Q7. What is the purpose of `git status`?

It displays:

* Untracked files
* Modified files
* Staged files
* Branch information
* Repository status

---

## Q8. Why is Git called Distributed Version Control?

Because every developer has a complete copy of the repository, including the full project history.

---

## Q9. What does `git init` do?

It initializes a new Git repository by creating the hidden `.git` directory.

---

## Q10. What is the purpose of `git pull`?

It downloads changes from the remote repository and integrates them into the local repository.

---

# 9. Quick Revision

| Concept           | Summary                                       |
| ----------------- | --------------------------------------------- |
| Git               | Distributed Version Control System            |
| Repository        | Project managed by Git                        |
| Working Directory | Current project files                         |
| Staging Area      | Temporary area before commit                  |
| Commit            | Snapshot of staged changes                    |
| Remote Repository | Repository hosted on GitHub/GitLab            |
| `git init`        | Initialize repository                         |
| `git status`      | Display repository status                     |
| `git add`         | Stage changes                                 |
| `git commit`      | Save staged changes                           |
| `git pull`        | Download and integrate remote changes         |
| `git push`        | Upload local commits to the remote repository |

---

# 10. Conclusion

This exercise provides a practical introduction to Git by covering the essential workflow required to start managing source code.

During this exercise, you learned how to:

* Install and verify Git.
* Configure user information.
* Configure a default editor.
* Create a local Git repository.
* Add files to the repository.
* Stage changes.
* Create commits.
* View repository history.
* Connect a local repository to a remote repository.
* Synchronize changes using `git pull` and `git push`.

Mastering these fundamental operations provides the foundation for advanced Git topics such as branching, merging, conflict resolution, rebasing, tagging, and collaborative software development.

---

# 11. References

The content of this exercise is based on the **CTS Deep Skilling Git Hands-on Lab**, which covers:

* Git configuration
* Default editor integration
* Repository creation
* Adding files
* Repository status
* Staging
* Committing
* Pulling from a remote repository
* Pushing to a remote repository     

---

**End of README – Week 6 Git Hands-on Lab | Exercise 1: Git Configuration and Creating Your First Local Repository**



