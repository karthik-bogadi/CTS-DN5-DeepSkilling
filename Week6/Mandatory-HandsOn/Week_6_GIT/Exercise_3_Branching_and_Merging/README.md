# Exercise 3 - Git Branching and Merging

## 📌 Introduction

Branching is one of Git's most powerful features that enables multiple developers to work independently on different features, bug fixes, or experiments without affecting the main source code. Each branch acts as an isolated development environment where changes can be made safely.

Once the work on a branch is completed and verified, it can be merged back into the main branch (also called **master** or **trunk**) to integrate the changes into the primary project.

In this hands-on exercise, a new branch is created, changes are made within that branch, the changes are committed, compared with the main branch, and finally merged into the main branch. After a successful merge, the temporary branch is deleted. This exercise demonstrates the complete Git branching and merging workflow used in professional software development. The CTS hands-on specifically focuses on constructing a branch, making changes in it, and merging it back into the main branch. :contentReference[oaicite:0]{index=0}

---

# 🎯 Learning Objectives

After completing this exercise, you will be able to:

- Understand the concept of Git branches.
- Learn why branching is important in software development.
- Create a new Git branch.
- Switch between branches.
- Make independent changes in a branch.
- Commit changes within a branch.
- Compare differences between two branches.
- Merge one branch into another.
- View the commit history using Git logs.
- Delete branches after successful merging.

These objectives are based on the CTS Git Hands-on Exercise 3. :contentReference[oaicite:1]{index=1}

---

# 📋 Prerequisites

Before starting this exercise, ensure the following requirements are satisfied:

- Git is installed on your system.
- Git Bash is configured.
- A local Git repository already exists.
- A remote GitHub repository is connected.
- Basic understanding of Git commands.
- P4Merge tool is configured if visual comparison is required.

These prerequisites are mentioned in the CTS Hands-on document. :contentReference[oaicite:2]{index=2}

---

# 📖 Background Concepts

## What is a Branch?

A branch in Git is an independent line of development. It allows developers to work on new features or fixes without modifying the main branch.

Instead of changing the production-ready code directly, developers create separate branches and work independently.

Example:

```
main
 │
 ├───────────────
 │
 └── Feature Branch
```

Each branch maintains its own commits until it is merged.

---

## Why Do We Need Branches?

Without branches:

- Every developer edits the same code.
- Conflicts increase.
- Bugs may directly affect production.
- Feature development becomes risky.

With branches:

- Development is isolated.
- Features can be tested independently.
- Multiple developers can work simultaneously.
- Stable code remains unaffected.

---

## Types of Git Branches

### Main Branch

The **main** (or **master**) branch contains the stable version of the project.

Example:

```
main
```

---

### Feature Branch

A feature branch is created to develop a new feature.

Example:

```
main
   \
    Feature-Login
```

---

### Bug Fix Branch

Used to fix software defects.

Example:

```
main
   \
    BugFix-101
```

---

### Release Branch

Prepared before releasing a software version.

Example:

```
main
   \
    Release-v1.0
```

---

### Hotfix Branch

Created to fix urgent production issues.

Example:

```
main
   \
    HotFix
```

---

# 🌳 What is Git Branching?

Git Branching is the process of creating an independent copy of the current project so that development can continue without affecting the original branch.

When a branch is created:

- Source code is copied logically.
- Commit history is shared initially.
- New commits belong only to that branch.
- Branches remain isolated until merged.

Example:

```
           main
             │
             │
      Initial Commit
             │
      Second Commit
             │
     -------------------
      │
      ▼
GitNewBranch
      │
      │
 Added branch.txt
      │
 New Commit
```

---

# 🔀 What is Git Merging?

Git Merging combines changes from one branch into another.

Suppose a developer creates a feature branch and completes the work.

Instead of copying files manually, Git automatically combines the commits into the destination branch.

Example:

