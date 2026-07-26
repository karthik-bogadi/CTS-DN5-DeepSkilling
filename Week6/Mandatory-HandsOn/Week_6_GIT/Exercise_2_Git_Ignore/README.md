# Exercise 2 - Git Ignore

## 📌 Introduction

Git is a distributed version control system that tracks changes made to files in a project. However, not every file created during development should be stored in the repository. Temporary files, log files, compiled binaries, cache files, and IDE-specific configuration files are usually unnecessary and can clutter the repository.

Git provides a special file named **`.gitignore`** that specifies which files and folders Git should ignore. Any file matching the patterns defined in `.gitignore` will not be shown as untracked and will not be committed to the repository.

This hands-on exercise demonstrates how to ignore unwanted files and folders using the **`.gitignore`** file. Specifically, a `.log` file and a `log` directory are created and then excluded from version control using Git Ignore rules. This exercise aligns with the CTS Git Hands-on objective of implementing Git Ignore to ignore unwanted files and folders. :contentReference[oaicite:0]{index=0}

---

# 🎯 Learning Objectives

After completing this exercise, you will be able to:

- Understand the purpose of `.gitignore`.
- Learn why certain files should not be committed.
- Create and configure a `.gitignore` file.
- Ignore files using wildcard patterns.
- Ignore directories using folder patterns.
- Verify ignored files using Git commands.
- Commit only the required files to the repository.

---

# 📋 Prerequisites

Before performing this exercise, ensure the following requirements are met:

- Git is installed.
- Git Bash is configured.
- GitHub account is available.
- Local Git repository is already created.
- Remote GitHub repository is connected.
- Basic knowledge of Git commands (`git status`, `git add`, `git commit`, `git push`).

These prerequisites are consistent with the CTS Hands-on document. :contentReference[oaicite:1]{index=1}

---

# 📖 Background

## What is `.gitignore`?

A `.gitignore` file is a plain text configuration file that tells Git which files or folders should be ignored while tracking changes.

Ignored files:

- are not shown as untracked,
- cannot be added accidentally,
- are not committed,
- are not pushed to GitHub.

---

## Why is `.gitignore` Required?

During software development, many files are generated automatically, such as:

- Log files
- Cache files
- Build outputs
- Temporary files
- IDE configuration files

These files:

- increase repository size,
- create unnecessary commits,
- cause merge conflicts,
- reduce repository cleanliness.

Using `.gitignore` keeps the repository organized.

---

# 📂 Project Structure

```
Exercise_2_Git_Ignore/
│
├── README.md
├── .gitignore
├── sample.log          (ignored)
└── log/                (ignored)
    └── server.log
```

After committing, GitHub will contain only:

```
Exercise_2_Git_Ignore/
│
├── README.md
└── .gitignore
```

The `.log` files and `log` folder are ignored.

---

# ⚙️ Implementation

## Step 1: Navigate to the Repository

```bash
cd CTS_DN-5.0_DeepSkilling/Week_6_GIT/Exercise_2_Git_Ignore
```

---

## Step 2: Create a Log File

```bash
echo "Application Started" > sample.log
```

---

## Step 3: Create a Log Folder

```bash
mkdir log
```

Move into the folder:

```bash
cd log
```

Create another log file:

```bash
echo "Server Started" > server.log
```

Return to the project folder:

```bash
cd ..
```

---

## Step 4: Check Git Status

```bash
git status
```

Before adding `.gitignore`, Git shows:

```
Untracked files:
sample.log
log/
```

---

## Step 5: Create `.gitignore`

Create a file named:

```
.gitignore
```

Add:

```gitignore
*.log
log/
```

### Explanation

`*.log`

- Ignore every file having `.log` extension.

Examples:

```
sample.log
error.log
application.log
```

---

`log/`

Ignore the complete folder named `log`.

Example:

```
log/
    server.log
    error.log
```

---

## Step 6: Verify

Run:

```bash
git status
```

Expected result:

```
Untracked files:

.gitignore
```

Notice:

- `sample.log` is ignored.
- `log/` is ignored.

This confirms that Git Ignore is working correctly. :contentReference[oaicite:2]{index=2}

---

## Step 7: Stage `.gitignore`

```bash
git add .gitignore
```

---

## Step 8: Commit

```bash
git commit -m "Add .gitignore to ignore log files and folders"
```

---

## Step 9: Push

```bash
git pull origin main
git push origin main
```

---

# 📄 Sample `.gitignore`

```gitignore
*.log
log/
```

---

# 📸 Expected Git Status

Before `.gitignore`

```
Untracked files:

sample.log
log/
```

After `.gitignore`

```
Untracked files:

.gitignore
```

After Commit

```
nothing to commit, working tree clean
```

---

# 📊 Working Flow

```
Create log files
        │
        ▼
Git detects them
        │
        ▼
Create .gitignore
        │
        ▼
Git ignores matching files
        │
        ▼
Commit .gitignore
        │
        ▼
Push to GitHub
```

---

# ✅ Expected Output

Local Project

```
Exercise_2_Git_Ignore/
│
├── README.md
├── .gitignore
├── sample.log
└── log/
    └── server.log
```

GitHub Repository

```
Exercise_2_Git_Ignore/
│
├── README.md
└── .gitignore
```

---

# 💡 Advantages

- Keeps repository clean.
- Prevents accidental commits.
- Reduces repository size.
- Avoids unnecessary merge conflicts.
- Improves collaboration.
- Protects temporary development files.

---

# ⚠️ Limitations

- `.gitignore` only affects untracked files.
- Files already committed remain tracked until removed.
- Incorrect patterns may ignore required files.
- Different projects may require different ignore rules.

---

# 🌍 Real-World Applications

Git Ignore is commonly used to ignore:

- Log files
- Temporary files
- Build folders
- Cache directories
- Dependency folders
- IDE configuration files
- Environment variable files

---

# ❓ Interview Questions

### 1. What is `.gitignore`?

A configuration file used to ignore unwanted files and folders in Git.

---

### 2. Does `.gitignore` remove files already committed?

No. It only affects untracked files.

---

### 3. How do you ignore all log files?

```gitignore
*.log
```

---

### 4. How do you ignore an entire folder?

```gitignore
folder_name/
```

---

### 5. Can multiple `.gitignore` files exist?

Yes. Git supports multiple `.gitignore` files in different directories, and each applies to its own directory and subdirectories.

---

# 📝 Key Takeaways

- `.gitignore` prevents unnecessary files from being tracked.
- Wildcards (`*`) can ignore groups of files.
- Folder names ending with `/` ignore directories.
- Ignored files are not committed or pushed.
- `.gitignore` improves repository cleanliness and collaboration.

---

# 📚 References

- CTS Deep Skilling – Git Hands-on Exercise 2 (Git Ignore). :contentReference[oaicite:3]{index=3}
- Official Git Documentation: https://git-scm.com/docs/gitignore

---

# 🎉 Conclusion

In this exercise, Git Ignore was successfully implemented to exclude `.log` files and the `log` directory from version control. The `.gitignore` file ensured that unnecessary files remained outside the Git repository while only the required project files were committed. This hands-on exercise demonstrates a fundamental Git practice used in professional software development to maintain clean, efficient, and manageable repositories. :contentReference[oaicite:4]{index=4}
