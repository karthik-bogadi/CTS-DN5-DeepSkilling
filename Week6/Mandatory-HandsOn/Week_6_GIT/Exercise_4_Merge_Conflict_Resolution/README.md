# Exercise 4 - Merge Conflict Resolution

## 📌 Introduction

Git allows multiple developers to work on the same project simultaneously through branching. However, when different branches modify the same file or the same lines of code, Git may be unable to automatically combine those changes during a merge. This situation is called a **merge conflict**.

A merge conflict must be resolved manually or with a merge tool before the merge or rebase can be completed. Resolving conflicts correctly is an essential skill for software developers because collaborative development frequently involves multiple contributors working on shared files.

In this hands-on exercise, a new branch is created, changes are made to the same file in both the branch and the main branch, a merge conflict is intentionally generated, the conflict is resolved using Git's merge process (or a 3-way merge tool such as P4Merge), backup files are ignored using `.gitignore`, and the merged branch is deleted. This exercise demonstrates the complete workflow for handling merge conflicts in Git. 

---

# 🎯 Learning Objectives

After completing this exercise, you will be able to:

* Understand what a Git merge conflict is.
* Learn why merge conflicts occur.
* Create a branch for development.
* Modify the same file in different branches.
* Generate a merge conflict intentionally.
* Compare branch differences using Git.
* Resolve merge conflicts manually.
* Use a 3-way merge tool such as P4Merge.
* Complete a merge after resolving conflicts.
* Ignore backup files using `.gitignore`.
* Delete merged branches after successful integration.

These objectives are derived from the CTS Git Hands-on Exercise 4. 

---

# 📋 Prerequisites

Before starting this exercise, ensure the following requirements are satisfied:

* Git is installed on your system.
* Git Bash is configured.
* A local Git repository already exists.
* GitHub repository is connected as the remote repository.
* Exercises 1–3 have been completed.
* Basic knowledge of Git branching and merging.
* P4Merge is installed if graphical conflict resolution is required.

These prerequisites are mentioned in the CTS Hands-on document. 

---

# 📖 Background Concepts

## What is a Merge Conflict?

A **merge conflict** occurs when Git cannot automatically merge changes from two branches because both branches have modified the same section of a file differently.

Instead of choosing one version automatically, Git pauses the merge and asks the developer to resolve the conflict manually.

Example:

**Main Branch**

```xml
<message>Hello from Main Branch</message>
```

**GitWork Branch**

```xml
<message>Hello from GitWork Branch</message>
```

Since both branches changed the same line, Git cannot determine which version should be kept.

---

## Why Do Merge Conflicts Occur?

Merge conflicts commonly occur when:

* Two developers edit the same line of a file.
* One branch deletes a file while another modifies it.
* The same configuration file is changed differently in multiple branches.
* Long-lived branches diverge significantly before merging.

---

## What are Conflict Markers?

When Git detects a conflict, it inserts conflict markers into the affected file.

Example:

```xml
<<<<<<< HEAD
<message>Updated from Main Branch</message>
=======
<message>Updated from GitWork Branch</message>
>>>>>>> GitWork
```

Meaning:

* `<<<<<<< HEAD` → Current branch (`main`)
* `=======` → Separator between conflicting versions
* `>>>>>>> GitWork` → Incoming changes from the `GitWork` branch

These markers must be removed during conflict resolution.

---

## What is a 3-Way Merge?

A **3-way merge** compares three versions of a file:

1. **Base** – The common ancestor.
2. **Local** – The current branch (`main`).
3. **Remote** – The branch being merged (`GitWork`).

Using these three versions, Git or a merge tool helps combine the changes into a single final version.

---

## Manual vs Tool-Based Conflict Resolution

### Manual Resolution

* Open the conflicted file.
* Remove the conflict markers.
* Keep the desired content.
* Save the file.
* Stage and commit the resolved file.

### Tool-Based Resolution

Using tools such as **P4Merge**, **Meld**, or **VS Code Merge Editor**, developers can visually compare changes and choose which version to keep or combine.