```
Before Merge

main
 │
 ├──────────A──────────B

GitNewBranch
                 │
                 C
                 D


After Merge

main
 │
 ├──────────A──────────B──────────C──────────D
```

---

# 🧩 Types of Merge

## 1. Fast-Forward Merge

Occurs when the destination branch has no new commits.

```
main

A──B

GitNewBranch

A──B──C──D
```

After merge:

```
A──B──C──D
```

Git simply moves the pointer.

---

## 2. Three-Way Merge

Occurs when both branches have different commits.

```
        C
       /
A──B──
       \
        D
```

Git creates a new merge commit.

---

## What is Merge Conflict?

A merge conflict occurs when two branches modify the same portion of a file.

Example:

Main Branch

```
Welcome to Git
```

Feature Branch

```
Welcome to GitHub
```

Git cannot decide which change should be kept and requests manual resolution.

---

# 🔄 Branching Workflow

```
Create Branch
      │
      ▼
Switch Branch
      │
      ▼
Modify Files
      │
      ▼
Commit Changes
      │
      ▼
Switch to Main
      │
      ▼
Compare Branches
      │
      ▼
Merge Branch
      │
      ▼
Delete Branch
```

---

# 📂 Project Structure

```
CTS_DN-5.0_DeepSkilling/
│
└── Week_6_GIT/
    │
    └── Exercise_3_Branching_and_Merging/
        │
        ├── README.md
        └── branch.txt
```

---

# 🛠 Technologies Used

- Git
- Git Bash
- GitHub
- P4Merge (Optional)
- Windows Command Line

---

# ⚙️ Implementation

This section demonstrates the complete implementation of Git Branching and Merging as performed in this hands-on exercise. The workflow includes creating a new branch, making changes, committing those changes, comparing branches, merging the branch into the main branch, and finally deleting the temporary branch. These steps follow the CTS Git Hands-on Exercise 3 instructions. :contentReference[oaicite:0]{index=0}

---

# Step 1: Open Git Bash

Launch **Git Bash** from your system.

---

# Step 2: Navigate to the Repository

Move to the existing Git repository.

```bash
cd ~/OneDrive/Desktop/CTS_DN-5.0_DeepSkilling
```

Verify that you are inside the Git repository.

```bash
git status
```

### Expected Output

```text
On branch main

nothing to commit, working tree clean
```

---

# Step 3: Create a New Branch

Create a new branch named **GitNewBranch**.

```bash
git branch GitNewBranch
```

### Explanation

This command creates a new branch from the current branch.

At this point:

- No files are copied physically.
- Git creates a new pointer to the current commit.
- Both branches initially contain the same project history.

CTS specifies creating a branch named **GitNewBranch**. :contentReference[oaicite:1]{index=1}

---

# Step 4: Display Available Branches

Display all local branches.

```bash
git branch
```

or

```bash
git branch -a
```

### Expected Output

```text
GitNewBranch
* main
```

### Explanation

The `*` symbol indicates the currently active branch.

Currently:

```
main
↑
Current Branch
```

---

# Step 5: Switch to the New Branch

Move from **main** to **GitNewBranch**.

```bash
git checkout GitNewBranch
```

or

```bash
git switch GitNewBranch
```

### Expected Output

```text
Switched to branch 'GitNewBranch'
```

CTS instructs switching to the newly created branch before making any changes. :contentReference[oaicite:2]{index=2}

---

# Step 6: Verify the Active Branch

```bash
git branch
```

Expected Output

```text
* GitNewBranch
main
```

Now all new changes belong only to **GitNewBranch**.

---

# Step 7: Create a New File

Create a new text file.

```bash
echo "This file belongs to GitNewBranch." > branch.txt
```

Verify its contents.

```bash
cat branch.txt
```

Expected Output

```text
This file belongs to GitNewBranch.
```

CTS requires adding files with content in the new branch. :contentReference[oaicite:3]{index=3}

