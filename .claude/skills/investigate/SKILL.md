---
name: investigate
description: "Structured investigation of a bug or unexpected behavior in the Bonita Studio codebase. Use when the user reports a symptom, a regression, or an unexpected behavior and wants to understand the root cause before fixing anything."
argument-hint: "[description du symptome]"
---

# Investigate

Structured investigation of a bug or unexpected behavior in the Bonita Studio codebase.

## Input

The user describes a symptom, a code review finding, or a suspected issue.
Examples:
- Bug report: "unchecked JARs still appear in the BAR", "wizard crashes on Finish click"
- Code review finding: "this method calls getChild() 6 times per fragment — performance issue"
- Suspected regression: "this refactoring may have changed behavior"

## Protocol

### Phase 0: Confirm the issue is real (false positive detection)
Before any investigation, actively try to **disprove** the reported issue:

**For bug reports:**
- Is the behavior documented/expected?
- Do existing tests validate this behavior?

**For code review findings / performance claims:**
- **Trace the actual execution path** — don't trust the reviewer's description. Read the code yourself.
- **Check for caching** — does the called method cache its result? (lazy init fields, memoization, singletons)
- **Check for short-circuits** — does the code path actually reach the expensive operation, or does it exit early?
- **Distinguish similar-looking paths** — two methods with similar names may have very different costs (e.g., `getChildren()` does Maven resolution but `getRuntimeDependencies()` is a cached map lookup)
- **Verify the blast radius** — is there actually a caller that triggers the problematic path? Check with `grep` for callers, subclasses, overrides.

**For copyright/date/naming issues:**
- **Check git history** — use `git log --diff-filter=A` to find when the file was truly created. A file "new in this PR" may be a restoration of an old file.
- **Check the current date** — don't assume the year from context. Verify.

If the issue is a **false positive**, state it clearly with evidence and mark it [FALSE POSITIVE]. Do NOT proceed to fix phases.

NEVER attempt a fix before confirming the issue is real.

### Phase 1: Understand before acting
1. **Restate** the symptom in one clear sentence
2. **List 3+ hypotheses** for possible root causes
3. For each hypothesis, describe the command/read that will verify it
4. **Make NO code changes** during this phase

### Phase 2: Trace the data flow
Identify and document the complete code path involved:

```
Entry point (UI/Handler/Command)
  -> Service/Manager called
    -> Store/Repository accessed
      -> Operation performed (export, build, save...)
        -> Result produced
```

Use Grep and Read to trace each step. Document every file and method traversed.

### Phase 3: Isolate the cause
- Update each hypothesis with [CONFIRMED] / [ELIMINATED] / [FALSE POSITIVE] / [NEEDS MORE]
- If all initial hypotheses are eliminated, formulate new ones based on findings
- **If the issue turns out to be a false positive**: stop here. Explain WHY with code evidence. Do NOT proceed to Phase 4.
- **If the bug is not reproducible or evidence is inconclusive**: state it clearly, explain what was tried, and ask the user for more context (logs, steps to reproduce, environment)
- Converge on ONE root cause with concrete evidence (source code, logs, observed behavior)

### Phase 4: Propose the fix
- Explain the root cause and the full chain leading to the bug
- Propose the MINIMAL fix needed
- Indicate the exact files and lines to modify
- **Wait for user validation** before editing anything

## Output format

```
## Symptom
[One-sentence restatement of the reported bug]

## Hypotheses
| # | Hypothesis | Verification | Verdict |
|---|-----------|--------------|---------|
| 1 | ... | ... | [CONFIRMED] / [ELIMINATED] / [FALSE POSITIVE] |

## Verdict: REAL ISSUE / FALSE POSITIVE
[If FALSE POSITIVE: explain why with code evidence, then STOP — no root cause / fix sections needed]

## Root cause (only if REAL ISSUE)
[Explanation with evidence: file:line references, code snippets]

## Data flow (only if REAL ISSUE)
Entry point -> ... -> Bug location -> Incorrect result

## Proposed fix (only if REAL ISSUE)
- **File**: `path/to/File.java:42`
- **Change**: [description of the minimal fix]

## Confidence assessment
| Aspect | Confidence | Evidence |
|--------|-----------|----------|
| Symptom understood | X% | [what confirms it] |
| Root cause identified | X% | [file:line, code proof] |
| All code paths covered | X% | [which paths verified, which not] |
| Edge cases considered | X% | [which checked, which remain] |
| Fix completeness | X% | [what could be missed] |
| **Overall** | **X%** | |

If any aspect is below 80%: explicitly state what is missing and what action would raise confidence (reproduce, read specific file, check logs, ask user).
```

## Rules

- Architecture: `RepositoryManager` -> `IRepositoryStore` -> `IRepositoryFileStore`
- Community = `org.bonitasoft.studio.*`, Subscription = `*.ex` or `*.sp`
- If the bug is in a different code path than initially suspected, explain WHY
- Do not propose refactoring outside the scope of the bug
- Always check both sides: community AND subscription if relevant
- New test code must use JUnit 5 (existing modules may still use JUnit 4)
- **False positive bias**: Assume the issue might be a false positive until proven otherwise. It is better to correctly identify a false positive than to propose an unnecessary fix.
- **No speculation**: Every claim must be backed by a file:line reference. If you can't find evidence, say "unverified" not "likely".
- **Check caching/lazy-init**: Before claiming a performance issue, always check if the called method caches its result (look for `if (field == null) { field = ... }` patterns).
- **Check git history for context**: Before claiming a file is "new", check `git log --diff-filter=A`. Before claiming a copyright year is wrong, verify the actual creation date.
- **NEVER apply fixes** — only propose them. Wait for user validation.
- **Always end with the confidence assessment table** — never skip it. If confidence is low on any axis, explicitly list what would raise it before proposing a fix. Do NOT propose a fix if overall confidence is below 70%.