The CTS exercise recommends using **P4Merge** as the 3-way merge tool for better visualization. 

---

# ⚙️ How Git Handles a Merge Conflict

```
Create Branch
      │
      ▼
Modify File in Branch
      │
      ▼
Commit Changes
      │
      ▼
Switch to Main
      │
      ▼
Modify Same File
      │
      ▼
Commit Changes
      │
      ▼
Merge Branch
      │
      ▼
Conflict Detected
      │
      ▼
Resolve Conflict
      │
      ▼
Stage Resolved File
      │
      ▼
Complete Merge
```

---

# 📂 Project Structure

```text
CTS_DN-5.0_DeepSkilling/
│
└── Week_6_GIT/
    │
    └── Exercise_4_Merge_Conflict_Resolution/
        │
        ├── README.md
        ├── hello.xml
        ├── .gitignore
        ├── Exercise_4_Merge_Conflicts_and_Resolutions.pdf
        └── Screenshots/
```

---

# 🛠 Technologies Used

* Git
* Git Bash
* GitHub
* Visual Studio Code
* P4Merge (Optional)
* Windows Command Prompt / PowerShell

---

# 🌟 Key Concepts Covered

* Git Branching
* Merge Conflicts
* Conflict Markers
* Manual Conflict Resolution
* 3-Way Merge
* Git Diff
* Git Difftool
* Git Rebase (if applicable)
* Git Ignore
* Commit History
* Branch Management

---

# ⚙️ Implementation

This section demonstrates the complete implementation of Merge Conflict Resolution as performed in this CTS Git Hands-on Exercise. The workflow includes creating a branch, modifying the same file in two different branches, generating a merge conflict, resolving the conflict manually (or using P4Merge), committing the resolved changes, ignoring backup files, deleting the merged branch, and viewing the final commit history. These implementation steps follow the CTS Hands-on Exercise 4. :contentReference[oaicite:0]{index=0}

---

# Step 1: Open Git Bash

Launch **Git Bash** from your system.

---

# Step 2: Navigate to the Repository

Navigate to your existing Git repository.

```bash
cd ~/OneDrive/Desktop/CTS_DN-5.0_DeepSkilling
```

Verify that the repository is in a clean state.

```bash
git status
```

### Expected Output

```text
On branch main

nothing to commit, working tree clean
```

CTS requires verifying that the master (main) branch is in a clean state before starting the exercise. :contentReference[oaicite:1]{index=1}

---

# Step 3: Create a New Branch

Create a branch named **GitWork**.

```bash
git branch GitWork
```

### Explanation

This command creates a new development branch from the current branch without changing your current working branch.

CTS specifies creating a branch named **GitWork**. :contentReference[oaicite:2]{index=2}

---

# Step 4: Switch to the Branch

Move to the newly created branch.

```bash
git checkout GitWork
```

or

```bash
git switch GitWork
```

### Expected Output

```text
Switched to branch 'GitWork'
```

CTS requires switching to the newly created branch before making changes. :contentReference[oaicite:3]{index=3}

---

# Step 5: Create hello.xml

Create a new XML file.

```bash
echo "<message>Hello from GitWork</message>" > hello.xml
```

Verify its contents.

```bash
cat hello.xml
```

### Expected Output

```xml
<message>Hello from GitWork</message>
```

CTS specifies creating a file named **hello.xml** in the GitWork branch. :contentReference[oaicite:4]{index=4}

---

# Step 6: Update hello.xml

Replace the existing content.

```bash
echo "<message>Updated from GitWork Branch</message>" > hello.xml
```

Check the repository status.

```bash
git status
```

### Expected Output

```text
Untracked files:

hello.xml
```

### Explanation

Since **hello.xml** is a newly created file and has not yet been committed, Git identifies it as an **Untracked** file. This is the expected behavior until the file is added to the staging area.

CTS requires updating the file and observing the status. :contentReference[oaicite:5]{index=5}

---

# Step 7: Commit the Branch Changes

