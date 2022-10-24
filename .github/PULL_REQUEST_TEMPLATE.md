PR Description

- [ ] Manual testing on Windows
- [ ] Manual testing on Linux
- [ ] Manual testing on OSX

Additional build commands:
!executeIT -- Execute integration and end to end tests (integration tests are skipped by default)
!zip --  Build .zip archives (zip archives are not packaged by default)
!sign -- Codesign is performed
!skipTests -- Skip all tests execution (unit tests are executed by default)
!mvnArgs -- Add arguments to the mvn command line of the build (eg: !mvnArgs=-DfailIfNoTests=false)

closes [#JIRA_ISSUE_ID](https://bonitasoft.atlassian.net/browse/#JIRA_ISSUE_ID)