---

# Step 8: Check Repository Status

```bash
git status
```

Expected Output

```text
On branch GitNewBranch

Untracked files:

branch.txt
```

### Explanation

Git detects that:

- A new file has been created.
- The file is currently untracked.
- It must be added before committing.

---

# Step 9: Stage the File

```bash
git add branch.txt
```

Verify again.

```bash
git status
```

Expected Output

```text
Changes to be committed:

new file: branch.txt
```

---

# Step 10: Commit the Changes

```bash
git commit -m "Add branch.txt in GitNewBranch"
```

### Expected Output

```text
[GitNewBranch xxxxxx]

Add branch.txt in GitNewBranch

1 file changed

create mode 100644 branch.txt
```

### Explanation

The commit records:

- branch name
- commit message
- author
- timestamp
- snapshot of the repository

CTS specifies committing the changes made in the branch. :contentReference[oaicite:4]{index=4}

---

# Step 11: Verify Repository Status

```bash
git status
```

Expected Output

```text
On branch GitNewBranch

nothing to commit, working tree clean
```

The branch is now fully committed.

---

# Step 12: Switch Back to Main Branch

```bash
git checkout main
```

or

```bash
git switch main
```

Expected Output

```text
Switched to branch 'main'
```

CTS requires switching back to the main branch before merging. :contentReference[oaicite:5]{index=5}

---

# Step 13: Compare Branches

Compare the differences between **main** and **GitNewBranch**.

```bash
git diff main GitNewBranch
```

### Explanation

Git displays:

- Added files
- Deleted files
- Modified lines

CTS requires displaying the command-line differences between the trunk and the branch. :contentReference[oaicite:6]{index=6}

---

# Step 14: Compare Using P4Merge (Optional)

If **P4Merge** is installed and configured:

```bash
git difftool main GitNewBranch
```

### Explanation

P4Merge opens a graphical interface showing:

- side-by-side comparison
- highlighted differences
- changed lines

CTS recommends using P4Merge for visual comparison. :contentReference[oaicite:7]{index=7}

---

# Step 15: Merge the Branch

Merge **GitNewBranch** into **main**.

```bash
git merge GitNewBranch
```

### Expected Output (Fast-Forward)

```text
Updating abc123..def456

Fast-forward

branch.txt | 1 +

1 file changed
```

or

```text
Merge made by the 'ort' strategy.
```

depending on the repository history.

CTS requires merging the source branch into the trunk. :contentReference[oaicite:8]{index=8}

---

# Step 16: View Commit History

```bash
git log --oneline --graph --decorate
```

Example Output

```text
* abc1234 (HEAD -> main)

Add branch.txt in GitNewBranch

* bcd2345 Previous Commit

* cde3456 Initial Commit
```

### Explanation

Options used:

- `--oneline` → compact commit history
- `--graph` → graphical branch structure
- `--decorate` → displays branch pointers

CTS specifically instructs viewing the log after merging. :contentReference[oaicite:9]{index=9}

---

# Step 17: Delete the Branch

```bash
git branch -d GitNewBranch
```

Expected Output

```text
Deleted branch GitNewBranch (was abc1234).
```

### Explanation

After merging:

- GitNewBranch is no longer required.
- Deleting it keeps the repository clean.

CTS instructs deleting the branch after the merge. :contentReference[oaicite:10]{index=10}

---

# Step 18: Verify Remaining Branches

```bash
git branch
```

Expected Output

```text
* main
```

Only the main branch remains.

---

# Step 19: Verify Final Status

```bash
git status
```

Expected Output

```text
On branch main

nothing to commit, working tree clean
```

This confirms:

- Merge completed successfully.
- Repository is synchronized.
- Working directory is clean.

---

# Step 20: Push Changes to GitHub

Finally, upload the merged changes.

```bash
git pull origin main

git push origin main
```

After pushing:

- The main branch on GitHub contains the merged changes.
- The feature branch exists only locally unless it was also pushed separately.

---

# 📊 Git Branching and Merging Workflow

The following diagram illustrates the complete workflow performed during this exercise.

```
                Start
                  │
                  ▼
         Existing Git Repository
                  │
                  ▼
        Create GitNewBranch
                  │
                  ▼
      Switch to GitNewBranch
                  │
                  ▼
         Create / Modify Files
                  │
                  ▼
            git add
                  │
                  ▼
          git commit
                  │
                  ▼
         Switch to main
                  │
                  ▼
      Compare Both Branches
                  │
                  ▼
        Merge GitNewBranch
                  │
                  ▼
      View Commit History
                  │
                  ▼
      Delete GitNewBranch
                  │
                  ▼
       Push Changes to GitHub
                  │
                  ▼
                Finish
```

---

# 📸 Expected Outputs

## Output 1 – Repository Status Before Creating Branch

```text
On branch main

nothing to commit, working tree clean
```

---

## Output 2 – Branch Successfully Created

```text
GitNewBranch
* main
```

The `*` symbol represents the currently active branch.

---

## Output 3 – Switched to GitNewBranch

```text
Switched to branch 'GitNewBranch'
```

---

## Output 4 – New File Created

```text
branch.txt
```

Contents:

```text
This file belongs to GitNewBranch.
```

---

## Output 5 – Git Status

```text
Untracked files:

branch.txt
```

---

## Output 6 – File Staged

```text
Changes to be committed:

new file: branch.txt
```

---

## Output 7 – Commit Created

```text
[GitNewBranch xxxxxx]

Add branch.txt in GitNewBranch
```

---

## Output 8 – Branch Comparison

Executing

```bash
git diff main GitNewBranch
```

shows the differences between the two branches.

---

## Output 9 – Merge Successful

```text
Updating xxxxxx..xxxxxx

Fast-forward

branch.txt | 1 +
```

or

```text
Merge made by the 'ort' strategy.
```

Both indicate a successful merge depending on the repository history. :contentReference[oaicite:0]{index=0}

---

## Output 10 – Commit Graph

```text
* abc1234 (HEAD -> main)

Add branch.txt in GitNewBranch

* bcd2345 Previous Commit

* cde3456 Initial Commit
```

The graph displays the relationship between commits after merging. The CTS hands-on specifically recommends viewing the log using `git log --oneline --graph --decorate`. :contentReference[oaicite:1]{index=1}

---

## Output 11 – Branch Deleted

```text
Deleted branch GitNewBranch (was abc1234).
```

---

## Output 12 – Repository Status

```text
On branch main

nothing to commit, working tree clean
```

This confirms that the merge has completed successfully and the repository is synchronized.

---

# 📂 Final Project Structure

```
CTS_DN-5.0_DeepSkilling/
│
├── Week_1_Engineering_Concepts/
├── Week_2_Spring_Framework/
├── Week_3_Spring_REST/
├── Week_4_Microservices/
├── Week_5_ReactJS_HOL/
└── Week_6_GIT/
    │
    ├── Exercise_1_Git_Basics/
    ├── Exercise_2_Git_Ignore/
    └── Exercise_3_Branching_and_Merging/
        │
        ├── README.md
        └── branch.txt
```

---

# ✅ Verification Checklist

| Task | Status |
|------|:------:|
| Git repository exists | ✅ |
| New branch created | ✅ |
| Switched to new branch | ✅ |
| File created in branch | ✅ |
| File committed | ✅ |
| Switched back to main | ✅ |
| Branches compared | ✅ |
| Branch merged | ✅ |
| Commit graph viewed | ✅ |
| Branch deleted | ✅ |
| Changes pushed to GitHub | ✅ |

---

# ⚠️ Common Errors and Solutions

## 1. Branch Already Exists

**Error**

```text
fatal: A branch named 'GitNewBranch' already exists.
```