Stage the file.

```bash
git add hello.xml
```

Commit the changes.

```bash
git commit -m "Update hello.xml in GitWork branch"
```

### Expected Output

```text
[GitWork xxxxxx]

Update hello.xml in GitWork branch
```

CTS requires committing the changes made in the branch. :contentReference[oaicite:6]{index=6}

---

# Step 8: Switch Back to Main Branch

```bash
git checkout main
```

or

```bash
git switch main
```

### Expected Output

```text
Switched to branch 'main'
```

CTS requires switching back to the master (main) branch before creating conflicting changes. :contentReference[oaicite:7]{index=7}

---

# Step 9: Modify hello.xml in Main

Create the same file with different content.

```bash
echo "<message>Hello from Main Branch</message>" > hello.xml
```

Update it.

```bash
echo "<message>Updated from Main Branch</message>" > hello.xml
```

Verify the contents.

```bash
cat hello.xml
```

### Expected Output

```xml
<message>Updated from Main Branch</message>
```

CTS specifies adding the same file with different content in the master branch to intentionally create a merge conflict. :contentReference[oaicite:8]{index=8}

---

# Step 10: Commit the Main Branch Changes

Stage the modified file.

```bash
git add hello.xml
```

Commit the changes.

```bash
git commit -m "Update hello.xml in main branch"
```

### Expected Output

```text
[main xxxxxx]

Update hello.xml in main branch
```

---

# Step 11: View Commit History

Display the complete commit graph.

```bash
git log --oneline --graph --decorate --all
```

### Example Output

```text
* abc1234 (HEAD -> main)
* def5678 (GitWork)
* ghi9012 Initial Commit
```

CTS requires viewing the commit history before merging. :contentReference[oaicite:9]{index=9}

---

# Step 12: Compare Both Branches

Compare the differences between the two branches.

```bash
git diff main GitWork
```

Git displays all line-by-line differences.

CTS requires checking the differences using Git Diff. :contentReference[oaicite:10]{index=10}

---

# Step 13: Compare Using P4Merge (Optional)

If P4Merge is installed and configured:

```bash
git difftool main GitWork
```

This opens a graphical comparison window where differences between both branches can be viewed visually.

CTS recommends using **P4Merge** for better visualization. :contentReference[oaicite:11]{index=11}

---

# Step 14: Merge the Branch

Merge **GitWork** into **main**.

```bash
git merge GitWork
```

### Expected Output

```text
Auto-merging hello.xml

CONFLICT (content): Merge conflict in hello.xml

Automatic merge failed; fix conflicts and then commit the result.
```

The merge conflict occurs because both branches modified the same file differently.

CTS intentionally creates this conflict as part of the exercise. :contentReference[oaicite:12]{index=12}

---

# Step 15: Observe the Conflict Markers

Open the conflicted file.

```bash
cat hello.xml
```

Git inserts conflict markers similar to:

```xml
<<<<<<< HEAD
<message>Updated from Main Branch</message>
=======
<message>Updated from GitWork Branch</message>
>>>>>>> GitWork
```

These markers indicate the conflicting changes from both branches.

CTS requires observing the Git markup generated during the conflict. :contentReference[oaicite:13]{index=13}

---

# Step 16: Resolve the Conflict

Open the file in Visual Studio Code.

```bash
code hello.xml
```

Remove all conflict markers:

```text
<<<<<<< HEAD

=======

>>>>>>> GitWork
```

Keep the final content.

```xml
<message>Updated from Main Branch and GitWork Branch</message>
```

Save the file.

If P4Merge is configured, the same conflict can be resolved using its **3-way merge interface**.

CTS specifically instructs resolving the conflict using a 3-way merge tool. :contentReference[oaicite:14]{index=14}

---

# Step 17: Stage the Resolved File

```bash
git add hello.xml
```

This tells Git that the conflict has been resolved.

---

# Step 18: Complete the Merge

```bash
git commit -m "Resolve merge conflict in hello.xml"
```

### Expected Output

