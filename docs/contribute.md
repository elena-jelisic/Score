# Contributing Guideline

## Content
- [Development environment requirement](https://github.com/OAGi/Score/wiki/Getting-Score-develop-environments-with-Docker,-Node.js,-and-JDK.)
- Learn about Score
  - [Read installation guide and component architecture](https://github.com/OAGi/Score/wiki/Basic-Installation-Guide-using-Docker-for-Score-Application-Release-1.1.2-and-up)
  - Overview of database structure
  - Overview of code structure
  - How to initialize database with OAGIS data.
  - Overview of test case document
  - Read user guide or at least read its content.
- How to contribute
   - Create an issue if one does not exist
   - Discuss issue in the Score meeting
      -  If issue is accepted,  
         -  assign label bug, enhancement, or design change
      - If issue is not accepted, then close issue.
  - When start working on the issue.
     - Assign milestone and project
     - Once entered a project, the issue is placed on "To DO" card. 
   - After that the issue may traverse through the follow cards a few rounds in various orders. These cards only serve as indications that the issue has been through these stages. In some cases, with multiple developers working on the same issue, some of these activities can occur in parallel, e.g., writing test assertions (in detail) may happen at the same time as coding. Comments may be made in the issue when some of these tasks are not needed for the issue.
     - "Writing Test Assertion" 
     - "Coding and Unit Testing"
     - "Implementing test script"
     - "Debugging" - This means excuting the test and may be also changing content associated with other tasks.
     - "Updating user guide"
  - Once, all of the above tasks are done, create a pull request.
  - If pull request is accepted, the person who merges the content close the issue. The issue automatically goes to the Close card.
  - If the pull request is not accepted, the developer may cycle through the above tasks again or simply put it back in the "Debugging" card.
  - If the merge causes failure in routine test, a new issue should be created that optionally citing the original issue.