**Solution**

List branches:

```bash
git branch
```

Use the existing branch:

```bash
git switch GitNewBranch
```

or delete it:

```bash
git branch -d GitNewBranch
```

---

## 2. Merge Conflict

**Error**

```text
CONFLICT (content): Merge conflict in branch.txt
```

**Reason**

The same portion of a file was modified in both branches.

**Solution**

- Open the conflicted file.
- Resolve the conflict manually.
- Save the file.
- Stage the resolved file.

```bash
git add branch.txt
```

Complete the merge:

```bash
git commit
```

---

## 3. Cannot Delete Branch

**Error**

```text
error: The branch 'GitNewBranch' is not fully merged.
```

**Solution**

Merge it first:

```bash
git merge GitNewBranch
```

or force delete (only if you intentionally want to discard its commits):

```bash
git branch -D GitNewBranch
```

---

## 4. Nothing to Commit

```text
nothing to commit, working tree clean
```

**Meaning**

All changes have already been committed.

---

## 5. Push Rejected

```text
Updates were rejected
```

**Solution**

Pull the latest changes:

```bash
git pull origin main
```

Then push again:

```bash
git push origin main
```

---

# 💡 Best Practices

- Create a separate branch for every new feature.
- Keep the `main` branch stable.
- Use meaningful branch names.
- Commit changes frequently with descriptive messages.
- Merge only after testing the branch.
- Delete merged branches to keep the repository organized.
- Pull the latest changes before starting new work.
- Push regularly to back up your work and collaborate effectively.

---

# 🚀 Advantages of Branching and Merging

- Enables parallel development.
- Keeps the main branch stable.
- Simplifies collaboration among developers.
- Makes feature testing safer.
- Facilitates bug fixes without affecting production code.
- Provides a clear project history.
- Supports multiple development workflows.

---

# ⚠️ Limitations

- Frequent merges can lead to merge conflicts.
- Poor branch naming can create confusion.
- Long-lived branches are harder to merge.
- Improper branch management increases maintenance effort.
- Requires developers to understand Git workflows.

---

# 🌍 Real-World Applications

Git Branching and Merging is widely used in:

- Software product development
- Open-source projects
- Agile and Scrum teams
- Continuous Integration / Continuous Deployment (CI/CD)
- Feature development
- Bug fixing
- Release management
- Code review workflows through Pull Requests or Merge Requests

The CTS exercise also mentions understanding branch creation and merge request concepts as part of the learning objectives. :contentReference[oaicite:2]{index=2}

# 📝 Command Summary

The following commands were used throughout this hands-on exercise.

## Repository Navigation

```bash
cd ~/OneDrive/Desktop/CTS_DN-5.0_DeepSkilling
```

---

## Check Repository Status

```bash
git status
```

---

## Create a New Branch

```bash
git branch GitNewBranch
```

---

## List Available Branches

```bash
git branch
```

or

```bash
git branch -a
```

---

## Switch to the New Branch

```bash
git checkout GitNewBranch
```

or

```bash
git switch GitNewBranch
```

---

## Create a File

```bash
echo "This file belongs to GitNewBranch." > branch.txt
```

---

## Stage the File

```bash
git add branch.txt
```

---

## Commit Changes

```bash
git commit -m "Add branch.txt in GitNewBranch"
```

---

## Switch Back to Main

```bash
git checkout main
```

or

```bash
git switch main
```

---

## Compare Branches

```bash
git diff main GitNewBranch
```

---

## Compare Using P4Merge (Optional)

```bash
git difftool main GitNewBranch
```

---

## Merge Branch

```bash
git merge GitNewBranch
```

---

## View Commit Graph

```bash
git log --oneline --graph --decorate
```

---

## Delete Branch

```bash
git branch -d GitNewBranch
```

---

## Push Changes

```bash
git pull origin main

git push origin main
```

---