```text
[main xxxxxx]

Resolve merge conflict in hello.xml
```

CTS requires committing the resolved merge conflict. :contentReference[oaicite:15]{index=15}

---

# Step 19: Ignore Backup Files

If your merge tool creates backup files (for example, `hello.xml.orig`), create or edit the `.gitignore` file.

```text
*.orig
```

Stage the `.gitignore`.

```bash
git add .gitignore
```

Commit the change.

```bash
git commit -m "Ignore backup files"
```

CTS instructs adding backup files to `.gitignore` after resolving the conflict. :contentReference[oaicite:16]{index=16}

---

# Step 20: List Available Branches

```bash
git branch
```

### Example Output

```text
GitWork
* main
```

CTS requires listing all available branches. :contentReference[oaicite:17]{index=17}

---

# Step 21: Delete the Merged Branch

```bash
git branch -d GitWork
```

### Expected Output

```text
Deleted branch GitWork (was abc1234).
```

CTS requires deleting the merged branch after successful integration. :contentReference[oaicite:18]{index=18}

---

# Step 22: View Final Commit History

Display the final commit graph.

```bash
git log --oneline --graph --decorate
```

### Example Output

```text
* 9ab4567 (HEAD -> main) Resolve merge conflict in hello.xml
* 8cd3456 Merge branch 'GitWork'
* 7ef2345 Update hello.xml in main branch
* 6de1234 Update hello.xml in GitWork branch
* 5bc0123 Initial Commit
```

This confirms that the merge conflict has been resolved successfully and the repository history now contains the merged changes.

CTS concludes the exercise by viewing the commit history after resolving the conflict. :contentReference[oaicite:19]{index=19}

# 📊 Merge Conflict Resolution Workflow

The following diagram illustrates the complete workflow performed during this exercise.

```
                 Start
                   │
                   ▼
        Verify Repository Status
                   │
                   ▼
         Create GitWork Branch
                   │
                   ▼
        Switch to GitWork Branch
                   │
                   ▼
        Create hello.xml File
                   │
                   ▼
      Update hello.xml Content
                   │
                   ▼
          Commit Branch Changes
                   │
                   ▼
          Switch to Main Branch
                   │
                   ▼
 Modify the Same hello.xml File
                   │
                   ▼
         Commit Main Changes
                   │
                   ▼
      Compare Both Branches
                   │
                   ▼
        Merge GitWork Branch
                   │
                   ▼
        Merge Conflict Occurs
                   │
                   ▼
      Resolve Conflict Manually
          or Using P4Merge
                   │
                   ▼
       Stage Resolved File
                   │
                   ▼
      Commit Merge Resolution
                   │
                   ▼
     Ignore Backup Files
                   │
                   ▼
      Delete GitWork Branch
                   │
                   ▼
      View Final Commit History
                   │
                   ▼
                Finish
```

---

# 📸 Expected Outputs

## Output 1 – Clean Repository

```text
On branch main

nothing to commit, working tree clean
```

This confirms that the repository is ready for the exercise.

---

## Output 2 – Branch Created

```text
GitWork
* main
```

The `*` symbol indicates the currently active branch.

---

## Output 3 – Switched to GitWork

```text
Switched to branch 'GitWork'
```

---

## Output 4 – hello.xml Created

```xml
<message>Hello from GitWork</message>
```

---

## Output 5 – Repository Status

```text
Untracked files:

hello.xml
```

Since **hello.xml** has not yet been committed, Git reports it as an untracked file.

---

## Output 6 – Commit Created

```text
[GitWork xxxxxx]

Update hello.xml in GitWork branch
```

The file is now tracked by Git.

---

## Output 7 – Main Branch Updated

```xml
<message>Updated from Main Branch</message>
```

---

## Output 8 – Commit History

```text
* abc1234 (HEAD -> main)

* def5678 (GitWork)

* ghi9012 Initial Commit
```

CTS requires viewing the commit graph before performing the merge. :contentReference[oaicite:0]{index=0}

---

## Output 9 – Git Diff

Executing

```bash
git diff main GitWork
```

displays the differences between both branches.

CTS instructs comparing the branches before merging. :contentReference[oaicite:1]{index=1}

---

## Output 10 – Merge Conflict

Running

```bash
git merge GitWork
```

produces:

```text
Auto-merging hello.xml

CONFLICT (content): Merge conflict in hello.xml

Automatic merge failed; fix conflicts and then commit the result.
```

CTS intentionally creates this conflict to demonstrate conflict resolution. :contentReference[oaicite:2]{index=2}

---

## Output 11 – Conflict Markers

Git inserts conflict markers.

```xml
<<<<<<< HEAD
<message>Updated from Main Branch</message>
=======
<message>Updated from GitWork Branch</message>
>>>>>>> GitWork
```

These markers identify conflicting sections that require manual resolution.

---

## Output 12 – Conflict Resolved

After editing:

```xml
<message>Updated from Main Branch and GitWork Branch</message>
```

The conflict markers have been removed and the desired content has been retained.

---

## Output 13 – Merge Commit

```text
[main xxxxxx]

Resolve merge conflict in hello.xml
```

This confirms that the merge conflict has been successfully resolved.

---

## Output 14 – Branch Deleted

```text
Deleted branch GitWork (was abc1234).
```

---

## Output 15 – Final Repository Status

```text
On branch main

nothing to commit, working tree clean
```

The repository is synchronized and all conflicts have been resolved.

---

# 📂 Final Project Structure

```text
CTS_DN-5.0_DeepSkilling/
│
├── Week_1_Engineering_Concepts/
├── Week_2_Spring_Framework/
├── Week_3_Spring_REST/
├── Week_4_Microservices/
├── Week_5_ReactJS_HOL/
└── Week_6_GIT/
    │
    └── Exercise_4_Merge_Conflict_Resolution/
        │
        ├── README.md
        ├── hello.xml
        ├── .gitignore
        ├── Exercise_4_Merge_Conflicts_and_Resolutions.pdf
        └── Screenshots/
```

---

# ✅ Verification Checklist

| Task | Status |
|------|:------:|
| Repository verified | ✅ |
| GitWork branch created | ✅ |
| Switched to GitWork | ✅ |
| hello.xml created | ✅ |
| Branch changes committed | ✅ |
| Main branch updated | ✅ |
| Branches compared | ✅ |
| Merge conflict generated | ✅ |
| Conflict resolved | ✅ |
| Merge committed | ✅ |
| Backup files ignored | ✅ |
| Branch deleted | ✅ |
| Commit history viewed | ✅ |
| Changes pushed to GitHub | ✅ |

---

# ⚠️ Common Errors and Solutions

## 1. Branch Already Exists

**Error**

```text
fatal: A branch named 'GitWork' already exists.
```

**Solution**

Check existing branches.

```bash
git branch
```

Switch to the existing branch.

```bash
git switch GitWork
```

---

## 2. Merge Conflict

**Error**

```text
CONFLICT (content): Merge conflict in hello.xml
```

**Reason**

Both branches modified the same file differently.

**Solution**

- Open `hello.xml`.
- Remove the conflict markers.
- Keep the required content.
- Save the file.
- Stage the file.

```bash
git add hello.xml
```

Complete the merge.

```bash
git commit
```

---

## 3. Rebase Conflict

**Error**

```text
interactive rebase in progress
```

**Solution**

Resolve the conflict.

```bash
git add hello.xml
```

Continue the rebase.

```bash
git rebase --continue
```

If required:

```bash
git rebase --abort
```

---

## 4. Push Rejected

**Error**

```text
non-fast-forward
```

**Solution**

Synchronize with the remote repository.

```bash
git pull origin main --rebase
```

Resolve any conflicts.

Then push again.

```bash
git push origin main
```

---

## 5. Backup Files Created

Some merge tools generate backup files like:

```text
hello.xml.orig
```

Ignore them by adding:

```text
*.orig
```

to `.gitignore`.