# 📖 Important Git Commands Used

| Command | Purpose |
|---------|---------|
| `git branch` | Create or list branches |
| `git checkout` | Switch branches |
| `git switch` | Modern command to switch branches |
| `git add` | Stage files |
| `git commit` | Save changes in the local repository |
| `git diff` | Compare branches or files |
| `git difftool` | Visual comparison using an external tool |
| `git merge` | Merge one branch into another |
| `git log` | Display commit history |
| `git branch -d` | Delete a merged branch |
| `git push` | Upload commits to GitHub |

---

# 🎓 Interview Questions

## 1. What is a Git Branch?

A Git branch is an independent line of development that allows developers to work on new features or bug fixes without affecting the main branch.

---

## 2. Why do we create branches?

Branches are created to:

- develop new features
- fix bugs
- experiment safely
- collaborate with multiple developers
- protect the stable version of the project

---

## 3. What is the difference between `git branch` and `git checkout`?

`git branch`

- Creates or lists branches.

Example

```bash
git branch GitNewBranch
```

`git checkout`

- Switches to another branch.

Example

```bash
git checkout GitNewBranch
```

---

## 4. What is the purpose of `git merge`?

`git merge` combines changes from one branch into another while preserving the commit history.

Example

```bash
git merge GitNewBranch
```

---

## 5. What is a Fast-Forward Merge?

A Fast-Forward Merge occurs when the destination branch has no additional commits. Git simply moves the branch pointer forward without creating a new merge commit.

---

## 6. What is a Three-Way Merge?

A Three-Way Merge occurs when both branches have different commits. Git creates an additional merge commit to combine the histories.

---

## 7. What is a Merge Conflict?

A merge conflict occurs when two branches modify the same section of a file differently. Git cannot automatically determine which version to keep and requires manual resolution.

---

## 8. Why should merged branches be deleted?

Deleting merged branches:

- keeps the repository clean
- avoids confusion
- reduces unnecessary branches
- simplifies repository management

---

## 9. How can you view the commit graph?

```bash
git log --oneline --graph --decorate
```

This command displays a compact graphical representation of the commit history.

---

## 10. What is P4Merge?

P4Merge is a graphical comparison and merge tool that visually displays differences between files and assists in resolving merge conflicts. The CTS hands-on recommends using it for visual branch comparison if it is configured. :contentReference[oaicite:0]{index=0}

---

# 📌 Key Takeaways

- Git branches enable isolated development without affecting the main codebase.
- Developers can create multiple branches for features, bug fixes, or experiments.
- Changes made in a branch remain independent until merged.
- Git Merge integrates completed work into the main branch.
- `git diff` helps compare branches before merging.
- `git log --oneline --graph --decorate` provides a clear visualization of the commit history.
- Deleting merged branches keeps the repository organized and easier to maintain.
- Branching and merging are essential workflows in collaborative software development.

---

# 📚 References

1. CTS Deep Skilling – Git Hands-on Exercise 3 (Branching and Merging). :contentReference[oaicite:1]{index=1}
2. Git Official Documentation – Branching and Merging:
   https://git-scm.com/book/en/v2/Git-Branching-Branches-in-a-Nutshell
3. Git Official Documentation – Merge:
   https://git-scm.com/docs/git-merge
4. Git Official Documentation – Branch:
   https://git-scm.com/docs/git-branch

---

# 🎉 Conclusion

In this exercise, Git Branching and Merging were successfully implemented by creating a new branch, making changes independently, committing those changes, comparing the branch with the main branch, merging the completed work into the main branch, and deleting the temporary branch. This workflow demonstrates one of Git's most important collaboration features and reflects how professional development teams safely develop, review, and integrate code changes. The hands-on also introduces the concept of using a visual comparison tool (P4Merge) and emphasizes observing the commit graph after merging, providing a practical understanding of Git's branching model. :contentReference[oaicite:2]{index=2}