CTS specifically instructs ignoring backup files after conflict resolution. :contentReference[oaicite:3]{index=3}

---

# 💡 Best Practices

- Pull the latest changes before starting work.
- Create a separate branch for each feature.
- Keep commits small and meaningful.
- Merge frequently to reduce conflicts.
- Review changes using `git diff` before merging.
- Resolve conflicts carefully rather than deleting code blindly.
- Test the project after resolving conflicts.
- Delete merged branches to keep the repository clean.
- Use graphical merge tools such as P4Merge when working with complex conflicts.

---

# 🚀 Advantages of Merge Conflict Resolution

- Prevents accidental loss of code.
- Enables safe collaboration among developers.
- Maintains project consistency.
- Preserves complete commit history.
- Improves code quality through manual review.
- Supports large development teams.
- Encourages structured version control practices.

---

# ⚠️ Limitations

- Resolving conflicts can be time-consuming.
- Large conflicts require careful analysis.
- Long-lived branches increase the likelihood of conflicts.
- Incorrect conflict resolution may introduce bugs.
- Requires developers to understand Git workflows and merge strategies.

---

# 🌍 Real-World Applications

Merge conflict resolution is commonly used in:

- Enterprise software development
- Open-source projects
- Agile and Scrum development teams
- CI/CD pipelines
- DevOps workflows
- Code review and Pull Request processes
- Large collaborative development environments

The CTS exercise focuses on creating, resolving, and understanding merge conflicts as part of collaborative Git workflows. :contentReference[oaicite:4]{index=4}

# 📝 Command Summary

The following Git commands were used throughout this Merge Conflict Resolution exercise.

---

## Navigate to the Repository

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
git branch GitWork
```

---

## Switch to GitWork Branch

```bash
git checkout GitWork
```

or

```bash
git switch GitWork
```

---

## Create hello.xml

```bash
echo "<message>Hello from GitWork</message>" > hello.xml
```

---

## Update hello.xml

```bash
echo "<message>Updated from GitWork Branch</message>" > hello.xml
```

---

## Stage the File

```bash
git add hello.xml
```

---

## Commit Branch Changes

```bash
git commit -m "Update hello.xml in GitWork branch"
```

---

## Switch Back to Main Branch

```bash
git checkout main
```

or

```bash
git switch main
```

---

## Update hello.xml in Main Branch

```bash
echo "<message>Hello from Main Branch</message>" > hello.xml

echo "<message>Updated from Main Branch</message>" > hello.xml
```

---

## Commit Main Branch Changes

```bash
git add hello.xml

git commit -m "Update hello.xml in main branch"
```

---

## View Commit History

```bash
git log --oneline --graph --decorate --all
```

---

## Compare Branches

```bash
git diff main GitWork
```

---

## Compare Using P4Merge (Optional)

```bash
git difftool main GitWork
```

---

## Merge GitWork into Main

```bash
git merge GitWork
```

---

## Resolve Conflict

Open the conflicted file.

```bash
code hello.xml
```

Remove the conflict markers.

---

## Stage the Resolved File

```bash
git add hello.xml
```

---

## Complete the Merge

```bash
git commit -m "Resolve merge conflict in hello.xml"
```

---

## Ignore Backup Files

```bash
git add .gitignore

git commit -m "Ignore backup files"
```

---

## List Available Branches

```bash
git branch
```

---

## Delete the Merged Branch

```bash
git branch -d GitWork
```

---

## View Final Commit History

```bash
git log --oneline --graph --decorate
```

---

## Push Changes to GitHub

```bash
git pull origin main

git push origin main
```

---

# 📖 Important Git Commands Used

| Command | Purpose |
|----------|---------|
| `git branch` | Create or list branches |
| `git checkout` | Switch branches |
| `git switch` | Modern command to switch branches |
| `git status` | Display repository status |
| `git add` | Stage files |
| `git commit` | Save staged changes |
| `git diff` | Compare differences between branches |
| `git difftool` | Open graphical comparison tool |
| `git merge` | Merge one branch into another |
| `git log` | Display commit history |
| `git branch -d` | Delete merged branch |
| `git push` | Upload commits to GitHub |
| `git rebase --continue` | Continue a rebase after resolving conflicts |

---

# 🎓 Interview Questions

## 1. What is a Git Merge Conflict?

A merge conflict occurs when Git cannot automatically merge changes because two branches have modified the same portion of a file differently.

---

## 2. Why do merge conflicts occur?

Merge conflicts occur when:

- Two developers edit the same file.
- Both branches modify the same lines.
- One branch deletes a file while another modifies it.
- Branches remain separate for a long period before merging.

---

## 3. How does Git identify a merge conflict?

Git inserts conflict markers into the file.

Example:

```text
<<<<<<< HEAD
=======
>>>>>>> GitWork
```

These markers indicate the conflicting sections that require manual resolution.

---

## 4. How do you resolve a merge conflict manually?

1. Open the conflicted file.
2. Remove all conflict markers.
3. Keep the desired content.
4. Save the file.
5. Stage the file.

```bash
git add hello.xml
```

6. Complete the merge.

```bash
git commit
```

---

## 5. What is a 3-Way Merge?

A 3-way merge compares:

- Base version
- Local version
- Incoming version

It helps Git or a merge tool determine how to combine changes from both branches.

---

## 6. What is P4Merge?

P4Merge is a graphical merge and comparison tool that displays the Base, Local, Remote, and Result versions of a file, making conflict resolution easier. The CTS hands-on recommends using P4Merge for visual conflict resolution. :contentReference[oaicite:0]{index=0}

---

## 7. Why should backup files be ignored?

Merge tools may create backup files such as:

```text
hello.xml.orig
```

These files are temporary and should not be committed to the repository. They can be ignored using `.gitignore`.

---

## 8. How can you check the differences between two branches?

Using Git Diff:

```bash
git diff main GitWork
```

or visually using:

```bash
git difftool main GitWork
```

---

## 9. How can you view the complete commit graph?

```bash
git log --oneline --graph --decorate --all
```

This command displays branch history, merge commits, and branch pointers.

---

## 10. Why should merged branches be deleted?

Deleting merged branches:

- keeps the repository clean
- reduces confusion
- improves repository management
- removes branches that are no longer required

---

# 📌 Key Takeaways

- Merge conflicts are a normal part of collaborative software development.
- Git cannot automatically merge changes when the same section of a file has been modified differently.
- Conflict markers help identify the conflicting sections.
- Conflicts can be resolved manually or with graphical merge tools such as P4Merge.
- After resolving conflicts, the file must be staged before completing the merge.
- Backup files generated during merging should be ignored using `.gitignore`.
- Viewing the commit graph helps understand the repository history after merging.
- Deleting merged branches keeps the project organized.

---

# 📚 References

1. CTS Deep Skilling – Git Hands-on Exercise 4 (Merge Conflict Resolution). :contentReference[oaicite:1]{index=1}
2. Git Official Documentation – Merge:
   https://git-scm.com/docs/git-merge
3. Git Official Documentation – Branching:
   https://git-scm.com/book/en/v2/Git-Branching-Branches-in-a-Nutshell
4. Git Official Documentation – Git Diff:
   https://git-scm.com/docs/git-diff
5. Git Official Documentation – Git Rebase:
   https://git-scm.com/docs/git-rebase

---

# 🎉 Conclusion

In this exercise, a merge conflict was intentionally created by modifying the same file in both the **GitWork** branch and the **main** branch. The conflict was analyzed, the Git-generated conflict markers were examined, and the conflict was resolved either manually or by using a 3-way merge tool such as **P4Merge**. After resolving the conflict, the changes were committed, backup files were ignored using `.gitignore`, the merged branch was deleted, and the final commit history was verified. This exercise demonstrates an essential Git workflow used by development teams to safely integrate concurrent changes while maintaining the integrity of the project's version history. :contentReference[oaicite:2]{index=2}
